FROM node:lts-alpine

# Create app directory
WORKDIR /usr/src/app

RUN apk update && \
  apk upgrade && \
  adduser -D -u 1001 appuser

COPY package*.json /usr/src/app

# Install app dependencies

RUN npm install 

# Bundle app source
COPY . .
RUN npm run build

EXPOSE 4200
CMD [ "npm", "run", "start"]
