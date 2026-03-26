package projeto;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Cliente extends Thread {

    private String nome;
    private Banco banco;
    private Scanner sc;
    private int id;

    public Cliente(String nome, Banco banco, Scanner sc, int id) {
        this.nome = nome;
        this.banco = banco;
        this.sc = sc;
        this.id = id;
    }

    public void run() {

        int op = -1;

        do {

            banco.esperarTurno(id);

            boolean operacaoValida = false;

            while (!operacaoValida) {

                try {

                    System.out.println("\n" + nome + " escolha operação:");
                    System.out.println("1 - Consultar saldo");
                    System.out.println("2 - Depositar");
                    System.out.println("3 - Sacar");
                    System.out.println("4 - Pagar boleto");
                    System.out.println("0 - Sair");

                    op = sc.nextInt();

                    switch (op) {

                        case 1:
                            banco.consultarSaldo(nome);
                            operacaoValida = true;
                            break;

                        case 2:
                            System.out.println("Valor:");
                            banco.depositar(nome, sc.nextDouble());
                            operacaoValida = true;
                            break;

                        case 3:
                            System.out.println("Valor:");
                            banco.sacar(nome, sc.nextDouble());
                            operacaoValida = true;
                            break;

                        case 4:
                            System.out.println("Valor:");
                            banco.pagarBoleto(nome, sc.nextDouble());
                            operacaoValida = true;
                            break;

                        case 0:
                            System.out.println(nome + " saiu.");
                            operacaoValida = true;
                            break;

                        default:
                            System.out.println("Opção inválida.");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Digite apenas números!");
                    sc.nextLine(); 
                }
            }

            banco.passarTurno();

        } while (op != 0);
    }
}