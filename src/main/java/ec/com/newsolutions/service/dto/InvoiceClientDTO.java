package ec.com.newsolutions.service.dto;

import ec.com.newsolutions.domain.enumeration.CurrencyEnum;
import ec.com.newsolutions.domain.enumeration.IdentificationTypeEnum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InvoiceClientDTO extends TributaryDocumentDTO {

    private BigDecimal totalWithoutTax;

    private BigDecimal totalDiscount;

    private BigDecimal totalBaseTaxIVA;

    private BigDecimal totalBaseTaxICE;

    private BigDecimal totalTaxIVA;

    private BigDecimal totalTaxICE;

    private BigDecimal tip;

    private BigDecimal total;

    private CurrencyEnum currency;

    private List<PaymentDTO> payments  = new ArrayList<>();

    private List<DetailInvoiceClientDTO> detailsInvoiceClient = new ArrayList<>();

    private List<AdditionalInformationDTO> additionalsInformation = new ArrayList<>();

    public BigDecimal getTotalWithoutTax() {
        return totalWithoutTax;
    }

    public void setTotalWithoutTax(BigDecimal totalWithoutTax) {
        this.totalWithoutTax = totalWithoutTax;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public BigDecimal getTotalBaseTaxIVA() {
        return totalBaseTaxIVA;
    }

    public void setTotalBaseTaxIVA(BigDecimal totalBaseTaxIVA) {
        this.totalBaseTaxIVA = totalBaseTaxIVA;
    }

    public BigDecimal getTotalBaseTaxICE() {
        return totalBaseTaxICE;
    }

    public void setTotalBaseTaxICE(BigDecimal totalBaseTaxICE) {
        this.totalBaseTaxICE = totalBaseTaxICE;
    }

    public BigDecimal getTotalTaxIVA() {
        return totalTaxIVA;
    }

    public void setTotalTaxIVA(BigDecimal totalTaxIVA) {
        this.totalTaxIVA = totalTaxIVA;
    }

    public BigDecimal getTotalTaxICE() {
        return totalTaxICE;
    }

    public void setTotalTaxICE(BigDecimal totalTaxICE) {
        this.totalTaxICE = totalTaxICE;
    }

    public BigDecimal getTip() {
        return tip;
    }

    public void setTip(BigDecimal tip) {
        this.tip = tip;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    public List<PaymentDTO> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentDTO> payments) {
        this.payments = payments;
    }

    public List<DetailInvoiceClientDTO> getDetailsInvoiceClient() {
        return detailsInvoiceClient;
    }

    public void setDetailsInvoiceClient(List<DetailInvoiceClientDTO> detailsInvoiceClient) {
        this.detailsInvoiceClient = detailsInvoiceClient;
    }

    public List<AdditionalInformationDTO> getAdditionalsInformation() {
        return additionalsInformation;
    }

    public void setAdditionalsInformation(List<AdditionalInformationDTO> additionalsInformation) {
        this.additionalsInformation = additionalsInformation;
    }
}
