FROM node:lts-alpine

# Create app directory
WORKDIR /usr/src/app

COPY package*.json ./

# Install app dependencies

RUN npm install 

# Bundle app source
COPY . .
RUN npm run build

EXPOSE 4200
CMD [ "npm", "run", "start"]