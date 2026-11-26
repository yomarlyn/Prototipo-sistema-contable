package prototipo.sistema.contable.interfaces;

import Seguridad.GestorContrasenas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import prototipo.sistema.contable.datos.ArchivoUsuarios;
import prototipo.sistema.contable.modelo.Usuario;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Panel de mantenimiento de usuarios (antes FrmMantenimientoUsuarios, un JFrame).
 *
 * Incluye un buscador en tiempo real al inicio de las tres pestañas
 * (crear, modificar y desactivar).
 *
 * Uso rápido desde cualquier formulario:
 *     FrmMantenimientoUsuariosPanel.mostrarEnDialogo(this);
 *
 * Para incrustarlo en otro contenedor (por ejemplo el área central del menú):
 *     FrmMantenimientoUsuariosPanel p = new FrmMantenimientoUsuariosPanel();
 *     p.setAccionSalir(() -> { ...volver a la pantalla anterior... });
 */
public class FrmMantenimientoUsuariosPanel extends JPanel {

    // ================= COLORES =================
    private static final Color FONDO = new Color(245, 241, 234);
    private static final Color MORADO = new Color(89, 51, 75);
    private static final Color ROSADO = new Color(239, 226, 232);
    private static final Color BORDE = new Color(221, 198, 207);
    private static final Color BOTON = new Color(140, 90, 120);
    private static final Color BOTON_SALIR = new Color(163, 77, 88);
    private static final Color VERDE = new Color(46, 125, 50);
    private static final Color ROJO = new Color(183, 28, 28);
    private static final Color GRIS = new Color(120, 110, 115);

    // ================= MODOS =================
    private static final int MODO_CREAR = 0;
    private static final int MODO_MODIFICAR = 1;
    private static final int MODO_DESACTIVAR = 2;

    // Máximo de sugerencias visibles en la lista desplegable
    private static final int MAX_SUGERENCIAS = 8;

    // ================= ESTADO =================
    private final ArchivoUsuarios archivoUsuarios = new ArchivoUsuarios();
    private String usuarioOriginal = null;
    private int modoActual = MODO_CREAR;
    private Runnable accionSalir;

    // Copia en memoria de los usuarios, para buscar sin leer el archivo en cada tecla
    private ArrayList<Usuario> usuariosCache = new ArrayList<>();
    private boolean actualizandoBusqueda = false;

    // ================= COMPONENTES =================
    private JLabel lblTitulo, lblSubtitulo, lblDatos;
    private JLabel lblNombre, lblApellido, lblCorreo, lblGenero;
    private JLabel lblUsuario, lblTipoUsuario, lblContrasena;
    private JLabel lblEstadoUsuario;

    private JTextField txtNombre, txtApellido, txtCorreo, txtUsuario;
    private JPasswordField txtContrasena;
    private JComboBox<String> cmbGenero, cmbTipoUsuario, cmbEstadoUsuario;

    private JButton btnRegistrar, btnModificar, btnEliminar;
    private JButton btnLimpiar, btnSalir;

    private JButton btnCrearPestana, btnModificarPestana, btnEliminarPestana;

    private JPanel panelFormulario;
    private JPanel panelAcciones;

    // ----- Buscador en tiempo real -----
    private JLabel lblBuscar, lblEstadoBusqueda;
    private JTextField txtBuscar;
    private DefaultListModel<Usuario> modeloSugerencias;
    private JList<Usuario> listaSugerencias;
    private JScrollPane scrollSugerencias;

