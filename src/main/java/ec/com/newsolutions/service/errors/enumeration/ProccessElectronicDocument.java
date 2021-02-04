package ec.com.newsolutions.service.errors.enumeration;

public enum ProccessElectronicDocument {
    GENERATE_XML ("Generate XML"),
    SIGN ("Sing"),
    RECEPTION ("Reception"),
    AUTHORIZATION ("Authorization");

    private String description;

    ProccessElectronicDocument (String description){
        this.description = description;
    }

    public String description() {
        return description;
    }
}
