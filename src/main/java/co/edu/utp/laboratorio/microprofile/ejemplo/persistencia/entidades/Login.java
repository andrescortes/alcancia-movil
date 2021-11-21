package co.edu.utp.laboratorio.microprofile.ejemplo.persistencia.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;



public class Login implements Serializable {



    private String usuario;
    private String password;


    public String getUsuario(){
        return usuario;
    }

    public String setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getPassword(){
        return password;
    }

    public String setPassword(String password){
        this.password = password;
    }



}
