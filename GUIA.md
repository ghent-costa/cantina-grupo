# Cantina: o código dividido em 5 partes

Cada parte é um arquivo que **compila e roda sozinho** e fica com uma pessoa do grupo. Cada arquivo acrescenta a sua parte ao anterior. A **Parte5.java é o código completo** para entregar.

| Parte | Responsável | Roteiro | O que a pessoa explica |
|---|---|---|---|
| 1 | ________ | 3.1 e 3.2 | Constantes, login, caixa inicial e menu |
| 2 | ________ | 3.4 e 3.5 | Consultar e repor estoque |
| 3 | ________ | 3.3 (passos 1 a 4) | Escolher os itens da venda (estoque reservado) |
| 4 | ________ | 3.3 (passos 5 e 6) e seção 4 | Desconto, pagamento, troco, cupom e totais |
| 5 | ________ | 3.6 e 3.7 | Relatório parcial e fechamento do caixa |

Para ver só o que cada pessoa fez: no VS Code, clique com o botão direito num arquivo, "Select for Compare", depois no da parte seguinte, "Compare with Selected".

**Importante:** na apresentação, o professor pode chamar qualquer pessoa para explicar qualquer trecho. Cada um apresenta a sua parte, mas todo mundo deve ler o guia inteiro.

---

## Base para todos: `equals` e `!`

Para comparar **números** usamos `==`. Para comparar **textos** (String) usamos `.equals(...)`:

```java
if (senha.equals(SENHA))      // a senha digitada é "cantina2026"?
if (estudante.equals("S"))    // a pessoa digitou S?
```

Com texto, o `==` não compara as letras. Ele compara se é "a mesma caixinha na memória", e o texto digitado é sempre uma caixinha nova. Por isso **o login não funciona sem `equals`**.

O `!` quer dizer "NÃO": `!estudante.equals("S")` significa "estudante NÃO é S".

Para aceitar maiúscula e minúscula, o código testa as duas: `estudante.equals("S") || estudante.equals("s")`.

---

## Parte 1: abertura do caixa e menu

**O roteiro pede:** constantes de preço e estoque; nome e senha (`cantina2026`) com no máximo 3 tentativas; caixa inicial que não pode ser negativo; boas-vindas; menu que se repete até fechar o caixa.

**Como funciona:**

- `final` torna a variável uma constante, que não pode mudar. Os estoques não são `final` porque mudam ao vender e ao repor.
- O login fica em `while (tentativas < 3 && autenticado == false)`. Ele para quando acabam as tentativas **ou** quando a pessoa acerta.
- `tentativas = tentativas + 1` conta cada tentativa, e `3 - tentativas` mostra quantas restam.
- `System.exit` é proibido. Por isso o código usa `if (autenticado == false) { bloqueia } else { resto do programa }`: se errar 3 vezes, o programa só chega ao fim.
- Caixa inicial: lê o valor e, **enquanto** for negativo, avisa e lê de novo. Esse jeito "lê, valida com while, lê de novo" aparece no programa todo.
- Menu: `boolean caixaAberto = true;` e `while (caixaAberto == true)`. O menu se repete até alguém fazer `caixaAberto = false`, o que só acontece no fechamento.
- `Integer.parseInt(...)` transforma o texto digitado em número. Os `if / else if` escolhem a opção, e o `else` do final pega opção inválida.

**Teste:** errar a senha 3 vezes; errar 1 e acertar; caixa `-10` e depois `100`; no menu, `9` (inválida) e depois `5`.

**Perguntas prováveis:** Por que `equals` e não `==`? Como o programa termina sem `System.exit`? Por que o menu fica dentro do `else`?

---

## Parte 2: consultar e repor estoque

**O roteiro pede:** mostrar o estoque com ESTOQUE BAIXO (até 5) e ESGOTADO (zero); repor com código de 1 a 4 e quantidade maior que zero.

**Como funciona:**

- **Consultar:** para cada produto há três casos. Se for `0`, mostra ESGOTADO; senão, se for `<= 5`, mostra ESTOQUE BAIXO; senão, mostra só o número.
- **O teste do zero vem primeiro** porque 0 também é menor que 5. Na ordem contrária, um produto zerado apareceria como "estoque baixo".
- **Repor:** valida o código com `while (cod < 1 || cod > 4)`, valida a quantidade com `while (qtd <= 0)` e soma no estoque do produto escolhido.
- O código é repetido para os 4 produtos porque não pode usar vetor. O roteiro avisa que é esperado.

**Teste:** repor com código `7`, quantidade `-1` e `0` (todos recusados), depois `5` pães; consultar e ver 35.

**Perguntas prováveis:** Por que o ESGOTADO é testado antes? O que acontece se digitar o código 0 na reposição?

---

## Parte 3: registrar venda, escolha dos itens

É a parte mais importante do trabalho.

**O roteiro pede:** mostrar os produtos e pedir o código (0 encerra, fora de 0 a 4 é recusado); pedir a quantidade (maior que zero e até o estoque); mostrar o subtotal; permitir pedir o mesmo produto de novo; cancelar a venda se não tiver item. E o estoque **só baixa quando a venda é confirmada**.

