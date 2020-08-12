package ec.com.newsolutions.xml.jaxb.sri;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "infoFactura")
public class InvoiceInformationJaxb {

    private String dateIssue;
    private String establishmentAddress;
    private String specialTaxpayer;
    private String obligedAccounting;
    private String buyerIdentificationType;
    private String referralGuide;
    private String buyerBusinessName;
    private String buyerIdentification;
    private String buyerAddress;
    private BigDecimal totalWithoutTaxes;
    private BigDecimal totalDiscount;
    private TotalWithTaxesJaxb totalWithTaxesJaxb;
    private BigDecimal tip;
    private BigDecimal total;
    private String currency;
    private PaymentsJaxb paymentsJaxb;

    @XmlElement(name = "fechaEmision")
    public String getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(String dateIssue) {
        this.dateIssue = dateIssue;
    }

    @XmlElement(name = "dirEstablecimiento")
    public String getEstablishmentAddress() {
        return establishmentAddress;
    }

    public void setEstablishmentAddress(String establishmentAddress) {
        this.establishmentAddress = establishmentAddress;
    }

    @XmlElement(name = "contribuyenteEspecial")
    public String getSpecialTaxpayer() {
        return specialTaxpayer;
    }

    public void setSpecialTaxpayer(String specialTaxpayer) {
        this.specialTaxpayer = specialTaxpayer;
    }

    @XmlElement(name = "obligadoContabilidad")
    public String getObligedAccounting() {
        return obligedAccounting;
    }

    public void setObligedAccounting(String obligedAccounting) {
        this.obligedAccounting = obligedAccounting;
    }

    @XmlElement(name = "tipoIdentificacionComprador")
    public String getBuyerIdentificationType() {
        return buyerIdentificationType;
    }

    public void setBuyerIdentificationType(String buyerIdentificationType) {
        this.buyerIdentificationType = buyerIdentificationType;
    }

    @XmlElement(name = "guiaRemision")
    public String getReferralGuide() {
        return referralGuide;
    }

    public void setReferralGuide(String referralGuide) {
        this.referralGuide = referralGuide;
    }

    @XmlElement(name = "razonSocialComprador")
    public String getBuyerBusinessName() {
        return buyerBusinessName;
    }

    public void setBuyerBusinessName(String buyerBusinessName) {
        this.buyerBusinessName = buyerBusinessName;
    }

    @XmlElement(name = "identificacionComprador")
    public String getBuyerIdentification() {
        return buyerIdentification;
    }

    public void setBuyerIdentification(String buyerIdentification) {
        this.buyerIdentification = buyerIdentification;
    }

    @XmlElement(name = "direccionComprador")
    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    @XmlElement(name = "totalSinImpuestos")
    public BigDecimal getTotalWithoutTaxes() {
        return totalWithoutTaxes;
    }

    public void setTotalWithoutTaxes(BigDecimal totalWithoutTaxes) {
        this.totalWithoutTaxes = totalWithoutTaxes;
    }

    @XmlElement(name = "totalDescuento")
    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    @XmlElement(name="totalConImpuestos")
    public TotalWithTaxesJaxb getTotalWithTaxesJaxb() {
        return totalWithTaxesJaxb;
    }

    public void setTotalWithTaxesJaxb(TotalWithTaxesJaxb totalWithTaxesJaxb) {
        this.totalWithTaxesJaxb = totalWithTaxesJaxb;
    }

    @XmlElement(name = "propina")
    public BigDecimal getTip() {
        return tip;
    }

    public void setTip(BigDecimal tip) {
        this.tip = tip;
    }

    @XmlElement(name = "importeTotal")
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @XmlElement(name = "moneda")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @XmlElement(name = "pagos")
    public PaymentsJaxb getPaymentsJaxb() {
        return paymentsJaxb;
    }

    public void setPaymentsJaxb(PaymentsJaxb paymentsJaxb) {
        this.paymentsJaxb = paymentsJaxb;
    }
}
