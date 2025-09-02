# Configuração de Portas - Consulta Créditos

## Portas Configuradas

### Desenvolvimento Local
- **Backend (API)**: `http://localhost:8080`
- **Frontend (Angular)**: `http://localhost:4200`
- **Banco de Dados**: `localhost:5432`

### Docker
- **Backend (API)**: `http://localhost:8080`
- **Frontend (Nginx)**: `http://localhost:8081`
- **Banco de Dados**: `localhost:5432`

## Como Executar

### Desenvolvimento Local

1. **Iniciar o banco de dados:**
   ```bash
   docker-compose up db -d
   ```

2. **Iniciar o backend:**
   ```bash
   cd consulta-creditos-api
   ./gradlew bootRun
   ```

3. **Iniciar o frontend:**
   ```bash
   cd consulta-creditos-web
   npm run start:local
   ```

### Docker Completo

```bash
docker-compose up --build
```

## Configurações de Proxy

- **proxy.conf.json**: Configuração padrão (porta 8080)
- **proxy.local.conf.json**: Para desenvolvimento local (porta 8080)
- **proxy.docker.conf.json**: Para ambiente Docker (porta 8080)

## URLs de Acesso

- **Frontend Local**: http://localhost:4200
- **Frontend Docker**: http://localhost:8081
- **API Swagger UI**: http://localhost:8080/swagger-ui
- **API Docs**: http://localhost:8080/v3/api-docs
- **API OpenAPI JSON**: http://localhost:8080/v3/api-docs
