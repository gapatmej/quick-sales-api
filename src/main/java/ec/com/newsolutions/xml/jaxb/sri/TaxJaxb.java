package ec.com.newsolutions.xml.jaxb.sri;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "impuesto")
public class TaxJaxb {

    private int code;
    private Integer percentagecode;
    private Integer rate;
    private BigDecimal taxBase;
    private BigDecimal value;

    @XmlElement(name = "codigo")
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @XmlElement(name = "codigoPorcentaje")
    public Integer getPercentagecode() {
        return percentagecode;
    }

    public void setPercentagecode(Integer percentagecode) {
        this.percentagecode = percentagecode;
    }

    @XmlElement(name = "tarifa")
    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @XmlElement(name = "baseImponible")
    public BigDecimal getTaxBase() {
        return taxBase;
    }

    public void setTaxBase(BigDecimal taxBase) {
        this.taxBase = taxBase;
    }

    @XmlElement(name = "valor")
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
