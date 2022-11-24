/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Nicolás Fernández
 */
public class ClientInterImpl extends UnicastRemoteObject implements ClientInter{

    private Cliente cl;
    
    public ClientInterImpl(Cliente cl) throws RemoteException {
        this.cl=cl;
    }   

    @Override
    public void recibirMensaje(String mensaje,String tipo) throws RemoteException {
        cl.recibirMensaje(mensaje,tipo);
    }

    @Override
    public void notifica(String mensaje) throws RemoteException {
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
                javax.swing.JOptionPane.showMessageDialog(null, mensaje,"Acceso", javax.swing.JOptionPane.DEFAULT_OPTION);
            }
        });
    }
    
}
