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
     public void recibirMensaje(String mensaje,String user) throws RemoteException;
     public void notifica(String user,ClientInter clt) throws RemoteException;
     public void desconexion (String user) throws RemoteException;

    public void nuevaSolicitud(String solicitante) throws RemoteException;

    public void solicitudAceptada(String user)throws RemoteException;

    public void rechazarSolicitud(String user) throws RemoteException;
    
    public void borrarSolicitudEnviada(String user) throws RemoteException;
}
