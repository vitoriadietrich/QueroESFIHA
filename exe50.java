
import java.util.Scanner;

/*Combine String, int, double, boolean, operadores, if/else e switch para representar um
pedido com status. Calcule o total, aplique desconto VIP de 10% se total >= 400, valide pagamento e estoque, e
use switch para indicar a próxima ação do status. */
public class exe50 {

    static double valor = 0; //O static significa, simplificando: “esse método pertence à própria classe, não a um objeto dela.”
    static int estoqueCombo1 = 2;
    static int estoqueCombo2 = 1;
    static int estoqueCombo3 = 4;
    static int estoqueCombo4 = 3;
    static int estoqueCombo5 = 5;
    static int estoqueCombo6 = 3;
    static int estoqueCoca350 = 3;
    static int estoqueGuarana350 = 3;
    static int estoqueFanta350 = 3;
    static int estoqueCoca1L = 3;
    static int estoqueGuarana1L = 3;
    static int estoqueAgua = 3;
    static boolean VIP = true;

    public static void main(String[] args) {
        Scanner entradaDados = new Scanner(System.in);

        //MINI SISTEMA DE PEDIDOS - QueroESFIHA
        System.out.println("Olá, por favor, digite seu nome para começarmos:");
        String nome = entradaDados.nextLine();
        System.out.println("Bem-vindo ao QueroESFIHA, " + nome + "!");
        System.out.println("Escolha uma opção: \n1 - Cardápio:\n2 - Sair:");
        int opcao = entradaDados.nextInt();

        switch (opcao) {
            case 1:
                Cardapio();
                break;
            case 2:
                Sair();
                break;
            default:
                System.out.println("");
        }
    }

    public static void Cardapio() {
        Scanner entradaDados = new Scanner(System.in);

        System.out.println("Escolha um combo: \nCombo 1: \nCombo 2: \nCombo 3: \nCombo 4: \nCombo 5: \nCombo 6: \nBebidas: \nSair:");
        int combo = entradaDados.nextInt();

        switch (combo) {
            case 1:
                Combo1();
                break;
            case 2:
                Combo2();
                break;
            case 3:
                Combo3();
                break;
            case 4:
                Combo4();
                break;
            case 5:
                Combo5();
                break;
            case 6:
                Combo6();
                break;
            case 7:
                Bebidas();
                break;
            case 8:
                Sair();
                break;
            default:
                System.out.println("");
        }
    }

    public static void Combo1() {
        Scanner entradaDados = new Scanner(System.in);
        System.out.println("\n========================================");
        System.out.println("           COMBO 1 - QUEROESFIHA");
        System.out.println("========================================");
        System.out.println("  5 ESFIHAS                   R$ 10,00");
        System.out.println("----------------------------------------");
        System.out.println("  3x Carne");
        System.out.println("  2x Queijo");
        System.out.println("========================================");

        System.out.println("1 - Comprar");
        System.out.println("2 - Sair");

        int combo = entradaDados.nextInt();

        if (combo == 1) {
            if (estoqueCombo1 > 0) {
                estoqueCombo1 = estoqueCombo1 - 1;
                valor += 10.00;
            } else {
                System.out.println("Produto não disponível");
                Cardapio();
            }
            Comprar();
        } else {
            Cardapio();
        }
    }

    public static void Combo2() {
        Scanner entradaDados = new Scanner(System.in);
        System.out.println("\n========================================");
        System.out.println("           COMBO 2 - QUEROESFIHA");
        System.out.println("========================================");
        System.out.println("  10 ESFIHAS                  R$ 15,00");
        System.out.println("----------------------------------------");
        System.out.println("  5x Carne");
        System.out.println("  5x Queijo");
        System.out.println("========================================");

        System.out.println("1 - Comprar");
        System.out.println("2 - Sair");

        int combo = entradaDados.nextInt();

        if (combo == 1) {
            if (estoqueCombo2 > 0) {
                estoqueCombo2 = estoqueCombo2 - 1;
                valor += 15.00;
            } else {
                System.out.println("Produto não disponível");
                Cardapio();
            }
            Comprar();
        } else {
            Cardapio();
        }
    }

