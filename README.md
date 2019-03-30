# scd-demo

[![Build Status](https://travis-ci.org/AllanMLeite/scd-demo.svg?branch=master)](https://travis-ci.org/AllanMLeite/scd-demo)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=AllanMLeite_scd-demo&metric=alert_status)](https://sonarcloud.io/dashboard?id=AllanMLeite_scd-demo)

Segue abaixo a minha solução para o teste prático:

A implementação foi realizada com base no conceito de TDD, utilizando as bibliotecas de teste junit e mockito, o que resultou em um código enxuto (sem desperdício) e limpo. As refatorações foram constantes pelo fato das melhorias terem sido incrementadas gradualmente, conforme a necessidade dos testes. Me preocupei, também, em manter cada classe somente com o código de sua responsabilidade. Criei domínios exclusivos por camada da aplicação, o que garante um isolamento entre as camadas, facilitando a adaptação à mudanças. Entretanto, é importante ressaltar que aplicando essa abordagem o número de classes de dominio cresce consideravelmente.

Procurei utilizar a filosofia do DDD para implementar domínios com comportamentos, como validações nos construtores e métodos que são de responsabilidade do domínio. Um dos benefícios dessa escolha é um design de código "null-safe", permitindo que o desenvolvedor codifique sem a necessidade de verificar se determinada propriedade do objeto é diferente de nula após a sua construção.

Para o armazenamento dos dados utilizei o Postgres juntamente com o liquibase como gerenciador dos scripts de banco de dados.

Com o intuito de criar uma solução "cloud native", tomei a liberdade de configurar uma integração contínua com o Travis. Configurei o pipeline para fazer o deploy de uma imagem Docker, o que traz uma maior facilidade ao escalar a estrutura. O pipeline pode ser visualizado no arquivo .travis.yml na raíz do projeto.

 -> Os logs gerados pelo travis durante a execução da integração contínua podem ser consultados pelo link: 
	https://travis-ci.org/AllanMLeite/scd-demo

 -> O preview da aplicação no heroku está disponível neste link: https://scd-demo.herokuapp.com

Qualquer dúvida ou comentário, estou à disposição.

Allan Moreira Leite

----------
Para rodar local, inicialize um container com postgres:

docker run --name scd-postgres -p 5432:5432 -e POSTGRES_PASSWORD=sa -d postgres
