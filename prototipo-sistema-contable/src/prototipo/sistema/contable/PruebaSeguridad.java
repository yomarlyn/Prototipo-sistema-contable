
package prototipo.sistema.contable;

import java.io.IOException;
import prototipo.sistema.contable.modelo.Usuario;
import prototipo.sistema.contable.datos.ArchivoUsuarios;
import Seguridad.Autenticacion;
import Seguridad.GestorContrasenas;
import Seguridad.GestorPermisos;

public class PruebaSeguridad {

    public static void main(String[] args) {

        try {

            ArchivoUsuarios archivo = new ArchivoUsuarios();

            // Crear un usuario de prueba
            String contrasena = "Prueba123";
            String hash = GestorContrasenas.generarHash(contrasena);

            Usuario usuarioPrueba = new Usuario(
                    "Maria",
                    "Mencia",
                    "maria@gmail.com",
                    "Femenino",
                    "maria02",
                    hash,
                    "Administrador"
            );

            boolean agregado = archivo.agregarUsuario(usuarioPrueba);
            System.out.println("Usuario agregado: " + agregado);
            
            

            if (!agregado) {
                System.out.println("El usuario de prueba ya existe.");
            }

            // Probar autenticación
            Autenticacion autenticacion = new Autenticacion();

            Usuario resultado = autenticacion.iniciarSesion(
                    "maria02", "Prueba123"
            );
            
            if (resultado != null) {
                System.out.println("Hash guardado: " + resultado.getContrasena());
            }

            if (resultado != null) {
                System.out.println("Inicio de sesión correcto.");
                System.out.println("Usuario: " + resultado.getUsuario());
            } else {
                System.out.println("Error al iniciar sesión.");
            }

            // Probar contraseña incorrecta
            Usuario resultadoIncorrecto = autenticacion.iniciarSesion(
                    "maria02", "ContraseñaIncorrecta"
            );

            System.out.println(
                    "Contraseña incorrecta rechazada: "
                    + (resultadoIncorrecto == null)
            );

            // Probar permisos
            System.out.println(
                    "Administrador puede crear Usuario: "
                    + GestorPermisos.puedeCrearUsuario(
                            usuarioPrueba, "Usuario"
                    )
            );

            System.out.println(
                    "Administrador puede crear Superadministrador: "
                    + GestorPermisos.puedeCrearUsuario(
                            usuarioPrueba, "Superadministrador"
                    )
            );

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en la prueba: " + e.getMessage());
        }
    }
}
