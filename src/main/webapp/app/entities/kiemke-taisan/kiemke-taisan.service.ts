import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IKiemkeTaisan } from 'app/shared/model/kiemke-taisan.model';

type EntityResponseType = HttpResponse<IKiemkeTaisan>;
type EntityArrayResponseType = HttpResponse<IKiemkeTaisan[]>;

@Injectable({ providedIn: 'root' })
export class KiemkeTaisanService {
  public resourceUrl = SERVER_API_URL + 'api/kiemke-taisans';

  constructor(protected http: HttpClient) {}

  create(kiemkeTaisan: IKiemkeTaisan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(kiemkeTaisan);
    return this.http
      .post<IKiemkeTaisan>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(kiemkeTaisan: IKiemkeTaisan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(kiemkeTaisan);
    return this.http
      .put<IKiemkeTaisan>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IKiemkeTaisan>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IKiemkeTaisan[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(kiemkeTaisan: IKiemkeTaisan): IKiemkeTaisan {
    const copy: IKiemkeTaisan = Object.assign({}, kiemkeTaisan, {
      ngayLapphieu:
        kiemkeTaisan.ngayLapphieu && kiemkeTaisan.ngayLapphieu.isValid() ? kiemkeTaisan.ngayLapphieu.format(DATE_FORMAT) : undefined,
      ngayKiemke: kiemkeTaisan.ngayKiemke && kiemkeTaisan.ngayKiemke.isValid() ? kiemkeTaisan.ngayKiemke.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ngayLapphieu = res.body.ngayLapphieu ? moment(res.body.ngayLapphieu) : undefined;
      res.body.ngayKiemke = res.body.ngayKiemke ? moment(res.body.ngayKiemke) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((kiemkeTaisan: IKiemkeTaisan) => {
        kiemkeTaisan.ngayLapphieu = kiemkeTaisan.ngayLapphieu ? moment(kiemkeTaisan.ngayLapphieu) : undefined;
        kiemkeTaisan.ngayKiemke = kiemkeTaisan.ngayKiemke ? moment(kiemkeTaisan.ngayKiemke) : undefined;
      });
    }
    return res;
  }
}
