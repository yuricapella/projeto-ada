# Guia de Uso

<a id="voltar-ao-topo"></a>  
[🔝 Voltar ao topo](#voltar-ao-topo)

## 📬 1. Acesso à Collection do Postman

🔗 [Visualizar no Postman (modo viewer)](https://yuricapella.postman.co/workspace/Yuri-Capella's-Workspace~eed12cec-649d-4622-8f2c-fee779577473/collection/43702238-1bf00d63-906d-4f84-83f7-46e258bf48d8?share=true&origin=sidebar)

📁 [Baixar arquivo da collection](https://github.com/yuricapella/projeto-ada/tree/main/src/main/java/tech/ada/projeto_ada/postman)

> Você pode importar esse arquivo `.json` diretamente no Postman para testar as rotas da API, escolha **Import** ▶ **File**.

## 2. Endpoints Principais da API

Base URL: `http://localhost:8080`

| Método | URL                        | Descrição                            | Autorização        |
|--------|----------------------------|--------------------------------------|--------------------|
| GET    | `/api/usuarios`            | Lista todos os usuários              | Basic Auth* / aberto |
| GET    | `/api/usuarios/{id}`       | Busca usuário por ID                 | Basic Auth* / aberto |
| POST   | `/api/usuarios`            | Cria novo usuário (JSON)             | aberto             |
| PUT    | `/api/usuarios/{id}`       | Atualiza usuário existente (JSON)    | Basic Auth* / aberto |
| DELETE | `/api/usuarios/{id}`       | Remove usuário                       | Basic Auth* / aberto |

\* Para autenticar (caso ainda ative segurança):  
```
username: apiuser
password: apipassword
```

### 2.1 Formato JSON para criação/atualização

```json
{
  "nome": "Rodrigo Silva",
  "email": "rodrigo.silva@email.com",
  "senha": "senha123"
}
```

## 3. Acesso ao H2 Console

- URL: `http://localhost:8080/h2`
- JDBC URL: `jdbc:h2:mem:test`
- Username: `sa`
- (senha em branco)

## 4. Swagger / OpenAPI

- A documentação interativa está disponível em:  
  `http://localhost:8080/swagger.html`
- Os endpoints OpenAPI em JSON estão em:  
  `http://localhost:8080/v3/api-docs`

## 5. Interface Web (Thymeleaf)

- Ao acessar `http://localhost:8080`, você será redirecionado para a tela de **login**.
- **Usuários** já pré-populados pelo `DataInitializer` podem fazer login imediatamente.
- Para criar nova conta: clique em **Não tem uma conta? Cadastre-se**.
- Após cadastro, use as credenciais para efetuar login.
- Logout disponível no menu.

## 6. Observações

- A API REST de usuários está 100% funcional, com tratamento de exceções e mensagens personalizadas.
- Módulos de **Lógica de Programação** e **POO 1** estão presentes como páginas esqueléticas e serão integrados à API em breve.
- Testes foram estruturados (Postman e mocks) mas ainda não possuem validações automatizadas.


<a id="voltar-ao-topo"></a>  
[🔝 Voltar ao topo](#voltar-ao-topo)

