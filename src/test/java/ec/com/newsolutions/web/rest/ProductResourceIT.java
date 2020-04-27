package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.QuickSalesApiApp;
import ec.com.newsolutions.domain.Product;
import ec.com.newsolutions.domain.Tax;
import ec.com.newsolutions.domain.Category;
import ec.com.newsolutions.domain.SubCategory;
import ec.com.newsolutions.repository.ProductRepository;
import ec.com.newsolutions.service.ProductService;
import ec.com.newsolutions.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static ec.com.newsolutions.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ec.com.newsolutions.domain.enumeration.ProductTypeEnum;
/**
 * Integration tests for the {@link ProductResource} REST controller.
 */
@SpringBootTest(classes = QuickSalesApiApp.class)
public class ProductResourceIT {

    private static final String DEFAULT_MAIN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MAIN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_AUXILIARY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_AUXILIARY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BARCODE = "AAAAAAAAAA";
    private static final String UPDATED_BARCODE = "BBBBBBBBBB";

    private static final ProductTypeEnum DEFAULT_PRODUCT_TYPE = ProductTypeEnum.GOOD;
    private static final ProductTypeEnum UPDATED_PRODUCT_TYPE = ProductTypeEnum.SERVICE;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(0);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(1);

    private static final String DEFAULT_ATTRIBUTE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUTE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUTE_3 = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_3 = "BBBBBBBBBB";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restProductMockMvc;

