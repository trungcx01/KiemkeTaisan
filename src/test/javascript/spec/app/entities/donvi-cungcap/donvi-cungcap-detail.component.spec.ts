import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TaisanTestModule } from '../../../test.module';
import { DonviCungcapDetailComponent } from 'app/entities/donvi-cungcap/donvi-cungcap-detail.component';
import { DonviCungcap } from 'app/shared/model/donvi-cungcap.model';

describe('Component Tests', () => {
  describe('DonviCungcap Management Detail Component', () => {
    let comp: DonviCungcapDetailComponent;
    let fixture: ComponentFixture<DonviCungcapDetailComponent>;
    const route = ({ data: of({ donviCungcap: new DonviCungcap('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaisanTestModule],
        declarations: [DonviCungcapDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DonviCungcapDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DonviCungcapDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load donviCungcap on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.donviCungcap).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
