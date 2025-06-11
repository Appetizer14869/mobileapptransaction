import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IMobileAppTransactions } from '../mobile-app-transactions.model';
import {
  sampleWithFullData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithRequiredData,
} from '../mobile-app-transactions.test-samples';

import { MobileAppTransactionsService } from './mobile-app-transactions.service';

const requireRestSample: IMobileAppTransactions = {
  ...sampleWithRequiredData,
};

describe('MobileAppTransactions Service', () => {
  let service: MobileAppTransactionsService;
  let httpMock: HttpTestingController;
  let expectedResult: IMobileAppTransactions | IMobileAppTransactions[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(MobileAppTransactionsService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a MobileAppTransactions', () => {
      const mobileAppTransactions = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(mobileAppTransactions).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a MobileAppTransactions', () => {
      const mobileAppTransactions = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(mobileAppTransactions).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a MobileAppTransactions', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of MobileAppTransactions', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a MobileAppTransactions', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addMobileAppTransactionsToCollectionIfMissing', () => {
      it('should add a MobileAppTransactions to an empty array', () => {
        const mobileAppTransactions: IMobileAppTransactions = sampleWithRequiredData;
        expectedResult = service.addMobileAppTransactionsToCollectionIfMissing([], mobileAppTransactions);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(mobileAppTransactions);
      });

      it('should not add a MobileAppTransactions to an array that contains it', () => {
        const mobileAppTransactions: IMobileAppTransactions = sampleWithRequiredData;
        const mobileAppTransactionsCollection: IMobileAppTransactions[] = [
          {
            ...mobileAppTransactions,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addMobileAppTransactionsToCollectionIfMissing(mobileAppTransactionsCollection, mobileAppTransactions);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a MobileAppTransactions to an array that doesn't contain it", () => {
        const mobileAppTransactions: IMobileAppTransactions = sampleWithRequiredData;
        const mobileAppTransactionsCollection: IMobileAppTransactions[] = [sampleWithPartialData];
        expectedResult = service.addMobileAppTransactionsToCollectionIfMissing(mobileAppTransactionsCollection, mobileAppTransactions);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(mobileAppTransactions);
      });

      it('should add only unique MobileAppTransactions to an array', () => {
        const mobileAppTransactionsArray: IMobileAppTransactions[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const mobileAppTransactionsCollection: IMobileAppTransactions[] = [sampleWithRequiredData];
        expectedResult = service.addMobileAppTransactionsToCollectionIfMissing(
          mobileAppTransactionsCollection,
          ...mobileAppTransactionsArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const mobileAppTransactions: IMobileAppTransactions = sampleWithRequiredData;
        const mobileAppTransactions2: IMobileAppTransactions = sampleWithPartialData;
        expectedResult = service.addMobileAppTransactionsToCollectionIfMissing([], mobileAppTransactions, mobileAppTransactions2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(mobileAppTransactions);
        expect(expectedResult).toContain(mobileAppTransactions2);
      });

      it('should accept null and undefined values', () => {
        const mobileAppTransactions: IMobileAppTransactions = sampleWithRequiredData;
        expectedResult = service.addMobileAppTransactionsToCollectionIfMissing([], null, mobileAppTransactions, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(mobileAppTransactions);
      });

      it('should return initial array if no MobileAppTransactions is added', () => {
        const mobileAppTransactionsCollection: IMobileAppTransactions[] = [sampleWithRequiredData];
        expectedResult = service.addMobileAppTransactionsToCollectionIfMissing(mobileAppTransactionsCollection, undefined, null);
        expect(expectedResult).toEqual(mobileAppTransactionsCollection);
      });
    });

    describe('compareMobileAppTransactions', () => {
      it('should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareMobileAppTransactions(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('should return false if one entity is null', () => {
        const entity1 = { id: 25618 };
        const entity2 = null;

        const compareResult1 = service.compareMobileAppTransactions(entity1, entity2);
        const compareResult2 = service.compareMobileAppTransactions(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('should return false if primaryKey differs', () => {
        const entity1 = { id: 25618 };
        const entity2 = { id: 15098 };

        const compareResult1 = service.compareMobileAppTransactions(entity1, entity2);
        const compareResult2 = service.compareMobileAppTransactions(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('should return false if primaryKey matches', () => {
        const entity1 = { id: 25618 };
        const entity2 = { id: 25618 };

        const compareResult1 = service.compareMobileAppTransactions(entity1, entity2);
        const compareResult2 = service.compareMobileAppTransactions(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
