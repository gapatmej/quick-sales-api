package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.QuickSalesApiApp;
import ec.com.newsolutions.domain.Cellar;
import ec.com.newsolutions.repository.CellarRepository;
import ec.com.newsolutions.service.CellarService;
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
 * Integration tests for the {@link CellarResource} REST controller.
 */
@SpringBootTest(classes = QuickSalesApiApp.class)
public class CellarResourceIT {

    private static final Boolean DEFAULT_PREDETERMINED = false;
    private static final Boolean UPDATED_PREDETERMINED = true;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private CellarRepository cellarRepository;

    @Autowired
    private CellarService cellarService;

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

    private MockMvc restCellarMockMvc;

    private Cellar cellar;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CellarResource cellarResource = new CellarResource(cellarService);
        this.restCellarMockMvc = MockMvcBuilders.standaloneSetup(cellarResource)
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
    public static Cellar createEntity(EntityManager em) {
        Cellar cellar = new Cellar()
            .predetermined(DEFAULT_PREDETERMINED)
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .address(DEFAULT_ADDRESS)
            .description(DEFAULT_DESCRIPTION);
        return cellar;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cellar createUpdatedEntity(EntityManager em) {
        Cellar cellar = new Cellar()
            .predetermined(UPDATED_PREDETERMINED)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .description(UPDATED_DESCRIPTION);
        return cellar;
    }

    @BeforeEach
    public void initTest() {
        cellar = createEntity(em);
    }

    @Test
    @Transactional
    public void createCellar() throws Exception {
        int databaseSizeBeforeCreate = cellarRepository.findAll().size();

        // Create the Cellar
        restCellarMockMvc.perform(post("/api/cellars")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cellar)))
            .andExpect(status().isCreated());

        // Validate the Cellar in the database
        List<Cellar> cellarList = cellarRepository.findAll();
        assertThat(cellarList).hasSize(databaseSizeBeforeCreate + 1);
        Cellar testCellar = cellarList.get(cellarList.size() - 1);
        assertThat(testCellar.isPredetermined()).isEqualTo(DEFAULT_PREDETERMINED);
        assertThat(testCellar.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCellar.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCellar.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testCellar.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createCellarWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cellarRepository.findAll().size();

        // Create the Cellar with an existing ID
        cellar.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCellarMockMvc.perform(post("/api/cellars")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cellar)))
            .andExpect(status().isBadRequest());

        // Validate the Cellar in the database
        List<Cellar> cellarList = cellarRepository.findAll();
        assertThat(cellarList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPredeterminedIsRequired() throws Exception {
        int databaseSizeBeforeTest = cellarRepository.findAll().size();
        // set the field null
        cellar.setPredetermined(null);

        // Create the Cellar, which fails.

        restCellarMockMvc.perform(post("/api/cellars")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cellar)))
            .andExpect(status().isBadRequest());

        List<Cellar> cellarList = cellarRepository.findAll();
        assertThat(cellarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = cellarRepository.findAll().size();
        // set the field null
        cellar.setCode(null);

        // Create the Cellar, which fails.

        restCellarMockMvc.perform(post("/api/cellars")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cellar)))
            .andExpect(status().isBadRequest());

        List<Cellar> cellarList = cellarRepository.findAll();
        assertThat(cellarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = cellarRepository.findAll().size();
        // set the field null
        cellar.setName(null);

        // Create the Cellar, which fails.

        restCellarMockMvc.perform(post("/api/cellars")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cellar)))
            .andExpect(status().isBadRequest());

        List<Cellar> cellarList = cellarRepository.findAll();
        assertThat(cellarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCellars() throws Exception {
        // Initialize the database
        cellarRepository.saveAndFlush(cellar);

        // Get all the cellarList
        restCellarMockMvc.perform(get("/api/cellars?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cellar.getId().intValue())))
            .andExpect(jsonPath("$.[*].predetermined").value(hasItem(DEFAULT_PREDETERMINED.booleanValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getCellar() throws Exception {
        // Initialize the database
        cellarRepository.saveAndFlush(cellar);

        // Get the cellar
        restCellarMockMvc.perform(get("/api/cellars/{id}", cellar.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cellar.getId().intValue()))
            .andExpect(jsonPath("$.predetermined").value(DEFAULT_PREDETERMINED.booleanValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    public void getNonExistingCellar() throws Exception {
        // Get the cellar
        restCellarMockMvc.perform(get("/api/cellars/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCellar() throws Exception {
        // Initialize the database
        cellarService.save(cellar);

        int databaseSizeBeforeUpdate = cellarRepository.findAll().size();

        // Update the cellar
        Cellar updatedCellar = cellarRepository.findById(cellar.getId()).get();
        // Disconnect from session so that the updates on updatedCellar are not directly saved in db
        em.detach(updatedCellar);
        updatedCellar
            .predetermined(UPDATED_PREDETERMINED)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .description(UPDATED_DESCRIPTION);

        restCellarMockMvc.perform(put("/api/cellars")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCellar)))
            .andExpect(status().isOk());

        // Validate the Cellar in the database
        List<Cellar> cellarList = cellarRepository.findAll();
        assertThat(cellarList).hasSize(databaseSizeBeforeUpdate);
        Cellar testCellar = cellarList.get(cellarList.size() - 1);
        assertThat(testCellar.isPredetermined()).isEqualTo(UPDATED_PREDETERMINED);
        assertThat(testCellar.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCellar.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCellar.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testCellar.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingCellar() throws Exception {
        int databaseSizeBeforeUpdate = cellarRepository.findAll().size();

        // Create the Cellar

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCellarMockMvc.perform(put("/api/cellars")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cellar)))
            .andExpect(status().isBadRequest());

        // Validate the Cellar in the database
        List<Cellar> cellarList = cellarRepository.findAll();
        assertThat(cellarList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCellar() throws Exception {
        // Initialize the database
        cellarService.save(cellar);

        int databaseSizeBeforeDelete = cellarRepository.findAll().size();

        // Delete the cellar
        restCellarMockMvc.perform(delete("/api/cellars/{id}", cellar.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cellar> cellarList = cellarRepository.findAll();
        assertThat(cellarList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
