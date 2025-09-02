# Configuração do Swagger/OpenAPI

## 📚 Visão Geral

O projeto utiliza o **SpringDoc OpenAPI** para gerar automaticamente a documentação da API RESTful. Esta configuração oferece uma interface interativa e completa para testar e entender os endpoints disponíveis.

## 🚀 URLs de Acesso

### Swagger UI (Interface Interativa)
- **Local**: http://localhost:8080/swagger-ui
- **Docker**: http://localhost:8080/swagger-ui

### OpenAPI JSON (Documentação Estruturada)
- **Local**: http://localhost:8080/v3/api-docs
- **Docker**: http://localhost:8080/v3/api-docs

## ⚙️ Configurações Implementadas

### 1. Dependências
```gradle
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0'
```

### 2. Configurações do Application Properties
```properties
# SpringDoc / Swagger
springdoc.api-docs.enabled=true
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.enabled=true
springdoc.swagger-ui.path=/swagger-ui

# Personalização da Interface
springdoc.swagger-ui.operations-sorter=method
springdoc.swagger-ui.tags-sorter=alpha
springdoc.swagger-ui.try-it-out-enabled=true
springdoc.swagger-ui.filter=true
springdoc.swagger-ui.deep-linking=true
springdoc.swagger-ui.display-request-duration=true
springdoc.swagger-ui.doc-expansion=none

# Tema e Cores
springdoc.swagger-ui.syntax-highlight.theme=monokai
springdoc.swagger-ui.navbar-bg=#2c3e50
springdoc.swagger-ui.navbar-text-color=#ffffff
springdoc.swagger-ui.primary-color=#3498db

# Segurança
springdoc.swagger-ui.csrf.enabled=false
springdoc.swagger-ui.csrf.use-ignore-pattern=true
```

### 3. Configuração Java (SwaggerConfig.java)
- Informações da API (título, descrição, versão)
- Informações de contato e licença
- Configuração de servidores
- Esquemas de segurança (JWT Bearer)
- Tags personalizadas para organização

## 🎯 Funcionalidades Disponíveis

### Interface do Swagger UI
- ✅ **Try it out**: Teste direto dos endpoints
- ✅ **Filtros**: Busca por operações e tags
- ✅ **Exemplos**: Respostas de exemplo para cada endpoint
- ✅ **Validação**: Verificação automática de parâmetros
- ✅ **Documentação**: Descrições detalhadas de cada operação

### Endpoints Documentados
1. **GET /api/creditos/{numeroNfse}**
   - Lista créditos por NFS-e
   - Validação: apenas números
   - Exemplos de resposta incluídos

2. **GET /api/creditos/credito/{numeroCredito}**
   - Busca crédito por número
   - Tratamento de erros 404
   - Exemplos de resposta incluídos

## 🔧 Personalizações Implementadas

### Cores e Tema
- **Navbar**: Azul escuro (#2c3e50)
- **Texto**: Branco (#ffffff)
- **Primária**: Azul (#3498db)
- **Syntax Highlight**: Tema Monokai

### Organização
- **Operações**: Ordenadas por método HTTP
- **Tags**: Ordenadas alfabeticamente
- **Expansão**: Documentos fechados por padrão
- **Duração**: Exibe tempo de resposta das requisições

### Segurança
- **CSRF**: Desabilitado para facilitar testes
- **CORS**: Configurado para desenvolvimento
- **JWT**: Esquema de autenticação configurado

## 📱 Responsividade

A interface do Swagger UI é totalmente responsiva e funciona bem em:
- ✅ Desktop
- ✅ Tablet
- ✅ Mobile

## 🚨 Solução de Problemas

### Swagger não carrega
1. Verificar se a aplicação está rodando na porta 8080
2. Verificar logs de erro no console
3. Confirmar se as dependências foram baixadas

### Endpoints não aparecem
1. Verificar se os controllers estão anotados corretamente
2. Confirmar se o scan de pacotes está correto
3. Verificar se não há erros de compilação

### Problemas de CORS
1. Verificar configuração do CorsConfig
2. Confirmar se as origens estão corretas
3. Verificar se o frontend está na porta correta

## 🔗 Links Úteis

- **SpringDoc Documentation**: https://springdoc.org/
- **OpenAPI Specification**: https://swagger.io/specification/
- **Swagger UI**: https://swagger.io/tools/swagger-ui/
