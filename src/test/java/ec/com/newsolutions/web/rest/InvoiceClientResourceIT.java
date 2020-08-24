package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.QuickSalesApiApp;
import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.repository.InvoiceClientRepository;
import ec.com.newsolutions.service.InvoiceClientService;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static ec.com.newsolutions.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ec.com.newsolutions.domain.enumeration.SRIEnvironmentEnum;
import ec.com.newsolutions.domain.enumeration.EmissionTypeEnum;
import ec.com.newsolutions.domain.enumeration.IdentificationTypeEnum;
import ec.com.newsolutions.domain.enumeration.CurrencyEnum;
/**
 * Integration tests for the {@link InvoiceClientResource} REST controller.
 */
@SpringBootTest(classes = QuickSalesApiApp.class)
public class InvoiceClientResourceIT {

    private static final SRIEnvironmentEnum DEFAULT_S_RI_ENVIROMENT = SRIEnvironmentEnum.TEST;
    private static final SRIEnvironmentEnum UPDATED_S_RI_ENVIROMENT = SRIEnvironmentEnum.PRODUCTION;

    private static final EmissionTypeEnum DEFAULT_EMISSION_TYPE = EmissionTypeEnum.NORMAL;
    private static final EmissionTypeEnum UPDATED_EMISSION_TYPE = EmissionTypeEnum.NORMAL;

    private static final String DEFAULT_BUSINESS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_DOCUMENT = "AAAAAAAAAA";
    private static final String UPDATED_CODE_DOCUMENT = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_ISSUE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_ISSUE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final IdentificationTypeEnum DEFAULT_IDENTIFICATION_TYPE = IdentificationTypeEnum.RUC;
    private static final IdentificationTypeEnum UPDATED_IDENTIFICATION_TYPE = IdentificationTypeEnum.IDENTIFICATION_CARD;

    private static final String DEFAULT_IDENTIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFICATION = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_TOTAL_TAX_FREE = new BigDecimal(0);
    private static final BigDecimal UPDATED_TOTAL_TAX_FREE = new BigDecimal(1);

    private static final BigDecimal DEFAULT_TOTAL_DISCOUNT = new BigDecimal(0);
    private static final BigDecimal UPDATED_TOTAL_DISCOUNT = new BigDecimal(1);

    private static final BigDecimal DEFAULT_TOTAL_BASE_TAX_IVA = new BigDecimal(0);
    private static final BigDecimal UPDATED_TOTAL_BASE_TAX_IVA = new BigDecimal(1);

    private static final BigDecimal DEFAULT_TOTAL_BASE_TAX_ICE = new BigDecimal(0);
    private static final BigDecimal UPDATED_TOTAL_BASE_TAX_ICE = new BigDecimal(1);

    private static final BigDecimal DEFAULT_TOTAL_TAX_IVA = new BigDecimal(0);
    private static final BigDecimal UPDATED_TOTAL_TAX_IVA = new BigDecimal(1);

    private static final BigDecimal DEFAULT_TOTAL_TAX_ICE = new BigDecimal(0);
    private static final BigDecimal UPDATED_TOTAL_TAX_ICE = new BigDecimal(1);

    private static final BigDecimal DEFAULT_TIP = new BigDecimal(0);
    private static final BigDecimal UPDATED_TIP = new BigDecimal(1);

    private static final BigDecimal DEFAULT_TOTAL = new BigDecimal(0);
    private static final BigDecimal UPDATED_TOTAL = new BigDecimal(1);

    private static final CurrencyEnum DEFAULT_CURRENCY = CurrencyEnum.DOLAR;
    private static final CurrencyEnum UPDATED_CURRENCY = CurrencyEnum.EURO;

    @Autowired
    private InvoiceClientRepository invoiceClientRepository;

    @Autowired
    private InvoiceClientService invoiceClientService;

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

    private MockMvc restInvoiceClientMockMvc;

