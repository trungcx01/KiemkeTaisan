import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TaisanTestModule } from '../../../test.module';
import { DanhmucTaisanDetailComponent } from 'app/entities/danhmuc-taisan/danhmuc-taisan-detail.component';
import { DanhmucTaisan } from 'app/shared/model/danhmuc-taisan.model';

describe('Component Tests', () => {
  describe('DanhmucTaisan Management Detail Component', () => {
    let comp: DanhmucTaisanDetailComponent;
    let fixture: ComponentFixture<DanhmucTaisanDetailComponent>;
    const route = { data: of({ danhmucTaisan: new DanhmucTaisan('123') }) } as any as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaisanTestModule],
        declarations: [DanhmucTaisanDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DanhmucTaisanDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DanhmucTaisanDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load danhmucTaisan on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.danhmucTaisan).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
