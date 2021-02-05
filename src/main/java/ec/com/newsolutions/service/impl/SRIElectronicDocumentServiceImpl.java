package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.config.ApplicationProperties;
import ec.com.newsolutions.domain.AdditionalInformation;
import ec.com.newsolutions.domain.DetailInvoiceClient;
import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.domain.Payment;
import ec.com.newsolutions.domain.TaxDetailInvoice;
import ec.com.newsolutions.domain.TaxInvoice;
import ec.com.newsolutions.service.SRIElectronicDocumentService;
import ec.com.newsolutions.service.SignatureXAdES;
import ec.com.newsolutions.service.errors.ElectronicDocumentException;
import ec.com.newsolutions.service.errors.enumeration.ProccessElectronicDocument;
import ec.com.newsolutions.utils.electronicdocuments.ElectronicDocumentsUtils;
import ec.com.newsolutions.utils.electronicdocuments.Signature;
import ec.com.newsolutions.xml.jaxb.sri.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

@Service
@Transactional
public class SRIElectronicDocumentServiceImpl extends AbstractService implements SRIElectronicDocumentService {

    private final SignatureXAdES signatureXAdES;
    private final ApplicationProperties applicationProperties;

    public SRIElectronicDocumentServiceImpl(SignatureXAdES signatureXAdES, ApplicationProperties applicationProperties) {
        super(SRIElectronicDocumentServiceImpl.class);
        this.signatureXAdES = signatureXAdES;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public void generateXML(InvoiceClient invoiceClient) throws ElectronicDocumentException {
        try {

            InvoiceClientJaxb invoiceClientJaxb = new InvoiceClientJaxb();

            TributaryInformationJaxb tributaryInformationJaxb = new TributaryInformationJaxb();
            tributaryInformationJaxb.setEnvironment(invoiceClient.getSriEnvironment().code());
            tributaryInformationJaxb.setEmissionType(invoiceClient.getEmissionType().code());
            tributaryInformationJaxb.setBusinessName(invoiceClient.getOrganization().getBusinessName());
            tributaryInformationJaxb.setTradename(invoiceClient.getOrganization().getTradename());
            tributaryInformationJaxb.setIdentification(invoiceClient.getOrganization().getIdentification());
            tributaryInformationJaxb.setAccessKey(invoiceClient.getAccessKey());
            tributaryInformationJaxb.setCodeDocument(invoiceClient.getReceiptType().code());
            tributaryInformationJaxb.setEstablishmentCode(invoiceClient.getEstablishmentCode());
            tributaryInformationJaxb.setEmissionPointCode(invoiceClient.getEmissionPointCode());
            tributaryInformationJaxb.setSequence(String.format("%09d", invoiceClient.getSequence()));
            tributaryInformationJaxb.setMatrizAddress(invoiceClient.getOrganization().getAddress());
            invoiceClientJaxb.setTributaryInformationJaxb(tributaryInformationJaxb);

            InvoiceInformationJaxb invoiceInformationJaxb = new InvoiceInformationJaxb();
            invoiceInformationJaxb.setDateIssue(invoiceClient.getDateIssue().toString());
            invoiceInformationJaxb.setEstablishmentAddress(invoiceClient.getOrganization().getAddress());
            invoiceInformationJaxb.setSpecialTaxpayer(String.valueOf(invoiceClient.getOrganization().getSpecialTaxpayerNumber()));
            invoiceInformationJaxb.setObligedAccounting(invoiceClient.getOrganization().getKeepAccounting() ? "SI" : "NO");
            invoiceInformationJaxb.setBuyerIdentificationType(invoiceClient.getIdentificationType().code());
            invoiceInformationJaxb.setBuyerBusinessName(invoiceClient.getBusinessName());
            invoiceInformationJaxb.setBuyerIdentification(invoiceClient.getIdentification());
            invoiceInformationJaxb.setBuyerAddress(invoiceClient.getAddress());
            invoiceInformationJaxb.setTotalWithoutTaxes(invoiceClient.getTotalWithoutTax());
            invoiceInformationJaxb.setTotalDiscount(invoiceClient.getTotalDiscount());

            TotalWithTaxesJaxb totalWithTaxesJaxb = new TotalWithTaxesJaxb();
            for (TaxInvoice taxInvoice : invoiceClient.getTaxesInvoice()) {
                TotalTaxJaxb totalTaxJaxb = new TotalTaxJaxb();
                totalTaxJaxb.setCode(taxInvoice.getCode());
                totalTaxJaxb.setPercentageCode(Integer.valueOf(taxInvoice.getPercentageCode()));
                totalTaxJaxb.setTaxBase(taxInvoice.getTaxBase());
                totalTaxJaxb.setValue(taxInvoice.getAmount());
                totalWithTaxesJaxb.getTotalTaxJaxb().add(totalTaxJaxb);
            }
            invoiceInformationJaxb.setTotalWithTaxesJaxb(totalWithTaxesJaxb);

            invoiceInformationJaxb.setTip(invoiceClient.getTip());
            invoiceInformationJaxb.setTotal(invoiceClient.getTotal());
            invoiceInformationJaxb.setCurrency(invoiceClient.getCurrency().toString());

            PaymentsJaxb paymentsJaxb = new PaymentsJaxb();
            for (Payment payment : invoiceClient.getPayments()) {
                PaymentJaxb paymentJaxb = new PaymentJaxb();
                paymentJaxb.setPayWay(payment.getPayWay().getCode());
                paymentJaxb.setTotal(payment.getAmount());
                paymentJaxb.setTimeUnit(payment.getTimeUnit().value());
                paymentsJaxb.getPayment().add(paymentJaxb);
            }
            invoiceInformationJaxb.setPaymentsJaxb(paymentsJaxb);
            invoiceClientJaxb.setInvoiceInformationJaxb(invoiceInformationJaxb);


            DetailsJaxb detailsJaxb = new DetailsJaxb();
            for (DetailInvoiceClient detailInvoice : invoiceClient.getDetailsInvoiceClient()) {
                DetailJaxb detailJaxb = new DetailJaxb();
                detailsJaxb.getDetailJaxb().add(detailJaxb);

                detailJaxb.setMainCode(detailInvoice.getMainCode());
                detailJaxb.setAuxiliaryCode(detailInvoice.getAuxiliaryCode());
                detailJaxb.setDescription(detailInvoice.getDescription());
                detailJaxb.setQuantity(detailInvoice.getQuantity());
                detailJaxb.setUnitPrice(detailInvoice.getUnitPrice());
                detailJaxb.setDiscount(detailInvoice.getDiscount());
                detailJaxb.setTotalPriceWithoutTax(detailInvoice.getTotal());

                TaxesJaxb taxesJaxb = new TaxesJaxb();
                for (TaxDetailInvoice taxDetailInvoice : detailInvoice.getTaxesDetailInvoice()) {
                    TaxJaxb taxJaxb = new TaxJaxb();
                    taxJaxb.setCode(taxDetailInvoice.getCode());
                    taxJaxb.setPercentagecode(taxDetailInvoice.getPercentageCode());
                    taxJaxb.setRate((int) Math.round(taxDetailInvoice.getRate()));
                    taxJaxb.setTaxBase(taxDetailInvoice.getTaxBase());
                    taxJaxb.setValue(taxDetailInvoice.getAmount());

                    taxesJaxb.getTaxJaxb().add(taxJaxb);
                }
                detailJaxb.setTaxesJaxb(taxesJaxb);
            }
            invoiceClientJaxb.setDetailsJaxb(detailsJaxb);

            AdditionalsInformationJaxb additionalsInformationJaxb = new AdditionalsInformationJaxb();
            for (AdditionalInformation additionalInformation : invoiceClient.getAdditionalsInformation()) {
                additionalsInformationJaxb.getAdditionalInformation().add(additionalInformation.getValue());
            }
            invoiceClientJaxb.setAdditionalsInformationJaxb(additionalsInformationJaxb);

            JAXBContext context = JAXBContext.newInstance(InvoiceClientJaxb.class);
            Marshaller m = context.createMarshaller();
            m.marshal(invoiceClientJaxb, new File(ElectronicDocumentsUtils.getXMlPathWithAccessKey(invoiceClient)));

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ElectronicDocumentException(ProccessElectronicDocument.GENERATE_XML,invoiceClient.getAccessKey(),e.getMessage());
        }

    }

    @Override
    public void sign(Signature signature) throws ElectronicDocumentException {
        try {
            this.signatureXAdES.execute(signature);
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new ElectronicDocumentException(ProccessElectronicDocument.SIGN,signature.getSignedPath(),e.getMessage());
        }

    }

    @Override
    public void reception() throws ElectronicDocumentException {
        try {
            //
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new ElectronicDocumentException(ProccessElectronicDocument.RECEPTION,"",e.getMessage());
        }
    }


}
