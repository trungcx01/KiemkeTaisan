import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TaisanTestModule } from '../../../test.module';
import { KiemkeTaisanDetailComponent } from 'app/entities/kiemke-taisan/kiemke-taisan-detail.component';
import { KiemkeTaisan } from 'app/shared/model/kiemke-taisan.model';

describe('Component Tests', () => {
  describe('KiemkeTaisan Management Detail Component', () => {
    let comp: KiemkeTaisanDetailComponent;
    let fixture: ComponentFixture<KiemkeTaisanDetailComponent>;
    const route = { data: of({ kiemkeTaisan: new KiemkeTaisan('123') }) } as any as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaisanTestModule],
        declarations: [KiemkeTaisanDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(KiemkeTaisanDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(KiemkeTaisanDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load kiemkeTaisan on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.kiemkeTaisan).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
