package co.edu.utp.laboratorio.microprofile.ejemplo.persistencia.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQuery(name = Cuenta.QUERY_VALIDATE_CUENTA, query = "select Cuenta from Cuenta cuenta where cuenta.numeroCuenta = :numeroCuenta ")
public class Cuenta implements Serializable {

    public static final String QUERY_VALIDATE_CUENTA = "Cuenta.validCuenta";

    @Id
    @Column(length = 10)
    private String idCuenta;
    @Column(length = 10)
    private String numeroCuenta;
    @Column(length = 50)
    private String tipoCuenta;
    @Column(length = 12)
    private String saldo;
    @Column(length = 10)
    private String dni;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }


    public String getidCuenta() {
        return idCuenta;
    }

    public void setidCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getnumeroCuenta() {
        return numeroCuenta;
    }

    public void setnumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String gettipoCuenta() {
        return tipoCuenta;
    }

    public void settipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
            "idCuenta='" + idCuenta + '\'' +
            ", numeroCuenta='" + numeroCuenta + '\'' +
            ", tipoCuenta='" + tipoCuenta + '\'' +
            ", saldo='" + saldo + '\'' +
            '}';
    }
}
