package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.QuickSalesApiApp;
import ec.com.newsolutions.domain.SubCategory;
import ec.com.newsolutions.repository.SubCategoryRepository;
import ec.com.newsolutions.service.SubCategoryService;
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
 * Integration tests for the {@link SubCategoryResource} REST controller.
 */
@SpringBootTest(classes = QuickSalesApiApp.class)
public class SubCategoryResourceIT {

    private static final Boolean DEFAULT_PREDETERMINED = false;
    private static final Boolean UPDATED_PREDETERMINED = true;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private SubCategoryService subCategoryService;

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

    private MockMvc restSubCategoryMockMvc;

    private SubCategory subCategory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SubCategoryResource subCategoryResource = new SubCategoryResource(subCategoryService);
        this.restSubCategoryMockMvc = MockMvcBuilders.standaloneSetup(subCategoryResource)
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
    public static SubCategory createEntity(EntityManager em) {
        SubCategory subCategory = new SubCategory()
            .predetermined(DEFAULT_PREDETERMINED)
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION);
        return subCategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubCategory createUpdatedEntity(EntityManager em) {
        SubCategory subCategory = new SubCategory()
            .predetermined(UPDATED_PREDETERMINED)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        return subCategory;
    }

    @BeforeEach
    public void initTest() {
        subCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createSubCategory() throws Exception {
        int databaseSizeBeforeCreate = subCategoryRepository.findAll().size();

        // Create the SubCategory
        restSubCategoryMockMvc.perform(post("/api/sub-categories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subCategory)))
            .andExpect(status().isCreated());

        // Validate the SubCategory in the database
        List<SubCategory> subCategoryList = subCategoryRepository.findAll();
        assertThat(subCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        SubCategory testSubCategory = subCategoryList.get(subCategoryList.size() - 1);
        assertThat(testSubCategory.isPredetermined()).isEqualTo(DEFAULT_PREDETERMINED);
        assertThat(testSubCategory.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testSubCategory.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSubCategory.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createSubCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subCategoryRepository.findAll().size();

        // Create the SubCategory with an existing ID
        subCategory.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubCategoryMockMvc.perform(post("/api/sub-categories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subCategory)))
            .andExpect(status().isBadRequest());

        // Validate the SubCategory in the database
        List<SubCategory> subCategoryList = subCategoryRepository.findAll();
        assertThat(subCategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPredeterminedIsRequired() throws Exception {
        int databaseSizeBeforeTest = subCategoryRepository.findAll().size();
        // set the field null
        subCategory.setPredetermined(null);

        // Create the SubCategory, which fails.

        restSubCategoryMockMvc.perform(post("/api/sub-categories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subCategory)))
            .andExpect(status().isBadRequest());

        List<SubCategory> subCategoryList = subCategoryRepository.findAll();
        assertThat(subCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = subCategoryRepository.findAll().size();
        // set the field null
        subCategory.setCode(null);

        // Create the SubCategory, which fails.

        restSubCategoryMockMvc.perform(post("/api/sub-categories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subCategory)))
            .andExpect(status().isBadRequest());

        List<SubCategory> subCategoryList = subCategoryRepository.findAll();
        assertThat(subCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = subCategoryRepository.findAll().size();
        // set the field null
        subCategory.setName(null);

        // Create the SubCategory, which fails.

        restSubCategoryMockMvc.perform(post("/api/sub-categories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subCategory)))
            .andExpect(status().isBadRequest());

        List<SubCategory> subCategoryList = subCategoryRepository.findAll();
        assertThat(subCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSubCategories() throws Exception {
        // Initialize the database
        subCategoryRepository.saveAndFlush(subCategory);

        // Get all the subCategoryList
        restSubCategoryMockMvc.perform(get("/api/sub-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].predetermined").value(hasItem(DEFAULT_PREDETERMINED.booleanValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getSubCategory() throws Exception {
        // Initialize the database
        subCategoryRepository.saveAndFlush(subCategory);

        // Get the subCategory
        restSubCategoryMockMvc.perform(get("/api/sub-categories/{id}", subCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subCategory.getId().intValue()))
            .andExpect(jsonPath("$.predetermined").value(DEFAULT_PREDETERMINED.booleanValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    public void getNonExistingSubCategory() throws Exception {
        // Get the subCategory
        restSubCategoryMockMvc.perform(get("/api/sub-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSubCategory() throws Exception {
        // Initialize the database
        subCategoryService.save(subCategory);

        int databaseSizeBeforeUpdate = subCategoryRepository.findAll().size();

        // Update the subCategory
        SubCategory updatedSubCategory = subCategoryRepository.findById(subCategory.getId()).get();
        // Disconnect from session so that the updates on updatedSubCategory are not directly saved in db
        em.detach(updatedSubCategory);
        updatedSubCategory
            .predetermined(UPDATED_PREDETERMINED)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);

        restSubCategoryMockMvc.perform(put("/api/sub-categories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSubCategory)))
            .andExpect(status().isOk());

        // Validate the SubCategory in the database
        List<SubCategory> subCategoryList = subCategoryRepository.findAll();
        assertThat(subCategoryList).hasSize(databaseSizeBeforeUpdate);
        SubCategory testSubCategory = subCategoryList.get(subCategoryList.size() - 1);
        assertThat(testSubCategory.isPredetermined()).isEqualTo(UPDATED_PREDETERMINED);
        assertThat(testSubCategory.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testSubCategory.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSubCategory.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingSubCategory() throws Exception {
        int databaseSizeBeforeUpdate = subCategoryRepository.findAll().size();

        // Create the SubCategory

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubCategoryMockMvc.perform(put("/api/sub-categories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subCategory)))
            .andExpect(status().isBadRequest());

        // Validate the SubCategory in the database
        List<SubCategory> subCategoryList = subCategoryRepository.findAll();
        assertThat(subCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSubCategory() throws Exception {
        // Initialize the database
        subCategoryService.save(subCategory);

        int databaseSizeBeforeDelete = subCategoryRepository.findAll().size();

        // Delete the subCategory
        restSubCategoryMockMvc.perform(delete("/api/sub-categories/{id}", subCategory.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SubCategory> subCategoryList = subCategoryRepository.findAll();
        assertThat(subCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
