import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TaisanTestModule } from '../../../test.module';
import { NhanvienKiemkeDetailComponent } from 'app/entities/nhanvien-kiemke/nhanvien-kiemke-detail.component';
import { NhanvienKiemke } from 'app/shared/model/nhanvien-kiemke.model';

describe('Component Tests', () => {
  describe('NhanvienKiemke Management Detail Component', () => {
    let comp: NhanvienKiemkeDetailComponent;
    let fixture: ComponentFixture<NhanvienKiemkeDetailComponent>;
    const route = { data: of({ nhanvienKiemke: new NhanvienKiemke('123') }) } as any as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaisanTestModule],
        declarations: [NhanvienKiemkeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(NhanvienKiemkeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NhanvienKiemkeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load nhanvienKiemke on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.nhanvienKiemke).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
