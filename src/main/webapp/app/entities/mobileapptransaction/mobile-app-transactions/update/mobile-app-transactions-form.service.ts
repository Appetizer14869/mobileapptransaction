import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IMobileAppTransactions, NewMobileAppTransactions } from '../mobile-app-transactions.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IMobileAppTransactions for edit and NewMobileAppTransactionsFormGroupInput for create.
 */
type MobileAppTransactionsFormGroupInput = IMobileAppTransactions | PartialWithRequiredKeyOf<NewMobileAppTransactions>;

type MobileAppTransactionsFormDefaults = Pick<NewMobileAppTransactions, 'id'>;

type MobileAppTransactionsFormGroupContent = {
  id: FormControl<IMobileAppTransactions['id'] | NewMobileAppTransactions['id']>;
  channel: FormControl<IMobileAppTransactions['channel']>;
  channelIp: FormControl<IMobileAppTransactions['channelIp']>;
  channelReference: FormControl<IMobileAppTransactions['channelReference']>;
  channelTimestamp: FormControl<IMobileAppTransactions['channelTimestamp']>;
  clientId: FormControl<IMobileAppTransactions['clientId']>;
  createdAt: FormControl<IMobileAppTransactions['createdAt']>;
  debitAccount: FormControl<IMobileAppTransactions['debitAccount']>;
  direction: FormControl<IMobileAppTransactions['direction']>;
  errorDescription: FormControl<IMobileAppTransactions['errorDescription']>;
  geolocation: FormControl<IMobileAppTransactions['geolocation']>;
  hostCode: FormControl<IMobileAppTransactions['hostCode']>;
  phoneNumber: FormControl<IMobileAppTransactions['phoneNumber']>;
  responseCode: FormControl<IMobileAppTransactions['responseCode']>;
  responseMessage: FormControl<IMobileAppTransactions['responseMessage']>;
  transactionCode: FormControl<IMobileAppTransactions['transactionCode']>;
  transactionType: FormControl<IMobileAppTransactions['transactionType']>;
  userAgent: FormControl<IMobileAppTransactions['userAgent']>;
  userAgentVersion: FormControl<IMobileAppTransactions['userAgentVersion']>;
  amount: FormControl<IMobileAppTransactions['amount']>;
  chargeamount: FormControl<IMobileAppTransactions['chargeamount']>;
  creditAccount: FormControl<IMobileAppTransactions['creditAccount']>;
  cbsReference: FormControl<IMobileAppTransactions['cbsReference']>;
};

export type MobileAppTransactionsFormGroup = FormGroup<MobileAppTransactionsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class MobileAppTransactionsFormService {
  createMobileAppTransactionsFormGroup(
    mobileAppTransactions: MobileAppTransactionsFormGroupInput = { id: null },
  ): MobileAppTransactionsFormGroup {
    const mobileAppTransactionsRawValue = {
      ...this.getFormDefaults(),
      ...mobileAppTransactions,
    };
    return new FormGroup<MobileAppTransactionsFormGroupContent>({
      id: new FormControl(
        { value: mobileAppTransactionsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      channel: new FormControl(mobileAppTransactionsRawValue.channel),
      channelIp: new FormControl(mobileAppTransactionsRawValue.channelIp),
      channelReference: new FormControl(mobileAppTransactionsRawValue.channelReference),
      channelTimestamp: new FormControl(mobileAppTransactionsRawValue.channelTimestamp),
      clientId: new FormControl(mobileAppTransactionsRawValue.clientId),
      createdAt: new FormControl(mobileAppTransactionsRawValue.createdAt),
      debitAccount: new FormControl(mobileAppTransactionsRawValue.debitAccount),
      direction: new FormControl(mobileAppTransactionsRawValue.direction),
      errorDescription: new FormControl(mobileAppTransactionsRawValue.errorDescription),
      geolocation: new FormControl(mobileAppTransactionsRawValue.geolocation),
      hostCode: new FormControl(mobileAppTransactionsRawValue.hostCode),
      phoneNumber: new FormControl(mobileAppTransactionsRawValue.phoneNumber),
      responseCode: new FormControl(mobileAppTransactionsRawValue.responseCode),
      responseMessage: new FormControl(mobileAppTransactionsRawValue.responseMessage),
      transactionCode: new FormControl(mobileAppTransactionsRawValue.transactionCode),
      transactionType: new FormControl(mobileAppTransactionsRawValue.transactionType),
      userAgent: new FormControl(mobileAppTransactionsRawValue.userAgent),
      userAgentVersion: new FormControl(mobileAppTransactionsRawValue.userAgentVersion),
      amount: new FormControl(mobileAppTransactionsRawValue.amount),
      chargeamount: new FormControl(mobileAppTransactionsRawValue.chargeamount),
      creditAccount: new FormControl(mobileAppTransactionsRawValue.creditAccount),
      cbsReference: new FormControl(mobileAppTransactionsRawValue.cbsReference),
    });
  }

  getMobileAppTransactions(form: MobileAppTransactionsFormGroup): IMobileAppTransactions | NewMobileAppTransactions {
    return form.getRawValue() as IMobileAppTransactions | NewMobileAppTransactions;
  }

  resetForm(form: MobileAppTransactionsFormGroup, mobileAppTransactions: MobileAppTransactionsFormGroupInput): void {
    const mobileAppTransactionsRawValue = { ...this.getFormDefaults(), ...mobileAppTransactions };
    form.reset(
      {
        ...mobileAppTransactionsRawValue,
        id: { value: mobileAppTransactionsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): MobileAppTransactionsFormDefaults {
    return {
      id: null,
    };
  }
}
