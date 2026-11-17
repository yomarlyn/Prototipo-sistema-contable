
package Seguridad;

import prototipo.sistema.contable.modelo.Usuario;

public class GestorPermisos {

    public static boolean puedeCrearUsuario(
            Usuario usuarioActual, String tipoNuevoUsuario) {

        if (usuarioActual == null) {
            return false;
        }

        String rolActual = usuarioActual.getTipoUsuario();

        if (rolActual.equals("Superadministrador")) {
            return true;
        }

        if (rolActual.equals("Administrador")) {
            return tipoNuevoUsuario.equals("Usuario");
        }

        return false;
    }

    public static boolean puedeAdministrarUsuarios(
            Usuario usuarioActual) {

        if (usuarioActual == null) {
            return false;
        }

        String rol = usuarioActual.getTipoUsuario();

        return rol.equals("Administrador")
                || rol.equals("Superadministrador");
    }
}
