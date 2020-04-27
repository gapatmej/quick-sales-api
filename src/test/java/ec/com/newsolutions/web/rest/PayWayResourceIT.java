package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.QuickSalesApiApp;
import ec.com.newsolutions.domain.PayWay;
import ec.com.newsolutions.repository.PayWayRepository;
import ec.com.newsolutions.service.PayWayService;
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
 * Integration tests for the {@link PayWayResource} REST controller.
 */
@SpringBootTest(classes = QuickSalesApiApp.class)
public class PayWayResourceIT {

    private static final Integer DEFAULT_CODE = 99;
    private static final Integer UPDATED_CODE = 98;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private PayWayRepository payWayRepository;

    @Autowired
    private PayWayService payWayService;

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

    private MockMvc restPayWayMockMvc;

    private PayWay payWay;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PayWayResource payWayResource = new PayWayResource(payWayService);
        this.restPayWayMockMvc = MockMvcBuilders.standaloneSetup(payWayResource)
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
    public static PayWay createEntity(EntityManager em) {
        PayWay payWay = new PayWay()
            .code(DEFAULT_CODE)
            .description(DEFAULT_DESCRIPTION);
        return payWay;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PayWay createUpdatedEntity(EntityManager em) {
        PayWay payWay = new PayWay()
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION);
        return payWay;
    }

    @BeforeEach
    public void initTest() {
        payWay = createEntity(em);
    }

    @Test
    @Transactional
    public void createPayWay() throws Exception {
        int databaseSizeBeforeCreate = payWayRepository.findAll().size();

        // Create the PayWay
        restPayWayMockMvc.perform(post("/api/pay-ways")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(payWay)))
            .andExpect(status().isCreated());

        // Validate the PayWay in the database
        List<PayWay> payWayList = payWayRepository.findAll();
        assertThat(payWayList).hasSize(databaseSizeBeforeCreate + 1);
        PayWay testPayWay = payWayList.get(payWayList.size() - 1);
        assertThat(testPayWay.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testPayWay.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createPayWayWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = payWayRepository.findAll().size();

        // Create the PayWay with an existing ID
        payWay.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPayWayMockMvc.perform(post("/api/pay-ways")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(payWay)))
            .andExpect(status().isBadRequest());

        // Validate the PayWay in the database
        List<PayWay> payWayList = payWayRepository.findAll();
        assertThat(payWayList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = payWayRepository.findAll().size();
        // set the field null
        payWay.setCode(null);

        // Create the PayWay, which fails.

        restPayWayMockMvc.perform(post("/api/pay-ways")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(payWay)))
            .andExpect(status().isBadRequest());

        List<PayWay> payWayList = payWayRepository.findAll();
        assertThat(payWayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = payWayRepository.findAll().size();
        // set the field null
        payWay.setDescription(null);

        // Create the PayWay, which fails.

        restPayWayMockMvc.perform(post("/api/pay-ways")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(payWay)))
            .andExpect(status().isBadRequest());

        List<PayWay> payWayList = payWayRepository.findAll();
        assertThat(payWayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPayWays() throws Exception {
        // Initialize the database
        payWayRepository.saveAndFlush(payWay);

        // Get all the payWayList
        restPayWayMockMvc.perform(get("/api/pay-ways?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(payWay.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getPayWay() throws Exception {
        // Initialize the database
        payWayRepository.saveAndFlush(payWay);

        // Get the payWay
        restPayWayMockMvc.perform(get("/api/pay-ways/{id}", payWay.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(payWay.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    public void getNonExistingPayWay() throws Exception {
        // Get the payWay
        restPayWayMockMvc.perform(get("/api/pay-ways/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePayWay() throws Exception {
        // Initialize the database
        payWayService.save(payWay);

        int databaseSizeBeforeUpdate = payWayRepository.findAll().size();

        // Update the payWay
        PayWay updatedPayWay = payWayRepository.findById(payWay.getId()).get();
        // Disconnect from session so that the updates on updatedPayWay are not directly saved in db
        em.detach(updatedPayWay);
        updatedPayWay
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION);

        restPayWayMockMvc.perform(put("/api/pay-ways")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPayWay)))
            .andExpect(status().isOk());

        // Validate the PayWay in the database
        List<PayWay> payWayList = payWayRepository.findAll();
        assertThat(payWayList).hasSize(databaseSizeBeforeUpdate);
        PayWay testPayWay = payWayList.get(payWayList.size() - 1);
        assertThat(testPayWay.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testPayWay.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingPayWay() throws Exception {
        int databaseSizeBeforeUpdate = payWayRepository.findAll().size();

        // Create the PayWay

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPayWayMockMvc.perform(put("/api/pay-ways")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(payWay)))
            .andExpect(status().isBadRequest());

        // Validate the PayWay in the database
        List<PayWay> payWayList = payWayRepository.findAll();
        assertThat(payWayList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePayWay() throws Exception {
        // Initialize the database
        payWayService.save(payWay);

        int databaseSizeBeforeDelete = payWayRepository.findAll().size();

        // Delete the payWay
        restPayWayMockMvc.perform(delete("/api/pay-ways/{id}", payWay.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PayWay> payWayList = payWayRepository.findAll();
        assertThat(payWayList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
