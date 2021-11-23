package co.edu.utp.laboratorio.microprofile.ejemplo.negocio;

import co.edu.utp.laboratorio.microprofile.ejemplo.negocio.exceptions.JSONWebApplicationException;
import co.edu.utp.laboratorio.microprofile.ejemplo.persistencia.entidades.Login;

import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/userController")
@Singleton
public class UsuarioController {
    @Inject
    private LoginBO loginBO;

    @POST
    public String autenticacion(Login  login){

        String  token = "";
        List<Login> usuarioList ;
        usuarioList =  loginBO.validateUser(login);
        if( usuarioList.isEmpty()|| usuarioList == null  ){
            throw new JSONWebApplicationException(
                    String.format("el usuario ingresado no existe o es erroneo",login.getUsuario()), Response.Status.CONFLICT);
        }else{
            for (Login usuario : usuarioList) {
                if(usuario.getPassword().equals(login.getPassword())  ) {
                    token= "88JJ";
                }else{
                    throw new JSONWebApplicationException(
                            String.format("el usuario o contraseña es erroneo",login.getUsuario()), Response.Status.CONFLICT);
                }
            }
        }
        return token;
    }

}
