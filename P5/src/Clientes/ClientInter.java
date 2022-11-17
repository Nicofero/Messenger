/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Nicolás Fernández
 */
public interface ClientInter extends Remote{
     public void recibirMensaje() throws RemoteException;
     public void notifica(String mensaje) throws RemoteException;
}
