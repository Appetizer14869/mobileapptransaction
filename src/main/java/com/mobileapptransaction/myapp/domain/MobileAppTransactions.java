package com.mobileapptransaction.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A MobileAppTransactions.
 */
@Entity
@Table(name = "mobile_app_transactions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MobileAppTransactions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "channel")
    private String channel;

    @Column(name = "channel_ip")
    private String channelIp;

    @Column(name = "channel_reference")
    private String channelReference;

    @Column(name = "channel_timestamp")
    private String channelTimestamp;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "debit_account")
    private String debitAccount;

    @Column(name = "direction")
    private String direction;

    @Column(name = "error_description")
    private String errorDescription;

    @Column(name = "geolocation")
    private String geolocation;

    @Column(name = "host_code")
    private String hostCode;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "response_code")
    private String responseCode;

    @Column(name = "response_message")
    private String responseMessage;

    @Column(name = "transaction_code")
    private String transactionCode;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "user_agent_version")
    private String userAgentVersion;

    @Column(name = "amount")
    private String amount;

    @Column(name = "chargeamount")
    private String chargeamount;

    @Column(name = "credit_account")
    private String creditAccount;

    @Column(name = "cbs_reference")
    private String cbsReference;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public MobileAppTransactions id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannel() {
        return this.channel;
    }

    public MobileAppTransactions channel(String channel) {
        this.setChannel(channel);
        return this;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannelIp() {
        return this.channelIp;
    }

    public MobileAppTransactions channelIp(String channelIp) {
        this.setChannelIp(channelIp);
        return this;
    }

    public void setChannelIp(String channelIp) {
        this.channelIp = channelIp;
    }

    public String getChannelReference() {
        return this.channelReference;
    }

    public MobileAppTransactions channelReference(String channelReference) {
        this.setChannelReference(channelReference);
        return this;
    }

    public void setChannelReference(String channelReference) {
        this.channelReference = channelReference;
    }

    public String getChannelTimestamp() {
        return this.channelTimestamp;
    }

    public MobileAppTransactions channelTimestamp(String channelTimestamp) {
        this.setChannelTimestamp(channelTimestamp);
        return this;
    }

    public void setChannelTimestamp(String channelTimestamp) {
        this.channelTimestamp = channelTimestamp;
    }

    public String getClientId() {
        return this.clientId;
    }

    public MobileAppTransactions clientId(String clientId) {
        this.setClientId(clientId);
        return this;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public MobileAppTransactions createdAt(String createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDebitAccount() {
        return this.debitAccount;
    }

    public MobileAppTransactions debitAccount(String debitAccount) {
        this.setDebitAccount(debitAccount);
        return this;
    }

    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    public String getDirection() {
        return this.direction;
    }

    public MobileAppTransactions direction(String direction) {
        this.setDirection(direction);
        return this;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

    public MobileAppTransactions errorDescription(String errorDescription) {
        this.setErrorDescription(errorDescription);
        return this;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getGeolocation() {
        return this.geolocation;
    }

    public MobileAppTransactions geolocation(String geolocation) {
        this.setGeolocation(geolocation);
        return this;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

    public String getHostCode() {
        return this.hostCode;
    }

    public MobileAppTransactions hostCode(String hostCode) {
        this.setHostCode(hostCode);
        return this;
    }

    public void setHostCode(String hostCode) {
        this.hostCode = hostCode;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public MobileAppTransactions phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public MobileAppTransactions responseCode(String responseCode) {
        this.setResponseCode(responseCode);
        return this;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public MobileAppTransactions responseMessage(String responseMessage) {
        this.setResponseMessage(responseMessage);
        return this;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getTransactionCode() {
        return this.transactionCode;
    }

    public MobileAppTransactions transactionCode(String transactionCode) {
        this.setTransactionCode(transactionCode);
        return this;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getTransactionType() {
        return this.transactionType;
    }

    public MobileAppTransactions transactionType(String transactionType) {
        this.setTransactionType(transactionType);
        return this;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public MobileAppTransactions userAgent(String userAgent) {
        this.setUserAgent(userAgent);
        return this;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUserAgentVersion() {
        return this.userAgentVersion;
    }

    public MobileAppTransactions userAgentVersion(String userAgentVersion) {
        this.setUserAgentVersion(userAgentVersion);
        return this;
    }

    public void setUserAgentVersion(String userAgentVersion) {
        this.userAgentVersion = userAgentVersion;
    }

    public String getAmount() {
        return this.amount;
    }

    public MobileAppTransactions amount(String amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getChargeamount() {
        return this.chargeamount;
    }

    public MobileAppTransactions chargeamount(String chargeamount) {
        this.setChargeamount(chargeamount);
        return this;
    }

    public void setChargeamount(String chargeamount) {
        this.chargeamount = chargeamount;
    }

    public String getCreditAccount() {
        return this.creditAccount;
    }

    public MobileAppTransactions creditAccount(String creditAccount) {
        this.setCreditAccount(creditAccount);
        return this;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public String getCbsReference() {
        return this.cbsReference;
    }

    public MobileAppTransactions cbsReference(String cbsReference) {
        this.setCbsReference(cbsReference);
        return this;
    }

    public void setCbsReference(String cbsReference) {
        this.cbsReference = cbsReference;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MobileAppTransactions)) {
            return false;
        }
        return getId() != null && getId().equals(((MobileAppTransactions) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MobileAppTransactions{" +
            "id=" + getId() +
            ", channel='" + getChannel() + "'" +
            ", channelIp='" + getChannelIp() + "'" +
            ", channelReference='" + getChannelReference() + "'" +
            ", channelTimestamp='" + getChannelTimestamp() + "'" +
            ", clientId='" + getClientId() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", debitAccount='" + getDebitAccount() + "'" +
            ", direction='" + getDirection() + "'" +
            ", errorDescription='" + getErrorDescription() + "'" +
            ", geolocation='" + getGeolocation() + "'" +
            ", hostCode='" + getHostCode() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", responseCode='" + getResponseCode() + "'" +
            ", responseMessage='" + getResponseMessage() + "'" +
            ", transactionCode='" + getTransactionCode() + "'" +
            ", transactionType='" + getTransactionType() + "'" +
            ", userAgent='" + getUserAgent() + "'" +
            ", userAgentVersion='" + getUserAgentVersion() + "'" +
            ", amount='" + getAmount() + "'" +
            ", chargeamount='" + getChargeamount() + "'" +
            ", creditAccount='" + getCreditAccount() + "'" +
            ", cbsReference='" + getCbsReference() + "'" +
            "}";
    }
}
