package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.*;
import ec.com.newsolutions.service.ElectronicDocumentService;
import ec.com.newsolutions.service.SignatureXAdES_BES;
import ec.com.newsolutions.xml.jaxb.sri.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

@Service
@Transactional
public class ElectronicDocumentServiceImpl implements ElectronicDocumentService {

    private final Logger log = LoggerFactory.getLogger(InvoiceClientServiceImpl.class);

    private final SignatureXAdES_BES signatureXAdES_BES;

    public ElectronicDocumentServiceImpl(SignatureXAdES_BES signatureXAdES_BES) {
        this.signatureXAdES_BES = signatureXAdES_BES;
    }

    @Override
    public void generateXML(InvoiceClient invoiceClient) {
        try {
            InvoiceClientJaxb invoiceClientJaxb = new InvoiceClientJaxb();

            TributaryInformationJaxb tributaryInformationJaxb = new TributaryInformationJaxb();
            tributaryInformationJaxb.setEnvironment(invoiceClient.getElectronicDocument().getSriEnviroment().code());
            tributaryInformationJaxb.setEmissionType(invoiceClient.getElectronicDocument().getEmissionType().code());
            tributaryInformationJaxb.setBusinessName(invoiceClient.getElectronicDocument().getBusinessName());
            tributaryInformationJaxb.setTradename(invoiceClient.getElectronicDocument().getTradename());
            tributaryInformationJaxb.setIdentification(invoiceClient.getElectronicDocument().getIdentification());
            tributaryInformationJaxb.setAccessKey(invoiceClient.getElectronicDocument().getAccessKey());
            tributaryInformationJaxb.setCodeDocument(invoiceClient.getReceiptType().code());
            tributaryInformationJaxb.setEstablishmentCode(invoiceClient.getEstablishmentCode());
            tributaryInformationJaxb.setEmissionPointCode(invoiceClient.getEmissionPointCode());
            tributaryInformationJaxb.setSequence(invoiceClient.getSequence());
            tributaryInformationJaxb.setMatrizAddress(invoiceClient.getElectronicDocument().getMainAddress());
            invoiceClientJaxb.setTributaryInformationJaxb(tributaryInformationJaxb);

            InvoiceInformationJaxb invoiceInformationJaxb = new InvoiceInformationJaxb();
            invoiceInformationJaxb.setDateIssue(invoiceClient.getDateIssue().toString());
            invoiceInformationJaxb.setEstablishmentAddress(invoiceClient.getElectronicDocument().getEstablishmentAddress());
            invoiceInformationJaxb.setSpecialTaxpayer(invoiceClient.getElectronicDocument().getSpecialTaxpayerNumber());
            invoiceInformationJaxb.setObligedAccounting(invoiceClient.getElectronicDocument().isKeepAccounting()?"SI":"NO");
            invoiceInformationJaxb.setBuyerIdentificationType(invoiceClient.getIdentificationType().code());
            invoiceInformationJaxb.setBuyerBusinessName(invoiceClient.getBusinessName());
            invoiceInformationJaxb.setBuyerIdentification(invoiceClient.getIdentification());
            invoiceInformationJaxb.setBuyerAddress(invoiceClient.getAddress());
            invoiceInformationJaxb.setTotalWithoutTaxes(invoiceClient.getTotalTaxFree());
            invoiceInformationJaxb.setTotalDiscount(invoiceClient.getTotalDiscount());

            TotalWithTaxesJaxb totalWithTaxesJaxb = new TotalWithTaxesJaxb();
            for (TaxInvoice taxInvoice : invoiceClient.getTaxInvoices()){
                TotalTaxJaxb totalTaxJaxb = new TotalTaxJaxb();
                totalTaxJaxb.setCode(taxInvoice.getCode());
                totalTaxJaxb.setPercentageCode(taxInvoice.getPercentageCode());
                totalTaxJaxb.setTaxBase(taxInvoice.getTaxBase());
                totalTaxJaxb.setValue(taxInvoice.getAmount());
                totalWithTaxesJaxb.getTotalTaxJaxb().add(totalTaxJaxb);
            }
            invoiceInformationJaxb.setTotalWithTaxesJaxb(totalWithTaxesJaxb);

            invoiceInformationJaxb.setTip(invoiceClient.getTip());
            invoiceInformationJaxb.setTotal(invoiceClient.getTotal());
            invoiceInformationJaxb.setCurrency(invoiceClient.getCurrency().toString());

            PaymentsJaxb paymentsJaxb= new PaymentsJaxb();
            for (Payment payment : invoiceClient.getPayments()){
                PaymentJaxb paymentJaxb = new PaymentJaxb();
                paymentJaxb.setPayWay(payment.getWayPay().getCode());
                paymentJaxb.setTotal(payment.getAmount());
                paymentJaxb.setTimeUnit(payment.getTimeUnit().value());
                paymentsJaxb.getPayment().add(paymentJaxb);
            }
            invoiceInformationJaxb.setPaymentsJaxb(paymentsJaxb);
            invoiceClientJaxb.setInvoiceInformationJaxb(invoiceInformationJaxb);


            DetailsJaxb detailsJaxb = new DetailsJaxb();
            for (DetailInvoice detailInvoice : invoiceClient.getDetailInvoices()){
                DetailJaxb detailJaxb = new DetailJaxb();
                detailJaxb.setMainCode(detailInvoice.getMainCode());
                detailJaxb.setAuxiliaryCode(detailInvoice.getAuxiliaryCode());
                detailJaxb.setDescription(detailInvoice.getDescription());
                detailJaxb.setQuantity(detailInvoice.getQuantity());
                detailJaxb.setUnitPrice(detailInvoice.getUnitPrice());
                detailJaxb.setDiscount(detailInvoice.getDiscount());
                detailJaxb.setTotalPriceWithoutTax(detailInvoice.getTotal());

                TaxesJaxb taxesJaxb = new TaxesJaxb();
                for (TaxDetailInvoice taxDetailInvoice: detailInvoice.getTaxDetailInvoices()){
                    TaxJaxb taxJaxb = new TaxJaxb();
                    taxJaxb.setCode(taxDetailInvoice.getCode());
                    taxJaxb.setPercentagecode(taxDetailInvoice.getPercentageCode());
                    taxJaxb.setRate(taxDetailInvoice.getRate());
                    taxJaxb.setTaxBase(taxDetailInvoice.getTaxBase());
                    taxJaxb.setValue(taxDetailInvoice.getAmount());

                    taxesJaxb.getTaxJaxb().add(taxJaxb);
                }
                detailJaxb.setTaxesJaxb(taxesJaxb);
            }
            invoiceClientJaxb.setDetailsJaxb(detailsJaxb);

            AdditionalsInformationJaxb additionalsInformationJaxb = new AdditionalsInformationJaxb();
            for (AdditionalInformation additionalInformation: invoiceClient.getAdditionalsInformation()){
                additionalsInformationJaxb.getAdditionalInformation().add(additionalInformation.getValue());
            }
            invoiceClientJaxb.setAdditionalsInformationJaxb(additionalsInformationJaxb);

            JAXBContext context = JAXBContext.newInstance(InvoiceClientJaxb.class);

            Marshaller m = context.createMarshaller();
            m.marshal(invoiceClientJaxb, new File("D:\\electronicDocuments\\Invoice\\issued\\pruebas.xml"));

            this.signatureXAdES_BES.execute();


        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }

    public void firmar() {
     /*   SignatureXAdES_BESImpl xades = new SignatureXAdES_BESImpl(this);
        xades.execute();*/
    }
}
