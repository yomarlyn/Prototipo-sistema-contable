
package prototipo.sistema.contable;

import Seguridad.InicializadorSistema;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import prototipo.sistema.contable.interfaces.FrmLogin;

public class PrototipoSistemaContable {

    public static void main(String[] args) {

        try {

            // Comprobar si es necesario crear el administrador inicial
            InicializadorSistema.crearAdministradorInicial();

            // Abrir la ventana de inicio de sesión
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FrmLogin login = new FrmLogin();
                    login.setLocationRelativeTo(null);
                    login.setVisible(true);
                }
            });

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al iniciar el sistema: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}

