/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Nicolás Fernández
 */
public class ServerInterImpl extends UnicastRemoteObject implements ServerInter{
    
    private ArrayList<String> usuarios;
    
    public ServerInterImpl() throws RemoteException{
        
        this.usuarios = new ArrayList();
        
    }

    @Override
    public ArrayList<String> registro(String user, String pwd) throws RemoteException {
        
        Scanner sc = new Scanner("users.csv");
        String a=null;
        
        while(sc.hasNext()){
            a = sc.nextLine();
            System.out.println(a);
            if(a.split(",")[0].equals(user) && a.split(",")[1].equals(pwd)) return usuarios;
        } 
        
        return null;
    }
    
    
}
