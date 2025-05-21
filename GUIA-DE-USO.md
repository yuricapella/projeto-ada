<a id="voltar-ao-topo"></a>
[⬅ Voltar ao README](README.md)

# 📘 Guia de Uso

## 📬 Acesso à Collection do Postman

🔗 [Acessar Collection](https://yuricapella.postman.co/workspace/Yuri-Capella's-Workspace~eed12cec-649d-4622-8f2c-fee779577473/collection/43702238-1bf00d63-906d-4f84-83f7-46e258bf48d8?share=true&origin=sidebar)  
📁 [Baixar Collection JSON](https://github.com/yuricapella/projeto-ada/blob/testes-a-entregar/postman/projeto_ada.postman_collection.json)

Importe no Postman: `Import` ▶ `File`.

---

## 🌐 Endpoints da API

Base URL: `http://localhost:8080`

### 🔓 Público (sem autenticação)

| Método | URL                                | Descrição                     |
|--------|------------------------------------|-------------------------------|
| POST   | /api/usuarios                      | Criar usuário                 |
| POST   | /api/poo1/clientes                 | Criar cliente                 |
| POST   | /api/poo1/veiculos                 | Criar veículo                 |
| POST   | /api/poo1/locacao                  | Criar locação                 |
| POST   | /api/logica-programacao/salarios  | Calcular salários             |


### 🔐 Protegido (requer autenticação)
| Entidade   | Verbo  | URL                                | Descrição                     |
|------------|--------|------------------------------------|-------------------------------|
| Usuários   | GET    | /api/usuarios                      | Listar todos                  |
| Usuários   | GET    | /api/usuarios/{id}                 | Buscar por ID                 |
| Usuários   | PUT    | /api/usuarios/{id}                 | Atualizar usuário             |
| Usuários   | DELETE | /api/usuarios/{id}                 | Deletar usuário               |
|------------|--------|------------------------------------|-------------------------------|
| Clientes   | GET    | /api/poo1/clientes                 | Listar todos                  |
| Clientes   | GET    | /api/poo1/clientes/{id}            | Buscar por ID                 |
| Clientes   | PUT    | /api/poo1/clientes/{id}            | Atualizar cliente             |
| Clientes   | DELETE | /api/poo1/clientes/{id}            | Deletar cliente               |
|------------|--------|------------------------------------|-------------------------------|
| Veículos   | GET    | /api/poo1/veiculos                 | Listar todos                  |
| Veículos   | GET    | /api/poo1/veiculos/{id}            | Buscar por ID                 |
| Veículos   | PUT    | /api/poo1/veiculos/{id}            | Atualizar veículo             |
| Veículos   | DELETE | /api/poo1/veiculos/{id}            | Deletar veículo               |
|------------|--------|------------------------------------|-------------------------------|
| Locação    | GET    | /api/poo1/locacao                  | Listar locações               |
| Locação    | GET    | /api/poo1/locacao/{id}             | Buscar por ID                 |
| Locação    | PUT    | /api/poo1/locacao/{id}             | Atualizar locação             |
| Locação    | DELETE | /api/poo1/locacao/{id}             | Deletar locação               |
---

# 👤 Usuários Padrão (pré-cadastrados) - autenticação via login no site e basic auth no postman.

Local: `config/inicializar/UsuarioDataInitializer`

| Email                        | Senha     |
|-----------------------------|-----------|
| joao.silva@email.com        | senha123  |
| maria.oliveira@email.com    | senha456  |
| pedro.santos@email.com      | senha789  |
| ana.rodrigues@email.com     | senhaabc  |
| yuri@yuri.com               | yuri      |



### Cadastro de Usuário via API

POST `http://localhost:8080/api/usuarios`

```json
{
  "nome": "Rodrigo Silva",
  "email": "rodrigo.silva@email.com",
  "senha": "senha123"
}
```

---
# 💰 Locação de veículos (POO1)

## 👥 Clientes Padrão (pré-cadastrados)

Local: `config/inicializador/ClienteDataInitializer`

| Nome            | Tipo             | Endereço     | Telefone        |
|-----------------|------------------|--------------|------------------|
| Cliente PF 1    | Pessoa Física    | Rua PF 1     | 11 91111-0001    |
| Cliente PJ 1    | Pessoa Jurídica  | Rua PJ 1     | 22 92222-0001    |

### Cadastro de Cliente via API

POST `http://localhost:8080/api/clientes`

```json
{
  "nome": "João da Silva",
  "documento": "CPF",
  "endereco": "Rua das Flores, 123",
  "telefone": "11912345678"
}
```

---

## 🚗 Veículos Padrão (pré-cadastrados)

Local: `config/inicializador/VeiculoDataInitializer`

| Nome             | Placa   | Tipo     | Categoria     |
|------------------|---------|----------|---------------|
| Caminhao 1       | CAM1    | Comum    | CAMINHAO      |
| Moto 1           | MOT1    | Comum    | MOTO          |
| Carro comum 1    | CAR1    | Comum    | CARRO_COMUM   |
| Carro Premium 1  | PREM1   | Luxo     | CARRO_PREMIUM |
| SUV 1            | SUV1    | Luxo     | SUV           |

---

### 📦 Cadastro de Veículos via API

- **POST** `http://localhost:8080/api/veiculos`

#### 📌 Exemplo de corpo da requisição

```json
{
  "modelo": "Civic",
  "placa": "DEF2F34",
  "valorDiaria": 120.0,
  "disponivel": true,
  "tipo": "COMUM",
  "tipoClasse": "CARRO_COMUM"
}
```

---

## 📄 Locação Padrão (pré-cadastrada)

Local: `config/inicializador/LocacaoDataInitializer`

| Cliente           | Veículo         | Dias | Placa        | Valor Diário | Status       |
|-------------------|-----------------|------|--------------|--------------|--------------|
| Cliente locacao   | Carro locacao    | 5    | CARLOCACAO   | 300.0        | Indisponível |

---

POST `http://localhost:8080/api/locacoes`

```json
{
  "clienteId": 1,
  "veiculoId": 3,
  "diasDeLocacao": 5
}
```

--- 
# 💰 Cálculo de Salários (Lógica de Programação)

Base URL: `http://localhost:8080/api/logica-programacao`

| Método | URL                        | Descrição                                         | Parâmetros              |
|--------|----------------------------|---------------------------------------------------|--------------------------|
| POST   | `/salarios`                | Calcula os descontos com base no salário bruto   | `dependentes` (opcional) |

### Parâmetro Opcional

| Nome         | Tipo   | Padrão | Descrição                                            |
|--------------|--------|--------|------------------------------------------------------|
| dependentes  | int    | `0`    | Número de dependentes usados no cálculo do IRRF     |

- Com dependentes:  
  `http://localhost:8080/api/logica-programacao/salarios?dependentes=2`  
- Sem dependentes (padrão):  
  `http://localhost:8080/api/logica-programacao/salarios`

### Formato JSON para requisição

Você pode enviar **um único salário** ou **uma lista de salários**:

#### ✔️ Exemplo com 1 salário

```json
[
  {
    "salarioBruto": 3000.0
  }
]
````

#### ✔️ Exemplo com múltiplos salários

```json
[
  {
    "salarioBruto": 3000.0
  },
  {
    "salarioBruto": 4500.0
  },
  {
    "salarioBruto": 7000.0
  }
]
```

---

## 🔧 Rodando o Projeto

```bash
mvn clean install
````

* App rodando em `http://localhost:8080`
* Swagger: `http://localhost:8080/swagger.html`
* H2 Console: `http://localhost:8080/h2`

  * JDBC: `jdbc:h2:mem:test`
  * Usuário: `sa`
  * Senha: *(em branco)*

---

## ✅ Executando os Testes

Após build:

```bash
mvn test
```

* Relatório de cobertura:
  Abrir `target/site/jacoco/index.html` no navegador

---

## 💡 Observações

* API REST funcional com tratamento de erros
* Módulos `logica-programacao` e `poo1` refatorados e concluídos
* Testes criados e organizados por camada (repository, service, controller)
* Arquivo Postman incluído para facilitar testes manuais

[🔝 Voltar ao topo](#voltar-ao-topo)
