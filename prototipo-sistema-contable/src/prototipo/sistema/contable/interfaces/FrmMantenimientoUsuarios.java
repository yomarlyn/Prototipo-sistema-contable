
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
        setUndecorated(true);
        initComponents();

        setDefaultCloseOperation(
            javax.swing.WindowConstants.DISPOSE_ON_CLOSE
        );

        btnEliminar.setText("Desactivar");

        getContentPane().removeAll();
        getContentPane().setLayout(
            new java.awt.BorderLayout()
        );

        getContentPane().add(
            pnlMantenimiento,
            java.awt.BorderLayout.CENTER
        );

        construirInterfazCodigo();

        setSize(950, 650);
        setLocationRelativeTo(null);
    }
    
    
private int modoActual = 0;
// 0 = Crear
// 1 = Modificar
// 2 = Eliminar

private javax.swing.JPanel panelFormulario;
private javax.swing.JPanel panelAcciones;

private javax.swing.JButton btnCrearPestana;
private javax.swing.JButton btnModificarPestana;
private javax.swing.JButton btnEliminarPestana;

private void construirInterfazCodigo() {

    java.awt.Color fondo =
        new java.awt.Color(245, 241, 234);

    java.awt.Color morado =
        new java.awt.Color(89, 51, 75);

    java.awt.Color rosado =
        new java.awt.Color(239, 226, 232);

    pnlMantenimiento.removeAll();
    pnlMantenimiento.setLayout(
        new java.awt.BorderLayout(0, 0)
    );
    pnlMantenimiento.setBackground(fondo);

    // ================= ENCABEZADO =================

    javax.swing.JPanel encabezado =
        new javax.swing.JPanel(
            new java.awt.BorderLayout()
        );

    encabezado.setBackground(morado);
    encabezado.setBorder(
        javax.swing.BorderFactory.createEmptyBorder(
            20, 30, 20, 30
        )
    );

    lblTitulo.setText("Mantenimiento de usuarios");
    lblSubtitulo.setText("CONSULT - SISTEMA CONTABLE");

    javax.swing.JPanel textos =
        new javax.swing.JPanel(
            new java.awt.GridLayout(2, 1)
        );

    textos.setOpaque(false);
    textos.add(lblTitulo);
    textos.add(lblSubtitulo);

    encabezado.add(
        textos,
        java.awt.BorderLayout.WEST
    );

    encabezado.add(
        btnSalir,
        java.awt.BorderLayout.EAST
    );

    pnlMantenimiento.add(
        encabezado,
        java.awt.BorderLayout.NORTH
    );

    // ================= PESTAÑAS =================

    javax.swing.JPanel panelPestanas =
        new javax.swing.JPanel(
            new java.awt.GridLayout(1, 3, 8, 0)
        );

    panelPestanas.setBackground(fondo);
    panelPestanas.setBorder(
        javax.swing.BorderFactory.createEmptyBorder(
            20, 30, 10, 30
        )
    );

    btnCrearPestana = new javax.swing.JButton(
        "Crear / Registrar usuarios"
    );

    btnModificarPestana = new javax.swing.JButton(
        "Modificar usuarios"
    );

    btnEliminarPestana = new javax.swing.JButton(
        "Eliminar usuarios"
    );

    btnCrearPestana.addActionListener(e -> cambiarModo(0));
    btnModificarPestana.addActionListener(e -> cambiarModo(1));
    btnEliminarPestana.addActionListener(e -> cambiarModo(2));

    panelPestanas.add(btnCrearPestana);
    panelPestanas.add(btnModificarPestana);
    panelPestanas.add(btnEliminarPestana);

    // ================= FORMULARIO =================

    panelFormulario = new javax.swing.JPanel(
        new java.awt.BorderLayout(0, 15)
    );

    panelFormulario.setBackground(java.awt.Color.WHITE);
    panelFormulario.setBorder(
        javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(221, 198, 207)
            ),
            javax.swing.BorderFactory.createEmptyBorder(
                25, 30, 25, 30
            )
        )
    );

    jLabel3.setText("Datos del usuario");

    panelFormulario.add(
        jLabel3,
        java.awt.BorderLayout.NORTH
    );

    javax.swing.JPanel campos =
        new javax.swing.JPanel(
            new java.awt.GridBagLayout()
        );

    campos.setBackground(java.awt.Color.WHITE);

    addCampo(campos, lblNombre, txtNombre, 0, 0);
    addCampo(campos, lblApellido, txtApellido, 2, 0);

    addCampo(campos, lblCorreo, txtCorreo, 0, 1);
    addCampo(campos, lblGenero, cmbGenero, 2, 1);

    addCampo(campos, lblUsuario, txtUsuario, 0, 2);
    addCampo(campos, lblTipoUsuario, cmbTipoUsuario, 2, 2);

    addCampo(campos, lblContrasena, txtContrasena, 0, 3);

    panelFormulario.add(
        campos,
        java.awt.BorderLayout.CENTER
    );

    // ================= ACCIONES =================

    panelAcciones = new javax.swing.JPanel(
        new java.awt.FlowLayout(
            java.awt.FlowLayout.CENTER,
            12,
            10
        )
    );

    panelAcciones.setBackground(fondo);

    // ================= CONTENIDO CENTRAL =================

    javax.swing.JPanel centro =
        new javax.swing.JPanel(
            new java.awt.BorderLayout()
        );

    centro.setBackground(fondo);
    centro.setBorder(
        javax.swing.BorderFactory.createEmptyBorder(
            0, 30, 20, 30
        )
    );

    centro.add(
        panelPestanas,
        java.awt.BorderLayout.NORTH
    );

    centro.add(
        panelFormulario,
        java.awt.BorderLayout.CENTER
    );

    centro.add(
        panelAcciones,
        java.awt.BorderLayout.SOUTH
    );

    pnlMantenimiento.add(
        centro,
        java.awt.BorderLayout.CENTER
    );

    cambiarModo(0);

    revalidate();
    repaint();
}

