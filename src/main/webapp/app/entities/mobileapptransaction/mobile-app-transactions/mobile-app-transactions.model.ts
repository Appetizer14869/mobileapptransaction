export interface IMobileAppTransactions {
  id: number;
  channel?: string | null;
  channelIp?: string | null;
  channelReference?: string | null;
  channelTimestamp?: string | null;
  clientId?: string | null;
  createdAt?: string | null;
  debitAccount?: string | null;
  direction?: string | null;
  errorDescription?: string | null;
  geolocation?: string | null;
  hostCode?: string | null;
  phoneNumber?: string | null;
  responseCode?: string | null;
  responseMessage?: string | null;
  transactionCode?: string | null;
  transactionType?: string | null;
  userAgent?: string | null;
  userAgentVersion?: string | null;
  amount?: string | null;
  chargeamount?: string | null;
  creditAccount?: string | null;
  cbsReference?: string | null;
}

export type NewMobileAppTransactions = Omit<IMobileAppTransactions, 'id'> & { id: null };
