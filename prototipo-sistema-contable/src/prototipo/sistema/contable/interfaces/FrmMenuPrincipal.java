/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo.sistema.contable.interfaces;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.awt.Image;
import javax.swing.ImageIcon;
import prototipo.sistema.contable.modelo.Usuario;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.IllegalComponentStateException;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author user
 */
public class FrmMenuPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FrmMenuPrincipal
     */

public FrmMenuPrincipal(Usuario usuario) {
    initComponents();

    estilizarSubmenu(menuMantenimientos);
    estilizarSubmenu(menuMovimiento);
    estilizarSubmenu(menuProcesos);
    estilizarSubmenu(menuConsultas);

    this.usuarioActual = usuario;
    ajustarPanelInicio();
    configurarPantallaInicio();

    lblUsuarioActivo.setText(
            usuarioActual.getNombre()
            + " " + usuarioActual.getApellido()
    );
    
    java.net.URL rutaUsuario = getClass().getResource(
        "/Imagenes/user.png"
);

    if (rutaUsuario != null) {

        javax.swing.ImageIcon iconoUsuario =
                new javax.swing.ImageIcon(rutaUsuario);

        java.awt.Image imagenUsuario =
                iconoUsuario.getImage().getScaledInstance(
                        24,
                        24,
                        java.awt.Image.SCALE_SMOOTH
                );

        lblUsuarioActivo.setIcon(
                new javax.swing.ImageIcon(imagenUsuario)
        );

        lblUsuarioActivo.setIconTextGap(8);
    }
    
   javax.swing.ImageIcon iconoSalida = new javax.swing.ImageIcon(
            getClass().getResource("/Imagenes/salida.png")
    );

    java.awt.Image imagenSalida = iconoSalida.getImage();

    java.awt.Image imagenAjustada = imagenSalida.getScaledInstance(
            20, 20, java.awt.Image.SCALE_SMOOTH
    );

    btnCerrarSesion.setIcon(
            new javax.swing.ImageIcon(imagenAjustada)
    );

    
    // Maximizar la ventana y establecer un tamaño mínimo
    setMinimumSize(new java.awt.Dimension(900, 600));
    setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);


    // Organizar el encabezado y el menú superior
    // Limpiar el contenedor superior
    pnlSuperior.removeAll();

    // Organizar los paneles verticalmente
        pnlSuperior.setLayout(
                new java.awt.BorderLayout(0, 0)
        );

        // Colocar el encabezado arriba
        pnlSuperior.add(
                pnlEncabezado,
                java.awt.BorderLayout.NORTH
        );

        // Colocar el menú debajo del encabezado
        pnlSuperior.add(
                pnlMenuSuperior,
                java.awt.BorderLayout.CENTER
        );
        
         configurarPanelUsuario();
      
    new java.awt.event.ComponentAdapter() {

        @Override
        public void componentResized(
                java.awt.event.ComponentEvent e) {

            for (java.awt.Component componente :
                    pnlEncabezado.getComponents()) {

                if (componente instanceof javax.swing.JPanel) {

                    componente.setBounds(
                        pnlEncabezado.getWidth() - 310,
                        5,
                        300,
                        80
                    );
                }
            }
        }
    };

    // Colocar el encabezado arriba
    pnlSuperior.add(
            pnlEncabezado,
            java.awt.BorderLayout.NORTH
    );

    // Colocar el menú debajo del encabezado
    pnlSuperior.add(
            pnlMenuSuperior,
            java.awt.BorderLayout.CENTER
    );
    
   
    btnCerrarSesion.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    btnCerrarSesion.setVerticalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    // Colocar el texto a la derecha del icono
    btnCerrarSesion.setHorizontalTextPosition(
            javax.swing.SwingConstants.RIGHT
    );

    // Separación entre icono y texto
    btnCerrarSesion.setIconTextGap(8);
    

    btnCerrarSesion.setIcon(
            new ImageIcon(imagenAjustada)
    );
    
    btnCerrarSesion.addActionListener(e -> {

    int opcion = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de que desea cerrar sesión?",
            "Cerrar sesión",
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.QUESTION_MESSAGE
    );

    if (opcion == javax.swing.JOptionPane.YES_OPTION) {

        this.dispose();

        FrmLogin login = new FrmLogin();
        login.setLocationRelativeTo(null);
        login.setVisible(true);

    }

    });

    // Alinear los paneles hacia la izquierda
    pnlEncabezado.setAlignmentX(
            java.awt.Component.LEFT_ALIGNMENT
    );

    pnlMenuSuperior.setAlignmentX(
            java.awt.Component.LEFT_ALIGNMENT
    );

    // Permitir que ambos paneles ocupen todo el ancho
    pnlEncabezado.setMaximumSize(
            new java.awt.Dimension(
                    Integer.MAX_VALUE, 85
            )
    );

    pnlMenuSuperior.setMaximumSize(
            new java.awt.Dimension(
                    Integer.MAX_VALUE, 55
            )
    );

    // Organizar los botones del menú en una sola fila
    pnlMenuSuperior.setLayout(
            new java.awt.GridLayout(1, 5, 0, 0)
    );

    // Organizar la ventana principal
    getContentPane().setLayout(
            new java.awt.BorderLayout(0, 0)
    );

    getContentPane().removeAll();

    // Agregar la parte superior
    getContentPane().add(
            pnlSuperior,
            java.awt.BorderLayout.NORTH
    );

    // Agregar el área de trabajo
    getContentPane().add(
            pnlContenido,
            java.awt.BorderLayout.CENTER
    );
    

    // Actualizar la interfaz
    revalidate();
    repaint();

    
    // MENÚS DESPLEGABLES
   

    
    btnMantenimientos.addActionListener(e -> {
        seleccionarBoton(btnMantenimientos);

        menuMantenimientos.show(
            btnMantenimientos, 0, btnMantenimientos.getHeight()
        );
    });

    btnMovimientos.addActionListener(e -> {
        seleccionarBoton(btnMovimientos);

        menuMovimiento.show(
            btnMovimientos, 0, btnMovimientos.getHeight()
        );
    });

    btnProcesos.addActionListener(e -> {
        seleccionarBoton(btnProcesos);

        menuProcesos.show(
            btnProcesos, 0, btnProcesos.getHeight()
        );
    });

    btnConsultas.addActionListener(e -> {
        seleccionarBoton(btnConsultas);

        menuConsultas.show(
            btnConsultas, 0, btnConsultas.getHeight()
        );
    });
    
    seleccionarBoton(btnInicio);


