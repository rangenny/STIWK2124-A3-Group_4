# Stage 1: Compile Angular code to static bundles
FROM node:20-alpine AS frontend-builder
WORKDIR /app

# Copy package configurations to cache dependencies
COPY package*.json ./
RUN npm ci

# Copy full application tree
COPY . .

# Run production build compilation 
RUN npm run build -- --configuration=production

# Stage 2: Production Web Proxy Server Delivery
FROM nginx:alpine
COPY --from=frontend-builder /app/dist/arl-frontend/browser /usr/share/nginx/html

# Expose web server layer port
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]