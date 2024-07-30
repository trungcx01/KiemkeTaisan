import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TaisanTestModule } from '../../../test.module';
import { DanhmucTaisanUpdateComponent } from 'app/entities/danhmuc-taisan/danhmuc-taisan-update.component';
import { DanhmucTaisanService } from 'app/entities/danhmuc-taisan/danhmuc-taisan.service';
import { DanhmucTaisan } from 'app/shared/model/danhmuc-taisan.model';

describe('Component Tests', () => {
  describe('DanhmucTaisan Management Update Component', () => {
    let comp: DanhmucTaisanUpdateComponent;
    let fixture: ComponentFixture<DanhmucTaisanUpdateComponent>;
    let service: DanhmucTaisanService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaisanTestModule],
        declarations: [DanhmucTaisanUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DanhmucTaisanUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DanhmucTaisanUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DanhmucTaisanService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DanhmucTaisan('123');
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
        const entity = new DanhmucTaisan();
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
