import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TaisanTestModule } from '../../../test.module';
import { TaisanDetailComponent } from 'app/entities/taisan/taisan-detail.component';
import { Taisan } from 'app/shared/model/taisan.model';

describe('Component Tests', () => {
  describe('Taisan Management Detail Component', () => {
    let comp: TaisanDetailComponent;
    let fixture: ComponentFixture<TaisanDetailComponent>;
    const route = { data: of({ taisan: new Taisan('123') }) } as any as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaisanTestModule],
        declarations: [TaisanDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TaisanDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TaisanDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load taisan on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.taisan).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
