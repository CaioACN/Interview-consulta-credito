// src/app/shared/models/credito.model.ts
export interface Credito {
  id?: number;
  numeroCredito: string;
  numeroNfse: string;
  dataConstituicao: string; // "YYYY-MM-DD" vindo do Spring
  valorIssqn: number;
  tipoCredito: string;
  simplesNacional: boolean;
  aliquota: number;
  valorFaturado: number;
  valorDeducao: number;
  baseCalculo: number;
}
