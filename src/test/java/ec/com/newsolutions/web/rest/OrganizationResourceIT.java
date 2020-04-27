package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.QuickSalesApiApp;
import ec.com.newsolutions.domain.Organization;
import ec.com.newsolutions.repository.OrganizationRepository;
import ec.com.newsolutions.service.OrganizationService;
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
import java.util.List;

import static ec.com.newsolutions.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ec.com.newsolutions.domain.enumeration.TokenEnum;
/**
 * Integration tests for the {@link OrganizationResource} REST controller.
 */
@SpringBootTest(classes = QuickSalesApiApp.class)
public class OrganizationResourceIT {

    private static final String DEFAULT_IDENTIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFICATION = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TRADENAME = "AAAAAAAAAA";
    private static final String UPDATED_TRADENAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_MOVIL_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_MOVIL_PHONE = "BBBBBBBBBB";

    private static final Integer DEFAULT_SPECIAL_TAXPAYER_NUMBER = 99999;
    private static final Integer UPDATED_SPECIAL_TAXPAYER_NUMBER = 99998;

    private static final Boolean DEFAULT_IS_KEEP_ACCOUNTING = false;
    private static final Boolean UPDATED_IS_KEEP_ACCOUNTING = true;

    private static final String DEFAULT_LOGO = "AAAAAAAAAA";
    private static final String UPDATED_LOGO = "BBBBBBBBBB";

    private static final TokenEnum DEFAULT_TOKEN = TokenEnum.EXPORTED_CERTIFICATE;
    private static final TokenEnum UPDATED_TOKEN = TokenEnum.IKEY_2032;

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationService organizationService;

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

    private MockMvc restOrganizationMockMvc;