**A ideia do "reservado":**

- Durante a venda, o código guarda quanto de cada produto já foi pedido em `reservadoCoxinha`, `reservadoPao`, `reservadoSuco` e `reservadoCafe`. Essas variáveis começam em 0 a cada venda nova.
- O que ainda dá para vender nesta venda é `estoque - reservado`.
- Exemplo do roteiro: tem 8 coxinhas. A pessoa pede 5, o reservado vira 5 e sobram 3. Se pedir mais 5, o programa recusa e mostra "Disponível: 3".

**O laço da venda:**

- `int codigo = -1;` começa em -1 só para o `while (codigo != 0)` rodar a primeira vez.
- Há um `if` para cada produto (1, 2, 3, 4). Dentro de cada um: calcula o disponível; se for 0, avisa que esgotou; senão, pede a quantidade, valida, soma no reservado, calcula o subtotal e soma no `totalBruto`.
- `else if (codigo != 0)` pega os códigos inválidos.
- Se a pessoa digita 0 sem ter pedido nada (`totalBruto == 0`), a venda é cancelada.

**Teste:** vender 12 coxinhas (sobram 8). Em outra venda, pedir 5 e depois mais 5: o segundo pedido tem que ser recusado.

**Perguntas prováveis:** Para que serve o reservado? Por que o `codigo` começa em -1? Por que não baixar o estoque na hora em que o item é pedido?

---

## Parte 4: registrar venda, pagamento e cupom

**Totais do dia:** as variáveis `vendidosCoxinha` (e as dos outros produtos), `qtdVendas`, `totalArrecadado`, `totalDinheiro`, `totalPix`, `totalCartao`, `dinheiroRecebido`, `trocoDevolvido` e `maiorVenda` ficam no começo do programa, **fora** do menu. Assim elas não zeram a cada venda.

**Regras, na ordem da seção 4:**

1. **Estudante:** pergunta S/N e repete se a resposta for outra. O desconto de 10% só vale se for estudante **e** o total bruto for `>= 20`.
2. **Pagamento:** 1, 2 ou 3, e repete se for inválido.
3. **Cartão:** acréscimo de 3% sobre o valor **já com desconto**.
4. **Troco:** só no dinheiro. Enquanto o valor recebido for menor que o total, o programa mostra quanto falta e pede de novo. O troco é recebido menos o total.
5. **Cupom:** para cada produto, `if (reservadoCoxinha > 0)` imprime a linha dele, então só aparecem os produtos da venda.
6. **Confirmação:** tira do estoque (`estoque = estoque - reservado`), soma em `vendidos`, soma no total da forma de pagamento e verifica se é a maior venda do dia.

**`totalDinheiro` x `dinheiroRecebido`:** `totalDinheiro` é o valor das vendas pagas em dinheiro, que vai no relatório. `dinheiroRecebido` é a soma das notas que os clientes entregaram, que entra na conta do caixa junto com o troco.

**Teste (exemplo do roteiro):** 2 coxinhas e 2 sucos, cliente estudante, pagando no cartão. Deve dar total bruto 23,00, desconto 2,30, acréscimo 0,62 e total final 21,32. Pagando em dinheiro com nota de 50: total final 20,70 e troco 29,30.

**Perguntas prováveis:** Por que o acréscimo vem depois do desconto? (A seção 4 manda essa ordem, e o exemplo confirma: 3% de 20,70.) O que acontece se o cliente der dinheiro a menos?

---

## Parte 5: relatório parcial e fechamento

**Relatório parcial (opção 4):**

- **Ticket médio:** se `qtdVendas == 0`, mostra "nenhuma venda" em vez de dividir por zero.
- **Mais vendido:** o código começa supondo que é a coxinha e compara com cada um dos outros. Se algum vendeu **mais**, ele passa a ser o mais vendido. Em caso de empate fica o primeiro, e o roteiro permite isso.

**Fechar caixa (opção 5):**

- Pergunta S/N. Se a resposta for N, não faz nada e o menu volta sozinho.
- Se for S, mostra o relatório (uma cópia do relatório da opção 4) e depois o caixa: **valor final = inicial + dinheiro recebido - troco devolvido**.
- **Gráfico:** para cada produto, um `for` imprime um `#` por unidade vendida usando `IO.print`, que não pula linha. Depois o `IO.println` mostra o número e pula a linha.
- `caixaAberto = false;` faz o menu parar, e o programa termina com a despedida.

**Teste:** abrir o relatório sem vendas; fazer vendas; opção 5 com `x` (inválido), depois `N` (volta ao menu), depois 5 com `S`.

**Perguntas prováveis:** Como evita a divisão por zero? Como funciona o "mais vendido"? Por que o relatório aparece repetido no código? (Porque não pode criar funções.)

---

## Cuidados para o dia

- Ao digitar valores com centavos, use **ponto**: `50.00` ou `12.5`. Com vírgula, o `Double.parseDouble` dá erro.
- Os valores na tela podem aparecer com vírgula ou com ponto, dependendo do idioma do computador. Os dois estão certos.
