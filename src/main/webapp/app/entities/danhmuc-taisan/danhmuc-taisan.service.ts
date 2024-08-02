import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDanhmucTaisan } from 'app/shared/model/danhmuc-taisan.model';

type EntityResponseType = HttpResponse<IDanhmucTaisan>;
type EntityArrayResponseType = HttpResponse<IDanhmucTaisan[]>;

@Injectable({ providedIn: 'root' })
export class DanhmucTaisanService {
  public resourceUrl = SERVER_API_URL + 'api/danhmuc-taisans';

  constructor(protected http: HttpClient) {}

  create(danhmucTaisan: IDanhmucTaisan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(danhmucTaisan);
    return this.http
      .post<IDanhmucTaisan>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(danhmucTaisan: IDanhmucTaisan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(danhmucTaisan);
    return this.http
      .put<IDanhmucTaisan>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IDanhmucTaisan>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDanhmucTaisan[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(danhmucTaisan: IDanhmucTaisan): IDanhmucTaisan {
    const copy: IDanhmucTaisan = Object.assign({}, danhmucTaisan, {
      ngaytao: danhmucTaisan.ngaytao && danhmucTaisan.ngaytao.isValid() ? danhmucTaisan.ngaytao.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ngaytao = res.body.ngaytao ? moment(res.body.ngaytao) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((danhmucTaisan: IDanhmucTaisan) => {
        danhmucTaisan.ngaytao = danhmucTaisan.ngaytao ? moment(danhmucTaisan.ngaytao) : undefined;
      });
    }
    return res;
  }
}
