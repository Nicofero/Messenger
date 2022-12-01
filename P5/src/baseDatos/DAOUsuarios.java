package baseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUsuarios extends AbstractDAO {

    public DAOUsuarios(Connection conexion) {
        super.setConexion(conexion);
    }

    public boolean iniciarSesion(String nombreUsuario, String clave) {
        boolean sesionIniciada = false;


        Connection con;
        PreparedStatement stmUsuario = null;
        ResultSet rsUsuario;

        con = this.getConexion();

        try {
            stmUsuario = con.prepareStatement("select nombre_usuario "
                    + "from usuario "
                    + "where nombre_usuario = ? and clave = crypt(?, clave)");
            stmUsuario.setString(1, nombreUsuario);
            stmUsuario.setString(2, clave);
            rsUsuario = stmUsuario.executeQuery();
            
            if (rsUsuario.next()) {
                sesionIniciada = true;
            }

            if (sesionIniciada == false) {
                System.out.println("Error en el inicio de sesion");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmUsuario != null) {
                    stmUsuario.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return sesionIniciada;
    }

    public List<String> obtenerUsuarios() {
        ArrayList<String> usuarios = new ArrayList<>();
        Connection con;
        PreparedStatement stmUsuario = null;
        ResultSet rsUsuario;

        con = this.getConexion();

        try {
            stmUsuario = con.prepareStatement("select nombre_usuario "
                    + "from usuario ");
            rsUsuario = stmUsuario.executeQuery();

            while (rsUsuario.next()) {
                usuarios.add(rsUsuario.getString("nombre_usuario"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmUsuario != null) {
                    stmUsuario.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return usuarios;
    }

    public List<String> obtenerAmigos(String nombre){
        ArrayList<String> amigos = new ArrayList<>();
        Connection con;
        PreparedStatement stmUsuario = null;
        ResultSet rsUsuario;

        con = this.getConexion();

        try {
            stmUsuario = con.prepareStatement("select nombre_amigo "
                    + "from amistad "+
                    "where nombre_usuario = ? and solicitudaceptada = TRUE "+
                    "union "+
                    "select nombre_usuario "
                    + "from amistad "+
                    "where nombre_amigo = ? and solicitudaceptada = TRUE ");
            stmUsuario.setString(1, nombre);
            stmUsuario.setString(2, nombre);
            rsUsuario = stmUsuario.executeQuery();

            while (rsUsuario.next()) {
                amigos.add(rsUsuario.getString("nombre_amigo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmUsuario != null) {
                    stmUsuario.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return amigos;
    }

    public void aceptarSolicitud(String usuario, String amigo){
        PreparedStatement stm = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta = "update amistad "
                + "set solicitudaceptada = true"
                + " where nombre_usuario = ? and nombre_amigo = ?";
        try {
            stm = con.prepareStatement(consulta);
            stm.setString(1, usuario);
            stm.setString(2, amigo);
            stm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public void anhadirSolicitudAmistad(String usuario, String amigo){
        PreparedStatement stmCheck = null;
        PreparedStatement stmIns = null;
        Connection con;
        String consulta;

        con = this.getConexion();

        try {
            consulta = "insert into amistad(nombre_usuario, nombre_amigo, solicitudaceptada) values (?,?,?)";
            stmIns = con.prepareStatement(consulta);
            stmIns.setString(1, usuario);
            stmIns.setString(2, amigo);
            stmIns.setBoolean(3, false);
            stmIns.executeUpdate();
            
            

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmIns != null) {
                    stmIns.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public List<String> obtenerSolicitudesEnviadas(String nombre){
        ArrayList<String> solicitudes = new ArrayList<>();
        Connection con;
        PreparedStatement stmUsuario = null;
        ResultSet rsUsuario;

        con = this.getConexion();

        try {
            stmUsuario = con.prepareStatement("select nombre_amigo "
                    + "from amistad "+
                    "where nombre_usuario = ? and solicitudaceptada = FALSE");
            stmUsuario.setString(1, nombre);
            rsUsuario = stmUsuario.executeQuery();

            while (rsUsuario.next()) {
                solicitudes.add(rsUsuario.getString("nombre_amigo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmUsuario != null) {
                    stmUsuario.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return solicitudes;
    }
    
    public List<String> obtenerSolicitudesRecibidas(String nombre){
        ArrayList<String> solicitudes = new ArrayList<>();
        Connection con;
        PreparedStatement stmUsuario = null;
        ResultSet rsUsuario;

        con = this.getConexion();

        try {
            stmUsuario = con.prepareStatement("select nombre_usuario "
                    + "from amistad "+
                    "where nombre_amigo = ? and solicitudaceptada = FALSE");
            stmUsuario.setString(1, nombre);
            rsUsuario = stmUsuario.executeQuery();

            while (rsUsuario.next()) {
                solicitudes.add(rsUsuario.getString("nombre_usuario"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmUsuario != null) {
                    stmUsuario.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return solicitudes;
    }
    
    public void borrarSolicitud(String usuario, String amigo){
        PreparedStatement stm = null;
        ResultSet rst;
        Connection con;

        con = this.getConexion();

        String consulta = "delete from amistad "
                + " where nombre_usuario = ? and nombre_amigo = ? and solicitudaceptada = FALSE";
        try {
            stm = con.prepareStatement(consulta);
            stm.setString(1, usuario);
            stm.setString(2, amigo);
            stm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    void crearUsuario(String user, String pwd) {
        
        PreparedStatement stmCheck = null;
        PreparedStatement stmIns = null;
        Connection con;
        String consulta;

        con = this.getConexion();

        try {
            consulta = "Insert into usuario (nombre_usuario, clave) values (?, crypt(?, gen_salt('bf', 4)));";
            stmIns = con.prepareStatement(consulta);
            stmIns.setString(1, user);
            stmIns.setString(2, pwd);
            stmIns.executeUpdate();
            
            

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmIns != null) {
                    stmIns.close();
                }
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }
}