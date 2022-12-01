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
import java.util.List;

/**
 *
 * @author Nicolás Fernández
 */
public interface ServerInter extends Remote{
    
    public HashMap<String,ClientInter> registro(String user,String pwd,ClientInter clt) throws RemoteException;
    public void desconexion(String user) throws RemoteException;
    public boolean solicitarAmistad(String user, String amigo) throws RemoteException;
    public void aceptarSolicitud(String user, String amigo) throws RemoteException;
    public void rechazarSolicitud(String user, String amigo) throws RemoteException;
    public void borrarSolicitudEnviada(String user, String amigo) throws RemoteException;
    public List<String> obtenerAmigos(String user) throws RemoteException;
    public List<String> obtenerSolicitudesRecibidas(String user) throws RemoteException;
    public List<String> obtenerSolicitudesEnviadas(String user) throws RemoteException;
    public boolean crearUsuario(String user,String pwd) throws RemoteException;
        
}
