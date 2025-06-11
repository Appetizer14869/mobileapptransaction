import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IMobileAppTransactions } from '../mobile-app-transactions.model';
import { MobileAppTransactionsService } from '../service/mobile-app-transactions.service';

const mobileAppTransactionsResolve = (route: ActivatedRouteSnapshot): Observable<null | IMobileAppTransactions> => {
  const id = route.params.id;
  if (id) {
    return inject(MobileAppTransactionsService)
      .find(id)
      .pipe(
        mergeMap((mobileAppTransactions: HttpResponse<IMobileAppTransactions>) => {
          if (mobileAppTransactions.body) {
            return of(mobileAppTransactions.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default mobileAppTransactionsResolve;
