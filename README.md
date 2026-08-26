# Menthfy - Mentorship Service (Java & Spring Boot)

[![Status do Projeto](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)](https://github.com/FatecLP/mentorship-service)
[![Java](https://img.shields.io/badge/Java-21-007396?logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-9.6-4479A1?logo=mysql&logoColor=white)](https://mysql.com/)
[![Maven](https://img.shields.io/badge/Maven-3-C71A36?logo=apachemaven&logoColor=white)](https://maven.apache.org/)

Microsserviço independente responsável por gerenciar as solicitações, agendamentos e o ciclo de vida das mentorias entre alunos e professores na plataforma **Menthfy**.

---

## 🏛️ Arquitetura da Plataforma

Este serviço faz parte da arquitetura distribuída do ecossistema Menthfy:
- 🎨 **[FatecLP/menthfy](https://github.com/FatecLP/menthfy)**: Frontend SPA em React 19 + Vite.
- 🟢 **[FatecLP/menthfy-backend](https://github.com/FatecLP/menthfy-backend)**: Backend Gateway em Node.js & Express.
- ☕ **[FatecLP/mentorship-service](https://github.com/FatecLP/mentorship-service)** (Este Repositório): Microsserviço de domínio de mentorias em Java + Spring Boot.

---

## 💻 Tecnologias Utilizadas

- **Java 21 (LTS)**
- **Spring Boot 4.0** (Spring Web MVC, Spring Data JPA)
- **Hibernate ORM**
- **MySQL Connector/J**
- **Maven Wrapper**
- **CORS Multi-Origin Config** (`WebConfig`)

---

## 📡 Endpoints da API REST (`/api/mentorships`)

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/api/mentorships` | Solicita uma nova mentoria (Aluno -> Professor) |
| `PUT` | `/api/mentorships/{id}/accept` | Professor aceita a mentoria solicitada |
| `GET` | `/api/mentorships/teacher/{teacherId}` | Lista todas as mentorias de um professor |
| `GET` | `/api/mentorships/student/{studentId}` | Lista todas as mentorias de um aluno |

### Exemplos de Requisição:

#### 1. Criar Solicitação de Mentoria:
`POST /api/mentorships`
```json
{
  "studentId": 1,
  "teacherId": 10
}
```
**Resposta (201 Created):**
```json
{
  "id": 1,
  "studentId": 1,
  "teacherId": 10,
  "status": "REQUESTED"
}
```

#### 2. Aceitar Mentoria:
`PUT /api/mentorships/1/accept`

**Resposta (200 OK):**
```json
{
  "id": 1,
  "studentId": 1,
  "teacherId": 10,
  "status": "ACCEPTED"
}
```

---

## ⚙️ Configuração e Variáveis de Ambiente (`application.properties`)

O microsserviço aceita configuração via variáveis de ambiente com fallbacks para desenvolvimento local:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/menthfy_db}
spring.datasource.username=${DB_USER:root}
spring.datasource.password=${DB_PASS:root}
server.port=${MENTORSHIP_PORT:8080}
app.cors.allowed-origins=${CORS_ALLOWED_ORIGINS:http://localhost:3000,http://localhost:5173}
```

---

## 🚀 Como Executar Localmente

### Pré-requisitos
- **Java JDK 21** instalado e configurado (`JAVA_HOME`).
- **MySQL 8.0+ / 9.0+** com o banco `menthfy_db` ativo.

### 1. Clonar o Repositório
```bash
git clone https://github.com/FatecLP/mentorship-service.git
cd mentorship-service
```

### 2. Executar a Aplicação
- **No Linux/macOS:**
  ```bash
  ./mvnw spring-boot:run
  ```
- **No Windows (PowerShell/CMD):**
  ```powershell
  .\mvnw.cmd spring-boot:run
  ```

A API estará disponível por padrão em: **`http://localhost:8080`**

### 3. Executar Testes
```bash
./mvnw test
```

---

## 📜 Licença

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Este projeto está licenciado sob a licença MIT.

---

<div align="center">
  <strong>Desenvolvido com 💙 pela equipe Menthfy</strong><br>
  FATEC Luigi Papaiz - Diadema/SP - 2026
</div>
