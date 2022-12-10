package Clientes;

import java.rmi.Naming;
import Servidor.ServerInter;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Cliente extends javax.swing.JFrame {

    private static HashMap<String, ClientInter> amigos;
    private static String nombre;
    private static ServerInter h;
    private static ClientInter callbackObj;
    private static String clave;

    /**
     * Creates new form Cliente
     */
    public Cliente() {
        initComponents();
        error.setVisible(false);
        noCoinc.setVisible(false);
        exito.setVisible(false);
        try {
            String registryURL = "rmi://localhost:1099/messenger";
            this.h = (ServerInter) Naming.lookup(registryURL);
            this.callbackObj = (ClientInter) new ClientInterImpl(this);
            this.amigos = new HashMap();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.exit(1);
        }

        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    h.desconexion(nombre, clave);
                } catch (RemoteException ex) {
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            javax.swing.JOptionPane.showMessageDialog(null, ex, "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        }
                    });
                }
            }
        });
    }

    public void setAmigos(HashMap amigos) {
        this.amigos = amigos;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        chat = new javax.swing.JTabbedPane();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        solicitudBuscador = new javax.swing.JTextField();
        solicitarAmistadBoton = new javax.swing.JButton();
        nombreSolicitudText = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        solicitudesTabla = new javax.swing.JTable();
        rechazarBoton = new javax.swing.JButton();
        aceptarBoton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        amigosTabla = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        enviadasTabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        repNuevaPwd = new javax.swing.JPasswordField();
        antigua = new javax.swing.JPasswordField();
        nuevaPwd = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        error = new javax.swing.JLabel();
        noCoinc = new javax.swing.JLabel();
        exito = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        chat.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chatStateChanged(evt);
            }
        });
        jTabbedPane1.addTab("Chats", chat);

        solicitarAmistadBoton.setText("Solicitar amistad");
        solicitarAmistadBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solicitarAmistadBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nombreSolicitudText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(solicitudBuscador, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(77, 77, 77))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(solicitarAmistadBoton)
                .addContainerGap(246, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(solicitudBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(solicitarAmistadBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombreSolicitudText, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Solicitar", jPanel1);

        solicitudesTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre de usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(solicitudesTabla);

        rechazarBoton.setText("Rechazar solicitud");
        rechazarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechazarBotonActionPerformed(evt);
            }
        });

        aceptarBoton.setText("Aceptar solicitud");
        aceptarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(aceptarBoton)
                        .addGap(27, 27, 27)
                        .addComponent(rechazarBoton))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rechazarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aceptarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Solicitudes", jPanel2);

        amigosTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre de usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(amigosTabla);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Amigos", jPanel3);

        enviadasTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre de usuario"
            }
        ));
        jScrollPane3.setViewportView(enviadasTabla);

        jButton1.setText("Retitar solicitud");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Solicitudes enviadas", jPanel4);

        jTabbedPane1.addTab("Amigos", jTabbedPane4);

        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Antigua contraseña");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Nueva contraseña");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Repetir contraseña");

        jButton2.setText("Cambiar contraseña");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        error.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        error.setForeground(new java.awt.Color(255, 51, 51));
        error.setText("ERROR EN EL CAMBIO DE LA CONTRASEÑA");

        noCoinc.setForeground(new java.awt.Color(255, 51, 51));
        noCoinc.setText("LAS CONTRASEÑAS NO COINCIDEN");

        exito.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        exito.setForeground(new java.awt.Color(0, 153, 51));
        exito.setText("EL CAMBIO DE CONTRASEÑA SE HA REALIZADO CON EXITO");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(repNuevaPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(antigua, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nuevaPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(error)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addGap(46, 46, 46))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(noCoinc)
                        .addGap(122, 122, 122))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(exito)
                        .addGap(35, 35, 35))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(antigua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nuevaPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(repNuevaPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noCoinc)
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(error))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(exito)
                .addGap(38, 38, 38))
        );

        jTabbedPane1.addTab("Opciones", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void solicitarAmistadBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solicitarAmistadBotonActionPerformed

        try {
            if (!nombre.equals(solicitudBuscador.getText()) && h.solicitarAmistad(nombre, solicitudBuscador.getText(), clave)) {
                anhadirFila(solicitudBuscador.getText(), enviadasTabla);
                solicitudBuscador.setText("");

                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String mensaje = "La solicitud se ha enviado con exito. A la espera de confirmación por el usuario";
                        javax.swing.JOptionPane.showMessageDialog(null, mensaje, "INFORMACIÓN", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    }
                });
            } else {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        javax.swing.JOptionPane.showMessageDialog(null, "El usuario no existe, ya es amigo o la solicitud ya a sido enviada", "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                });
            }
        } catch (RemoteException ex) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    javax.swing.JOptionPane.showMessageDialog(null, ex, "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }//GEN-LAST:event_solicitarAmistadBotonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel tm = (DefaultTableModel) enviadasTabla.getModel();
        int fila = enviadasTabla.getSelectedRow();
        String dato = String.valueOf(tm.getValueAt(fila, 0));
        try {
            h.borrarSolicitudEnviada(nombre, dato, clave);
            tm.removeRow(fila);
            solicitudesTabla.setModel(tm);

        } catch (RemoteException ex) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    javax.swing.JOptionPane.showMessageDialog(null, ex, "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void aceptarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarBotonActionPerformed
        DefaultTableModel tm = (DefaultTableModel) solicitudesTabla.getModel();
        int fila = solicitudesTabla.getSelectedRow();
        String dato = String.valueOf(tm.getValueAt(fila, 0));
        try {
            h.aceptarSolicitud(dato, nombre, clave);
            tm.removeRow(fila);
            solicitudesTabla.setModel(tm);
            anhadirFilaAmigos(dato);

        } catch (RemoteException ex) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    javax.swing.JOptionPane.showMessageDialog(null, ex, "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }//GEN-LAST:event_aceptarBotonActionPerformed

    private void rechazarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechazarBotonActionPerformed
        DefaultTableModel tm = (DefaultTableModel) solicitudesTabla.getModel();
        int fila = solicitudesTabla.getSelectedRow();
        String dato = String.valueOf(tm.getValueAt(fila, 0));
        try {
            h.rechazarSolicitud(dato, nombre, clave);
            tm.removeRow(fila);
            solicitudesTabla.setModel(tm);
        } catch (RemoteException ex) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    javax.swing.JOptionPane.showMessageDialog(null, ex, "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }//GEN-LAST:event_rechazarBotonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        error.setVisible(false);
        noCoinc.setVisible(false);

        String n1 = new String(nuevaPwd.getPassword()), n2 = new String(repNuevaPwd.getPassword());

        if (n1.equals(n2)) {
            try {
                if (!h.cambiarContraseña(nombre, new String(antigua.getPassword()), n1)) {
                    error.setVisible(true);
                } else {
                    nuevaPwd.setText(null);
                    repNuevaPwd.setText(null);
                    antigua.setText(null);
                    exito.setVisible(true);
                    this.setClave(n1);
                }
            } catch (RemoteException ex) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        javax.swing.JOptionPane.showMessageDialog(null, ex, "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                });
            }
        } else {
            noCoinc.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        // TODO add your handling code here:
        exito.setVisible(false);
    }//GEN-LAST:event_jPanel5MouseClicked

    private void chatStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chatStateChanged
        // TODO add your handling code here:
        if(chat.getTabCount()>0)
            chat.setTitleAt(chat.getSelectedIndex(), chat.getTitleAt(chat.getSelectedIndex()).split(" ")[0].trim());
    }//GEN-LAST:event_chatStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Cliente c = new Cliente();
                new Registro(c, true, h, c, callbackObj).setVisible(true);
                c.setTitle(nombre);

                for (String amigo : amigos.keySet()) {
                    c.abrirChat(amigo, amigos.get(amigo));
                }

                c.setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarBoton;
    private javax.swing.JTable amigosTabla;
    private javax.swing.JPasswordField antigua;
    private javax.swing.JTabbedPane chat;
    private javax.swing.JTable enviadasTabla;
    private javax.swing.JLabel error;
    private javax.swing.JLabel exito;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JLabel noCoinc;
    private javax.swing.JLabel nombreSolicitudText;
    private javax.swing.JPasswordField nuevaPwd;
    private javax.swing.JButton rechazarBoton;
    private javax.swing.JPasswordField repNuevaPwd;
    private javax.swing.JButton solicitarAmistadBoton;
    private javax.swing.JTextField solicitudBuscador;
    private javax.swing.JTable solicitudesTabla;
    // End of variables declaration//GEN-END:variables

    void recibirMensaje(String mensaje, String user) {
        int cnt = chat.getTabCount();

        for (int i = 0; i < cnt; i++) {

            if (chat.getTitleAt(i).split(" ")[0].equals(user)) {
                Chat a = (Chat) chat.getComponentAt(i);
                a.setText(mensaje, user);

                if (chat.getSelectedIndex() != i) {

                    chat.setTitleAt(i, user + " " + "⬤");

                }
                break;
            }
        }

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void eliminarFilaEnviadas(String nombre) {
        DefaultTableModel modelo = (DefaultTableModel) enviadasTabla.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (modelo.getValueAt(i, 0).equals(nombre)) {
                modelo.removeRow(i);
                break;
            }
        }

        enviadasTabla.setModel(modelo);
    }

    public void eliminarFilaRecibidas(String nombre) {
        DefaultTableModel modelo = (DefaultTableModel) solicitudesTabla.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (modelo.getValueAt(i, 0).equals(nombre)) {
                modelo.removeRow(i);
                break;
            }
        }

        solicitudesTabla.setModel(modelo);
    }

    public void anhadirFilaAmigos(String nombre) {
        anhadirFila(nombre, amigosTabla);
    }

    public void anhadirFilaRecibidas(String nombre) {
        anhadirFila(nombre, solicitudesTabla);
    }

    public void anhadirFilaEnviadas(String nombre) {
        anhadirFila(nombre, enviadasTabla);
    }

    private void anhadirFila(String nombre, JTable tabla) {
        Object[] fila = new Object[1];

        fila[0] = (Object) nombre;

        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.addRow(fila);

        tabla.setModel(modelo);
    }

    public void abrirChat(String user, ClientInter clt) {
        int cnt = chat.getTabCount();
        for (int i = 0; i < cnt; i++) {

            if (chat.getTitleAt(i).split(" ")[0].equals(user)) {
                break;
            }
        }
        Chat a = new Chat(clt, nombre);
        chat.addTab(user, a);
    }

    void eliminarChat(String user) {
        int cnt = chat.getTabCount();

        for (int i = 0; i < cnt; i++) {

            if (chat.getTitleAt(i).split(" ")[0].equals(user)) {
                chat.remove(i);
                break;
            }
        }
        
    }

    public void iniciarTablas() {
        try {
            ArrayList<String> temp = (ArrayList) h.obtenerAmigos(nombre, clave);
            for (String amigo : temp) {
                anhadirFilaAmigos(amigo);
            }

            temp = (ArrayList) h.obtenerSolicitudesEnviadas(nombre, clave);

            for (String amigo : temp) {
                anhadirFilaEnviadas(amigo);
            }

            temp = (ArrayList) h.obtenerSolicitudesRecibidas(nombre, clave);

            for (String amigo : temp) {
                anhadirFilaRecibidas(amigo);
            }

        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void avisar(String solicitante) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                javax.swing.JOptionPane.showMessageDialog(null, nombre + ", has recibido una solicitud de amistad de " + solicitante, "Nueva solicitud", javax.swing.JOptionPane.DEFAULT_OPTION);
            }
        });
    }
}
