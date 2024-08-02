import { ITaisan } from 'app/shared/model/taisan.model';

export interface IDonviCungcap {
  id?: string;
  maDVCC?: string;
  ten?: string;
  diachi?: string;
  soDienThoai?: string;
  email?: string;
  taisans?: ITaisan[];
}

export class DonviCungcap implements IDonviCungcap {
  constructor(
    public id?: string,
    public maDVCC?: string,
    public ten?: string,
    public diachi?: string,
    public soDienThoai?: string,
    public email?: string,
    public taisans?: ITaisan[]
  ) {}
}
