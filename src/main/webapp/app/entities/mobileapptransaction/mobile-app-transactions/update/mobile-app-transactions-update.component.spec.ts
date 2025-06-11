import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { MobileAppTransactionsService } from '../service/mobile-app-transactions.service';
import { IMobileAppTransactions } from '../mobile-app-transactions.model';
import { MobileAppTransactionsFormService } from './mobile-app-transactions-form.service';

import { MobileAppTransactionsUpdateComponent } from './mobile-app-transactions-update.component';

describe('MobileAppTransactions Management Update Component', () => {
  let comp: MobileAppTransactionsUpdateComponent;
  let fixture: ComponentFixture<MobileAppTransactionsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let mobileAppTransactionsFormService: MobileAppTransactionsFormService;
  let mobileAppTransactionsService: MobileAppTransactionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [MobileAppTransactionsUpdateComponent],
      providers: [
        provideHttpClient(),
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(MobileAppTransactionsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MobileAppTransactionsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    mobileAppTransactionsFormService = TestBed.inject(MobileAppTransactionsFormService);
    mobileAppTransactionsService = TestBed.inject(MobileAppTransactionsService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('should update editForm', () => {
      const mobileAppTransactions: IMobileAppTransactions = { id: 15098 };

      activatedRoute.data = of({ mobileAppTransactions });
      comp.ngOnInit();

      expect(comp.mobileAppTransactions).toEqual(mobileAppTransactions);
    });
  });

  describe('save', () => {
    it('should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMobileAppTransactions>>();
      const mobileAppTransactions = { id: 25618 };
      jest.spyOn(mobileAppTransactionsFormService, 'getMobileAppTransactions').mockReturnValue(mobileAppTransactions);
      jest.spyOn(mobileAppTransactionsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ mobileAppTransactions });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: mobileAppTransactions }));
      saveSubject.complete();

      // THEN
      expect(mobileAppTransactionsFormService.getMobileAppTransactions).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(mobileAppTransactionsService.update).toHaveBeenCalledWith(expect.objectContaining(mobileAppTransactions));
      expect(comp.isSaving).toEqual(false);
    });

    it('should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMobileAppTransactions>>();
      const mobileAppTransactions = { id: 25618 };
      jest.spyOn(mobileAppTransactionsFormService, 'getMobileAppTransactions').mockReturnValue({ id: null });
      jest.spyOn(mobileAppTransactionsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ mobileAppTransactions: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: mobileAppTransactions }));
      saveSubject.complete();

      // THEN
      expect(mobileAppTransactionsFormService.getMobileAppTransactions).toHaveBeenCalled();
      expect(mobileAppTransactionsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMobileAppTransactions>>();
      const mobileAppTransactions = { id: 25618 };
      jest.spyOn(mobileAppTransactionsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ mobileAppTransactions });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(mobileAppTransactionsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