// ESTILO Y SELECCIÓN DE BOTONES

java.awt.Color moradoMenu =
        new java.awt.Color(90, 48, 73);

java.awt.Color moradoHover =
        new java.awt.Color(115, 65, 95);

java.awt.Color moradoSeleccionado =
        new java.awt.Color(47, 25, 38);

javax.swing.JButton[] botones = {
    btnInicio,
    btnMantenimientos,
    btnMovimientos,
    btnProcesos,
    btnConsultas
};

// Configurar apariencia inicial
for (javax.swing.JButton btn : botones) {

    btn.setUI(
        new javax.swing.plaf.basic.BasicButtonUI()
    );

    btn.setBackground(moradoMenu);
    btn.setForeground(java.awt.Color.WHITE);

    btn.setOpaque(true);
    btn.setContentAreaFilled(true);
    btn.setBorderPainted(false);
    btn.setFocusPainted(false);

    btn.addMouseListener(
        new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(
                    java.awt.event.MouseEvent e) {

                if (btn.getBackground() != moradoSeleccionado) {
                    btn.setBackground(moradoHover);
                }
            }

            @Override
            public void mouseExited(
                    java.awt.event.MouseEvent e) {

                if (btn.getBackground() != moradoSeleccionado) {
                    btn.setBackground(moradoMenu);
                }
            }
        }
    );
    
    
} // fin del constructor

// Método para cambiar el botón seleccionado

for (javax.swing.JButton btn : botones) {

    btn.setUI(new javax.swing.plaf.basic.BasicButtonUI());
    btn.setBackground(colorMenu);
    btn.setForeground(java.awt.Color.WHITE);
    btn.setOpaque(true);
    btn.setContentAreaFilled(true);
    btn.setBorderPainted(false);
    btn.setFocusPainted(false);

    btn.addMouseListener(new java.awt.event.MouseAdapter() {

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {

            if (botonMenuSeleccionado != btn) {
                btn.setBackground(colorHover);
            }
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {

            if (botonMenuSeleccionado == btn) {
                btn.setBackground(colorSeleccionado);
            } else {
                btn.setBackground(colorMenu);
            }
        }
    });
}

btnInicio.addActionListener(e -> mostrarInicio());


btnMantenimientos.addActionListener(e ->
    seleccionarBotonMenu(btnMantenimientos));

btnMovimientos.addActionListener(e ->
    seleccionarBotonMenu(btnMovimientos));

btnProcesos.addActionListener(e ->
    seleccionarBotonMenu(btnProcesos));

btnConsultas.addActionListener(e ->
    seleccionarBotonMenu(btnConsultas));

seleccionarBotonMenu(btnInicio);
    //permisos del menu
    
    this.usuarioActual = usuario;
    
        // Controlar las opciones según el tipo de usuario
    if (usuarioActual.getTipoUsuario().equalsIgnoreCase("Administrador")) {
        itemUsuario.setVisible(true);
    } else {
        itemUsuario.setVisible(false);
    }
    
    java.net.URL rutaControl = getClass().getResource(
            "/Imagenes/control.png"
    );

    if (rutaControl != null) {

        javax.swing.ImageIcon iconoControl =
                new javax.swing.ImageIcon(rutaControl);

        java.awt.Image imagenControl =
                iconoControl.getImage().getScaledInstance(
                        45,
                        45,
                        java.awt.Image.SCALE_SMOOTH
                );

        lblIconoControl.setIcon(
                new javax.swing.ImageIcon(imagenControl)
        );
    }
    
    java.net.URL rutaSeguridad = getClass().getResource(
            "/Imagenes/seguridad.png"
    );

    if (rutaSeguridad != null) {

        javax.swing.ImageIcon iconoSeguridad =
                new javax.swing.ImageIcon(rutaSeguridad);

        java.awt.Image imagenSeguridad =
                iconoSeguridad.getImage().getScaledInstance(
                        45,
                        45,
                        java.awt.Image.SCALE_SMOOTH
                );

        lblIconoSeguridad.setIcon(
                new javax.swing.ImageIcon(imagenSeguridad)
        );
    }
    
    java.net.URL rutaOrganizacion = getClass().getResource(
            "/Imagenes/organizacion.png"
    );

    if (rutaOrganizacion != null) {

        javax.swing.ImageIcon iconoOrganizacion =
                new javax.swing.ImageIcon(rutaOrganizacion);

        java.awt.Image imagenOrganizacion =
                iconoOrganizacion.getImage().getScaledInstance(
                        45,
                        45,
                        java.awt.Image.SCALE_SMOOTH
                );

        lblIconoOrganizacion.setIcon(
                new javax.swing.ImageIcon(imagenOrganizacion)
        );
    }
    
} //fin constructor



