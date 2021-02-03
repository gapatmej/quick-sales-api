package ec.com.newsolutions.service.errors.enumeration;

public enum ProccessElectronicDocument {
    GENERATE_XML ("Generate XML"),
    SIGN ("Sing");

    private String description;

    ProccessElectronicDocument (String description){
        this.description = description;
    }

    public String description() {
        return description;
    }
}
