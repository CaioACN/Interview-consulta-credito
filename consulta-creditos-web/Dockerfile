# ===== Stage 1: build Angular =====
FROM node:18-alpine AS build
WORKDIR /app

COPY package*.json ./
RUN npm ci

COPY . .
RUN npm run build -- --configuration production

# ===== Stage 2: Nginx =====
FROM nginx:alpine
# copia o CONTEÚDO de dist/*/browser para a raiz do Nginx
COPY --from=build /app/dist/*/browser/ /usr/share/nginx/html/
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
