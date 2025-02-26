package com.tutor.auth0r.web.rest;

import com.tutor.auth0r.domain.WalletTransaction;
import com.tutor.auth0r.domain.enumeration.WalletTransactionStatus;
import com.tutor.auth0r.repository.WalletTransactionRepository;
import com.tutor.auth0r.service.WalletTransactionService;
import com.tutor.auth0r.service.dto.CustomDTO.MonthlyRevenueDTO;
import com.tutor.auth0r.service.dto.CustomDTO.WithDrawLISTDTO;
import com.tutor.auth0r.service.dto.WalletTransactionDTO;
import com.tutor.auth0r.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing
 * {@link com.tutor.auth0r.domain.WalletTransaction}.
 */
@RestController
@RequestMapping("/api/wallet-transactions")
public class WalletTransactionResource {

    private static final Logger log = LoggerFactory.getLogger(WalletTransactionResource.class);

    private static final String ENTITY_NAME = "walletTransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WalletTransactionService walletTransactionService;

    private final WalletTransactionRepository walletTransactionRepository;

    public WalletTransactionResource(
        WalletTransactionService walletTransactionService,
        WalletTransactionRepository walletTransactionRepository
    ) {
        this.walletTransactionService = walletTransactionService;
        this.walletTransactionRepository = walletTransactionRepository;
    }

    /**
     * {@code POST  /wallet-transactions} : Create a new walletTransaction.
     *
     * @param walletTransactionDTO the walletTransactionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new walletTransactionDTO, or with status
     *         {@code 400 (Bad Request)} if the walletTransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<WalletTransactionDTO> createWalletTransaction(@RequestBody WalletTransactionDTO walletTransactionDTO)
        throws URISyntaxException {
        log.debug("REST request to save WalletTransaction : {}", walletTransactionDTO);
        if (walletTransactionDTO.getId() != null) {
            throw new BadRequestAlertException("A new walletTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        walletTransactionDTO = walletTransactionService.save(walletTransactionDTO);
        return ResponseEntity.created(new URI("/api/wallet-transactions/" + walletTransactionDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, walletTransactionDTO.getId().toString()))
            .body(walletTransactionDTO);
    }

    /**
     * {@code PUT  /wallet-transactions/:id} : Updates an existing
     * walletTransaction.
     *
     * @param id                   the id of the walletTransactionDTO to save.
     * @param walletTransactionDTO the walletTransactionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated walletTransactionDTO,
     *         or with status {@code 400 (Bad Request)} if the walletTransactionDTO
     *         is not valid,
     *         or with status {@code 500 (Internal Server Error)} if the
     *         walletTransactionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<WalletTransactionDTO> updateWalletTransaction(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WalletTransactionDTO walletTransactionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WalletTransaction : {}, {}", id, walletTransactionDTO);
        if (walletTransactionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, walletTransactionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!walletTransactionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        walletTransactionDTO = walletTransactionService.update(walletTransactionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, walletTransactionDTO.getId().toString()))
            .body(walletTransactionDTO);
    }

    /**
     * {@code PATCH  /wallet-transactions/:id} : Partial updates given fields of an
     * existing walletTransaction, field will ignore if it is null
     *
     * @param id                   the id of the walletTransactionDTO to save.
     * @param walletTransactionDTO the walletTransactionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated walletTransactionDTO,
     *         or with status {@code 400 (Bad Request)} if the walletTransactionDTO
     *         is not valid,
     *         or with status {@code 404 (Not Found)} if the walletTransactionDTO is
     *         not found,
     *         or with status {@code 500 (Internal Server Error)} if the
     *         walletTransactionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<WalletTransactionDTO> partialUpdateWalletTransaction(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WalletTransactionDTO walletTransactionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update WalletTransaction partially : {}, {}", id, walletTransactionDTO);
        if (walletTransactionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, walletTransactionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!walletTransactionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WalletTransactionDTO> result = walletTransactionService.partialUpdate(walletTransactionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, walletTransactionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /wallet-transactions} : get all the walletTransactions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of walletTransactions in body.
     */
    @GetMapping("")
    public List<WalletTransactionDTO> getAllWalletTransactions() {
        log.debug("REST request to get all WalletTransactions");
        return walletTransactionService.findAll();
    }

    /**
     * {@code GET  /wallet-transactions/:id} : get the "id" walletTransaction.
     *
     * @param id the id of the walletTransactionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the walletTransactionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<WalletTransactionDTO> getWalletTransaction(@PathVariable("id") Long id) {
        log.debug("REST request to get WalletTransaction : {}", id);
        Optional<WalletTransactionDTO> walletTransactionDTO = walletTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(walletTransactionDTO);
    }

    /**
     * {@code DELETE  /wallet-transactions/:id} : delete the "id" walletTransaction.
     *
     * @param id the id of the walletTransactionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWalletTransaction(@PathVariable("id") Long id) {
        log.debug("REST request to delete WalletTransaction : {}", id);
        walletTransactionService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/financials/admin-monthly-revenue")
    public ResponseEntity<MonthlyRevenueDTO> getAdminMonthlyRevenue(@RequestParam int year, @RequestParam int month) {
        MonthlyRevenueDTO monthlyRevenueDTO = walletTransactionService.calculateMonthlyRevenueForAdmin(year, month);
        if (monthlyRevenueDTO != null) {
            return ResponseEntity.ok().body(monthlyRevenueDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/withdrawals")
    public ResponseEntity<List<WalletTransaction>> getWithdrawals() {
        List<WalletTransaction> withdrawals = walletTransactionService.getWithdrawals();
        return ResponseEntity.ok(withdrawals);
    }

    @GetMapping("/wallet-transactions/withdrawal-details")
    public ResponseEntity<List<WithDrawLISTDTO>> getAllWithdrawalDetails() {
        List<WithDrawLISTDTO> withdrawalDetails = walletTransactionService.getAllWithdrawalDetails();
        return ResponseEntity.ok(withdrawalDetails);
    }

    @PostMapping("/wallet-transactions/{id}")
    public ResponseEntity<WithDrawLISTDTO> updateTransactionStatus(@PathVariable Long id) {
        WithDrawLISTDTO updatedTransaction = walletTransactionService.updateTransactionStatus(id);
        return ResponseEntity.ok(updatedTransaction);
    }

    @PostMapping("/wallet-transactions/{id}/reject")
    public ResponseEntity<WithDrawLISTDTO> rejectTransaction(@PathVariable Long id) {
        WithDrawLISTDTO rejectedTransaction = walletTransactionService.rejectTransaction(id);
        return ResponseEntity.ok(rejectedTransaction);
    }
}
