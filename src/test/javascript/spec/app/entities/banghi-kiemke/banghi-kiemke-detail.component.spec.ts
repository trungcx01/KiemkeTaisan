import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TaisanTestModule } from '../../../test.module';
import { BanghiKiemkeDetailComponent } from 'app/entities/banghi-kiemke/banghi-kiemke-detail.component';
import { BanghiKiemke } from 'app/shared/model/banghi-kiemke.model';

describe('Component Tests', () => {
  describe('BanghiKiemke Management Detail Component', () => {
    let comp: BanghiKiemkeDetailComponent;
    let fixture: ComponentFixture<BanghiKiemkeDetailComponent>;
    const route = ({ data: of({ banghiKiemke: new BanghiKiemke('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaisanTestModule],
        declarations: [BanghiKiemkeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BanghiKiemkeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BanghiKiemkeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load banghiKiemke on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.banghiKiemke).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
