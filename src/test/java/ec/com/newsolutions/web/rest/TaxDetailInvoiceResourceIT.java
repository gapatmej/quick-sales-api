package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.QuickSalesApiApp;
import ec.com.newsolutions.domain.TaxDetailInvoice;
import ec.com.newsolutions.repository.TaxDetailInvoiceRepository;
import ec.com.newsolutions.service.TaxDetailInvoiceService;
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
 * Integration tests for the {@link TaxDetailInvoiceResource} REST controller.
 */
@SpringBootTest(classes = QuickSalesApiApp.class)
public class TaxDetailInvoiceResourceIT {

    private static final Integer DEFAULT_CODE = 1;
    private static final Integer UPDATED_CODE = 2;

    private static final Integer DEFAULT_PERCENTAGE_CODE = 1;
    private static final Integer UPDATED_PERCENTAGE_CODE = 2;

    private static final Integer DEFAULT_RATE = 1;
    private static final Integer UPDATED_RATE = 2;

    private static final BigDecimal DEFAULT_TAX_BASE = new BigDecimal(1);
    private static final BigDecimal UPDATED_TAX_BASE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    @Autowired
    private TaxDetailInvoiceRepository taxDetailInvoiceRepository;

    @Autowired
    private TaxDetailInvoiceService taxDetailInvoiceService;

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

    private MockMvc restTaxDetailInvoiceMockMvc;

