package ec.com.newsolutions.domain.enumeration;

/**
 * The TimeUnitEnum enumeration.
 */
public enum TimeUnitEnum {
    DAY("dias"), MONTH("meses"), YEAR("años");

    private String value;

    TimeUnitEnum(String value){
        this.value=value;
    }

    public String value() {
        return value;
    }
}
