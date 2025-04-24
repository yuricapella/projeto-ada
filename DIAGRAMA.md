<a id="voltar-ao-topo"></a>
[⬅ Voltar ao README](README.md)
# Diagramas das Classes Principais

Este documento apresenta, em estilo UML ASCII, os diagramas das principais classes do Projeto Ada.

---

## 1. Diagrama de Usuário e Serviços

```plaintext
+------------------------------+            +------------------------------+
|          Usuario             |            |    UsuarioRepository «repo»  |
+------------------------------+            +------------------------------+
| - id: Long                   |<>----------| extends JpaRepository<Usuario, Long>
| - nome: String               |            +------------------------------+
| - email: String              |            
| - senha: String              |            +------------------------------+
| - dataCriacao: LocalDateTime |            |  CriarUsuarioService «service» |
| - dataAtualizacao: LocalDateTime |         +------------------------------+
+------------------------------+            | - repository: UsuarioRepository |
| +getId(): Long              |            | - passwordEncoder: PasswordEncoder |
| +setSenha(String): void     |            +------------------------------+
+------------------------------+            | +criarUsuario(dto): Usuario  |
                                            +------------------------------+
                                            
                                            +------------------------------+
                                            |BuscarUsuarioService «service»|
                                            +------------------------------+
                                            | - repository: UsuarioRepository |
                                            +------------------------------+
                                            | +buscarTodos(): List<UsuarioDTO> |
                                            | +buscarPorId(id): Usuario      |
                                            +------------------------------+

                                            +------------------------------+
                                            |AtualizarUsuarioService «service»|
                                            +------------------------------+
                                            | - repository: UsuarioRepository |
                                            | - buscarService: BuscarUsuarioService |
                                            | - passwordEncoder: PasswordEncoder   |
                                            +------------------------------+
                                            | +atualizarUsuario(dto, id): void    |
                                            +------------------------------+

                                            +------------------------------+
                                            |DeletarUsuarioService «service»     |
                                            +------------------------------+
                                            | - repository: UsuarioRepository    |
                                            +------------------------------+
                                            | +deletarPorId(id): void            |
                                            +------------------------------+
```

---

## 2. Diagrama de Controllers e Segurança

```plaintext
+------------------------------+           +-------------------------------+
|UsuarioApiController «controller»|        |UsuarioViewController «controller»|
+------------------------------+           +-------------------------------+
| +GET /api/usuarios           |           | +POST /usuarios/cadastro     |
| +GET /api/usuarios/{id}      |           +-------------------------------+
| +POST /api/usuarios          |                     ^
| +PUT /api/usuarios/{id}      |                     |
| +DELETE /api/usuarios/{id}   |                     |
+------------------------------+                     |
           ^                                       renders
           | calls                                 |
           v                                       |
+------------------------------+                   |
|SecurityConfig «config»       |<------------------+
+------------------------------+
| - apiFilterChain()           |
| - webFilterChain()           |
+------------------------------+

+------------------------------+
|MainController «controller»    |
+------------------------------+
| +GET / → redirect:/login      |
| +GET /login → "login/login"  |
| +GET /cadastro → "cadastro/cadastro" |
| +GET /home [@PreAuthorize] → "home" |
+------------------------------+
| +GET / -> redirect /login     |
| +GET /home                    |
+------------------------------+
           ^
           |
           | includes nav fragment
           v
+------------------------------+
| navigation.html «template»    |
+------------------------------+
| <a href="/logout">Sair</a>   |
+------------------------------+
```

---

## 3. Diagrama de Views (Thymeleaf)

```plaintext
+-------------+       +---------------+
| login.html  |<----->| header.html   |
+-------------+       +---------------+
| form login  |       | navbar (inclui logout) |
+-------------+       +---------------+

+----------------+
| cadastro.html  |
+----------------+
| form cadastro  |
+----------------+
```

---

*Esses diagramas resumem as principais classes, serviços, controllers e templates do projeto.*
[🔝 Voltar ao topo](#voltar-ao-topo)
