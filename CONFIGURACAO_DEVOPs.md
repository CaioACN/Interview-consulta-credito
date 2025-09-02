# Configuração DevOps - Consulta Créditos

## 🎯 Objetivo
Configuração parametrizada para evitar conflitos de porta entre Docker e execução local.

## 📋 Cenários de Execução

### (a) API no Docker + bootRun local
- **Backend Docker**: `http://localhost:8086` (porta parametrizada)
- **Backend Local**: `http://localhost:8080` (porta padrão)
- **Frontend**: `http://localhost:4200` (Angular dev server)

### (b) Tudo no Docker
- **Backend**: `http://localhost:8086`
- **Frontend**: `http://localhost:8081`
- **Database**: `localhost:5432`

### (c) Tudo local (sem Docker)
- **Backend**: `http://localhost:8080`
- **Frontend**: `http://localhost:4200`
- **Database**: `localhost:5432`

## ⚙️ Configurações

### Portas Configuradas
| Serviço | Local | Docker | Variável |
|---------|-------|--------|----------|
| Backend | 8080 | 8086 | `API_PORT` |
| Frontend | 4200 | 8081 | `WEB_PORT` |
| Database | 5432 | 5432 | `POSTGRES_PORT` |

### Variáveis de Ambiente
```bash
# Portas
API_PORT=8086
WEB_PORT=8081
POSTGRES_PORT=5432

# Banco de Dados
POSTGRES_DB=creditosdb
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres

# Desenvolvimento
API_PORT_DEV=8086
```

## 🚀 Como Executar

### Cenário (a): API Docker + Local
```bash
# 1. Iniciar banco e API no Docker
docker-compose up db consulta-creditos-api -d

# 2. Iniciar frontend local
cd consulta-creditos-web
npm run start:local
```

### Cenário (b): Tudo Docker
```bash
# Usar script automatizado
start-docker.bat

# Ou manualmente
docker-compose up --build
```

### Cenário (c): Tudo Local
```bash
# 1. Iniciar banco no Docker
docker-compose up db -d

# 2. Iniciar backend local
cd consulta-creditos-api
./gradlew bootRun

# 3. Iniciar frontend local
cd consulta-creditos-web
npm run start:local
```

## 🔧 Configurações Técnicas

### Docker Compose
- **API**: Porta parametrizada `${API_PORT:-8086}:8080`
- **Web**: Porta parametrizada `${WEB_PORT:-8081}:80`
- **DB**: Porta parametrizada `${POSTGRES_PORT:-5432}:5432`

### Spring Boot
- **Porta**: `${SERVER_PORT:8080}`
- **Datasource**: Via variáveis `SPRING_DATASOURCE_*`
- **Swagger**: `/swagger` e `/v3/api-docs`

### Angular Proxy
- **Docker**: `http://localhost:8086`
- **Local**: `http://localhost:8080`

### CORS
Permite origens:
- `http://localhost:4200` (Angular dev)
- `http://localhost:8081` (Docker web)
- `http://localhost:58229` (VS Code Live Server)

## ✅ Benefícios

1. **Sem conflitos de porta**: Docker usa 8086, local usa 8080
2. **Parametrizado**: Todas as portas via variáveis de ambiente
3. **Flexível**: Suporta todos os cenários de desenvolvimento
4. **CORS configurado**: Sem problemas de comunicação
5. **Swagger funcional**: Documentação acessível em ambos os cenários

## 🔍 URLs de Acesso

### Desenvolvimento Local
- Frontend: http://localhost:4200
- Backend: http://localhost:8080
- Swagger: http://localhost:8080/swagger

### Docker
- Frontend: http://localhost:8081
- Backend: http://localhost:8086
- Swagger: http://localhost:8086/swagger

### API Docs (ambos)
- OpenAPI JSON: http://localhost:8080/v3/api-docs (local)
- OpenAPI JSON: http://localhost:8086/v3/api-docs (docker)
