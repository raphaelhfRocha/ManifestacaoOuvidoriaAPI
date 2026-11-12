# 📢 Manifestação Ouvidoria API

<div align="center">

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.2-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![H2 Database](https://img.shields.io/badge/H2-Database-003545?style=for-the-badge&logo=databricks&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI_3-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Status](https://img.shields.io/badge/Status-Em_Desenvolvimento-yellow?style=for-the-badge)

**API REST para gestão de manifestações de ouvidoria pública, inspirada no sistema de ouvidoria do Governo Federal brasileiro.**

[Começando](#-começando) •
[Endpoints](#-endpoints-da-api) •
[Arquitetura](#%EF%B8%8F-arquitetura) •
[Modelo de Dados](#-modelo-de-dados) •
[Swagger](#-documentação-swagger)

</div>

---

## 📋 Sobre o Projeto

A **Manifestação Ouvidoria API** é uma aplicação backend desenvolvida em **Java 21** com **Spring Boot** que permite o registro, acompanhamento e gestão de manifestações de ouvidoria pública.

O sistema permite que cidadãos registrem diferentes tipos de manifestações (**denúncias, reclamações, sugestões e elogios**) direcionadas a órgãos governamentais, com rastreabilidade completa através de um histórico de movimentações.

### Principais Funcionalidades

- ✅ **CRUD completo** de Manifestações e Órgãos
- 📝 **Registro de manifestações** com tipos: Denúncia, Reclamação, Sugestão e Elogio
- 🏛️ **Vinculação a órgãos governamentais** (CGU, MEC, MS, INSS, etc.)
- 📊 **Rastreamento automático de movimentações** — cada alteração de status gera um registro no histórico
- 🔄 **Fluxo de situação**: `Aberta` → `Em Análise` → `Concluída`
- 🔍 **Consulta por órgão** através da sigla
- 📖 **Documentação interativa** com Swagger UI
- 💾 **Dados iniciais pré-carregados** com 9 órgãos do Governo Federal

---

## 🚀 Começando

### Pré-requisitos

- **Java 21** (JDK)
- **Maven 3.9+** (ou usar o wrapper `mvnw` incluso)

### Instalação e Execução

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/ManifestacaoOuvidoriaAPI.git
cd ManifestacaoOuvidoriaAPI

# Execute a aplicação
./mvnw spring-boot:run
```

A API será iniciada em `http://localhost:8080`.

### Acessos Rápidos

| Recurso | URL |
|---|---|
| **Swagger UI** | http://localhost:8080/ |
| **API Docs (JSON)** | http://localhost:8080/api-docs |
| **Console H2** | http://localhost:8080/h2-console |
| **Actuator** | http://localhost:8080/actuator |

> **Console H2** — JDBC URL: `jdbc:h2:mem:ouvidoria` · Usuário: `sa` · Senha: *(vazio)*

---

## 📡 Endpoints da API

### Manifestações — `/manifestacoes`

| Método | Rota | Descrição |
|--------|------|-----------|
| `GET` | `/manifestacoes` | Lista todas as manifestações |
| `GET` | `/manifestacoes/{id}` | Busca manifestação por ID |
| `GET` | `/manifestacoes/orgao/{sigla}` | Lista manifestações de um órgão (pela sigla) |
| `POST` | `/manifestacoes` | Registra uma nova manifestação |
| `PUT` | `/manifestacoes/{id}` | Atualiza uma manifestação existente |
| `DELETE` | `/manifestacoes/{id}` | Exclui uma manifestação |

### Órgãos — `/orgaos`

| Método | Rota | Descrição |
|--------|------|-----------|
| `GET` | `/orgaos` | Lista todos os órgãos |
| `GET` | `/orgaos/{id}` | Busca órgão por ID |
| `POST` | `/orgaos` | Registra um novo órgão |
| `PUT` | `/orgaos/{id}` | Atualiza um órgão existente |
| `DELETE` | `/orgaos/{id}` | Exclui um órgão |

### Exemplos de Requisição

#### Criar uma Manifestação

```json
POST /manifestacoes
Content-Type: application/json

{
  "statusTipoManifestacao": "Reclamação",
  "assunto": "Demora no atendimento",
  "descricao": "Aguardando resposta há mais de 30 dias sobre requerimento de benefício.",
  "nomeManifestante": "João Silva",
  "orgao": "INSS"
}
```

#### Resposta (201 Created)

```json
{
  "id": 1,
  "statusTipoManifestacao": "Reclamação",
  "assunto": "Demora no atendimento",
  "descricao": "Aguardando resposta há mais de 30 dias sobre requerimento de benefício.",
  "nomeManifestante": "João Silva",
  "dataRegistro": "2024-11-12T14:30:00",
  "dataAtualizacaoRegistro": "2024-11-12T14:30:00",
  "statusSituacaoManifestacao": "Aberta",
  "orgao": "INSS",
  "movimentacoesDTO": null
}
```

#### Criar um Órgão

```json
POST /orgaos
Content-Type: application/json

{
  "nome": "Tribunal de Contas da União",
  "sigla": "TCU"
}
```

---

## 🏗️ Arquitetura

O projeto segue uma **arquitetura em camadas** com uso de **interfaces (contratos)** para desacoplamento, facilitando testes e manutenção.

```
src/main/java/com/example/manifestacoes_ouvidoria/app/
│
├── config/                  # Configurações (OpenAPI/Swagger)
├── controllers/             # Camada de apresentação (REST Controllers)
├── DTOs/                    # Data Transfer Objects (Java Records)
├── entities/                # Entidades JPA
├── enums/                   # Enumerações de status
├── globals/                 # Classes globais (tratamento de exceções)
├── interfaces/
│   ├── repositories/        # Contratos de repositório + JPA interfaces
│   ├── services/            # Contratos de serviço
│   └── Mapper.java          # Interface genérica de mapeamento
├── maps/
│   ├── enums/               # Mapeamento de enums ↔ String
│   ├── ManifestacaoMapper   # Mapper Manifestação
│   ├── MovimentacaoMapper   # Mapper Movimentação
│   └── OrgaoMapper          # Mapper Órgão
├── repositories/            # Implementações concretas dos repositórios
└── services/                # Implementações concretas dos serviços
```

### Padrões Utilizados

| Padrão | Onde |
|--------|------|
| **DTO (Data Transfer Object)** | Java Records em `DTOs/` — separação entre entidade e resposta da API |
| **Repository Pattern** | Contratos em `interfaces/repositories/` com implementações em `repositories/` |
| **Mapper Genérico** | Interface `Mapper<E, D>` com implementações tipadas por entidade |
| **Injeção de Dependência** | Via construtor em todas as camadas (sem `@Autowired` em campo) |
| **Contratos (Interfaces)** | Services e Repositories expõem contratos, não implementações |

---

## 📐 Modelo de Dados

### Diagrama de Relacionamento

```
┌──────────────────────┐       ┌──────────────────────────────┐
│        Orgao          │       │        Manifestacao           │
├──────────────────────┤       ├──────────────────────────────┤
│ id          BIGINT PK │──┐   │ id                  BIGINT PK│
│ nome        VARCHAR   │  │   │ tipo_manifestacao   ENUM     │
│ sigla       CHAR(5)   │  │   │ assunto             VARCHAR  │
└──────────────────────┘  │   │ descricao           VARCHAR  │
                           │   │ nome_manifestante   VARCHAR? │
                           │   │ data_registro       DATETIME │
                           │   │ data_atualizacao    DATETIME │
                           │   │ situacao            ENUM     │
                           └──►│ orgao_id            BIGINT FK│
                               └─────────────┬────────────────┘
                                              │ 1:N
                               ┌──────────────▼────────────────┐
                               │       Movimentacao             │
                               ├───────────────────────────────┤
                               │ id                   BIGINT PK│
                               │ data_hora            DATETIME │
                               │ descricao            VARCHAR  │
                               │ status_resultante    ENUM     │
                               │ manifestacao_id      BIGINT FK│
                               └───────────────────────────────┘
```

### Enumerações

**Tipo de Manifestação** (`StatusTipoManifestacao`)

| Valor | Descrição |
|-------|-----------|
| `Denuncia` | Denúncia de irregularidade |
| `Reclamação` | Reclamação sobre serviço |
| `Sugestão` | Sugestão de melhoria |
| `Elogio` | Elogio a serviço prestado |

**Situação da Manifestação** (`StatusSituacao`)

| Valor | Descrição |
|-------|-----------|
| `Aberta` | Manifestação registrada (status inicial automático) |
| `Em análise` | Em processo de análise pelo órgão |
| `Concluída` | Manifestação finalizada |

### Fluxo de Status

```
  ┌─────────┐      ┌─────────────┐      ┌────────────┐
  │ Aberta  │ ───► │ Em Análise  │ ───► │ Concluída  │
  └─────────┘      └─────────────┘      └────────────┘
     (auto)          (atualização)        (atualização)
```

> Cada transição de status gera automaticamente um registro de **Movimentação** com data/hora e descrição da mudança.

---

## 🏛️ Órgãos Pré-cadastrados

A aplicação já inicia com **9 órgãos do Governo Federal** carregados via `data.sql`:

| Sigla | Nome |
|-------|------|
| `CGU` | Controladoria-Geral da União |
| `MS` | Ministério da Saúde |
| `MEC` | Ministério da Educação |
| `INSS` | Instituto Nacional do Seguro Social |
| `MF` | Ministério da Fazenda |
| `MJSP` | Ministério da Justiça e Segurança Pública |
| `MTE` | Ministério do Trabalho e Emprego |
| `MDS` | Ministério do Desenvolvimento e Assistência Social |
| `MMA` | Ministério do Meio Ambiente e Mudança do Clima |

---

## 🛠️ Stack Tecnológica

| Tecnologia | Versão | Finalidade |
|---|---|---|
| **Java** | 21 | Linguagem principal |
| **Spring Boot** | 4.1.2 | Framework web |
| **Spring Data JPA** | — | Persistência e ORM |
| **H2 Database** | — | Banco em memória (desenvolvimento) |
| **SpringDoc OpenAPI** | 2.8.9 | Documentação Swagger UI |
| **Spring Boot Actuator** | — | Monitoramento e health checks |
| **Spring Boot DevTools** | — | Hot reload em desenvolvimento |
| **Maven** | — | Gerenciamento de dependências e build |

---

## 📖 Documentação Swagger

A documentação interativa está disponível na **raiz da aplicação** (`/`). A interface Swagger UI permite testar todos os endpoints diretamente no navegador.

**Acesse:** http://localhost:8080/

---

## 🧠 Regras de Negócio

1. **Registro de manifestação** — ao criar, a situação é automaticamente definida como `Aberta` e uma movimentação inicial é registrada.
2. **Atualização de manifestação** — cada atualização gera um novo registro de movimentação descrevendo a transição de status.
3. **Manifestante anônimo** — o campo `nomeManifestante` é opcional, permitindo manifestações anônimas.
4. **Vínculo obrigatório com órgão** — toda manifestação deve ser associada a um órgão existente (identificado pela sigla).
5. **Histórico completo** — o histórico de movimentações é retornado junto com a manifestação, garantindo rastreabilidade total.
6. **Exclusão em cascata** — ao excluir uma manifestação, todas as movimentações vinculadas são removidas automaticamente (`orphanRemoval = true`).

---

## 📂 Estrutura do Projeto

```
ManifestacaoOuvidoriaAPI/
├── pom.xml                          # Configuração Maven e dependências
├── src/
│   ├── main/
│   │   ├── java/.../app/
│   │   │   ├── AppApplication.java          # Classe principal (bootstrap)
│   │   │   ├── config/
│   │   │   │   └── OpenApiConfig.java       # Configuração Swagger/OpenAPI
│   │   │   ├── controllers/
│   │   │   │   ├── ManifestacaoController   # REST endpoints manifestações
│   │   │   │   └── OrgaoController          # REST endpoints órgãos
│   │   │   ├── DTOs/
│   │   │   │   ├── ManifestacaoDTO          # Record de transferência
│   │   │   │   ├── MovimentacaoDTO          # Record de transferência
│   │   │   │   └── OrgaoDTO                 # Record de transferência
│   │   │   ├── entities/
│   │   │   │   ├── Manifestacao             # Entidade JPA
│   │   │   │   ├── Movimentacao             # Entidade JPA
│   │   │   │   └── Orgao                    # Entidade JPA
│   │   │   ├── enums/
│   │   │   │   ├── StatusSituacao           # Aberta, Em Análise, Concluída
│   │   │   │   └── StatusTipoManifestacao   # Denúncia, Reclamação, etc.
│   │   │   ├── interfaces/
│   │   │   │   ├── Mapper.java              # Interface genérica <E, D>
│   │   │   │   ├── repositories/            # Contratos de repositório + JPA
│   │   │   │   └── services/                # Contratos de serviço
│   │   │   ├── maps/                        # Implementações de Mapper
│   │   │   ├── repositories/                # Implementações de repositório
│   │   │   └── services/                    # Implementações de serviço
│   │   └── resources/
│   │       ├── application.properties       # Configurações da aplicação
│   │       └── data.sql                     # Seed de órgãos governamentais
│   └── test/
│       └── java/.../AppApplicationTests     # Testes
└── .mvn/                                    # Maven Wrapper
```
