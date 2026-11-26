

package prototipo.sistema.contable;

import java.io.IOException;
import java.util.ArrayList;

import prototipo.sistema.contable.modelo.Usuario;
import prototipo.sistema.contable.datos.ArchivoUsuarios;

public class PruebaArchivoUsuarios {

    public static void main(String[] args) {

        ArchivoUsuarios archivo = new ArchivoUsuarios();

        try {

            // Recuperar los usuarios del archivo TXT
            ArrayList<Usuario> usuarios = archivo.cargarUsuarios();

            System.out.println("\n--- USUARIOS REGISTRADOS ---");

            System.out.println("Cantidad de usuarios: " + usuarios.size());

            for (Usuario u : usuarios) {

                System.out.println("Nombre: " + u.getNombre());
                System.out.println("Apellido: " + u.getApellido());
                System.out.println("Usuario: " + u.getUsuario());
                System.out.println("Tipo: " + u.getTipoUsuario());

                System.out.println("-------------------------");
            }

        } catch (IOException e) {

            System.out.println(
                "Error al leer el archivo: " + e.getMessage()
            );
        }
    }
}