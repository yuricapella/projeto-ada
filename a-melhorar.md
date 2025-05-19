em poo1, clientes, adicionar botao de filtrar por tipo.
adicionar caixa para digitar nome ou id e filtrar também
Listar por ordenações

Quantidade de clientes que serão mostrados na pagina, 10,25,50. (acho que é paginação o nome), já fiz via postman uma vez
tem que ver como faz no site

---------------------
atrelar as informações ao usuario logado, para que outro usuario nao possa ver as mesmas coisas. (tentarei ao finalizar o projeto com ele funcional)
---------------------

colocar no html os usuarios disponiveis para logar no site
----------------------
Usar a api viacep vista na aula para pegar o cep e as informações do cliente no projeto poo1 automaticamente, 
mas também será possivel digitar manualmente o endereço.
----------------------
usar enum para status das transações,criações,para ativar,desativar, finalizar, clientes, veiculos e principalmente locações ao "deletar".
as listagens podem pegar somente os status ativos, e também uma opção de filtrar os inativos.
veiculos alugados nao aparecem na lista de veiculos por exemplo.
locações canceladas ou finalizadas nao aparecem na lista de locações.
----------------------
colocar verificação de cpf ou cnpj em cliente para nao deixar criar o mesmo
fazer uma verificação se for tipo CPF, só pode 11 digitos, se for tipo CNPJ, só pode 14 digitos
---------------------
ter validações de, nao poder excluir cliente e nem veiculo se eles já tiverem locações ativas.
------------
se eu tento atualizar veiculo com a mesma placa da erro de duplicado,
-----------
colocar tratamento de exceção para veiculo na view, placa duplicada da white label page

----
adicionar filtragem em numero de cliente ou até outro campo só para dd do telefone mas enfim
telefone tem 11 digitos no total, daria para fazer alguma conversão para mostrar (11) 99999-9999, mesmo salvando 11999999999
colocar também min e maximo de 11 digitos na validação
---------
criar classes de validação para ter regras de negocio na service e também ter mais testes
exemplo: ValidadorUsuario  com metodos que validam nome,email,senha, igual as validações do dto.
Verificar se é necessário criar exceções para cada tipo de atributo, nome invalido ou nome nulo, nome vazio
ou talvez só chamar o methodargumentnotvalid ou com esses nomes especificos extender dele, muitas possibilidades.

focar em entregar todos os testes como o projeto está atualmente, depois se sobrar tempo fazer teste de interface com selenium
e se ainda tiver tempo, colocar as validações para entregar testes com melhor boa pratica.
----------
-colocar checklist-
Verificar como criar profiles para separar produção e testes.
Parece que repository tem que ter uma profile diferente também pois 
pode ter dependencias diferentes e demorar mais que os testes unitários geralmente.
- pelo que verifiquei apenas quando usa @SpringTestBoot ou @DataJPATest algo assim para poder colocar @activeprofile e separar
- até o momento só consegui colocar no teste de usuario repository.
----------
usar aquele tipo de teste que roda com varios argumentos, parametrized test? em teste de badrequest para testar mais cenarios
----------