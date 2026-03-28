<h1 align="center">🛒 Sistema de Vendas API</h1>

<p align="center">
  <strong>API REST completa para gerenciamento de vendas, construída com Java 21 e Spring Boot 3</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21"/>
  <img src="https://img.shields.io/badge/Spring_Boot-3.5.11-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Spring_Security-JWT-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white" alt="Spring Security"/>
  <img src="https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL"/>
  <img src="https://img.shields.io/badge/Docker-Ready-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker"/>
  <img src="https://img.shields.io/badge/Maven-3.11.0-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven"/>
  <img src="https://img.shields.io/badge/Swagger-UI-85EA2D?style=for-the-badge&logo=swagger&logoColor=black" alt="Swagger"/>
</p>

<p align="center">
  A aplicação oferece controle completo de <strong>usuários, clientes, produtos, pedidos e itens de pedido</strong>, com autenticação e autorização via <strong>JWT</strong> e controle de acesso baseado em papéis (RBAC).
</p>

---

## 📋 Índice

- [✨ Funcionalidades](#-funcionalidades)
- [🛠️ Tecnologias](#️-tecnologias)
- [🏗️ Arquitetura](#️-arquitetura)
- [📁 Estrutura de Pastas](#-estrutura-de-pastas)
- [🗄️ Modelo de Dados](#️-modelo-de-dados)
- [🚀 Como Executar](#-como-executar)
- [⚙️ Configuração](#️-configuração)
- [📡 Endpoints da API](#-endpoints-da-api)
- [🔒 Segurança e Autenticação](#-segurança-e-autenticação)
- [🎯 Objetivo do Projeto](#-objetivo-do-projeto)

---

## ✨ Funcionalidades

| # | Funcionalidade | Descrição |
|---|---|---|
| 🔐 | **Autenticação JWT** | Login e registro com geração de tokens JWT (HS256) |
| 👥 | **Gestão de Usuários** | CRUD completo, restrito ao perfil ADMIN |
| 🧑‍💼 | **Gestão de Clientes** | Cadastro e manutenção de clientes |
| 📦 | **Gestão de Produtos** | CRUD com controle de estoque e busca por nome |
| 🧾 | **Gestão de Pedidos** | Criação, consulta e cancelamento de pedidos |
| 🧩 | **Itens de Pedido** | Gerenciamento de itens com cálculo automático de totais |
| 🔑 | **Controle de Acesso (RBAC)** | Permissões granulares por papel: `ADMIN` e `VENDEDOR` |
| 🔐 | **Senhas Seguras** | Armazenamento com hash **BCrypt** |
| 📊 | **Swagger UI** | Documentação interativa automática da API |

---

## 🛠️ Tecnologias

<table>
  <thead>
    <tr>
      <th>Tecnologia</th>
      <th>Versão</th>
      <th>Descrição</th>
    </tr>
  </thead>
  <tbody>
    <tr><td><strong>Java</strong></td><td>21</td><td>Linguagem principal</td></tr>
    <tr><td><strong>Spring Boot</strong></td><td>3.5.11</td><td>Framework principal</td></tr>
    <tr><td><strong>Spring Security</strong></td><td>3.5.11</td><td>Autenticação e autorização</td></tr>
    <tr><td><strong>Spring Data JPA</strong></td><td>3.5.11</td><td>Persistência de dados (ORM)</td></tr>
    <tr><td><strong>JJWT</strong></td><td>0.11.5</td><td>Geração e validação de tokens JWT</td></tr>
    <tr><td><strong>PostgreSQL</strong></td><td>—</td><td>Banco de dados relacional</td></tr>
    <tr><td><strong>Hibernate</strong></td><td>—</td><td>ORM (via Spring Data JPA)</td></tr>
    <tr><td><strong>Lombok</strong></td><td>—</td><td>Redução de código boilerplate</td></tr>
    <tr><td><strong>Jakarta Validation</strong></td><td>—</td><td>Validação de dados de entrada</td></tr>
    <tr><td><strong>SpringDoc OpenAPI</strong></td><td>2.8.16</td><td>Documentação automática (Swagger UI)</td></tr>
    <tr><td><strong>Maven</strong></td><td>3.11.0</td><td>Gerenciador de dependências e build</td></tr>
    <tr><td><strong>Docker</strong></td><td>—</td><td>Containerização do banco de dados</td></tr>
  </tbody>
</table>

---

## 🏗️ Arquitetura

O projeto segue o padrão de **Arquitetura em Camadas** (inspirada em Clean Architecture), com responsabilidades bem definidas em cada camada:

```
┌──────────────────────────────────────────────────────┐
│                     WEB LAYER                        │
│       Controllers REST  │  Exception Advice          │
└──────────────────────────┬───────────────────────────┘
                           │
┌──────────────────────────▼───────────────────────────┐
│                APPLICATION LAYER                     │
│   Services (interfaces + implementações)             │
│   DTOs (Request / Response)  │  Mappers              │
└──────────────────────────┬───────────────────────────┘
                           │
┌──────────────────────────▼───────────────────────────┐
│                  DOMAIN LAYER                        │
│   Entidades JPA  │  Enums  │  Repositórios           │
└──────────────────────────┬───────────────────────────┘
                           │
┌──────────────────────────▼───────────────────────────┐
│              INFRASTRUCTURE LAYER                    │
│   Spring Security  │  JWT  │  Exceções customizadas  │
└──────────────────────────────────────────────────────┘
```

| Camada | Pacote | Responsabilidade |
|---|---|---|
| **Web** | `web/controller`, `web/advice` | Controllers REST e tratamento global de exceções |
| **Application** | `application/service`, `application/dto`, `application/mapper` | Lógica de negócio, DTOs e mapeamento |
| **Domain** | `domain/entity`, `domain/repository` | Entidades JPA, enums e interfaces de repositório |
| **Infrastructure** | `infra/security`, `infra/exception` | Segurança (JWT, filtros) e exceções customizadas |

---

## 📁 Estrutura de Pastas

```
gestaovendas/
└── src/
    ├── main/
    │   ├── java/com/weg/centroweg/gestaovendas/
    │   │   ├── SistemaDeVendasApplication.java       ← Ponto de entrada
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
    │   │   │   ├── entity/            # Entidades JPA (Usuario, Cliente, Produto, Pedido, ItemPedido)
    │   │   │   │   └── enums/         # RoleUsuario, StatusPedido
    │   │   │   └── repository/        # Repositórios Spring Data JPA
    │   │   ├── infra/
    │   │   │   ├── exception/         # Exceções de negócio customizadas
    │   │   │   └── secutiry/
    │   │   │       ├── config/        # SecurityConfig
    │   │   │       ├── filter/        # JwtAuthenticationFilter
    │   │   │       ├── jwt/           # JwtService
    │   │   │       └── service/       # UserDetailsServiceImpl
    │   │   └── web/
    │   │       ├── advice/            # ApiExceptionHandler (tratamento global)
    │   │       └── controller/        # AuthController, UsuarioController, ClienteController...
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/...
```

---

## 🗄️ Modelo de Dados

### Diagrama de Entidades (ER)

```
┌───────────────┐        ┌──────────────────┐        ┌──────────────┐
│   Usuario     │ 1    * │     Pedido       │ *    1 │   Cliente    │
├───────────────┤────────├──────────────────┤────────├──────────────┤
│ id (UUID) 🔑  │        │ id (UUID) 🔑     │        │ id (UUID) 🔑 │
│ nome          │        │ dataPedido       │        │ nome         │
│ email (único) │        │ valorTotal       │        │ email (único)│
│ senha (hash)  │        │ status (enum)    │        │ telefone     │
│ role (enum)   │        │ cliente_id (FK)  │        └──────────────┘
│ dataCriacao   │        │ usuario_id (FK)  │
│ criadoPor(FK) │        └────────┬─────────┘
└───────────────┘                 │ 1
                                  │
                                  │ *
                         ┌────────┴─────────┐        ┌──────────────┐
                         │   ItemPedido     │ *    1 │   Produto    │
                         ├──────────────────┤────────├──────────────┤
                         │ id (UUID) 🔑     │        │ id (UUID) 🔑 │
                         │ quantidade       │        │ nome         │
                         │ precoUnitario    │        │ descricao    │
                         │ pedido_id (FK)   │        │ preco        │
                         │ produto_id (FK)  │        │ estoque      │
                         └──────────────────┘        └──────────────┘
```

### Enumerações

| Enum | Valores | Descrição |
|---|---|---|
| `RoleUsuario` | `ADMIN` \| `VENDEDOR` | Papel do usuário no sistema |
| `StatusPedido` | `PENDENTE` \| `CANCELADO` \| `ENVIADO` | Estado atual do pedido |

---

## 🚀 Como Executar

### Pré-requisitos

- ☕ **[Java 21](https://adoptium.net/)** ou superior instalado
- 📦 **[Maven 3.8+](https://maven.apache.org/)** (ou use o Maven Wrapper incluso)
- 🐳 **[Docker](https://www.docker.com/)** para subir o banco de dados PostgreSQL

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

> 💡 **Dica:** Para parar o container: `docker stop vendas-db` | Para reiniciar: `docker start vendas-db`

### 3. Configure as propriedades da aplicação

Edite o arquivo `gestaovendas/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/vendas_db
spring.datasource.username=admin
spring.datasource.password=SUA_SENHA_AQUI

jwt.secret=SUA_CHAVE_SECRETA_JWT_LONGA_E_ALEATORIA
jwt.expiration=86400000
```

### 4. Execute a aplicação

```bash
cd gestaovendas
./mvnw spring-boot:run
```

### 5. Acesse a aplicação

| Recurso | URL |
|---|---|
| 🌐 **API Base** | `http://localhost:8080` |
| 📖 **Swagger UI** | `http://localhost:8080/swagger-ui.html` |

---

## ⚙️ Configuração

### Variáveis de Ambiente / Propriedades

| Propriedade | Descrição | Exemplo |
|---|---|---|
| `spring.datasource.url` | URL de conexão com o PostgreSQL | `jdbc:postgresql://localhost:5432/vendas_db` |
| `spring.datasource.username` | Usuário do banco de dados | `admin` |
| `spring.datasource.password` | Senha do banco de dados | `sua_senha_segura` |
| `jwt.secret` | Chave secreta para assinar tokens JWT | string aleatória longa (min. 32 chars) |
| `jwt.expiration` | Tempo de expiração do token (ms) | `86400000` *(= 24 horas)* |

> ⚠️ **Atenção:** Nunca exponha credenciais reais em repositórios públicos. Em produção, utilize variáveis de ambiente do sistema operacional ou um cofre de segredos.

---

## 📡 Endpoints da API

> Todos os endpoints (exceto `/auth/**`) requerem o token JWT no header de cada requisição:
> ```
> Authorization: Bearer <seu_token_jwt>
> ```

### 🔐 Autenticação — `/auth` *(público)*

| Método | Rota | Acesso | Descrição |
|---|---|---|---|
| `POST` | `/auth/login` | 🌐 Público | Realiza login e retorna token JWT |
| `POST` | `/auth/register` | 🌐 Público | Registra novo usuário e retorna token JWT |

<details>
<summary><strong>Ver exemplos de body</strong></summary>

**Login — `POST /auth/login`:**
```json
{
  "email": "usuario@email.com",
  "senha": "minhasenha"
}
```

**Registro — `POST /auth/register`:**
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
</details>

---

### 👤 Usuários — `/usuarios` *(apenas ADMIN)*

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/usuarios` | Cria um novo usuário |
| `GET` | `/usuarios` | Lista todos os usuários |
| `GET` | `/usuarios/{id}` | Busca usuário por ID |
| `PUT` | `/usuarios/{id}` | Atualiza dados do usuário |
| `DELETE` | `/usuarios/{id}` | Remove um usuário |

<details>
<summary><strong>Ver exemplo de body</strong></summary>

```json
{
  "nome": "Maria Souza",
  "email": "maria@email.com",
  "senha": "senha123",
  "role": "VENDEDOR"
}
```
</details>

---

### 🧑‍🤝‍🧑 Clientes — `/clientes`

| Método | Rota | Acesso | Descrição |
|---|---|---|---|
| `POST` | `/clientes` | ADMIN, VENDEDOR | Cadastra um novo cliente |
| `GET` | `/clientes` | Autenticado | Lista todos os clientes |
| `GET` | `/clientes/{id}` | Autenticado | Busca cliente por ID |
| `PUT` | `/clientes/{id}` | Autenticado | Atualiza dados do cliente |
| `DELETE` | `/clientes/{id}` | Autenticado | Remove um cliente |

<details>
<summary><strong>Ver exemplo de body</strong></summary>

```json
{
  "nome": "Carlos Lima",
  "email": "carlos@email.com",
  "telefone": "(11) 99999-0000"
}
```
</details>

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

<details>
<summary><strong>Ver exemplos de body</strong></summary>

**Criar produto:**
```json
{
  "nome": "Notebook Dell",
  "descricao": "Notebook com processador Intel i7",
  "preco": 4500.00,
  "estoque": 10
}
```

**Atualizar estoque:**
```json
{
  "estoque": 25
}
```
</details>

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

<details>
<summary><strong>Ver exemplo de body</strong></summary>

```json
{
  "clienteId": "uuid-do-cliente",
  "usuarioId": "uuid-do-usuario"
}
```
</details>

---

### 🧩 Itens de Pedido — `/itens`

| Método | Rota | Acesso | Descrição |
|---|---|---|---|
| `POST` | `/itens` | ADMIN, VENDEDOR | Adiciona um item a um pedido |
| `GET` | `/itens` | Autenticado | Lista todos os itens de pedidos |
| `GET` | `/itens/{id}` | Autenticado | Busca item por ID |
| `PUT` | `/itens/{id}` | Autenticado | Atualiza um item de pedido |
| `DELETE` | `/itens/{id}` | Autenticado | Remove um item de pedido |

<details>
<summary><strong>Ver exemplo de body</strong></summary>

```json
{
  "pedidoId": "uuid-do-pedido",
  "produtoId": "uuid-do-produto",
  "quantidade": 2
}
```
</details>

---

## 🔒 Segurança e Autenticação

A aplicação utiliza **Spring Security** com autenticação **stateless** baseada em **JWT**.

### Fluxo de Autenticação

```
  ┌──────────┐                            ┌─────────────────┐
  │  Cliente │                            │   Servidor API  │
  └────┬─────┘                            └────────┬────────┘
       │                                           │
       │  POST /auth/login {email, senha}          │
       │ ──────────────────────────────────────>  │
       │                                           │  Valida credenciais
       │         { "token": "eyJhbG..." }          │  Gera JWT (HS256)
       │ <──────────────────────────────────────  │
       │                                           │
       │  GET /produtos                            │
       │  Authorization: Bearer eyJhbG...          │
       │ ──────────────────────────────────────>  │
       │                                           │  JwtAuthenticationFilter
       │                                           │  valida token
       │         [ lista de produtos ]             │  popula SecurityContext
       │ <──────────────────────────────────────  │
  ┌────┴─────┐                            ┌────────┴────────┐
  │  Cliente │                            │   Servidor API  │
  └──────────┘                            └─────────────────┘
```

### Papéis (Roles) e Permissões

| Role | 👤 Usuários | 🧑 Clientes | 📦 Produtos | 🧾 Pedidos | 🧩 Itens |
|---|:---:|:---:|:---:|:---:|:---:|
| **ADMIN** | ✅ CRUD | ✅ CRUD | ✅ CRUD | ✅ Ler/Criar/Cancelar | ✅ CRUD |
| **VENDEDOR** | ❌ | ✅ CRUD | 👁️ Leitura + Estoque | ✅ Criar/Cancelar | ✅ CRUD |

### Token JWT

| Propriedade | Valor |
|---|---|
| Algoritmo | `HS256` (HMAC-SHA256) |
| Expiração padrão | `86400000 ms` (24 horas) |
| Header HTTP | `Authorization: Bearer <token>` |

> 🔐 As senhas são armazenadas de forma segura com o algoritmo de hash **BCrypt**.

---

## 🎯 Objetivo do Projeto

Este projeto foi desenvolvido com o objetivo de praticar e consolidar habilidades no desenvolvimento de **APIs REST com Spring Boot**, aplicando as seguintes boas práticas:

- ✅ **Arquitetura em camadas** inspirada em Clean Architecture
- ✅ **Segurança** robusta com Spring Security e JWT
- ✅ **Persistência** eficiente com Spring Data JPA e PostgreSQL
- ✅ **Separação de responsabilidades** (Controllers → Services → Repositories → DTOs)
- ✅ **Validação de dados** com Jakarta Bean Validation
- ✅ **Controle de acesso** granular baseado em papéis (RBAC)
- ✅ **Documentação automática** da API com SpringDoc/Swagger
- ✅ **Tratamento centralizado de erros** com `@RestControllerAdvice`

---

<p align="center">
  📌 <em>Projeto desenvolvido com fins de aprendizado e aprimoramento de habilidades em desenvolvimento Back-End com o ecossistema Spring.</em>
</p>
