package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.QuickSalesApiApp;
import ec.com.newsolutions.domain.TaxInvoice;
import ec.com.newsolutions.repository.TaxInvoiceRepository;
import ec.com.newsolutions.service.TaxInvoiceService;
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
 * Integration tests for the {@link TaxInvoiceResource} REST controller.
 */
@SpringBootTest(classes = QuickSalesApiApp.class)
public class TaxInvoiceResourceIT {

    private static final Integer DEFAULT_CODE = 1;
    private static final Integer UPDATED_CODE = 2;

    private static final Integer DEFAULT_PERCENTAGE_CODE = 1;
    private static final Integer UPDATED_PERCENTAGE_CODE = 2;

    private static final BigDecimal DEFAULT_TAX_BASE = new BigDecimal(1);
    private static final BigDecimal UPDATED_TAX_BASE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    @Autowired
    private TaxInvoiceRepository taxInvoiceRepository;

    @Autowired
    private TaxInvoiceService taxInvoiceService;

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

    private MockMvc restTaxInvoiceMockMvc;

    private TaxInvoice taxInvoice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TaxInvoiceResource taxInvoiceResource = new TaxInvoiceResource(taxInvoiceService);
        this.restTaxInvoiceMockMvc = MockMvcBuilders.standaloneSetup(taxInvoiceResource)
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
    public static TaxInvoice createEntity(EntityManager em) {
        TaxInvoice taxInvoice = new TaxInvoice()
            .code(DEFAULT_CODE)
            .percentageCode(DEFAULT_PERCENTAGE_CODE)
            .taxBase(DEFAULT_TAX_BASE)
            .amount(DEFAULT_AMOUNT);
        return taxInvoice;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaxInvoice createUpdatedEntity(EntityManager em) {
        TaxInvoice taxInvoice = new TaxInvoice()
            .code(UPDATED_CODE)
            .percentageCode(UPDATED_PERCENTAGE_CODE)
            .taxBase(UPDATED_TAX_BASE)
            .amount(UPDATED_AMOUNT);
        return taxInvoice;
    }

    @BeforeEach
    public void initTest() {
        taxInvoice = createEntity(em);
    }

    @Test
    @Transactional
    public void createTaxInvoice() throws Exception {
        int databaseSizeBeforeCreate = taxInvoiceRepository.findAll().size();

        // Create the TaxInvoice
        restTaxInvoiceMockMvc.perform(post("/api/tax-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxInvoice)))
            .andExpect(status().isCreated());

        // Validate the TaxInvoice in the database
        List<TaxInvoice> taxInvoiceList = taxInvoiceRepository.findAll();
        assertThat(taxInvoiceList).hasSize(databaseSizeBeforeCreate + 1);
        TaxInvoice testTaxInvoice = taxInvoiceList.get(taxInvoiceList.size() - 1);
        assertThat(testTaxInvoice.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTaxInvoice.getPercentageCode()).isEqualTo(DEFAULT_PERCENTAGE_CODE);
        assertThat(testTaxInvoice.getTaxBase()).isEqualTo(DEFAULT_TAX_BASE);
        assertThat(testTaxInvoice.getAmount()).isEqualTo(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void createTaxInvoiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = taxInvoiceRepository.findAll().size();

        // Create the TaxInvoice with an existing ID
        taxInvoice.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaxInvoiceMockMvc.perform(post("/api/tax-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxInvoice)))
            .andExpect(status().isBadRequest());

        // Validate the TaxInvoice in the database
        List<TaxInvoice> taxInvoiceList = taxInvoiceRepository.findAll();
        assertThat(taxInvoiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = taxInvoiceRepository.findAll().size();
        // set the field null
        taxInvoice.setCode(null);

        // Create the TaxInvoice, which fails.

        restTaxInvoiceMockMvc.perform(post("/api/tax-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxInvoice)))
            .andExpect(status().isBadRequest());

        List<TaxInvoice> taxInvoiceList = taxInvoiceRepository.findAll();
        assertThat(taxInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPercentageCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = taxInvoiceRepository.findAll().size();
        // set the field null
        taxInvoice.setPercentageCode(null);

        // Create the TaxInvoice, which fails.

        restTaxInvoiceMockMvc.perform(post("/api/tax-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxInvoice)))
            .andExpect(status().isBadRequest());

        List<TaxInvoice> taxInvoiceList = taxInvoiceRepository.findAll();
        assertThat(taxInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTaxBaseIsRequired() throws Exception {
        int databaseSizeBeforeTest = taxInvoiceRepository.findAll().size();
        // set the field null
        taxInvoice.setTaxBase(null);

        // Create the TaxInvoice, which fails.

        restTaxInvoiceMockMvc.perform(post("/api/tax-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxInvoice)))
            .andExpect(status().isBadRequest());

        List<TaxInvoice> taxInvoiceList = taxInvoiceRepository.findAll();
        assertThat(taxInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = taxInvoiceRepository.findAll().size();
        // set the field null
        taxInvoice.setAmount(null);

        // Create the TaxInvoice, which fails.

        restTaxInvoiceMockMvc.perform(post("/api/tax-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxInvoice)))
            .andExpect(status().isBadRequest());

        List<TaxInvoice> taxInvoiceList = taxInvoiceRepository.findAll();
        assertThat(taxInvoiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTaxInvoices() throws Exception {
        // Initialize the database
        taxInvoiceRepository.saveAndFlush(taxInvoice);

        // Get all the taxInvoiceList
        restTaxInvoiceMockMvc.perform(get("/api/tax-invoices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taxInvoice.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].percentageCode").value(hasItem(DEFAULT_PERCENTAGE_CODE)))
            .andExpect(jsonPath("$.[*].taxBase").value(hasItem(DEFAULT_TAX_BASE.intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())));
    }
    
    @Test
    @Transactional
    public void getTaxInvoice() throws Exception {
        // Initialize the database
        taxInvoiceRepository.saveAndFlush(taxInvoice);

        // Get the taxInvoice
        restTaxInvoiceMockMvc.perform(get("/api/tax-invoices/{id}", taxInvoice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taxInvoice.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.percentageCode").value(DEFAULT_PERCENTAGE_CODE))
            .andExpect(jsonPath("$.taxBase").value(DEFAULT_TAX_BASE.intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTaxInvoice() throws Exception {
        // Get the taxInvoice
        restTaxInvoiceMockMvc.perform(get("/api/tax-invoices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTaxInvoice() throws Exception {
        // Initialize the database
        taxInvoiceService.save(taxInvoice);

        int databaseSizeBeforeUpdate = taxInvoiceRepository.findAll().size();

        // Update the taxInvoice
        TaxInvoice updatedTaxInvoice = taxInvoiceRepository.findById(taxInvoice.getId()).get();
        // Disconnect from session so that the updates on updatedTaxInvoice are not directly saved in db
        em.detach(updatedTaxInvoice);
        updatedTaxInvoice
            .code(UPDATED_CODE)
            .percentageCode(UPDATED_PERCENTAGE_CODE)
            .taxBase(UPDATED_TAX_BASE)
            .amount(UPDATED_AMOUNT);

        restTaxInvoiceMockMvc.perform(put("/api/tax-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTaxInvoice)))
            .andExpect(status().isOk());

        // Validate the TaxInvoice in the database
        List<TaxInvoice> taxInvoiceList = taxInvoiceRepository.findAll();
        assertThat(taxInvoiceList).hasSize(databaseSizeBeforeUpdate);
        TaxInvoice testTaxInvoice = taxInvoiceList.get(taxInvoiceList.size() - 1);
        assertThat(testTaxInvoice.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTaxInvoice.getPercentageCode()).isEqualTo(UPDATED_PERCENTAGE_CODE);
        assertThat(testTaxInvoice.getTaxBase()).isEqualTo(UPDATED_TAX_BASE);
        assertThat(testTaxInvoice.getAmount()).isEqualTo(UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingTaxInvoice() throws Exception {
        int databaseSizeBeforeUpdate = taxInvoiceRepository.findAll().size();

        // Create the TaxInvoice

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaxInvoiceMockMvc.perform(put("/api/tax-invoices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxInvoice)))
            .andExpect(status().isBadRequest());

        // Validate the TaxInvoice in the database
        List<TaxInvoice> taxInvoiceList = taxInvoiceRepository.findAll();
        assertThat(taxInvoiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTaxInvoice() throws Exception {
        // Initialize the database
        taxInvoiceService.save(taxInvoice);

        int databaseSizeBeforeDelete = taxInvoiceRepository.findAll().size();

        // Delete the taxInvoice
        restTaxInvoiceMockMvc.perform(delete("/api/tax-invoices/{id}", taxInvoice.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TaxInvoice> taxInvoiceList = taxInvoiceRepository.findAll();
        assertThat(taxInvoiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
