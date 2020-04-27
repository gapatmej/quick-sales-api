package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.QuickSalesApiApp;
import ec.com.newsolutions.domain.DetailInvoice;
import ec.com.newsolutions.repository.DetailInvoiceRepository;
import ec.com.newsolutions.service.DetailInvoiceService;
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

/**
 * Integration tests for the {@link DetailInvoiceResource} REST controller.
 */
@SpringBootTest(classes = QuickSalesApiApp.class)
public class DetailInvoiceResourceIT {

    private static final String DEFAULT_MAIN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MAIN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_AUXILIARY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_AUXILIARY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_QUANTITY = new BigDecimal(0);
    private static final BigDecimal UPDATED_QUANTITY = new BigDecimal(1);

    private static final BigDecimal DEFAULT_UNIT_PRICE = new BigDecimal(0);
    private static final BigDecimal UPDATED_UNIT_PRICE = new BigDecimal(1);

    private static final BigDecimal DEFAULT_DISCOUNT = new BigDecimal(0);
    private static final BigDecimal UPDATED_DISCOUNT = new BigDecimal(1);

    private static final BigDecimal DEFAULT_TOTAL = new BigDecimal(0);
    private static final BigDecimal UPDATED_TOTAL = new BigDecimal(1);

    @Autowired
    private DetailInvoiceRepository detailInvoiceRepository;

    @Autowired
    private DetailInvoiceService detailInvoiceService;

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

    private MockMvc restDetailInvoiceMockMvc;

