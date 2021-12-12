package co.edu.utp.laboratorio.microprofile.ejemplo.negocio;

import co.edu.utp.laboratorio.microprofile.ejemplo.persistencia.entidades.Cuenta;
import co.edu.utp.laboratorio.microprofile.ejemplo.persistencia.entidades.Login;
import co.edu.utp.laboratorio.microprofile.ejemplo.persistencia.entidades.Persona;
import co.edu.utp.laboratorio.microprofile.ejemplo.persistencia.entidades.Transferencia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Stateless
public class TransferenciaBO {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Cuenta> validateCuenta(String numeroCuenta){
        return entityManager.createNamedQuery(Cuenta.QUERY_VALIDATE_CUENTA,Cuenta.class)
            .setParameter("numeroCuenta",numeroCuenta).getResultList();
    }

    public void update( Cuenta cuenta ){
        entityManager.merge(cuenta);
    }

    public void registrarTransferencia(Transferencia transferencia){
        entityManager.persist(transferencia);
    }
}
