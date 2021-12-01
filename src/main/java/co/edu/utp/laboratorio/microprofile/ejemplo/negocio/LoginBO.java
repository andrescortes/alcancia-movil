package co.edu.utp.laboratorio.microprofile.ejemplo.negocio;

import co.edu.utp.laboratorio.microprofile.ejemplo.persistencia.entidades.Login;
import co.edu.utp.laboratorio.microprofile.ejemplo.persistencia.entidades.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LoginBO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Login> validateUser(Login login){
        return entityManager.createNamedQuery(Login.QUERY_VALIDATE_USER,Login.class)
            .setParameter("usuario",login.getUsuario()).setParameter("password",login.getPassword()).getResultList();
    }

    public void registrarUsuario(Login login){
        entityManager.persist(login);
    }

}
