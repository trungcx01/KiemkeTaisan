import { Injectable } from '@angular/core';
import { NgbDateParserFormatter, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

@Injectable()
export class CustomDateParserFormatter extends NgbDateParserFormatter {
  parse(value: string): NgbDateStruct {
    if (value) {
      const dateParts = value.trim().split('/');
      if (dateParts.length === 3) {
        return {
          day: +dateParts[0],
          month: +dateParts[1],
          year: +dateParts[2],
        };
      }
    }
    return null;
  }

  format(date: NgbDateStruct): string {
    return date ? `${this.padNumber(date.day)}/${this.padNumber(date.month)}/${date.year}` : '';
  }

  private padNumber(value: number | null): string {
    if (!value) {
      return '';
    }
    return value < 10 ? `0${value}` : `${value}`;
  }
}
