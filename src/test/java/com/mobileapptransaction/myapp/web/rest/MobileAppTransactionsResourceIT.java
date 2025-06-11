package com.mobileapptransaction.myapp.web.rest;

import static com.mobileapptransaction.myapp.domain.MobileAppTransactionsAsserts.*;
import static com.mobileapptransaction.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobileapptransaction.myapp.IntegrationTest;
import com.mobileapptransaction.myapp.domain.MobileAppTransactions;
import com.mobileapptransaction.myapp.repository.MobileAppTransactionsRepository;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link MobileAppTransactionsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MobileAppTransactionsResourceIT {

    private static final String DEFAULT_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_CHANNEL_IP = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL_IP = "BBBBBBBBBB";

    private static final String DEFAULT_CHANNEL_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_CHANNEL_TIMESTAMP = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL_TIMESTAMP = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_AT = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_AT = "BBBBBBBBBB";

    private static final String DEFAULT_DEBIT_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_DEBIT_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECTION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECTION = "BBBBBBBBBB";

    private static final String DEFAULT_ERROR_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ERROR_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_GEOLOCATION = "AAAAAAAAAA";
    private static final String UPDATED_GEOLOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_HOST_CODE = "AAAAAAAAAA";
    private static final String UPDATED_HOST_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_USER_AGENT = "AAAAAAAAAA";
    private static final String UPDATED_USER_AGENT = "BBBBBBBBBB";

    private static final String DEFAULT_USER_AGENT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_USER_AGENT_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_CHARGEAMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_CHARGEAMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_CREDIT_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_CREDIT_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_CBS_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_CBS_REFERENCE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/mobile-app-transactions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MobileAppTransactionsRepository mobileAppTransactionsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMobileAppTransactionsMockMvc;

    private MobileAppTransactions mobileAppTransactions;

    private MobileAppTransactions insertedMobileAppTransactions;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MobileAppTransactions createEntity() {
        return new MobileAppTransactions()
            .channel(DEFAULT_CHANNEL)
            .channelIp(DEFAULT_CHANNEL_IP)
            .channelReference(DEFAULT_CHANNEL_REFERENCE)
            .channelTimestamp(DEFAULT_CHANNEL_TIMESTAMP)
            .clientId(DEFAULT_CLIENT_ID)
            .createdAt(DEFAULT_CREATED_AT)
            .debitAccount(DEFAULT_DEBIT_ACCOUNT)
            .direction(DEFAULT_DIRECTION)
            .errorDescription(DEFAULT_ERROR_DESCRIPTION)
            .geolocation(DEFAULT_GEOLOCATION)
            .hostCode(DEFAULT_HOST_CODE)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .responseCode(DEFAULT_RESPONSE_CODE)
            .responseMessage(DEFAULT_RESPONSE_MESSAGE)
            .transactionCode(DEFAULT_TRANSACTION_CODE)
            .transactionType(DEFAULT_TRANSACTION_TYPE)
            .userAgent(DEFAULT_USER_AGENT)
            .userAgentVersion(DEFAULT_USER_AGENT_VERSION)
            .amount(DEFAULT_AMOUNT)
            .chargeamount(DEFAULT_CHARGEAMOUNT)
            .creditAccount(DEFAULT_CREDIT_ACCOUNT)
            .cbsReference(DEFAULT_CBS_REFERENCE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MobileAppTransactions createUpdatedEntity() {
        return new MobileAppTransactions()
            .channel(UPDATED_CHANNEL)
            .channelIp(UPDATED_CHANNEL_IP)
            .channelReference(UPDATED_CHANNEL_REFERENCE)
            .channelTimestamp(UPDATED_CHANNEL_TIMESTAMP)
            .clientId(UPDATED_CLIENT_ID)
            .createdAt(UPDATED_CREATED_AT)
            .debitAccount(UPDATED_DEBIT_ACCOUNT)
            .direction(UPDATED_DIRECTION)
            .errorDescription(UPDATED_ERROR_DESCRIPTION)
            .geolocation(UPDATED_GEOLOCATION)
            .hostCode(UPDATED_HOST_CODE)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .responseCode(UPDATED_RESPONSE_CODE)
            .responseMessage(UPDATED_RESPONSE_MESSAGE)
            .transactionCode(UPDATED_TRANSACTION_CODE)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .userAgent(UPDATED_USER_AGENT)
            .userAgentVersion(UPDATED_USER_AGENT_VERSION)
            .amount(UPDATED_AMOUNT)
            .chargeamount(UPDATED_CHARGEAMOUNT)
            .creditAccount(UPDATED_CREDIT_ACCOUNT)
            .cbsReference(UPDATED_CBS_REFERENCE);
    }

    @BeforeEach
    void initTest() {
        mobileAppTransactions = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedMobileAppTransactions != null) {
            mobileAppTransactionsRepository.delete(insertedMobileAppTransactions);
            insertedMobileAppTransactions = null;
        }
    }

    @Test
    @Transactional
    void createMobileAppTransactions() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the MobileAppTransactions
        var returnedMobileAppTransactions = om.readValue(
            restMobileAppTransactionsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(mobileAppTransactions)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            MobileAppTransactions.class
        );

        // Validate the MobileAppTransactions in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertMobileAppTransactionsUpdatableFieldsEquals(
            returnedMobileAppTransactions,
            getPersistedMobileAppTransactions(returnedMobileAppTransactions)
        );

        insertedMobileAppTransactions = returnedMobileAppTransactions;
    }

    @Test
    @Transactional
    void createMobileAppTransactionsWithExistingId() throws Exception {
        // Create the MobileAppTransactions with an existing ID
        mobileAppTransactions.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMobileAppTransactionsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(mobileAppTransactions)))
            .andExpect(status().isBadRequest());

        // Validate the MobileAppTransactions in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMobileAppTransactions() throws Exception {
        // Initialize the database
        insertedMobileAppTransactions = mobileAppTransactionsRepository.saveAndFlush(mobileAppTransactions);

        // Get all the mobileAppTransactionsList
        restMobileAppTransactionsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mobileAppTransactions.getId().intValue())))
            .andExpect(jsonPath("$.[*].channel").value(hasItem(DEFAULT_CHANNEL)))
            .andExpect(jsonPath("$.[*].channelIp").value(hasItem(DEFAULT_CHANNEL_IP)))
            .andExpect(jsonPath("$.[*].channelReference").value(hasItem(DEFAULT_CHANNEL_REFERENCE)))
            .andExpect(jsonPath("$.[*].channelTimestamp").value(hasItem(DEFAULT_CHANNEL_TIMESTAMP)))
            .andExpect(jsonPath("$.[*].clientId").value(hasItem(DEFAULT_CLIENT_ID)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.[*].debitAccount").value(hasItem(DEFAULT_DEBIT_ACCOUNT)))
            .andExpect(jsonPath("$.[*].direction").value(hasItem(DEFAULT_DIRECTION)))
            .andExpect(jsonPath("$.[*].errorDescription").value(hasItem(DEFAULT_ERROR_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].geolocation").value(hasItem(DEFAULT_GEOLOCATION)))
            .andExpect(jsonPath("$.[*].hostCode").value(hasItem(DEFAULT_HOST_CODE)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].responseCode").value(hasItem(DEFAULT_RESPONSE_CODE)))
            .andExpect(jsonPath("$.[*].responseMessage").value(hasItem(DEFAULT_RESPONSE_MESSAGE)))
            .andExpect(jsonPath("$.[*].transactionCode").value(hasItem(DEFAULT_TRANSACTION_CODE)))
            .andExpect(jsonPath("$.[*].transactionType").value(hasItem(DEFAULT_TRANSACTION_TYPE)))
            .andExpect(jsonPath("$.[*].userAgent").value(hasItem(DEFAULT_USER_AGENT)))
            .andExpect(jsonPath("$.[*].userAgentVersion").value(hasItem(DEFAULT_USER_AGENT_VERSION)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.[*].chargeamount").value(hasItem(DEFAULT_CHARGEAMOUNT)))
            .andExpect(jsonPath("$.[*].creditAccount").value(hasItem(DEFAULT_CREDIT_ACCOUNT)))
            .andExpect(jsonPath("$.[*].cbsReference").value(hasItem(DEFAULT_CBS_REFERENCE)));
    }

    @Test
    @Transactional
    void getMobileAppTransactions() throws Exception {
        // Initialize the database
        insertedMobileAppTransactions = mobileAppTransactionsRepository.saveAndFlush(mobileAppTransactions);

        // Get the mobileAppTransactions
        restMobileAppTransactionsMockMvc
            .perform(get(ENTITY_API_URL_ID, mobileAppTransactions.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mobileAppTransactions.getId().intValue()))
            .andExpect(jsonPath("$.channel").value(DEFAULT_CHANNEL))
            .andExpect(jsonPath("$.channelIp").value(DEFAULT_CHANNEL_IP))
            .andExpect(jsonPath("$.channelReference").value(DEFAULT_CHANNEL_REFERENCE))
            .andExpect(jsonPath("$.channelTimestamp").value(DEFAULT_CHANNEL_TIMESTAMP))
            .andExpect(jsonPath("$.clientId").value(DEFAULT_CLIENT_ID))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT))
            .andExpect(jsonPath("$.debitAccount").value(DEFAULT_DEBIT_ACCOUNT))
            .andExpect(jsonPath("$.direction").value(DEFAULT_DIRECTION))
            .andExpect(jsonPath("$.errorDescription").value(DEFAULT_ERROR_DESCRIPTION))
            .andExpect(jsonPath("$.geolocation").value(DEFAULT_GEOLOCATION))
            .andExpect(jsonPath("$.hostCode").value(DEFAULT_HOST_CODE))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.responseCode").value(DEFAULT_RESPONSE_CODE))
            .andExpect(jsonPath("$.responseMessage").value(DEFAULT_RESPONSE_MESSAGE))
            .andExpect(jsonPath("$.transactionCode").value(DEFAULT_TRANSACTION_CODE))
            .andExpect(jsonPath("$.transactionType").value(DEFAULT_TRANSACTION_TYPE))
            .andExpect(jsonPath("$.userAgent").value(DEFAULT_USER_AGENT))
            .andExpect(jsonPath("$.userAgentVersion").value(DEFAULT_USER_AGENT_VERSION))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT))
            .andExpect(jsonPath("$.chargeamount").value(DEFAULT_CHARGEAMOUNT))
            .andExpect(jsonPath("$.creditAccount").value(DEFAULT_CREDIT_ACCOUNT))
            .andExpect(jsonPath("$.cbsReference").value(DEFAULT_CBS_REFERENCE));
    }

    @Test
    @Transactional
    void getNonExistingMobileAppTransactions() throws Exception {
        // Get the mobileAppTransactions
        restMobileAppTransactionsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMobileAppTransactions() throws Exception {
        // Initialize the database
        insertedMobileAppTransactions = mobileAppTransactionsRepository.saveAndFlush(mobileAppTransactions);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the mobileAppTransactions
        MobileAppTransactions updatedMobileAppTransactions = mobileAppTransactionsRepository
            .findById(mobileAppTransactions.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedMobileAppTransactions are not directly saved in db
        em.detach(updatedMobileAppTransactions);
        updatedMobileAppTransactions
            .channel(UPDATED_CHANNEL)
            .channelIp(UPDATED_CHANNEL_IP)
            .channelReference(UPDATED_CHANNEL_REFERENCE)
            .channelTimestamp(UPDATED_CHANNEL_TIMESTAMP)
            .clientId(UPDATED_CLIENT_ID)
            .createdAt(UPDATED_CREATED_AT)
            .debitAccount(UPDATED_DEBIT_ACCOUNT)
            .direction(UPDATED_DIRECTION)
            .errorDescription(UPDATED_ERROR_DESCRIPTION)
            .geolocation(UPDATED_GEOLOCATION)
            .hostCode(UPDATED_HOST_CODE)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .responseCode(UPDATED_RESPONSE_CODE)
            .responseMessage(UPDATED_RESPONSE_MESSAGE)
            .transactionCode(UPDATED_TRANSACTION_CODE)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .userAgent(UPDATED_USER_AGENT)
            .userAgentVersion(UPDATED_USER_AGENT_VERSION)
            .amount(UPDATED_AMOUNT)
            .chargeamount(UPDATED_CHARGEAMOUNT)
            .creditAccount(UPDATED_CREDIT_ACCOUNT)
            .cbsReference(UPDATED_CBS_REFERENCE);

        restMobileAppTransactionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMobileAppTransactions.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedMobileAppTransactions))
            )
            .andExpect(status().isOk());

        // Validate the MobileAppTransactions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedMobileAppTransactionsToMatchAllProperties(updatedMobileAppTransactions);
    }

    @Test
    @Transactional
    void putNonExistingMobileAppTransactions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mobileAppTransactions.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMobileAppTransactionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, mobileAppTransactions.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(mobileAppTransactions))
            )
            .andExpect(status().isBadRequest());

        // Validate the MobileAppTransactions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMobileAppTransactions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mobileAppTransactions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMobileAppTransactionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(mobileAppTransactions))
            )
            .andExpect(status().isBadRequest());

        // Validate the MobileAppTransactions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMobileAppTransactions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mobileAppTransactions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMobileAppTransactionsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(mobileAppTransactions)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MobileAppTransactions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMobileAppTransactionsWithPatch() throws Exception {
        // Initialize the database
        insertedMobileAppTransactions = mobileAppTransactionsRepository.saveAndFlush(mobileAppTransactions);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the mobileAppTransactions using partial update
        MobileAppTransactions partialUpdatedMobileAppTransactions = new MobileAppTransactions();
        partialUpdatedMobileAppTransactions.setId(mobileAppTransactions.getId());

        partialUpdatedMobileAppTransactions
            .channelIp(UPDATED_CHANNEL_IP)
            .channelReference(UPDATED_CHANNEL_REFERENCE)
            .debitAccount(UPDATED_DEBIT_ACCOUNT)
            .responseCode(UPDATED_RESPONSE_CODE)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .amount(UPDATED_AMOUNT)
            .creditAccount(UPDATED_CREDIT_ACCOUNT)
            .cbsReference(UPDATED_CBS_REFERENCE);

        restMobileAppTransactionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMobileAppTransactions.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMobileAppTransactions))
            )
            .andExpect(status().isOk());

        // Validate the MobileAppTransactions in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMobileAppTransactionsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedMobileAppTransactions, mobileAppTransactions),
            getPersistedMobileAppTransactions(mobileAppTransactions)
        );
    }

    @Test
    @Transactional
    void fullUpdateMobileAppTransactionsWithPatch() throws Exception {
        // Initialize the database
        insertedMobileAppTransactions = mobileAppTransactionsRepository.saveAndFlush(mobileAppTransactions);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the mobileAppTransactions using partial update
        MobileAppTransactions partialUpdatedMobileAppTransactions = new MobileAppTransactions();
        partialUpdatedMobileAppTransactions.setId(mobileAppTransactions.getId());

        partialUpdatedMobileAppTransactions
            .channel(UPDATED_CHANNEL)
            .channelIp(UPDATED_CHANNEL_IP)
            .channelReference(UPDATED_CHANNEL_REFERENCE)
            .channelTimestamp(UPDATED_CHANNEL_TIMESTAMP)
            .clientId(UPDATED_CLIENT_ID)
            .createdAt(UPDATED_CREATED_AT)
            .debitAccount(UPDATED_DEBIT_ACCOUNT)
            .direction(UPDATED_DIRECTION)
            .errorDescription(UPDATED_ERROR_DESCRIPTION)
            .geolocation(UPDATED_GEOLOCATION)
            .hostCode(UPDATED_HOST_CODE)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .responseCode(UPDATED_RESPONSE_CODE)
            .responseMessage(UPDATED_RESPONSE_MESSAGE)
            .transactionCode(UPDATED_TRANSACTION_CODE)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .userAgent(UPDATED_USER_AGENT)
            .userAgentVersion(UPDATED_USER_AGENT_VERSION)
            .amount(UPDATED_AMOUNT)
            .chargeamount(UPDATED_CHARGEAMOUNT)
            .creditAccount(UPDATED_CREDIT_ACCOUNT)
            .cbsReference(UPDATED_CBS_REFERENCE);

        restMobileAppTransactionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMobileAppTransactions.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMobileAppTransactions))
            )
            .andExpect(status().isOk());

        // Validate the MobileAppTransactions in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMobileAppTransactionsUpdatableFieldsEquals(
            partialUpdatedMobileAppTransactions,
            getPersistedMobileAppTransactions(partialUpdatedMobileAppTransactions)
        );
    }

    @Test
    @Transactional
    void patchNonExistingMobileAppTransactions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mobileAppTransactions.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMobileAppTransactionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, mobileAppTransactions.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(mobileAppTransactions))
            )
            .andExpect(status().isBadRequest());

        // Validate the MobileAppTransactions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMobileAppTransactions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mobileAppTransactions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMobileAppTransactionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(mobileAppTransactions))
            )
            .andExpect(status().isBadRequest());

        // Validate the MobileAppTransactions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMobileAppTransactions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        mobileAppTransactions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMobileAppTransactionsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(mobileAppTransactions)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MobileAppTransactions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMobileAppTransactions() throws Exception {
        // Initialize the database
        insertedMobileAppTransactions = mobileAppTransactionsRepository.saveAndFlush(mobileAppTransactions);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the mobileAppTransactions
        restMobileAppTransactionsMockMvc
            .perform(delete(ENTITY_API_URL_ID, mobileAppTransactions.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return mobileAppTransactionsRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected MobileAppTransactions getPersistedMobileAppTransactions(MobileAppTransactions mobileAppTransactions) {
        return mobileAppTransactionsRepository.findById(mobileAppTransactions.getId()).orElseThrow();
    }

    protected void assertPersistedMobileAppTransactionsToMatchAllProperties(MobileAppTransactions expectedMobileAppTransactions) {
        assertMobileAppTransactionsAllPropertiesEquals(
            expectedMobileAppTransactions,
            getPersistedMobileAppTransactions(expectedMobileAppTransactions)
        );
    }

    protected void assertPersistedMobileAppTransactionsToMatchUpdatableProperties(MobileAppTransactions expectedMobileAppTransactions) {
        assertMobileAppTransactionsAllUpdatablePropertiesEquals(
            expectedMobileAppTransactions,
            getPersistedMobileAppTransactions(expectedMobileAppTransactions)
        );
    }
}
