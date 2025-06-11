import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IMobileAppTransactions } from '../mobile-app-transactions.model';
import { MobileAppTransactionsService } from '../service/mobile-app-transactions.service';
import { MobileAppTransactionsFormGroup, MobileAppTransactionsFormService } from './mobile-app-transactions-form.service';

@Component({
  selector: 'jhi-mobile-app-transactions-update',
  templateUrl: './mobile-app-transactions-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class MobileAppTransactionsUpdateComponent implements OnInit {
  isSaving = false;
  mobileAppTransactions: IMobileAppTransactions | null = null;

  protected mobileAppTransactionsService = inject(MobileAppTransactionsService);
  protected mobileAppTransactionsFormService = inject(MobileAppTransactionsFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: MobileAppTransactionsFormGroup = this.mobileAppTransactionsFormService.createMobileAppTransactionsFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mobileAppTransactions }) => {
      this.mobileAppTransactions = mobileAppTransactions;
      if (mobileAppTransactions) {
        this.updateForm(mobileAppTransactions);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const mobileAppTransactions = this.mobileAppTransactionsFormService.getMobileAppTransactions(this.editForm);
    if (mobileAppTransactions.id !== null) {
      this.subscribeToSaveResponse(this.mobileAppTransactionsService.update(mobileAppTransactions));
    } else {
      this.subscribeToSaveResponse(this.mobileAppTransactionsService.create(mobileAppTransactions));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMobileAppTransactions>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(mobileAppTransactions: IMobileAppTransactions): void {
    this.mobileAppTransactions = mobileAppTransactions;
    this.mobileAppTransactionsFormService.resetForm(this.editForm, mobileAppTransactions);
  }
}
