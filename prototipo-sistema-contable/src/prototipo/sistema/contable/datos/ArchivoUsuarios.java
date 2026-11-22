
package prototipo.sistema.contable.datos;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.util.Scanner;

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
    
    // Para modificar usuarios
    public boolean modificarUsuario(String nombreUsuario, Usuario usuarioModificado) {

    File temporal = new File("usuarios_temp.txt");

    if (!archivo.exists()) {
        return false;
    }
    
    boolean encontrado = false;

    try {
    Scanner lector = new Scanner(archivo);

    while (lector.hasNextLine()) {
        String linea = lector.nextLine();

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
    }

    lector.close();

    } catch (Exception e) {
    System.out.println(e);
    return false;
    }

    return encontrado;

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

