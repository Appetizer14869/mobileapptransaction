import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { MobileAppTransactionsDetailComponent } from './mobile-app-transactions-detail.component';

describe('MobileAppTransactions Management Detail Component', () => {
  let comp: MobileAppTransactionsDetailComponent;
  let fixture: ComponentFixture<MobileAppTransactionsDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MobileAppTransactionsDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./mobile-app-transactions-detail.component').then(m => m.MobileAppTransactionsDetailComponent),
              resolve: { mobileAppTransactions: () => of({ id: 25618 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(MobileAppTransactionsDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MobileAppTransactionsDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('should load mobileAppTransactions on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', MobileAppTransactionsDetailComponent);

      // THEN
      expect(instance.mobileAppTransactions()).toEqual(expect.objectContaining({ id: 25618 }));
    });
  });

  describe('PreviousState', () => {
    it('should navigate to previous state', () => {
      jest.spyOn(window.history, 'back');
      comp.previousState();
      expect(window.history.back).toHaveBeenCalled();
    });
  });
});
