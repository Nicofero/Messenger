/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Nicolás Fernández
 */
public class Server {

    /**
     * @param args the command line arguments
     */
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
            Registry registry = LocateRegistry.createRegistry(i);
            System.out.println(
                    "Registro RMI creado en el puerto " + i);
        }
    }

}
