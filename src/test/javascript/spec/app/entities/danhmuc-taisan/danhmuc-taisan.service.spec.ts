import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { DanhmucTaisanService } from 'app/entities/danhmuc-taisan/danhmuc-taisan.service';
import { IDanhmucTaisan, DanhmucTaisan } from 'app/shared/model/danhmuc-taisan.model';

describe('Service Tests', () => {
  describe('DanhmucTaisan Service', () => {
    let injector: TestBed;
    let service: DanhmucTaisanService;
    let httpMock: HttpTestingController;
    let elemDefault: IDanhmucTaisan;
    let expectedResult: IDanhmucTaisan | IDanhmucTaisan[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DanhmucTaisanService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new DanhmucTaisan('ID', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            ngaytao: currentDate.format(DATE_FORMAT),
            ngayCapnhat: currentDate.format(DATE_FORMAT),
          },
          elemDefault,
        );
        service
          .find('123')
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a DanhmucTaisan', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            ngaytao: currentDate.format(DATE_FORMAT),
            ngayCapnhat: currentDate.format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            ngaytao: currentDate,
            ngayCapnhat: currentDate,
          },
          returnedFromService,
        );
        service
          .create(new DanhmucTaisan())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a DanhmucTaisan', () => {
        const returnedFromService = Object.assign(
          {
            maDMTS: 'BBBBBB',
            ten: 'BBBBBB',
            mota: 'BBBBBB',
            ngaytao: currentDate.format(DATE_FORMAT),
            ngayCapnhat: currentDate.format(DATE_FORMAT),
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            ngaytao: currentDate,
            ngayCapnhat: currentDate,
          },
          returnedFromService,
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of DanhmucTaisan', () => {
        const returnedFromService = Object.assign(
          {
            maDMTS: 'BBBBBB',
            ten: 'BBBBBB',
            mota: 'BBBBBB',
            ngaytao: currentDate.format(DATE_FORMAT),
            ngayCapnhat: currentDate.format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            ngaytao: currentDate,
            ngayCapnhat: currentDate,
          },
          returnedFromService,
        );
        service
          .query()
          .pipe(
            take(1),
            map(resp => resp.body),
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a DanhmucTaisan', () => {
        service.delete('123').subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
