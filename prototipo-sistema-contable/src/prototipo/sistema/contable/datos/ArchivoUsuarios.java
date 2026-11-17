package prototipo.sistema.contable.datos;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import prototipo.sistema.contable.modelo.Usuario;

public class ArchivoUsuarios {
    private final File archivo;
    
    // Constructor
    public ArchivoUsuarios() {

        File carpeta = new File(
            System.getProperty("user.home"),
            ".miSistema"
        );

        archivo = new File(carpeta, "usuarios.dat");
    }
    
        // Método para recuperar los usuarios
    public ArrayList<Usuario> cargarUsuarios()
            throws IOException, ClassNotFoundException {

        ArrayList<Usuario> usuarios = new ArrayList<>();

        if (!archivo.exists()) {
            return usuarios;
        }

        try (ObjectInputStream entrada = new ObjectInputStream(
                new FileInputStream(archivo))) {

            Object objeto = entrada.readObject();

            if (!(objeto instanceof ArrayList<?>)) {
                throw new IOException("El archivo tiene un formato incorrecto.");
            }

            for (Object elemento : (ArrayList<?>) objeto) {

                if (!(elemento instanceof Usuario)) {
                    throw new IOException("Se encontró un dato inválido.");
                }

                usuarios.add((Usuario) elemento);
            }
        }

        return usuarios;
    }
    
        // Método para guardar los usuarios
    public void guardarUsuarios(List<Usuario> usuarios)
            throws IOException {

        File carpeta = archivo.getParentFile();

        if (!carpeta.exists() && !carpeta.mkdirs()) {
            throw new IOException("No se pudo crear la carpeta.");
        }

        try (ObjectOutputStream salida = new ObjectOutputStream(
                new FileOutputStream(archivo))) {

            salida.writeObject(new ArrayList<>(usuarios));
        }
    }
    
        // Método para agregar un usuario
    public boolean agregarUsuario(Usuario nuevoUsuario)
            throws IOException, ClassNotFoundException {

        ArrayList<Usuario> usuarios = cargarUsuarios();

        for (Usuario u : usuarios) {

            if (u.getUsuario().equalsIgnoreCase(    //no diferencia entre mayusculas/minusculas para evitar duplicacion de usuarios
                    nuevoUsuario.getUsuario())) {

                return false;
            }
        }

        usuarios.add(nuevoUsuario);

        guardarUsuarios(usuarios);

        return true;
    }
    
}
