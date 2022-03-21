FROM node:lts-alpine
WORKDIR usr/src/app
COPY bookwyrm_frontend/package*.json ./
RUN npm install -g http-server
COPY bookwyrm_frontend ./
RUN npm run build

EXPOSE 8080
CMD ["http-server", "dist"]



