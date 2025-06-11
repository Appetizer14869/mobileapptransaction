import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'mobile-app-transactions',
    data: { pageTitle: 'mobileapptransactionApp.mobileapptransactionMobileAppTransactions.home.title' },
    loadChildren: () => import('./mobileapptransaction/mobile-app-transactions/mobile-app-transactions.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
