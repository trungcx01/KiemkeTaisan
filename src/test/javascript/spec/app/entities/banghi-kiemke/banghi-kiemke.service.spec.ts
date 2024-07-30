import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { BanghiKiemkeService } from 'app/entities/banghi-kiemke/banghi-kiemke.service';
import { IBanghiKiemke, BanghiKiemke } from 'app/shared/model/banghi-kiemke.model';

describe('Service Tests', () => {
  describe('BanghiKiemke Service', () => {
    let injector: TestBed;
    let service: BanghiKiemkeService;
    let httpMock: HttpTestingController;
    let elemDefault: IBanghiKiemke;
    let expectedResult: IBanghiKiemke | IBanghiKiemke[] | boolean | null;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BanghiKiemkeService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new BanghiKiemke('ID', 0, 0, 0, 'AAAAAAA', 0, 0);
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

      it('should create a BanghiKiemke', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new BanghiKiemke())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a BanghiKiemke', () => {
        const returnedFromService = Object.assign(
          {
            soluong: 1,
            nguyengia: 1,
            giatriConlai: 1,
            ghichu: 'BBBBBB',
            tinhtrangSudung: 1,
            hinhthucXuly: 1,
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

      it('should return a list of BanghiKiemke', () => {
        const returnedFromService = Object.assign(
          {
            soluong: 1,
            nguyengia: 1,
            giatriConlai: 1,
            ghichu: 'BBBBBB',
            tinhtrangSudung: 1,
            hinhthucXuly: 1,
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

      it('should delete a BanghiKiemke', () => {
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
