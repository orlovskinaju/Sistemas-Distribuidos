import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor extends UnicastRemoteObject implements Calculadora {

    protected Servidor() throws RemoteException {
        super();
    }

    public int somar(int a, int b) throws RemoteException {
        return a + b;
    }

    public int subtrair(int a, int b) throws RemoteException {
        return a - b;
    }

    public int multiplicar(int a, int b) throws RemoteException {
        return a * b;
    }

    public int dividir(int a, int b) throws RemoteException {
        if (b == 0) throw new RemoteException("Divisão por zero não permitida!");
        return a / b;
    }

    public static void main(String[] args) {
        try {
            Servidor obj = new Servidor();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Calculadora", obj);
            System.out.println("Servidor pronto e aguardando clientes...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
