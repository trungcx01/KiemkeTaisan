import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TaisanTestModule } from '../../../test.module';
import { NhanvienUpdateComponent } from 'app/entities/nhanvien/nhanvien-update.component';
import { NhanvienService } from 'app/entities/nhanvien/nhanvien.service';
import { Nhanvien } from 'app/shared/model/nhanvien.model';

describe('Component Tests', () => {
  describe('Nhanvien Management Update Component', () => {
    let comp: NhanvienUpdateComponent;
    let fixture: ComponentFixture<NhanvienUpdateComponent>;
    let service: NhanvienService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaisanTestModule],
        declarations: [NhanvienUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(NhanvienUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NhanvienUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NhanvienService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Nhanvien('123');
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
        const entity = new Nhanvien();
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
