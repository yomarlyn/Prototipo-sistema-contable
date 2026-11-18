

package Seguridad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import prototipo.sistema.contable.datos.ArchivoUsuarios;
import prototipo.sistema.contable.modelo.Usuario;

public class InicializadorSistema {

    public static void crearAdministradorInicial()
            throws IOException {

        ArchivoUsuarios archivo = new ArchivoUsuarios();

        ArrayList<Usuario> usuarios = archivo.cargarUsuarios();

        // Si ya existen usuarios, no es necesario crear otro administrador
        if (!usuarios.isEmpty()) {
            return;
        }

        Scanner leer = new Scanner(System.in);

        System.out.println("=== CONFIGURACIÓN INICIAL ===");

        System.out.print("Nombre: ");
        String nombre = leer.nextLine();

        System.out.print("Apellido: ");
        String apellido = leer.nextLine();

        System.out.print("Correo: ");
        String correo = leer.nextLine();

        System.out.print("Género: ");
        String genero = leer.nextLine();

        System.out.print("Nombre de usuario: ");
        String usuario = leer.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = leer.nextLine();

        String hash = GestorContrasenas.generarHash(contrasena);

        Usuario administrador = new Usuario(
                nombre,
                apellido,
                correo,
                genero,
                usuario,
                hash,
                "Administrador"
        );

        archivo.agregarUsuario(administrador);

        System.out.println("Administrador creado correctamente.");
    }
}