private void addCampo(
        javax.swing.JPanel panel,
        javax.swing.JLabel etiqueta,
        java.awt.Component campo,
        int columna,
        int fila) {

    java.awt.GridBagConstraints gbc =
        new java.awt.GridBagConstraints();

    gbc.gridy = fila;
    gbc.insets = new java.awt.Insets(10, 10, 10, 10);
    gbc.anchor = java.awt.GridBagConstraints.WEST;

    if (columna == 0) {
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;

        panel.add(etiqueta, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;

        campo.setPreferredSize(
            new java.awt.Dimension(250, 36)
        );

        panel.add(campo, gbc);

    } else {
        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;

        panel.add(etiqueta, gbc);

        gbc.gridx = 3;
        gbc.weightx = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;

        campo.setPreferredSize(
            new java.awt.Dimension(250, 36)
        );

        panel.add(campo, gbc);
    }
} // fin metodo


private void cambiarModo(int modo) {

    modoActual = modo;

    java.awt.Color rosado =
        new java.awt.Color(239, 226, 232);

    java.awt.Color blanco =
        new java.awt.Color(255, 255, 255);

    java.awt.Color morado =
        new java.awt.Color(89, 51, 75);

    // Estilo de las tres pestañas

    estilizarPestana(btnCrearPestana, modo == 0);
    estilizarPestana(btnModificarPestana, modo == 1);
    estilizarPestana(btnEliminarPestana, modo == 2);

    // Actualizar el título de la operación

    if (modo == 0) {
        jLabel3.setText("Registrar nuevo usuario");
    } else if (modo == 1) {
        jLabel3.setText("Modificar datos del usuario");
    } else {
        jLabel3.setText("Desactivar usuario");
    }

    // Cambiar botones inferiores

    panelAcciones.removeAll();

    if (modo == 0) {
        panelAcciones.add(btnRegistrar);
    }

    if (modo == 1) {
        panelAcciones.add(btnConsultar);
        panelAcciones.add(btnModificar);
    }

    if (modo == 2) {
        panelAcciones.add(btnConsultar);
        panelAcciones.add(btnEliminar);
    }

    panelAcciones.add(btnLimpiar);
    panelAcciones.add(btnSalir);

    // Campos editables según la operación

    boolean editable = modo != 2;

    txtNombre.setEditable(editable);
    txtApellido.setEditable(editable);
    txtCorreo.setEditable(editable);
    txtContrasena.setEditable(editable);

    cmbGenero.setEnabled(editable);
    cmbTipoUsuario.setEnabled(editable);

    txtUsuario.setEditable(true);

    // En registro, limpiar para comenzar desde cero

    if (modo == 0) {
        limpiarCampos();
    }

    panelAcciones.revalidate();
    panelAcciones.repaint();

    pnlMantenimiento.revalidate();
    pnlMantenimiento.repaint();
}

private void estilizarPestana(
        javax.swing.JButton boton,
        boolean seleccionada) {

    java.awt.Color fondo = seleccionada
        ? new java.awt.Color(239, 226, 232)
        : java.awt.Color.WHITE;

    boton.setBackground(fondo);
    boton.setForeground(
        new java.awt.Color(89, 51, 75)
    );

    boton.setFont(
        new java.awt.Font(
            "SansSerif",
            java.awt.Font.BOLD,
            13
        )
    );

    boton.setOpaque(true);
    boton.setBorderPainted(true);
    boton.setFocusPainted(false);

    boton.setBorder(
        javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(221, 198, 207)
            ),
            javax.swing.BorderFactory.createEmptyBorder(
                12, 10, 12, 10
            )
        )
    );
}
// fin metodo cambiarmodo


    
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

        pnlMantenimiento = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
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
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mantenimiento de usuarios - CONSULT");
        setBackground(new java.awt.Color(245, 241, 234));

        pnlMantenimiento.setBackground(new java.awt.Color(245, 241, 235));

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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addGap(87, 87, 87))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblSubtitulo)
                        .addGap(154, 154, 154))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(2, 2, 2)
                .addComponent(lblSubtitulo)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(89, 51, 75));
        jLabel3.setText("Datos del usuario");

        lblApellido.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblApellido.setForeground(new java.awt.Color(89, 51, 75));
        lblApellido.setText("Apellido");

        lblNombre.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(89, 51, 75));
        lblNombre.setText("Nombre");

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

        javax.swing.GroupLayout pnlMantenimientoLayout = new javax.swing.GroupLayout(pnlMantenimiento);
        pnlMantenimiento.setLayout(pnlMantenimientoLayout);
        pnlMantenimientoLayout.setHorizontalGroup(
            pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                                .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84)
                                .addComponent(cmbTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar)
                .addGap(26, 26, 26))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(294, 294, 294)
                    .addComponent(lblNombre)
                    .addContainerGap(546, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(293, 293, 293)
                    .addComponent(lblApellido)
                    .addContainerGap(544, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(260, 260, 260)
                    .addComponent(lblCorreo)
                    .addContainerGap(512, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(296, 296, 296)
                    .addComponent(lblGenero)
                    .addContainerGap(547, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(259, 259, 259)
                    .addComponent(lblUsuario)
                    .addContainerGap(510, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(283, 283, 283)
                    .addComponent(lblContrasena)
                    .addContainerGap(535, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(218, 218, 218)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(469, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(218, 218, 218)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(469, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(218, 218, 218)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(469, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(270, 270, 270)
                    .addComponent(lblTipoUsuario)
                    .addContainerGap(521, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(271, 271, 271)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(522, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(275, 275, 275)
                    .addComponent(btnEliminar)
                    .addContainerGap(527, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(269, 269, 269)
                    .addComponent(btnConsultar)
                    .addContainerGap(521, Short.MAX_VALUE)))
        );
        pnlMantenimientoLayout.setVerticalGroup(
            pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMantenimientoLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalir)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegistrar)
                            .addComponent(btnModificar))
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMantenimientoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119))))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(lblNombre)
                    .addContainerGap(321, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(lblApellido)
                    .addContainerGap(321, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(lblCorreo)
                    .addContainerGap(321, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(lblGenero)
                    .addContainerGap(321, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(321, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(lblContrasena)
                    .addContainerGap(321, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(311, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(311, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(311, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(lblTipoUsuario)
                    .addContainerGap(321, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(btnLimpiar)
                    .addContainerGap(317, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(btnEliminar)
                    .addContainerGap(317, Short.MAX_VALUE)))
            .addGroup(pnlMantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMantenimientoLayout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(btnConsultar)
                    .addContainerGap(317, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 422, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMantenimiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
         
         String usuario = txtUsuario.getText().trim();

         if (usuario.isEmpty()) {
             JOptionPane.showMessageDialog(this,
                     "Escriba o consulte el usuario que desea desactivar.");
             return;
         }

         int confirmacion = JOptionPane.showConfirmDialog(
                 this,
                 "¿Está seguro de que desea desactivar este usuario?",
                 "Confirmar desactivación",
                 JOptionPane.YES_NO_OPTION
         );

         if (confirmacion == JOptionPane.YES_OPTION) {
             boolean resultado = archivoUsuarios.eliminarUsuario(usuario);

             if (resultado) {
                 JOptionPane.showMessageDialog(this,
                         "Usuario desactivado correctamente.");
                 limpiarCampos();
             } else {
                 JOptionPane.showMessageDialog(this,
                         "No se pudo desactivar el usuario. Verifique que exista.");
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

if (nombre.isEmpty() || apellido.isEmpty() || usuario.isEmpty() ||
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
            txtContrasena.setText("");
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
    private javax.swing.JPanel pnlMantenimiento;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
