# TigreQuest

Problema inical: Falta de organização na hora do empréstimo dos equipamentos, dificultando saber quem está com o equipamento. Não conseguir saber com quem está atualmente com o equipamento. Não estar catalogado todos os empréstimos para ajudar os que entregam com defeitos.

Super classe Itens{
*será uma classe abstrada
*terá um atributo que contabiliza o número de itens disponíveis para empréstimo.

Super classe Bolas
-Bola_futsal
-Bola_volei
-Bola_basquete
@Regra Bolas: pode ficar com a pessoa até o fim do dia.

Classe Raquete_tenis_mesa
@Regras Raquete: pode ficar com a pessoa até dois.

Classe Coletes
@Regra Coletes: pode ficar com a pessoa até o fim do dia.

Classe Bicicletas
@Regra Bicicletas: pode ficar com uma pessoa até uma semana e necessitam de vistoria na devolução
(pneu, freio e corrente) *será feito uma função que verifica os atributos na hora da devolução.

}Itens


Super classe Pessoa{

*função empréstimo terá duas verificações, sendo elas: se a pessoa está com bloqueio/atraso e se o item está disponível para empréstimo.
*criar uma variável "dias atrasados" que contabiliza o atraso da pessoa, usando o valor dessa variável para calcular bloqueio de dias da pessoa.
 
Subclasses:
-Alunos
@Regra Alunos: até 2 itens ao mesmo tempo *uma função que verifica a quantidade de itens
emprestados por uma pessoa.

-Servidores
@Regra Servidores: até 4 itens ao mesmo tempo *será usado polimorfismo da função que verifica a quantidade de uma pessoa.

}Pessoa
*será uma classe abstrada

17/10
Pedro 1ª etapa: 
-Definir as classes e seus atributos
-Criar as herenças e defini-lás

17/10
Seiji 2ª etapa:
-Esboços dos métodos
-Fazer o encapsulamento

18/10
Pedro 3ª etapa:
-Fazer o polimorfismo
-Terminar os métodos

18/10
Seiji 4ª etapa:
-Sobreescrita dos métodos das Superclasses nas Subclasses

20/10
Pedro 5ª etapa:
-Testes e entradas do usuário

20/10
Seiji 6ª etapa:
-Verificações de erros(encapsulamentos e excessões)

21/10
Pedro 7ª etapa:
-Modelar e esboçar o histórico de empréstimos

21/10
Seiji 8ª etapa:
-Teste do histórico e teste final do programa