private void configurarPantallaInicio() {

    // PANEL PRINCIPAL DE BIENVENIDA
    

    pnlContenido.setLayout(new java.awt.BorderLayout());
    pnlInicio.setLayout(new java.awt.GridBagLayout());

    pnlInicio.setBackground(
            new java.awt.Color(245, 241, 235)
    );

    // Panel que contendrá todo el contenido
    javax.swing.JPanel contenido =
            new javax.swing.JPanel();

    contenido.setLayout(
            new javax.swing.BoxLayout(
                    contenido,
                    javax.swing.BoxLayout.Y_AXIS
            )
    );

    contenido.setBackground(
            new java.awt.Color(245, 241, 235)
    );

    contenido.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(
                    35, 45, 35, 45
            )
    );

    
    // TITULOS Y DESCRIPCION
    

    lblTituloBienvenida.setAlignmentX(
            java.awt.Component.CENTER_ALIGNMENT
    );

    lblTituloSistema.setAlignmentX(
            java.awt.Component.CENTER_ALIGNMENT
    );

    lblDescripcion.setAlignmentX(
            java.awt.Component.CENTER_ALIGNMENT
    );

    lblTituloBienvenida.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    lblTituloSistema.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    lblDescripcion.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    lblTituloBienvenida.setFont(
            new java.awt.Font("SansSerif", 0, 27)
    );

    lblTituloSistema.setFont(
            new java.awt.Font("SansSerif", 1, 53)
    );

    lblDescripcion.setFont(
            new java.awt.Font("SansSerif", 0, 22)
    );

    lblTituloSistema.setForeground(
            new java.awt.Color(90, 48, 73)
    );

    lblTituloBienvenida.setForeground(
            new java.awt.Color(90, 48, 73)
    );

    lblDescripcion.setForeground(
            new java.awt.Color(99, 99, 99)
    );

    
    // SEPARADOR
    

    jSeparator1.setForeground(
            new java.awt.Color(221, 198, 207)
    );

    jSeparator1.setMaximumSize(
            new java.awt.Dimension(
                    Integer.MAX_VALUE, 2
            )
    );

   
    // PANEL DE CARACTERISTICAS
   

    pnlCaracteristicas.setLayout(
            new java.awt.GridLayout(1, 3, 25, 0)
    );

    pnlCaracteristicas.setBackground(
            new java.awt.Color(245, 241, 235)
    );

    pnlCaracteristicas.setMaximumSize(
            new java.awt.Dimension(
                    Integer.MAX_VALUE, 220
            )
    );

    pnlCaracteristicas.setPreferredSize(
            new java.awt.Dimension(1000, 220)
    );

    
    // ESTILO DE LAS TARJETAS
    

    java.awt.Color fondoTarjeta =
            new java.awt.Color(255, 255, 255);

    java.awt.Color bordeTarjeta =
            new java.awt.Color(221, 198, 207);

    javax.swing.JPanel[] tarjetas = {
        pnlControlTotal,
        pnlSeguridad,
        pnlOrganizacion
    };

    javax.swing.JLabel[] iconos = {
        lblIconoControl,
        lblIconoSeguridad,
        lblIconoOrganizacion
    };

    javax.swing.JLabel[] titulos = {
        lblTituloControl,
        lblTituloSeguridad,
        lblTituloOrganizacion
    };

    javax.swing.JLabel[] descripciones = {
        lblDescripcionControl,
        lblDescripcionSeguridad,
        lblDescripcionOrganizacion
    };

    for (int i = 0; i < tarjetas.length; i++) {

        tarjetas[i].removeAll();

        tarjetas[i].setLayout(
                new javax.swing.BoxLayout(
                        tarjetas[i],
                        javax.swing.BoxLayout.Y_AXIS
                )
        );

        tarjetas[i].setBackground(fondoTarjeta);

        tarjetas[i].setBorder(
                javax.swing.BorderFactory.createCompoundBorder(
                        javax.swing.BorderFactory.createLineBorder(
                                bordeTarjeta, 1
                        ),
                        javax.swing.BorderFactory.createEmptyBorder(
                                20, 15, 20, 15
                        )
                )
        );

        tarjetas[i].setPreferredSize(
                new java.awt.Dimension(240, 220)
        );

        tarjetas[i].setMinimumSize(
                new java.awt.Dimension(150, 180)
        );

        // Iconos centrados
        iconos[i].setAlignmentX(
                java.awt.Component.CENTER_ALIGNMENT
        );

        iconos[i].setHorizontalAlignment(
                javax.swing.SwingConstants.CENTER
        );

        // Titulos centrados
        titulos[i].setAlignmentX(
                java.awt.Component.CENTER_ALIGNMENT
        );

        titulos[i].setHorizontalAlignment(
                javax.swing.SwingConstants.CENTER
        );

        titulos[i].setFont(
                new java.awt.Font("SansSerif", 1, 22)
        );

        titulos[i].setForeground(
                new java.awt.Color(56, 36, 50)
        );

        // Descripciones centradas
        descripciones[i].setAlignmentX(
                java.awt.Component.CENTER_ALIGNMENT
        );

        descripciones[i].setHorizontalAlignment(
                javax.swing.SwingConstants.CENTER
        );

        descripciones[i].setFont(
                new java.awt.Font("SansSerif", 0, 19)
        );

        descripciones[i].setForeground(
                new java.awt.Color(99, 99, 99)
        );

        // Organizar elementos verticalmente
        tarjetas[i].add(iconos[i]);

        tarjetas[i].add(
                javax.swing.Box.createVerticalStrut(15)
        );

        tarjetas[i].add(titulos[i]);

        tarjetas[i].add(
                javax.swing.Box.createVerticalStrut(10)
        );

        tarjetas[i].add(descripciones[i]);
    }

    
    // ORGANIZAR CONTENIDO
   

    contenido.add(
            javax.swing.Box.createVerticalStrut(15)
    );

    contenido.add(lblTituloBienvenida);

    contenido.add(
            javax.swing.Box.createVerticalStrut(8)
    );

    contenido.add(lblTituloSistema);

    contenido.add(
            javax.swing.Box.createVerticalStrut(15)
    );

    contenido.add(lblDescripcion);

    contenido.add(
            javax.swing.Box.createVerticalStrut(30)
    );

    contenido.add(jSeparator1);

    contenido.add(
            javax.swing.Box.createVerticalStrut(30)
    );

    contenido.add(pnlCaracteristicas);

    contenido.add(
            javax.swing.Box.createVerticalStrut(15)
    );

    // Centrar el contenido dentro de la pantalla
    java.awt.GridBagConstraints gbc =
            new java.awt.GridBagConstraints();

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gbc.anchor = java.awt.GridBagConstraints.NORTH;
    gbc.insets = new java.awt.Insets(0, 0, 0, 0);

    pnlInicio.removeAll();

    pnlInicio.add(contenido, gbc);

    pnlContenido.removeAll();

    pnlContenido.add(
            pnlInicio,
            java.awt.BorderLayout.CENTER
    );

    pnlContenido.revalidate();
    pnlContenido.repaint();

    pnlInicio.revalidate();
    pnlInicio.repaint();
    
   
}




