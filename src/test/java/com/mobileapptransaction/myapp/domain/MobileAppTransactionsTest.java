package com.mobileapptransaction.myapp.domain;

import static com.mobileapptransaction.myapp.domain.MobileAppTransactionsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mobileapptransaction.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MobileAppTransactionsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MobileAppTransactions.class);
        MobileAppTransactions mobileAppTransactions1 = getMobileAppTransactionsSample1();
        MobileAppTransactions mobileAppTransactions2 = new MobileAppTransactions();
        assertThat(mobileAppTransactions1).isNotEqualTo(mobileAppTransactions2);

        mobileAppTransactions2.setId(mobileAppTransactions1.getId());
        assertThat(mobileAppTransactions1).isEqualTo(mobileAppTransactions2);

        mobileAppTransactions2 = getMobileAppTransactionsSample2();
        assertThat(mobileAppTransactions1).isNotEqualTo(mobileAppTransactions2);
    }
}
