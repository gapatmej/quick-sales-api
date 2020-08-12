package ec.com.newsolutions.xml.jaxb.sri;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "detalle")
public class DetailJaxb {
    private String mainCode;
    private String auxiliaryCode;
    private String description;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal discount;
    private BigDecimal totalPriceWithoutTax;
    private TaxesJaxb taxesJaxb;

    @XmlElement(name = "codigoPrincipal")
    public String getMainCode() {
        return mainCode;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }

    @XmlElement(name = "codigoAuxiliar")
    public String getAuxiliaryCode() {
        return auxiliaryCode;
    }

    public void setAuxiliaryCode(String auxiliaryCode) {
        this.auxiliaryCode = auxiliaryCode;
    }

    @XmlElement(name = "descripcion")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "cantidad")
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @XmlElement(name = "precioUnitario")
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @XmlElement(name = "descuento")
    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @XmlElement(name = "precioTotalSinImpuesto")
    public BigDecimal getTotalPriceWithoutTax() {
        return totalPriceWithoutTax;
    }

    public void setTotalPriceWithoutTax(BigDecimal totalPriceWithoutTax) {
        this.totalPriceWithoutTax = totalPriceWithoutTax;
    }

    @XmlElement(name = "impuestos")
    public TaxesJaxb getTaxesJaxb() {
        return taxesJaxb;
    }

    public void setTaxesJaxb(TaxesJaxb taxesJaxb) {
        this.taxesJaxb = taxesJaxb;
    }
}
