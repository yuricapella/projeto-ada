# Anotações e Melhorias para o Projeto

## Funcionalidades a Adicionar

* Em **clientes**, adicionar:

  * Botão para **filtrar por tipo**
  * Caixa para digitar **nome ou ID** e filtrar
  * Opção para **listar por ordenações**
* Adicionar **quantidade de clientes por página**: 10, 25 ou 50 (paginação). Já testado via Postman, precisa implementar no site.
* Adicionar **filtragem por número de cliente** ou outro campo (ex: apenas o DDD do telefone).
* Em telefone:

  * Aplicar máscara para exibir como **(11) 99999-9999**
  * Validar número com mínimo e máximo de **11 dígitos**

## Integrações e Dados Dinâmicos

* Utilizar a **API ViaCEP** (vista na aula) para preencher os dados do cliente automaticamente via CEP.

  * Também permitir **inserção manual** do endereço.
* Mostrar no HTML os **usuários disponíveis para login**.

## Validações e Regras de Negócio

* Criar **validações específicas** para CPF e CNPJ:

  * Não permitir duplicados
  * CPF: exatamente **11 dígitos**
  * CNPJ: exatamente **14 dígitos**
* Criar **validações de negócio**:

  * Não permitir **excluir cliente ou veículo com locações ativas**
  * Evitar erro de **placa duplicada** ao atualizar veículo
  * Tratar exceção de placa duplicada na **view** (evitar página branca do WhiteLabel)

## Enumerações e Status

* Utilizar **enum** para controlar status (ativo, inativo, cancelado, finalizado etc.) em:

  * Clientes
  * Veículos
  * Locações
* Listagens devem mostrar **somente itens ativos** por padrão, com opção para filtrar inativos.

  * Ex: veículos alugados não aparecem na listagem de veículos
  * Locações canceladas ou finalizadas não aparecem na listagem de locações

## Segurança e Usuário Logado

* Atrelar todas as informações ao **usuário logado**

  * Um usuário não pode visualizar os dados de outro
  * Planejado para ser implementado ao final, com o sistema funcional

## Testes

* Criar **classes de validação** com regras de negócio, aplicadas no service

  * Exemplo: `ValidadorUsuario` com validações de nome, e-mail, senha (similar ao DTO)
  * Avaliar se deve lançar exceções específicas (ex: `NomeInvalidoException`) ou usar `MethodArgumentNotValidException`
* **Cobrir todos os testes** possíveis com o estado atual do projeto

  * Se sobrar tempo:

    * Criar testes de interface com **Selenium**
    * Adicionar validações no código para aumentar cobertura com **boas práticas**
* Verificar uso de **testes parametrizados** (`@ParameterizedTest`) em cenários de Bad Request
* Verificar como **criar profiles** para separar produção e testes:

  * Usar `@ActiveProfiles` com `@SpringBootTest`, `@DataJpaTest`, etc.
  * Criado perfil para **RepositoryTests** e **ViewControllerTests**

## Boas Práticas Adotadas

* Na controller:

  * Usado `setControllerAdvice()` no `MockMvc` para capturar e testar erros
  * Exemplo no caso de usuário:

    ```java
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerAdviceRest(), new UsuarioControllerAdviceRest())
                .build();
    ```

## Checklist

* [ ] Filtragem por tipo em cliente
* [ ] Filtragem por nome/ID em cliente
* [ ] Ordenações nas listagens
* [ ] Paginação de clientes (10, 25, 50)
* [x] Atrelar dados ao usuário logado
* [x] Listar usuários demo no HTML (bonus: pode-se clicar para logar automaticamente)
* [ ] ViaCEP integrado ao cadastro de cliente
* [ ] Enum para controle de status
* [ ] Filtrar por status ativo/inativo
* [ ] Ocultar veículos alugados e locações encerradas
* [ ] Validações CPF/CNPJ
* [x] Restrições para exclusão com locações ativas
* [x] Tratamento de exceções para placas duplicadas
* [ ] Máscara e validação de telefone
* [ ] Classes de validação no service
* [ ] Testes completos e organizados
* [x] Testes com `@ParameterizedTest` (feito no teste de interface de calcular salarios)
* [x] Profiles separados para produção/teste

---

adiciona um botao para criar clientes, veiculos, locações fakes do usuario agora que estão atrelados ao id do usuario.
caso queira testar na interface.

agora que os clientes estão atrelados por usuarios, os seus ids continuam geral, se eu crio cliente 1 com usuario 1
se eu logar no usuario 5 e criar o cliente 2, vai aparecer o numero 2, verificar um jeito de resetar id do cliente por usuario.

Ou, melhor seria nao expor o id e fazer a locação por cpf e placa do veiculo por exemplo.

* [x] Botão para criar dados fake atrelados ao usuário logado
* [ ] Não expor ID na interface para clientes/veículos/locações
* [ ] Referenciar cliente por CPF e veículo por placa ao criar locação

Colocar DTO em consumidor e revisar dtos dos outros projetos também.