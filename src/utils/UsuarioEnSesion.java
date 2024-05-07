package utils;

import Usuarios.Usuario;

public class UsuarioEnSesion {

    private static UsuarioEnSesion instancia;
    private Usuario usuarioActual;

    private UsuarioEnSesion() {
    }

    public static UsuarioEnSesion obtenerInstancia() {
        if(instancia == null) {
            instancia = new UsuarioEnSesion();
        }

        return instancia;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public boolean hayUsuarioEnSesion() {
        return usuarioActual != null;
    }

    public void cerrarSesion() {
        instancia = null;
        usuarioActual = null;
    }
}
