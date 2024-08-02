import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TaisanTestModule } from '../../../test.module';
import { NhanvienDetailComponent } from 'app/entities/nhanvien/nhanvien-detail.component';
import { Nhanvien } from 'app/shared/model/nhanvien.model';

describe('Component Tests', () => {
  describe('Nhanvien Management Detail Component', () => {
    let comp: NhanvienDetailComponent;
    let fixture: ComponentFixture<NhanvienDetailComponent>;
    const route = ({ data: of({ nhanvien: new Nhanvien('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaisanTestModule],
        declarations: [NhanvienDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(NhanvienDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NhanvienDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load nhanvien on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.nhanvien).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
