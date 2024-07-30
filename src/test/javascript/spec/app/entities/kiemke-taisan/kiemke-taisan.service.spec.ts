import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { KiemkeTaisanService } from 'app/entities/kiemke-taisan/kiemke-taisan.service';
import { IKiemkeTaisan, KiemkeTaisan } from 'app/shared/model/kiemke-taisan.model';

describe('Service Tests', () => {
  describe('KiemkeTaisan Service', () => {
    let injector: TestBed;
    let service: KiemkeTaisanService;
    let httpMock: HttpTestingController;
    let elemDefault: IKiemkeTaisan;
    let expectedResult: IKiemkeTaisan | IKiemkeTaisan[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(KiemkeTaisanService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new KiemkeTaisan('ID', 'AAAAAAA', currentDate, currentDate, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            ngayLapphieu: currentDate.format(DATE_FORMAT),
            ngayKiemke: currentDate.format(DATE_FORMAT),
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

      it('should create a KiemkeTaisan', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            ngayLapphieu: currentDate.format(DATE_FORMAT),
            ngayKiemke: currentDate.format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            ngayLapphieu: currentDate,
            ngayKiemke: currentDate,
          },
          returnedFromService,
        );
        service
          .create(new KiemkeTaisan())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a KiemkeTaisan', () => {
        const returnedFromService = Object.assign(
          {
            sophieu: 'BBBBBB',
            ngayLapphieu: currentDate.format(DATE_FORMAT),
            ngayKiemke: currentDate.format(DATE_FORMAT),
            donviSudung: 'BBBBBB',
            ghichu: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            ngayLapphieu: currentDate,
            ngayKiemke: currentDate,
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

      it('should return a list of KiemkeTaisan', () => {
        const returnedFromService = Object.assign(
          {
            sophieu: 'BBBBBB',
            ngayLapphieu: currentDate.format(DATE_FORMAT),
            ngayKiemke: currentDate.format(DATE_FORMAT),
            donviSudung: 'BBBBBB',
            ghichu: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            ngayLapphieu: currentDate,
            ngayKiemke: currentDate,
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

      it('should delete a KiemkeTaisan', () => {
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
