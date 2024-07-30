import { Moment } from 'moment';
import { INhanvienKiemke } from 'app/shared/model/nhanvien-kiemke.model';
import { IBanghiKiemke } from 'app/shared/model/banghi-kiemke.model';

export interface IKiemkeTaisan {
  id?: string;
  sophieu?: string;
  ngayLapphieu?: Moment;
  ngayKiemke?: Moment;
  donviSudung?: string;
  ghichu?: string;
  nhanvienKiemkes?: INhanvienKiemke[];
  banghiKiemkes?: IBanghiKiemke[];
}

export class KiemkeTaisan implements IKiemkeTaisan {
  constructor(
    public id?: string,
    public sophieu?: string,
    public ngayLapphieu?: Moment,
    public ngayKiemke?: Moment,
    public donviSudung?: string,
    public ghichu?: string,
    public nhanvienKiemkes?: INhanvienKiemke[],
    public banghiKiemkes?: IBanghiKiemke[],
  ) {}
}
