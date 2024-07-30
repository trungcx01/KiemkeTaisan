import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { TaisanTestModule } from '../../../test.module';
import { KiemkeTaisanComponent } from 'app/entities/kiemke-taisan/kiemke-taisan.component';
import { KiemkeTaisanService } from 'app/entities/kiemke-taisan/kiemke-taisan.service';
import { KiemkeTaisan } from 'app/shared/model/kiemke-taisan.model';

describe('Component Tests', () => {
  describe('KiemkeTaisan Management Component', () => {
    let comp: KiemkeTaisanComponent;
    let fixture: ComponentFixture<KiemkeTaisanComponent>;
    let service: KiemkeTaisanService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaisanTestModule],
        declarations: [KiemkeTaisanComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: {
                subscribe: (fn: (value: Data) => void) =>
                  fn({
                    pagingParams: {
                      predicate: 'id',
                      reverse: false,
                      page: 0,
                    },
                  }),
              },
            },
          },
        ],
      })
        .overrideTemplate(KiemkeTaisanComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(KiemkeTaisanComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(KiemkeTaisanService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new KiemkeTaisan('123')],
            headers,
          }),
        ),
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.kiemkeTaisans && comp.kiemkeTaisans[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new KiemkeTaisan('123')],
            headers,
          }),
        ),
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.kiemkeTaisans && comp.kiemkeTaisans[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
