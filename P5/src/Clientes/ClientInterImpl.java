package Clientes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ClientInterImpl extends UnicastRemoteObject implements ClientInter {

    private Cliente cl;
    private HashMap<String, ClientInter> amigos;

    public ClientInterImpl(Cliente cl) throws RemoteException {
        this.cl = cl;
        amigos = new HashMap<>();
    }

    @Override
    public void recibirMensaje(String mensaje, String tipo) throws RemoteException {
        cl.recibirMensaje(mensaje, tipo);
    }

    @Override
    public void notifica(String user, ClientInter clt) throws RemoteException {

        amigos.put(user, clt);
        cl.abrirChat(user, clt);
    }

    @Override
    public void desconexion(String user) throws RemoteException {

        amigos.remove(user);
        cl.eliminarChat(user);
    }

    @Override
    public void nuevaSolicitud(String solicitante) throws RemoteException {
        cl.anhadirFilaRecibidas(solicitante);
        cl.avisar(solicitante);
    }

    @Override
    public void solicitudAceptada(String user) throws RemoteException {
        cl.anhadirFilaAmigos(user);
        cl.eliminarFilaEnviadas(user);
    }

    @Override
    public void rechazarSolicitud(String user) throws RemoteException {
        cl.eliminarFilaEnviadas(user);
    }

    @Override
    public void borrarSolicitudEnviada(String user) throws RemoteException {
        cl.eliminarFilaRecibidas(user);
    }

}