    private Organization organization;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrganizationResource organizationResource = new OrganizationResource(organizationService);
        this.restOrganizationMockMvc = MockMvcBuilders.standaloneSetup(organizationResource)
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
    public static Organization createEntity(EntityManager em) {
        Organization organization = new Organization()
            .identification(DEFAULT_IDENTIFICATION)
            .businessName(DEFAULT_BUSINESS_NAME)
            .tradename(DEFAULT_TRADENAME)
            .address(DEFAULT_ADDRESS)
            .phone(DEFAULT_PHONE)
            .movilPhone(DEFAULT_MOVIL_PHONE)
            .specialTaxpayerNumber(DEFAULT_SPECIAL_TAXPAYER_NUMBER)
            .isKeepAccounting(DEFAULT_IS_KEEP_ACCOUNTING)
            .logo(DEFAULT_LOGO)
            .token(DEFAULT_TOKEN)
            .mail(DEFAULT_MAIL);
        return organization;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Organization createUpdatedEntity(EntityManager em) {
        Organization organization = new Organization()
            .identification(UPDATED_IDENTIFICATION)
            .businessName(UPDATED_BUSINESS_NAME)
            .tradename(UPDATED_TRADENAME)
            .address(UPDATED_ADDRESS)
            .phone(UPDATED_PHONE)
            .movilPhone(UPDATED_MOVIL_PHONE)
            .specialTaxpayerNumber(UPDATED_SPECIAL_TAXPAYER_NUMBER)
            .isKeepAccounting(UPDATED_IS_KEEP_ACCOUNTING)
            .logo(UPDATED_LOGO)
            .token(UPDATED_TOKEN)
            .mail(UPDATED_MAIL);
        return organization;
    }

    @BeforeEach
    public void initTest() {
        organization = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrganization() throws Exception {
        int databaseSizeBeforeCreate = organizationRepository.findAll().size();

        // Create the Organization
        restOrganizationMockMvc.perform(post("/api/organizations")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organization)))
            .andExpect(status().isCreated());

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeCreate + 1);
        Organization testOrganization = organizationList.get(organizationList.size() - 1);
        assertThat(testOrganization.getIdentification()).isEqualTo(DEFAULT_IDENTIFICATION);
        assertThat(testOrganization.getBusinessName()).isEqualTo(DEFAULT_BUSINESS_NAME);
        assertThat(testOrganization.getTradename()).isEqualTo(DEFAULT_TRADENAME);
        assertThat(testOrganization.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testOrganization.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testOrganization.getMovilPhone()).isEqualTo(DEFAULT_MOVIL_PHONE);
        assertThat(testOrganization.getSpecialTaxpayerNumber()).isEqualTo(DEFAULT_SPECIAL_TAXPAYER_NUMBER);
        assertThat(testOrganization.isIsKeepAccounting()).isEqualTo(DEFAULT_IS_KEEP_ACCOUNTING);
        assertThat(testOrganization.getLogo()).isEqualTo(DEFAULT_LOGO);
        assertThat(testOrganization.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testOrganization.getMail()).isEqualTo(DEFAULT_MAIL);
    }

    @Test
    @Transactional
    public void createOrganizationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = organizationRepository.findAll().size();

        // Create the Organization with an existing ID
        organization.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrganizationMockMvc.perform(post("/api/organizations")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organization)))
            .andExpect(status().isBadRequest());

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdentificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = organizationRepository.findAll().size();
        // set the field null
        organization.setIdentification(null);

        // Create the Organization, which fails.

        restOrganizationMockMvc.perform(post("/api/organizations")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organization)))
            .andExpect(status().isBadRequest());

        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBusinessNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = organizationRepository.findAll().size();
        // set the field null
        organization.setBusinessName(null);

        // Create the Organization, which fails.

        restOrganizationMockMvc.perform(post("/api/organizations")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organization)))
            .andExpect(status().isBadRequest());

        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTradenameIsRequired() throws Exception {
        int databaseSizeBeforeTest = organizationRepository.findAll().size();
        // set the field null
        organization.setTradename(null);

        // Create the Organization, which fails.

        restOrganizationMockMvc.perform(post("/api/organizations")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organization)))
            .andExpect(status().isBadRequest());

        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = organizationRepository.findAll().size();
        // set the field null
        organization.setAddress(null);

        // Create the Organization, which fails.

        restOrganizationMockMvc.perform(post("/api/organizations")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organization)))
            .andExpect(status().isBadRequest());

        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsKeepAccountingIsRequired() throws Exception {
        int databaseSizeBeforeTest = organizationRepository.findAll().size();
        // set the field null
        organization.setIsKeepAccounting(null);

        // Create the Organization, which fails.

        restOrganizationMockMvc.perform(post("/api/organizations")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organization)))
            .andExpect(status().isBadRequest());

        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTokenIsRequired() throws Exception {
        int databaseSizeBeforeTest = organizationRepository.findAll().size();
        // set the field null
        organization.setToken(null);

        // Create the Organization, which fails.

        restOrganizationMockMvc.perform(post("/api/organizations")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organization)))
            .andExpect(status().isBadRequest());

        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMailIsRequired() throws Exception {
        int databaseSizeBeforeTest = organizationRepository.findAll().size();
        // set the field null
        organization.setMail(null);

        // Create the Organization, which fails.

        restOrganizationMockMvc.perform(post("/api/organizations")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organization)))
            .andExpect(status().isBadRequest());

        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrganizations() throws Exception {
        // Initialize the database
        organizationRepository.saveAndFlush(organization);

        // Get all the organizationList
        restOrganizationMockMvc.perform(get("/api/organizations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(organization.getId().intValue())))
            .andExpect(jsonPath("$.[*].identification").value(hasItem(DEFAULT_IDENTIFICATION)))
            .andExpect(jsonPath("$.[*].businessName").value(hasItem(DEFAULT_BUSINESS_NAME)))
            .andExpect(jsonPath("$.[*].tradename").value(hasItem(DEFAULT_TRADENAME)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].movilPhone").value(hasItem(DEFAULT_MOVIL_PHONE)))
            .andExpect(jsonPath("$.[*].specialTaxpayerNumber").value(hasItem(DEFAULT_SPECIAL_TAXPAYER_NUMBER)))
            .andExpect(jsonPath("$.[*].isKeepAccounting").value(hasItem(DEFAULT_IS_KEEP_ACCOUNTING.booleanValue())))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(DEFAULT_LOGO)))
            .andExpect(jsonPath("$.[*].token").value(hasItem(DEFAULT_TOKEN.toString())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL)));
    }
    
    @Test
    @Transactional
    public void getOrganization() throws Exception {
        // Initialize the database
        organizationRepository.saveAndFlush(organization);

        // Get the organization
        restOrganizationMockMvc.perform(get("/api/organizations/{id}", organization.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(organization.getId().intValue()))
            .andExpect(jsonPath("$.identification").value(DEFAULT_IDENTIFICATION))
            .andExpect(jsonPath("$.businessName").value(DEFAULT_BUSINESS_NAME))
            .andExpect(jsonPath("$.tradename").value(DEFAULT_TRADENAME))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.movilPhone").value(DEFAULT_MOVIL_PHONE))
            .andExpect(jsonPath("$.specialTaxpayerNumber").value(DEFAULT_SPECIAL_TAXPAYER_NUMBER))
            .andExpect(jsonPath("$.isKeepAccounting").value(DEFAULT_IS_KEEP_ACCOUNTING.booleanValue()))
            .andExpect(jsonPath("$.logo").value(DEFAULT_LOGO))
            .andExpect(jsonPath("$.token").value(DEFAULT_TOKEN.toString()))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL));
    }

    @Test
    @Transactional
    public void getNonExistingOrganization() throws Exception {
        // Get the organization
        restOrganizationMockMvc.perform(get("/api/organizations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrganization() throws Exception {
        // Initialize the database
        organizationService.save(organization);

        int databaseSizeBeforeUpdate = organizationRepository.findAll().size();

        // Update the organization
        Organization updatedOrganization = organizationRepository.findById(organization.getId()).get();
        // Disconnect from session so that the updates on updatedOrganization are not directly saved in db
        em.detach(updatedOrganization);
        updatedOrganization
            .identification(UPDATED_IDENTIFICATION)
            .businessName(UPDATED_BUSINESS_NAME)
            .tradename(UPDATED_TRADENAME)
            .address(UPDATED_ADDRESS)
            .phone(UPDATED_PHONE)
            .movilPhone(UPDATED_MOVIL_PHONE)
            .specialTaxpayerNumber(UPDATED_SPECIAL_TAXPAYER_NUMBER)
            .isKeepAccounting(UPDATED_IS_KEEP_ACCOUNTING)
            .logo(UPDATED_LOGO)
            .token(UPDATED_TOKEN)
            .mail(UPDATED_MAIL);

        restOrganizationMockMvc.perform(put("/api/organizations")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrganization)))
            .andExpect(status().isOk());

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeUpdate);
        Organization testOrganization = organizationList.get(organizationList.size() - 1);
        assertThat(testOrganization.getIdentification()).isEqualTo(UPDATED_IDENTIFICATION);
        assertThat(testOrganization.getBusinessName()).isEqualTo(UPDATED_BUSINESS_NAME);
        assertThat(testOrganization.getTradename()).isEqualTo(UPDATED_TRADENAME);
        assertThat(testOrganization.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testOrganization.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testOrganization.getMovilPhone()).isEqualTo(UPDATED_MOVIL_PHONE);
        assertThat(testOrganization.getSpecialTaxpayerNumber()).isEqualTo(UPDATED_SPECIAL_TAXPAYER_NUMBER);
        assertThat(testOrganization.isIsKeepAccounting()).isEqualTo(UPDATED_IS_KEEP_ACCOUNTING);
        assertThat(testOrganization.getLogo()).isEqualTo(UPDATED_LOGO);
        assertThat(testOrganization.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testOrganization.getMail()).isEqualTo(UPDATED_MAIL);
    }

    @Test
    @Transactional
    public void updateNonExistingOrganization() throws Exception {
        int databaseSizeBeforeUpdate = organizationRepository.findAll().size();

        // Create the Organization

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrganizationMockMvc.perform(put("/api/organizations")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organization)))
            .andExpect(status().isBadRequest());

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrganization() throws Exception {
        // Initialize the database
        organizationService.save(organization);

        int databaseSizeBeforeDelete = organizationRepository.findAll().size();

        // Delete the organization
        restOrganizationMockMvc.perform(delete("/api/organizations/{id}", organization.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
