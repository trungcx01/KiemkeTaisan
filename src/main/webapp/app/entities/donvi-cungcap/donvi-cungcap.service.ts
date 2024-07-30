import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDonviCungcap } from 'app/shared/model/donvi-cungcap.model';

type EntityResponseType = HttpResponse<IDonviCungcap>;
type EntityArrayResponseType = HttpResponse<IDonviCungcap[]>;

@Injectable({ providedIn: 'root' })
export class DonviCungcapService {
  public resourceUrl = SERVER_API_URL + 'api/donvi-cungcaps';

  constructor(protected http: HttpClient) {}

  create(donviCungcap: IDonviCungcap): Observable<EntityResponseType> {
    return this.http.post<IDonviCungcap>(this.resourceUrl, donviCungcap, { observe: 'response' });
  }

  update(donviCungcap: IDonviCungcap): Observable<EntityResponseType> {
    return this.http.put<IDonviCungcap>(this.resourceUrl, donviCungcap, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IDonviCungcap>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDonviCungcap[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
