# Controle de Candidatos - Processo Seletivo
Programa desenvolvido como parte do treinamento "Bootcamp Santander" promovido pela Digital Innovation One

## Sobre o programa
O escopo do software é ler uma lista de candidatos a um processo seletivo e retornar uma lista de aprovados. Para isso algumas etapas devem ser atendidas:

1. O objetivo do programa é selecionar um total de 5 candidatos que atendam aos requisitos de contratação.
- Se na primeira chamada este objeto for concluído, o processo seletivo dá-se como encerrado.
- Se na primeira chamada não existir 5 candidatos aprovados, é realizada uma segunda chamada.

2. O salário pretendido deve estar de acordo com o salário oferecido.
- Candidatos que tiveram salário igual serão consultados pela possibilidade de negociação na primeira chamada.
- Candidatos que tiveram salário igual ou maior serão consultados pela possibilidade de negociação na segunda chamada.

3. Durante a primeira chamada, os candidatos que previamente atenderam ao requisito salarial serão contatados.
- Se atenderem até um total de 3 tentativas, serão colocados na lista de aprovados.
- Se não atenderem até um total de 3 tentativas, serão novamente contatados na segunda chamada.
