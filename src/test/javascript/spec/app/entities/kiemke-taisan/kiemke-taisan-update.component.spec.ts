import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TaisanTestModule } from '../../../test.module';
import { KiemkeTaisanUpdateComponent } from 'app/entities/kiemke-taisan/kiemke-taisan-update.component';
import { KiemkeTaisanService } from 'app/entities/kiemke-taisan/kiemke-taisan.service';
import { KiemkeTaisan } from 'app/shared/model/kiemke-taisan.model';

describe('Component Tests', () => {
  describe('KiemkeTaisan Management Update Component', () => {
    let comp: KiemkeTaisanUpdateComponent;
    let fixture: ComponentFixture<KiemkeTaisanUpdateComponent>;
    let service: KiemkeTaisanService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaisanTestModule],
        declarations: [KiemkeTaisanUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(KiemkeTaisanUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(KiemkeTaisanUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(KiemkeTaisanService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new KiemkeTaisan('123');
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
        const entity = new KiemkeTaisan();
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
