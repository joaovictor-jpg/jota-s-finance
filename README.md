# Jota's Financias - Backend

Este repositório contém o **backend** da aplicação **Jota's Financias**, uma API RESTful completa para gerenciamento de finanças pessoais. Ela é responsável por toda a lógica de negócio, segurança e persistência dos dados.

> **Frontend:** O projeto frontend, construído com Next.js, está disponível no repositório: **[joaovictor-jpg/financias](https://www.google.com/search?q=https://github.com/joaovictor-jpg/financias)**

## 🚀 Sobre a API

A API foi desenvolvida em **Java 21** com o framework **Spring Boot**, seguindo as melhores práticas de desenvolvimento para criar um serviço robusto, seguro e escalável. Ela oferece endpoints para todas as operações essenciais de um aplicativo financeiro.

### ✨ Funcionalidades Principais

* **Autenticação e Autorização com JWT:** Sistema de login e cadastro seguro utilizando Spring Security. O acesso aos endpoints é protegido por tokens JWT, garantindo que apenas usuários autenticados possam acessar seus próprios dados.
* **Gerenciamento de Contas Bancárias:** Endpoints para criar e deletar contas bancárias associadas a um usuário.
* **Controle de Orçamentos (Budgets):** API para criar, listar, atualizar e deletar orçamentos mensais por categoria.
* **Definição de Metas (Goals):** Operações de CRUD para que os usuários possam criar e acompanhar suas metas financeiras.
* **Registro de Transações:** Endpoints para registrar, listar e deletar transações de receita e despesa.
* **Categorização:** Permite que os usuários criem e gerenciem suas próprias categorias de transações.
* **Validação de Dados:** Utiliza Jakarta Bean Validation para garantir a integridade dos dados recebidos nas requisições.

## 🛠️ Tecnologias Utilizadas

* **[Java 21](https://www.oracle.com/java/)**: Linguagem de programação principal.
* **[Spring Boot 3](https://spring.io/projects/spring-boot)**: Framework para criação da API.
* **[Spring Security](https://spring.io/projects/spring-security)**: Para autenticação e autorização via JWT.
* **[Spring Data JPA](https://spring.io/projects/spring-data-jpa)**: Para persistência de dados e abstração do acesso ao banco.
* **[PostgreSQL](https://www.postgresql.org/)**: Banco de dados relacional.
* **[Flyway](https://flywaydb.org/)**: Ferramenta para versionamento e migração de schema do banco de dados.
* **[Docker & Docker Compose](https://www.docker.com/)**: Para containerização da aplicação e do banco de dados, facilitando a configuração do ambiente de desenvolvimento.
* **[Maven](https://maven.apache.org/)**: Gerenciador de dependências e build do projeto.

## ⚙️ Começando

A maneira mais simples de executar o backend é utilizando Docker.

### Pré-requisitos

* **JDK 21** ou superior
* **Maven**
* **Docker** e **Docker Compose**

### Instalação e Execução com Docker

1.  **Clone o repositório:**

    ```bash
    git clone https://github.com/joaovictor-jpg/jota-s-finance.git
    cd jota-s-finance
    ```

2.  **Crie o arquivo de variáveis de ambiente:**
    Na raiz do projeto, crie um arquivo chamado `.env`. Ele será usado pelo `docker-compose.yml` para configurar os containers.

    ```env
    DB_USER=admin
    DB_PASSWORD=admin
    DB_NAME=finance
    app_jwt_secret=sua-chave-secreta-para-jwt
    ```

    > **Atenção:** A chave `app_jwt_secret` é fundamental para a segurança da aplicação. Utilize um valor longo e aleatório. Este mesmo valor precisará ser configurado no frontend.

3.  **Inicie os containers:**
    Execute o comando abaixo na raiz do projeto. Ele irá construir a imagem da aplicação e iniciar os containers do backend e do banco de dados PostgreSQL.

    ```bash
    docker-compose up --build
    ```

    Após a conclusão, a API estará rodando e acessível em `http://localhost:8080`.

## 🗄️ Banco de Dados

O projeto utiliza **Flyway** para gerenciar as migrações do banco de dados. Os scripts de criação e alteração das tabelas estão localizados em `src/main/resources/db/migration`. Ao iniciar a aplicação, o Flyway aplicará automaticamente as migrações pendentes.

As principais tabelas são:

* `users`
* `goals`
* `bank_accounts`
* `categories`
* `budgets`
* `transactions`

## 📄 Licença

Este projeto está licenciado sob a Licença Apache 2.0. Veja o arquivo [LICENSE](https://www.google.com/search?q=LICENSE) para mais detalhes.