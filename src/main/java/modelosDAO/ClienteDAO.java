/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelosDAO;

import db.cn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.Clientes;

/**
 *
 * @author Esau
 */
public class ClienteDAO {

    private cn CN;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public ClienteDAO() throws SQLException {
        try {
            CN = new cn();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Clientes> consultaGeneral() {

        ArrayList<Clientes> lista = new ArrayList<>();
        // Agrega aquí la lógica para obtener todos los clientes de la base de datos.
        // Utiliza el objeto CN para establecer la conexión y ejecutar consultas SQL.
        // Llena la lista con los resultados y devuelve la lista.
        String sql = "SELECT ID, Nombre, Dirección, Teléfono, Email FROM clientes";

        try (PreparedStatement ps = CN.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Clientes cliente = new Clientes();
                cliente.setID(rs.getInt("ID"));
                cliente.setNombre(rs.getString("Nombre"));
                cliente.setDirección(rs.getString("Dirección"));
                cliente.setTeléfono(rs.getString("Teléfono"));
                cliente.setEmail(rs.getString("Email"));

                lista.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean agregarCliente(Clientes cliente) {
        String sql = "INSERT INTO clientes (Nombre, Dirección, Teléfono, Email) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = CN.getConnection().prepareStatement(sql);
            // Configura los parámetros del PreparedStatement con los datos del cliente.
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDirección());
            ps.setString(3, cliente.getTeléfono());
            ps.setString(4, cliente.getEmail());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al agregar cliente: " + e.getMessage());
        }
        return false;
    }

    public Clientes obtenerClienteByID(int idCliente) {
        String sql = "SELECT * FROM clientes WHERE ID = ?";
        Clientes cliente = null;

        try (PreparedStatement ps = CN.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new Clientes();
                    cliente.setID(rs.getInt("ID"));
                    cliente.setNombre(rs.getString("Nombre"));
                    cliente.setDirección(rs.getString("Dirección"));
                    cliente.setTeléfono(rs.getString("Teléfono"));
                    cliente.setEmail(rs.getString("Email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    public boolean actualizarCliente(Clientes cliente) {
        String sql = "UPDATE clientes SET Nombre = ?, Dirección = ?, Teléfono = ?, Email = ? WHERE Id = ?";

        try (PreparedStatement ps = CN.getConnection().prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDirección());
            ps.setString(3, cliente.getTeléfono());
            ps.setString(4, cliente.getEmail());
            ps.setInt(5, cliente.getID());

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminarClientes(int idcliente) {
        String sql = "DELETE FROM clientes WHERE ID = ?";

        try (PreparedStatement ps = CN.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idcliente);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

}