    public static void Combo3() {
        Scanner entradaDados = new Scanner(System.in);
        System.out.println("\n========================================");
        System.out.println("           COMBO 3 - QUEROESFIHA");
        System.out.println("========================================");
        System.out.println("  15 ESFIHAS                  R$ 20,00");
        System.out.println("----------------------------------------");
        System.out.println("  5x Carne");
        System.out.println("  5x Queijo");
        System.out.println("  5x Calabresa");
        System.out.println("========================================");

        System.out.println("1 - Comprar");
        System.out.println("2 - Sair");

        int combo = entradaDados.nextInt();

        if (combo == 1) {
            if (estoqueCombo3 > 0) {
                estoqueCombo3 = estoqueCombo3 - 1;
                valor += 20.00;
            } else {
                System.out.println("Produto não disponível");
                Cardapio();
            }
            Comprar();
        } else {
            Cardapio();
        }
    }

    public static void Combo4() {
        Scanner entradaDados = new Scanner(System.in);
        System.out.println("\n========================================");
        System.out.println("           COMBO 4 - QUEROESFIHA");
        System.out.println("========================================");
        System.out.println("  20 ESFIHAS                  R$ 27,90");
        System.out.println("----------------------------------------");
        System.out.println("  5x Carne");
        System.out.println("  5x Queijo");
        System.out.println("  5x Calabresa");
        System.out.println("  5x Frango com Catupiry");
        System.out.println("========================================");

        System.out.println("1 - Comprar");
        System.out.println("2 - Sair");

        int combo = entradaDados.nextInt();

        if (combo == 1) {
            if (estoqueCombo4 > 0) {
                estoqueCombo4 = estoqueCombo4 - 1;
                valor += 27.90;
            } else {
                System.out.println("Produto não disponível");
                Cardapio();
            }
            Comprar();
        } else {
            Cardapio();
        }
    }

    public static void Combo5() {
        Scanner entradaDados = new Scanner(System.in);
        System.out.println("\n========================================");
        System.out.println("           COMBO 5 - QUEROESFIHA");
        System.out.println("========================================");
        System.out.println("  25 ESFIHAS                  R$ 35,00");
        System.out.println("----------------------------------------");
        System.out.println("  5x Carne");
        System.out.println("  5x Queijo");
        System.out.println("  5x Calabresa");
        System.out.println("  5x Frango com Catupiry");
        System.out.println("  5x Atum");
        System.out.println("========================================");

        System.out.println("1 - Comprar");
        System.out.println("2 - Sair");

        int combo = entradaDados.nextInt();

        if (combo == 1) {
            if (estoqueCombo5 > 0) {
                estoqueCombo5 = estoqueCombo5 - 1;
                valor += 35.00;
            } else {
                System.out.println("Produto não disponível");
                Cardapio();
            }
            Comprar();
        } else {
            Cardapio();
        }
    }

    public static void Combo6() {
        Scanner entradaDados = new Scanner(System.in);
        System.out.println("\n========================================");
        System.out.println("           COMBO 6 - QUEROESFIHA");
        System.out.println("========================================");
        System.out.println("  30 ESFIHAS                  R$ 40,00");
        System.out.println("----------------------------------------");
        System.out.println("  5x Carne");
        System.out.println("  5x Queijo");
        System.out.println("  5x Calabresa");
        System.out.println("  5x Frango com Catupiry");
        System.out.println("  5x Atum");
        System.out.println("  5x Palmito");
        System.out.println("========================================");

        System.out.println("1 - Comprar");
        System.out.println("2 - Sair");

        int combo = entradaDados.nextInt();

        if (combo == 1) {
            if (estoqueCombo6 > 0) {
                estoqueCombo6 = estoqueCombo6 - 1;
                valor += 40.00;
            } else {
                System.out.println("Produto não disponível");
                Cardapio();
            }
            Comprar();
        } else {
            Cardapio();
        }
    }

