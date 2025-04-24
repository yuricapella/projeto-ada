```
├── .gitattributes
├── .gitignore
├── .mvn
    └── wrapper
    │   └── maven-wrapper.properties
├── ESTRUTURA.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    ├── main
        ├── java
        │   └── tech
        │   │   └── ada
        │   │       └── projeto_ada
        │   │           ├── ProjetoAdaApplication.java
        │   │           ├── config
        │   │               ├── ControllerAdviceRest.java
        │   │               ├── DataInitializer.java
        │   │               ├── OpenApiConfig.java
        │   │               └── SecurityConfig.java
        │   │           ├── controller
        │   │               └── MainController.java
        │   │           ├── exception
        │   │               ├── ErroCodigo.java
        │   │               ├── ErroPadrao.java
        │   │               └── UsuarioNaoEncontradoException.java
        │   │           ├── logica_programacao
        │   │               ├── controller
        │   │               │   └── SalaryController.java
        │   │               ├── model
        │   │               │   └── Salary.java
        │   │               ├── service
        │   │               │   └── SalaryService.java
        │   │               └── util
        │   │               │   ├── TabelaDescontosImpostoDeRenda.java
        │   │               │   └── TabelaDescontosInss.java
        │   │           ├── postman
        │   │               └── projeto_ada.postman_collection.json
        │   │           ├── programacao_orientada_a_objetos_1
        │   │               ├── controller
        │   │               │   ├── ClienteController.java
        │   │               │   ├── HomeController.java
        │   │               │   ├── LocacaoController.java
        │   │               │   └── VeiculoController.java
        │   │               ├── model
        │   │               │   ├── Cliente.java
        │   │               │   ├── Locacao.java
        │   │               │   ├── ServicoLuxo.java
        │   │               │   ├── TipoCliente.java
        │   │               │   ├── TipoVeiculo.java
        │   │               │   ├── Veiculo.java
        │   │               │   └── VeiculoLuxo.java
        │   │               ├── repository
        │   │               │   ├── ClienteRepository.java
        │   │               │   ├── LocacaoRepository.java
        │   │               │   └── VeiculoRepository.java
        │   │               └── service
        │   │               │   ├── ClienteService.java
        │   │               │   ├── LocacaoService.java
        │   │               │   └── VeiculoService.java
        │   │           └── usuario
        │   │               ├── controller
        │   │                   ├── UsuarioApiController.java
        │   │                   └── UsuarioViewController.java
        │   │               ├── dto
        │   │                   ├── CriarUsuarioRequestDTO.java
        │   │                   ├── UsuarioResponseDTO.java
        │   │                   └── mapper
        │   │                   │   ├── CriarUsuarioRequestMapper.java
        │   │                   │   └── UsuarioResponseMapper.java
        │   │               ├── model
        │   │                   └── Usuario.java
        │   │               ├── repository
        │   │                   └── UsuarioRepository.java
        │   │               ├── service
        │   │                   ├── AtualizarUsuarioService.java
        │   │                   ├── BuscarUsuarioService.java
        │   │                   ├── CriarUsuarioService.java
        │   │                   └── DeletarUsuarioService.java
        │   │               └── util
        │   │                   └── FormataData.java
        └── resources
        │   ├── application.properties
        │   ├── static
        │       └── css
        │       │   ├── poo1
        │       │       └── style.css
        │       │   └── style.css
        │   └── templates
        │       ├── cadastro
        │           └── cadastro.html
        │       ├── fragments
        │           ├── footer.html
        │           ├── header.html
        │           └── navigation.html
        │       ├── home.html
        │       ├── logica_programacao
        │           ├── form.html
        │           ├── home.html
        │           └── result.html
        │       ├── login
        │           └── login.html
        │       └── poo1
        │           ├── cliente
        │               └── list.html
        │           ├── home.html
        │           ├── locacao
        │               ├── create.html
        │               └── list.html
        │           └── veiculo
        │               ├── list-tipo.html
        │               └── list.html
    └── test
        └── java
            └── tech
                └── ada
                    └── projeto_ada
                        ├── ProjetoAdaApplicationTests.java
                        ├── controller
                            └── MainControllerTest.java
                        ├── logica_programacao
                            ├── controller
                            │   └── SalaryControllerTest.java
                            └── service
                            │   └── SalaryServiceTest.java
                        ├── programacao_orientada_a_objetos_1
                            ├── controller
                            │   ├── ClienteControllerTest.java
                            │   ├── LocacaoControllerTest.java
                            │   └── VeiculoControllerTest.java
                            └── service
                            │   ├── ClienteServiceTest.java
                            │   ├── LocacaoServiceTest.java
                            │   └── VeiculoServiceTest.java
                        └── usuario
                            ├── controller
                                └── UsuarioControllerTest.java
                            └── service
                                ├── AtualizarUsuarioServiceTest.java
                                ├── BuscarUsuarioServiceTest.java
                                ├── CriarUsuarioServiceTest.java
                                └── DeletarUsuarioServiceTest.java
```
