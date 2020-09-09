package ec.com.newsolutions.domain.enumeration;

/**
 * The TaxTypeEnum enumeration.
 */
public enum TaxTypeEnum {
    IVA ("2"), ICE ("3");

    private String codeTax;

    TaxTypeEnum (String codeTax){
        this.codeTax = codeTax;
    }
    public String codeTax(String codeTax) {
        return codeTax;
    }
}
