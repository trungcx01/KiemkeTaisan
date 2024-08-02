import { Moment } from 'moment';
import { ITaisan } from 'app/shared/model/taisan.model';

export interface IDanhmucTaisan {
  id?: string;
  maDMTS?: string;
  ten?: string;
  mota?: string;
  ngaytao?: Moment;
  taisans?: ITaisan[];
}

export class DanhmucTaisan implements IDanhmucTaisan {
  constructor(
    public id?: string,
    public maDMTS?: string,
    public ten?: string,
    public mota?: string,
    public ngaytao?: Moment,
    public taisans?: ITaisan[]
  ) {}
}
