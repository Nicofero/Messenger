/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 *
 * @author Nicolás Fernández
 */
public class ClientInterImpl extends UnicastRemoteObject implements ClientInter{

    private Cliente cl;
    private HashMap <String,ClientInter> amigos;
    
    public ClientInterImpl(Cliente cl) throws RemoteException {
        this.cl=cl;
        amigos = new HashMap<>();
    }   

    @Override
    public void recibirMensaje(String mensaje,String tipo) throws RemoteException {
        cl.recibirMensaje(mensaje,tipo);
    }

    @Override
    public void notifica(String user,ClientInter clt) throws RemoteException {
        
        amigos.put(user, clt);
        cl.abrirChat(user,clt);
    }

    @Override
    public void desconexion(String user) throws RemoteException {
        
        amigos.remove(user);
        cl.eliminarChat(user);
    }
    
}
