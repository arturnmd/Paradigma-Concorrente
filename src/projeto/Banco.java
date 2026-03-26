package projeto;

public class Banco {

    private double saldo;
    private int turno = 1; 

    public Banco(double saldo) {
        this.saldo = saldo;
    }

    public synchronized void esperarTurno(int id) {
        while (turno != id) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void passarTurno() {
        turno = (turno == 1) ? 2 : 1;
        notifyAll();
    }

    public synchronized void consultarSaldo(String cliente) {
        System.out.println(cliente + " visualizou saldo: R$ " + saldo);
    }

    public synchronized void depositar(String cliente, double valor) {
        saldo += valor;
        System.out.println(cliente + " depositou: R$ " + valor);
        System.out.println("Saldo atual: R$ " + saldo);
    }

    public synchronized void sacar(String cliente, double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            System.out.println(cliente + " sacou: R$ " + valor);
        } else {
            System.out.println("Saldo insuficiente");
        }
        System.out.println("Saldo atual: R$ " + saldo);
    }

    public synchronized void pagarBoleto(String cliente, double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            System.out.println(cliente + " pagou: R$ " + valor);
        } else {
            System.out.println("Saldo insuficiente");
        }
        System.out.println("Saldo atual: R$ " + saldo);
    }
}