# scd-demo

[![Build Status](https://travis-ci.org/AllanMLeite/scd-demo.svg?branch=master)](https://travis-ci.org/AllanMLeite/scd-demo)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=AllanMLeite_scd-demo&metric=alert_status)](https://sonarcloud.io/dashboard?id=AllanMLeite_scd-demo)

 -> Os logs gerados pelo travis durante a execução da integração contínua podem ser consultados pelo link: 
	https://travis-ci.org/AllanMLeite/scd-demo

 -> O preview da aplicação no heroku está disponível neste link: https://scd-demo.herokuapp.com

----------
Para rodar local, inicialize um container com postgres:

docker run --name scd-postgres -p 5432:5432 -e POSTGRES_PASSWORD=sa -d postgres
