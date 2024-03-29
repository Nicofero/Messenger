package Servidor;

import Clientes.ClientInter;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

public interface ServerInter extends Remote {

    public HashMap<String, ClientInter> registro(String user, String pwd, ClientInter clt) throws RemoteException;

    public void desconexion(String user, String clave) throws RemoteException;

    public boolean solicitarAmistad(String user, String amigo, String clave) throws RemoteException;

    public void aceptarSolicitud(String user, String amigo, String clave) throws RemoteException;

    public void rechazarSolicitud(String user, String amigo, String clave) throws RemoteException;

    public void borrarSolicitudEnviada(String user, String amigo, String clave) throws RemoteException;

    public List<String> obtenerAmigos(String user, String clave) throws RemoteException;

    public List<String> obtenerSolicitudesRecibidas(String user, String clave) throws RemoteException;

    public List<String> obtenerSolicitudesEnviadas(String user, String clave) throws RemoteException;

    public boolean crearUsuario(String user, String pwd) throws RemoteException;
    
    public boolean cambiarContraseña (String user,String pwd,String npwd) throws RemoteException;

}
