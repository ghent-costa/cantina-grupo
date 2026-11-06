// PARTE 1: abertura do caixa e menu principal (roteiro 3.1 e 3.2)
// Novidade: constantes, estoque, login, caixa inicial e o menu que se repete

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
                IO.println("(em construção) Registrar venda");

            } else if (opcao == 2) {
                IO.println("(em construção) Consultar estoque");

            } else if (opcao == 3) {
                IO.println("(em construção) Repor estoque");

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
