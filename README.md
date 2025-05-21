<a id="voltar-ao-topo"></a>
# Sistema Projeto Ada

Este repositório contém o **Projeto de Conclusão do Módulo de Testes Automatizados** do curso Desenvolva+ da Ada Tech com Mercado Livre.


---

## 🚀 Guia rápido

* **Coleção Postman**
  [postman/projeto\_ada.postman\_collection.json](https://github.com/yuricapella/projeto-ada/blob/testes-a-entregar/postman/projeto_ada.postman_collection.json)


* **Estrutura do repositório**
  Veja detalhes em [ESTRUTURA.md](./ESTRUTURA.md)


* **Guia de uso:**  
  Passo a passo para importar a coleção no Postman, usar os principais endpoints da API (usuários, clientes, veículos, locações, cálculo de salários), acessar Swagger e console H2, rodar o projeto com Maven e executar os testes automatizados.
  [GUIA-DE-USO.md](./GUIA-DE-USO.md)


* **Documento do Projeto Final**
  Informações gerais do projeto em [PROJETO-FINAL.md](./PROJETO-FINAL.md)

---

## 🔍 Testes Automatizados

*Este projeto possui testes nas principais camadas da aplicação para garantir a qualidade e o correto funcionamento.*


### Testes de Interface (Selenium)

Esses testes percorrem os principais fluxos de uso da aplicação via navegador Chrome:

| Classe / Arquivo             | Descrição                                                                                                        |
| ---------------------------- | ---------------------------------------------------------------------------------------------------------------- |
| **LoginTest.java**           | Verifica login bem-sucedido (`loginComSucesso`) e falha com credenciais inválidas, validando mensagens.          |
| **CadastroTest.java**        | Testa cadastro de usuário: sucesso ao preencher campos válidos e falha ao usar senhas diferentes.                |
| **CalcularSalarioTest.java** | Valida o fluxo da calculadora de IR: acesso via menu, submissão de salário (com e sem dependentes) e resultados. |
| **ClienteTest.java**         | Exercita CRUD de clientes via UI: listagem, criação, edição (pré-preenchimento) e exclusão com confirmação.      |

---

### Testes de Repository
**Arquivo:** `UsuarioRepositoryTest.java`  
Cobrem operações básicas (salvar, buscar, atualizar, deletar) e consultas customizadas.  
Validam constraints do banco e o comportamento do JPA.  
**Exemplo:** Testa se um usuário válido é salvo e recuperado com sucesso.

---

### Testes de Service
**Arquivo:** `AtualizarUsuarioServiceTest.java`  
Testam a lógica de negócio isolada, com mocks para repositórios e serviços dependentes.  
Verificam fluxos positivos e exceções.  
Confirma a ordem correta das chamadas.  
**Exemplo:** Atualiza um usuário com criptografia da senha e verifica chamadas internas.

---

### Testes de Controller API
**Arquivo:** `UsuarioApiControllerTest.java`  
Testam endpoints REST (GET, POST, PUT, DELETE) via MockMvc.  
Verificam status HTTP, conteúdo JSON e tratamento de exceções.  
Validam casos de sucesso e erros (ex: 404 e BadRequest).  
**Exemplo:** GET /api/usuarios/{id} retorna o usuário correto ou erro 404 quando não encontrado.

---

### Testes de Controller de View (Web MVC)
**Arquivo:** `MainControllerTest.java`  
Testam controllers que retornam páginas via Thymeleaf.  
Validam redirecionamentos, renderização das views e autenticação.  
Usam `@WebMvcTest` com contexto Spring.  
**Exemplo:** Verifica redirecionamento para login e carregamento das páginas de login, cadastro e home (esta última somente para usuários autenticados).

---

### Relatórios de Testes

* Gerados via Jacoco em:
  `target/site/jacoco/index.html`

* Exibem cobertura dos testes automatizados, com exclusões para mostrar apenas o código da API, omitindo:

  * `model`
  * `util`
  * `config`
  * `exception`
  * `dto`
  * Controllers de view (ex: `ViewController`, `HomeController`)

---

[🔝 Voltar ao topo](#voltar-ao-topo)
