package baseDatos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;


public class FachadaBaseDatos {

    private static FachadaBaseDatos _instance;

    private java.sql.Connection conexion;
    private DAOUsuarios daoUsuarios;
    private static final String nombreArchivo = "P5/baseDatos.properties";

    public static FachadaBaseDatos getInstance() {
        if (_instance == null) {
            _instance = new FachadaBaseDatos();
        }
        return _instance;
    }


    private FachadaBaseDatos() {
        /*
         * Intenta conectarse a la base de datos
         */
        Properties configuracion = new Properties();
        FileInputStream arqConfiguracion;
        try {
            arqConfiguracion = new FileInputStream(nombreArchivo);
            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            Properties usuario = new Properties();
            String gestor = configuracion.getProperty("gestor");

            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));
            this.conexion = java.sql.DriverManager.getConnection("jdbc:" + gestor + "://"
                            + configuracion.getProperty("servidor") + ":"
                            + configuracion.getProperty("puerto") + "/"
                            + configuracion.getProperty("baseDatos"),
                    usuario);
            daoUsuarios = new DAOUsuarios(conexion);
            System.out.println("Conexión con la base de datos \"" + configuracion.getProperty("baseDatos")
                    + "\" realizada con éxito!");
        } catch (FileNotFoundException f) {
            System.err.println("Archivo de configuración " + nombreArchivo + " no encontrado:");
            System.err.println(f.getMessage());
        } catch (IOException i) {
            System.err.println("Error al leer el archivo:");
            System.err.println(i.getMessage());
        } catch (java.sql.SQLException e) {
            System.err.println("Error al conectarse a la base de datos:");
            System.err.println(e.getMessage());
        }
    }

    public boolean iniciarSesion(String nombre, String clave) {
        return daoUsuarios.iniciarSesion(nombre, clave);
    }

    public List<String> obtenerUsuarios(){
        return daoUsuarios.obtenerUsuarios();
    }

    public List<String> obtenerAmigos(String nombre){
        return daoUsuarios.obtenerAmigos(nombre);
    }

    public void anhadirSolicitudAmistad(String usuario, String amigo){
        daoUsuarios.anhadirSolicitudAmistad(usuario, amigo);
    }

    public void aceptarSolicitud(String usuario, String amigo){
        daoUsuarios.aceptarSolicitud(usuario,amigo);
    }

}