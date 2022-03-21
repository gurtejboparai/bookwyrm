FROM node:lts-alpine
RUN mkdir -p /usr/app

WORKDIR /usr/app
COPY ./* /usr/app/

RUN npm install
RUN npm run build

EXPOSE 8080
CMD ["npm", "run", "serve"]