private Usuario usuarioActual;
private javax.swing.JButton botonMenuSeleccionado;

private final java.awt.Color colorMenu =
        new java.awt.Color(90, 48, 73);

private final java.awt.Color colorHover =
        new java.awt.Color(115, 65, 95);

private final java.awt.Color colorSeleccionado =
        new java.awt.Color(47, 25, 38);

private void seleccionarBotonMenu(javax.swing.JButton boton) {

    if (botonMenuSeleccionado != null) {
        botonMenuSeleccionado.setBackground(colorMenu);
    }

    botonMenuSeleccionado = boton;
    botonMenuSeleccionado.setBackground(colorSeleccionado);
}

private void ajustarPanelInicio() {

    // Diseño adaptable del panel de bienvenida
    pnlInicio.removeAll();
    pnlInicio.setLayout(new java.awt.GridBagLayout());
    pnlInicio.setBorder(
        javax.swing.BorderFactory.createEmptyBorder(30, 45, 30, 45)
    );

    // Las tres tarjetas tendrán el mismo tamaño y crecerán juntas
    pnlCaracteristicas.setLayout(
        new java.awt.GridLayout(1, 3, 25, 0)
    );

    java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();

    gbc.gridx = 0;
    gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gbc.anchor = java.awt.GridBagConstraints.CENTER;
    gbc.weightx = 1.0;

    // Primer texto: Bienvenido/a al
    gbc.gridy = 0;
    gbc.weighty = 0.05;
    gbc.insets = new java.awt.Insets(0, 0, 2, 0);
    pnlInicio.add(lblTituloBienvenida, gbc);

    // Título principal: Sistema Contable
    gbc.gridy = 1;
    gbc.weighty = 0.15;
    gbc.insets = new java.awt.Insets(0, 0, 5, 0);
    pnlInicio.add(lblTituloSistema, gbc);

    // Descripción
    gbc.gridy = 2;
    gbc.weighty = 0.10;
    gbc.insets = new java.awt.Insets(0, 0, 10, 0);
    pnlInicio.add(lblDescripcion, gbc);

    // Separador: ocupa todo el ancho disponible
    gbc.gridy = 3;
    gbc.weighty = 0.02;
    gbc.insets = new java.awt.Insets(5, 0, 25, 0);
    pnlInicio.add(jSeparator1, gbc);

    // Tarjetas: ocupan el espacio restante
    gbc.gridy = 4;
    gbc.fill = java.awt.GridBagConstraints.BOTH;
    gbc.weighty = 1.0;
    gbc.insets = new java.awt.Insets(5, 0, 5, 0);
    pnlInicio.add(pnlCaracteristicas, gbc);

    // Alinear los textos al centro
    lblTituloBienvenida.setHorizontalAlignment(
        javax.swing.SwingConstants.CENTER
    );

    lblTituloSistema.setHorizontalAlignment(
        javax.swing.SwingConstants.CENTER
    );

    lblDescripcion.setHorizontalAlignment(
        javax.swing.SwingConstants.CENTER
    );

    // Ajustar los tamaños de letra al cambiar el tamaño de la ventana
    pnlInicio.addComponentListener(new java.awt.event.ComponentAdapter() {
        @Override
        public void componentResized(java.awt.event.ComponentEvent e) {
            ajustarTextoBienvenida();
        }
    });

    ajustarTextoBienvenida();

    pnlInicio.revalidate();
    pnlInicio.repaint();
}


private void ajustarTextoBienvenida() {

    int ancho = pnlInicio.getWidth();

    if (ancho <= 0) {
        return;
    }

    // Escala según el ancho disponible
    double escala = ancho / 1100.0;

    // Evitar que las letras sean demasiado pequeñas o grandes
    escala = Math.max(0.80, Math.min(escala, 1.25));

    lblTituloBienvenida.setFont(
        new java.awt.Font("SansSerif", java.awt.Font.PLAIN,
            (int) (24 * escala))
    );

    lblTituloSistema.setFont(
        new java.awt.Font("SansSerif", java.awt.Font.BOLD,
            (int) (60 * escala))
    );

    lblDescripcion.setFont(
        new java.awt.Font("SansSerif", java.awt.Font.PLAIN,
            (int) (18 * escala))
    );
}

private void mostrarEnContenido(javax.swing.JComponent panel) {
    pnlContenido.removeAll();
    pnlContenido.setLayout(new java.awt.BorderLayout());
    pnlContenido.add(panel, java.awt.BorderLayout.CENTER);
    pnlContenido.revalidate();
    pnlContenido.repaint();
}

private void mostrarInicio() {
    mostrarEnContenido(pnlInicio);
    seleccionarBotonMenu(btnInicio);
    seleccionarBoton(btnInicio);
}

private void seleccionarBoton(javax.swing.JButton seleccionado) {

    java.awt.Color colorNormal =
            new java.awt.Color(90, 48, 73);

    java.awt.Color colorSeleccionado =
            new java.awt.Color(47, 25, 38);

    javax.swing.JButton[] botones = {
        btnInicio,
        btnMantenimientos,
        btnMovimientos,
        btnProcesos,
        btnConsultas
    };

    for (javax.swing.JButton btn : botones) {

        if (btn == seleccionado) {
            btn.setBackground(colorSeleccionado);
        } else {
            btn.setBackground(colorNormal);
        }
    }
}


