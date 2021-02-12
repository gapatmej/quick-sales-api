package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.config.Constants;
import ec.com.newsolutions.domain.BranchOffice;
import ec.com.newsolutions.domain.DocumentAuthorization;
import ec.com.newsolutions.domain.ElectronicDocument;
import ec.com.newsolutions.domain.EmissionPoint;
import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.domain.Organization;
import ec.com.newsolutions.domain.TributaryDocument;
import ec.com.newsolutions.domain.enumeration.ReceiptTypeEnum;
import ec.com.newsolutions.domain.enumeration.SRIDocumentStateEnum;
import ec.com.newsolutions.repository.ElectronicDocumentRepository;
import ec.com.newsolutions.security.SecurityUtils;
import ec.com.newsolutions.service.BranchOfficeService;
import ec.com.newsolutions.service.DocumentAuthorizationService;
import ec.com.newsolutions.service.DocumentService;
import ec.com.newsolutions.service.ElectronicDocumentService;
import ec.com.newsolutions.service.EmissionPointService;
import ec.com.newsolutions.service.InvoiceClientService;
import ec.com.newsolutions.service.OrganizationService;
import ec.com.newsolutions.service.dto.WorkspaceDTO;
import ec.com.newsolutions.service.mapper.BranchOfficeMapper;
import ec.com.newsolutions.service.mapper.DocumentAuthorizationMapper;
import ec.com.newsolutions.service.mapper.DocumentMapper;
import ec.com.newsolutions.service.mapper.EmissionPointMapper;
import ec.com.newsolutions.service.mapper.OrganizationMapper;
import ec.com.newsolutions.utils.electronicdocuments.ElectronicDocumentsUtils;
import ec.com.newsolutions.web.rest.errors.EntityNotFoundException;
import ec.com.newsolutions.web.rest.errors.WorkspaceNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class ElectronicDocumentServiceImpl extends AbstractService implements ElectronicDocumentService {

    private final ElectronicDocumentRepository electronicDocumentRepository;
    private final OrganizationService organizationService;
    private final EmissionPointService emissionPointService;
    private final BranchOfficeService branchOfficeService;
    private final DocumentAuthorizationService documentAuthorizationService;

    private final DocumentAuthorizationMapper documentAuthorizationMapper;
    private final InvoiceClientService invoiceClientService;

    public ElectronicDocumentServiceImpl(ElectronicDocumentRepository electronicDocumentRepository, OrganizationService organizationService, EmissionPointService emissionPointService,
                                         BranchOfficeService branchOfficeService,
                                         DocumentAuthorizationService documentAuthorizationService, DocumentAuthorizationMapper documentAuthorizationMapper,
                                         @Lazy InvoiceClientService invoiceClientService) {
        super(ElectronicDocumentServiceImpl.class);
        this.electronicDocumentRepository = electronicDocumentRepository;
        this.organizationService = organizationService;
        this.emissionPointService = emissionPointService;
        this.branchOfficeService = branchOfficeService;
        this.documentAuthorizationService = documentAuthorizationService;
        this.documentAuthorizationMapper = documentAuthorizationMapper;
        this.invoiceClientService = invoiceClientService;
    }

    @Override
    public void build(TributaryDocument tributaryDocument) {
        Long emissionPointId = SecurityUtils.getCurrentWorkspace()
            .map(WorkspaceDTO::getEmissionPointId)
            .orElseThrow(() -> new WorkspaceNotFoundException(EmissionPoint.class.getSimpleName()));

        tributaryDocument.setOrganization(organizationService.findOne(tributaryDocument.getOrganization().getId())
            .orElseThrow(()-> new EntityNotFoundException(tributaryDocument.getOrganization().getId())));

        EmissionPoint emissionPoint = emissionPointService.findOne(emissionPointId)
            .orElseThrow(()-> new EntityNotFoundException(emissionPointId));

        Long branchOfficeId = SecurityUtils.getCurrentWorkspace()
            .map(WorkspaceDTO::getBranchOfficeId)
            .orElseThrow(() -> new WorkspaceNotFoundException(BranchOffice.class.getSimpleName()));
        BranchOffice branchOffice = branchOfficeService.findOne(branchOfficeId)
            .orElseThrow(()-> new EntityNotFoundException(branchOfficeId));

        tributaryDocument.setBranchOffice(branchOffice);
        tributaryDocument.getElectronicDocument().setSriEnvironment(tributaryDocument.getOrganization().getSriEnvironment());
        tributaryDocument.getElectronicDocument().setEmissionType(tributaryDocument.getOrganization().getEmissionType());
        tributaryDocument.setEstablishmentCode(branchOffice.getEstablishmentCode());
        tributaryDocument.setEmissionPointCode(emissionPoint.getEmissionPointCode());

        if(tributaryDocument.getId() == null){
            DocumentAuthorization documentAuthorization = documentAuthorizationService
                .findByDocumentIdAndEmissionPointId(tributaryDocument.getDocument().getId(),emissionPointId)
                .orElseThrow(()->new EntityNotFoundException(tributaryDocument.getDocument().getId()));
            documentAuthorization.setSequence(documentAuthorization.getSequence()+1);
            documentAuthorizationService.save(documentAuthorizationMapper.toDto(documentAuthorization));
            tributaryDocument.setSequence(documentAuthorization.getSequence());
        }else{
            tributaryDocument.setSequence(invoiceClientService.findOne(tributaryDocument.getId())
                .map(InvoiceClient::getSequence)
                .orElseThrow(()->new EntityNotFoundException(tributaryDocument.getId())));
        }


        if(tributaryDocument instanceof InvoiceClient){
            tributaryDocument.getElectronicDocument().setReceiptType(ReceiptTypeEnum.INVOICE);
        }
        tributaryDocument.getElectronicDocument().setSriDocumentState(SRIDocumentStateEnum.EMITTED);
        generateAccessKey(tributaryDocument.getOrganization(),tributaryDocument);

    }

    @Override
    public void updateSriDocumentState(SRIDocumentStateEnum sriDocumentStateEnum, ElectronicDocument electronicDocument) {
        this.electronicDocumentRepository.updateSriDocumentState(sriDocumentStateEnum,electronicDocument.getId());
    }

    @Override
    public ElectronicDocument save(ElectronicDocument electronicDocument) {
        return electronicDocumentRepository.save(electronicDocument);
    }

    @Override
    public Optional<ElectronicDocument> findOne(Long id) {
        throw new UnsupportedOperationException();
    }

    private void generateAccessKey(Organization organization, TributaryDocument tributaryDocument){

        StringBuilder accessKey = new StringBuilder();
        accessKey.append(Constants.accessKeyFormatDate.format(Date.from(tributaryDocument.getDateIssue())));
        accessKey.append(tributaryDocument.getElectronicDocument().getReceiptType().code());
        accessKey.append(organization.getIdentification());
        accessKey.append(organization.getSriEnvironment().code());
        accessKey.append(tributaryDocument.getEstablishmentCode());
        accessKey.append(tributaryDocument.getEmissionPointCode());
        accessKey.append(StringUtils.leftPad(String.valueOf(tributaryDocument.getSequence()),9,"0"));
        accessKey.append(Constants.numericCode);
        accessKey.append(tributaryDocument.getElectronicDocument().getEmissionType().code());
        accessKey.append(ElectronicDocumentsUtils.generateVerificationDigit(accessKey.toString()));

        tributaryDocument.getElectronicDocument().setAccessKey(accessKey.toString());
    }
}
