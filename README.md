# 🚀 Microservice de Propostas 💳

Este projeto é um desafio proposto pela time da Zup Academy para a turma do Orange Talents 5.

[![GitHub stars](https://img.shields.io/github/stars/matheuscarv69/orange-talents-05-template-proposta?color=orange)](https://github.com/matheuscarv69/orange-talents-05-template-proposta/stargazers)
![Maven Central with version prefix filter](https://img.shields.io/maven-central/v/org.apache.maven/apache-maven/3.6.3?color=orange)
![GitHub](https://img.shields.io/github/license/matheuscarv69/orange-talents-05-template-proposta?color=orange)

## 🤔 O que é o Microservice de Propostas?

Esse serviço é responsável por simular o fluxo de solicitação de cartões mediante uma proposta enviada pelo cliente,
isso é feito por meio de integrações com outros serviços externos.

Basicamente o fluxo principal de uma proposta consiste em:

- Receber a proposta e enviar para analíse dessa proposta em um sistema externo.
- Associação de um cartão, caso a proposta seja elegível de acordo com a regra de negócio do sistema externo.

## 🛠 Pré-Requisitos

### 📍 Local

Antes de começar a mexer no código você precisa ter instalado em sua máquina as seguintes ferramentas:

Essas são as ferramentas básicas, porém não irá conseguir rodar a aplicação somente com isso, pois ela se integra à
alguns serviços externos, esses descritos no Docker-compose do projeto.

- [Java JDK 11+](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
- [Maven 3.6.3](https://maven.apache.org/download.cgi)

### 🐳 Docker

Este projeto conta com um **docker-compose**, inclusive a própria imagem da aplicação já está configurada para ser
executada no docker. Os requisitos para isso são:

- [Docker](https://www.docker.com/products/docker-desktop) - Baixe de acordo com o seu SO
- [Docker-compose](https://docs.docker.com/compose/install/)

## 🎲 Executando a API com o docker-compose
Com esse repositório já clonado em sua máquina e com todos os pré-requisitos atendidos.

1. Você deve ir até a raiz do projeto onde o arquivo **docker-compose.yml** está.
2. Deve abrir um terminal na raiz do projeto.
3. Agora certifique-se que o seu Docker já está em execução.
4. Execute o seguinte comando no terminal:

```bash
docker-compose up -d
```

5. Com isso sua aplicação já está em execução

## 📝Fazendo requisições - Insomnia

Esse serviço tem alguns endpoints que estão configurados no aplicativo **Insomnia**, clicando no botão abaixo você pode
baixar o workspace de requests utilizados nesse projeto.
<br/>
<br/>
[![Run in Insomnia}](https://insomnia.rest/images/run.svg)](https://insomnia.rest/run/?label=&uri=https%3A%2F%2Fgist.githubusercontent.com%2Fmatheuscarv69%2Fab9bf5849f9351619fa2acaeeb1e1658%2Fraw%2F47ab2faca37c6dc43a7c896896c785246ffed337%2Fmicroservice-propostas.yaml)

## 🚀 Tecnologias 👩‍🚀

As seguintes tecnologias foram utilizadas e/ou utilizadas no desenvolvimento do projeto.

- Spring Boot 2.3.11
    - Web
    - Data JPA
    - Cloud Feign
    - Security OAuth2
    - Validation
    - Actuator
- Micrometer
- Jaeger
- Prometheus
- Postgres

## 👨🏻‍💻 Autor

<br>
<a href="https://github.com/matheuscarv69">
 <img style="border-radius: 35%;" src="https://avatars1.githubusercontent.com/u/55814214?s=460&u=ffb1e928527a55f53df6e0d323c2fd7ba92fe0c3&v=4" width="100px;" alt=""/>
 <br />
 <sub><b>Matheus Carvalho</b></sub></a> <a href="https://github.com/matheuscarv69" title="Matheus Carvalho">🚀</a>

Feito por Matheus Carvalho, entre em contato!✌🏻
 <p align="left">
    <a href="mailto:matheus9126@gmail.com" alt="Gmail" target="_blank">
      <img src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white&link=mailto:matheus9126@gmail.com"/></a>
    <a href="https://www.linkedin.com/in/matheus-carvalho69/" alt="Linkedin" target="_blank">
        <img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white&link=https://www.linkedin.com/in/matheus-carvalho69/"/></a>  
    <a href="https://www.instagram.com/_mmcarvalho/" alt="Instagram" target="_blank">
      <img src="https://img.shields.io/badge/Instagram-E4405F?style=for-the-badge&logo=instagram&logoColor=white&link=https://www.instagram.com/_mmcarvalho/"/></a>  
  </p>

## 📝 Licença
Este projeto esta sob a licença Apache-2.0 .
