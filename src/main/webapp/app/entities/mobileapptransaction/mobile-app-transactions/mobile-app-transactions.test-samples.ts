import { IMobileAppTransactions, NewMobileAppTransactions } from './mobile-app-transactions.model';

export const sampleWithRequiredData: IMobileAppTransactions = {
  id: 23011,
};

export const sampleWithPartialData: IMobileAppTransactions = {
  id: 12160,
  channelTimestamp: 'if',
  clientId: 'for instantly',
  createdAt: 'boohoo knotty',
  debitAccount: 'woot ah',
  errorDescription: 'only',
  geolocation: 'geez ha sonar',
  responseMessage: 'bungalow',
  transactionCode: 'yippee busily',
  userAgent: 'yahoo self-assured',
  userAgentVersion: 'sophisticated oof',
  amount: 'challenge dramatic handle',
  chargeamount: 'brightly through frantically',
  cbsReference: 'where feline tromp',
};

export const sampleWithFullData: IMobileAppTransactions = {
  id: 6106,
  channel: 'with minus overproduce',
  channelIp: 'considering',
  channelReference: 'yippee whoa dress',
  channelTimestamp: 'self-confidence pro out',
  clientId: 'stark marketplace',
  createdAt: 'near rejigger near',
  debitAccount: 'management',
  direction: 'boldly er',
  errorDescription: 'boss alongside ick',
  geolocation: 'explode',
  hostCode: 'until acidly',
  phoneNumber: 'geez sugary',
  responseCode: 'boo extent',
  responseMessage: 'acidic',
  transactionCode: 'for',
  transactionType: 'because sideboard',
  userAgent: 'because aw',
  userAgentVersion: 'above',
  amount: 'shudder beyond pip',
  chargeamount: 'hm',
  creditAccount: 'glum sweetly',
  cbsReference: 'towards',
};

export const sampleWithNewData: NewMobileAppTransactions = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
