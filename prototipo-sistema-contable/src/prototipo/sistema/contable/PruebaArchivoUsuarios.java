
package prototipo.sistema.contable;

import java.io.IOException;
import java.util.ArrayList;

import prototipo.sistema.contable.modelo.Usuario;
import prototipo.sistema.contable.datos.ArchivoUsuarios;

public class PruebaArchivoUsuarios {

    public static void main(String[] args) {

        ArchivoUsuarios archivo = new ArchivoUsuarios();

        try {

            // Crear un usuario de prueba
            Usuario nuevoUsuario = new Usuario(
                "Maria",
                "Mencia",
                "maria@gmail.com",
                "Femenino",
                "maria01",
                "hashDePrueba",
                "Administrador"
            );

            // Intentar agregar el usuario
            boolean agregado = archivo.agregarUsuario(nuevoUsuario);

            if (agregado) {
                System.out.println("Usuario agregado correctamente.");
            } else {
                System.out.println("El usuario ya existe.");
            }

            // Recuperar los usuarios
            ArrayList<Usuario> usuarios = archivo.cargarUsuarios();

            System.out.println("\n--- USUARIOS REGISTRADOS ---");

            for (Usuario u : usuarios) {

                System.out.println("Nombre: " + u.getNombre());
                System.out.println("Apellido: " + u.getApellido());
                System.out.println("Usuario: " + u.getUsuario());
                System.out.println("Tipo: " + u.getTipoUsuario());

                System.out.println("-------------------------");
            }

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error al trabajar con el archivo: " + e.getMessage());

        }
    }
}
