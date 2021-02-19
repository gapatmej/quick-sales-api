package ec.com.newsolutions.service.reports.impl;

import ec.com.newsolutions.domain.TributaryDocument;
import ec.com.newsolutions.domain.enumeration.ReportsEnum;
import ec.com.newsolutions.repository.InvoiceClientRepository;
import ec.com.newsolutions.service.dto.ReportInvoiceClientDTO;
import ec.com.newsolutions.service.impl.AbstractService;
import ec.com.newsolutions.service.reports.ReportInvoiceClientService;
import ec.com.newsolutions.utils.electronicdocuments.ElectronicDocumentsUtils;
import ec.com.newsolutions.utils.PathsUtils;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ReportInvoiceClientServiceImpl extends AbstractService implements ReportInvoiceClientService {

    private final InvoiceClientRepository invoiceClientRepository;

    public ReportInvoiceClientServiceImpl(InvoiceClientRepository invoiceClientRepository) {
        super(ReportInvoiceClientServiceImpl.class);
        this.invoiceClientRepository = invoiceClientRepository;
    }


    @Override
    public void execute(TributaryDocument tributaryDocument) {

        try{
            List<ReportInvoiceClientDTO> reportInvoiceClientDTOList = invoiceClientRepository.reportInvoiceClient(tributaryDocument.getId());
            File file = new File(PathsUtils.getReportsPathWithReportName(tributaryDocument.getOrganization().getId(), ReportsEnum.REPORT_INVOICE_CLIENT));

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reportInvoiceClientDTOList);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("organizationIdentification",tributaryDocument.getOrganization().getIdentification());
            parameters.put("organizationBusinessName",tributaryDocument.getOrganization().getBusinessName());
            parameters.put("organizationMainAddress",tributaryDocument.getOrganization().getAddress());
            parameters.put("organizationBranchAddress",tributaryDocument.getBranchOffice().getAddress());
            parameters.put("organizationSpecialTaxpayerNumber",tributaryDocument.getOrganization().getSpecialTaxpayerNumber());
            parameters.put("organizationKeepAccounting",tributaryDocument.getOrganization().getKeepAccounting());
            parameters.put("organizationLogo",PathsUtils.getResourcesPathWithFileName(tributaryDocument.getOrganization().getId(),tributaryDocument.getOrganization().getLogo()));

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, ElectronicDocumentsUtils.getAuthorizedPdfPathWithAccessKey(tributaryDocument));

        }catch (Exception ex){
            log.error(ex.getMessage());
        }

    }
}
