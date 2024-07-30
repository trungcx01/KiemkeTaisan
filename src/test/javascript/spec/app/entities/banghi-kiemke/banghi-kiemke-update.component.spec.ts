import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TaisanTestModule } from '../../../test.module';
import { BanghiKiemkeUpdateComponent } from 'app/entities/banghi-kiemke/banghi-kiemke-update.component';
import { BanghiKiemkeService } from 'app/entities/banghi-kiemke/banghi-kiemke.service';
import { BanghiKiemke } from 'app/shared/model/banghi-kiemke.model';

describe('Component Tests', () => {
  describe('BanghiKiemke Management Update Component', () => {
    let comp: BanghiKiemkeUpdateComponent;
    let fixture: ComponentFixture<BanghiKiemkeUpdateComponent>;
    let service: BanghiKiemkeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaisanTestModule],
        declarations: [BanghiKiemkeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BanghiKiemkeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BanghiKiemkeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BanghiKiemkeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BanghiKiemke('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new BanghiKiemke();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
