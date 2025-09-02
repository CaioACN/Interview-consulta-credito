# ===== Stage 1: build Angular =====
FROM node:20-alpine AS build
WORKDIR /app

COPY package*.json ./
RUN npm ci

COPY . .
# Gera a pasta dist/ (Angular 16+ gera dist/<nome-projeto>/browser)
RUN npm run build -- --configuration production

# ===== Stage 2: Nginx =====
FROM nginx:alpine

# copia o CONTEÚDO de dist/*/browser para a raiz do Nginx
COPY --from=build /app/dist/*/browser/ /usr/share/nginx/html/

# proxy para a API (arquivo precisa existir no projeto web)
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
