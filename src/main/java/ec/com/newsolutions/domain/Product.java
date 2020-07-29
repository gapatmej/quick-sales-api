package ec.com.newsolutions.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;

import ec.com.newsolutions.domain.enumeration.ProductTypeEnum;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "main_code", nullable = false, unique = true)
    private String mainCode;

    @Column(name = "auxiliary_code")
    private String auxiliaryCode;

    @Column(name = "barcode")
    private String barcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    private ProductTypeEnum productType;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "price", precision = 21, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "attribute_1")
    private String attribute1;

    @Column(name = "attribute_2")
    private String attribute2;

    @Column(name = "attribute_3")
    private String attribute3;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name="tax_iva_id", nullable = false)
    private Tax iva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name="tax_ice_id")
    private Tax ice;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name="category_id" , nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name="sub_category_id", nullable = false)
    private SubCategory subCategory;

    @JsonIgnoreProperties("products")
    @JoinColumn(name="cellar_id")
    private Cellar cellar;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMainCode() {
        return mainCode;
    }

    public Product mainCode(String mainCode) {
        this.mainCode = mainCode;
        return this;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }

    public String getAuxiliaryCode() {
        return auxiliaryCode;
    }

    public Product auxiliaryCode(String auxiliaryCode) {
        this.auxiliaryCode = auxiliaryCode;
        return this;
    }

    public void setAuxiliaryCode(String auxiliaryCode) {
        this.auxiliaryCode = auxiliaryCode;
    }

    public String getBarcode() {
        return barcode;
    }

    public Product barcode(String barcode) {
        this.barcode = barcode;
        return this;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public ProductTypeEnum getProductType() {
        return productType;
    }

    public Product productType(ProductTypeEnum productType) {
        this.productType = productType;
        return this;
    }

    public void setProductType(ProductTypeEnum productType) {
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public Product name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public Product attribute1(String attribute1) {
        this.attribute1 = attribute1;
        return this;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public Product attribute2(String attribute2) {
        this.attribute2 = attribute2;
        return this;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public Product attribute3(String attribute3) {
        this.attribute3 = attribute3;
        return this;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public Tax getIva() {
        return iva;
    }

    public Product iva(Tax tax) {
        this.iva = tax;
        return this;
    }

    public void setIva(Tax tax) {
        this.iva = tax;
    }

    public Tax getIce() {
        return ice;
    }

    public Product ice(Tax tax) {
        this.ice = tax;
        return this;
    }

    public void setIce(Tax tax) {
        this.ice = tax;
    }

    public Category getCategory() {
        return category;
    }

    public Product category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public Product subCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Cellar getCellar() {
        return cellar;
    }

    public Product cellar(Cellar cellar) {
        this.cellar = cellar;
        return this;
    }

    public void setCellar(Cellar cellar) {
        this.cellar = cellar;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", mainCode='" + getMainCode() + "'" +
            ", auxiliaryCode='" + getAuxiliaryCode() + "'" +
            ", barcode='" + getBarcode() + "'" +
            ", productType='" + getProductType() + "'" +
            ", name='" + getName() + "'" +
            ", price=" + getPrice() +
            ", attribute1='" + getAttribute1() + "'" +
            ", attribute2='" + getAttribute2() + "'" +
            ", attribute3='" + getAttribute3() + "'" +
            "}";
    }
}
