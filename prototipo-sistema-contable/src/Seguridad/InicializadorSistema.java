
package Seguridad;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import prototipo.sistema.contable.datos.ArchivoUsuarios;
import prototipo.sistema.contable.modelo.Usuario;

public class InicializadorSistema {

    public static void crearSuperadministrador()
            throws IOException, ClassNotFoundException {

        ArchivoUsuarios archivo = new ArchivoUsuarios();

        ArrayList<Usuario> usuarios = archivo.cargarUsuarios();

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

        Usuario superadmin = new Usuario(
                nombre,
                apellido,
                correo,
                genero,
                usuario,
                hash,
                "Superadministrador"
        );

        archivo.agregarUsuario(superadmin);

        System.out.println("Superadministrador creado correctamente.");
    }
}
