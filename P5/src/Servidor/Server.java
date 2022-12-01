package Servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {

        InputStreamReader not;
        BufferedReader lc = null;

        try {
            not = new InputStreamReader(System.in);
            lc = new BufferedReader(not);

            ServerInterImpl inter = new ServerInterImpl();

            startRegistry(1099);

            String registryURL = "rmi://localhost:1099/messenger";
            Naming.rebind(registryURL, inter);

            System.out.println("El servidor esta funcionando");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private static void startRegistry(int i) throws RemoteException {

        //Comprobamos si existe un registro
        try {
            Registry registry = LocateRegistry.getRegistry(i);
            registry.list();
        } catch (RemoteException ex) {
            //Si no existe lo creamos
            LocateRegistry.createRegistry(i);
            System.out.println("Registro RMI creado en el puerto " + i);
        }
    }

}
