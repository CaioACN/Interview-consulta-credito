@echo off
echo Iniciando aplicacao localmente...

echo.
echo 1. Iniciando banco de dados...
docker-compose up db -d

echo.
echo 2. Aguardando banco ficar pronto...
timeout /t 10 /nobreak

echo.
echo 3. Iniciando backend (porta 8080)...
start "Backend" cmd /k "cd consulta-creditos-api && gradlew bootRun"

echo.
echo 4. Aguardando backend ficar pronto...
timeout /t 15 /nobreak

echo.
echo 5. Iniciando frontend (porta 4200)...
start "Frontend" cmd /k "cd consulta-creditos-web && npm run start:local"

echo.
echo Aplicacao iniciada!
echo Backend: http://localhost:8080
echo Frontend: http://localhost:4200
echo Swagger: http://localhost:8080/swagger
echo API Docs: http://localhost:8080/v3/api-docs
pause
