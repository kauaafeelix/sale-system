# Sistema de Vendas API

API REST desenvolvida em **Java com Spring Boot** para gerenciamento de um sistema de vendas. A aplicação permite o controle de **usuários, clientes, produtos e pedidos**, além de possuir **autenticação e autorização utilizando JWT**, garantindo acesso seguro aos recursos da API.

O projeto segue uma organização em camadas inspirada em **Clean Architecture**, separando domínio, aplicação, infraestrutura e interface da API, facilitando manutenção e escalabilidade.

---

# Funcionalidades

- Autenticação e autorização com **JWT**
- Cadastro e gerenciamento de **usuários**
- Cadastro e gerenciamento de **clientes**
- Cadastro e gerenciamento de **produtos**
- Criação e gerenciamento de **pedidos**
- Controle de **estoque de produtos**
- Criptografia de senhas com **BCrypt**

---

# Tecnologias utilizadas

- **Java**
- **Spring Boot**
- **Spring Security**
- **JWT**
- **Spring Data JPA**
- **PostgreSQL**
- **Docker**
- **DBeaver**
- **Maven**

---

# Arquitetura do projeto
application
domain
infra
web


**Descrição das camadas:**

- **domain** → entidades e regras principais do sistema  
- **application** → serviços, DTOs e lógica da aplicação  
- **infra** → persistência, segurança e configurações externas  
- **web** → controllers e interface da API  

---

# Banco de dados

O banco de dados utilizado é **PostgreSQL**, executado em um **container Docker**.  

O gerenciamento do banco pode ser realizado utilizando ferramentas como **DBeaver**.

---

# Segurança

A aplicação utilizará **Spring Security** com autenticação baseada em **JWT**.  

As senhas dos usuários serão armazenadas de forma segura utilizando **BCrypt**.

---

# Objetivo do projeto

Este projeto tem como objetivo praticar o desenvolvimento de **APIs REST com Spring Boot**, aplicando boas práticas de arquitetura, segurança e organização de código.
