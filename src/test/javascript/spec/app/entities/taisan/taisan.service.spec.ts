import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { TaisanService } from 'app/entities/taisan/taisan.service';
import { ITaisan, Taisan } from 'app/shared/model/taisan.model';

describe('Service Tests', () => {
  describe('Taisan Service', () => {
    let injector: TestBed;
    let service: TaisanService;
    let httpMock: HttpTestingController;
    let elemDefault: ITaisan;
    let expectedResult: ITaisan | ITaisan[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TaisanService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Taisan(
        'ID',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
        0,
        currentDate,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            ngayNhan: currentDate.format(DATE_FORMAT),
            ngaySudung: currentDate.format(DATE_FORMAT),
            ngaySanxuat: currentDate.format(DATE_FORMAT),
            ngayHethan: currentDate.format(DATE_FORMAT),
            ngayHoadon: currentDate.format(DATE_FORMAT),
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

      it('should create a Taisan', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            ngayNhan: currentDate.format(DATE_FORMAT),
            ngaySudung: currentDate.format(DATE_FORMAT),
            ngaySanxuat: currentDate.format(DATE_FORMAT),
            ngayHethan: currentDate.format(DATE_FORMAT),
            ngayHoadon: currentDate.format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            ngayNhan: currentDate,
            ngaySudung: currentDate,
            ngaySanxuat: currentDate,
            ngayHethan: currentDate,
            ngayHoadon: currentDate,
          },
          returnedFromService,
        );
        service
          .create(new Taisan())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Taisan', () => {
        const returnedFromService = Object.assign(
          {
            maTaisan: 'BBBBBB',
            tenTaisan: 'BBBBBB',
            tenRutgon: 'BBBBBB',
            model: 'BBBBBB',
            serial: 'BBBBBB',
            loaiTaisan: 1,
            mota: 'BBBBBB',
            ngayNhan: currentDate.format(DATE_FORMAT),
            ngaySudung: currentDate.format(DATE_FORMAT),
            ngaySanxuat: currentDate.format(DATE_FORMAT),
            thoigianSudung: 1,
            ngayHethan: currentDate.format(DATE_FORMAT),
            haomon: 1,
            nguyengia: 1,
            soHopdongMua: 'BBBBBB',
            soHoadonMua: 'BBBBBB',
            ngayHoadon: currentDate.format(DATE_FORMAT),
            trangthai: 1,
            hangSanxuat: 'BBBBBB',
            nuocSanxuat: 'BBBBBB',
            congsuatSudung: 1,
            dientichSudung: 1,
            donviTinh: 'BBBBBB',
            giatriConlai: 1,
            tangNguyengia: 1,
            nguon: 'BBBBBB',
            vitri: 'BBBBBB',
            donviQuanly: 'BBBBBB',
            donviSudung: 'BBBBBB',
            soluong: 1,
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            ngayNhan: currentDate,
            ngaySudung: currentDate,
            ngaySanxuat: currentDate,
            ngayHethan: currentDate,
            ngayHoadon: currentDate,
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

      it('should return a list of Taisan', () => {
        const returnedFromService = Object.assign(
          {
            maTaisan: 'BBBBBB',
            tenTaisan: 'BBBBBB',
            tenRutgon: 'BBBBBB',
            model: 'BBBBBB',
            serial: 'BBBBBB',
            loaiTaisan: 1,
            mota: 'BBBBBB',
            ngayNhan: currentDate.format(DATE_FORMAT),
            ngaySudung: currentDate.format(DATE_FORMAT),
            ngaySanxuat: currentDate.format(DATE_FORMAT),
            thoigianSudung: 1,
            ngayHethan: currentDate.format(DATE_FORMAT),
            haomon: 1,
            nguyengia: 1,
            soHopdongMua: 'BBBBBB',
            soHoadonMua: 'BBBBBB',
            ngayHoadon: currentDate.format(DATE_FORMAT),
            trangthai: 1,
            hangSanxuat: 'BBBBBB',
            nuocSanxuat: 'BBBBBB',
            congsuatSudung: 1,
            dientichSudung: 1,
            donviTinh: 'BBBBBB',
            giatriConlai: 1,
            tangNguyengia: 1,
            nguon: 'BBBBBB',
            vitri: 'BBBBBB',
            donviQuanly: 'BBBBBB',
            donviSudung: 'BBBBBB',
            soluong: 1,
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            ngayNhan: currentDate,
            ngaySudung: currentDate,
            ngaySanxuat: currentDate,
            ngayHethan: currentDate,
            ngayHoadon: currentDate,
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

      it('should delete a Taisan', () => {
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