    public static void Bebidas() {

        Scanner entradaDados = new Scanner(System.in);

        System.out.println("\n========================================");
        System.out.println("            BEBIDAS - QUEROESFIHA");
        System.out.println("========================================");
        System.out.println("  1 - Coca-Cola 350ml          R$ 6,00");
        System.out.println("  2 - Guaraná 350ml            R$ 6,00");
        System.out.println("  3 - Fanta Laranja 350ml      R$ 6,00");
        System.out.println("  4 - Coca-Cola 1L             R$ 9,00");
        System.out.println("  5 - Guaraná 1L               R$ 9,00");
        System.out.println("  6 - Água 500ml               R$ 4,00");
        System.out.println("========================================");

        System.out.println("Escolha uma bebida:");

        int bebida = entradaDados.nextInt();

        switch (bebida) {

            case 1:

                if (estoqueCoca350 > 0) {
                    estoqueCoca350 = estoqueCoca350 - 1;
                    valor += 6.00;
                    Comprar();
                } else {
                    System.out.println("Coca-Cola 350ml não disponível.");
                    Cardapio();
                }

                break;

            case 2:

                if (estoqueGuarana350 > 0) {
                    estoqueGuarana350 = estoqueGuarana350 - 1;
                    valor += 6.00;
                    Comprar();
                } else {
                    System.out.println("Guaraná 350ml não disponível.");
                    Cardapio();
                }

                break;

            case 3:

                if (estoqueFanta350 > 0) {
                    estoqueFanta350 = estoqueFanta350 - 1;
                    valor += 6.00;
                    Comprar();
                } else {
                    System.out.println("Fanta Laranja 350ml não disponível.");
                    Cardapio();
                }

                break;

            case 4:

                if (estoqueCoca1L > 0) {
                    estoqueCoca1L = estoqueCoca1L - 1;
                    valor += 9.00;
                    Comprar();
                } else {
                    System.out.println("Coca-Cola 1L não disponível.");
                    Cardapio();
                }

                break;

            case 5:

                if (estoqueGuarana1L > 0) {
                    estoqueGuarana1L = estoqueGuarana1L - 1;
                    valor += 9.00;
                    Comprar();
                } else {
                    System.out.println("Guaraná 1L não disponível.");
                    Cardapio();
                }

                break;

            case 6:

                if (estoqueAgua > 0) {
                    estoqueAgua = estoqueAgua - 1;
                    valor += 4.00;
                    Comprar();
                } else {
                    System.out.println("Água 500ml não disponível.");
                    Cardapio();
                }

                break;

            default:

                System.out.println("Opção inválida!");
                Cardapio();
        }
    }

    public static void Comprar() {
        Scanner entradaDados = new Scanner(System.in);
        System.out.println("Gostaria de finalizar o pedido (SIM ou NÃO)?");
        String comprando = entradaDados.nextLine();
        comprando = comprando.toUpperCase(); //vai deixar em maiúsculo

        if (comprando.equals("SIM")) {
            Finalizar();
        } else {
            Cardapio();
        }
    }

    public static void Finalizar() {
        Scanner entradaDados = new Scanner(System.in);
        System.out.println("O total foi de R$ " + valor + "\nEscolha sua forma de pagamento: \n1 - Débito \n2 - Crédito \n3 - Vale Refeição ou Alimentação \n4 - PIX:");
        int pagamento = entradaDados.nextInt();

        switch (pagamento) {

    case 1:
        if (VIP == true && valor >= 40) {
            valor = valor - (valor * 0.10);
            System.out.println("Pagamento no débito realizado com sucesso.");
            System.out.println("Desconto VIP de 10% aplicado!");
            System.out.println("Valor final: R$ " + valor);
        } else {
            System.out.println("Pagamento no débito realizado com sucesso.");
            System.out.println("Valor final: R$ " + valor);
        }
        return;

    case 2:
        if (VIP == true && valor >= 40) {
            valor = valor - (valor * 0.10);
            System.out.println("Pagamento no crédito realizado com sucesso.");
            System.out.println("Desconto VIP de 10% aplicado!");
            System.out.println("Valor final: R$ " + valor);
        } else {
            System.out.println("Pagamento no crédito realizado com sucesso.");
            System.out.println("Valor final: R$ " + valor);
        }
        return;

    case 3:
        if (VIP == true && valor >= 40) {
            valor = valor - (valor * 0.10);
            System.out.println("Pagamento no vale refeição ou alimentação realizado com sucesso.");
            System.out.println("Desconto VIP de 10% aplicado!");
            System.out.println("Valor final: R$ " + valor);
        } else {
            System.out.println("Pagamento no vale refeição ou alimentação realizado com sucesso.");
            System.out.println("Valor final: R$ " + valor);
        }
        return;

    case 4:
        if (VIP == true && valor >= 40) {
            valor = valor - (valor * 0.10);
            System.out.println("Pagamento no PIX realizado com sucesso.");
            System.out.println("Desconto VIP de 10% aplicado!");
            System.out.println("Valor final: R$ " + valor);
        } else {
            System.out.println("Pagamento no PIX realizado com sucesso.");
            System.out.println("Valor final: R$ " + valor);
        }
        return;

    default:
        System.out.println("Tente novamente:");
        Finalizar();
        break;
}
    }
    public static void Sair() {
    }
}
