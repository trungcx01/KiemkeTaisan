import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBanghiKiemke } from 'app/shared/model/banghi-kiemke.model';

type EntityResponseType = HttpResponse<IBanghiKiemke>;
type EntityArrayResponseType = HttpResponse<IBanghiKiemke[]>;

@Injectable({ providedIn: 'root' })
export class BanghiKiemkeService {
  public resourceUrl = SERVER_API_URL + 'api/banghi-kiemkes';

  constructor(protected http: HttpClient) {}

  create(banghiKiemke: IBanghiKiemke): Observable<EntityResponseType> {
    return this.http.post<IBanghiKiemke>(this.resourceUrl, banghiKiemke, { observe: 'response' });
  }

  update(banghiKiemke: IBanghiKiemke): Observable<EntityResponseType> {
    return this.http.put<IBanghiKiemke>(this.resourceUrl, banghiKiemke, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IBanghiKiemke>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBanghiKiemke[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
