

package prototipo.sistema.contable.datos;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import prototipo.sistema.contable.modelo.Usuario;

public class ArchivoUsuarios {

    private final File archivo;

    // Constructor
    public ArchivoUsuarios() {
        archivo = new File("usuarios.txt");
    }

    // Método para recuperar los usuarios
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

            if (datos.length != 7) {
                throw new IOException(
                        "Formato incorrecto en la línea " + numeroLinea);
            }

            Usuario usuario = new Usuario(
                    datos[0],
                    datos[1],
                    datos[2],
                    datos[3],
                    datos[4],
                    datos[5],
                    datos[6]
            );

            usuarios.add(usuario);
        }

        return usuarios;
    }

    // Método para guardar los usuarios
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
                    limpiar(u.getTipoUsuario());

            lineas.add(linea);
        }

        Files.write(
                archivo.toPath(),
                lineas,
                StandardCharsets.UTF_8
        );
    }

    // Método para agregar un usuario
    public boolean agregarUsuario(Usuario nuevoUsuario)
            throws IOException {

        if (nuevoUsuario == null ||
                nuevoUsuario.getUsuario() == null ||
                nuevoUsuario.getUsuario().trim().isEmpty()) {
            return false;
        }

        ArrayList<Usuario> usuarios = cargarUsuarios();

        for (Usuario u : usuarios) {

            if (u.getUsuario().equalsIgnoreCase(
                    nuevoUsuario.getUsuario())) {

                return false;
            }
        }

        usuarios.add(nuevoUsuario);

        guardarUsuarios(usuarios);

        return true;
    }

    // Método para modificar un usuario
    public boolean modificarUsuario(
            String nombreUsuario, Usuario usuarioModificado) {

        File temporal = new File("usuarios_temp.txt");

        if (nombreUsuario == null ||
                nombreUsuario.trim().isEmpty() ||
                usuarioModificado == null ||
                usuarioModificado.getUsuario() == null ||
                usuarioModificado.getUsuario().trim().isEmpty()) {
            return false;
        }

        if (temporal.exists() && !temporal.delete()) {
            return false;
        }

        if (!archivo.exists()) {
            return false;
        }

        boolean encontrado = false;
        boolean usuarioDuplicado = false;

        try {
            Scanner lector = new Scanner(archivo);

            while (lector.hasNextLine()) {

                String linea = lector.nextLine();

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";", -1);

                if (datos.length != 7) {
                    lector.close();
                    temporal.delete();
                    return false;
                }

                Scanner separador = new Scanner(linea);
                separador.useDelimiter(";");

                String nombre = separador.next();
                String apellido = separador.next();
                String correo = separador.next();
                String genero = separador.next();
                String usuario = separador.next();
                String contrasena = separador.next();
                String tipoUsuario = separador.next();

                separador.close();

                if (usuario.equalsIgnoreCase(nombreUsuario)) {

                    encontrado = true;

                    String nuevaLinea =
                            limpiar(usuarioModificado.getNombre()) + ";" +
                            limpiar(usuarioModificado.getApellido()) + ";" +
                            limpiar(usuarioModificado.getCorreo()) + ";" +
                            limpiar(usuarioModificado.getGenero()) + ";" +
                            limpiar(usuarioModificado.getUsuario()) + ";" +
                            limpiar(usuarioModificado.getContrasena()) + ";" +
                            limpiar(usuarioModificado.getTipoUsuario());

                    if (!escribir(temporal, nuevaLinea)) {
                        lector.close();
                        temporal.delete();
                        return false;
                    }

                } else {

                    if (usuario.equalsIgnoreCase(
                            usuarioModificado.getUsuario())) {

                        usuarioDuplicado = true;
                    }

                    if (!escribir(temporal, linea)) {
                        lector.close();
                        temporal.delete();
                        return false;
                    }
                }
            }

            lector.close();

        } catch (Exception e) {

            System.out.println(e);
            temporal.delete();
            return false;
        }

        if (!encontrado || usuarioDuplicado) {
            temporal.delete();
            return false;
        }

        return reemplazarArchivo(temporal);
    }

    // Método para eliminar un usuario
    public boolean eliminarUsuario(String nombreUsuario) {

        File temporal = new File("usuarios_temp.txt");

        if (nombreUsuario == null ||
                nombreUsuario.trim().isEmpty()) {
            return false;
        }

        if (temporal.exists() && !temporal.delete()) {
            return false;
        }

        if (!archivo.exists()) {
            return false;
        }

        boolean encontrado = false;

        try {
            temporal.createNewFile();

            Scanner lector = new Scanner(archivo);

            while (lector.hasNextLine()) {

                String linea = lector.nextLine();

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";", -1);

                if (datos.length != 7) {
                    lector.close();
                    temporal.delete();
                    return false;
                }

                Scanner separador = new Scanner(linea);
                separador.useDelimiter(";");

                String nombre = separador.next();
                String apellido = separador.next();
                String correo = separador.next();
                String genero = separador.next();
                String usuario = separador.next();
                String contrasena = separador.next();
                String tipoUsuario = separador.next();

                separador.close();

                if (usuario.equalsIgnoreCase(nombreUsuario)) {

                    encontrado = true;

                } else {

                    if (!escribir(temporal, linea)) {
                        lector.close();
                        temporal.delete();
                        return false;
                    }
                }
            }

            lector.close();

        } catch (Exception e) {

            System.out.println(e);
            temporal.delete();
            return false;
        }

        if (!encontrado) {
            temporal.delete();
            return false;
        }

        return reemplazarArchivo(temporal);
    }

    // Método para reemplazar el archivo original por el temporal
    private boolean reemplazarArchivo(File temporal) {

        File respaldo = new File("usuarios_respaldo.txt");

        if (respaldo.exists()) {
            System.out.println("Ya existe un archivo de respaldo.");
            temporal.delete();
            return false;
        }

        if (archivo.renameTo(respaldo)) {

            if (temporal.renameTo(archivo)) {

                if (!respaldo.delete()) {
                    System.out.println(
                            "Advertencia: no se pudo eliminar el respaldo.");
                }

                return true;

            } else {

                System.out.println(
                        "No se pudo renombrar el archivo temporal.");

                if (!respaldo.renameTo(archivo)) {
                    System.out.println(
                            "No se pudo recuperar el archivo original.");
                }

                temporal.delete();
                return false;
            }

        } else {

            System.out.println(
                    "No se pudo crear el respaldo del archivo original.");

            temporal.delete();
            return false;
        }
    }

    // Método para escribir en el archivo temporal
    private boolean escribir(File fichero, String cadena) {

        try {

            if (!fichero.exists()) {
                fichero.createNewFile();
            }

            FileWriter escritor = new FileWriter(fichero, true);

            escritor.write(cadena + System.lineSeparator());

            escritor.close();

            return true;

        } catch (Exception e) {

            System.out.println(e);
            return false;
        }
    }

    // Método para limpiar los datos antes de guardarlos
    private String limpiar(String texto) {

        if (texto == null) {
            return "";
        }

        return texto.replace(";", ",")
                    .replace("\n", " ")
                    .replace("\r", " ");
    }
}


