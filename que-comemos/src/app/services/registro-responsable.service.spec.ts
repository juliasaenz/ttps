import { TestBed } from '@angular/core/testing';

import { RegistroResponsableService } from './registro-responsable.service';

describe('RegistroResponsableService', () => {
  let service: RegistroResponsableService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegistroResponsableService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
