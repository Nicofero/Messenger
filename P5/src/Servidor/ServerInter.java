/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Nicolás Fernández
 */
public interface ServerInter extends Remote{
    
    public ArrayList<String> registro(String user,String pwd) throws RemoteException;
}
