package com.mobileapptransaction.myapp.repository;

import com.mobileapptransaction.myapp.domain.MobileAppTransactions;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MobileAppTransactions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MobileAppTransactionsRepository extends JpaRepository<MobileAppTransactions, Long> {}
