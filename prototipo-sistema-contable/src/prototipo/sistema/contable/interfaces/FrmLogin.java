package prototipo.sistema.contable.interfaces;

import Seguridad.Autenticacion;
import prototipo.sistema.contable.modelo.Usuario;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class FrmLogin extends javax.swing.JFrame {

    private javax.swing.JLabel lblCapsLock;
    
    public FrmLogin() {
        initComponents();

        caracterOculto = txtContrasena.getEchoChar();

        configurarAvisoCapsLock();

        setLocationRelativeTo(null);
        setResizable(false);
    }

    private Autenticacion autenticacion = new Autenticacion();
    
private void configurarAvisoCapsLock() {

    lblCapsLock = new javax.swing.JLabel("¡Bloq Mayús está activado!");

    lblCapsLock.setFont(new java.awt.Font("SansSerif", 0, 12));
    lblCapsLock.setForeground(new java.awt.Color(180, 80, 80));
    lblCapsLock.setVisible(false);

    // Agregar la etiqueta al panel
    pnlLogin.add(lblCapsLock);

    // Escuchar las teclas del campo de contraseña
    txtContrasena.addKeyListener(new KeyAdapter() {

        @Override
        public void keyPressed(KeyEvent e) {
            actualizarAvisoCapsLock();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            actualizarAvisoCapsLock();
        }

    });

    // Posicionar la etiqueta debajo del campo
    lblCapsLock.setBounds(
            txtContrasena.getX(),
            txtContrasena.getY() + txtContrasena.getHeight() + 3,
            250,
            20
    );

    pnlLogin.setComponentZOrder(lblCapsLock, 0);
}

private void actualizarAvisoCapsLock() {

    try {

        boolean activado = Toolkit.getDefaultToolkit()
                .getLockingKeyState(KeyEvent.VK_CAPS_LOCK);

        lblCapsLock.setVisible(activado);

    } catch (Exception e) {

        lblCapsLock.setVisible(false);

    }
}
    
    private char caracterOculto;
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        pnlMarca = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblNombreSistema = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlLogin = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnIniciarSesion = new javax.swing.JButton();
        txtContrasena = new javax.swing.JPasswordField();
        btnSalir = new javax.swing.JButton();
        btnMostrarContrasena = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(164, 110, 198));
        setPreferredSize(new java.awt.Dimension(1000, 600));
        setSize(new java.awt.Dimension(1000, 600));

        pnlPrincipal.setMaximumSize(new java.awt.Dimension(1000, 600));
        pnlPrincipal.setPreferredSize(new java.awt.Dimension(1000, 600));

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        pnlMarca.setBackground(new java.awt.Color(90, 48, 73));
        pnlMarca.setAlignmentX(75.0F);
        pnlMarca.setAlignmentY(205.0F);
        pnlMarca.setPreferredSize(new java.awt.Dimension(400, 600));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(221, 198, 207));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Gestión y control en un solo lugar");
        jLabel3.setAlignmentX(40.0F);
        jLabel3.setAlignmentY(325.0F);
        jLabel3.setPreferredSize(new java.awt.Dimension(320, 45));

        jLabel4.setFont(new java.awt.Font("Serif", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(221, 198, 207));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("C");
        jLabel4.setPreferredSize(new java.awt.Dimension(80, 80));

        lblNombreSistema.setFont(new java.awt.Font("Serif", 1, 32)); // NOI18N
        lblNombreSistema.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreSistema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreSistema.setText("CONSULT");
        lblNombreSistema.setAlignmentX(50.0F);
        lblNombreSistema.setAlignmentY(205.0F);
        lblNombreSistema.setPreferredSize(new java.awt.Dimension(300, 55));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(221, 198, 207));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("SISTEMA CONTABLE");
        jLabel6.setAlignmentX(50.0F);
        jLabel6.setAlignmentY(265.0F);
        jLabel6.setPreferredSize(new java.awt.Dimension(300, 35));

        jSeparator1.setAlignmentX(75.0F);
        jSeparator1.setAlignmentY(205.0F);
        jSeparator1.setPreferredSize(new java.awt.Dimension(450, 10));

        javax.swing.GroupLayout pnlMarcaLayout = new javax.swing.GroupLayout(pnlMarca);
        pnlMarca.setLayout(pnlMarcaLayout);
        pnlMarcaLayout.setHorizontalGroup(
            pnlMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMarcaLayout.createSequentialGroup()
                .addGroup(pnlMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlMarcaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNombreSistema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlMarcaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlMarcaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
                    .addGroup(pnlMarcaLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMarcaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        pnlMarcaLayout.setVerticalGroup(
            pnlMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMarcaLayout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(lblNombreSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(235, Short.MAX_VALUE))
        );

        pnlLogin.setBackground(new java.awt.Color(245, 241, 235));
        pnlLogin.setPreferredSize(new java.awt.Dimension(600, 600));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(90, 48, 73));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Bienvenido/a");
        lblTitulo.setAlignmentX(100.0F);
        lblTitulo.setAlignmentY(115.0F);
        lblTitulo.setPreferredSize(new java.awt.Dimension(400, 50));

        txtUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(56, 48, 56));
        txtUsuario.setToolTipText("Ingrese su nombre de usuario");
        txtUsuario.setAlignmentX(65.0F);
        txtUsuario.setAlignmentY(205.0F);
        txtUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        txtUsuario.setPreferredSize(new java.awt.Dimension(470, 25));

        btnIniciarSesion.setBackground(new java.awt.Color(90, 48, 73));
        btnIniciarSesion.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        btnIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarSesion.setText("INICIAR SESIÓN");
        btnIniciarSesion.setAlignmentX(65.0F);
        btnIniciarSesion.setAlignmentY(435.0F);
        btnIniciarSesion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnIniciarSesion.setBorderPainted(false);
        btnIniciarSesion.setFocusPainted(false);
        btnIniciarSesion.setFocusable(false);
        btnIniciarSesion.setMargin(new java.awt.Insets(10, 15, 10, 15));
        btnIniciarSesion.setPreferredSize(new java.awt.Dimension(300, 48));
        btnIniciarSesion.setRequestFocusEnabled(false);
        btnIniciarSesion.setRolloverEnabled(false);
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        txtContrasena.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtContrasena.setForeground(new java.awt.Color(56, 48, 56));
        txtContrasena.setToolTipText("Ingrese su contraseña");
        txtContrasena.setAlignmentX(65.0F);
        txtContrasena.setAlignmentY(330.0F);
        txtContrasena.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        txtContrasena.setPreferredSize(new java.awt.Dimension(470, 42));

        btnSalir.setBackground(new java.awt.Color(234, 226, 229));
        btnSalir.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(90, 48, 73));
        btnSalir.setText("SALIR");
        btnSalir.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnSalir.setBorderPainted(false);
        btnSalir.setFocusPainted(false);
        btnSalir.setFocusable(false);
        btnSalir.setRequestFocusEnabled(false);
        btnSalir.setRolloverEnabled(false);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnMostrarContrasena.setBackground(new java.awt.Color(245, 241, 235));
        btnMostrarContrasena.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        btnMostrarContrasena.setForeground(new java.awt.Color(90, 48, 73));
        btnMostrarContrasena.setSelected(true);
        btnMostrarContrasena.setText("Mostrar contraseña");
        btnMostrarContrasena.setAlignmentX(65.0F);
        btnMostrarContrasena.setAlignmentY(380.0F);
        btnMostrarContrasena.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnMostrarContrasena.setBorderPainted(false);
        btnMostrarContrasena.setContentAreaFilled(false);
        btnMostrarContrasena.setFocusable(false);
        btnMostrarContrasena.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnMostrarContrasena.setMargin(new java.awt.Insets(5, 15, 5, 15));
        btnMostrarContrasena.setPreferredSize(new java.awt.Dimension(100, 30));
        btnMostrarContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarContrasenaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(132, 124, 127));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ingresa tus credenciales para continuar");

        jSeparator2.setForeground(new java.awt.Color(90, 48, 73));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(90, 48, 73));
        jLabel2.setText("Nombre de Usuario:");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(90, 48, 73));
        jLabel5.setText("Contraseña: ");

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLayout.createSequentialGroup()
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlLoginLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMostrarContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel5)
                        .addGap(382, 382, 382)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMostrarContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMostrarContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarContrasenaActionPerformed
        if (btnMostrarContrasena.isSelected()) {

            txtContrasena.setEchoChar((char) 0);
            btnMostrarContrasena.setText("Ocultar contraseña");

        } else {

            txtContrasena.setEchoChar(caracterOculto);
            btnMostrarContrasena.setText("Mostrar contraseña");

        }
    }//GEN-LAST:event_btnMostrarContrasenaActionPerformed

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed

        String usuario = txtUsuario.getText().trim();

        String contrasena = new String(
                txtContrasena.getPassword()
        );

        // Comprobar campos vacíos
        if (usuario.isEmpty() || contrasena.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Por favor, complete todos los campos.",
                    "Campos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            Usuario usuarioActual = autenticacion.iniciarSesion(
                    usuario,
                    contrasena
            );

            if (usuarioActual != null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Bienvenido/a " + usuarioActual.getNombre(),
                        "Inicio de sesión exitoso",
                        JOptionPane.INFORMATION_MESSAGE
                );

                FrmMenuPrincipal menu = new FrmMenuPrincipal(usuarioActual);
                menu.setVisible(true);

                this.dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "El usuario o la contraseña son incorrectos.",
                        "Error de autenticación",
                        JOptionPane.ERROR_MESSAGE
                );

                txtContrasena.setText("");
                txtContrasena.requestFocus();

            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo acceder a los datos de los usuarios: "
                            + e.getMessage(),
                    "Error del sistema",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JToggleButton btnMostrarContrasena;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblNombreSistema;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPanel pnlMarca;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
