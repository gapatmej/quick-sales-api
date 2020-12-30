package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.config.Constants;
import ec.com.newsolutions.domain.BranchOffice;
import ec.com.newsolutions.domain.DocumentAuthorization;
import ec.com.newsolutions.domain.ElectronicDocument;
import ec.com.newsolutions.domain.EmissionPoint;
import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.domain.Organization;
import ec.com.newsolutions.domain.enumeration.ReceiptTypeEnum;
import ec.com.newsolutions.security.SecurityUtils;
import ec.com.newsolutions.service.BranchOfficeService;
import ec.com.newsolutions.service.DocumentAuthorizationService;
import ec.com.newsolutions.service.DocumentService;
import ec.com.newsolutions.service.ElectronicDocumentService;
import ec.com.newsolutions.service.EmissionPointService;
import ec.com.newsolutions.service.OrganizationService;
import ec.com.newsolutions.service.dto.WorkspaceDTO;
import ec.com.newsolutions.service.mapper.BranchOfficeMapper;
import ec.com.newsolutions.service.mapper.DocumentAuthorizationMapper;
import ec.com.newsolutions.service.mapper.DocumentMapper;
import ec.com.newsolutions.service.mapper.EmissionPointMapper;
import ec.com.newsolutions.service.mapper.OrganizationMapper;
import ec.com.newsolutions.web.rest.errors.EntityNotFoundException;
import ec.com.newsolutions.web.rest.errors.WorkspaceNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class ElectronicDocumentServiceImpl extends AbstractService implements ElectronicDocumentService {

    private final OrganizationService organizationService;
    private final EmissionPointService emissionPointService;
    private final BranchOfficeService branchOfficeService;
    private final DocumentService documentService;
    private final DocumentAuthorizationService documentAuthorizationService;

    private final OrganizationMapper organizationMapper;
    private final EmissionPointMapper emissionPointMapper;
    private final BranchOfficeMapper branchOfficeMapper;
    private final DocumentMapper documentMapper;
    private final DocumentAuthorizationMapper documentAuthorizationMapper;

    public ElectronicDocumentServiceImpl(OrganizationService organizationService, EmissionPointService emissionPointService, BranchOfficeService branchOfficeService, DocumentService documentService, DocumentAuthorizationService documentAuthorizationService, OrganizationMapper organizationMapper, EmissionPointMapper emissionPointMapper, BranchOfficeMapper branchOfficeMapper, DocumentMapper documentMapper, DocumentAuthorizationMapper documentAuthorizationMapper) {
        super(ElectronicDocumentServiceImpl.class);
        this.organizationService = organizationService;
        this.emissionPointService = emissionPointService;
        this.branchOfficeService = branchOfficeService;
        this.documentService = documentService;
        this.documentAuthorizationService = documentAuthorizationService;
        this.organizationMapper = organizationMapper;
        this.emissionPointMapper = emissionPointMapper;
        this.branchOfficeMapper = branchOfficeMapper;
        this.documentMapper = documentMapper;
        this.documentAuthorizationMapper = documentAuthorizationMapper;
    }

    @Override
    public void build(ElectronicDocument electronicDocument) {
        Long emissionPointId = SecurityUtils.getCurrentWorkspace()
            .map(WorkspaceDTO::getEmissionPointId)
            .orElseThrow(() -> new WorkspaceNotFoundException(EmissionPoint.class.getSimpleName()));

        Long branchOfficeId = SecurityUtils.getCurrentWorkspace()
            .map(WorkspaceDTO::getBranchOfficeId)
            .orElseThrow(() -> new WorkspaceNotFoundException(BranchOffice.class.getSimpleName()));

        Organization organization = organizationService.findOne(electronicDocument.getOrganization().getId())
            .map(organizationMapper::toEntity).orElseThrow(()-> new EntityNotFoundException(electronicDocument.getOrganization().getId()));

        EmissionPoint emissionPoint = emissionPointService.findOne(emissionPointId)
            .map(emissionPointMapper::toEntity).orElseThrow(()-> new EntityNotFoundException(emissionPointId));

        BranchOffice branchOffice = branchOfficeService.findOneLight(branchOfficeId)
            .map(branchOfficeMapper::toEntity).orElseThrow(()-> new EntityNotFoundException(branchOfficeId));

        DocumentAuthorization documentAuthorization = documentAuthorizationService
            .findByDocumentIdAndEmissionPointId(electronicDocument.getDocument().getId(),emissionPointId)
            .map(documentAuthorizationMapper::toEntity).orElseThrow(()->new EntityNotFoundException(electronicDocument.getDocument().getId()));

        documentAuthorization.setSequence(documentAuthorization.getSequence()+1);
        documentAuthorizationService.save(documentAuthorizationMapper.toDto(documentAuthorization));

      /*  electronicDocument.setOrganization(organization);
        electronicDocument.setEmissionPoint(emissionPoint);*/
        electronicDocument.setSriEnvironment(organization.getSriEnvironment());
        electronicDocument.setEmissionType(organization.getEmissionType());
        electronicDocument.setEstablishmentCode(branchOffice.getEstablishmentCode());
        electronicDocument.setEmissionPointCode(emissionPoint.getEmissionPointCode());
        electronicDocument.setSequence(documentAuthorization.getSequence());

        if(electronicDocument instanceof InvoiceClient){
            electronicDocument.setReceiptType(ReceiptTypeEnum.INVOICE);
        }
        generateAccessKey(organization,electronicDocument);

    }

    private void generateAccessKey(Organization organization, ElectronicDocument electronicDocument){

        StringBuilder accessKey = new StringBuilder();
        accessKey.append(Constants.accessKeyFormatDate.format(Date.from(electronicDocument.getDateIssue())));
        accessKey.append(electronicDocument.getReceiptType().code());
        accessKey.append(organization.getIdentification());
        accessKey.append(organization.getSriEnvironment().code());
        accessKey.append(electronicDocument.getEstablishmentCode());
        accessKey.append(electronicDocument.getEmissionPointCode());
        accessKey.append(StringUtils.leftPad(String.valueOf(electronicDocument.getSequence()),9,"0"));
        accessKey.append(Constants.numericCode);
        accessKey.append(electronicDocument.getEmissionType().code());
        accessKey.append(generateVerificationDigit(accessKey.toString()));

        electronicDocument.setAccessKey(accessKey.toString());
    }

    private int generateVerificationDigit(String accessKey) {

        int baseMultiplier = 7;
        int[] aux = new int[accessKey.length()];
        int multiplier = 2;
        int total = 0;
        int checker = 0;
        for (int i = aux.length - 1; i >= 0; --i) {
            aux[i] = Integer.parseInt("" + accessKey.charAt(i));
            aux[i] *= multiplier;
            ++multiplier;
            if (multiplier > baseMultiplier) {
                multiplier = 2;
            }
            total += aux[i];
        }

        if ((total == 0) || (total == 1))
            checker = 0;
        else {
            checker = (11 - (total % 11) == 11) ? 0 : 11 - (total % 11);
        }

        if (checker == 10) {
            checker = 1;
        }

        return checker;
    }


}
