import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import MobileAppTransactionsResolve from './route/mobile-app-transactions-routing-resolve.service';

const mobileAppTransactionsRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/mobile-app-transactions.component').then(m => m.MobileAppTransactionsComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/mobile-app-transactions-detail.component').then(m => m.MobileAppTransactionsDetailComponent),
    resolve: {
      mobileAppTransactions: MobileAppTransactionsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/mobile-app-transactions-update.component').then(m => m.MobileAppTransactionsUpdateComponent),
    resolve: {
      mobileAppTransactions: MobileAppTransactionsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/mobile-app-transactions-update.component').then(m => m.MobileAppTransactionsUpdateComponent),
    resolve: {
      mobileAppTransactions: MobileAppTransactionsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default mobileAppTransactionsRoute;
