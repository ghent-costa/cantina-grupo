// PARTE 3: registrar venda, escolha dos itens (roteiro 3.3, passos 1 a 4)
// Novidade: inclusão de itens com o estoque reservado

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
                    IO.println("(desconto, pagamento e cupom entram na Parte 4)");

                    // venda confirmada: agora tira do estoque
                    estoqueCoxinha = estoqueCoxinha - reservadoCoxinha;
                    estoquePao = estoquePao - reservadoPao;
                    estoqueSuco = estoqueSuco - reservadoSuco;
                    estoqueCafe = estoqueCafe - reservadoCafe;
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
                IO.println("(em construção) Relatório parcial");

            } else if (opcao == 5) {
                // provisório: a confirmação e o relatório final entram na Parte 5
                IO.println("Caixa fechado. Até amanhã, " + operador + "!");
                caixaAberto = false;

            } else {
                IO.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
