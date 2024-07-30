import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TaisanTestModule } from '../../../test.module';
import { TaisanUpdateComponent } from 'app/entities/taisan/taisan-update.component';
import { TaisanService } from 'app/entities/taisan/taisan.service';
import { Taisan } from 'app/shared/model/taisan.model';

describe('Component Tests', () => {
  describe('Taisan Management Update Component', () => {
    let comp: TaisanUpdateComponent;
    let fixture: ComponentFixture<TaisanUpdateComponent>;
    let service: TaisanService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaisanTestModule],
        declarations: [TaisanUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TaisanUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TaisanUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TaisanService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Taisan('123');
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
        const entity = new Taisan();
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
