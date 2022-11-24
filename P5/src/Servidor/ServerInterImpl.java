/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Clientes.ClientInter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Nicolás Fernández
 */
public class ServerInterImpl extends UnicastRemoteObject implements ServerInter{
    
    private HashMap<String,ClientInter> usuarios;
    
    public ServerInterImpl() throws RemoteException{
        
        this.usuarios = new HashMap();
        
    }

    @Override
    public HashMap<String,ClientInter> registro(String user, String pwd,ClientInter clt) throws RemoteException {
        
        Scanner sc = new Scanner("users.csv");
        String a=null;
        
        while(sc.hasNext()){
            a = sc.nextLine();
            System.out.println(a);
            if(a.split(",")[0].equals(user) && a.split(",")[1].equals(pwd)){
                usuarios.put(user, clt);
                return usuarios;
            }
        } 
        
        return null;
    }
    
    
}
