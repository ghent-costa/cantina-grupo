// PARTE 5 (CÓDIGO FINAL): relatório parcial e fechamento do caixa (roteiro 3.6 e 3.7)
// Novidade: relatório, confirmação, conferência do caixa e gráfico de barras

void main() {

    // constantes (não mudam)
    final String SENHA = "cantina2026";
    final double PRECO_COXINHA = 6.50;
    final double PRECO_PAO = 4.00;
    final double PRECO_SUCO = 5.00;
    final double PRECO_CAFE = 3.00;

    // estoque de cada produto
    int estoqueCoxinha = 20;
    int estoquePao = 30;
    int estoqueSuco = 15;
    int estoqueCafe = 40;

    // quantas unidades de cada produto foram vendidas no dia
    int vendidosCoxinha = 0;
    int vendidosPao = 0;
    int vendidosSuco = 0;
    int vendidosCafe = 0;

    // totais do dia
    int qtdVendas = 0;
    double totalArrecadado = 0;
    double totalDinheiro = 0;
    double totalPix = 0;
    double totalCartao = 0;
    double dinheiroRecebido = 0;
    double trocoDevolvido = 0;
    double maiorVenda = 0;

    // login
    String operador = IO.readln("Nome do operador: ");

    int tentativas = 0;
    boolean autenticado = false;

    while (tentativas < 3 && autenticado == false) {
        String senha = IO.readln("Senha: ");
        tentativas = tentativas + 1;

        if (senha.equals(SENHA)) {
            autenticado = true;
        } else {
            IO.println("Senha incorreta. Tentativas restantes: " + (3 - tentativas));
        }
    }

    if (autenticado == false) {
        IO.println("Acesso bloqueado. Programa encerrado.");
    } else {

        double caixaInicial = Double.parseDouble(IO.readln("Valor inicial em caixa: R$ "));
        while (caixaInicial < 0) {
            IO.println("Valor inválido. Não pode ser negativo.");
            caixaInicial = Double.parseDouble(IO.readln("Valor inicial em caixa: R$ "));
        }

        IO.println("Bem-vindo(a), " + operador + "! Caixa aberto.");

        // menu principal
        boolean caixaAberto = true;

        while (caixaAberto == true) {
            IO.println("");
            IO.println("========== CANTINA - CAIXA ABERTO ==========");
            IO.println("Operador: " + operador);
            IO.println("1 - Registrar venda");
            IO.println("2 - Consultar estoque");
            IO.println("3 - Repor estoque");
            IO.println("4 - Relatório parcial");
            IO.println("5 - Fechar caixa");
            int opcao = Integer.parseInt(IO.readln("Escolha uma opção: "));

            if (opcao == 1) {
                // ---------- REGISTRAR VENDA ----------

                // quanto de cada produto está nesta venda (ainda não saiu do estoque)
                int reservadoCoxinha = 0;
                int reservadoPao = 0;
                int reservadoSuco = 0;
                int reservadoCafe = 0;

                double totalBruto = 0;
                int codigo = -1;

                while (codigo != 0) {
                    IO.println("");
                    IO.println("1 - Coxinha        R$ " + String.format("%.2f", PRECO_COXINHA) + "  Estoque: " + (estoqueCoxinha - reservadoCoxinha));
                    IO.println("2 - Pão de queijo  R$ " + String.format("%.2f", PRECO_PAO) + "  Estoque: " + (estoquePao - reservadoPao));
                    IO.println("3 - Suco natural   R$ " + String.format("%.2f", PRECO_SUCO) + "  Estoque: " + (estoqueSuco - reservadoSuco));
                    IO.println("4 - Café           R$ " + String.format("%.2f", PRECO_CAFE) + "  Estoque: " + (estoqueCafe - reservadoCafe));
                    codigo = Integer.parseInt(IO.readln("Código do produto (0 para finalizar): "));

                    if (codigo == 1) {
                        int disponivel = estoqueCoxinha - reservadoCoxinha;
                        if (disponivel == 0) {
                            IO.println("Coxinha esgotada.");
                        } else {
                            int qtd = Integer.parseInt(IO.readln("Quantidade: "));
                            while (qtd <= 0 || qtd > disponivel) {
                                if (qtd <= 0) {
                                    IO.println("A quantidade deve ser maior que zero.");
                                } else {
                                    IO.println("Estoque insuficiente. Disponível: " + disponivel);
                                }
                                qtd = Integer.parseInt(IO.readln("Quantidade: "));
                            }
                            reservadoCoxinha = reservadoCoxinha + qtd;
                            double subtotal = qtd * PRECO_COXINHA;
                            totalBruto = totalBruto + subtotal;
                            IO.println("Subtotal: R$ " + String.format("%.2f", subtotal));
                        }

                    } else if (codigo == 2) {
                        int disponivel = estoquePao - reservadoPao;
                        if (disponivel == 0) {
                            IO.println("Pão de queijo esgotado.");
                        } else {
                            int qtd = Integer.parseInt(IO.readln("Quantidade: "));
                            while (qtd <= 0 || qtd > disponivel) {
                                if (qtd <= 0) {
                                    IO.println("A quantidade deve ser maior que zero.");
                                } else {
                                    IO.println("Estoque insuficiente. Disponível: " + disponivel);
                                }
                                qtd = Integer.parseInt(IO.readln("Quantidade: "));
                            }
                            reservadoPao = reservadoPao + qtd;
                            double subtotal = qtd * PRECO_PAO;
                            totalBruto = totalBruto + subtotal;
                            IO.println("Subtotal: R$ " + String.format("%.2f", subtotal));
                        }

                    } else if (codigo == 3) {
                        int disponivel = estoqueSuco - reservadoSuco;
                        if (disponivel == 0) {
                            IO.println("Suco natural esgotado.");
                        } else {
                            int qtd = Integer.parseInt(IO.readln("Quantidade: "));
                            while (qtd <= 0 || qtd > disponivel) {
                                if (qtd <= 0) {
                                    IO.println("A quantidade deve ser maior que zero.");
                                } else {
                                    IO.println("Estoque insuficiente. Disponível: " + disponivel);
                                }
                                qtd = Integer.parseInt(IO.readln("Quantidade: "));
                            }
                            reservadoSuco = reservadoSuco + qtd;
                            double subtotal = qtd * PRECO_SUCO;
                            totalBruto = totalBruto + subtotal;
                            IO.println("Subtotal: R$ " + String.format("%.2f", subtotal));
                        }

                    } else if (codigo == 4) {
                        int disponivel = estoqueCafe - reservadoCafe;
                        if (disponivel == 0) {
                            IO.println("Café esgotado.");
                        } else {
                            int qtd = Integer.parseInt(IO.readln("Quantidade: "));
                            while (qtd <= 0 || qtd > disponivel) {
                                if (qtd <= 0) {
                                    IO.println("A quantidade deve ser maior que zero.");
                                } else {
                                    IO.println("Estoque insuficiente. Disponível: " + disponivel);
                                }
                                qtd = Integer.parseInt(IO.readln("Quantidade: "));
                            }
                            reservadoCafe = reservadoCafe + qtd;
                            double subtotal = qtd * PRECO_CAFE;
                            totalBruto = totalBruto + subtotal;
                            IO.println("Subtotal: R$ " + String.format("%.2f", subtotal));
                        }

                    } else if (codigo != 0) {
                        IO.println("Código inválido.");
                    }
                }

                if (totalBruto == 0) {
                    IO.println("Nenhum item incluído. Venda cancelada.");
                } else {
                    IO.println("Total bruto: R$ " + String.format("%.2f", totalBruto));

                    // desconto de estudante
                    String estudante = IO.readln("Cliente é estudante? (S/N): ");
                    while (!estudante.equals("S") && !estudante.equals("s") && !estudante.equals("N") && !estudante.equals("n")) {
                        IO.println("Responda S ou N.");
                        estudante = IO.readln("Cliente é estudante? (S/N): ");
                    }

                    double desconto = 0;
                    if ((estudante.equals("S") || estudante.equals("s")) && totalBruto >= 20) {
                        desconto = totalBruto * 0.10;
                    }
                    double totalComDesconto = totalBruto - desconto;

                    // forma de pagamento
                    IO.println("Forma de pagamento: 1 - Dinheiro | 2 - Pix | 3 - Cartão");
                    int forma = Integer.parseInt(IO.readln("Opção: "));
                    while (forma < 1 || forma > 3) {
                        IO.println("Forma de pagamento inválida.");
                        forma = Integer.parseInt(IO.readln("Opção: "));
                    }

                    // acréscimo do cartão (3% sobre o valor com desconto)
                    double acrescimo = 0;
                    if (forma == 3) {
                        acrescimo = totalComDesconto * 0.03;
                    }
                    double totalFinal = totalComDesconto + acrescimo;

                    // troco (só no dinheiro)
                    double recebido = 0;
                    double troco = 0;
                    if (forma == 1) {
                        IO.println("Total a pagar: R$ " + String.format("%.2f", totalFinal));
                        recebido = Double.parseDouble(IO.readln("Valor recebido: R$ "));
                        while (recebido < totalFinal) {
                            IO.println("Valor insuficiente. Faltam R$ " + String.format("%.2f", totalFinal - recebido));
                            recebido = Double.parseDouble(IO.readln("Valor recebido: R$ "));
                        }
                        troco = recebido - totalFinal;
                    }

                    // cupom
                    IO.println("");
                    IO.println("============ CUPOM ============");
                    if (reservadoCoxinha > 0) {
                        IO.println(reservadoCoxinha + "x Coxinha = R$ " + String.format("%.2f", reservadoCoxinha * PRECO_COXINHA));
                    }
                    if (reservadoPao > 0) {
                        IO.println(reservadoPao + "x Pão de queijo = R$ " + String.format("%.2f", reservadoPao * PRECO_PAO));
                    }
                    if (reservadoSuco > 0) {
                        IO.println(reservadoSuco + "x Suco natural = R$ " + String.format("%.2f", reservadoSuco * PRECO_SUCO));
                    }
                    if (reservadoCafe > 0) {
                        IO.println(reservadoCafe + "x Café = R$ " + String.format("%.2f", reservadoCafe * PRECO_CAFE));
                    }
                    IO.println("-------------------------------");
                    IO.println("Total bruto: R$ " + String.format("%.2f", totalBruto));
                    IO.println("Desconto:    R$ " + String.format("%.2f", desconto));
                    IO.println("Acréscimo:   R$ " + String.format("%.2f", acrescimo));
                    IO.println("Total final: R$ " + String.format("%.2f", totalFinal));
                    if (forma == 1) {
                        IO.println("Pagamento:   Dinheiro");
                        IO.println("Recebido:    R$ " + String.format("%.2f", recebido));
                        IO.println("Troco:       R$ " + String.format("%.2f", troco));
                    } else if (forma == 2) {
                        IO.println("Pagamento:   Pix");
                    } else {
                        IO.println("Pagamento:   Cartão");
                    }
                    IO.println("===============================");

                    // venda confirmada: agora tira do estoque
                    estoqueCoxinha = estoqueCoxinha - reservadoCoxinha;
                    estoquePao = estoquePao - reservadoPao;
                    estoqueSuco = estoqueSuco - reservadoSuco;
                    estoqueCafe = estoqueCafe - reservadoCafe;

                    vendidosCoxinha = vendidosCoxinha + reservadoCoxinha;
                    vendidosPao = vendidosPao + reservadoPao;
                    vendidosSuco = vendidosSuco + reservadoSuco;
                    vendidosCafe = vendidosCafe + reservadoCafe;

                    // atualiza os totais do dia
                    qtdVendas = qtdVendas + 1;
                    totalArrecadado = totalArrecadado + totalFinal;

                    if (forma == 1) {
                        totalDinheiro = totalDinheiro + totalFinal;
                        dinheiroRecebido = dinheiroRecebido + recebido;
                        trocoDevolvido = trocoDevolvido + troco;
                    } else if (forma == 2) {
                        totalPix = totalPix + totalFinal;
                    } else {
                        totalCartao = totalCartao + totalFinal;
                    }

                    if (totalFinal > maiorVenda) {
                        maiorVenda = totalFinal;
                    }
                }

            } else if (opcao == 2) {
                // ---------- CONSULTAR ESTOQUE ----------
                IO.println("");
                IO.println("--- ESTOQUE ATUAL ---");

                if (estoqueCoxinha == 0) {
                    IO.println("Coxinha: 0  ESGOTADO");
                } else if (estoqueCoxinha <= 5) {
                    IO.println("Coxinha: " + estoqueCoxinha + "  ESTOQUE BAIXO");
                } else {
                    IO.println("Coxinha: " + estoqueCoxinha);
                }

                if (estoquePao == 0) {
                    IO.println("Pão de queijo: 0  ESGOTADO");
                } else if (estoquePao <= 5) {
                    IO.println("Pão de queijo: " + estoquePao + "  ESTOQUE BAIXO");
                } else {
                    IO.println("Pão de queijo: " + estoquePao);
                }

                if (estoqueSuco == 0) {
                    IO.println("Suco natural: 0  ESGOTADO");
                } else if (estoqueSuco <= 5) {
                    IO.println("Suco natural: " + estoqueSuco + "  ESTOQUE BAIXO");
                } else {
                    IO.println("Suco natural: " + estoqueSuco);
                }

                if (estoqueCafe == 0) {
                    IO.println("Café: 0  ESGOTADO");
                } else if (estoqueCafe <= 5) {
                    IO.println("Café: " + estoqueCafe + "  ESTOQUE BAIXO");
                } else {
                    IO.println("Café: " + estoqueCafe);
                }

            } else if (opcao == 3) {
                // ---------- REPOR ESTOQUE ----------
                int cod = Integer.parseInt(IO.readln("Código do produto (1 a 4): "));
                while (cod < 1 || cod > 4) {
                    IO.println("Código inválido.");
                    cod = Integer.parseInt(IO.readln("Código do produto (1 a 4): "));
                }

                int qtd = Integer.parseInt(IO.readln("Quantidade a adicionar: "));
                while (qtd <= 0) {
                    IO.println("A quantidade deve ser maior que zero.");
                    qtd = Integer.parseInt(IO.readln("Quantidade a adicionar: "));
                }

                if (cod == 1) {
                    estoqueCoxinha = estoqueCoxinha + qtd;
                    IO.println("Novo estoque de Coxinha: " + estoqueCoxinha);
                } else if (cod == 2) {
                    estoquePao = estoquePao + qtd;
                    IO.println("Novo estoque de Pão de queijo: " + estoquePao);
                } else if (cod == 3) {
                    estoqueSuco = estoqueSuco + qtd;
                    IO.println("Novo estoque de Suco natural: " + estoqueSuco);
                } else {
                    estoqueCafe = estoqueCafe + qtd;
                    IO.println("Novo estoque de Café: " + estoqueCafe);
                }

            } else if (opcao == 4) {
                // ---------- RELATÓRIO PARCIAL ----------
                IO.println("");
                IO.println("============ RELATÓRIO PARCIAL ============");
                IO.println("Vendas realizadas: " + qtdVendas);
                IO.println("Total arrecadado: R$ " + String.format("%.2f", totalArrecadado));

                if (qtdVendas == 0) {
                    IO.println("Ticket médio: nenhuma venda realizada ainda.");
                } else {
                    IO.println("Ticket médio: R$ " + String.format("%.2f", totalArrecadado / qtdVendas));
                }

                IO.println("Dinheiro: R$ " + String.format("%.2f", totalDinheiro));
                IO.println("Pix: R$ " + String.format("%.2f", totalPix));
                IO.println("Cartão: R$ " + String.format("%.2f", totalCartao));

                IO.println("Coxinha vendidas: " + vendidosCoxinha);
                IO.println("Pão de queijo vendidos: " + vendidosPao);
                IO.println("Suco natural vendidos: " + vendidosSuco);
                IO.println("Café vendidos: " + vendidosCafe);

                // produto mais vendido
                String maisVendido = "Coxinha";
                int maiorQtd = vendidosCoxinha;
                if (vendidosPao > maiorQtd) {
                    maisVendido = "Pão de queijo";
                    maiorQtd = vendidosPao;
                }
                if (vendidosSuco > maiorQtd) {
                    maisVendido = "Suco natural";
                    maiorQtd = vendidosSuco;
                }
                if (vendidosCafe > maiorQtd) {
                    maisVendido = "Café";
                    maiorQtd = vendidosCafe;
                }

                if (maiorQtd == 0) {
                    IO.println("Produto mais vendido: nenhum ainda.");
                } else {
                    IO.println("Produto mais vendido: " + maisVendido + " (" + maiorQtd + " unidades)");
                }

                IO.println("Maior venda do dia: R$ " + String.format("%.2f", maiorVenda));

            } else if (opcao == 5) {
                // ---------- FECHAR CAIXA ----------
                String conf = IO.readln("Confirma o fechamento do caixa? (S/N): ");
                while (!conf.equals("S") && !conf.equals("s") && !conf.equals("N") && !conf.equals("n")) {
                    IO.println("Responda S ou N.");
                    conf = IO.readln("Confirma o fechamento do caixa? (S/N): ");
                }

                if (conf.equals("S") || conf.equals("s")) {

                    // relatório (igual ao da opção 4)
                    IO.println("");
                    IO.println("============ RELATÓRIO FINAL ============");
                    IO.println("Vendas realizadas: " + qtdVendas);
                    IO.println("Total arrecadado: R$ " + String.format("%.2f", totalArrecadado));

                    if (qtdVendas == 0) {
                        IO.println("Ticket médio: nenhuma venda realizada.");
                    } else {
                        IO.println("Ticket médio: R$ " + String.format("%.2f", totalArrecadado / qtdVendas));
                    }

                    IO.println("Dinheiro: R$ " + String.format("%.2f", totalDinheiro));
                    IO.println("Pix: R$ " + String.format("%.2f", totalPix));
                    IO.println("Cartão: R$ " + String.format("%.2f", totalCartao));

                    IO.println("Coxinha vendidas: " + vendidosCoxinha);
                    IO.println("Pão de queijo vendidos: " + vendidosPao);
                    IO.println("Suco natural vendidos: " + vendidosSuco);
                    IO.println("Café vendidos: " + vendidosCafe);

                    String maisVendido = "Coxinha";
                    int maiorQtd = vendidosCoxinha;
                    if (vendidosPao > maiorQtd) {
                        maisVendido = "Pão de queijo";
                        maiorQtd = vendidosPao;
                    }
                    if (vendidosSuco > maiorQtd) {
                        maisVendido = "Suco natural";
                        maiorQtd = vendidosSuco;
                    }
                    if (vendidosCafe > maiorQtd) {
                        maisVendido = "Café";
                        maiorQtd = vendidosCafe;
                    }

                    if (maiorQtd == 0) {
                        IO.println("Produto mais vendido: nenhum.");
                    } else {
                        IO.println("Produto mais vendido: " + maisVendido + " (" + maiorQtd + " unidades)");
                    }

                    IO.println("Maior venda do dia: R$ " + String.format("%.2f", maiorVenda));

                    // parte do caixa
                    double caixaFinal = caixaInicial + dinheiroRecebido - trocoDevolvido;
                    IO.println("");
                    IO.println("Valor inicial em caixa: R$ " + String.format("%.2f", caixaInicial));
                    IO.println("Dinheiro recebido: R$ " + String.format("%.2f", dinheiroRecebido));
                    IO.println("Troco devolvido: R$ " + String.format("%.2f", trocoDevolvido));
                    IO.println("Valor final em caixa: R$ " + String.format("%.2f", caixaFinal));

                    // gráfico de barras
                    IO.println("");
                    IO.println("Vendas por produto");

                    IO.print("Coxinha        ");
                    for (int i = 0; i < vendidosCoxinha; i++) {
                        IO.print("#");
                    }
                    IO.println(" " + vendidosCoxinha);

                    IO.print("Pão de queijo  ");
                    for (int i = 0; i < vendidosPao; i++) {
                        IO.print("#");
                    }
                    IO.println(" " + vendidosPao);

                    IO.print("Suco natural   ");
                    for (int i = 0; i < vendidosSuco; i++) {
                        IO.print("#");
                    }
                    IO.println(" " + vendidosSuco);

                    IO.print("Café           ");
                    for (int i = 0; i < vendidosCafe; i++) {
                        IO.print("#");
                    }
                    IO.println(" " + vendidosCafe);

                    IO.println("");
                    IO.println("Caixa fechado. Até amanhã, " + operador + "!");
                    caixaAberto = false;
                }

            } else {
                IO.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
