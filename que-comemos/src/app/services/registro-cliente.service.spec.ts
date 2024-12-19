import { TestBed } from '@angular/core/testing';

import { RegistroClienteService } from './registro-cliente.service';

describe('RegistroClienteService', () => {
  let service: RegistroClienteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegistroClienteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