    private DetailInvoice detailInvoice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DetailInvoiceResource detailInvoiceResource = new DetailInvoiceResource(detailInvoiceService);
        this.restDetailInvoiceMockMvc = MockMvcBuilders.standaloneSetup(detailInvoiceResource)
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
    public static DetailInvoice createEntity(EntityManager em) {
        DetailInvoice detailInvoice = new DetailInvoice()
            .mainCode(DEFAULT_MAIN_CODE)
            .auxiliaryCode(DEFAULT_AUXILIARY_CODE)
            .description(DEFAULT_DESCRIPTION)
            .quantity(DEFAULT_QUANTITY)
            .unitPrice(DEFAULT_UNIT_PRICE)
            .discount(DEFAULT_DISCOUNT)
            .total(DEFAULT_TOTAL);
        return detailInvoice;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetailInvoice createUpdatedEntity(EntityManager em) {
        DetailInvoice detailInvoice = new DetailInvoice()
            .mainCode(UPDATED_MAIN_CODE)
            .auxiliaryCode(UPDATED_AUXILIARY_CODE)
            .description(UPDATED_DESCRIPTION)
            .quantity(UPDATED_QUANTITY)
            .unitPrice(UPDATED_UNIT_PRICE)
            .discount(UPDATED_DISCOUNT)
            .total(UPDATED_TOTAL);
        return detailInvoice;
    }

    @BeforeEach
    public void initTest() {
        detailInvoice = createEntity(em);
    }

    @Test
    @Transactional
    public void createDetailInvoice() throws Exception {
        int databaseSizeBeforeCreate = detailInvoiceRepository.findAll().size();

        // Create the DetailInvoice
        restDetailInvoiceMockMvc.perform(post("/api/detail-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailInvoice)))
            .andExpect(status().isCreated());

        // Validate the DetailInvoice in the database
        List<DetailInvoice> detailInvoiceList = detailInvoiceRepository.findAll();
        assertThat(detailInvoiceList).hasSize(databaseSizeBeforeCreate + 1);
        DetailInvoice testDetailInvoice = detailInvoiceList.get(detailInvoiceList.size() - 1);
        assertThat(testDetailInvoice.getMainCode()).isEqualTo(DEFAULT_MAIN_CODE);
        assertThat(testDetailInvoice.getAuxiliaryCode()).isEqualTo(DEFAULT_AUXILIARY_CODE);
        assertThat(testDetailInvoice.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDetailInvoice.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testDetailInvoice.getUnitPrice()).isEqualTo(DEFAULT_UNIT_PRICE);
        assertThat(testDetailInvoice.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testDetailInvoice.getTotal()).isEqualTo(DEFAULT_TOTAL);
    }

    @Test
    @Transactional
    public void createDetailInvoiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = detailInvoiceRepository.findAll().size();

        // Create the DetailInvoice with an existing ID
        detailInvoice.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetailInvoiceMockMvc.perform(post("/api/detail-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailInvoice)))
            .andExpect(status().isBadRequest());

        // Validate the DetailInvoice in the database
        List<DetailInvoice> detailInvoiceList = detailInvoiceRepository.findAll();
        assertThat(detailInvoiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMainCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailInvoiceRepository.findAll().size();
        // set the field null
        detailInvoice.setMainCode(null);

        // Create the DetailInvoice, which fails.

        restDetailInvoiceMockMvc.perform(post("/api/detail-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailInvoice)))
            .andExpect(status().isBadRequest());

        List<DetailInvoice> detailInvoiceList = detailInvoiceRepository.findAll();
        assertThat(detailInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailInvoiceRepository.findAll().size();
        // set the field null
        detailInvoice.setQuantity(null);

        // Create the DetailInvoice, which fails.

        restDetailInvoiceMockMvc.perform(post("/api/detail-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailInvoice)))
            .andExpect(status().isBadRequest());

        List<DetailInvoice> detailInvoiceList = detailInvoiceRepository.findAll();
        assertThat(detailInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUnitPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailInvoiceRepository.findAll().size();
        // set the field null
        detailInvoice.setUnitPrice(null);

        // Create the DetailInvoice, which fails.

        restDetailInvoiceMockMvc.perform(post("/api/detail-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailInvoice)))
            .andExpect(status().isBadRequest());

        List<DetailInvoice> detailInvoiceList = detailInvoiceRepository.findAll();
        assertThat(detailInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDiscountIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailInvoiceRepository.findAll().size();
        // set the field null
        detailInvoice.setDiscount(null);

        // Create the DetailInvoice, which fails.

        restDetailInvoiceMockMvc.perform(post("/api/detail-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailInvoice)))
            .andExpect(status().isBadRequest());

        List<DetailInvoice> detailInvoiceList = detailInvoiceRepository.findAll();
        assertThat(detailInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailInvoiceRepository.findAll().size();
        // set the field null
        detailInvoice.setTotal(null);

        // Create the DetailInvoice, which fails.

        restDetailInvoiceMockMvc.perform(post("/api/detail-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailInvoice)))
            .andExpect(status().isBadRequest());

        List<DetailInvoice> detailInvoiceList = detailInvoiceRepository.findAll();
        assertThat(detailInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDetailInvoices() throws Exception {
        // Initialize the database
        detailInvoiceRepository.saveAndFlush(detailInvoice);

        // Get all the detailInvoiceList
        restDetailInvoiceMockMvc.perform(get("/api/detail-invoices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detailInvoice.getId().intValue())))
            .andExpect(jsonPath("$.[*].mainCode").value(hasItem(DEFAULT_MAIN_CODE)))
            .andExpect(jsonPath("$.[*].auxiliaryCode").value(hasItem(DEFAULT_AUXILIARY_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].unitPrice").value(hasItem(DEFAULT_UNIT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.intValue())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.intValue())));
    }
    
    @Test
    @Transactional
    public void getDetailInvoice() throws Exception {
        // Initialize the database
        detailInvoiceRepository.saveAndFlush(detailInvoice);

        // Get the detailInvoice
        restDetailInvoiceMockMvc.perform(get("/api/detail-invoices/{id}", detailInvoice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(detailInvoice.getId().intValue()))
            .andExpect(jsonPath("$.mainCode").value(DEFAULT_MAIN_CODE))
            .andExpect(jsonPath("$.auxiliaryCode").value(DEFAULT_AUXILIARY_CODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.unitPrice").value(DEFAULT_UNIT_PRICE.intValue()))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT.intValue()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDetailInvoice() throws Exception {
        // Get the detailInvoice
        restDetailInvoiceMockMvc.perform(get("/api/detail-invoices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDetailInvoice() throws Exception {
        // Initialize the database
        detailInvoiceService.save(detailInvoice);

        int databaseSizeBeforeUpdate = detailInvoiceRepository.findAll().size();

        // Update the detailInvoice
        DetailInvoice updatedDetailInvoice = detailInvoiceRepository.findById(detailInvoice.getId()).get();
        // Disconnect from session so that the updates on updatedDetailInvoice are not directly saved in db
        em.detach(updatedDetailInvoice);
        updatedDetailInvoice
            .mainCode(UPDATED_MAIN_CODE)
            .auxiliaryCode(UPDATED_AUXILIARY_CODE)
            .description(UPDATED_DESCRIPTION)
            .quantity(UPDATED_QUANTITY)
            .unitPrice(UPDATED_UNIT_PRICE)
            .discount(UPDATED_DISCOUNT)
            .total(UPDATED_TOTAL);

        restDetailInvoiceMockMvc.perform(put("/api/detail-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDetailInvoice)))
            .andExpect(status().isOk());

        // Validate the DetailInvoice in the database
        List<DetailInvoice> detailInvoiceList = detailInvoiceRepository.findAll();
        assertThat(detailInvoiceList).hasSize(databaseSizeBeforeUpdate);
        DetailInvoice testDetailInvoice = detailInvoiceList.get(detailInvoiceList.size() - 1);
        assertThat(testDetailInvoice.getMainCode()).isEqualTo(UPDATED_MAIN_CODE);
        assertThat(testDetailInvoice.getAuxiliaryCode()).isEqualTo(UPDATED_AUXILIARY_CODE);
        assertThat(testDetailInvoice.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDetailInvoice.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testDetailInvoice.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
        assertThat(testDetailInvoice.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testDetailInvoice.getTotal()).isEqualTo(UPDATED_TOTAL);
    }

    @Test
    @Transactional
    public void updateNonExistingDetailInvoice() throws Exception {
        int databaseSizeBeforeUpdate = detailInvoiceRepository.findAll().size();

        // Create the DetailInvoice

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetailInvoiceMockMvc.perform(put("/api/detail-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailInvoice)))
            .andExpect(status().isBadRequest());

        // Validate the DetailInvoice in the database
        List<DetailInvoice> detailInvoiceList = detailInvoiceRepository.findAll();
        assertThat(detailInvoiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDetailInvoice() throws Exception {
        // Initialize the database
        detailInvoiceService.save(detailInvoice);

        int databaseSizeBeforeDelete = detailInvoiceRepository.findAll().size();

        // Delete the detailInvoice
        restDetailInvoiceMockMvc.perform(delete("/api/detail-invoices/{id}", detailInvoice.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DetailInvoice> detailInvoiceList = detailInvoiceRepository.findAll();
        assertThat(detailInvoiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
