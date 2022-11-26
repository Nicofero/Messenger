/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Clientes.ClientInter;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 *
 * @author Nicolás Fernández
 */
public interface ServerInter extends Remote{
    
    public HashMap<String,ClientInter> registro(String user,String pwd,ClientInter clt) throws RemoteException;
    public HashMap<String,ClientInter> desconexion(String user) throws RemoteException;
    public void solicitarAmistad(String user, String amigo) throws RemoteException;
    public void aceptarSolicitud(String user, String amigo) throws RemoteException;
    
    
}
