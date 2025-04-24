<a id="voltar-ao-topo"></a>  
# Sistema Projeto Ada

Este repositório contém o Projeto de Conclusão do módulo Programação web 2 do curso Desenvolva+ da Ada Tech com Mercado Livre “Sistema Projeto Ada”, uma aplicação Spring Boot que combina:

- uma **API REST** para CRUD de usuários  
- uma **interface web** (Thymeleaf) para login, cadastro e navegação entre módulos didáticos  
- esqueleto dos módulos de **Lógica de Programação** e **POO-1**  
- **Swagger UI** e **Postman Collection** para teste de API  

---

## 📂 Estrutura do repositório

Veja a organização completa das pastas e arquivos em [ESTRUTURA.md](./ESTRUTURA.md).

---

## 🚀 Como usar

Siga o passo-a-passo de instalação, importação da Postman Collection e execução da aplicação em [GUIA-DE-USO.md](./GUIA-DE-USO.md).

---

## 🎤 Apresentação e desafios

Uma narrativa dos principais desafios, “perrengues” e soluções aplicadas está em [APRESENTACAO.md](./apresentacao.md).

---

## 📊 Diagramas

Veja o diagrama completo de classes e estrutura do projeto em [DIAGRAMA.md](./DIAGRAMA.md).

---

## 🔑 Principais pontos

- **Usuários**  
  - CRUD completo via `/api/usuarios` (DTOs, BCrypt, tratamento de exceções)  
  - Formulário Thymeleaf em `/login` e `/cadastro`  
- **Segurança**  
  - Spring Security: Basic Auth para API, form-login para views  
- **Documentação de API**  
  - Swagger em `/swagger.html`  
  - OpenAPI JSON em `/v3/api-docs`  
- **Testes**  
  - Collection Postman na pasta `src/.../postman`  
  - Skeleton de testes unitários com `@WebMvcTest`  

---

## 📌 Referências

- **ESTRUTURA.md** – diagrama de pastas e pacotes  
- **GUIA-DE-USO.md** – como executar e testar endpoints  
- **APRESENTACAO.md** – desafios, soluções e roadmap futuro  

---


A seguir, mapeamento dos requisitos do desafio e o que já foi efetivamente entregue no **Sistema Projeto Ada**.

| Requisito                                         | Status        | Onde conferir / detalhes                                                                                                                                     |
|---------------------------------------------------|---------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Persistência em banco de dados (H2)**           | ✔ Implementado  | – Configurado em `application.properties` (H2 em memória)<br>– `DataInitializer` popula dados na inicialização (substituiu `data.sql`)                        |
| **Consumo de API externa pública** (opcional)     | ❌ Não feito    | Ainda não há integração com API externa. Futuramente pode ser adicionado em novo módulo de serviço.                                                           |
| **Autenticação Básica** (opcional)                | ✔ Implementado  | – `SecurityConfig` define `apiFilterChain` com `.httpBasic()` para `/api/**`<br>– usuário em memória (`apiuser`/`apipassword`)                                 |
| **Swagger / OpenAPI** (opcional)                  | ✔ Implementado  | – Configurado em `OpenApiConfig`<br>– UI disponível em `/swagger.html` e JSON em `/v3/api-docs`                                                              |
| **Frontend** (opcional)                           | ✔ Implementado  | – Thymeleaf templates para `login.html`, `cadastro.html`, páginas de módulos (lógica-programacao, poo1)<br>– CSS em `src/main/resources/static/css`           |
| **Entrega no GitHub ou zip**                      | ✔ Implementado  | Repositório público em GitHub: https://github.com/yuricapella/projeto-ada                                                                                   |
| **Documentação no README.md**                     | ✔ Implementado  | – `README.md` com resumo do projeto e links para:<br> • `ESTRUTURA.md` (diagrama de pastas)<br> • `GUIA-DE-USO.md`<br> • `APRESENTACAO.md`<br> • `DIAGRAMA.md`                     |
| **Diagramas das classes principais**              | ✔ Implementado  | – Diagrama de classes de usuário, serviços e controllers em `DIAGRAMA.md`                                                                                              |
| **Guia de uso**                                   | ✔ Implementado  | – `GUIA-DE-USO.md` explicita como importar Postman, acessar H2, usar Swagger e navegar na interface web                                                       |
| **Apresentação dos desafios e “perrengues”**      | ✔ Implementado  | – `APRESENTACAO.md` documenta problemas (datas iguais, segurança, separação API/View) e soluções adotadas                                                     |

---

**Resumo das entregas**  
- Banco H2 com inicialização programática (`DataInitializer`).  
- CRUD de usuários com API REST (JSON) e interface HTML (login/cadastro).  
- Segurança segmentada: Basic Auth para API, form-login para views.  
- Swagger UI para explorar/documentar endpoints.  
- Estrutura de pastas documentada, guia de uso e apresentação de desafios.  

O que **ainda falta**:  
- Consumo de API externa pública.  
- Implementar e validar testes automatizados.  
- Completar lógica dos módulos “lógica-programacao” e “poo1” e integrá-los à API.


[🔝 Voltar ao topo](#voltar-ao-topo)
