import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { TaisanTestModule } from '../../../test.module';
import { DanhmucTaisanComponent } from 'app/entities/danhmuc-taisan/danhmuc-taisan.component';
import { DanhmucTaisanService } from 'app/entities/danhmuc-taisan/danhmuc-taisan.service';
import { DanhmucTaisan } from 'app/shared/model/danhmuc-taisan.model';

describe('Component Tests', () => {
  describe('DanhmucTaisan Management Component', () => {
    let comp: DanhmucTaisanComponent;
    let fixture: ComponentFixture<DanhmucTaisanComponent>;
    let service: DanhmucTaisanService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TaisanTestModule],
        declarations: [DanhmucTaisanComponent],
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
                      page: 0
                    }
                  })
              }
            }
          }
        ]
      })
        .overrideTemplate(DanhmucTaisanComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DanhmucTaisanComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DanhmucTaisanService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DanhmucTaisan('123')],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.danhmucTaisans && comp.danhmucTaisans[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DanhmucTaisan('123')],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.danhmucTaisans && comp.danhmucTaisans[0]).toEqual(jasmine.objectContaining({ id: '123' }));
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
