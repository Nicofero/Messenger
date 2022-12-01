/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Clientes.ClientInter;
import baseDatos.FachadaBaseDatos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Nicolás Fernández
 */
public class ServerInterImpl extends UnicastRemoteObject implements ServerInter {

    private HashMap<String, ClientInter> usuarios;

    public ServerInterImpl() throws RemoteException {

        this.usuarios = new HashMap();

    }

    @Override
    public HashMap<String, ClientInter> registro(String user, String pwd, ClientInter clt) throws RemoteException {

        if (FachadaBaseDatos.getInstance().iniciarSesion(user, pwd)) {
            usuarios.put(user, clt);
            System.out.println("Inicio correcto");
            HashMap<String, ClientInter> amigosConectados = new HashMap<>();

            //Ver que usuarios conectados son amigos del usuario
            ArrayList<String> amigos = (ArrayList) FachadaBaseDatos.getInstance().obtenerAmigos(user);
            for (String amigo : amigos) {
                if (usuarios.containsKey(amigo)) {
                    amigosConectados.put(amigo, usuarios.get(amigo));
                    usuarios.get(amigo).notifica(user, clt);
                }
            }
            return amigosConectados;
        }

        return null;
    }

    @Override
    public void desconexion(String user, String clave) throws RemoteException {

        if (FachadaBaseDatos.getInstance().iniciarSesion(user, clave)) {
            usuarios.remove(user);

            ArrayList<String> amigos = (ArrayList) FachadaBaseDatos.getInstance().obtenerAmigos(user);
            for (String amigo : amigos) {
                if (usuarios.containsKey(amigo)) {
                    usuarios.get(amigo).desconexion(user);
                }
            }
        }

    }

    @Override
    public boolean solicitarAmistad(String user, String amigo, String clave) throws RemoteException {

        boolean solicitudRealizada = false;

        if (FachadaBaseDatos.getInstance().iniciarSesion(user, clave)) {

            if (FachadaBaseDatos.getInstance().obtenerUsuarios().contains(amigo)) {
                if (!FachadaBaseDatos.getInstance().obtenerAmigos(user).contains(amigo)) {
                    if (!FachadaBaseDatos.getInstance().obtenerSolicitudesEnviadas(user).contains(amigo)) {
                        FachadaBaseDatos.getInstance().anhadirSolicitudAmistad(user, amigo);
                        if (usuarios.containsKey(amigo)) {
                            usuarios.get(amigo).nuevaSolicitud(user);
                        }
                        solicitudRealizada = true;
                    }
                }
            }
        }

        return solicitudRealizada;
    }

    @Override
    public void aceptarSolicitud(String user, String amigo, String clave) throws RemoteException {
        if (FachadaBaseDatos.getInstance().iniciarSesion(user, clave)) {
            FachadaBaseDatos.getInstance().aceptarSolicitud(user, amigo);
            if (usuarios.containsKey(amigo)) {
                usuarios.get(user).solicitudAceptada(amigo);
                usuarios.get(amigo).notifica(user, usuarios.get(user));
                usuarios.get(user).notifica(amigo, usuarios.get(amigo));
            }
        }
    }

    @Override
    public void borrarSolicitudEnviada(String user, String amigo, String clave) throws RemoteException {
        if (FachadaBaseDatos.getInstance().iniciarSesion(user, clave)) {
            FachadaBaseDatos.getInstance().borrarSolicitud(user, amigo);
            if (usuarios.containsKey(amigo)) {
                usuarios.get(amigo).borrarSolicitudEnviada(user);
            }
        }
    }

    @Override
    public void rechazarSolicitud(String user, String amigo, String clave) throws RemoteException {
        if (FachadaBaseDatos.getInstance().iniciarSesion(user, clave)) {
            FachadaBaseDatos.getInstance().borrarSolicitud(user, amigo);
            if (usuarios.containsKey(user)) {
                usuarios.get(user).rechazarSolicitud(amigo);
            }
        }
    }

    @Override
    public List<String> obtenerAmigos(String user, String clave) throws RemoteException {
        ArrayList<String> lista = null;
        if (FachadaBaseDatos.getInstance().iniciarSesion(user, clave)) {
            lista = (ArrayList) FachadaBaseDatos.getInstance().obtenerAmigos(user);
        }
        return lista;
    }

    @Override
    public List<String> obtenerSolicitudesEnviadas(String user, String clave) throws RemoteException {
        ArrayList<String> lista = null;
        if (FachadaBaseDatos.getInstance().iniciarSesion(user, clave)) {
            lista = (ArrayList) FachadaBaseDatos.getInstance().obtenerSolicitudesEnviadas(user);
        }
        return lista;
    }
    
    public List<String> obtenerSolicitudesRecibidas(String user, String clave) throws RemoteException {
        ArrayList<String> lista = null;
        if (FachadaBaseDatos.getInstance().iniciarSesion(user, clave)) {
            lista = (ArrayList) FachadaBaseDatos.getInstance().obtenerSolicitudesRecibidas(user);
        }
        return lista;
    }
    @Override
    public boolean crearUsuario(String user, String pwd) throws RemoteException {
        return FachadaBaseDatos.getInstance().crearUsuario(user, pwd);
    }

}
