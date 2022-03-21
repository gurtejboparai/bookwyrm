FROM node:lts-alpine
WORKDIR /app
COPY bookwyrm_frontend/package.json ./
RUN npm install
COPY bookwyrm_frontend ./
RUN npm run build

EXPOSE 8080
CMD ["npm", "run", "serve"]



