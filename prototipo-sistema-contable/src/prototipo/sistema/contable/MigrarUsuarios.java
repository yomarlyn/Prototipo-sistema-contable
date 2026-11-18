
package prototipo.sistema.contable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import prototipo.sistema.contable.datos.ArchivoUsuarios;
import prototipo.sistema.contable.modelo.Usuario;

public class MigrarUsuarios {

    public static void main(String[] args) {

        try {
            // Leer los usuarios del archivo .dat original
            ArchivoUsuarios archivoOriginal = new ArchivoUsuarios();
            ArrayList<Usuario> usuarios = archivoOriginal.cargarUsuarios();

            if (usuarios.isEmpty()) {
                System.out.println("No hay usuarios para migrar.");
                return;
            }

            // Crear el archivo TXT en la carpeta del proyecto
            File archivoTxt = new File("usuarios.txt");

            try (FileWriter escritor = new FileWriter(archivoTxt)) {

                for (Usuario u : usuarios) {
                    String rol = u.getTipoUsuario();

                    // Adaptar el rol antiguo al nuevo sistema
                    if ("Superadministrador".equals(rol)) {
                        rol = "Administrador";
                    }

                    escritor.write(
                    limpiar(u.getNombre()) + ";" +
                    limpiar(u.getApellido()) + ";" +
                    limpiar(u.getCorreo()) + ";" +
                    limpiar(u.getGenero()) + ";" +
                    limpiar(u.getUsuario()) + ";" +
                    limpiar(u.getContrasena()) + ";" +
                    limpiar(rol) +
                    System.lineSeparator()
);
                }
            }

            System.out.println("Migración completada.");
            System.out.println("Usuarios migrados: " + usuarios.size());
            System.out.println("Archivo creado en: " + archivoTxt.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Error durante la migración: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String limpiar(String texto) {
    if (texto == null) {
        return "";
    }

    return texto.replace(";", ",")
                .replace("\n", " ")
                .replace("\r", " ");
}
}

