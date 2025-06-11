package com.mobileapptransaction.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class MobileAppTransactionsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static MobileAppTransactions getMobileAppTransactionsSample1() {
        return new MobileAppTransactions()
            .id(1L)
            .channel("channel1")
            .channelIp("channelIp1")
            .channelReference("channelReference1")
            .channelTimestamp("channelTimestamp1")
            .clientId("clientId1")
            .createdAt("createdAt1")
            .debitAccount("debitAccount1")
            .direction("direction1")
            .errorDescription("errorDescription1")
            .geolocation("geolocation1")
            .hostCode("hostCode1")
            .phoneNumber("phoneNumber1")
            .responseCode("responseCode1")
            .responseMessage("responseMessage1")
            .transactionCode("transactionCode1")
            .transactionType("transactionType1")
            .userAgent("userAgent1")
            .userAgentVersion("userAgentVersion1")
            .amount("amount1")
            .chargeamount("chargeamount1")
            .creditAccount("creditAccount1")
            .cbsReference("cbsReference1");
    }

    public static MobileAppTransactions getMobileAppTransactionsSample2() {
        return new MobileAppTransactions()
            .id(2L)
            .channel("channel2")
            .channelIp("channelIp2")
            .channelReference("channelReference2")
            .channelTimestamp("channelTimestamp2")
            .clientId("clientId2")
            .createdAt("createdAt2")
            .debitAccount("debitAccount2")
            .direction("direction2")
            .errorDescription("errorDescription2")
            .geolocation("geolocation2")
            .hostCode("hostCode2")
            .phoneNumber("phoneNumber2")
            .responseCode("responseCode2")
            .responseMessage("responseMessage2")
            .transactionCode("transactionCode2")
            .transactionType("transactionType2")
            .userAgent("userAgent2")
            .userAgentVersion("userAgentVersion2")
            .amount("amount2")
            .chargeamount("chargeamount2")
            .creditAccount("creditAccount2")
            .cbsReference("cbsReference2");
    }

    public static MobileAppTransactions getMobileAppTransactionsRandomSampleGenerator() {
        return new MobileAppTransactions()
            .id(longCount.incrementAndGet())
            .channel(UUID.randomUUID().toString())
            .channelIp(UUID.randomUUID().toString())
            .channelReference(UUID.randomUUID().toString())
            .channelTimestamp(UUID.randomUUID().toString())
            .clientId(UUID.randomUUID().toString())
            .createdAt(UUID.randomUUID().toString())
            .debitAccount(UUID.randomUUID().toString())
            .direction(UUID.randomUUID().toString())
            .errorDescription(UUID.randomUUID().toString())
            .geolocation(UUID.randomUUID().toString())
            .hostCode(UUID.randomUUID().toString())
            .phoneNumber(UUID.randomUUID().toString())
            .responseCode(UUID.randomUUID().toString())
            .responseMessage(UUID.randomUUID().toString())
            .transactionCode(UUID.randomUUID().toString())
            .transactionType(UUID.randomUUID().toString())
            .userAgent(UUID.randomUUID().toString())
            .userAgentVersion(UUID.randomUUID().toString())
            .amount(UUID.randomUUID().toString())
            .chargeamount(UUID.randomUUID().toString())
            .creditAccount(UUID.randomUUID().toString())
            .cbsReference(UUID.randomUUID().toString());
    }
}