private void estilizarSubmenu(javax.swing.JPopupMenu menu) {

    menu.setBackground(new java.awt.Color(255, 255, 255));

    menu.setBorder(
        javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(255, 255, 255)
            ),
            javax.swing.BorderFactory.createEmptyBorder(5, 0, 5, 0)
        )
    );

    for (java.awt.Component componente : menu.getComponents()) {

        if (componente instanceof javax.swing.JMenuItem) {

            javax.swing.JMenuItem item =
                (javax.swing.JMenuItem) componente;

            item.setOpaque(true);
            item.setBackground(new java.awt.Color(255, 255, 255));
            item.setForeground(new java.awt.Color(56, 36, 50));

            item.setFont(new java.awt.Font(
                "Segoe UI",
                java.awt.Font.PLAIN,
                13
            ));

            item.setBorder(
                javax.swing.BorderFactory.createEmptyBorder(
                    9, 15, 9, 15
                )
            );

            item.setFocusPainted(false);

            item.addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    item.setBackground(new java.awt.Color(239, 226, 232));
                    item.setForeground(new java.awt.Color(47, 25, 38));
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    item.setBackground(new java.awt.Color(255, 255, 255));
                    item.setForeground(new java.awt.Color(56, 36, 50));
                }
            });
        }
    }
}

private javax.swing.JPanel pnlUsuario;


private void configurarPanelUsuario() {

    pnlUsuario = new javax.swing.JPanel(
        new java.awt.FlowLayout(
            java.awt.FlowLayout.RIGHT,
            15,
            10
        )
    );

    pnlUsuario.setOpaque(false);

    // Quitar los componentes de sus posiciones anteriores
    pnlEncabezado.remove(lblUsuarioActivo);
    pnlEncabezado.remove(btnCerrarSesion);

    // Alinear el nombre y el botón
    lblUsuarioActivo.setHorizontalAlignment(
        javax.swing.SwingConstants.RIGHT
    );

    btnCerrarSesion.setHorizontalTextPosition(
        javax.swing.SwingConstants.RIGHT
    );

    btnCerrarSesion.setIconTextGap(8);

    pnlUsuario.add(lblUsuarioActivo);
    pnlUsuario.add(btnCerrarSesion);

    // Agregar el panel al encabezado
    pnlEncabezado.add(
        pnlUsuario,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(
            0, 0, 1, 90
        )
    );

    // Posicionar inicialmente y cada vez que cambie el tamaño
    posicionarPanelUsuario();

    pnlEncabezado.addComponentListener(
        new java.awt.event.ComponentAdapter() {

            @Override
            public void componentResized(
                    java.awt.event.ComponentEvent e) {

                posicionarPanelUsuario();
            }
        }
    );
}

