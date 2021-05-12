package ec.com.newsolutions.domain.enumeration;

public enum SRIDocumentStateEnum {

    EMITTED("EMITIDO"),
    RETURNED("DEVUELTA"),
    RECEIVED("RECIBIDA"),
    AUTHORIZED("AUTORIZADO"),
    NO_AUTHORIZED("NO AUTORIZADO");

    private final String state;

    SRIDocumentStateEnum(String state){
        this.state = state;
    }

    public String state() {
        return state;
    }

    public static SRIDocumentStateEnum getByState(String state) {
        for (SRIDocumentStateEnum i : values()) {
            if (i.state.equals(state)) {
                return i;
            }
        }
        throw new IllegalArgumentException(String.valueOf(state));
    }
}
