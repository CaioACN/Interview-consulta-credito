// src/app/core/services/credito.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Credito } from '../../shared/models/credito.model';

@Injectable({ providedIn: 'root' })
export class CreditoService {
  // use a base configurável (proxy.conf.json ou environment.apiUrl)
  private readonly apiUrl = '/api/creditos';

  constructor(private http: HttpClient) {}

  /** GET /api/creditos/{numeroNfse} -> lista por NFS-e */
  porNfse(numeroNfse: string): Observable<Credito[]> {
    return this.http.get<Credito[]>(`${this.apiUrl}/${encodeURIComponent(numeroNfse)}`);
  }

  /** GET /api/creditos/credito/{numeroCredito} -> detalhe por nº do crédito */
  buscarPorNumero(numeroCredito: string): Observable<Credito> {
    return this.http.get<Credito>(`${this.apiUrl}/credito/${encodeURIComponent(numeroCredito)}`);
  }
}
