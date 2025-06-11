import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IMobileAppTransactions } from '../mobile-app-transactions.model';
import { MobileAppTransactionsService } from '../service/mobile-app-transactions.service';

@Component({
  templateUrl: './mobile-app-transactions-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class MobileAppTransactionsDeleteDialogComponent {
  mobileAppTransactions?: IMobileAppTransactions;

  protected mobileAppTransactionsService = inject(MobileAppTransactionsService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.mobileAppTransactionsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
