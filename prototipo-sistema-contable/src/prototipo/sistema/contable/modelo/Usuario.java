package prototipo.sistema.contable.modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String apellido;
    private String correo;
    private String genero;
    private String usuario;
    private String contrasena;
    private String tipoUsuario;
    private String estado;

    // Constructor original: los usuarios nuevos comienzan activos.
    public Usuario(String nombre, String apellido, String correo,
                   String genero, String usuario, String contrasena,
                   String tipoUsuario) {

        this(nombre, apellido, correo, genero, usuario,
             contrasena, tipoUsuario, "ACTIVO");
    }

    // Constructor que también permite indicar el estado.
    public Usuario(String nombre, String apellido, String correo,
                   String genero, String usuario, String contrasena,
                   String tipoUsuario, String estado) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.genero = genero;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.tipoUsuario = tipoUsuario;
        this.estado = estado;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getGenero() {
        return genero;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public String getEstado() {
        return estado;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}