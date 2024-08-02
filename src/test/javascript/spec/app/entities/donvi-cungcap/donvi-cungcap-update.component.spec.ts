import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TaisanTestModule } from '../../../test.module';
import { DonviCungcapUpdateComponent } from 'app/entities/donvi-cungcap/donvi-cungcap-update.component';
import { DonviCungcapService } from 'app/entities/donvi-cungcap/donvi-cungcap.service';
import { DonviCungcap } from 'app/shared/model/donvi-cungcap.model';

describe('Component Tests', () => {
  describe('DonviCungcap Management Update Component', () => {
    let comp: DonviCungcapUpdateComponent;
    let fixture: ComponentFixture<DonviCungcapUpdateComponent>;
    let service: DonviCungcapService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaisanTestModule],
        declarations: [DonviCungcapUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DonviCungcapUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DonviCungcapUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DonviCungcapService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DonviCungcap('123');
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
        const entity = new DonviCungcap();
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
