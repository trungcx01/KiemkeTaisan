import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TaisanTestModule } from '../../../test.module';
import { NhanvienKiemkeUpdateComponent } from 'app/entities/nhanvien-kiemke/nhanvien-kiemke-update.component';
import { NhanvienKiemkeService } from 'app/entities/nhanvien-kiemke/nhanvien-kiemke.service';
import { NhanvienKiemke } from 'app/shared/model/nhanvien-kiemke.model';

describe('Component Tests', () => {
  describe('NhanvienKiemke Management Update Component', () => {
    let comp: NhanvienKiemkeUpdateComponent;
    let fixture: ComponentFixture<NhanvienKiemkeUpdateComponent>;
    let service: NhanvienKiemkeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaisanTestModule],
        declarations: [NhanvienKiemkeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(NhanvienKiemkeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NhanvienKiemkeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NhanvienKiemkeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NhanvienKiemke('123');
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
        const entity = new NhanvienKiemke();
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
