package com.example.semana12wsrestphp;

public class Usuario {
    private  String usuario;
    private String clave;
    private String nombre;
    private String correo;

    public Usuario(String ID,  String nombre, String correo) {
        this.usuario =ID;

        this.nombre = nombre;
        this.correo = correo;
    }

    public Usuario(){


    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
