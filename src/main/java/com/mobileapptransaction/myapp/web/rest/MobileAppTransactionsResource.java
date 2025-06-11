package com.mobileapptransaction.myapp.web.rest;

import com.mobileapptransaction.myapp.domain.MobileAppTransactions;
import com.mobileapptransaction.myapp.repository.MobileAppTransactionsRepository;
import com.mobileapptransaction.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mobileapptransaction.myapp.domain.MobileAppTransactions}.
 */
@RestController
@RequestMapping("/api/mobile-app-transactions")
@Transactional
public class MobileAppTransactionsResource {

    private static final Logger LOG = LoggerFactory.getLogger(MobileAppTransactionsResource.class);

    private static final String ENTITY_NAME = "mobileapptransactionMobileAppTransactions";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MobileAppTransactionsRepository mobileAppTransactionsRepository;

    public MobileAppTransactionsResource(MobileAppTransactionsRepository mobileAppTransactionsRepository) {
        this.mobileAppTransactionsRepository = mobileAppTransactionsRepository;
    }

    /**
     * {@code POST  /mobile-app-transactions} : Create a new mobileAppTransactions.
     *
     * @param mobileAppTransactions the mobileAppTransactions to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mobileAppTransactions, or with status {@code 400 (Bad Request)} if the mobileAppTransactions has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<MobileAppTransactions> createMobileAppTransactions(@RequestBody MobileAppTransactions mobileAppTransactions)
        throws URISyntaxException {
        LOG.debug("REST request to save MobileAppTransactions : {}", mobileAppTransactions);
        if (mobileAppTransactions.getId() != null) {
            throw new BadRequestAlertException("A new mobileAppTransactions cannot already have an ID", ENTITY_NAME, "idexists");
        }
        mobileAppTransactions = mobileAppTransactionsRepository.save(mobileAppTransactions);
        return ResponseEntity.created(new URI("/api/mobile-app-transactions/" + mobileAppTransactions.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, mobileAppTransactions.getId().toString()))
            .body(mobileAppTransactions);
    }

    /**
     * {@code PUT  /mobile-app-transactions/:id} : Updates an existing mobileAppTransactions.
     *
     * @param id the id of the mobileAppTransactions to save.
     * @param mobileAppTransactions the mobileAppTransactions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mobileAppTransactions,
     * or with status {@code 400 (Bad Request)} if the mobileAppTransactions is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mobileAppTransactions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MobileAppTransactions> updateMobileAppTransactions(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MobileAppTransactions mobileAppTransactions
    ) throws URISyntaxException {
        LOG.debug("REST request to update MobileAppTransactions : {}, {}", id, mobileAppTransactions);
        if (mobileAppTransactions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mobileAppTransactions.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mobileAppTransactionsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        mobileAppTransactions = mobileAppTransactionsRepository.save(mobileAppTransactions);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mobileAppTransactions.getId().toString()))
            .body(mobileAppTransactions);
    }

    /**
     * {@code PATCH  /mobile-app-transactions/:id} : Partial updates given fields of an existing mobileAppTransactions, field will ignore if it is null
     *
     * @param id the id of the mobileAppTransactions to save.
     * @param mobileAppTransactions the mobileAppTransactions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mobileAppTransactions,
     * or with status {@code 400 (Bad Request)} if the mobileAppTransactions is not valid,
     * or with status {@code 404 (Not Found)} if the mobileAppTransactions is not found,
     * or with status {@code 500 (Internal Server Error)} if the mobileAppTransactions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MobileAppTransactions> partialUpdateMobileAppTransactions(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MobileAppTransactions mobileAppTransactions
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update MobileAppTransactions partially : {}, {}", id, mobileAppTransactions);
        if (mobileAppTransactions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mobileAppTransactions.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mobileAppTransactionsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MobileAppTransactions> result = mobileAppTransactionsRepository
            .findById(mobileAppTransactions.getId())
            .map(existingMobileAppTransactions -> {
                if (mobileAppTransactions.getChannel() != null) {
                    existingMobileAppTransactions.setChannel(mobileAppTransactions.getChannel());
                }
                if (mobileAppTransactions.getChannelIp() != null) {
                    existingMobileAppTransactions.setChannelIp(mobileAppTransactions.getChannelIp());
                }
                if (mobileAppTransactions.getChannelReference() != null) {
                    existingMobileAppTransactions.setChannelReference(mobileAppTransactions.getChannelReference());
                }
                if (mobileAppTransactions.getChannelTimestamp() != null) {
                    existingMobileAppTransactions.setChannelTimestamp(mobileAppTransactions.getChannelTimestamp());
                }
                if (mobileAppTransactions.getClientId() != null) {
                    existingMobileAppTransactions.setClientId(mobileAppTransactions.getClientId());
                }
                if (mobileAppTransactions.getCreatedAt() != null) {
                    existingMobileAppTransactions.setCreatedAt(mobileAppTransactions.getCreatedAt());
                }
                if (mobileAppTransactions.getDebitAccount() != null) {
                    existingMobileAppTransactions.setDebitAccount(mobileAppTransactions.getDebitAccount());
                }
                if (mobileAppTransactions.getDirection() != null) {
                    existingMobileAppTransactions.setDirection(mobileAppTransactions.getDirection());
                }
                if (mobileAppTransactions.getErrorDescription() != null) {
                    existingMobileAppTransactions.setErrorDescription(mobileAppTransactions.getErrorDescription());
                }
                if (mobileAppTransactions.getGeolocation() != null) {
                    existingMobileAppTransactions.setGeolocation(mobileAppTransactions.getGeolocation());
                }
                if (mobileAppTransactions.getHostCode() != null) {
                    existingMobileAppTransactions.setHostCode(mobileAppTransactions.getHostCode());
                }
                if (mobileAppTransactions.getPhoneNumber() != null) {
                    existingMobileAppTransactions.setPhoneNumber(mobileAppTransactions.getPhoneNumber());
                }
                if (mobileAppTransactions.getResponseCode() != null) {
                    existingMobileAppTransactions.setResponseCode(mobileAppTransactions.getResponseCode());
                }
                if (mobileAppTransactions.getResponseMessage() != null) {
                    existingMobileAppTransactions.setResponseMessage(mobileAppTransactions.getResponseMessage());
                }
                if (mobileAppTransactions.getTransactionCode() != null) {
                    existingMobileAppTransactions.setTransactionCode(mobileAppTransactions.getTransactionCode());
                }
                if (mobileAppTransactions.getTransactionType() != null) {
                    existingMobileAppTransactions.setTransactionType(mobileAppTransactions.getTransactionType());
                }
                if (mobileAppTransactions.getUserAgent() != null) {
                    existingMobileAppTransactions.setUserAgent(mobileAppTransactions.getUserAgent());
                }
                if (mobileAppTransactions.getUserAgentVersion() != null) {
                    existingMobileAppTransactions.setUserAgentVersion(mobileAppTransactions.getUserAgentVersion());
                }
                if (mobileAppTransactions.getAmount() != null) {
                    existingMobileAppTransactions.setAmount(mobileAppTransactions.getAmount());
                }
                if (mobileAppTransactions.getChargeamount() != null) {
                    existingMobileAppTransactions.setChargeamount(mobileAppTransactions.getChargeamount());
                }
                if (mobileAppTransactions.getCreditAccount() != null) {
                    existingMobileAppTransactions.setCreditAccount(mobileAppTransactions.getCreditAccount());
                }
                if (mobileAppTransactions.getCbsReference() != null) {
                    existingMobileAppTransactions.setCbsReference(mobileAppTransactions.getCbsReference());
                }

                return existingMobileAppTransactions;
            })
            .map(mobileAppTransactionsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mobileAppTransactions.getId().toString())
        );
    }

    /**
     * {@code GET  /mobile-app-transactions} : get all the mobileAppTransactions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mobileAppTransactions in body.
     */
    @GetMapping("")
    public ResponseEntity<List<MobileAppTransactions>> getAllMobileAppTransactions(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of MobileAppTransactions");
        Page<MobileAppTransactions> page = mobileAppTransactionsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /mobile-app-transactions/:id} : get the "id" mobileAppTransactions.
     *
     * @param id the id of the mobileAppTransactions to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mobileAppTransactions, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MobileAppTransactions> getMobileAppTransactions(@PathVariable("id") Long id) {
        LOG.debug("REST request to get MobileAppTransactions : {}", id);
        Optional<MobileAppTransactions> mobileAppTransactions = mobileAppTransactionsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mobileAppTransactions);
    }

    /**
     * {@code DELETE  /mobile-app-transactions/:id} : delete the "id" mobileAppTransactions.
     *
     * @param id the id of the mobileAppTransactions to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMobileAppTransactions(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete MobileAppTransactions : {}", id);
        mobileAppTransactionsRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
