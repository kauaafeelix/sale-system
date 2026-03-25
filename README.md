# 🛒 Sistema de Vendas API

API REST desenvolvida em **Java 21 com Spring Boot 3** para gerenciamento completo de um sistema de vendas. A aplicação permite o controle de **usuários, clientes, produtos, pedidos e itens de pedido**, com **autenticação e autorização via JWT**, garantindo acesso seguro e baseado em papéis (roles) aos recursos da API.

O projeto segue uma organização em camadas inspirada em **Clean Architecture**, separando domínio, aplicação, infraestrutura e interface da API, facilitando manutenção e escalabilidade.

---

## 📋 Índice

- [Funcionalidades](#-funcionalidades)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura do Projeto](#-arquitetura-do-projeto)
- [Estrutura de Pastas](#-estrutura-de-pastas)
- [Modelo de Dados](#-modelo-de-dados)
- [Como Executar](#-como-executar)
- [Variáveis de Ambiente](#-variáveis-de-ambiente)
- [Endpoints da API](#-endpoints-da-api)
- [Segurança e Autenticação](#-segurança-e-autenticação)
- [Objetivo do Projeto](#-objetivo-do-projeto)

---

## ✅ Funcionalidades

- Autenticação e autorização com **JWT** (JSON Web Token)
- Cadastro e gerenciamento de **usuários** (acesso restrito ao ADMIN)
- Cadastro e gerenciamento de **clientes**
- Cadastro e gerenciamento de **produtos** com controle de estoque
- Criação e gerenciamento de **pedidos**
- Gerenciamento de **itens de pedido** com cálculo de valor total
- Controle de **estoque** com validação de quantidade
- Criptografia de senhas com **BCrypt**
- Controle de acesso por **papéis (roles)**: `ADMIN` e `VENDEDOR`

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|---|---|---|
| **Java** | 21 | Linguagem principal |
| **Spring Boot** | 3.5.11 | Framework principal |
| **Spring Security** | 3.5.11 | Autenticação e autorização |
| **Spring Data JPA** | 3.5.11 | Persistência de dados |
| **JJWT** | 0.11.5 | Geração e validação de tokens JWT |
| **PostgreSQL** | — | Banco de dados relacional |
| **Hibernate** | — | ORM (via Spring Data JPA) |
| **Lombok** | — | Redução de código boilerplate |
| **Jakarta Validation** | — | Validação de dados de entrada |
| **SpringDoc OpenAPI** | 2.8.16 | Documentação automática (Swagger UI) |
| **Maven** | 3.11.0 | Gerenciador de dependências e build |
| **Docker** | — | Containerização do banco de dados |

---

## 🏗️ Arquitetura do Projeto

O projeto segue o padrão de **Arquitetura em Camadas** (inspirada em Clean Architecture), com responsabilidades bem definidas:

```
┌──────────────────────────────────────────────────┐
│                   WEB LAYER                      │
│  Controllers REST  │  Exception Advice           │
└──────────────────────┬───────────────────────────┘
                       │
┌──────────────────────▼───────────────────────────┐
│              APPLICATION LAYER                   │
│  Services (interfaces + implementações)          │
│  DTOs (Request / Response)  │  Mappers           │
└──────────────────────┬───────────────────────────┘
                       │
┌──────────────────────▼───────────────────────────┐
│                DOMAIN LAYER                      │
│  Entidades JPA  │  Enums  │  Repositórios        │
└──────────────────────┬───────────────────────────┘
                       │
┌──────────────────────▼───────────────────────────┐
│            INFRASTRUCTURE LAYER                  │
│  Spring Security  │  JWT  │  Exceções customizadas│
└──────────────────────────────────────────────────┘
```

**Descrição das camadas:**

| Camada | Pacote | Responsabilidade |
|---|---|---|
| **web** | `web/controller`, `web/advice` | Controllers REST e tratamento global de exceções |
| **application** | `application/service`, `application/dto`, `application/mapper` | Lógica de negócio, DTOs e mapeamento |
| **domain** | `domain/entity`, `domain/repository` | Entidades JPA, enums e interfaces de repositório |
| **infra** | `infra/secutiry`, `infra/exception` | Segurança (JWT, filtros), exceções customizadas |

---

## 📁 Estrutura de Pastas

```
gestaovendas/
└── src/
    ├── main/
    │   ├── java/com/weg/centroweg/gestaovendas/
    │   │   ├── SistemaDeVendasApplication.java
    │   │   ├── application/
    │   │   │   ├── dto/
    │   │   │   │   ├── auth/          # LoginRequest, RegisterRequest, AuthResponse
    │   │   │   │   ├── cliente/       # ClienteRequestDto, ClienteResponseDto
    │   │   │   │   ├── itempedido/    # ItemPedidoRequestDto, ItemPedidoResponseDto
    │   │   │   │   ├── pedido/        # PedidoRequestDto, PedidoResponseDto
    │   │   │   │   ├── produto/       # ProdutoRequestDto, ProdutoResponseDto, AtualizaEstoqueRequestDto
    │   │   │   │   └── usuario/       # UsuarioRequestDto, UsuarioResponseDto
    │   │   │   ├── mapper/            # Conversão Entidade ↔ DTO
    │   │   │   └── service/
    │   │   │       ├── contracts/     # Interfaces dos serviços
    │   │   │       └── impl/          # Implementações dos serviços
    │   │   ├── domain/
    │   │   │   ├── entity/            # Entidades JPA
    │   │   │   │   └── enums/         # RoleUsuario, StatusPedido
    │   │   │   └── repository/        # Repositórios Spring Data JPA
    │   │   ├── infra/
    │   │   │   ├── exception/         # BusinessException
    │   │   │   └── secutiry/
    │   │   │       ├── config/        # SecurityConfig
    │   │   │       ├── filter/        # JwtAuthenticationFilter
    │   │   │       ├── jwt/           # JwtService
    │   │   │       └── service/       # UserDetailsServiceImpl
    │   │   └── web/
    │   │       ├── advice/            # ApiExceptionHandler
    │   │       └── controller/        # AuthController, UsuarioController, etc.
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/...
```

---

## 🗄️ Modelo de Dados

### Diagrama de Entidades

```
┌───────────────┐        ┌──────────────────┐        ┌──────────────┐
│   Usuario     │1      *│     Pedido       │*      1│   Cliente    │
├───────────────┤────────├──────────────────┤────────├──────────────┤
│ id (UUID)     │        │ id (UUID)        │        │ id (UUID)    │
│ nome          │        │ dataPedido       │        │ nome         │
│ email (único) │        │ valorTotal       │        │ email (único)│
│ senha         │        │ status (enum)    │        │ telefone     │
│ role (enum)   │        │ cliente_id (FK)  │        └──────────────┘
│ dataCriacao   │        │ usuario_id (FK)  │
│ criadoPor(FK) │        └────────┬─────────┘
└───────────────┘                 │ 1
                                  │
                                  │ *
                         ┌────────┴─────────┐        ┌──────────────┐
                         │   ItemPedido     │*      1│   Produto    │
                         ├──────────────────┤────────├──────────────┤
                         │ id (UUID)        │        │ id (UUID)    │
                         │ quantidade       │        │ nome         │
                         │ precoUnitario    │        │ descricao    │
                         │ pedido_id (FK)   │        │ preco        │
                         │ produto_id (FK)  │        │ estoque      │
                         └──────────────────┘        └──────────────┘
```

### Enumerações

| Enum | Valores |
|---|---|
| `RoleUsuario` | `ADMIN`, `VENDEDOR` |
| `StatusPedido` | `PENDENTE`, `CANCELADO`, `ENVIADO` |

---

## 🚀 Como Executar

### Pré-requisitos

- **Java 21** ou superior
- **Maven 3.8+**
- **Docker** (para o banco de dados PostgreSQL)

### 1. Clone o repositório

```bash
git clone https://github.com/kauaafeelix/sale-system.git
cd sale-system
```

### 2. Suba o banco de dados com Docker

```bash
docker run --name vendas-db \
  -e POSTGRES_DB=vendas_db \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=1234 \
  -p 5432:5432 \
  -d postgres
```

### 3. Configure as propriedades da aplicação

Edite o arquivo `gestaovendas/src/main/resources/application.properties` com suas credenciais:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/vendas_db
spring.datasource.username=admin
spring.datasource.password=SUA_SENHA

jwt.secret=SEU_JWT_SECRET
jwt.expiration=86400000
```

### 4. Execute a aplicação

```bash
cd gestaovendas
./mvnw spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

A documentação Swagger estará disponível em: `http://localhost:8080/swagger-ui.html`

---

## ⚙️ Variáveis de Ambiente

| Propriedade | Descrição | Exemplo |
|---|---|---|
| `spring.datasource.url` | URL de conexão com o PostgreSQL | `jdbc:postgresql://localhost:5432/vendas_db` |
| `spring.datasource.username` | Usuário do banco de dados | `admin` |
| `spring.datasource.password` | Senha do banco de dados | `sua_senha_segura` |
| `jwt.secret` | Chave secreta para assinar tokens JWT | string aleatória longa |
| `jwt.expiration` | Tempo de expiração do token em milissegundos | `86400000` (24h) |

> ⚠️ **Atenção:** Nunca exponha credenciais reais em repositórios públicos. Utilize variáveis de ambiente ou um cofre de segredos em ambientes de produção.

---

## 📡 Endpoints da API

Todos os endpoints (exceto `/auth/**`) exigem o envio do token JWT no header:

```
Authorization: Bearer <seu_token>
```

### 🔐 Autenticação — `/auth`

| Método | Rota | Acesso | Descrição |
|---|---|---|---|
| `POST` | `/auth/login` | Público | Realiza login e retorna um token JWT |
| `POST` | `/auth/register` | Público | Registra novo usuário e retorna um token JWT |

**Body — Login (`/auth/login`):**
```json
{
  "email": "usuario@email.com",
  "senha": "minhasenha"
}
```

**Body — Registro (`/auth/register`):**
```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "minhasenha",
  "role": "VENDEDOR"
}
```

**Resposta — `AuthResponse`:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

### 👤 Usuários — `/usuarios` *(apenas ADMIN)*

| Método | Rota | Acesso | Descrição |
|---|---|---|---|
| `POST` | `/usuarios` | ADMIN | Cria um novo usuário |
| `GET` | `/usuarios` | ADMIN | Lista todos os usuários |
| `GET` | `/usuarios/{id}` | ADMIN | Busca usuário por ID |
| `PUT` | `/usuarios/{id}` | ADMIN | Atualiza dados do usuário |
| `DELETE` | `/usuarios/{id}` | ADMIN | Remove um usuário |

**Body — `UsuarioRequestDto`:**
```json
{
  "nome": "Maria Souza",
  "email": "maria@email.com",
  "senha": "senha123",
  "role": "VENDEDOR"
}
```

---

### 🧑‍🤝‍🧑 Clientes — `/clientes`

| Método | Rota | Acesso | Descrição |
|---|---|---|---|
| `POST` | `/clientes` | ADMIN, VENDEDOR | Cadastra um novo cliente |
| `GET` | `/clientes` | Autenticado | Lista todos os clientes |
| `GET` | `/clientes/{id}` | Autenticado | Busca cliente por ID |
| `PUT` | `/clientes/{id}` | Autenticado | Atualiza dados do cliente |
| `DELETE` | `/clientes/{id}` | Autenticado | Remove um cliente |

**Body — `ClienteRequestDto`:**
```json
{
  "nome": "Carlos Lima",
  "email": "carlos@email.com",
  "telefone": "(11) 99999-0000"
}
```

---

### 📦 Produtos — `/produtos`

| Método | Rota | Acesso | Descrição |
|---|---|---|---|
| `POST` | `/produtos` | ADMIN | Cadastra um novo produto |
| `GET` | `/produtos` | Autenticado | Lista todos os produtos |
| `GET` | `/produtos/id/{id}` | Autenticado | Busca produto por ID |
| `GET` | `/produtos/nome/{nome}` | Autenticado | Busca produtos por nome (parcial) |
| `PATCH` | `/produtos/{id}` | Autenticado | Atualiza o estoque do produto |
| `DELETE` | `/produtos/{id}` | Autenticado | Remove um produto |

**Body — `ProdutoRequestDto`:**
```json
{
  "nome": "Notebook Dell",
  "descricao": "Notebook com processador Intel i7",
  "preco": 4500.00,
  "estoque": 10
}
```

**Body — `AtualizaEstoqueRequestDto`:**
```json
{
  "estoque": 25
}
```

---

### 🧾 Pedidos — `/pedidos`

| Método | Rota | Acesso | Descrição |
|---|---|---|---|
| `POST` | `/pedidos` | VENDEDOR | Cria um novo pedido |
| `GET` | `/pedidos` | Autenticado | Lista todos os pedidos |
| `GET` | `/pedidos/{id}` | Autenticado | Busca pedido por ID |
| `GET` | `/pedidos/clientes/{id}` | Autenticado | Lista pedidos de um cliente |
| `GET` | `/pedidos/usuarios/{id}` | Autenticado | Lista pedidos de um usuário |
| `PUT` | `/pedidos/{id}` | Autenticado | Cancela um pedido |

**Body — `PedidoRequestDto`:**
```json
{
  "clienteId": "uuid-do-cliente",
  "usuarioId": "uuid-do-usuario"
}
```

---

### 🧩 Itens de Pedido — `/itens`

| Método | Rota | Acesso | Descrição |
|---|---|---|---|
| `POST` | `/itens` | ADMIN, VENDEDOR | Adiciona um item a um pedido |
| `GET` | `/itens` | Autenticado | Lista todos os itens de pedidos |
| `GET` | `/itens/{id}` | Autenticado | Busca item por ID |
| `PUT` | `/itens/{id}` | Autenticado | Atualiza um item de pedido |
| `DELETE` | `/itens/{id}` | Autenticado | Remove um item de pedido |

**Body — `ItemPedidoRequestDto`:**
```json
{
  "pedidoId": "uuid-do-pedido",
  "produtoId": "uuid-do-produto",
  "quantidade": 2
}
```

---

## 🔒 Segurança e Autenticação

A aplicação utiliza **Spring Security** com autenticação **stateless** baseada em **JWT**.

### Fluxo de autenticação

```
Cliente → POST /auth/login → [Spring Security valida credenciais]
       ← token JWT ←

Cliente → GET /produtos (Authorization: Bearer <token>)
       → [JwtAuthenticationFilter valida o token]
       → [SecurityContext populado com usuário autenticado]
       ← resposta ←
```

### Papéis (Roles)

| Role | Permissões |
|---|---|
| **ADMIN** | Acesso total: gerenciar usuários, produtos, clientes, pedidos e itens |
| **VENDEDOR** | Criar pedidos, gerenciar clientes e itens de pedido, consultar produtos |

### Detalhes do Token JWT

| Propriedade | Valor padrão |
|---|---|
| Algoritmo | HS256 (HMAC-SHA256) |
| Expiração | 86400000 ms (24 horas) |
| Header | `Authorization: Bearer <token>` |

As senhas são armazenadas de forma segura utilizando o algoritmo de hash **BCrypt**.

---

## 🎯 Objetivo do Projeto

Este projeto tem como objetivo praticar o desenvolvimento de **APIs REST com Spring Boot**, aplicando boas práticas de:

- **Arquitetura em camadas** (inspirada em Clean Architecture)
- **Segurança** com Spring Security e JWT
- **Persistência** com Spring Data JPA e PostgreSQL
- **Organização de código** com separação de responsabilidades (controllers, services, repositories, DTOs)
- **Validação de dados** com Jakarta Bean Validation
- **Controle de acesso** baseado em papéis (RBAC)

> 📌 Projeto desenvolvido com fins de aprendizado e aprimoramento de habilidades em desenvolvimento Back-End com o ecossistema Spring.
