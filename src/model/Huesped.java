package model;

import java.util.Date;

public class Huesped {
    private Integer id;
    private String nombreH;
    private String apellidoH;
    private Date fechaNacimientoH;
    private String nacionalidadH;
    private String telefonoH;
    private Integer idReserva;

    public Huesped(String nombreH, String apellidoH, Date fechaNacimientoH, String nacionalidadH, String telefonoH, int idReserva) {
        super();
        this.nombreH = nombreH;
        this.apellidoH = apellidoH;
        this.fechaNacimientoH = fechaNacimientoH;
        this.nacionalidadH = nacionalidadH;
        this.telefonoH = telefonoH;
        this.idReserva = idReserva;
    }

    public Huesped(Integer id, String nombreH, String apellidoH, Date fechaNacimientoH, String nacionalidadH, String telefonoH, Integer idReserva) {
        this.id = id;
        this.nombreH = nombreH;
        this.apellidoH = apellidoH;
        this.fechaNacimientoH = fechaNacimientoH;
        this.nacionalidadH = nacionalidadH;
        this.telefonoH = telefonoH;
        this.idReserva = idReserva;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreH() {
        return nombreH;
    }

    public void setNombreH(String nombreH) {
        this.nombreH = nombreH;
    }

    public String getApellidoH() {
        return apellidoH;
    }

    public void setApellidoH(String apellidoH) {
        this.apellidoH = apellidoH;
    }

    public Date getFechaNacimientoH() {
        return fechaNacimientoH;
    }

    public void setFechaNacimientoH(Date fechaNacimientoH) {
        this.fechaNacimientoH = fechaNacimientoH;
    }

    public String getNacionalidadH() {
        return nacionalidadH;
    }

    public void setNacionalidadH(String nacionalidadH) {
        this.nacionalidadH = nacionalidadH;
    }

    public String getTelefonoH() {
        return telefonoH;
    }

    public void setTelefonoH(String telefonoH) {
        this.telefonoH = telefonoH;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }
}
