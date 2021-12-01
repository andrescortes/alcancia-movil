package co.edu.utp.laboratorio.microprofile.ejemplo.negocio;

import co.edu.utp.laboratorio.microprofile.ejemplo.negocio.exceptions.JSONWebApplicationException;
import co.edu.utp.laboratorio.microprofile.ejemplo.persistencia.entidades.Login;
import co.edu.utp.laboratorio.microprofile.ejemplo.persistencia.entidades.Persona;

import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
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
                token= "88JJ";
        }

        return token;
    }


    @POST
    public Response registrarUsuario(Login login){
        if(  loginBO.validateUser(login) != null ){
            throw new JSONWebApplicationException(
                    String.format("La persona con dni %s ya existe",login.getUsuario()),Response.Status.CONFLICT);
        }
        loginBO.registrarUsuario(login);
        return Response.status(Response.Status.CREATED).entity(login).build();
    }

}
