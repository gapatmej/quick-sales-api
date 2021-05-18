package ec.com.newsolutions.service.dto;

import ec.com.newsolutions.domain.DetailInvoiceClient;

import java.util.List;

public class BatchElectronicDocumentDTO {

    private String businessName;
    private String identification;
    private int count;

    public BatchElectronicDocumentDTO(String businessName, String identification, DetailInvoiceClient detailsInvoiceClient) {
        this.businessName = businessName;
        this.identification = identification;
        this.count = 2;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }
}
