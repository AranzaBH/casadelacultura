import { TestBed } from '@angular/core/testing';
import { TallerService } from '../services/talleres.service';

describe('TalleresService', () => {
  let service: TallerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TallerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
