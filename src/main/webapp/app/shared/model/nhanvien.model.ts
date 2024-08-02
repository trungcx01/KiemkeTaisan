import { Moment } from 'moment';

export interface INhanvien {
  id?: string;
  maNV?: string;
  ten?: string;
  chucvu?: string;
  diachi?: string;
  gioitinh?: number;
  sdt?: string;
  phongban?: string;
  email?: string;
  ngayThamgia?: Moment;
}

export class Nhanvien implements INhanvien {
  constructor(
    public id?: string,
    public maNV?: string,
    public ten?: string,
    public chucvu?: string,
    public diachi?: string,
    public gioitinh?: number,
    public sdt?: string,
    public phongban?: string,
    public email?: string,
    public ngayThamgia?: Moment
  ) {}
}
