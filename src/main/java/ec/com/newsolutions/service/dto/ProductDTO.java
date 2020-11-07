package ec.com.newsolutions.service.dto;

import ec.com.newsolutions.domain.enumeration.ProductTypeEnum;

import java.math.BigDecimal;

public class ProductDTO extends AbstractMainDTO {

    private Long organizationId;

    private String mainCode;

    private String auxiliaryCode;

    private String barcode;

    private ProductTypeEnum productType;

    private String name;

    private BigDecimal price;

    private String attribute1;

    private String attribute2;

    private String attribute3;

    private Long ivaId;

    private Long iceId;

    private Long categoryId;

    private Long cellarId;

    private Long unitId;

    private boolean active;

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getMainCode() {
        return mainCode;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }

    public String getAuxiliaryCode() {
        return auxiliaryCode;
    }

    public void setAuxiliaryCode(String auxiliaryCode) {
        this.auxiliaryCode = auxiliaryCode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public ProductTypeEnum getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeEnum productType) {
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public Long getIvaId() {
        return ivaId;
    }

    public void setIvaId(Long ivaId) {
        this.ivaId = ivaId;
    }

    public Long getIceId() {
        return iceId;
    }

    public void setIceId(Long iceId) {
        this.iceId = iceId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCellarId() {
        return cellarId;
    }

    public void setCellarId(Long cellarId) {
        this.cellarId = cellarId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
