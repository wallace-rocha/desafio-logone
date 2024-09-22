Controle de Agendamentos de Vagas

Este projeto é um sistema para gerenciar agendamentos de vagas, com funcionalidades de cadastro de vagas, solicitantes e agendamentos, além da consulta de agendamentos por período e por solicitante.

Requisitos:
- Java 17
- Maven
- Banco de dados HSQLDB ou H2 (alternativo)

Download do Banco de Dados:
- HSQLDB: para instalar o HSQLDB acesse o site https://sourceforge.net/projects/hsqldb/files/hsqldb/hsqldb_2_5/. Baixe a versão 2.5.1. Após isso, descompacte o arquivo em alguma pasta do computador.

Execução:

- Criar as pastas para o banco de dados C:\logone\database\agendamento no seu sistema.
- Acesse a pasta do projeto.
- Execute o comando: mvn clean.
- Execute o comando: mvn install -DskipTests.
- Execute o comando: java -jar target/Teste-Pratico-Desenvolvedor-Java-0.0.2-SNAPSHOT.jar.
- Em caso do banco de dados ser o HSQLDB execute o comando na raiz de onde foi descompactado: java -cp ./lib/hsqldb.jar org.hsqldb.util.DatabaseManagerSwing. Ao abrir o HSQL Database Manager, defina o Type como HSQL Database Engine Standalone, a URL jdbc:hsqldb:file:C:\logone\database\agendamento, o User: sa e sem password.
- Em caso do banco de dados ser o H2: edite o application.properties revomendo os comentários das configurações do banco H2 e comentando as configurações do HSQLDB. Após isso no seu navegador digite localhost:9494/h2-console, ao abrir o console digite no campo URL jdbc:h2:C:/logone/database/agendamento, o User: sa e sem password.
- Acesse a aplicação em http://localhost:9494.

Funcionalidades:

Cadastro de vagas:
- Cadastra novas vagas com data de início, data de término e quantidade de vagas disponíveis.

Cadastro de solicitantes:
- Cadastra novos solicitantes.

Cadastro de agendamentos:
- Agenda vaga para um solicitante, informando a data do agendamento, um número do agendamento, a motivação do agendamento e o código do solicitante.

Consulta de agendamentos:
- Consulta todos os agendamentos realizados em um determinado período, filtrando por solicitante.
- Visualiza o total de agendamentos realizados por cada solicitante, a quantidade de vagas disponíveis e a porcentagem de vagas utilizadas.

Validações:

Cadastro de Vagas:
- Não permite o cadastro de vagas com datas retroativas.
- Não permite inserir quantidade com valor negativo ou zero.

Cadastro de Agendamentos:
- Não permite o cadastro de agendamento com datas retroativas.
- Não permite o cadastro de agendamento para solicitantes não cadastrados.
- Limita o número de agendamentos para um mesmo solicitante em um determinado período a 25% da quantidade de vagas disponíveis.

Consulta de agendamentos:
- Não permite o consulta de agendamento com data inicial posterior a data final.

Campos obrigatórios:
- Todos os campos são obrigatórios.

Arquitetura:
- O projeto utiliza o Spring Boot como framework de desenvolvimento. O acesso ao banco de dados é feito através do JPA e Hibernate. A interface do usuário é construída com JSF e Primefaces.
