import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

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
    return this.http.post<INhanvien>(this.resourceUrl, nhanvien, { observe: 'response' });
  }

  update(nhanvien: INhanvien): Observable<EntityResponseType> {
    return this.http.put<INhanvien>(this.resourceUrl, nhanvien, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<INhanvien>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INhanvien[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