    private Product product;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductResource productResource = new ProductResource(productService);
        this.restProductMockMvc = MockMvcBuilders.standaloneSetup(productResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Product createEntity(EntityManager em) {
        Product product = new Product()
            .mainCode(DEFAULT_MAIN_CODE)
            .auxiliaryCode(DEFAULT_AUXILIARY_CODE)
            .barcode(DEFAULT_BARCODE)
            .productType(DEFAULT_PRODUCT_TYPE)
            .name(DEFAULT_NAME)
            .price(DEFAULT_PRICE)
            .attribute1(DEFAULT_ATTRIBUTE_1)
            .attribute2(DEFAULT_ATTRIBUTE_2)
            .attribute3(DEFAULT_ATTRIBUTE_3);
        // Add required entity
        Tax tax;
        if (TestUtil.findAll(em, Tax.class).isEmpty()) {
            tax = TaxResourceIT.createEntity(em);
            em.persist(tax);
            em.flush();
        } else {
            tax = TestUtil.findAll(em, Tax.class).get(0);
        }
        product.setIva(tax);
        // Add required entity
        Category category;
        if (TestUtil.findAll(em, Category.class).isEmpty()) {
            category = CategoryResourceIT.createEntity(em);
            em.persist(category);
            em.flush();
        } else {
            category = TestUtil.findAll(em, Category.class).get(0);
        }
        product.setCategory(category);
        // Add required entity
        SubCategory subCategory;
        if (TestUtil.findAll(em, SubCategory.class).isEmpty()) {
            subCategory = SubCategoryResourceIT.createEntity(em);
            em.persist(subCategory);
            em.flush();
        } else {
            subCategory = TestUtil.findAll(em, SubCategory.class).get(0);
        }
        product.setSubCategory(subCategory);
        return product;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Product createUpdatedEntity(EntityManager em) {
        Product product = new Product()
            .mainCode(UPDATED_MAIN_CODE)
            .auxiliaryCode(UPDATED_AUXILIARY_CODE)
            .barcode(UPDATED_BARCODE)
            .productType(UPDATED_PRODUCT_TYPE)
            .name(UPDATED_NAME)
            .price(UPDATED_PRICE)
            .attribute1(UPDATED_ATTRIBUTE_1)
            .attribute2(UPDATED_ATTRIBUTE_2)
            .attribute3(UPDATED_ATTRIBUTE_3);
        // Add required entity
        Tax tax;
        if (TestUtil.findAll(em, Tax.class).isEmpty()) {
            tax = TaxResourceIT.createUpdatedEntity(em);
            em.persist(tax);
            em.flush();
        } else {
            tax = TestUtil.findAll(em, Tax.class).get(0);
        }
        product.setIva(tax);
        // Add required entity
        Category category;
        if (TestUtil.findAll(em, Category.class).isEmpty()) {
            category = CategoryResourceIT.createUpdatedEntity(em);
            em.persist(category);
            em.flush();
        } else {
            category = TestUtil.findAll(em, Category.class).get(0);
        }
        product.setCategory(category);
        // Add required entity
        SubCategory subCategory;
        if (TestUtil.findAll(em, SubCategory.class).isEmpty()) {
            subCategory = SubCategoryResourceIT.createUpdatedEntity(em);
            em.persist(subCategory);
            em.flush();
        } else {
            subCategory = TestUtil.findAll(em, SubCategory.class).get(0);
        }
        product.setSubCategory(subCategory);
        return product;
    }

    @BeforeEach
    public void initTest() {
        product = createEntity(em);
    }

    @Test
    @Transactional
    public void createProduct() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product
        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isCreated());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate + 1);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getMainCode()).isEqualTo(DEFAULT_MAIN_CODE);
        assertThat(testProduct.getAuxiliaryCode()).isEqualTo(DEFAULT_AUXILIARY_CODE);
        assertThat(testProduct.getBarcode()).isEqualTo(DEFAULT_BARCODE);
        assertThat(testProduct.getProductType()).isEqualTo(DEFAULT_PRODUCT_TYPE);
        assertThat(testProduct.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProduct.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testProduct.getAttribute1()).isEqualTo(DEFAULT_ATTRIBUTE_1);
        assertThat(testProduct.getAttribute2()).isEqualTo(DEFAULT_ATTRIBUTE_2);
        assertThat(testProduct.getAttribute3()).isEqualTo(DEFAULT_ATTRIBUTE_3);
    }

    @Test
    @Transactional
    public void createProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product with an existing ID
        product.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMainCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setMainCode(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setName(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setPrice(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProducts() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList
        restProductMockMvc.perform(get("/api/products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(product.getId().intValue())))
            .andExpect(jsonPath("$.[*].mainCode").value(hasItem(DEFAULT_MAIN_CODE)))
            .andExpect(jsonPath("$.[*].auxiliaryCode").value(hasItem(DEFAULT_AUXILIARY_CODE)))
            .andExpect(jsonPath("$.[*].barcode").value(hasItem(DEFAULT_BARCODE)))
            .andExpect(jsonPath("$.[*].productType").value(hasItem(DEFAULT_PRODUCT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].attribute1").value(hasItem(DEFAULT_ATTRIBUTE_1)))
            .andExpect(jsonPath("$.[*].attribute2").value(hasItem(DEFAULT_ATTRIBUTE_2)))
            .andExpect(jsonPath("$.[*].attribute3").value(hasItem(DEFAULT_ATTRIBUTE_3)));
    }
    
    @Test
    @Transactional
    public void getProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", product.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(product.getId().intValue()))
            .andExpect(jsonPath("$.mainCode").value(DEFAULT_MAIN_CODE))
            .andExpect(jsonPath("$.auxiliaryCode").value(DEFAULT_AUXILIARY_CODE))
            .andExpect(jsonPath("$.barcode").value(DEFAULT_BARCODE))
            .andExpect(jsonPath("$.productType").value(DEFAULT_PRODUCT_TYPE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()))
            .andExpect(jsonPath("$.attribute1").value(DEFAULT_ATTRIBUTE_1))
            .andExpect(jsonPath("$.attribute2").value(DEFAULT_ATTRIBUTE_2))
            .andExpect(jsonPath("$.attribute3").value(DEFAULT_ATTRIBUTE_3));
    }

    @Test
    @Transactional
    public void getNonExistingProduct() throws Exception {
        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProduct() throws Exception {
        // Initialize the database
        productService.save(product);

        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Update the product
        Product updatedProduct = productRepository.findById(product.getId()).get();
        // Disconnect from session so that the updates on updatedProduct are not directly saved in db
        em.detach(updatedProduct);
        updatedProduct
            .mainCode(UPDATED_MAIN_CODE)
            .auxiliaryCode(UPDATED_AUXILIARY_CODE)
            .barcode(UPDATED_BARCODE)
            .productType(UPDATED_PRODUCT_TYPE)
            .name(UPDATED_NAME)
            .price(UPDATED_PRICE)
            .attribute1(UPDATED_ATTRIBUTE_1)
            .attribute2(UPDATED_ATTRIBUTE_2)
            .attribute3(UPDATED_ATTRIBUTE_3);

        restProductMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProduct)))
            .andExpect(status().isOk());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getMainCode()).isEqualTo(UPDATED_MAIN_CODE);
        assertThat(testProduct.getAuxiliaryCode()).isEqualTo(UPDATED_AUXILIARY_CODE);
        assertThat(testProduct.getBarcode()).isEqualTo(UPDATED_BARCODE);
        assertThat(testProduct.getProductType()).isEqualTo(UPDATED_PRODUCT_TYPE);
        assertThat(testProduct.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProduct.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testProduct.getAttribute1()).isEqualTo(UPDATED_ATTRIBUTE_1);
        assertThat(testProduct.getAttribute2()).isEqualTo(UPDATED_ATTRIBUTE_2);
        assertThat(testProduct.getAttribute3()).isEqualTo(UPDATED_ATTRIBUTE_3);
    }

    @Test
    @Transactional
    public void updateNonExistingProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Create the Product

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProduct() throws Exception {
        // Initialize the database
        productService.save(product);

        int databaseSizeBeforeDelete = productRepository.findAll().size();

        // Delete the product
        restProductMockMvc.perform(delete("/api/products/{id}", product.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
