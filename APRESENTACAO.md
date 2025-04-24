<a id="voltar-ao-topo"></a>
[⬅ Voltar ao README](README.md)

# 🎓 Projeto de Conclusão - Sistema Projeto Ada

## 📌 Contexto Geral

Aplicação Spring Boot com API REST e interface web (Thymeleaf) para cadastro e login de usuários, além de páginas iniciais para módulos didáticos de Lógica de Programação e POO 1.

---

## 🧩 Desafios e Soluções

### Atualização de datas
- Ajustado `CriarUsuarioRequestMapper.updateEntity(...)` para incluir `usuarioExistente.setDataAtualizacao(LocalDateTime.now())`, garantindo que **dataAtualizacao ≠ dataCriacao**.

### Separação API x View
- Criados `UsuarioApiController` (JSON) e `UsuarioViewController` (HTML) para manter responsabilidades distintas.
- Configurado `apiFilterChain` e `webFilterChain` em `SecurityConfig`.
- Temporariamente usado `web.ignoring().requestMatchers("/api/**")` para forçar JSON puro na API.

### Criptografia de senha
- Serviços `CriarUsuarioService` e `AtualizarUsuarioService` agora injetam `PasswordEncoder` e aplicam BCrypt em `criarUsuario(...)` e `atualizarUsuario(...)`.

### Refatorações importantes
- **Branch `develop`** criada para agrupar múltiplas mudanças (facilitou entregas rápidas em vez de várias `feat/` branches).  

### Implementação de thymeleaf
- Foi complicado de implementar o html e css e ainda mesclar com o contexto do spring security, ter que colocar o thymeleaf security e saber como fazer as requisições corretas para login e cadastro funcionarem.
---

## 🚧 Melhorias Futuras
- Testes unitários implementados; a estrutura existe porém precisa de validação real, ainda preciso parar para testar e ver se estão ok.
- Módulos de Lógica de Programação e Promgração Orienta a Objetos 1 estão como esqueleto e não funcionam plenamente; em breve serão parametrizados, ligados à API e protegidos por autenticação de usuário.
- Serão adicionados os demais módulos do curso Ada como páginas (views) integradas à API REST.

---

## Comentários
- Atualmente apenas as requisições de usuário estão totalmente funcionais, com tratamento de exceções e mensagens de erro personalizadas.

---

## ✅ Conclusão

Funcionalidades de usuário (CRUD, login, cadastro) estão operacionais. Demais módulos e testes serão implementados nas próximas iterações.

[🔝 Voltar ao topo](#voltar-ao-topo)
