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
import java.util.Scanner;

/**
 *
 * @author Nicolás Fernández
 */
public class ServerInterImpl extends UnicastRemoteObject implements ServerInter{
    
    private HashMap<String,ClientInter> usuarios;
    
    public ServerInterImpl() throws RemoteException{
        
        this.usuarios = new HashMap();
        
    }

    @Override
    public HashMap<String,ClientInter> registro(String user, String pwd,ClientInter clt) throws RemoteException {

        clt.recibirMensaje("hola", "pedro");
        clt.recibirMensaje("te quiero muchisimo beibi", "juan");
        
        if(FachadaBaseDatos.getInstance().iniciarSesion(user, pwd)){
            usuarios.put(user, clt);
            System.out.println("Inicio correcto");
            HashMap<String, ClientInter> amigosConectados = new HashMap<>();
            
            //Ver que usuarios conectados son amigos del usuario
            ArrayList<String> amigos = (ArrayList) FachadaBaseDatos.getInstance().obtenerAmigos(user);
            for(String amigo: amigos){
                if(usuarios.containsKey(amigo)){
                    amigosConectados.put(amigo, usuarios.get(amigo));
                }
            }
            return amigosConectados;
        }

        return null;
    }
    
    
    @Override
    public HashMap<String,ClientInter> desconexion(String user) throws RemoteException{
        usuarios.remove(user);
        return usuarios;
    }

    @Override
    public void solicitarAmistad(String user, String amigo) throws RemoteException {
        FachadaBaseDatos.getInstance().anhadirSolicitudAmistad(user, amigo);
        
    }

    @Override
    public void aceptarSolicitud(String user, String amigo) throws RemoteException {
        FachadaBaseDatos.getInstance().aceptarSolicitud(user, amigo);
    }
    
    

}


