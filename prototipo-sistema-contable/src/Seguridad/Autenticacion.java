
package Seguridad;

import java.io.IOException;
import java.util.ArrayList;
import prototipo.sistema.contable.datos.ArchivoUsuarios;
import prototipo.sistema.contable.modelo.Usuario;

public class Autenticacion {

    private ArchivoUsuarios archivoUsuarios;

    public Autenticacion() {
        archivoUsuarios = new ArchivoUsuarios();
    }

    public Usuario iniciarSesion(String usuario, String contrasena)
            throws IOException, ClassNotFoundException {

        ArrayList<Usuario> usuarios = archivoUsuarios.cargarUsuarios();

        for (Usuario u : usuarios) {

            if (u.getUsuario().equalsIgnoreCase(usuario)) {

                boolean contrasenaCorrecta =
                        GestorContrasenas.verificarContrasena(
                                contrasena,
                                u.getContrasena()
                        );

                if (contrasenaCorrecta) {
                    return u;
                }
            }
        }

        return null;
    }
}
