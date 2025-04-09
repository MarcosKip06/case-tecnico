# 🔗 Integração com HubSpot via OAuth 2.0

Este projeto é uma aplicação Spring Boot que realiza integração com a API do HubSpot utilizando OAuth 2.0.

## 🚀 Funcionalidades

- Geração da URL de autorização para OAuth.
- Callback para troca de código por token.
- Armazenamento dos tokens em banco de dados.
- Criação de contatos na API CRM do HubSpot.
- Recebimento de Webhooks de criação de contatos.

---

## 🧑‍💻 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Security
- Spring Data JPA
- H2 Database (banco de testes)
- RestTemplate (para chamadas HTTP)

---

## 🛠️ Como executar o projeto

### Pré-requisitos

- Java 17
- Maven 3.8+
- Criar uma conta de desenvolvedor e uma de testes no HubSpot: HubSpot Developer Account

### Passo a passo

1. Clone o repositório:
   ```bash
   git clone https://github.com/MarcosKip06/case-tecnico.git


📄 Documentação Técnica
✔️ Decisões Tomadas

    Spring Boot como framework principal
    Escolhi o Spring Boot por ser um framework robusto, com ótima integração a bibliotecas de segurança, autenticação e consumo de APIs externas. Além disso, ele facilita bastante a configuração e acelera o desenvolvimento.

    Uso de DTOs (Data Transfer Objects)
    Optei por utilizar DTOs para fazer a separação entre os dados recebidos do cliente e as entidades do sistema. Isso ajuda a evitar vazamento de informações sensíveis e garante mais controle sobre a validação dos dados.

    Estrutura em camadas (Controller, Service, Config, DTO)
    Organizei o projeto em camadas para manter uma boa separação de responsabilidades. Essa abordagem facilita a manutenção, leitura do código e criação de testes.

    Uso de RestTemplate
    Usei o RestTemplate para consumir a API do HubSpot, por ser uma solução simples e que atende bem à necessidade do projeto no momento.

    Fluxo OAuth 2.0
    Implementei o fluxo de autenticação seguindo as boas práticas da própria HubSpot: geração da URL de autorização, tratamento do callback e armazenamento seguro dos tokens de acesso.

    Listener para Webhooks
    Criei um endpoint específico para escutar eventos disparados pela HubSpot, como a criação de contatos. Essa abordagem facilita o tratamento assíncrono desses dados e mantém o sistema mais desacoplado.

🔧 Bibliotecas Utilizadas e Motivações
- Biblioteca	Uso Principal
- Spring Boot	Framework base para criação da API REST
- Spring Security	Controle de segurança nos endpoints e preparação para ambientes de produção
- RestTemplate/WebClient	Comunicação com a API externa do HubSpot
- H2 Database	Banco de dados em memória, usado em testes e para armazenamento simples
   


