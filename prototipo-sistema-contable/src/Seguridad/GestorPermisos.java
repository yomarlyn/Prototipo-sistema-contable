

package Seguridad;

import prototipo.sistema.contable.modelo.Usuario;

public class GestorPermisos {

    // Verifica si el administrador puede crear un usuario
    public static boolean puedeCrearUsuario(
            Usuario usuarioActual, String tipoNuevoUsuario) {

        if (usuarioActual == null) {
            return false;
        }

        String rolActual = usuarioActual.getTipoUsuario();

        // El administrador puede crear administradores y usuarios
        if (rolActual.equals("Administrador")) {
            return tipoNuevoUsuario.equals("Administrador")
                    || tipoNuevoUsuario.equals("Usuario");
        }

        return false;
    }

    // Verifica si puede administrar usuarios
    public static boolean puedeAdministrarUsuarios(
            Usuario usuarioActual) {

        if (usuarioActual == null) {
            return false;
        }

        return usuarioActual.getTipoUsuario().equals("Administrador");//Determina si es administrador o usuario
    }
}
