package ec.com.newsolutions.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

import ec.com.newsolutions.domain.enumeration.ProductTypeEnum;

@Entity
@Table(name = "product")
public class Product extends AbstractMainEntity  implements Serializable {

    @Column(name = "main_code", length = 50, nullable = false, unique = true)
    private String mainCode;

    @Column(name = "auxiliary_code", length = 50, unique = true)
    private String auxiliaryCode;

    @Column(name = "barcode", length = 50, unique = true)
    private String barcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type", nullable = false)
    private ProductTypeEnum productType;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "price", precision = 9, scale = 4, nullable = false)
    private BigDecimal price;

    @Column(name = "attribute_1", length = 100)
    private String attribute1;

    @Column(name = "attribute_2", length = 100)
    private String attribute2;

    @Column(name = "attribute_3", length = 100)
    private String attribute3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tax_iva_id", nullable = false)
    private Tax iva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name="tax_ice_id")
    private Tax ice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id" , nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cellar_id", nullable = false)
    private Cellar cellar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="unit_id", nullable = false)
    private Unit unit;

    @Column(name = "active", nullable = false)
    private Boolean active;

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

    public Tax getIva() {
        return iva;
    }

    public void setIva(Tax iva) {
        this.iva = iva;
    }

    public Tax getIce() {
        return ice;
    }

    public void setIce(Tax ice) {
        this.ice = ice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Cellar getCellar() {
        return cellar;
    }

    public void setCellar(Cellar cellar) {
        this.cellar = cellar;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
