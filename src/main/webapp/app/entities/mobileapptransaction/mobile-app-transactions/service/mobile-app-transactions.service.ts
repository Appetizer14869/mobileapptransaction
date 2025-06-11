import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IMobileAppTransactions, NewMobileAppTransactions } from '../mobile-app-transactions.model';

export type PartialUpdateMobileAppTransactions = Partial<IMobileAppTransactions> & Pick<IMobileAppTransactions, 'id'>;

export type EntityResponseType = HttpResponse<IMobileAppTransactions>;
export type EntityArrayResponseType = HttpResponse<IMobileAppTransactions[]>;

@Injectable({ providedIn: 'root' })
export class MobileAppTransactionsService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/mobile-app-transactions', 'mobileapptransaction');

  create(mobileAppTransactions: NewMobileAppTransactions): Observable<EntityResponseType> {
    return this.http.post<IMobileAppTransactions>(this.resourceUrl, mobileAppTransactions, { observe: 'response' });
  }

  update(mobileAppTransactions: IMobileAppTransactions): Observable<EntityResponseType> {
    return this.http.put<IMobileAppTransactions>(
      `${this.resourceUrl}/${this.getMobileAppTransactionsIdentifier(mobileAppTransactions)}`,
      mobileAppTransactions,
      { observe: 'response' },
    );
  }

  partialUpdate(mobileAppTransactions: PartialUpdateMobileAppTransactions): Observable<EntityResponseType> {
    return this.http.patch<IMobileAppTransactions>(
      `${this.resourceUrl}/${this.getMobileAppTransactionsIdentifier(mobileAppTransactions)}`,
      mobileAppTransactions,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMobileAppTransactions>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMobileAppTransactions[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getMobileAppTransactionsIdentifier(mobileAppTransactions: Pick<IMobileAppTransactions, 'id'>): number {
    return mobileAppTransactions.id;
  }

  compareMobileAppTransactions(o1: Pick<IMobileAppTransactions, 'id'> | null, o2: Pick<IMobileAppTransactions, 'id'> | null): boolean {
    return o1 && o2 ? this.getMobileAppTransactionsIdentifier(o1) === this.getMobileAppTransactionsIdentifier(o2) : o1 === o2;
  }

  addMobileAppTransactionsToCollectionIfMissing<Type extends Pick<IMobileAppTransactions, 'id'>>(
    mobileAppTransactionsCollection: Type[],
    ...mobileAppTransactionsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const mobileAppTransactions: Type[] = mobileAppTransactionsToCheck.filter(isPresent);
    if (mobileAppTransactions.length > 0) {
      const mobileAppTransactionsCollectionIdentifiers = mobileAppTransactionsCollection.map(mobileAppTransactionsItem =>
        this.getMobileAppTransactionsIdentifier(mobileAppTransactionsItem),
      );
      const mobileAppTransactionsToAdd = mobileAppTransactions.filter(mobileAppTransactionsItem => {
        const mobileAppTransactionsIdentifier = this.getMobileAppTransactionsIdentifier(mobileAppTransactionsItem);
        if (mobileAppTransactionsCollectionIdentifiers.includes(mobileAppTransactionsIdentifier)) {
          return false;
        }
        mobileAppTransactionsCollectionIdentifiers.push(mobileAppTransactionsIdentifier);
        return true;
      });
      return [...mobileAppTransactionsToAdd, ...mobileAppTransactionsCollection];
    }
    return mobileAppTransactionsCollection;
  }
}
