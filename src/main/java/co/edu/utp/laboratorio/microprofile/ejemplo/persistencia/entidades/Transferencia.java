package co.edu.utp.laboratorio.microprofile.ejemplo.persistencia.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transferencia implements Serializable {

    @Id
    @Column(length = 10)
    private String id_transferencia;
    @Column(length = 12)
    private String valor;
    @Column(length = 12)
    private String cuentaDestino;
    @Column(length = 12)
    private String cuentaOrigen;

    public String getId_transferencia() {
        return id_transferencia;
    }

    public void setId_transferencia(String id_transferencia) {
        this.id_transferencia = id_transferencia;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }
}
