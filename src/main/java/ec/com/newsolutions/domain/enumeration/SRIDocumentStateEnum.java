package ec.com.newsolutions.domain.enumeration;

public enum SRIDocumentStateEnum {

    EMITTED("EMITIDO"),
    RETURNED("DEVUELTA"),
    RECEIVED("RECIBIDO"),
    AUTHORIZED("AUTORIZADO"),
    NO_AUTHORIZED("NO AUTORIZADO");

    private final String state;

    SRIDocumentStateEnum(String state){
        this.state = state;
    }

    public String state() {
        return state;
    }
}
