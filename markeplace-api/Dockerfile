#FROM node:12
#
#WORKDIR /nft-ui
#COPY nft-ui /nft-ui
#WORKDIR /nft-ui
#RUN npm install
#RUN npm run dev


FROM maven:3.8.2-jdk-8
WORKDIR /marketplace-api
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run