private void posicionarPanelUsuario() {

    int ancho = pnlEncabezado.getWidth();

    if (ancho <= 0 || pnlUsuario == null) {
        return;
    }

    int anchoUsuario = pnlUsuario.getPreferredSize().width;
    int altoUsuario = 90;

    int x = ancho - anchoUsuario - 20;

    // Evitar que se superponga con el logo y el nombre del sistema
    x = Math.max(290, x);

    org.netbeans.lib.awtextra.AbsoluteLayout layout =
        (org.netbeans.lib.awtextra.AbsoluteLayout) pnlEncabezado.getLayout();

    pnlEncabezado.remove(pnlUsuario);

    pnlEncabezado.add(
        pnlUsuario,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(
            x, 0, anchoUsuario, altoUsuario
        )
    );

    pnlEncabezado.revalidate();
    pnlEncabezado.repaint();
}




    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuMantenimientos = new javax.swing.JPopupMenu();
        itemUsuario = new javax.swing.JMenuItem();
        itemCatalogoMantenimiento = new javax.swing.JMenuItem();
        menuMovimiento = new javax.swing.JPopupMenu();
        itemTransacciones = new javax.swing.JMenuItem();
        menuProcesos = new javax.swing.JPopupMenu();
        itemCierreDiario = new javax.swing.JMenuItem();
        menuConsultas = new javax.swing.JPopupMenu();
        itemCatalogoConsulta = new javax.swing.JMenuItem();
        itemTransaccionesFecha = new javax.swing.JMenuItem();
        itemTransaccionesRango = new javax.swing.JMenuItem();
        itemTransaccionesDocumento = new javax.swing.JMenuItem();
        itemTransaccionesTipoDocumento = new javax.swing.JMenuItem();
        itemBalanzaGeneral = new javax.swing.JMenuItem();
        itemBalanzaComprobacion = new javax.swing.JMenuItem();
        itemResumenGastos = new javax.swing.JMenuItem();
        itemEstadoGanancias = new javax.swing.JMenuItem();
        pnlSuperior = new javax.swing.JPanel();
        pnlEncabezado = new javax.swing.JPanel();
        lblNombreSistema = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblUsuarioActivo = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        pnlMenuSuperior = new javax.swing.JPanel();
        btnInicio = new javax.swing.JButton();
        btnMantenimientos = new javax.swing.JButton();
        btnMovimientos = new javax.swing.JButton();
        btnProcesos = new javax.swing.JButton();
        btnConsultas = new javax.swing.JButton();
        pnlContenido = new javax.swing.JPanel();
        pnlInicio = new javax.swing.JPanel();
        lblTituloSistema = new javax.swing.JLabel();
        lblTituloBienvenida = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlCaracteristicas = new javax.swing.JPanel();
        pnlControlTotal = new javax.swing.JPanel();
        lblIconoControl = new javax.swing.JLabel();
        lblTituloControl = new javax.swing.JLabel();
        lblDescripcionControl = new javax.swing.JLabel();
        pnlSeguridad = new javax.swing.JPanel();
        lblIconoSeguridad = new javax.swing.JLabel();
        lblTituloSeguridad = new javax.swing.JLabel();
        lblDescripcionSeguridad = new javax.swing.JLabel();
        pnlOrganizacion = new javax.swing.JPanel();
        lblIconoOrganizacion = new javax.swing.JLabel();
        lblTituloOrganizacion = new javax.swing.JLabel();
        lblDescripcionOrganizacion = new javax.swing.JLabel();

        itemUsuario.setBackground(new java.awt.Color(245, 241, 235));
        itemUsuario.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        itemUsuario.setForeground(new java.awt.Color(56, 48, 56));
        itemUsuario.setText("De Usuarios");
        itemUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemUsuarioActionPerformed(evt);
            }
        });
        menuMantenimientos.add(itemUsuario);

        itemCatalogoMantenimiento.setBackground(new java.awt.Color(245, 241, 235));
        itemCatalogoMantenimiento.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        itemCatalogoMantenimiento.setForeground(new java.awt.Color(56, 48, 56));
        itemCatalogoMantenimiento.setText("De Catálogo de cuenta");
        menuMantenimientos.add(itemCatalogoMantenimiento);

        itemTransacciones.setBackground(new java.awt.Color(245, 241, 235));
        itemTransacciones.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        itemTransacciones.setForeground(new java.awt.Color(56, 48, 56));
        itemTransacciones.setText("De Transacciones");
        menuMovimiento.add(itemTransacciones);

        itemCierreDiario.setBackground(new java.awt.Color(245, 241, 235));
        itemCierreDiario.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        itemCierreDiario.setForeground(new java.awt.Color(56, 48, 56));
        itemCierreDiario.setText("Cierre de Diario por fechas");
        itemCierreDiario.setToolTipText("");
        itemCierreDiario.setActionCommand("\n");
        menuProcesos.add(itemCierreDiario);

        itemCatalogoConsulta.setBackground(new java.awt.Color(245, 241, 235));
        itemCatalogoConsulta.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        itemCatalogoConsulta.setForeground(new java.awt.Color(56, 48, 56));
        itemCatalogoConsulta.setText("De Catálogo de cuenta");
        menuConsultas.add(itemCatalogoConsulta);

        itemTransaccionesFecha.setBackground(new java.awt.Color(245, 241, 235));
        itemTransaccionesFecha.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        itemTransaccionesFecha.setForeground(new java.awt.Color(56, 48, 56));
        itemTransaccionesFecha.setText("De Transacciones por Fecha");
        menuConsultas.add(itemTransaccionesFecha);

        itemTransaccionesRango.setBackground(new java.awt.Color(245, 241, 235));
        itemTransaccionesRango.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        itemTransaccionesRango.setForeground(new java.awt.Color(56, 48, 56));
        itemTransaccionesRango.setText("De Transacciones por rango de fechas");
        menuConsultas.add(itemTransaccionesRango);

        itemTransaccionesDocumento.setBackground(new java.awt.Color(245, 241, 235));
        itemTransaccionesDocumento.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        itemTransaccionesDocumento.setForeground(new java.awt.Color(56, 48, 56));
        itemTransaccionesDocumento.setText("De Transacciones por Documento");
        menuConsultas.add(itemTransaccionesDocumento);

        itemTransaccionesTipoDocumento.setBackground(new java.awt.Color(245, 241, 235));
        itemTransaccionesTipoDocumento.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        itemTransaccionesTipoDocumento.setForeground(new java.awt.Color(56, 48, 56));
        itemTransaccionesTipoDocumento.setText("De Transacciones por Tipo de Documento");
        menuConsultas.add(itemTransaccionesTipoDocumento);

        itemBalanzaGeneral.setBackground(new java.awt.Color(245, 241, 235));
        itemBalanzaGeneral.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        itemBalanzaGeneral.setForeground(new java.awt.Color(56, 48, 56));
        itemBalanzaGeneral.setText("Balanza General");
        menuConsultas.add(itemBalanzaGeneral);

        itemBalanzaComprobacion.setBackground(new java.awt.Color(245, 241, 235));
        itemBalanzaComprobacion.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        itemBalanzaComprobacion.setForeground(new java.awt.Color(56, 48, 56));
        itemBalanzaComprobacion.setText("Balanza de Comprobación");
        menuConsultas.add(itemBalanzaComprobacion);

        itemResumenGastos.setBackground(new java.awt.Color(245, 241, 235));
        itemResumenGastos.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        itemResumenGastos.setForeground(new java.awt.Color(56, 48, 56));
        itemResumenGastos.setText("Resumen de Gastos Generales");
        menuConsultas.add(itemResumenGastos);

        itemEstadoGanancias.setBackground(new java.awt.Color(245, 241, 235));
        itemEstadoGanancias.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        itemEstadoGanancias.setForeground(new java.awt.Color(56, 48, 56));
        itemEstadoGanancias.setText("Estado de Ganancias y Pérdidas");
        menuConsultas.add(itemEstadoGanancias);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Contable");
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1200, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlEncabezado.setBackground(new java.awt.Color(56, 36, 50));
        pnlEncabezado.setAlignmentX(0.0F);
        pnlEncabezado.setAlignmentY(0.0F);
        pnlEncabezado.setPreferredSize(new java.awt.Dimension(1200, 90));
        pnlEncabezado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombreSistema.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lblNombreSistema.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreSistema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreSistema.setText("CONSULT");
        pnlEncabezado.add(lblNombreSistema, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 128, -1));

        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(221, 198, 207));
        lblSubtitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtitulo.setText("SISTEMA CONTABLE");
        pnlEncabezado.add(lblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 128, -1));

        lblLogo.setFont(new java.awt.Font("Serif", 1, 54)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(184, 141, 157));
        lblLogo.setText("C");
        lblLogo.setAutoscrolls(true);
        lblLogo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlEncabezado.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, -1, 90));

        lblUsuarioActivo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblUsuarioActivo.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuarioActivo.setText("nombre");
        pnlEncabezado.add(lblUsuarioActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 19, -1, -1));

        btnCerrarSesion.setBackground(new java.awt.Color(90, 48, 73));
        btnCerrarSesion.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setBorderPainted(false);
        btnCerrarSesion.setContentAreaFilled(false);
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setFocusable(false);
        btnCerrarSesion.setRequestFocusEnabled(false);
        btnCerrarSesion.setRolloverEnabled(false);
        pnlEncabezado.add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(926, 46, -1, -1));

        pnlMenuSuperior.setBackground(new java.awt.Color(90, 48, 73));
        pnlMenuSuperior.setAlignmentY(85.0F);
        pnlMenuSuperior.setPreferredSize(new java.awt.Dimension(100, 50));
        pnlMenuSuperior.setLayout(new java.awt.GridLayout(1, 5));

        btnInicio.setBackground(new java.awt.Color(47, 25, 38));
        btnInicio.setForeground(new java.awt.Color(255, 255, 255));
        btnInicio.setText("Inicio");
        btnInicio.setBorderPainted(false);
        btnInicio.setFocusable(false);
        btnInicio.setRequestFocusEnabled(false);
        btnInicio.setRolloverEnabled(false);
        pnlMenuSuperior.add(btnInicio);

        btnMantenimientos.setBackground(new java.awt.Color(90, 48, 73));
        btnMantenimientos.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnMantenimientos.setForeground(new java.awt.Color(255, 255, 255));
        btnMantenimientos.setText("Mantenimientos");
        btnMantenimientos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(90, 48, 73), 1, true));
        btnMantenimientos.setBorderPainted(false);
        btnMantenimientos.setFocusPainted(false);
        btnMantenimientos.setFocusable(false);
        btnMantenimientos.setRequestFocusEnabled(false);
        btnMantenimientos.setRolloverEnabled(false);
        pnlMenuSuperior.add(btnMantenimientos);

        btnMovimientos.setBackground(new java.awt.Color(90, 48, 73));
        btnMovimientos.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnMovimientos.setForeground(new java.awt.Color(255, 255, 255));
        btnMovimientos.setText("Movimientos");
        btnMovimientos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(90, 48, 73), 1, true));
        btnMovimientos.setBorderPainted(false);
        btnMovimientos.setFocusPainted(false);
        pnlMenuSuperior.add(btnMovimientos);

        btnProcesos.setBackground(new java.awt.Color(90, 48, 73));
        btnProcesos.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnProcesos.setForeground(new java.awt.Color(255, 255, 255));
        btnProcesos.setText("Procesos");
        btnProcesos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(90, 48, 73), 1, true));
        btnProcesos.setBorderPainted(false);
        btnProcesos.setFocusPainted(false);
        btnProcesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesosActionPerformed(evt);
            }
        });
        pnlMenuSuperior.add(btnProcesos);

        btnConsultas.setBackground(new java.awt.Color(90, 48, 73));
        btnConsultas.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnConsultas.setForeground(new java.awt.Color(255, 255, 255));
        btnConsultas.setText("Consultas");
        btnConsultas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(90, 48, 73), 1, true));
        btnConsultas.setBorderPainted(false);
        btnConsultas.setFocusPainted(false);
        pnlMenuSuperior.add(btnConsultas);

        javax.swing.GroupLayout pnlSuperiorLayout = new javax.swing.GroupLayout(pnlSuperior);
        pnlSuperior.setLayout(pnlSuperiorLayout);
        pnlSuperiorLayout.setHorizontalGroup(
            pnlSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuperiorLayout.createSequentialGroup()
                .addComponent(pnlMenuSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 969, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 25, Short.MAX_VALUE))
            .addGroup(pnlSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE))
        );
        pnlSuperiorLayout.setVerticalGroup(
            pnlSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSuperiorLayout.createSequentialGroup()
                .addContainerGap(121, Short.MAX_VALUE)
                .addComponent(pnlMenuSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(pnlSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlSuperiorLayout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(pnlEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(200, Short.MAX_VALUE)))
        );

        getContentPane().add(pnlSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlContenido.setBackground(new java.awt.Color(245, 241, 235));
        pnlContenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pnlContenido.setPreferredSize(new java.awt.Dimension(972, 300));

        pnlInicio.setBackground(new java.awt.Color(245, 241, 235));
        pnlInicio.setMaximumSize(new java.awt.Dimension(32767, 1000));
        pnlInicio.setPreferredSize(new java.awt.Dimension(1000, 600));

        lblTituloSistema.setBackground(new java.awt.Color(90, 48, 73));
        lblTituloSistema.setFont(new java.awt.Font("SansSerif", 1, 60)); // NOI18N
        lblTituloSistema.setForeground(new java.awt.Color(90, 48, 73));
        lblTituloSistema.setText("Sistema Contable");

        lblTituloBienvenida.setBackground(new java.awt.Color(56, 36, 50));
        lblTituloBienvenida.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTituloBienvenida.setForeground(new java.awt.Color(90, 48, 73));
        lblTituloBienvenida.setText(" Bienvenido/a al");

        lblDescripcion.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(99, 99, 99));
        lblDescripcion.setText("Gestiona tus operaciones financieras \nde manera segura, eficiente y organizada.\n");

        jSeparator1.setBackground(new java.awt.Color(90, 48, 73));
        jSeparator1.setForeground(new java.awt.Color(90, 48, 73));

        pnlCaracteristicas.setBackground(new java.awt.Color(245, 241, 235));
        pnlCaracteristicas.setLayout(new java.awt.GridLayout(1, 3, 20, 0));

        pnlControlTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 198, 207)));

        lblIconoControl.setForeground(new java.awt.Color(90, 48, 73));

        lblTituloControl.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblTituloControl.setForeground(new java.awt.Color(56, 36, 50));
        lblTituloControl.setText("Control total");

        lblDescripcionControl.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblDescripcionControl.setText("de tus movimientos");

        javax.swing.GroupLayout pnlControlTotalLayout = new javax.swing.GroupLayout(pnlControlTotal);
        pnlControlTotal.setLayout(pnlControlTotalLayout);
        pnlControlTotalLayout.setHorizontalGroup(
            pnlControlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIconoControl)
                .addGap(34, 34, 34)
                .addGroup(pnlControlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescripcionControl)
                    .addComponent(lblTituloControl))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlControlTotalLayout.setVerticalGroup(
            pnlControlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlTotalLayout.createSequentialGroup()
                .addGroup(pnlControlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlControlTotalLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lblIconoControl))
                    .addGroup(pnlControlTotalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTituloControl)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescripcionControl)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        pnlCaracteristicas.add(pnlControlTotal);

        pnlSeguridad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 198, 207)));

        lblIconoSeguridad.setForeground(new java.awt.Color(90, 48, 73));

        lblTituloSeguridad.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblTituloSeguridad.setForeground(new java.awt.Color(56, 36, 50));
        lblTituloSeguridad.setText("Información Seguridad");

        lblDescripcionSeguridad.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblDescripcionSeguridad.setText("y confiable");

        javax.swing.GroupLayout pnlSeguridadLayout = new javax.swing.GroupLayout(pnlSeguridad);
        pnlSeguridad.setLayout(pnlSeguridadLayout);
        pnlSeguridadLayout.setHorizontalGroup(
            pnlSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSeguridadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIconoSeguridad)
                .addGap(34, 34, 34)
                .addGroup(pnlSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescripcionSeguridad)
                    .addComponent(lblTituloSeguridad))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSeguridadLayout.setVerticalGroup(
            pnlSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSeguridadLayout.createSequentialGroup()
                .addGroup(pnlSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSeguridadLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lblIconoSeguridad))
                    .addGroup(pnlSeguridadLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTituloSeguridad)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescripcionSeguridad)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCaracteristicas.add(pnlSeguridad);

        pnlOrganizacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 198, 207)));

        lblIconoOrganizacion.setForeground(new java.awt.Color(90, 48, 73));

        lblTituloOrganizacion.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblTituloOrganizacion.setForeground(new java.awt.Color(56, 36, 50));
        lblTituloOrganizacion.setText("Mayor Organizacion");

        lblDescripcionOrganizacion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblDescripcionOrganizacion.setText("en tu negocio");

        javax.swing.GroupLayout pnlOrganizacionLayout = new javax.swing.GroupLayout(pnlOrganizacion);
        pnlOrganizacion.setLayout(pnlOrganizacionLayout);
        pnlOrganizacionLayout.setHorizontalGroup(
            pnlOrganizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOrganizacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIconoOrganizacion)
                .addGap(34, 34, 34)
                .addGroup(pnlOrganizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescripcionOrganizacion)
                    .addComponent(lblTituloOrganizacion))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        pnlOrganizacionLayout.setVerticalGroup(
            pnlOrganizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOrganizacionLayout.createSequentialGroup()
                .addGroup(pnlOrganizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOrganizacionLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lblIconoOrganizacion))
                    .addGroup(pnlOrganizacionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTituloOrganizacion)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescripcionOrganizacion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCaracteristicas.add(pnlOrganizacion);

        javax.swing.GroupLayout pnlInicioLayout = new javax.swing.GroupLayout(pnlInicio);
        pnlInicio.setLayout(pnlInicioLayout);
        pnlInicioLayout.setHorizontalGroup(
            pnlInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInicioLayout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(pnlInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInicioLayout.createSequentialGroup()
                        .addComponent(lblTituloBienvenida)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlInicioLayout.createSequentialGroup()
                        .addGroup(pnlInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 911, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTituloSistema)
                            .addComponent(lblDescripcion)
                            .addComponent(pnlCaracteristicas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlInicioLayout.setVerticalGroup(
            pnlInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInicioLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lblTituloBienvenida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTituloSistema)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(pnlCaracteristicas, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addComponent(pnlInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 1210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addComponent(pnlInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(pnlContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, -1, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProcesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProcesosActionPerformed

    private void itemUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemUsuarioActionPerformed
         if (!usuarioActual.getTipoUsuario().equalsIgnoreCase("Administrador")) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "No tienes permisos para acceder a esta opción.",
                    "Acceso denegado",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        FrmMantenimientoUsuariosPanel panelUsuarios = new FrmMantenimientoUsuariosPanel();
        panelUsuarios.setAccionSalir(this::mostrarInicio);

        mostrarEnContenido(panelUsuarios);

        seleccionarBotonMenu(btnMantenimientos);
        seleccionarBoton(btnMantenimientos);
    }//GEN-LAST:event_itemUsuarioActionPerformed
// fin itemUsuarioActionPerformed
   

    
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
            java.util.logging.Logger.getLogger(FrmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnConsultas;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnMantenimientos;
    private javax.swing.JButton btnMovimientos;
    private javax.swing.JButton btnProcesos;
    private javax.swing.JMenuItem itemBalanzaComprobacion;
    private javax.swing.JMenuItem itemBalanzaGeneral;
    private javax.swing.JMenuItem itemCatalogoConsulta;
    private javax.swing.JMenuItem itemCatalogoMantenimiento;
    private javax.swing.JMenuItem itemCierreDiario;
    private javax.swing.JMenuItem itemEstadoGanancias;
    private javax.swing.JMenuItem itemResumenGastos;
    private javax.swing.JMenuItem itemTransacciones;
    private javax.swing.JMenuItem itemTransaccionesDocumento;
    private javax.swing.JMenuItem itemTransaccionesFecha;
    private javax.swing.JMenuItem itemTransaccionesRango;
    private javax.swing.JMenuItem itemTransaccionesTipoDocumento;
    private javax.swing.JMenuItem itemUsuario;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDescripcionControl;
    private javax.swing.JLabel lblDescripcionOrganizacion;
    private javax.swing.JLabel lblDescripcionSeguridad;
    private javax.swing.JLabel lblIconoControl;
    private javax.swing.JLabel lblIconoOrganizacion;
    private javax.swing.JLabel lblIconoSeguridad;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNombreSistema;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTituloBienvenida;
    private javax.swing.JLabel lblTituloControl;
    private javax.swing.JLabel lblTituloOrganizacion;
    private javax.swing.JLabel lblTituloSeguridad;
    private javax.swing.JLabel lblTituloSistema;
    private javax.swing.JLabel lblUsuarioActivo;
    private javax.swing.JPopupMenu menuConsultas;
    private javax.swing.JPopupMenu menuMantenimientos;
    private javax.swing.JPopupMenu menuMovimiento;
    private javax.swing.JPopupMenu menuProcesos;
    private javax.swing.JPanel pnlCaracteristicas;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlControlTotal;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JPanel pnlInicio;
    private javax.swing.JPanel pnlMenuSuperior;
    private javax.swing.JPanel pnlOrganizacion;
    private javax.swing.JPanel pnlSeguridad;
    private javax.swing.JPanel pnlSuperior;
    // End of variables declaration//GEN-END:variables
}
