package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.QuickSalesApiApp;
import ec.com.newsolutions.domain.BranchOffice;
import ec.com.newsolutions.repository.BranchOfficeRepository;
import ec.com.newsolutions.service.BranchOfficeService;
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

/**
 * Integration tests for the {@link BranchOfficeResource} REST controller.
 */
@SpringBootTest(classes = QuickSalesApiApp.class)
public class BranchOfficeResourceIT {

    private static final String DEFAULT_BUSINESS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_MOVIL_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_MOVIL_PHONE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ESTABLISHMENT_CODE = 999;
    private static final Integer UPDATED_ESTABLISHMENT_CODE = 998;

    private static final Integer DEFAULT_EMISSION_POINT_CODE = 999;
    private static final Integer UPDATED_EMISSION_POINT_CODE = 998;

    @Autowired
    private BranchOfficeRepository branchOfficeRepository;

    @Autowired
    private BranchOfficeService branchOfficeService;

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

    private MockMvc restBranchOfficeMockMvc;

    private BranchOffice branchOffice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BranchOfficeResource branchOfficeResource = new BranchOfficeResource(branchOfficeService);
        this.restBranchOfficeMockMvc = MockMvcBuilders.standaloneSetup(branchOfficeResource)
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
    public static BranchOffice createEntity(EntityManager em) {
        BranchOffice branchOffice = new BranchOffice()
            .businessName(DEFAULT_BUSINESS_NAME)
            .address(DEFAULT_ADDRESS)
            .phone(DEFAULT_PHONE)
            .movilPhone(DEFAULT_MOVIL_PHONE)
            .establishmentCode(DEFAULT_ESTABLISHMENT_CODE)
            .emissionPointCode(DEFAULT_EMISSION_POINT_CODE);
        return branchOffice;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchOffice createUpdatedEntity(EntityManager em) {
        BranchOffice branchOffice = new BranchOffice()
            .businessName(UPDATED_BUSINESS_NAME)
            .address(UPDATED_ADDRESS)
            .phone(UPDATED_PHONE)
            .movilPhone(UPDATED_MOVIL_PHONE)
            .establishmentCode(UPDATED_ESTABLISHMENT_CODE)
            .emissionPointCode(UPDATED_EMISSION_POINT_CODE);
        return branchOffice;
    }

    @BeforeEach
    public void initTest() {
        branchOffice = createEntity(em);
    }

    @Test
    @Transactional
    public void createBranchOffice() throws Exception {
        int databaseSizeBeforeCreate = branchOfficeRepository.findAll().size();

        // Create the BranchOffice
        restBranchOfficeMockMvc.perform(post("/api/branch-offices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(branchOffice)))
            .andExpect(status().isCreated());

        // Validate the BranchOffice in the database
        List<BranchOffice> branchOfficeList = branchOfficeRepository.findAll();
        assertThat(branchOfficeList).hasSize(databaseSizeBeforeCreate + 1);
        BranchOffice testBranchOffice = branchOfficeList.get(branchOfficeList.size() - 1);
        assertThat(testBranchOffice.getBusinessName()).isEqualTo(DEFAULT_BUSINESS_NAME);
        assertThat(testBranchOffice.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testBranchOffice.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testBranchOffice.getMovilPhone()).isEqualTo(DEFAULT_MOVIL_PHONE);
        assertThat(testBranchOffice.getEstablishmentCode()).isEqualTo(DEFAULT_ESTABLISHMENT_CODE);
        assertThat(testBranchOffice.getEmissionPointCode()).isEqualTo(DEFAULT_EMISSION_POINT_CODE);
    }

    @Test
    @Transactional
    public void createBranchOfficeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = branchOfficeRepository.findAll().size();

        // Create the BranchOffice with an existing ID
        branchOffice.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBranchOfficeMockMvc.perform(post("/api/branch-offices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(branchOffice)))
            .andExpect(status().isBadRequest());

        // Validate the BranchOffice in the database
        List<BranchOffice> branchOfficeList = branchOfficeRepository.findAll();
        assertThat(branchOfficeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkBusinessNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = branchOfficeRepository.findAll().size();
        // set the field null
        branchOffice.setBusinessName(null);

        // Create the BranchOffice, which fails.

        restBranchOfficeMockMvc.perform(post("/api/branch-offices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(branchOffice)))
            .andExpect(status().isBadRequest());

        List<BranchOffice> branchOfficeList = branchOfficeRepository.findAll();
        assertThat(branchOfficeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = branchOfficeRepository.findAll().size();
        // set the field null
        branchOffice.setAddress(null);

        // Create the BranchOffice, which fails.

        restBranchOfficeMockMvc.perform(post("/api/branch-offices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(branchOffice)))
            .andExpect(status().isBadRequest());

        List<BranchOffice> branchOfficeList = branchOfficeRepository.findAll();
        assertThat(branchOfficeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBranchOffices() throws Exception {
        // Initialize the database
        branchOfficeRepository.saveAndFlush(branchOffice);

        // Get all the branchOfficeList
        restBranchOfficeMockMvc.perform(get("/api/branch-offices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(branchOffice.getId().intValue())))
            .andExpect(jsonPath("$.[*].businessName").value(hasItem(DEFAULT_BUSINESS_NAME)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].movilPhone").value(hasItem(DEFAULT_MOVIL_PHONE)))
            .andExpect(jsonPath("$.[*].establishmentCode").value(hasItem(DEFAULT_ESTABLISHMENT_CODE)))
            .andExpect(jsonPath("$.[*].emissionPointCode").value(hasItem(DEFAULT_EMISSION_POINT_CODE)));
    }
    
    @Test
    @Transactional
    public void getBranchOffice() throws Exception {
        // Initialize the database
        branchOfficeRepository.saveAndFlush(branchOffice);

        // Get the branchOffice
        restBranchOfficeMockMvc.perform(get("/api/branch-offices/{id}", branchOffice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(branchOffice.getId().intValue()))
            .andExpect(jsonPath("$.businessName").value(DEFAULT_BUSINESS_NAME))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.movilPhone").value(DEFAULT_MOVIL_PHONE))
            .andExpect(jsonPath("$.establishmentCode").value(DEFAULT_ESTABLISHMENT_CODE))
            .andExpect(jsonPath("$.emissionPointCode").value(DEFAULT_EMISSION_POINT_CODE));
    }

    @Test
    @Transactional
    public void getNonExistingBranchOffice() throws Exception {
        // Get the branchOffice
        restBranchOfficeMockMvc.perform(get("/api/branch-offices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBranchOffice() throws Exception {
        // Initialize the database
        branchOfficeService.save(branchOffice);

        int databaseSizeBeforeUpdate = branchOfficeRepository.findAll().size();

        // Update the branchOffice
        BranchOffice updatedBranchOffice = branchOfficeRepository.findById(branchOffice.getId()).get();
        // Disconnect from session so that the updates on updatedBranchOffice are not directly saved in db
        em.detach(updatedBranchOffice);
        updatedBranchOffice
            .businessName(UPDATED_BUSINESS_NAME)
            .address(UPDATED_ADDRESS)
            .phone(UPDATED_PHONE)
            .movilPhone(UPDATED_MOVIL_PHONE)
            .establishmentCode(UPDATED_ESTABLISHMENT_CODE)
            .emissionPointCode(UPDATED_EMISSION_POINT_CODE);

        restBranchOfficeMockMvc.perform(put("/api/branch-offices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBranchOffice)))
            .andExpect(status().isOk());

        // Validate the BranchOffice in the database
        List<BranchOffice> branchOfficeList = branchOfficeRepository.findAll();
        assertThat(branchOfficeList).hasSize(databaseSizeBeforeUpdate);
        BranchOffice testBranchOffice = branchOfficeList.get(branchOfficeList.size() - 1);
        assertThat(testBranchOffice.getBusinessName()).isEqualTo(UPDATED_BUSINESS_NAME);
        assertThat(testBranchOffice.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testBranchOffice.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testBranchOffice.getMovilPhone()).isEqualTo(UPDATED_MOVIL_PHONE);
        assertThat(testBranchOffice.getEstablishmentCode()).isEqualTo(UPDATED_ESTABLISHMENT_CODE);
        assertThat(testBranchOffice.getEmissionPointCode()).isEqualTo(UPDATED_EMISSION_POINT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingBranchOffice() throws Exception {
        int databaseSizeBeforeUpdate = branchOfficeRepository.findAll().size();

        // Create the BranchOffice

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchOfficeMockMvc.perform(put("/api/branch-offices")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(branchOffice)))
            .andExpect(status().isBadRequest());

        // Validate the BranchOffice in the database
        List<BranchOffice> branchOfficeList = branchOfficeRepository.findAll();
        assertThat(branchOfficeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBranchOffice() throws Exception {
        // Initialize the database
        branchOfficeService.save(branchOffice);

        int databaseSizeBeforeDelete = branchOfficeRepository.findAll().size();

        // Delete the branchOffice
        restBranchOfficeMockMvc.perform(delete("/api/branch-offices/{id}", branchOffice.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BranchOffice> branchOfficeList = branchOfficeRepository.findAll();
        assertThat(branchOfficeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
