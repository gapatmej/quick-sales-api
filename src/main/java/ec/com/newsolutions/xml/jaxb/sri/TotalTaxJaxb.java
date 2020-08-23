package ec.com.newsolutions.xml.jaxb.sri;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "totalImpuesto")
public class TotalTaxJaxb {
    private int code;
    private Integer percentageCode;
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
    public Integer getPercentageCode() {
        return percentageCode;
    }

    public void setPercentageCode(Integer percentageCode) {
        this.percentageCode = percentageCode;
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
