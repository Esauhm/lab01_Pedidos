/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Esau
 */
public class Clientes {
    private int ID;
    private String Nombre;
    private String Dirección;
    private String Teléfono;
    private String Email;
    private int totalPedidos;

    public int getTotalPedidos() {
        return totalPedidos;
    }

    public void setTotalPedidos(int totalPedidos) {
        this.totalPedidos = totalPedidos;
    }

    public Clientes(int ID, String nombre, String dirección, String teléfono, String email) {
        this.ID = ID;
        Nombre = nombre;
        Dirección = dirección;
        Teléfono = teléfono;
        Email = email;
    }
    

    public Clientes(String nombre, String dirección, String teléfono, String email) {
        Nombre = nombre;
        Dirección = dirección;
        Teléfono = teléfono;
        Email = email;
    }

    public Clientes() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDirección() {
        return Dirección;
    }

    public void setDirección(String dirección) {
        Dirección = dirección;
    }

    public String getTeléfono() {
        return Teléfono;
    }

    public void setTeléfono(String teléfono) {
        Teléfono = teléfono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
