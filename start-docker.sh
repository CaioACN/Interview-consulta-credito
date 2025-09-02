#!/bin/bash

echo "Iniciando aplicacao com Docker..."

echo ""
echo "1. Parando containers existentes..."
docker-compose down

echo ""
echo "2. Construindo e iniciando containers..."
docker-compose up --build

echo ""
echo "Aplicacao iniciada!"
echo "Frontend: http://localhost:8081"
echo "Backend: http://localhost:8080"
echo "Swagger UI: http://localhost:8080/swagger-ui"
echo "API Docs: http://localhost:8080/v3/api-docs"
