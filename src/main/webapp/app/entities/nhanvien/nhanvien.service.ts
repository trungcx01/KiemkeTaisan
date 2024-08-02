import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INhanvien } from 'app/shared/model/nhanvien.model';

type EntityResponseType = HttpResponse<INhanvien>;
type EntityArrayResponseType = HttpResponse<INhanvien[]>;

@Injectable({ providedIn: 'root' })
export class NhanvienService {
  public resourceUrl = SERVER_API_URL + 'api/nhanviens';

  constructor(protected http: HttpClient) {}

  create(nhanvien: INhanvien): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(nhanvien);
    return this.http
      .post<INhanvien>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(nhanvien: INhanvien): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(nhanvien);
    return this.http
      .put<INhanvien>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<INhanvien>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<INhanvien[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(nhanvien: INhanvien): INhanvien {
    const copy: INhanvien = Object.assign({}, nhanvien, {
      ngayThamgia: nhanvien.ngayThamgia && nhanvien.ngayThamgia.isValid() ? nhanvien.ngayThamgia.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ngayThamgia = res.body.ngayThamgia ? moment(res.body.ngayThamgia) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((nhanvien: INhanvien) => {
        nhanvien.ngayThamgia = nhanvien.ngayThamgia ? moment(nhanvien.ngayThamgia) : undefined;
      });
    }
    return res;
  }
}
