import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            Registry registry = LocateRegistry.getRegistry("localhost"); 
            Calculadora calc = (Calculadora) registry.lookup("Calculadora");

            System.out.println("=== Cliente RPC Interativo ===");

            while (true) {
                System.out.print("Digite o primeiro número: ");
                int num1 = sc.nextInt();
                System.out.print("Digite o segundo número: ");
                int num2 = sc.nextInt();

                System.out.println("Escolha a operação: ");
                System.out.println("1 - Somar");
                System.out.println("2 - Subtrair");
                System.out.println("3 - Multiplicar");
                System.out.println("4 - Dividir");
                System.out.println("0 - Sair");

                int opcao = sc.nextInt();

                if (opcao == 0) {
                    System.out.println("Cliente encerrado.");
                    break;
                }

                int resultado = 0;
                switch (opcao) {
                    case 1: resultado = calc.somar(num1, num2); break;
                    case 2: resultado = calc.subtrair(num1, num2); break;
                    case 3: resultado = calc.multiplicar(num1, num2); break;
                    case 4: resultado = calc.dividir(num1, num2); break;
                    default: System.out.println("Opção inválida!"); continue;
                }

                System.out.println("Resultado: " + resultado);
                System.out.println("-------------------------");
            }

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