    // =====================================================
    //  CONSTRUCTOR
    // =====================================================
    public FrmMantenimientoUsuariosPanel() {
        setLayout(new BorderLayout());
        setBackground(FONDO);
        setPreferredSize(new Dimension(950, 700));

        crearComponentes();
        crearBuscador();
        construirInterfaz();

        // Al hacer clic en una zona vacía del panel se cierra la lista de sugerencias
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ocultarSugerencias();
            }
        });

        cambiarModo(MODO_CREAR);
    }

    /** Qué hacer al pulsar "Salir". Si no se define, se cierra la ventana que contiene el panel. */
    public void setAccionSalir(Runnable accionSalir) {
        this.accionSalir = accionSalir;
    }

    /** Muestra el panel dentro de un diálogo modal. */
    public static void mostrarEnDialogo(Frame owner) {
        JDialog dialogo = new JDialog(owner, "Mantenimiento de usuarios - CONSULT", true);
        dialogo.setUndecorated(true);

        FrmMantenimientoUsuariosPanel panel = new FrmMantenimientoUsuariosPanel();
        panel.setAccionSalir(dialogo::dispose);

        dialogo.setContentPane(panel);
        dialogo.pack();
        dialogo.setLocationRelativeTo(owner);
        dialogo.setVisible(true);
    }

    // =====================================================
    //  CREACIÓN DE COMPONENTES
    // =====================================================
    private void crearComponentes() {
        lblTitulo = new JLabel("Mantenimiento de usuarios");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        lblTitulo.setForeground(Color.WHITE);

        lblSubtitulo = new JLabel("CONSULT - SISTEMA CONTABLE");
        lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblSubtitulo.setForeground(new Color(240, 220, 230));

        lblDatos = new JLabel("Datos del usuario", JLabel.CENTER);
        lblDatos.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblDatos.setForeground(MORADO);

        lblNombre = crearEtiqueta("Nombre");
        lblApellido = crearEtiqueta("Apellido");
        lblCorreo = crearEtiqueta("Correo electrónico");
        lblGenero = crearEtiqueta("Género");
        lblUsuario = crearEtiqueta("Nombre de usuario");
        lblTipoUsuario = crearEtiqueta("Tipo de usuario");
        lblContrasena = crearEtiqueta("Contraseña");

        txtNombre = crearTexto();
        txtApellido = crearTexto();
        txtCorreo = crearTexto();
        txtUsuario = crearTexto();

        txtContrasena = new JPasswordField();

        txtContrasena.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtContrasena.setForeground(MORADO);
        txtContrasena.setBackground(Color.WHITE);

        txtContrasena.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE, 1, true),
                BorderFactory.createEmptyBorder(7, 10, 7, 10)
        ));

        cmbGenero = crearCombo("Seleccione...", "Masculino", "Femenino");
        cmbTipoUsuario = crearCombo("Seleccione...", "Administrador", "Usuario normal");
        lblEstadoUsuario = crearEtiqueta("Estado del usuario");

        cmbEstadoUsuario = crearCombo("Habilitado", "Deshabilitado");

        btnRegistrar = crearBoton("Registrar", BOTON);
        btnModificar = crearBoton("Modificar", BOTON);
        btnEliminar = crearBoton("Desactivar", BOTON);
        btnLimpiar = crearBoton("Limpiar", BOTON_SALIR);
        btnSalir = crearBoton("Salir", BOTON_SALIR);

        btnRegistrar.addActionListener(e -> registrar());
        btnModificar.addActionListener(e -> modificar());
        btnEliminar.addActionListener(e -> desactivar());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnSalir.addActionListener(e -> salir());

        btnCrearPestana = new BotonRedondeado("Crear / Registrar usuarios");
        btnModificarPestana = new BotonRedondeado("Modificar usuarios");
        btnEliminarPestana = new BotonRedondeado("Desactivar usuarios");

        btnCrearPestana.addActionListener(e -> cambiarModo(MODO_CREAR));
        btnModificarPestana.addActionListener(e -> cambiarModo(MODO_MODIFICAR));
        btnEliminarPestana.addActionListener(e -> cambiarModo(MODO_DESACTIVAR));
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("SansSerif", Font.BOLD, 14));
        l.setForeground(MORADO);
        return l;
    }

    private JTextField crearTexto() {

        JTextField t = new JTextField();

        t.setFont(new Font("SansSerif", Font.PLAIN, 14));
        t.setForeground(MORADO);
        t.setBackground(Color.WHITE);

        t.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE, 1, true),
                BorderFactory.createEmptyBorder(7, 10, 7, 10)
        ));

        return t;
    }

    private JComboBox<String> crearCombo(String... items) {

        JComboBox<String> c = new JComboBox<>(items);

        c.setFont(new Font("SansSerif", Font.PLAIN, 14));
        c.setForeground(MORADO);
        c.setBackground(Color.WHITE);

        c.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE, 1, true),
                BorderFactory.createEmptyBorder(4, 7, 4, 7)
        ));

        return c;
    }

    private JButton crearBoton(String texto, Color fondo) {

        JButton b = new BotonRedondeado(texto);

        b.setBackground(fondo);

        return b;
    }

    // =====================================================
    //  BUSCADOR EN TIEMPO REAL
    // =====================================================
    private void crearBuscador() {
        lblBuscar = crearEtiqueta("Buscar usuario");

        txtBuscar = crearTexto();
        txtBuscar.setToolTipText("Busque por usuario, nombre, apellido o correo");

        lblEstadoBusqueda = new JLabel(" ");
        lblEstadoBusqueda.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblEstadoBusqueda.setForeground(GRIS);

        // Lista desplegable de coincidencias
        modeloSugerencias = new DefaultListModel<>();
        listaSugerencias = new JList<>(modeloSugerencias);
        listaSugerencias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaSugerencias.setFixedCellHeight(30);
        listaSugerencias.setFocusable(false);
        listaSugerencias.setCellRenderer(new RenderSugerencia());

        listaSugerencias.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int i = indiceBajoMouse(e);
                if (i >= 0) {
                    listaSugerencias.setSelectedIndex(i);
                }
            }
        });

        listaSugerencias.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int i = indiceBajoMouse(e);
                if (i >= 0) {
                    listaSugerencias.setSelectedIndex(i);
                    elegirUsuario(listaSugerencias.getSelectedValue());
                }
            }
        });

        scrollSugerencias = new JScrollPane(listaSugerencias);
        scrollSugerencias.setBorder(BorderFactory.createLineBorder(BORDE));
        scrollSugerencias.setFocusable(false);
        scrollSugerencias.getVerticalScrollBar().setFocusable(false);

        // Cada vez que cambia el texto se vuelve a buscar
        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarEnTiempoReal();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarEnTiempoReal();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarEnTiempoReal();
            }
        });

        // Teclado: flechas para moverse, Enter para elegir, Esc para cerrar
        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DOWN:
                        if (!sugerenciasVisibles()) {
                            buscarEnTiempoReal();
                        }
                        moverSeleccion(1);
                        e.consume();
                        break;
                    case KeyEvent.VK_UP:
                        moverSeleccion(-1);
                        e.consume();
                        break;
                    case KeyEvent.VK_ENTER:
                        confirmarBusqueda();
                        e.consume();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        ocultarSugerencias();
                        break;
                    default:
                        break;
                }
            }
        });

        txtBuscar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ocultarSugerencias();
            }
        });

        // Si el campo deja de verse (cambio de pantalla), se cierra la lista
        txtBuscar.addHierarchyListener(e -> {
            if (!txtBuscar.isShowing()) {
                ocultarSugerencias();
            }
        });
    }

    private void buscarEnTiempoReal() {
        if (actualizandoBusqueda) {
            return;
        }

        String texto = txtBuscar.getText().trim();
        modeloSugerencias.clear();

        if (texto.isEmpty()) {
            ocultarSugerencias();
            mostrarEstado(textoAyudaBusqueda(), GRIS);
            return;
        }

        ArrayList<Usuario> coincidencias = buscarCoincidencias(texto);

        for (int i = 0; i < coincidencias.size() && i < MAX_SUGERENCIAS; i++) {
            modeloSugerencias.addElement(coincidencias.get(i));
        }

        actualizarEstadoBusqueda(texto, coincidencias.size(), existeUsuarioExacto(texto));

        if (modeloSugerencias.isEmpty()) {
            ocultarSugerencias();
        } else {
            mostrarSugerencias();
        }
    }

    /** Busca por usuario, nombre, apellido o correo, sin importar mayúsculas ni tildes. */
    private ArrayList<Usuario> buscarCoincidencias(String texto) {
        String q = normalizar(texto);

        ArrayList<Usuario> empiezanIgual = new ArrayList<>();
        ArrayList<Usuario> contienen = new ArrayList<>();

        for (Usuario u : usuariosCache) {
            String user = normalizar(u.getUsuario());
            String nombreCompleto = normalizar(u.getNombre() + " " + u.getApellido());
            String correo = normalizar(u.getCorreo());

            if (user.startsWith(q)) {
                empiezanIgual.add(u);
            } else if (user.contains(q) || nombreCompleto.contains(q) || correo.contains(q)) {
                contienen.add(u);
            }
        }

        // Primero los usuarios que empiezan igual, luego el resto
        empiezanIgual.addAll(contienen);
        return empiezanIgual;
    }

    private boolean existeUsuarioExacto(String texto) {
        for (Usuario u : usuariosCache) {
            if (u.getUsuario() != null && u.getUsuario().equalsIgnoreCase(texto)) {
                return true;
            }
        }
        return false;
    }

    private static String normalizar(String s) {
        if (s == null) {
            return "";
        }
        String n = Normalizer.normalize(s, Normalizer.Form.NFD);
        return n.replaceAll("\\p{M}", "").toLowerCase();
    }

    private String textoAyudaBusqueda() {
        if (modoActual == MODO_CREAR) {
            return "Escriba un nombre de usuario para comprobar si ya existe.";
        }
        return "Escriba para buscar por usuario, nombre, apellido o correo.";
    }

    private void actualizarEstadoBusqueda(String texto, int total, boolean existeExacto) {
        if (modoActual == MODO_CREAR) {
            if (existeExacto) {
                mostrarEstado("El usuario \"" + texto + "\" ya existe. Elija otro nombre de usuario.", ROJO);
            } else if (total > 0) {
                mostrarEstado("El usuario \"" + texto + "\" está disponible. Hay " + total
                        + " usuario(s) con nombre parecido.", VERDE);
            } else {
                mostrarEstado("El usuario \"" + texto + "\" está disponible.", VERDE);
            }
            return;
        }

        if (total == 0) {
            mostrarEstado("No existe ningún usuario parecido a \"" + texto + "\".", ROJO);
        } else if (existeExacto) {
            mostrarEstado("El usuario existe. Selecciónelo en la lista para cargar sus datos.", VERDE);
        } else {
            mostrarEstado("Se encontraron " + total
                    + " coincidencia(s). Seleccione una para cargar sus datos.", VERDE);
        }
    }

    private void mostrarEstado(String texto, Color color) {
        lblEstadoBusqueda.setText(texto == null || texto.isEmpty() ? " " : texto);
        lblEstadoBusqueda.setForeground(color);
    }

    // ----- Lista desplegable (se dibuja sobre el resto del formulario) -----
    private boolean sugerenciasVisibles() {
        return scrollSugerencias.getParent() != null;
    }

    private void mostrarSugerencias() {
        JRootPane raiz = SwingUtilities.getRootPane(txtBuscar);

        if (raiz == null || !txtBuscar.isShowing() || modeloSugerencias.isEmpty()) {
            return;
        }

        JLayeredPane capa = raiz.getLayeredPane();
        Point p = SwingUtilities.convertPoint(txtBuscar, 0, txtBuscar.getHeight(), capa);

        int filas = Math.min(modeloSugerencias.size(), MAX_SUGERENCIAS);
        int alto = filas * listaSugerencias.getFixedCellHeight() + 2;

        scrollSugerencias.setBounds(p.x, p.y, txtBuscar.getWidth(), alto);

        if (scrollSugerencias.getParent() != capa) {
            capa.add(scrollSugerencias, JLayeredPane.POPUP_LAYER);
        }

        listaSugerencias.clearSelection();
        scrollSugerencias.revalidate();
        capa.repaint();
    }

    private void ocultarSugerencias() {
        Container padre = scrollSugerencias.getParent();

        if (padre != null) {
            Rectangle r = scrollSugerencias.getBounds();
            padre.remove(scrollSugerencias);
            padre.repaint(r.x, r.y, r.width, r.height);
        }
    }

    
    private void moverSeleccion(int delta) {
        if (modeloSugerencias.isEmpty()) {
            return;
        }

        int indice = listaSugerencias.getSelectedIndex() + delta;
        indice = Math.max(0, Math.min(indice, modeloSugerencias.size() - 1));

        listaSugerencias.setSelectedIndex(indice);

        // Mantiene visible la sugerencia seleccionada
        listaSugerencias.ensureIndexIsVisible(indice);
    }


    private int indiceBajoMouse(MouseEvent e) {
        int i = listaSugerencias.locationToIndex(e.getPoint());

        if (i >= 0) {
            Rectangle r = listaSugerencias.getCellBounds(i, i);
            if (r != null && r.contains(e.getPoint())) {
                return i;
            }
        }
        return -1;
    }

    /** Enter en el buscador: elige el resaltado, o la coincidencia exacta, o la única sugerida. */
    private void confirmarBusqueda() {
        Usuario elegido = listaSugerencias.getSelectedValue();

        if (elegido == null) {
            String texto = txtBuscar.getText().trim();
            for (Usuario u : usuariosCache) {
                if (u.getUsuario() != null && u.getUsuario().equalsIgnoreCase(texto)) {
                    elegido = u;
                    break;
                }
            }
        }

        if (elegido == null && modeloSugerencias.size() == 1) {
            elegido = modeloSugerencias.get(0);
        }

        if (elegido != null) {
            elegirUsuario(elegido);
        }
    }

    private void elegirUsuario(Usuario u) {
        if (u == null) {
            return;
        }

        ocultarSugerencias();

        // En "Crear" la lista es solo informativa: el usuario ya existe
        if (modoActual == MODO_CREAR) {
            mostrarEstado("El usuario \"" + u.getUsuario()
                    + "\" ya existe. Elija otro nombre de usuario.", ROJO);
            return;
        }

        cargarEnFormulario(u);
        sincronizarBusqueda(u.getUsuario());
    }

    private void sincronizarBusqueda(String usuario) {
        actualizandoBusqueda = true;
        txtBuscar.setText(usuario);
        actualizandoBusqueda = false;

        modeloSugerencias.clear();
        ocultarSugerencias();
        mostrarEstado("Usuario \"" + usuario + "\" cargado.", VERDE);
    }

    private void limpiarBusqueda() {
        actualizandoBusqueda = true;
        txtBuscar.setText("");
        actualizandoBusqueda = false;

        modeloSugerencias.clear();
        ocultarSugerencias();
        mostrarEstado(textoAyudaBusqueda(), GRIS);
    }

    /** Vuelve a leer los usuarios del archivo para que el buscador esté actualizado. */
    private void recargarUsuarios() {
        try {
            usuariosCache = archivoUsuarios.cargarUsuarios();
        } catch (IOException e) {
            usuariosCache = new ArrayList<>();
            mostrarEstado("No se pudo leer la lista de usuarios: " + e.getMessage(), ROJO);
        }
    }

    /** Cómo se dibuja cada fila de la lista de sugerencias. */
    private static class RenderSugerencia extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {

            super.getListCellRendererComponent(list, value, index, isSelected, false);

            if (value instanceof Usuario) {
                Usuario u = (Usuario) value;
                setText(u.getUsuario() + "   -   " + u.getNombre() + " " + u.getApellido()
                        + "   (" + u.getTipoUsuario() + ")");
            }

            setFont(new Font("SansSerif", Font.PLAIN, 13));
            setForeground(MORADO);
            setBackground(isSelected ? ROSADO : Color.WHITE);
            setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
            return this;
        }
    }

    // =====================================================
    //  CONSTRUCCIÓN DE LA INTERFAZ
    // =====================================================
    private void construirInterfaz() {

        // ---------- Encabezado ----------
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(MORADO);
        encabezado.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JPanel textos = new JPanel(new GridLayout(2, 1));
        textos.setOpaque(false);
        textos.add(lblTitulo);
        textos.add(lblSubtitulo);
        encabezado.add(textos, BorderLayout.WEST);

        add(encabezado, BorderLayout.NORTH);

        // ---------- Pestañas ----------
        JPanel panelPestanas = new JPanel(new GridLayout(1, 3, 8, 0));
        panelPestanas.setBackground(FONDO);
        panelPestanas.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        panelPestanas.add(btnCrearPestana);
        panelPestanas.add(btnModificarPestana);
        panelPestanas.add(btnEliminarPestana);

        // ---------- Buscador ----------
        JPanel panelBusqueda = new JPanel(new BorderLayout(12, 4));
        panelBusqueda.setBackground(Color.WHITE);
        panelBusqueda.setBorder(
                BorderFactory.createEmptyBorder(12, 20, 10, 20)
        );

        txtBuscar.setPreferredSize(new Dimension(300, 36));

        panelBusqueda.add(lblBuscar, BorderLayout.WEST);
        panelBusqueda.add(txtBuscar, BorderLayout.CENTER);
        panelBusqueda.add(lblEstadoBusqueda, BorderLayout.SOUTH);

        JPanel norte = new JPanel(new BorderLayout());
        norte.setOpaque(false);
        norte.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        norte.add(panelPestanas, BorderLayout.NORTH);
        norte.add(panelBusqueda, BorderLayout.CENTER);

        // ---------- Formulario ----------
        panelFormulario = new JPanel(new BorderLayout(0, 15));
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 25, 30)
        );

        panelFormulario.add(lblDatos, BorderLayout.NORTH);

        JPanel campos = new JPanel(new GridBagLayout());
        campos.setBackground(Color.WHITE);

        addCampo(campos, lblNombre, txtNombre, 0, 0);
        addCampo(campos, lblApellido, txtApellido, 2, 0);
        addCampo(campos, lblCorreo, txtCorreo, 0, 1);
        addCampo(campos, lblGenero, cmbGenero, 2, 1);
        addCampo(campos, lblUsuario, txtUsuario, 0, 2);
        addCampo(campos, lblTipoUsuario, cmbTipoUsuario, 2, 2);
        addCampo(campos, lblContrasena, txtContrasena, 0, 3);
        addCampo(campos, lblEstadoUsuario, cmbEstadoUsuario, 2, 3);

        panelFormulario.add(campos, BorderLayout.CENTER);

        // ---------- Acciones ----------
        panelAcciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        panelAcciones.setBackground(FONDO);

        // ---------- Centro ----------
        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(FONDO);
        centro.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 30));
        centro.add(norte, BorderLayout.NORTH);
        centro.add(panelFormulario, BorderLayout.CENTER);
        centro.add(panelAcciones, BorderLayout.SOUTH);

        add(centro, BorderLayout.CENTER);
    }

    /** columna 0 = izquierda, columna 2 = derecha. */
    private void addCampo(JPanel panel, JLabel etiqueta, Component campo, int columna, int fila) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = fila;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = columna;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(etiqueta, gbc);

        gbc.gridx = columna + 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        campo.setPreferredSize(new Dimension(250, 36));
        panel.add(campo, gbc);
    }

    // =====================================================
    //  MODOS (CREAR / MODIFICAR / DESACTIVAR)
    // =====================================================
    private void cambiarModo(int modo) {
        modoActual = modo;

        estilizarPestana(btnCrearPestana, modo == MODO_CREAR);
        estilizarPestana(btnModificarPestana, modo == MODO_MODIFICAR);
        estilizarPestana(btnEliminarPestana, modo == MODO_DESACTIVAR);

        switch (modo) {
            case MODO_CREAR:
                lblDatos.setText("Registrar nuevo usuario");
                lblBuscar.setText("Verificar usuario");
                break;
            case MODO_MODIFICAR:
                lblDatos.setText("Modificar datos del usuario");
                lblBuscar.setText("Buscar usuario");
                break;
            default:
                lblDatos.setText("Desactivar usuario");
                lblBuscar.setText("Buscar usuario");
                break;
        }

        panelAcciones.removeAll();

        if (modo == MODO_CREAR) {
            panelAcciones.add(btnRegistrar);
        } else if (modo == MODO_MODIFICAR) {
            panelAcciones.add(btnModificar);
        } else {
            panelAcciones.add(btnEliminar);
        }

        panelAcciones.add(btnLimpiar);
        panelAcciones.add(btnSalir);

        boolean editable = modo != MODO_DESACTIVAR;
        
        // El estado solo se puede consultar y modificar en esta pestaña.
        lblEstadoUsuario.setVisible(modo == MODO_MODIFICAR);
        cmbEstadoUsuario.setVisible(modo == MODO_MODIFICAR);

        txtNombre.setEditable(editable);
        txtApellido.setEditable(editable);
        txtCorreo.setEditable(editable);
        txtContrasena.setEditable(editable);
        cmbGenero.setEnabled(editable);
        cmbTipoUsuario.setEnabled(editable);
        txtUsuario.setEditable(true);

        // Al cambiar de pestaña se empieza limpio para no operar con datos viejos
        limpiarCampos();
        recargarUsuarios();

        panelAcciones.revalidate();
        panelAcciones.repaint();
    }

    private void estilizarPestana(JButton boton, boolean seleccionada) {

        boton.setBackground(seleccionada ? ROSADO : Color.WHITE);
        boton.setForeground(MORADO);

        boton.setFont(new Font("SansSerif", Font.BOLD, 13));

        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setOpaque(false);

        boton.setBorder(BorderFactory.createEmptyBorder(12, 10, 12, 10));

        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private class BotonRedondeado extends JButton {

    public BotonRedondeado(String texto) {
        super(texto);

        setFont(new Font("SansSerif", Font.BOLD, 14));
        setForeground(Color.WHITE);

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);

        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        Color color = getBackground();

        if (getModel().isPressed()) {
            color = color.darker();
        } else if (getModel().isRollover()) {
            color = color.brighter();
        }

        g2.setColor(color);

        g2.fillRoundRect(
                0, 0,
                getWidth(),
                getHeight(),
                16, 16
        );

        g2.dispose();

        super.paintComponent(g);
    }
}
    
    // =====================================================
    //  ACCIONES
    // =====================================================
    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        txtUsuario.setText("");
        txtContrasena.setText("");
        cmbGenero.setSelectedIndex(0);
        cmbTipoUsuario.setSelectedIndex(0);
        cmbEstadoUsuario.setSelectedIndex(0);
        usuarioOriginal = null;

        limpiarBusqueda();
    }

    private void cargarEnFormulario(Usuario u) {
        txtNombre.setText(u.getNombre());
        txtApellido.setText(u.getApellido());
        txtCorreo.setText(u.getCorreo());
        cmbGenero.setSelectedItem(u.getGenero());
        txtUsuario.setText(u.getUsuario());
        txtContrasena.setText("");
        cmbTipoUsuario.setSelectedItem(u.getTipoUsuario());
        cmbEstadoUsuario.setSelectedItem(
                "INACTIVO".equalsIgnoreCase(u.getEstado())
                        ? "Deshabilitado"
                        : "Habilitado"
        );

        usuarioOriginal = u.getUsuario();
    }

    private void registrar() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String correo = txtCorreo.getText().trim();
        String genero = cmbGenero.getSelectedItem().toString();
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());
        String tipoUsuario = cmbTipoUsuario.getSelectedItem().toString();

        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()
                || usuario.isEmpty() || contrasena.isEmpty()
                || genero.equals("Seleccione...")
                || tipoUsuario.equals("Seleccione...")) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.");
            return;
        }

        // El hash se calcula solo cuando los datos ya fueron validados
        String contrasenaHash = GestorContrasenas.generarHash(contrasena);

        Usuario nuevoUsuario = new Usuario(
                nombre, apellido, correo, genero,
                usuario, contrasenaHash, tipoUsuario);

        try {
            boolean resultado = archivoUsuarios.agregarUsuario(nuevoUsuario);

            if (resultado) {
                JOptionPane.showMessageDialog(this, "Usuario registrado correctamente.");
                limpiarCampos();
                recargarUsuarios();
            } else {
                JOptionPane.showMessageDialog(this,
                        "El nombre de usuario ya existe o los datos no son válidos.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al registrar el usuario: " + e.getMessage());
        }
    }

    private void consultar() {
        String usuarioBuscado = txtUsuario.getText().trim();

        if (usuarioBuscado.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Escriba el nombre de usuario que desea consultar.");
            return;
        }

        try {
            ArrayList<Usuario> usuarios = archivoUsuarios.cargarUsuarios();
            Usuario encontrado = null;

            for (Usuario u : usuarios) {
                if (u.getUsuario().equalsIgnoreCase(usuarioBuscado)) {
                    encontrado = u;
                    break;
                }
            }

            if (encontrado != null) {
                cargarEnFormulario(encontrado);
                sincronizarBusqueda(encontrado.getUsuario());

                JOptionPane.showMessageDialog(this, "Usuario encontrado.");
            } else {
                usuarioOriginal = null;
                JOptionPane.showMessageDialog(this, "No se encontró el usuario.");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al consultar: " + e.getMessage());
        }
    }

    private void modificar() {
        if (usuarioOriginal == null) {
            JOptionPane.showMessageDialog(this,
                    "Primero debe buscar o consultar el usuario que desea modificar.");
            return;
        }

        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String correo = txtCorreo.getText().trim();
        String genero = cmbGenero.getSelectedItem().toString();
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());
        String tipoUsuario = cmbTipoUsuario.getSelectedItem().toString();

        if (nombre.isEmpty() || apellido.isEmpty() || usuario.isEmpty()
                || genero.equals("Seleccione...")
                || tipoUsuario.equals("Seleccione...")) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.");
            return;
        }

        String contrasenaHash;

        if (contrasena.isEmpty()) {
            // Si no se escribe una contraseña nueva, se conserva la original
            try {
                contrasenaHash = obtenerHashOriginal();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Error al recuperar la contraseña: " + e.getMessage());
                return;
            }

            if (contrasenaHash == null) {
                JOptionPane.showMessageDialog(this,
                        "No se encontró la contraseña original.");
                return;
            }
        } else {
            contrasenaHash = GestorContrasenas.generarHash(contrasena);
        }

        // Convertir la opción visible al estado que se guarda en el archivo.
        String estado = cmbEstadoUsuario.getSelectedItem().toString()
                .equals("Habilitado") ? "ACTIVO" : "INACTIVO";

        Usuario usuarioModificado = new Usuario(
                nombre, apellido, correo, genero,
                usuario, contrasenaHash, tipoUsuario, estado);

        // Guardar los datos y el nuevo estado.
        boolean resultado = archivoUsuarios.modificarUsuario(
                usuarioOriginal,
                usuarioModificado,
                estado
        );
        
        if (resultado) {
            JOptionPane.showMessageDialog(this, "Usuario modificado correctamente.");
            limpiarCampos();
            recargarUsuarios();
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se pudo modificar el usuario. Verifique que el nuevo nombre de usuario "
                    + "no esté ocupado y que exista el usuario original.");
        }
    }

    /** Devuelve el hash guardado del usuario original, o null si no existe. */
    private String obtenerHashOriginal() throws IOException {
        for (Usuario u : archivoUsuarios.cargarUsuarios()) {
            if (u.getUsuario().equalsIgnoreCase(usuarioOriginal)) {
                return u.getContrasena();
            }
        }
        return null;
    }

    private void desactivar() {
        String usuario = txtUsuario.getText().trim();

        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Escriba o busque el usuario que desea desactivar.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea desactivar este usuario?",
                "Confirmar desactivación",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        boolean resultado = archivoUsuarios.eliminarUsuario(usuario);

        if (resultado) {
            JOptionPane.showMessageDialog(this, "Usuario desactivado correctamente.");
            limpiarCampos();
            recargarUsuarios();
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se pudo desactivar el usuario. Verifique que exista.");
        }
    }

    private void salir() {
        ocultarSugerencias();

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea salir del mantenimiento de usuarios?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION);

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        if (accionSalir != null) {
            accionSalir.run();
        } else {
            Window ventana = SwingUtilities.getWindowAncestor(this);
            if (ventana != null) {
                ventana.dispose();
            }
        }
    }
}