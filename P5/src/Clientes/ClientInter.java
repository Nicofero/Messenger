package Clientes;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInter extends Remote {

    public void recibirMensaje(String mensaje, String user) throws RemoteException;

    public void notifica(String user, ClientInter clt) throws RemoteException;

    public void desconexion(String user) throws RemoteException;

    public void nuevaSolicitud(String solicitante) throws RemoteException;

    public void solicitudAceptada(String user) throws RemoteException;

    public void rechazarSolicitud(String user) throws RemoteException;

    public void borrarSolicitudEnviada(String user) throws RemoteException;
}
