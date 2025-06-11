import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { IMobileAppTransactions } from '../mobile-app-transactions.model';

@Component({
  selector: 'jhi-mobile-app-transactions-detail',
  templateUrl: './mobile-app-transactions-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class MobileAppTransactionsDetailComponent {
  mobileAppTransactions = input<IMobileAppTransactions | null>(null);

  previousState(): void {
    window.history.back();
  }
}