    private InvoiceClient invoiceClient;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InvoiceClientResource invoiceClientResource = new InvoiceClientResource(invoiceClientService);
        this.restInvoiceClientMockMvc = MockMvcBuilders.standaloneSetup(invoiceClientResource)
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
    public static InvoiceClient createEntity(EntityManager em) {
        InvoiceClient invoiceClient = new InvoiceClient()
            .sRIEnviroment(DEFAULT_S_RI_ENVIROMENT)
            .emissionType(DEFAULT_EMISSION_TYPE)
            .businessName(DEFAULT_BUSINESS_NAME)
            .password(DEFAULT_PASSWORD)
            .codeDocument(DEFAULT_CODE_DOCUMENT)
            .dateIssue(DEFAULT_DATE_ISSUE)
            .identificationType(DEFAULT_IDENTIFICATION_TYPE)
            .identification(DEFAULT_IDENTIFICATION)
            .address(DEFAULT_ADDRESS)
            .phone(DEFAULT_PHONE)
            .email(DEFAULT_EMAIL)
            .totalTaxFree(DEFAULT_TOTAL_TAX_FREE)
            .totalDiscount(DEFAULT_TOTAL_DISCOUNT)
            .totalBaseTaxIVA(DEFAULT_TOTAL_BASE_TAX_IVA)
            .totalBaseTaxICE(DEFAULT_TOTAL_BASE_TAX_ICE)
            .totalTaxIVA(DEFAULT_TOTAL_TAX_IVA)
            .totalTaxICE(DEFAULT_TOTAL_TAX_ICE)
            .tip(DEFAULT_TIP)
            .total(DEFAULT_TOTAL)
            .currency(DEFAULT_CURRENCY);
        return invoiceClient;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceClient createUpdatedEntity(EntityManager em) {
        InvoiceClient invoiceClient = new InvoiceClient()
            .sRIEnviroment(UPDATED_S_RI_ENVIROMENT)
            .emissionType(UPDATED_EMISSION_TYPE)
            .businessName(UPDATED_BUSINESS_NAME)
            .password(UPDATED_PASSWORD)
            .codeDocument(UPDATED_CODE_DOCUMENT)
            .dateIssue(UPDATED_DATE_ISSUE)
            .identificationType(UPDATED_IDENTIFICATION_TYPE)
            .identification(UPDATED_IDENTIFICATION)
            .address(UPDATED_ADDRESS)
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL)
            .totalTaxFree(UPDATED_TOTAL_TAX_FREE)
            .totalDiscount(UPDATED_TOTAL_DISCOUNT)
            .totalBaseTaxIVA(UPDATED_TOTAL_BASE_TAX_IVA)
            .totalBaseTaxICE(UPDATED_TOTAL_BASE_TAX_ICE)
            .totalTaxIVA(UPDATED_TOTAL_TAX_IVA)
            .totalTaxICE(UPDATED_TOTAL_TAX_ICE)
            .tip(UPDATED_TIP)
            .total(UPDATED_TOTAL)
            .currency(UPDATED_CURRENCY);
        return invoiceClient;
    }

