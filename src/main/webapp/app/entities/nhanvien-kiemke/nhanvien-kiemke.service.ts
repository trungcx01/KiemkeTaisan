import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INhanvienKiemke } from 'app/shared/model/nhanvien-kiemke.model';

type EntityResponseType = HttpResponse<INhanvienKiemke>;
type EntityArrayResponseType = HttpResponse<INhanvienKiemke[]>;

@Injectable({ providedIn: 'root' })
export class NhanvienKiemkeService {
  public resourceUrl = SERVER_API_URL + 'api/nhanvien-kiemkes';

  constructor(protected http: HttpClient) {}

  create(nhanvienKiemke: INhanvienKiemke): Observable<EntityResponseType> {
    return this.http.post<INhanvienKiemke>(this.resourceUrl, nhanvienKiemke, { observe: 'response' });
  }

  update(nhanvienKiemke: INhanvienKiemke): Observable<EntityResponseType> {
    return this.http.put<INhanvienKiemke>(this.resourceUrl, nhanvienKiemke, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<INhanvienKiemke>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INhanvienKiemke[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
