package ec.com.newsolutions.service.dto;

import java.io.Serializable;

public class ReportUnitDTO implements Serializable {

    private String code;
    private String name;
    private String description;
    private String cliente;

    public ReportUnitDTO(String code, String name, String description, String cliente) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.cliente = cliente;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
