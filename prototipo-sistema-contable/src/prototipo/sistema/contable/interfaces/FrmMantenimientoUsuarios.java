
package prototipo.sistema.contable.interfaces;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.io.IOException;
import prototipo.sistema.contable.datos.ArchivoUsuarios;
import prototipo.sistema.contable.modelo.Usuario;
import Seguridad.GestorContrasenas;

public class FrmMantenimientoUsuarios extends javax.swing.JFrame {

    private ArchivoUsuarios archivoUsuarios = new ArchivoUsuarios();
    private String usuarioOriginal = null;

    public FrmMantenimientoUsuarios() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private void limpiarCampos() {//Método Limpiar

    txtNombre.setText("");
    txtApellido.setText("");
    txtCorreo.setText("");
    txtUsuario.setText("");
    txtContrasena.setText("");

    cmbGenero.setSelectedIndex(0);
    cmbTipoUsuario.setSelectedIndex(0);

    usuarioOriginal = null;
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblContrasena = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        cmbGenero = new javax.swing.JComboBox<>();
        txtContrasena = new javax.swing.JPasswordField();
        lblTipoUsuario = new javax.swing.JLabel();
        cmbTipoUsuario = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mantenimiento de usuarios - CONSULT");
        setBackground(new java.awt.Color(245, 241, 234));

        jPanel1.setBackground(new java.awt.Color(89, 51, 75));

        lblTitulo.setBackground(new java.awt.Color(255, 255, 255));
        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 26)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Mantenimiento de usuarios");

        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(240, 220, 230));
        lblSubtitulo.setText("CONSULT - SISTEMA CONTABLE");

        jPanel3.setBackground(new java.awt.Color(245, 241, 234));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 615, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSubtitulo)
                    .addComponent(lblTitulo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubtitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(89, 51, 75));
        jLabel3.setText("Datos del usuario");

        lblNombre.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(89, 51, 75));
        lblNombre.setText("Nombre");

        lblApellido.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblApellido.setForeground(new java.awt.Color(89, 51, 75));
        lblApellido.setText("Apellido");

        lblCorreo.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblCorreo.setForeground(new java.awt.Color(89, 51, 75));
        lblCorreo.setText("Correo electrónico");

        lblGenero.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblGenero.setForeground(new java.awt.Color(89, 51, 75));
        lblGenero.setText("Género");

        lblUsuario.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(89, 51, 75));
        lblUsuario.setText("Nombre de usuario");

        lblContrasena.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblContrasena.setForeground(new java.awt.Color(89, 51, 75));
        lblContrasena.setText("Contraseña");

        txtNombre.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(89, 51, 75));
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtApellido.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtApellido.setForeground(new java.awt.Color(89, 51, 75));

        txtCorreo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(89, 51, 75));

        txtUsuario.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(89, 51, 75));

        cmbGenero.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cmbGenero.setForeground(new java.awt.Color(89, 51, 75));
        cmbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Masculino", "Femenino" }));
        cmbGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGeneroActionPerformed(evt);
            }
        });

        txtContrasena.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtContrasena.setForeground(new java.awt.Color(89, 51, 75));

        lblTipoUsuario.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblTipoUsuario.setForeground(new java.awt.Color(89, 51, 75));
        lblTipoUsuario.setText("Tipo de usuario");

        cmbTipoUsuario.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cmbTipoUsuario.setForeground(new java.awt.Color(89, 51, 75));
        cmbTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Administrador", "Usuario normal" }));
        cmbTipoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoUsuarioActionPerformed(evt);
            }
        });

        btnRegistrar.setBackground(new java.awt.Color(140, 90, 120));
        btnRegistrar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Registrar");
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(140, 90, 120));
        btnModificar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar");
        btnModificar.setBorderPainted(false);
        btnModificar.setFocusPainted(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(163, 77, 88));
        btnLimpiar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorderPainted(false);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(140, 90, 120));
        btnEliminar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnConsultar.setBackground(new java.awt.Color(140, 90, 120));
        btnConsultar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnConsultar.setForeground(new java.awt.Color(255, 255, 255));
        btnConsultar.setText("Consultar");
        btnConsultar.setBorderPainted(false);
        btnConsultar.setFocusPainted(false);
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(163, 77, 88));
        btnSalir.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Salir");
        btnSalir.setBorderPainted(false);
        btnSalir.setFocusPainted(false);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUsuario)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCorreo)
                            .addComponent(lblTipoUsuario)
                            .addComponent(cmbTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGenero)
                            .addComponent(lblApellido)
                            .addComponent(lblContrasena)
                            .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(btnModificar)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEliminar)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnConsultar))))))
                    .addComponent(lblNombre))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(lblApellido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreo)
                    .addComponent(lblGenero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContrasena))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTipoUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnConsultar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnSalir))
                .addGap(0, 35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       String usuario = txtUsuario.getText().trim();

if (usuario.isEmpty()) {

JOptionPane.showMessageDialog(this,
"Escriba o consulte el usuario que desea eliminar.");

return;
}

int confirmacion = JOptionPane.showConfirmDialog(
this,
"¿Está seguro de que desea eliminar este usuario?",
"Confirmar eliminación",
JOptionPane.YES_NO_OPTION
);

if (confirmacion == JOptionPane.YES_OPTION) {

boolean resultado = archivoUsuarios.eliminarUsuario(usuario);

if (resultado) {

JOptionPane.showMessageDialog(this,
"Usuario eliminado correctamente.");

limpiarCampos();

} else {

JOptionPane.showMessageDialog(this,
"No se pudo eliminar el usuario. Verifique que exista.");
    }
}
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
String nombre = txtNombre.getText().trim();
String apellido = txtApellido.getText().trim();
String correo = txtCorreo.getText().trim();
String genero = cmbGenero.getSelectedItem().toString();
String usuario = txtUsuario.getText().trim();
String contrasena = new String(txtContrasena.getPassword());
String contrasenaHash = GestorContrasenas.generarHash(contrasena);
String tipoUsuario = cmbTipoUsuario.getSelectedItem().toString();

if (nombre.isEmpty() || apellido.isEmpty() ||
    correo.isEmpty() || usuario.isEmpty() ||
    contrasena.isEmpty() ||
    genero.equals("Seleccione...") ||
    tipoUsuario.equals("Seleccione...")) {

    JOptionPane.showMessageDialog(this,
    "Complete todos los campos.");

    return;
}

Usuario nuevoUsuario = new Usuario(
nombre, apellido, correo, genero,
usuario, contrasenaHash, tipoUsuario
);

try {

    boolean resultado = archivoUsuarios.agregarUsuario(nuevoUsuario);

    if (resultado) {

    JOptionPane.showMessageDialog(this,
        "Usuario registrado correctamente.");

        limpiarCampos();

    } else {

        JOptionPane.showMessageDialog(this,
        "El nombre de usuario ya existe o los datos no son válidos.");
    }

} catch (IOException e) {

    JOptionPane.showMessageDialog(this,
            "Error al registrar el usuario: " + e.getMessage());
}  // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void cmbGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbGeneroActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void cmbTipoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoUsuarioActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
    if (usuarioOriginal == null) {

    JOptionPane.showMessageDialog(this,
            "Primero debe consultar el usuario que desea modificar.");

    return;
}

String nombre = txtNombre.getText().trim();
String apellido = txtApellido.getText().trim();
String correo = txtCorreo.getText().trim();
String genero = cmbGenero.getSelectedItem().toString();
String usuario = txtUsuario.getText().trim();
String contrasena = new String(txtContrasena.getPassword());
String tipoUsuario = cmbTipoUsuario.getSelectedItem().toString();

if (nombre.isEmpty() || apellido.isEmpty() ||
        correo.isEmpty() || usuario.isEmpty() ||
        contrasena.isEmpty() ||
        genero.equals("Seleccione...") ||
        tipoUsuario.equals("Seleccione...")) {

    JOptionPane.showMessageDialog(this,
            "Complete todos los campos.");

    return;
}

String contrasenaHash;

if (contrasena.isEmpty()) {

    try {
        ArrayList<Usuario> usuarios = archivoUsuarios.cargarUsuarios();
        contrasenaHash = null;

        for (Usuario u : usuarios) {
            if (u.getUsuario().equalsIgnoreCase(usuarioOriginal)) {
                contrasenaHash = u.getContrasena();
                break;
            }
        }

        if (contrasenaHash == null) {
            JOptionPane.showMessageDialog(this,
                    "No se encontró la contraseña original.");
            return;
        }

    } catch (IOException e) {
        JOptionPane.showMessageDialog(this,
                "Error al recuperar la contraseña: " + e.getMessage());
        return;
    }

} else {
    contrasenaHash = GestorContrasenas.generarHash(contrasena);
}

if (contrasena.isEmpty()) {

    try {
        ArrayList<Usuario> usuarios = archivoUsuarios.cargarUsuarios();
        contrasenaHash = null;

        for (Usuario u : usuarios) {
            if (u.getUsuario().equalsIgnoreCase(usuarioOriginal)) {
                contrasenaHash = u.getContrasena();
                break;
            }
        }
        
 if (contrasenaHash == null) {
            JOptionPane.showMessageDialog(this,
                    "No se encontró la contraseña original.");
            return;
        }

    } catch (IOException e) {
        JOptionPane.showMessageDialog(this,
                "Error al recuperar la contraseña: " + e.getMessage());
        return;
    }
} else {
    contrasenaHash = GestorContrasenas.generarHash(contrasena);
}

Usuario usuarioModificado = new Usuario(
        nombre, apellido, correo, genero,
        usuario, contrasenaHash, tipoUsuario
);

boolean resultado = archivoUsuarios.modificarUsuario(
        usuarioOriginal, usuarioModificado
);

if (resultado) {

    JOptionPane.showMessageDialog(this,
            "Usuario modificado correctamente.");

    limpiarCampos();

} else {

    JOptionPane.showMessageDialog(this,
            "No se pudo modificar el usuario. Verifique que el nuevo nombre de usuario no esté ocupado y que exista el usuario original.");
}
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        int opcion = JOptionPane.showConfirmDialog(
        this,
        "¿Desea salir del mantenimiento de usuarios?",
        "Confirmar salida",
        JOptionPane.YES_NO_OPTION
);

if (opcion == JOptionPane.YES_OPTION) {
    this.dispose();
}
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        String usuarioBuscado = txtUsuario.getText().trim();

if (usuarioBuscado.isEmpty()) {
    JOptionPane.showMessageDialog(this,
            "Escriba el nombre de usuario que desea consultar.");
    return;
}

try {

    ArrayList<Usuario> usuarios = archivoUsuarios.cargarUsuarios();

    boolean encontrado = false;

    for (Usuario u : usuarios) {

        if (u.getUsuario().equalsIgnoreCase(usuarioBuscado)) {

            txtNombre.setText(u.getNombre());
            txtApellido.setText(u.getApellido());
            txtCorreo.setText(u.getCorreo());
            cmbGenero.setSelectedItem(u.getGenero());
            txtUsuario.setText(u.getUsuario());
            txtContrasena.setText(u.getContrasena());
            cmbTipoUsuario.setSelectedItem(u.getTipoUsuario());

            usuarioOriginal = u.getUsuario();

            encontrado = true;
            break;
        }
    }

    if (encontrado) {

        JOptionPane.showMessageDialog(this,
                "Usuario encontrado.");

    } else {

        usuarioOriginal = null;

        JOptionPane.showMessageDialog(this,
                "No se encontró el usuario.");

    }

} catch (IOException e) {

    JOptionPane.showMessageDialog(this,
            "Error al consultar: " + e.getMessage());
}
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
      limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    /**
     * @param args the command line arguments
     */
        public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMantenimientoUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cmbGenero;
    private javax.swing.JComboBox<String> cmbTipoUsuario;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTipoUsuario;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
