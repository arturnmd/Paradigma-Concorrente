package projeto;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Banco banco = new Banco(1000);
        Scanner sc = new Scanner(System.in);
        Cliente c1 = new Cliente("Cliente 1", banco, sc, 1);
        Cliente c2 = new Cliente("Cliente 2", banco, sc, 2);
        c1.start();
        c2.start();
    }
}