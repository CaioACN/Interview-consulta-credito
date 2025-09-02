#!/bin/bash

echo "Iniciando aplicacao localmente..."

echo ""
echo "1. Iniciando banco de dados..."
docker-compose up db -d

echo ""
echo "2. Aguardando banco ficar pronto..."
sleep 10

echo ""
echo "3. Iniciando backend..."
cd consulta-creditos-api
./gradlew bootRun &
BACKEND_PID=$!

echo ""
echo "4. Aguardando backend ficar pronto..."
sleep 15

echo ""
echo "5. Iniciando frontend..."
cd ../consulta-creditos-web
npm run start:local &
FRONTEND_PID=$!

echo ""
echo "Aplicacao iniciada!"
echo "Backend: http://localhost:8080"
echo "Frontend: http://localhost:4200"
echo "Swagger UI: http://localhost:8080/swagger-ui"
echo "API Docs: http://localhost:8080/v3/api-docs"
echo ""
echo "Pressione Ctrl+C para parar os servicos"

# Função para limpar processos ao sair
cleanup() {
    echo "Parando servicos..."
    kill $BACKEND_PID 2>/dev/null
    kill $FRONTEND_PID 2>/dev/null
    docker-compose down
    exit 0
}

trap cleanup SIGINT SIGTERM

# Aguardar indefinidamente
wait
