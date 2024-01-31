# Stage 1: Build Angular app
FROM docker.io/node:20 AS builder

WORKDIR /app

COPY package.json package-lock.json ./
RUN npm install

COPY . .
RUN npm run build 

# Stage 2: Create NGINX image
FROM docker.io/nginx:1.21-alpine

# Copy the built artifacts from the previous stage
COPY --from=builder /app/dist/fe-app/browser /usr/share/nginx/html

EXPOSE 80

# Command to run NGINX
CMD ["nginx", "-g", "daemon off;"]
