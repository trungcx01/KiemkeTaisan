import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { NhanvienService } from 'app/entities/nhanvien/nhanvien.service';
import { INhanvien, Nhanvien } from 'app/shared/model/nhanvien.model';

describe('Service Tests', () => {
  describe('Nhanvien Service', () => {
    let injector: TestBed;
    let service: NhanvienService;
    let httpMock: HttpTestingController;
    let elemDefault: INhanvien;
    let expectedResult: INhanvien | INhanvien[] | boolean | null;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(NhanvienService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Nhanvien('ID', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find('123')
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Nhanvien', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new Nhanvien())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Nhanvien', () => {
        const returnedFromService = Object.assign(
          {
            maNV: 'BBBBBB',
            ten: 'BBBBBB',
            chucvu: 'BBBBBB',
            diachi: 'BBBBBB',
            gioitinh: 1,
            sdt: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Nhanvien', () => {
        const returnedFromService = Object.assign(
          {
            maNV: 'BBBBBB',
            ten: 'BBBBBB',
            chucvu: 'BBBBBB',
            diachi: 'BBBBBB',
            gioitinh: 1,
            sdt: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
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

      it('should delete a Nhanvien', () => {
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
