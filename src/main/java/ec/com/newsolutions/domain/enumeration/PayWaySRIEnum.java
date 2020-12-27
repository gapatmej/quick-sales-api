package ec.com.newsolutions.domain.enumeration;

public enum PayWaySRIEnum {
    WITHOUT_FINANCIAL_SYSTEM ("01","SIN UTILIZACION DEL SISTEMA FINANCIERO"),
    DEBTS_COMPENSATION ("15","COMPENSACIÓN DE DEUDAS"),
    DEBIT ("16","TARJETA DE DÉBITO"),
    ELECTRONIC_MONEY ("17","DINERO ELECTRONICO"),
    PREPAID_CARD ("18","TARJETA PREPAGO"),
    CREDIT_CARD ("19","TARJETA DE CREDITO"),
    OTHERS_USING_FINANCIAL_SYSTEM ("20","OTROS CON UTILIZACION DEL SISTEMA FINANCIERO"),
    TITLES_ENDORSEMENT ("21","ENDOSO DE TÍTULOS");

    private final String code;
    private final String receipt;

    PayWaySRIEnum(String code, String receipt) {
        this.code = code;
        this.receipt = receipt;
    }

    public String code() {
        return code;
    }
    public String receipt() {
        return receipt;
    }
}
