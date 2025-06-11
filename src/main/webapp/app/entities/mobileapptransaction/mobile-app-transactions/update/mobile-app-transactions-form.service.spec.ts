import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../mobile-app-transactions.test-samples';

import { MobileAppTransactionsFormService } from './mobile-app-transactions-form.service';

describe('MobileAppTransactions Form Service', () => {
  let service: MobileAppTransactionsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MobileAppTransactionsFormService);
  });

  describe('Service methods', () => {
    describe('createMobileAppTransactionsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createMobileAppTransactionsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            channel: expect.any(Object),
            channelIp: expect.any(Object),
            channelReference: expect.any(Object),
            channelTimestamp: expect.any(Object),
            clientId: expect.any(Object),
            createdAt: expect.any(Object),
            debitAccount: expect.any(Object),
            direction: expect.any(Object),
            errorDescription: expect.any(Object),
            geolocation: expect.any(Object),
            hostCode: expect.any(Object),
            phoneNumber: expect.any(Object),
            responseCode: expect.any(Object),
            responseMessage: expect.any(Object),
            transactionCode: expect.any(Object),
            transactionType: expect.any(Object),
            userAgent: expect.any(Object),
            userAgentVersion: expect.any(Object),
            amount: expect.any(Object),
            chargeamount: expect.any(Object),
            creditAccount: expect.any(Object),
            cbsReference: expect.any(Object),
          }),
        );
      });

      it('passing IMobileAppTransactions should create a new form with FormGroup', () => {
        const formGroup = service.createMobileAppTransactionsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            channel: expect.any(Object),
            channelIp: expect.any(Object),
            channelReference: expect.any(Object),
            channelTimestamp: expect.any(Object),
            clientId: expect.any(Object),
            createdAt: expect.any(Object),
            debitAccount: expect.any(Object),
            direction: expect.any(Object),
            errorDescription: expect.any(Object),
            geolocation: expect.any(Object),
            hostCode: expect.any(Object),
            phoneNumber: expect.any(Object),
            responseCode: expect.any(Object),
            responseMessage: expect.any(Object),
            transactionCode: expect.any(Object),
            transactionType: expect.any(Object),
            userAgent: expect.any(Object),
            userAgentVersion: expect.any(Object),
            amount: expect.any(Object),
            chargeamount: expect.any(Object),
            creditAccount: expect.any(Object),
            cbsReference: expect.any(Object),
          }),
        );
      });
    });

    describe('getMobileAppTransactions', () => {
      it('should return NewMobileAppTransactions for default MobileAppTransactions initial value', () => {
        const formGroup = service.createMobileAppTransactionsFormGroup(sampleWithNewData);

        const mobileAppTransactions = service.getMobileAppTransactions(formGroup) as any;

        expect(mobileAppTransactions).toMatchObject(sampleWithNewData);
      });

      it('should return NewMobileAppTransactions for empty MobileAppTransactions initial value', () => {
        const formGroup = service.createMobileAppTransactionsFormGroup();

        const mobileAppTransactions = service.getMobileAppTransactions(formGroup) as any;

        expect(mobileAppTransactions).toMatchObject({});
      });

      it('should return IMobileAppTransactions', () => {
        const formGroup = service.createMobileAppTransactionsFormGroup(sampleWithRequiredData);

        const mobileAppTransactions = service.getMobileAppTransactions(formGroup) as any;

        expect(mobileAppTransactions).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IMobileAppTransactions should not enable id FormControl', () => {
        const formGroup = service.createMobileAppTransactionsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewMobileAppTransactions should disable id FormControl', () => {
        const formGroup = service.createMobileAppTransactionsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
