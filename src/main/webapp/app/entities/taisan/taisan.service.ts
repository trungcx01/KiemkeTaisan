import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITaisan } from 'app/shared/model/taisan.model';

type EntityResponseType = HttpResponse<ITaisan>;
type EntityArrayResponseType = HttpResponse<ITaisan[]>;

@Injectable({ providedIn: 'root' })
export class TaisanService {
  public resourceUrl = SERVER_API_URL + 'api/taisans';

  constructor(protected http: HttpClient) {}

  create(taisan: ITaisan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(taisan);
    return this.http
      .post<ITaisan>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(taisan: ITaisan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(taisan);
    return this.http
      .put<ITaisan>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: any): Observable<EntityResponseType> {
    return this.http
      .get<ITaisan>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITaisan[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getByDonviSudung(donviSudung: string): Observable<HttpResponse<{}>> {
    const params = new HttpParams().set('donviSudung', donviSudung);
    return this.http
      .get<ITaisan[]>(`${this.resourceUrl}/filter`, { observe: 'response', params })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(taisan: ITaisan): ITaisan {
    const copy: ITaisan = Object.assign({}, taisan, {
      ngayNhan: taisan.ngayNhan && taisan.ngayNhan.isValid() ? taisan.ngayNhan.format(DATE_FORMAT) : undefined,
      ngaySudung: taisan.ngaySudung && taisan.ngaySudung.isValid() ? taisan.ngaySudung.format(DATE_FORMAT) : undefined,
      ngaySanxuat: taisan.ngaySanxuat && taisan.ngaySanxuat.isValid() ? taisan.ngaySanxuat.format(DATE_FORMAT) : undefined,
      ngayHethan: taisan.ngayHethan && taisan.ngayHethan.isValid() ? taisan.ngayHethan.format(DATE_FORMAT) : undefined,
      ngayHoadon: taisan.ngayHoadon && taisan.ngayHoadon.isValid() ? taisan.ngayHoadon.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ngayNhan = res.body.ngayNhan ? moment(res.body.ngayNhan) : undefined;
      res.body.ngaySudung = res.body.ngaySudung ? moment(res.body.ngaySudung) : undefined;
      res.body.ngaySanxuat = res.body.ngaySanxuat ? moment(res.body.ngaySanxuat) : undefined;
      res.body.ngayHethan = res.body.ngayHethan ? moment(res.body.ngayHethan) : undefined;
      res.body.ngayHoadon = res.body.ngayHoadon ? moment(res.body.ngayHoadon) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((taisan: ITaisan) => {
        taisan.ngayNhan = taisan.ngayNhan ? moment(taisan.ngayNhan) : undefined;
        taisan.ngaySudung = taisan.ngaySudung ? moment(taisan.ngaySudung) : undefined;
        taisan.ngaySanxuat = taisan.ngaySanxuat ? moment(taisan.ngaySanxuat) : undefined;
        taisan.ngayHethan = taisan.ngayHethan ? moment(taisan.ngayHethan) : undefined;
        taisan.ngayHoadon = taisan.ngayHoadon ? moment(taisan.ngayHoadon) : undefined;
      });
    }
    return res;
  }
}