    private TaxDetailInvoice taxDetailInvoice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TaxDetailInvoiceResource taxDetailInvoiceResource = new TaxDetailInvoiceResource(taxDetailInvoiceService);
        this.restTaxDetailInvoiceMockMvc = MockMvcBuilders.standaloneSetup(taxDetailInvoiceResource)
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
    public static TaxDetailInvoice createEntity(EntityManager em) {
        TaxDetailInvoice taxDetailInvoice = new TaxDetailInvoice()
            .code(DEFAULT_CODE)
            .percentageCode(DEFAULT_PERCENTAGE_CODE)
            .rate(DEFAULT_RATE)
            .taxBase(DEFAULT_TAX_BASE)
            .amount(DEFAULT_AMOUNT);
        return taxDetailInvoice;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaxDetailInvoice createUpdatedEntity(EntityManager em) {
        TaxDetailInvoice taxDetailInvoice = new TaxDetailInvoice()
            .code(UPDATED_CODE)
            .percentageCode(UPDATED_PERCENTAGE_CODE)
            .rate(UPDATED_RATE)
            .taxBase(UPDATED_TAX_BASE)
            .amount(UPDATED_AMOUNT);
        return taxDetailInvoice;
    }

    @BeforeEach
    public void initTest() {
        taxDetailInvoice = createEntity(em);
    }

    @Test
    @Transactional
    public void createTaxDetailInvoice() throws Exception {
        int databaseSizeBeforeCreate = taxDetailInvoiceRepository.findAll().size();

        // Create the TaxDetailInvoice
        restTaxDetailInvoiceMockMvc.perform(post("/api/tax-detail-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxDetailInvoice)))
            .andExpect(status().isCreated());

        // Validate the TaxDetailInvoice in the database
        List<TaxDetailInvoice> taxDetailInvoiceList = taxDetailInvoiceRepository.findAll();
        assertThat(taxDetailInvoiceList).hasSize(databaseSizeBeforeCreate + 1);
        TaxDetailInvoice testTaxDetailInvoice = taxDetailInvoiceList.get(taxDetailInvoiceList.size() - 1);
        assertThat(testTaxDetailInvoice.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTaxDetailInvoice.getPercentageCode()).isEqualTo(DEFAULT_PERCENTAGE_CODE);
        assertThat(testTaxDetailInvoice.getRate()).isEqualTo(DEFAULT_RATE);
        assertThat(testTaxDetailInvoice.getTaxBase()).isEqualTo(DEFAULT_TAX_BASE);
        assertThat(testTaxDetailInvoice.getAmount()).isEqualTo(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void createTaxDetailInvoiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = taxDetailInvoiceRepository.findAll().size();

        // Create the TaxDetailInvoice with an existing ID
        taxDetailInvoice.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaxDetailInvoiceMockMvc.perform(post("/api/tax-detail-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxDetailInvoice)))
            .andExpect(status().isBadRequest());

        // Validate the TaxDetailInvoice in the database
        List<TaxDetailInvoice> taxDetailInvoiceList = taxDetailInvoiceRepository.findAll();
        assertThat(taxDetailInvoiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = taxDetailInvoiceRepository.findAll().size();
        // set the field null
        taxDetailInvoice.setCode(null);

        // Create the TaxDetailInvoice, which fails.

        restTaxDetailInvoiceMockMvc.perform(post("/api/tax-detail-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxDetailInvoice)))
            .andExpect(status().isBadRequest());

        List<TaxDetailInvoice> taxDetailInvoiceList = taxDetailInvoiceRepository.findAll();
        assertThat(taxDetailInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPercentageCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = taxDetailInvoiceRepository.findAll().size();
        // set the field null
        taxDetailInvoice.setPercentageCode(null);

        // Create the TaxDetailInvoice, which fails.

        restTaxDetailInvoiceMockMvc.perform(post("/api/tax-detail-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxDetailInvoice)))
            .andExpect(status().isBadRequest());

        List<TaxDetailInvoice> taxDetailInvoiceList = taxDetailInvoiceRepository.findAll();
        assertThat(taxDetailInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = taxDetailInvoiceRepository.findAll().size();
        // set the field null
        taxDetailInvoice.setRate(null);

        // Create the TaxDetailInvoice, which fails.

        restTaxDetailInvoiceMockMvc.perform(post("/api/tax-detail-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxDetailInvoice)))
            .andExpect(status().isBadRequest());

        List<TaxDetailInvoice> taxDetailInvoiceList = taxDetailInvoiceRepository.findAll();
        assertThat(taxDetailInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTaxBaseIsRequired() throws Exception {
        int databaseSizeBeforeTest = taxDetailInvoiceRepository.findAll().size();
        // set the field null
        taxDetailInvoice.setTaxBase(null);

        // Create the TaxDetailInvoice, which fails.

        restTaxDetailInvoiceMockMvc.perform(post("/api/tax-detail-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxDetailInvoice)))
            .andExpect(status().isBadRequest());

        List<TaxDetailInvoice> taxDetailInvoiceList = taxDetailInvoiceRepository.findAll();
        assertThat(taxDetailInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = taxDetailInvoiceRepository.findAll().size();
        // set the field null
        taxDetailInvoice.setAmount(null);

        // Create the TaxDetailInvoice, which fails.

        restTaxDetailInvoiceMockMvc.perform(post("/api/tax-detail-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxDetailInvoice)))
            .andExpect(status().isBadRequest());

        List<TaxDetailInvoice> taxDetailInvoiceList = taxDetailInvoiceRepository.findAll();
        assertThat(taxDetailInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTaxDetailInvoices() throws Exception {
        // Initialize the database
        taxDetailInvoiceRepository.saveAndFlush(taxDetailInvoice);

        // Get all the taxDetailInvoiceList
        restTaxDetailInvoiceMockMvc.perform(get("/api/tax-detail-invoices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taxDetailInvoice.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].percentageCode").value(hasItem(DEFAULT_PERCENTAGE_CODE)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)))
            .andExpect(jsonPath("$.[*].taxBase").value(hasItem(DEFAULT_TAX_BASE.intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())));
    }
    
    @Test
    @Transactional
    public void getTaxDetailInvoice() throws Exception {
        // Initialize the database
        taxDetailInvoiceRepository.saveAndFlush(taxDetailInvoice);

        // Get the taxDetailInvoice
        restTaxDetailInvoiceMockMvc.perform(get("/api/tax-detail-invoices/{id}", taxDetailInvoice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taxDetailInvoice.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.percentageCode").value(DEFAULT_PERCENTAGE_CODE))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE))
            .andExpect(jsonPath("$.taxBase").value(DEFAULT_TAX_BASE.intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTaxDetailInvoice() throws Exception {
        // Get the taxDetailInvoice
        restTaxDetailInvoiceMockMvc.perform(get("/api/tax-detail-invoices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTaxDetailInvoice() throws Exception {
        // Initialize the database
        taxDetailInvoiceService.save(taxDetailInvoice);

        int databaseSizeBeforeUpdate = taxDetailInvoiceRepository.findAll().size();

        // Update the taxDetailInvoice
        TaxDetailInvoice updatedTaxDetailInvoice = taxDetailInvoiceRepository.findById(taxDetailInvoice.getId()).get();
        // Disconnect from session so that the updates on updatedTaxDetailInvoice are not directly saved in db
        em.detach(updatedTaxDetailInvoice);
        updatedTaxDetailInvoice
            .code(UPDATED_CODE)
            .percentageCode(UPDATED_PERCENTAGE_CODE)
            .rate(UPDATED_RATE)
            .taxBase(UPDATED_TAX_BASE)
            .amount(UPDATED_AMOUNT);

        restTaxDetailInvoiceMockMvc.perform(put("/api/tax-detail-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTaxDetailInvoice)))
            .andExpect(status().isOk());

        // Validate the TaxDetailInvoice in the database
        List<TaxDetailInvoice> taxDetailInvoiceList = taxDetailInvoiceRepository.findAll();
        assertThat(taxDetailInvoiceList).hasSize(databaseSizeBeforeUpdate);
        TaxDetailInvoice testTaxDetailInvoice = taxDetailInvoiceList.get(taxDetailInvoiceList.size() - 1);
        assertThat(testTaxDetailInvoice.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTaxDetailInvoice.getPercentageCode()).isEqualTo(UPDATED_PERCENTAGE_CODE);
        assertThat(testTaxDetailInvoice.getRate()).isEqualTo(UPDATED_RATE);
        assertThat(testTaxDetailInvoice.getTaxBase()).isEqualTo(UPDATED_TAX_BASE);
        assertThat(testTaxDetailInvoice.getAmount()).isEqualTo(UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingTaxDetailInvoice() throws Exception {
        int databaseSizeBeforeUpdate = taxDetailInvoiceRepository.findAll().size();

        // Create the TaxDetailInvoice

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaxDetailInvoiceMockMvc.perform(put("/api/tax-detail-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxDetailInvoice)))
            .andExpect(status().isBadRequest());

        // Validate the TaxDetailInvoice in the database
        List<TaxDetailInvoice> taxDetailInvoiceList = taxDetailInvoiceRepository.findAll();
        assertThat(taxDetailInvoiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTaxDetailInvoice() throws Exception {
        // Initialize the database
        taxDetailInvoiceService.save(taxDetailInvoice);

        int databaseSizeBeforeDelete = taxDetailInvoiceRepository.findAll().size();

        // Delete the taxDetailInvoice
        restTaxDetailInvoiceMockMvc.perform(delete("/api/tax-detail-invoices/{id}", taxDetailInvoice.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TaxDetailInvoice> taxDetailInvoiceList = taxDetailInvoiceRepository.findAll();
        assertThat(taxDetailInvoiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
