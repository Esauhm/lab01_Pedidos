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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelos.Pedidos;

/**
 *
 * @author Esau
 */
public class PedidoDAO {
    private cn CN;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public PedidoDAO() throws SQLException {
        try {
            CN = new cn();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public List<Pedidos> consultarPedidos(int idCLiente) throws SQLException{
     List<Pedidos> lstPedidos = new ArrayList<>();
     String sql = "SELECT * FROM pedidos where id_Cliente = ?";
     
     ps = CN.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
     ps.setInt(idCLiente, idCLiente);
     
     try (ResultSet rs = ps.executeQuery()){
          while (rs.next()) {
                Pedidos pedido = new Pedidos();
                pedido.setID(rs.getInt("id"));
                pedido.setID_Cliente(rs.getInt("id_cliente"));
                pedido.setFecha(rs.getDate("fecha").toString());
                pedido.setTotal(rs.getDouble("total"));
                pedido.setEstado(rs.getString("estado"));

                lstPedidos.add(pedido);
            }
     }catch(SQLException e){
         e.printStackTrace();
         System.out.println("Error en "+e.getMessage());
     }
     return lstPedidos;
    }
    
   
    
      public int addPedido(Pedidos pedido) {
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
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al agregar pedido: " + e.getMessage()); // Imprime el mensaje de error en la consola

        }

        // se retorna valor negativo por causa error
        return -1;
    }
      
     public Pedidos obtenerPedidoPorId(int id) {
        String sql = "SELECT * FROM pedidos WHERE idPedido = ?";

        try  {
            ps.setInt(1, id);
            ps = CN.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Pedidos pedido = new Pedidos();
                    pedido.setID(rs.getInt("id"));
                    pedido.setID_Cliente(rs.getInt("id_cliente"));
                    pedido.setFecha(rs.getDate("fecha"));
                    pedido.setTotal(rs.getDouble("total"));
                    pedido.setEstado(rs.getString("estado"));

                    return pedido;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error en pedido"+e.getMessage());
        }

        return null; // Devuelve null si no se encuentra un pedido 
    }
    
     
}
