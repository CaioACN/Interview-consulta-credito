@echo off
echo Iniciando aplicacao com Docker...

echo.
echo 1. Parando containers existentes...
docker-compose down

echo.
echo 2. Construindo e iniciando containers...
docker-compose up --build

echo.
echo Aplicacao iniciada!
echo Frontend: http://localhost:8081
echo Backend: http://localhost:8086
echo Swagger: http://localhost:8086/swagger
echo API Docs: http://localhost:8086/v3/api-docs
pause
