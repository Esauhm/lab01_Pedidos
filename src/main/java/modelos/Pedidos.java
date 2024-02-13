/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author Esau
 */
public class Pedidos {
    private int ID;
    private int ID_Cliente;
    private Date Fecha;
    private double Total;
    private String Estado;

    public Pedidos(int ID, int ID_Cliente, Date fecha, double total, String estado) {
        this.ID = ID;
        this.ID_Cliente = ID_Cliente;
        Fecha = fecha;
        Total = total;
        Estado = estado;
    }

    public Pedidos(int ID_Cliente, Date fecha, double total, String estado) {
        this.ID_Cliente = ID_Cliente;
        Fecha = fecha;
        Total = total;
        Estado = estado;
    }

    public Pedidos() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
