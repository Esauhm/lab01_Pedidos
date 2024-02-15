/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelosDAO;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import db.cn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Pedidos;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Esau
 */
public class PedidoDAO {
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public PedidoDAO()  {
    }
    
    
   
    
    public List<Pedidos> consultarPedidos() throws SQLException {
     try {
         cn CN = new cn();
         
         List<Pedidos> lstPedidos = new ArrayList<>();
         String sql = "SELECT id,id_cliente,fecha,total,estado FROM pedidos ";
         
         ps = CN.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
         
         try (ResultSet rs = ps.executeQuery()){
             while (rs.next()) {
                 Pedidos pedido = new Pedidos();
                 pedido.setID(rs.getInt("id"));
                 pedido.setID_Cliente(rs.getInt("id_cliente"));
                 pedido.setFecha(rs.getDate("fecha"));
                 pedido.setTotal(rs.getDouble("total"));
                 pedido.setEstado(rs.getString("estado"));
                 
                 lstPedidos.add(pedido);
             }
             CN.cerrarConexion();
         }catch(SQLException e){
             e.printStackTrace();
             System.out.println("Error en "+e.getMessage());
             CN.cerrarConexion();
         }
         return lstPedidos;
     }catch(ClassNotFoundException ex){
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
     }
        return null;
    }
    
      public boolean addPedido(Pedidos pedido)  {
        try {
            cn CN = new cn();
            
            String sql = "INSERT INTO pedidos (id_cliente, fecha, total,estado) VALUES (?, ?, ?,?)";
            
            try {
                
                // Indica que deseas obtener el ID generado automáticamente
                ps = CN.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                
                ps.setInt(1, pedido.getID_Cliente());
                ps.setDate(2, new java.sql.Date(pedido.getFecha().getTime()));
                ps.setDouble(3, pedido.getTotal());
                ps.setString(4, pedido.getEstado());
                
                int filasAfectadas = ps.executeUpdate();
                
                if (filasAfectadas > 0) {
                    ///obtiene id generado
                    ResultSet generatedKeys = ps.getGeneratedKeys();
                    
                    if (generatedKeys.next()) {
                        CN.cerrarConexion();
                        return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al agregar pedido: " + e.getMessage()); // Imprime el mensaje de error en la consola
                try {
                    CN.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // se retorna valor negativo por causa error
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
      
     public Pedidos obtenerPedidoPorId(int id)  {
        try{
            cn CN = new cn();
            
            String sql = "SELECT * FROM pedidos WHERE id = ?";
            Pedidos pedido = null;
            
            try(PreparedStatement ps = CN.getConnection().prepareStatement(sql))  {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        pedido = new Pedidos();
                        pedido.setID(rs.getInt("id"));
                        pedido.setID_Cliente(rs.getInt("id_cliente"));
                        pedido.setFecha(rs.getDate("fecha"));
                        pedido.setTotal(rs.getDouble("total"));
                        pedido.setEstado(rs.getString("estado"));
                        
                        return pedido;
                    }
                    CN.cerrarConexion();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    CN.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("error en pedido"+e.getMessage());
            }
            
            return null; // Devuelve null si no se encuentra un pedido
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
     
    
      public boolean updatePedido(Pedidos pedido) {

        try {
            
            cn CN = new cn();
            
            String sql = "update pedidos set id_cliente = ?, fecha = ?, total = ?, estado = ? where id = ?";
            
            try (PreparedStatement ps = CN.getConnection().prepareStatement(sql)) {
                ps.setInt(1, pedido.getID_Cliente());
                ps.setDate(2, pedido.getFecha());
                ps.setDouble(3, pedido.getTotal());
                ps.setString(4, pedido.getEstado());
                ps.setInt(5, pedido.getID());
                
                int filasAfectadas = ps.executeUpdate();
                CN.cerrarConexion();
                return filasAfectadas > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    CN.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
      
      public boolean deletePedido(int id)  {
        try {
            cn CN = new cn();
            
            String sql = "DELETE FROM pedidos WHERE ID = ?";
            
            try (PreparedStatement ps = CN.getConnection().prepareStatement(sql)) {
                ps.setInt(1, id);
                
                int filasAfectadas = ps.executeUpdate();
                CN.cerrarConexion();
                return filasAfectadas > 0;
                
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    CN.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
      
      
  public List<Pedidos> graficaPedidos(String anio)  {
        try {
            cn CN = new cn();
            
            ArrayList<Pedidos> lista = new ArrayList<>();
            String sql = "Select monthname(fecha) as mes,count(ID) as cantidad from pedidos p\n" +
                    "where year(p.fecha) = ? \n" +
                    "group by month(fecha) order by cantidad desc\n" +
                    "limit 3;";
            
            try (PreparedStatement ps = CN.getConnection().prepareStatement(sql)) {
                ps.setString(1, anio);
                
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Pedidos pedido = new Pedidos();
                        pedido.setMes(rs.getString("mes"));
                        pedido.setCantidad(rs.getInt("cantidad"));
                        pedido.setAnio(anio);
                        lista.add(pedido);
                    }
                }
                CN.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    CN.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            return lista;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
