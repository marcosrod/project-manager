# Sobre a aplicacao

## Para iniciar a aplicacao
    A aplicacao esta utilizando: Maven, Spring Boot (3.3.0), Java 17

  1. Para iniciar as api's, sera necessario subir dois containers com Postgresql, para que entao seja possivel iniciar normalmente. Para iniciar os containers rode:
  
    docker run --name client-db -e POSTGRES_PASSWORD=clientdb -d -p 5436:5432 postgres
    docker run --name project-db -e POSTGRES_PASSWORD=projectdb -d -p 5437:5432 postgres

  OBS: EXISTE UM ARQUIVO SCHEMA.SQL EM CADA API QUE JA CRIA AUTOMATICAMENTE AS TABELAS DO BANCO NECESSARIAS PARA RODAR AS APLICACOES.
    
  2. Subir os dois servicos (client-api e project-api)

  3. Utilizar a collection do postman disponibilizada para realizar mais facilmente os requests:

     [Client-Project.postman_collection.json](https://github.com/user-attachments/files/15688598/Client-Project.postman_collection.json)

## Arquitetura

Foi utilizada a arquitetura de microservicos na construcao da aplicacao. Para tal, houve a divisao em dois servicos: client-api e project-api
Juntamente com o padrao MVC, estruturado em cada microservico, com as PRINCIPAIS camadas sendo: Controller, Service, Model, Repository.

  ### Client-api

    Contem a regra de negocio especifica dos Clientes
    Endpoints da API:
      - Cadastrar Cliente
      - Checar existencia de Cliente

  ### Project-api

    Contem a regra de negocio especifica dos Projetos, e portanto, contem tambem a regra de negocio das Tarefas
    Endpoints da API:
      - Cadastrar Projeto
      - Listar todos os projetos em aberto
      - Cadastrar Tarefa
      - Listar todas as tarefas por projeto

  ### Diagrama de Classes: ![Class-diagram-ag drawio](https://github.com/marcosrod/desafio-ag-capital/assets/49969076/1eb1c2b9-edb4-44d9-b0f6-9d1bc9f1f405)


  