    @BeforeEach
    public void initTest() {
        invoiceClient = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvoiceClient() throws Exception {
        int databaseSizeBeforeCreate = invoiceClientRepository.findAll().size();

        // Create the InvoiceClient
        restInvoiceClientMockMvc.perform(post("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isCreated());

        // Validate the InvoiceClient in the database
        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeCreate + 1);
        InvoiceClient testInvoiceClient = invoiceClientList.get(invoiceClientList.size() - 1);
        assertThat(testInvoiceClient.getSriEnviroment()).isEqualTo(DEFAULT_S_RI_ENVIROMENT);
        assertThat(testInvoiceClient.getEmissionType()).isEqualTo(DEFAULT_EMISSION_TYPE);
        assertThat(testInvoiceClient.getBusinessName()).isEqualTo(DEFAULT_BUSINESS_NAME);
        assertThat(testInvoiceClient.getAccessKey()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testInvoiceClient.getCodeDocument()).isEqualTo(DEFAULT_CODE_DOCUMENT);
        assertThat(testInvoiceClient.getDateIssue()).isEqualTo(DEFAULT_DATE_ISSUE);
        assertThat(testInvoiceClient.getIdentificationType()).isEqualTo(DEFAULT_IDENTIFICATION_TYPE);
        assertThat(testInvoiceClient.getIdentification()).isEqualTo(DEFAULT_IDENTIFICATION);
        assertThat(testInvoiceClient.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testInvoiceClient.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testInvoiceClient.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testInvoiceClient.getTotalTaxFree()).isEqualTo(DEFAULT_TOTAL_TAX_FREE);
        assertThat(testInvoiceClient.getTotalDiscount()).isEqualTo(DEFAULT_TOTAL_DISCOUNT);
        assertThat(testInvoiceClient.getTotalBaseTaxIVA()).isEqualTo(DEFAULT_TOTAL_BASE_TAX_IVA);
        assertThat(testInvoiceClient.getTotalBaseTaxICE()).isEqualTo(DEFAULT_TOTAL_BASE_TAX_ICE);
        assertThat(testInvoiceClient.getTotalTaxIVA()).isEqualTo(DEFAULT_TOTAL_TAX_IVA);
        assertThat(testInvoiceClient.getTotalTaxICE()).isEqualTo(DEFAULT_TOTAL_TAX_ICE);
        assertThat(testInvoiceClient.getTip()).isEqualTo(DEFAULT_TIP);
        assertThat(testInvoiceClient.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testInvoiceClient.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
    }

    @Test
    @Transactional
    public void createInvoiceClientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = invoiceClientRepository.findAll().size();

        // Create the InvoiceClient with an existing ID
        invoiceClient.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvoiceClientMockMvc.perform(post("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isBadRequest());

        // Validate the InvoiceClient in the database
        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checksRIEnviromentIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceClientRepository.findAll().size();
        // set the field null
        invoiceClient.setSriEnviroment(null);

        // Create the InvoiceClient, which fails.

        restInvoiceClientMockMvc.perform(post("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isBadRequest());

        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmissionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceClientRepository.findAll().size();
        // set the field null
        invoiceClient.setEmissionType(null);

        // Create the InvoiceClient, which fails.

        restInvoiceClientMockMvc.perform(post("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isBadRequest());

        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBusinessNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceClientRepository.findAll().size();
        // set the field null
        invoiceClient.setBusinessName(null);

        // Create the InvoiceClient, which fails.

        restInvoiceClientMockMvc.perform(post("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isBadRequest());

        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceClientRepository.findAll().size();
        // set the field null
        invoiceClient.setAccessKey(null);

        // Create the InvoiceClient, which fails.

        restInvoiceClientMockMvc.perform(post("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isBadRequest());

        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeDocumentIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceClientRepository.findAll().size();
        // set the field null
        invoiceClient.setCodeDocument(null);

        // Create the InvoiceClient, which fails.

        restInvoiceClientMockMvc.perform(post("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isBadRequest());

        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateIssueIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceClientRepository.findAll().size();
        // set the field null
        invoiceClient.setDateIssue(null);

        // Create the InvoiceClient, which fails.

        restInvoiceClientMockMvc.perform(post("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isBadRequest());

        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdentificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceClientRepository.findAll().size();
        // set the field null
        invoiceClient.setIdentification(null);

        // Create the InvoiceClient, which fails.

        restInvoiceClientMockMvc.perform(post("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isBadRequest());

        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceClientRepository.findAll().size();
        // set the field null
        invoiceClient.setEmail(null);

        // Create the InvoiceClient, which fails.

        restInvoiceClientMockMvc.perform(post("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isBadRequest());

        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalTaxFreeIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceClientRepository.findAll().size();
        // set the field null
        invoiceClient.setTotalTaxFree(null);

        // Create the InvoiceClient, which fails.

        restInvoiceClientMockMvc.perform(post("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isBadRequest());

        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalDiscountIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceClientRepository.findAll().size();
        // set the field null
        invoiceClient.setTotalDiscount(null);

        // Create the InvoiceClient, which fails.

        restInvoiceClientMockMvc.perform(post("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isBadRequest());

        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalBaseTaxIVAIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceClientRepository.findAll().size();
        // set the field null
        invoiceClient.setTotalBaseTaxIVA(null);

        // Create the InvoiceClient, which fails.

        restInvoiceClientMockMvc.perform(post("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isBadRequest());

        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalBaseTaxICEIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceClientRepository.findAll().size();
        // set the field null
        invoiceClient.setTotalBaseTaxICE(null);

        // Create the InvoiceClient, which fails.

        restInvoiceClientMockMvc.perform(post("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isBadRequest());

        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalTaxIVAIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceClientRepository.findAll().size();
        // set the field null
        invoiceClient.setTotalTaxIVA(null);

        // Create the InvoiceClient, which fails.

        restInvoiceClientMockMvc.perform(post("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isBadRequest());

        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalTaxICEIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceClientRepository.findAll().size();
        // set the field null
        invoiceClient.setTotalTaxICE(null);

        // Create the InvoiceClient, which fails.

        restInvoiceClientMockMvc.perform(post("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isBadRequest());

        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTipIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceClientRepository.findAll().size();
        // set the field null
        invoiceClient.setTip(null);

        // Create the InvoiceClient, which fails.

        restInvoiceClientMockMvc.perform(post("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isBadRequest());

        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceClientRepository.findAll().size();
        // set the field null
        invoiceClient.setTotal(null);

        // Create the InvoiceClient, which fails.

        restInvoiceClientMockMvc.perform(post("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isBadRequest());

        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCurrencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceClientRepository.findAll().size();
        // set the field null
        invoiceClient.setCurrency(null);

        // Create the InvoiceClient, which fails.

        restInvoiceClientMockMvc.perform(post("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isBadRequest());

        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInvoiceClients() throws Exception {
        // Initialize the database
        invoiceClientRepository.saveAndFlush(invoiceClient);

        // Get all the invoiceClientList
        restInvoiceClientMockMvc.perform(get("/api/invoice-clients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invoiceClient.getId().intValue())))
            .andExpect(jsonPath("$.[*].sRIEnviroment").value(hasItem(DEFAULT_S_RI_ENVIROMENT.toString())))
            .andExpect(jsonPath("$.[*].emissionType").value(hasItem(DEFAULT_EMISSION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].businessName").value(hasItem(DEFAULT_BUSINESS_NAME)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].codeDocument").value(hasItem(DEFAULT_CODE_DOCUMENT)))
            .andExpect(jsonPath("$.[*].dateIssue").value(hasItem(DEFAULT_DATE_ISSUE.toString())))
            .andExpect(jsonPath("$.[*].identificationType").value(hasItem(DEFAULT_IDENTIFICATION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].identification").value(hasItem(DEFAULT_IDENTIFICATION)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].totalTaxFree").value(hasItem(DEFAULT_TOTAL_TAX_FREE.intValue())))
            .andExpect(jsonPath("$.[*].totalDiscount").value(hasItem(DEFAULT_TOTAL_DISCOUNT.intValue())))
            .andExpect(jsonPath("$.[*].totalBaseTaxIVA").value(hasItem(DEFAULT_TOTAL_BASE_TAX_IVA.intValue())))
            .andExpect(jsonPath("$.[*].totalBaseTaxICE").value(hasItem(DEFAULT_TOTAL_BASE_TAX_ICE.intValue())))
            .andExpect(jsonPath("$.[*].totalTaxIVA").value(hasItem(DEFAULT_TOTAL_TAX_IVA.intValue())))
            .andExpect(jsonPath("$.[*].totalTaxICE").value(hasItem(DEFAULT_TOTAL_TAX_ICE.intValue())))
            .andExpect(jsonPath("$.[*].tip").value(hasItem(DEFAULT_TIP.intValue())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.toString())));
    }

    @Test
    @Transactional
    public void getInvoiceClient() throws Exception {
        // Initialize the database
        invoiceClientRepository.saveAndFlush(invoiceClient);

        // Get the invoiceClient
        restInvoiceClientMockMvc.perform(get("/api/invoice-clients/{id}", invoiceClient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(invoiceClient.getId().intValue()))
            .andExpect(jsonPath("$.sRIEnviroment").value(DEFAULT_S_RI_ENVIROMENT.toString()))
            .andExpect(jsonPath("$.emissionType").value(DEFAULT_EMISSION_TYPE.toString()))
            .andExpect(jsonPath("$.businessName").value(DEFAULT_BUSINESS_NAME))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.codeDocument").value(DEFAULT_CODE_DOCUMENT))
            .andExpect(jsonPath("$.dateIssue").value(DEFAULT_DATE_ISSUE.toString()))
            .andExpect(jsonPath("$.identificationType").value(DEFAULT_IDENTIFICATION_TYPE.toString()))
            .andExpect(jsonPath("$.identification").value(DEFAULT_IDENTIFICATION))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.totalTaxFree").value(DEFAULT_TOTAL_TAX_FREE.intValue()))
            .andExpect(jsonPath("$.totalDiscount").value(DEFAULT_TOTAL_DISCOUNT.intValue()))
            .andExpect(jsonPath("$.totalBaseTaxIVA").value(DEFAULT_TOTAL_BASE_TAX_IVA.intValue()))
            .andExpect(jsonPath("$.totalBaseTaxICE").value(DEFAULT_TOTAL_BASE_TAX_ICE.intValue()))
            .andExpect(jsonPath("$.totalTaxIVA").value(DEFAULT_TOTAL_TAX_IVA.intValue()))
            .andExpect(jsonPath("$.totalTaxICE").value(DEFAULT_TOTAL_TAX_ICE.intValue()))
            .andExpect(jsonPath("$.tip").value(DEFAULT_TIP.intValue()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.intValue()))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInvoiceClient() throws Exception {
        // Get the invoiceClient
        restInvoiceClientMockMvc.perform(get("/api/invoice-clients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvoiceClient() throws Exception {
        // Initialize the database
        invoiceClientService.save(invoiceClient);

        int databaseSizeBeforeUpdate = invoiceClientRepository.findAll().size();

        // Update the invoiceClient
        InvoiceClient updatedInvoiceClient = invoiceClientRepository.findById(invoiceClient.getId()).get();
        // Disconnect from session so that the updates on updatedInvoiceClient are not directly saved in db
        em.detach(updatedInvoiceClient);
        updatedInvoiceClient
            .sRIEnviroment(UPDATED_S_RI_ENVIROMENT)
            .emissionType(UPDATED_EMISSION_TYPE)
            .businessName(UPDATED_BUSINESS_NAME)
            .password(UPDATED_PASSWORD)
            .codeDocument(UPDATED_CODE_DOCUMENT)
            .dateIssue(UPDATED_DATE_ISSUE)
            .identificationType(UPDATED_IDENTIFICATION_TYPE)
            .identification(UPDATED_IDENTIFICATION)
            .address(UPDATED_ADDRESS)
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL)
            .totalTaxFree(UPDATED_TOTAL_TAX_FREE)
            .totalDiscount(UPDATED_TOTAL_DISCOUNT)
            .totalBaseTaxIVA(UPDATED_TOTAL_BASE_TAX_IVA)
            .totalBaseTaxICE(UPDATED_TOTAL_BASE_TAX_ICE)
            .totalTaxIVA(UPDATED_TOTAL_TAX_IVA)
            .totalTaxICE(UPDATED_TOTAL_TAX_ICE)
            .tip(UPDATED_TIP)
            .total(UPDATED_TOTAL)
            .currency(UPDATED_CURRENCY);

        restInvoiceClientMockMvc.perform(put("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedInvoiceClient)))
            .andExpect(status().isOk());

        // Validate the InvoiceClient in the database
        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeUpdate);
        InvoiceClient testInvoiceClient = invoiceClientList.get(invoiceClientList.size() - 1);
        assertThat(testInvoiceClient.getSriEnviroment()).isEqualTo(UPDATED_S_RI_ENVIROMENT);
        assertThat(testInvoiceClient.getEmissionType()).isEqualTo(UPDATED_EMISSION_TYPE);
        assertThat(testInvoiceClient.getBusinessName()).isEqualTo(UPDATED_BUSINESS_NAME);
        assertThat(testInvoiceClient.getAccessKey()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testInvoiceClient.getCodeDocument()).isEqualTo(UPDATED_CODE_DOCUMENT);
        assertThat(testInvoiceClient.getDateIssue()).isEqualTo(UPDATED_DATE_ISSUE);
        assertThat(testInvoiceClient.getIdentificationType()).isEqualTo(UPDATED_IDENTIFICATION_TYPE);
        assertThat(testInvoiceClient.getIdentification()).isEqualTo(UPDATED_IDENTIFICATION);
        assertThat(testInvoiceClient.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testInvoiceClient.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testInvoiceClient.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testInvoiceClient.getTotalTaxFree()).isEqualTo(UPDATED_TOTAL_TAX_FREE);
        assertThat(testInvoiceClient.getTotalDiscount()).isEqualTo(UPDATED_TOTAL_DISCOUNT);
        assertThat(testInvoiceClient.getTotalBaseTaxIVA()).isEqualTo(UPDATED_TOTAL_BASE_TAX_IVA);
        assertThat(testInvoiceClient.getTotalBaseTaxICE()).isEqualTo(UPDATED_TOTAL_BASE_TAX_ICE);
        assertThat(testInvoiceClient.getTotalTaxIVA()).isEqualTo(UPDATED_TOTAL_TAX_IVA);
        assertThat(testInvoiceClient.getTotalTaxICE()).isEqualTo(UPDATED_TOTAL_TAX_ICE);
        assertThat(testInvoiceClient.getTip()).isEqualTo(UPDATED_TIP);
        assertThat(testInvoiceClient.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testInvoiceClient.getCurrency()).isEqualTo(UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void updateNonExistingInvoiceClient() throws Exception {
        int databaseSizeBeforeUpdate = invoiceClientRepository.findAll().size();

        // Create the InvoiceClient

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoiceClientMockMvc.perform(put("/api/invoice-clients")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceClient)))
            .andExpect(status().isBadRequest());

        // Validate the InvoiceClient in the database
        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvoiceClient() throws Exception {
        // Initialize the database
        invoiceClientService.save(invoiceClient);

        int databaseSizeBeforeDelete = invoiceClientRepository.findAll().size();

        // Delete the invoiceClient
        restInvoiceClientMockMvc.perform(delete("/api/invoice-clients/{id}", invoiceClient.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvoiceClient> invoiceClientList = invoiceClientRepository.findAll();
        assertThat(invoiceClientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
