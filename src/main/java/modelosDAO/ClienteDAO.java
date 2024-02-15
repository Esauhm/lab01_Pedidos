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
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Clientes;

/**
 *
 * @author Esau
 */
public class ClienteDAO {

   
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public ClienteDAO()  {
       
    }
    
     public List<Clientes> consultagraficos()  {
        try {
            cn CN = new cn();
            
            System.out.println("LLegua hasta la consultaaa");
            List<Clientes> listaClientes = new ArrayList<>();
            
            // Agrega aquí la lógica para obtener los primeros 10 clientes con más pedidos.
            String sql = "SELECT c.nombre AS NombreCliente, COUNT(p.id_cliente) AS TotalPedidos " +
                    "FROM clientes c " +
                    "JOIN pedidos p ON c.ID = p.ID_Cliente " +
                    "GROUP BY c.ID " +
                    "ORDER BY TotalPedidos DESC " +
                    "LIMIT 10";
            
            try (PreparedStatement ps = CN.getConnection().prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                
                while (rs.next()) {
                    Clientes cliente = new Clientes();
                    cliente.setNombre(rs.getString("NombreCliente"));
                    cliente.setTotalPedidos(rs.getInt("TotalPedidos"));
                    
                    // Imprimir información en la consola
                    System.out.println("Nombre del Cliente: " + cliente.getNombre() + ", Total de Pedidos: " + cliente.getTotalPedidos());
                    
                    listaClientes.add(cliente);
                }
                CN.cerrarConexion();
            } catch (SQLException e) {
                try {
                    CN.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                e.printStackTrace();
            }
            return listaClientes;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Clientes> consultaGeneral()  {
        try {
            cn CN = new cn();
            
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
                CN.cerrarConexion();
            } catch (SQLException e) {
                try {
                    CN.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                e.printStackTrace();
            }
            return lista;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean agregarCliente(Clientes cliente)  {
        try {
            cn CN = new cn();
            
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
                CN.cerrarConexion();
            } catch (SQLException e) {
                try {
                    CN.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.err.println("Error al agregar cliente: " + e.getMessage());
            }
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Clientes obtenerClienteByID(int idCliente)  {
        try {
            cn CN = new cn();
            
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
                CN.cerrarConexion();
            } catch (SQLException e) {
                try {
                    CN.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                e.printStackTrace();
            }

            return cliente;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean actualizarCliente(Clientes cliente)  {
        try {
            cn CN = new cn();
            
            String sql = "UPDATE clientes SET Nombre = ?, Dirección = ?, Teléfono = ?, Email = ? WHERE Id = ?";
            
            try (PreparedStatement ps = CN.getConnection().prepareStatement(sql)) {
                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getDirección());
                ps.setString(3, cliente.getTeléfono());
                ps.setString(4, cliente.getEmail());
                ps.setInt(5, cliente.getID());
                
                int filasAfectadas = ps.executeUpdate();
                CN.cerrarConexion();
                return filasAfectadas > 0;
            } catch (SQLException e) {
                try {
                    CN.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                e.printStackTrace();
                return false;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean eliminarClientes(int idcliente)  {
        try {
            cn CN = new cn();
            
            String sql = "DELETE FROM clientes WHERE ID = ?";
            
            try (PreparedStatement ps = CN.getConnection().prepareStatement(sql)) {
                ps.setInt(1, idcliente);
                
                int filasAfectadas = ps.executeUpdate();
                CN.cerrarConexion();
                return filasAfectadas > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    CN.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    

}
