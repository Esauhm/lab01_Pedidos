/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;
import java.sql.SQLException;
import modelosDAO.ClienteDAO;

/**
 *
 * @author Esau
 */
public class Pedidos {
    private int ID;
    private int ID_Cliente;
    private int nombre_cliente;
    private Date Fecha;
    private double Total;
    private String Estado;
    private String mes;
    private int cantidad;
    private String anio;
    
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

    public String getNombre_cliente(String id_cliente) throws SQLException {
        ClienteDAO clt = new ClienteDAO();
        
         Clientes clientes = clt.obtenerClienteByID(Integer.parseInt(id_cliente));
            
        return clientes.getNombre();
    }

    
    public void setNombre_cliente(int id_cliente) {
        this.nombre_cliente = nombre_cliente;
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

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String anio) {
        this.mes = anio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
