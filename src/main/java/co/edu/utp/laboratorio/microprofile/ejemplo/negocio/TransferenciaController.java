package co.edu.utp.laboratorio.microprofile.ejemplo.negocio;

import co.edu.utp.laboratorio.microprofile.ejemplo.negocio.exceptions.JSONWebApplicationException;
import co.edu.utp.laboratorio.microprofile.ejemplo.persistencia.entidades.Cuenta;
import co.edu.utp.laboratorio.microprofile.ejemplo.persistencia.entidades.Login;
import co.edu.utp.laboratorio.microprofile.ejemplo.persistencia.entidades.Persona;
import co.edu.utp.laboratorio.microprofile.ejemplo.persistencia.entidades.Transferencia;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/transferenciaController")
@Singleton
public class TransferenciaController {

    @Inject
    private TransferenciaBO transferenciaBO;


    @POST
    @Path("/transferir")
    public Response transferir(Transferencia transferencia) {
        List<Cuenta>listaCuentaOrigen = new ArrayList<>();
        List<Cuenta> listaCuentaDestino = new ArrayList<>();

        listaCuentaOrigen = transferenciaBO.validateCuenta(
            transferencia.getCuentaOrigen());
        listaCuentaDestino = transferenciaBO.validateCuenta(
            transferencia.getCuentaDestino());
        System.out.println("listcuentadestino: " + listaCuentaDestino);
        System.out.println("listcuentaOrigen: " + listaCuentaOrigen);
        if (listaCuentaOrigen.isEmpty()) {
            throw new JSONWebApplicationException(
                String.format("La cuenta origen no existe"),
                Response.Status.CONFLICT);
        }

        if (listaCuentaDestino.isEmpty()) {
            throw new JSONWebApplicationException(
                String.format("La cuenta destino no existe"),
                Response.Status.CONFLICT);
        }
        if (Integer.valueOf(transferencia.getValor()) > Integer.valueOf(
            listaCuentaOrigen.get(0).getSaldo())) {
            throw new JSONWebApplicationException(
                String.format("El saldo de la cuenta origen es insuficiente"),
                Response.Status.CONFLICT);
        }
        if (transferencia.getCuentaDestino().equals(transferencia.getCuentaOrigen())) {
            throw new JSONWebApplicationException(
                String.format(
                    "No se puede realizar una transferencia desde una cuenta origen a su misma cuenta"),
                Response.Status.CONFLICT);
        }

        // se le resta el valor a la cuenta origen
        Cuenta cuentaOrigen;
        cuentaOrigen = listaCuentaOrigen.get(0);
        int valorTransferencia = Integer.valueOf(transferencia.getValor());
        int valorCuentaOrigen = Integer.valueOf(listaCuentaOrigen.get(0).getSaldo());
        int descount = valorCuentaOrigen - valorTransferencia;
        cuentaOrigen.setSaldo(String.valueOf(descount));
        transferenciaBO.update(cuentaOrigen);
        //se suma valor a la cuenta destino
        Cuenta cuentaDestino;
        cuentaDestino = listaCuentaDestino.get(0);
        int valorTransferencia1 = Integer.valueOf(transferencia.getValor());
        int valorCuentaOrigen1 = Integer.valueOf(listaCuentaDestino.get(0).getSaldo());
        int descount1 = valorCuentaOrigen1 + valorTransferencia1;
        cuentaDestino.setSaldo(String.valueOf(descount1));
        transferenciaBO.update(cuentaDestino);

        //se almacena la transferencia en la tabla transaccional
        transferenciaBO.registrarTransferencia(transferencia);

        return Response.status(Response.Status.CREATED).entity(transferencia).build();
    }



}
