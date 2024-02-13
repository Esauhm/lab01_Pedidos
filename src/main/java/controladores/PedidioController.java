/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Clientes;
import modelos.Pedidos;
import modelosDAO.ClienteDAO;
import modelosDAO.PedidoDAO;

/**
 *
 * @author Esau
 */
@WebServlet(name = "PedidioController", urlPatterns = {"/PedidioController", "/pedido","/crearPedidos","/editarPedido","/eliminarPedido","/graficoPedido"})
public class PedidioController extends HttpServlet {
    
    private PedidoDAO pedidoDao;
    
    private ClienteDAO clienteDao;
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet PedidioController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet PedidioController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
    
        @Override
    public void init() throws ServletException {
        super.init();
        try {
            pedidoDao = new PedidoDAO();
            clienteDao = new ClienteDAO();
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            String action = request.getServletPath();
            
            switch (action){
                case "/crearPedidos":{
                    formPedidos(request,response);
            }
            break;
            case "/editarPedido":{
                    frmEditar(request,response);
            }
            break;
            case "/pedido":{
                try {
                    showPedidos(request,response);
                } catch (SQLException ex) {
                    Logger.getLogger(PedidioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
              case "/eliminarPedido":
           {
               try {
                   dropPedido(request, response);
               } catch (SQLException ex) {
                   Logger.getLogger(PedidioController.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
                break;     
              case "/graficoPedido":
                  graficos(request,response);
            
            }
     //   processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
       String action = request.getServletPath();
       switch (action) {
            case "/crearPedidos": {
               try {
                   crearPedido(request, response);
               } catch (SQLException ex) {
                   Logger.getLogger(PedidioController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (ParseException ex) {
                   Logger.getLogger(PedidioController.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
            break;
            case "/editarPedido":
           {
               try {
                   pedidoEdit(request, response);
               } catch (SQLException ex) {
                   Logger.getLogger(PedidioController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (ParseException ex) {
                   Logger.getLogger(PedidioController.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
                break;
        
            default:
                // Lógica para otras rutas si es necesario
                break;
        }
    }
    
    
    private void showPedidos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        
        List<Pedidos> lstPedidos = pedidoDao.consultarPedidos();
        session.setAttribute("lstPedidos", lstPedidos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pedidos/index.jsp");
        dispatcher.forward(request, response);
    }

    
    private void crearPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {

        String cliente = request.getParameter("cliente");
        HttpSession session = request.getSession();
        if (cliente != null) {
            // Obtener los datos del formulario HTML
            String fechaPedido = request.getParameter("fechaPedido");
            double total = Double.parseDouble( request.getParameter("totalPedido"));
            String estado = request.getParameter("estadoPedido");
            // Crear un objeto Proveedor con los datos del formulario
            Pedidos nvPedido = new Pedidos();
              Date fecha = Date.valueOf(fechaPedido);
            System.out.println(fecha);
          System.out.println("estado: "+estado);
          System.out.println("total: "+total);
            
            nvPedido.setID_Cliente(Integer.parseInt(cliente));
            nvPedido.setFecha(fecha);            
            nvPedido.setEstado(estado);
            nvPedido.setTotal(total);
            
            PedidoDAO pedidoDao = new PedidoDAO();
            
            boolean exito = pedidoDao.addPedido(nvPedido); 
            
            if (exito) {
             List<Pedidos> lstPedidos = pedidoDao.consultarPedidos();
            session.setAttribute("lstPedidos", lstPedidos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("pedidos/index.jsp");
            dispatcher.forward(request, response);
            }else{
            session.setAttribute("errorMessage", "Error al registrar el pedido");

            // Manejo de errores (puedes personalizar esto según tus necesidades)
            response.sendRedirect("pedido?error=true");
            }
        
        }
    
    }
        
    private void frmEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
       
        String idPedido = request.getParameter("id");

        // Verificar si el ID es válido (puedes agregar validaciones adicionales)
        if (idPedido != null && !idPedido.isEmpty()) {
            try {
                int pedido = Integer.parseInt(idPedido);
  
                // Lógica para obtener los datos del producto desde la base de datos
                Pedidos pedidos = pedidoDao.obtenerPedidoPorId(pedido);

                if (pedidos != null) {
                    // Pasar los datos del producto a la vista de edición
                    List<Clientes> clientes = clienteDao.consultaGeneral();            
                    request.setAttribute("clientes", clientes);
                    request.setAttribute("pedidos", pedidos);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("pedidos/editar.jsp");
                    dispatcher.forward(request, response);
                } else {
                    // Manejo de error si el producto no existe
                    response.sendRedirect("pedidos?error=true");
                }
            } catch (NumberFormatException e) {
                // Manejo de error si el ID no es un número válido
                session.setAttribute("errorMessage", "Error al cargar editar el pedido");
                response.sendRedirect("pedidos?error=true");
            }
        } else {
            session.setAttribute("errorMessage", "Error al cargar editar pedido");
            // Manejo de error si no se proporciona un ID válido en la ruta
            response.sendRedirect("pedidos?error=true");
        }
    }
    
    private void pedidoEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {

        HttpSession session = request.getSession();
     
            // Obtener los datos del formulario HTML
             String fechaPedido = request.getParameter("fechaPedido");
            double total = Double.parseDouble( request.getParameter("totalPedido"));
            String estado = request.getParameter("estadoPedido");
            String cliente = request.getParameter("cliente");
            String id = request.getParameter("id");
            // Crear un objeto Proveedor con los datos del formulario
            
            Pedidos nvPedidoE = new Pedidos();
              Date fecha = Date.valueOf(fechaPedido);
            
            System.out.println("estado1: "+ estado);
            nvPedidoE.setID_Cliente(Integer.parseInt(cliente));
            nvPedidoE.setFecha(fecha);            
            nvPedidoE.setEstado(estado);
            nvPedidoE.setTotal(total);
            nvPedidoE.setID(Integer.parseInt(id));
            
            System.out.println("estado2: "+ nvPedidoE.getEstado());
            
            PedidoDAO pedidoDao = new PedidoDAO();
            
            boolean exito = pedidoDao.updatePedido(nvPedidoE);            
            if (exito) {
             List<Pedidos> lstPedidos = pedidoDao.consultarPedidos();
            session.setAttribute("lstPedidos", lstPedidos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("pedidos/index.jsp");
            dispatcher.forward(request, response);
            }else{
            session.setAttribute("errorMessage", "Error al registrar el pedido");

            // Manejo de errores (puedes personalizar esto según tus necesidades)
            response.sendRedirect("pedido?error=true");
            }
        
        
    
    }
    
    
   private void dropPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
              HttpSession session = request.getSession();
    
            // Obtener el ID del producto a eliminar desde la solicitud
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                try {
                    int idPedido = Integer.parseInt(id);

                    // Llamar al método en el DAO para eliminar el producto
                    boolean exito = pedidoDao.deletePedido(idPedido);

                    if (exito) {
                        System.out.println("exito");
                        // Redirigir a la página de productos después de la edición exitosa con un mensaje de éxito
                         List<Pedidos> lstPedidos = pedidoDao.consultarPedidos();
                         session.setAttribute("lstPedidos", lstPedidos);
                         session.setAttribute("successMessage", "Pedido eliminado con éxito");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("pedidos/index.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        System.out.println("error");

                        List<Pedidos> lstPedidos = pedidoDao.consultarPedidos();
                        session.setAttribute("lstPedidos", lstPedidos);
                        session.setAttribute("errorMessage", "Error al Eliminar el pedido");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("pedidos/index.jsp");
                        dispatcher.forward(request, response);

                    }
                } catch (NumberFormatException e) {
                    // Manejar el caso en el que el parámetro de ID no es un número válido con un mensaje de error
                    session.setAttribute("errorMessage", "Error al eliminar el pedidos");
                    response.sendRedirect("clientes");
                }
            } else {
                // Manejar el caso en el que no se proporcionó un ID válido con un mensaje de error
                session.setAttribute("errorMessage", "Error al eliminar el pedido");
                response.sendRedirect("lstPedidos");
            }
        
    }
   
   private void graficos(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
         HttpSession session = request.getSession();
        //String anio = request.getParameter("anio");
        
        
        
        try {
            // Lógica para obtener los datos del producto desde la base de datos
            List<Pedidos> lstPedidos = pedidoDao.graficaPedidos("2024");

            if (lstPedidos != null) {
                // Crear una instancia de Gson
                Gson gson = new Gson();

                // Convertir la lista de encuestas a JSON
                String encuestasJSON = gson.toJson(lstPedidos);

                // Pasar los datos JSON a la vista
                request.setAttribute("encuestasJSON", encuestasJSON);

                RequestDispatcher dispatcher = request.getRequestDispatcher("graficos/grafico1.jsp");
                dispatcher.forward(request, response);
            } else {
                session.setAttribute("errorMessage", "Llene antes su encuesta");
                response.sendRedirect("clientes?error=true");
            }

        } catch (NumberFormatException e) {
            // Manejo de error si el ID no es un número válido
            session.setAttribute("errorMessage", "1Error al cargar editar producto");
            response.sendRedirect("clientes?error=true");
        }
    }  
     
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

       private void formPedidos(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
             List<Clientes> clientes = clienteDao.consultaGeneral();
            
            request.setAttribute("clientes", clientes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("pedidos/crear.jsp");
            dispatcher.forward(request, response);       
    
    }
       
       
}
