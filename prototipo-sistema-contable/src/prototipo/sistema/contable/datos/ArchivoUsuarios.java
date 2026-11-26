package prototipo.sistema.contable.datos;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import prototipo.sistema.contable.modelo.Usuario;

public class ArchivoUsuarios {

    private final File archivo;

    public ArchivoUsuarios() {
        archivo = new File("usuarios.txt");

        System.out.println("Ubicación del archivo: "
                + archivo.getAbsolutePath());
    }

    // Recuperar todos los usuarios.
    public ArrayList<Usuario> cargarUsuarios() throws IOException {

        ArrayList<Usuario> usuarios = new ArrayList<>();

        if (!archivo.exists()) {
            return usuarios;
        }

        List<String> lineas = Files.readAllLines(
                archivo.toPath(), StandardCharsets.UTF_8);

        int numeroLinea = 0;

        for (String linea : lineas) {

            numeroLinea++;

            if (linea.trim().isEmpty()) {
                continue;
            }

            String[] datos = linea.split(";", -1);

            // Se aceptan los registros antiguos de 7 campos
            // y los nuevos de 8 campos.
            if (datos.length != 7 && datos.length != 8) {
                throw new IOException(
                        "Formato incorrecto en la línea " + numeroLinea);
            }

            String estado = "ACTIVO";

            if (datos.length == 8 && !datos[7].trim().isEmpty()) {
                estado = datos[7];
            }

            Usuario usuario = new Usuario(
                    datos[0],
                    datos[1],
                    datos[2],
                    datos[3],
                    datos[4],
                    datos[5],
                    datos[6],
                    estado
            );

            usuarios.add(usuario);
        }

        return usuarios;
    }

    // Guardar todos los usuarios, incluyendo su estado.
    public void guardarUsuarios(List<Usuario> usuarios)
            throws IOException {

        List<String> lineas = new ArrayList<>();

        for (Usuario u : usuarios) {

            String linea =
                    limpiar(u.getNombre()) + ";" +
                    limpiar(u.getApellido()) + ";" +
                    limpiar(u.getCorreo()) + ";" +
                    limpiar(u.getGenero()) + ";" +
                    limpiar(u.getUsuario()) + ";" +
                    limpiar(u.getContrasena()) + ";" +
                    limpiar(u.getTipoUsuario()) + ";" +
                    limpiar(u.getEstado());

            lineas.add(linea);
        }

        File temporal = new File("usuarios_temp.txt");

        Files.write(
                temporal.toPath(),
                lineas,
                StandardCharsets.UTF_8
        );

        try {
            Files.move(
                    temporal.toPath(),
                    archivo.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException e) {
            temporal.delete();
            throw e;
        }
    }

    // Agregar un usuario nuevo.
    public boolean agregarUsuario(Usuario nuevoUsuario)
            throws IOException {

        if (nuevoUsuario == null ||
                nuevoUsuario.getUsuario() == null ||
                nuevoUsuario.getUsuario().trim().isEmpty()) {
            return false;
        }

        ArrayList<Usuario> usuarios = cargarUsuarios();

        for (Usuario u : usuarios) {

            // No permite repetir nombres de usuario,
            // incluso si el registro está inactivo.
            if (u.getUsuario().equalsIgnoreCase(
                    nuevoUsuario.getUsuario())) {
                return false;
            }
        }

        nuevoUsuario.setEstado("ACTIVO");

        usuarios.add(nuevoUsuario);

        guardarUsuarios(usuarios);

        return true;
    }

    // Modificar un usuario sin perder su estado.
 
    // Modificar un usuario conservando su estado actual.
    public boolean modificarUsuario(
            String nombreUsuario, Usuario usuarioModificado) {

        return modificarUsuario(nombreUsuario, usuarioModificado, null);
    }

    // Modificar un usuario permitiendo cambiar su estado.
    public boolean modificarUsuario(
            String nombreUsuario,
            Usuario usuarioModificado,
            String nuevoEstado) {

        if (nombreUsuario == null ||
                nombreUsuario.trim().isEmpty() ||
                usuarioModificado == null ||
                usuarioModificado.getUsuario() == null ||
                usuarioModificado.getUsuario().trim().isEmpty()) {
            return false;
        }

        try {
            ArrayList<Usuario> usuarios = cargarUsuarios();
            Usuario usuarioOriginal = null;

            for (Usuario u : usuarios) {
                if (u.getUsuario().equalsIgnoreCase(nombreUsuario)) {
                    usuarioOriginal = u;
                    break;
                }
            }

            if (usuarioOriginal == null) {
                return false;
            }

            // Comprobar que el nombre no pertenezca a otro usuario.
            for (Usuario u : usuarios) {
                if (u != usuarioOriginal &&
                        u.getUsuario().equalsIgnoreCase(
                                usuarioModificado.getUsuario())) {
                    return false;
                }
            }

            // Si no se indica un estado nuevo, conservar el original.
            if (nuevoEstado == null) {
                usuarioModificado.setEstado(usuarioOriginal.getEstado());
            } else {
                usuarioModificado.setEstado(nuevoEstado);
            }

            int posicion = usuarios.indexOf(usuarioOriginal);
            usuarios.set(posicion, usuarioModificado);

            guardarUsuarios(usuarios);

            return true;

        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    
    // Desactivar un usuario sin eliminar su registro.
    public boolean eliminarUsuario(String nombreUsuario) {

        if (nombreUsuario == null ||
                nombreUsuario.trim().isEmpty()) {
            return false;
        }

        try {

            ArrayList<Usuario> usuarios = cargarUsuarios();

            for (Usuario u : usuarios) {

                if (u.getUsuario().equalsIgnoreCase(nombreUsuario)) {

                    if ("INACTIVO".equalsIgnoreCase(u.getEstado())) {
                        return true;
                    }

                    u.setEstado("INACTIVO");

                    guardarUsuarios(usuarios);

                    return true;
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        return false;
    }

    // Reactivar un usuario anteriormente desactivado.
    public boolean reactivarUsuario(String nombreUsuario) {

        if (nombreUsuario == null ||
                nombreUsuario.trim().isEmpty()) {
            return false;
        }

        try {

            ArrayList<Usuario> usuarios = cargarUsuarios();

            for (Usuario u : usuarios) {

                if (u.getUsuario().equalsIgnoreCase(nombreUsuario)) {

                    u.setEstado("ACTIVO");

                    guardarUsuarios(usuarios);

                    return true;
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        return false;
    }

    // Limpiar los datos antes de guardarlos.
    private String limpiar(String texto) {

        if (texto == null) {
            return "";
        }

        return texto.replace(";", ",")
                    .replace("\n", " ")
                    .replace("\r", " ");
    }
}