package co.edu.utp.laboratorio.microprofile.ejemplo.persistencia.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Entity
@NamedQuery(name = Login.QUERY_VALIDATE_USER,query = "select login from Login login where login.usuario = :usuario and login.password = :password")

public class Login implements Serializable {

     public static final String QUERY_VALIDATE_USER = "Login.validUser";

    @Id
    @Column(length = 10)
    private String dni;
    @Column(length = 20)
    private String usuario;
    @Column(length = 20)
    private String password;
    @Column(length = 20)
    private String telefono;
    @Column(length = 20)
    private String direccion;
    @Column(length = 20)
    private String email;


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Login{" +
                "dni='" + dni + '\'' +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}