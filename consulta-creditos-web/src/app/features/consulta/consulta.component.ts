// src/app/features/consulta/consulta.component.ts
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  ReactiveFormsModule,
  FormBuilder,
  Validators,
  FormGroup,
  FormControl
} from '@angular/forms';
import { finalize, map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Credito } from '../../shared/models/credito.model';
import { CreditoService } from '../../core/services/credito.service';

type TipoBusca = 'nfse' | 'credito';

@Component({
  selector: 'app-consulta',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.css']
})
export class ConsultaComponent {
  // forms tipados
  form: FormGroup<{
    tipo: FormControl<TipoBusca>;
    valor: FormControl<string>;
  }>;

  resultados: Credito[] = [];
  detalhe?: Credito;
  carregando = false;
  erro?: string;

  constructor(private fb: FormBuilder, private api: CreditoService) {
    this.form = this.fb.nonNullable.group({
      tipo: this.fb.nonNullable.control<TipoBusca>('nfse'),
      valor: this.fb.nonNullable.control('', [
        Validators.required,
        Validators.minLength(3)
      ])
    });
  }

  // DTO já traz "Sim"/"Não"; mantemos compat com boolean (se mudar no futuro)
  toSn(v: boolean | 'Sim' | 'Não' | null | undefined): 'Sim' | 'Não' {
    return v === true || v === 'Sim' ? 'Sim' : 'Não';
  }

  buscar(): void {
    this.erro = undefined;
    this.carregando = true;
    this.detalhe = undefined;
    this.resultados = [];

    const { tipo, valor } = this.form.getRawValue();
    const termo = (valor ?? '').trim();
    if (!termo) { this.carregando = false; return; }

    const obs: Observable<Credito[]> =
      tipo === 'nfse'
        ? this.api.porNfse(termo) // GET /api/creditos/{numeroNfse}
        : this.api.buscarPorNumero(termo).pipe(map(c => [c])); // GET /api/creditos/credito/{numeroCredito}

    obs
      .pipe(finalize(() => (this.carregando = false)))
      .subscribe({
        next: data => (this.resultados = data ?? []),
        error: (err) => {
          if (err?.status === 404) {
            this.erro = err?.error?.message ?? 'Crédito não encontrado.';
          } else if (err?.status === 0) {
            this.erro = 'Falha de conexão com a API. Ela está em execução?';
          } else {
            this.erro = 'Erro ao consultar.';
          }
        }
      });
  }

  verDetalhe(c: Credito): void {
    this.detalhe = c;
  }

  limpar(): void {
    this.form.reset({ tipo: 'nfse', valor: '' } as any);
    this.resultados = [];
    this.detalhe = undefined;
    this.erro = undefined;
  }
}
