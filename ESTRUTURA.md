```
├── .gitattributes
├── .gitignore
├── .mvn
    └── wrapper
    │   └── maven-wrapper.properties
├── GUIA-DE-USO.md
├── PROJETO-FINAL.md
├── README.md
├── a-melhorar.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── postman
    └── projeto_ada.postman_collection.json
└── src
    ├── main
        ├── java
        │   └── tech
        │   │   └── ada
        │   │       └── projeto_ada
        │   │           ├── ProjetoAdaApplication.java
        │   │           ├── config
        │   │               ├── ControllerAdviceRest.java
        │   │               ├── OpenApiConfig.java
        │   │               ├── SecurityConfig.java
        │   │               └── inicializador
        │   │               │   ├── ClienteDataInitializer.java
        │   │               │   ├── LocacaoDataInitializer.java
        │   │               │   ├── UsuarioDataInitializer.java
        │   │               │   └── VeiculoDataInitializer.java
        │   │           ├── controller
        │   │               └── MainController.java
        │   │           ├── exception
        │   │               ├── ErroCodigo.java
        │   │               └── ErroPadrao.java
        │   │           ├── logica_programacao
        │   │               ├── controller
        │   │               │   ├── SalaryApiController.java
        │   │               │   └── SalaryViewController.java
        │   │               ├── exception
        │   │               │   ├── SalarioInvalidoException.java
        │   │               │   └── SalaryControllerAdviceRest.java
        │   │               ├── model
        │   │               │   └── Salary.java
        │   │               ├── service
        │   │               │   └── SalaryService.java
        │   │               └── util
        │   │               │   ├── TabelaDescontosImpostoDeRenda.java
        │   │               │   └── TabelaDescontosInss.java
        │   │           ├── poo1
        │   │               ├── cliente
        │   │               │   ├── controller
        │   │               │   │   ├── ClienteApiController.java
        │   │               │   │   ├── ClienteViewController.java
        │   │               │   │   └── HomeController.java
        │   │               │   ├── dto
        │   │               │   │   ├── AtualizarClienteRequestDTO.java
        │   │               │   │   ├── ClienteResponseDTO.java
        │   │               │   │   ├── CriarClienteRequestDTO.java
        │   │               │   │   └── mapper
        │   │               │   │   │   ├── AtualizarClienteRequestMapper.java
        │   │               │   │   │   ├── ClienteResponseMapper.java
        │   │               │   │   │   └── CriarClienteRequestMapper.java
        │   │               │   ├── exception
        │   │               │   │   ├── ClienteControllerAdviceRest.java
        │   │               │   │   └── ClienteNaoEncontradoException.java
        │   │               │   ├── model
        │   │               │   │   └── Cliente.java
        │   │               │   ├── repository
        │   │               │   │   └── ClienteRepository.java
        │   │               │   ├── service
        │   │               │   │   ├── AtualizarClienteService.java
        │   │               │   │   ├── BuscarClienteService.java
        │   │               │   │   ├── CriarClienteService.java
        │   │               │   │   └── DeletarClienteService.java
        │   │               │   └── util
        │   │               │   │   └── TipoCliente.java
        │   │               ├── locacao
        │   │               │   ├── controller
        │   │               │   │   ├── LocacaoApiController.java
        │   │               │   │   └── LocacaoViewController.java
        │   │               │   ├── dto
        │   │               │   │   ├── AtualizarLocacaoRequestDTO.java
        │   │               │   │   ├── CriarLocacaoRequestDTO.java
        │   │               │   │   ├── LocacaoResponseDTO.java
        │   │               │   │   └── mapper
        │   │               │   │   │   ├── AtualizarLocacaoRequestMapper.java
        │   │               │   │   │   ├── CriarLocacaoRequestMapper.java
        │   │               │   │   │   └── LocacaoResponseMapper.java
        │   │               │   ├── exception
        │   │               │   │   ├── LocacaoControllerAdvice.java
        │   │               │   │   ├── LocacaoControllerAdviceRest.java
        │   │               │   │   └── LocacaoNaoEncontradaException.java
        │   │               │   ├── model
        │   │               │   │   └── Locacao.java
        │   │               │   ├── repository
        │   │               │   │   └── LocacaoRepository.java
        │   │               │   └── service
        │   │               │   │   ├── AtualizarLocacaoService.java
        │   │               │   │   ├── BuscarLocacaoService.java
        │   │               │   │   ├── CriarLocacaoService.java
        │   │               │   │   └── DeletarLocacaoService.java
        │   │               └── veiculo
        │   │               │   ├── controller
        │   │               │       ├── VeiculoApiController.java
        │   │               │       └── VeiculoViewController.java
        │   │               │   ├── dto
        │   │               │       ├── AtualizarVeiculoRequestDTO.java
        │   │               │       ├── CriarVeiculoRequestDTO.java
        │   │               │       ├── VeiculoResponseDTO.java
        │   │               │       └── mapper
        │   │               │       │   ├── AtualizarVeiculoRequestMapper.java
        │   │               │       │   ├── CriarVeiculoRequestMapper.java
        │   │               │       │   └── VeiculoResponseMapper.java
        │   │               │   ├── exception
        │   │               │       ├── VeiculoControllerAdviceRest.java
        │   │               │       ├── VeiculoIndisponivelException.java
        │   │               │       └── VeiculoNaoEncontradoException.java
        │   │               │   ├── model
        │   │               │       ├── Veiculo.java
        │   │               │       ├── veiculo_comum
        │   │               │       │   ├── Caminhao.java
        │   │               │       │   ├── CarroComum.java
        │   │               │       │   └── Moto.java
        │   │               │       └── veiculo_luxo
        │   │               │       │   ├── CarroPremium.java
        │   │               │       │   ├── ServicoLuxo.java
        │   │               │       │   ├── Suv.java
        │   │               │       │   └── VeiculoLuxo.java
        │   │               │   ├── repository
        │   │               │       └── VeiculoRepository.java
        │   │               │   ├── service
        │   │               │       ├── AtualizarVeiculoService.java
        │   │               │       ├── BuscarVeiculoService.java
        │   │               │       ├── CriarVeiculoService.java
        │   │               │       └── DeletarVeiculoService.java
        │   │               │   └── util
        │   │               │       ├── TipoClasseVeiculo.java
        │   │               │       └── TipoVeiculo.java
        │   │           ├── usuario
        │   │               ├── controller
        │   │               │   ├── UsuarioApiController.java
        │   │               │   └── UsuarioViewController.java
        │   │               ├── dto
        │   │               │   ├── AtualizarUsuarioRequestDTO.java
        │   │               │   ├── CriarUsuarioRequestDTO.java
        │   │               │   ├── UsuarioResponseDTO.java
        │   │               │   └── mapper
        │   │               │   │   ├── AtualizarUsuarioRequestMapper.java
        │   │               │   │   ├── CriarUsuarioRequestMapper.java
        │   │               │   │   └── UsuarioResponseMapper.java
        │   │               ├── exception
        │   │               │   ├── UsuarioControllerAdviceRest.java
        │   │               │   └── UsuarioNaoEncontradoException.java
        │   │               ├── model
        │   │               │   └── Usuario.java
        │   │               ├── repository
        │   │               │   └── UsuarioRepository.java
        │   │               └── service
        │   │               │   ├── AtualizarUsuarioService.java
        │   │               │   ├── BuscarUsuarioService.java
        │   │               │   ├── CriarUsuarioService.java
        │   │               │   ├── DeletarUsuarioService.java
        │   │               │   └── UserDetailsServiceImpl.java
        │   │           └── util
        │   │               └── FormataData.java
        └── resources
        │   ├── application-dev.properties
        │   ├── application.properties
        │   ├── static
        │       └── css
        │       │   └── style.css
        │   └── templates
        │       ├── cadastro
        │           └── cadastro.html
        │       ├── fragments
        │           ├── footer.html
        │           ├── form-validations.html
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
        │               ├── atualizar.html
        │               ├── cadastrar.html
        │               └── listar.html
        │           ├── home.html
        │           ├── locacao
        │               ├── atualizar.html
        │               ├── cadastrar.html
        │               └── listar.html
        │           └── veiculo
        │               ├── atualizar.html
        │               ├── cadastrar.html
        │               └── listar.html
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
                            │   └── SalaryApiControllerTest.java
                            ├── service
                            │   └── SalaryServiceTest.java
                            └── util
                            │   └── TestSalaryPrinter.java
                        ├── perfil
                            └── ConfiguracaoPerfilDevTest.java
                        ├── poo1
                            ├── cliente
                            │   ├── controller
                            │   │   └── ClienteApiControllerTest.java
                            │   ├── repository
                            │   │   └── ClienteRepositoryTest.java
                            │   ├── service
                            │   │   ├── AtualizarClienteServiceTest.java
                            │   │   ├── BuscarClienteServiceTest.java
                            │   │   ├── CriarClienteServiceTest.java
                            │   │   └── DeletarClienteServiceTest.java
                            │   └── util
                            │   │   ├── ClienteCreator.java
                            │   │   └── TestClientPrinter.java
                            ├── locacao
                            │   ├── controller
                            │   │   └── LocacaoApiControllerTest.java
                            │   ├── repository
                            │   │   └── LocacaoRepositoryTest.java
                            │   ├── service
                            │   │   ├── AtualizarLocacaoServiceTest.java
                            │   │   ├── BuscarLocacaoServiceTest.java
                            │   │   ├── CriarLocacaoServiceTest.java
                            │   │   └── DeletarLocacaoServiceTest.java
                            │   └── util
                            │   │   ├── LocacaoCreator.java
                            │   │   └── TestLocacaoPrinter.java
                            └── veiculo
                            │   ├── controller
                            │       └── VeiculoApiControllerTest.java
                            │   ├── repository
                            │       └── VeiculoRepositoryTest.java
                            │   ├── service
                            │       ├── AtualizarVeiculoServiceTest.java
                            │       ├── BuscarVeiculoServiceTest.java
                            │       ├── CriarVeiculoServiceTest.java
                            │       └── DeletarVeiculoServiceTest.java
                            │   └── util
                            │       ├── TestVeiculoPrinter.java
                            │       └── VeiculoCreator.java
                        ├── usuario
                            ├── controller
                            │   └── UsuarioApiControllerTest.java
                            ├── repository
                            │   └── UsuarioRepositoryTest.java
                            ├── service
                            │   ├── AtualizarUsuarioServiceTest.java
                            │   ├── BuscarUsuarioServiceTest.java
                            │   ├── CriarUsuarioServiceTest.java
                            │   ├── DeletarUsuarioServiceTest.java
                            │   └── UserDetailsServiceImplTest.java
                            └── util
                            │   └── TestUsuarioPrinter.java
                        └── util
                            ├── JsonUtil.java
                            └── TestPrinter.java

                            ```
