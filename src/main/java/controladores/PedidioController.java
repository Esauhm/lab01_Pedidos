/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

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
import modelosDAO.PedidoDAO;

/**
 *
 * @author Esau
 */
@WebServlet(name = "PedidioController", urlPatterns = {"/PedidioController", "/pedido","/crear","/crearPedidos","/editar"})
public class PedidioController extends HttpServlet {
    
    private PedidoDAO pedidoDao;
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
                case "/crear":{
                    formPedidos(request,response);
            }
            break;
            case "/editar":{
                    //formEditar(request,response);
            }
            break;
                    
                  
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
                //editarPedido(request, response);
                break;
            default:
                // Lógica para otras rutas si es necesario
                break;
        }
    }
    
    
    private void crearPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {

        String cliente = request.getParameter("cliente");

        if (cliente != null) {
            // Obtener los datos del formulario HTML
            System.out.println("entro al controlador de categoria");
            String fechaPedido = request.getParameter("fechaPedido");
            String estado = request.getParameter("estado");
            // Crear un objeto Proveedor con los datos del formulario
            Pedidos nvPedido = new Pedidos();
            
            Date fecha = (Date) formatoFecha.parse(fechaPedido);
              java.sql.Date fechaFormat = new java.sql.Date(fecha.getTime());
           
            
              nvPedido.setID_Cliente(Integer.parseInt(cliente));
            nvPedido.setFecha(fechaFormat);            
            nvPedido.setEstado(estado);
            
            PedidoDAO pedidoDao = new PedidoDAO();
            
            int exito = pedidoDao.addPedido(nvPedido);            
            if (exito >= 0) {
              response.sendRedirect("pedidos");
            }
            
      //  } else {
        //    response.sendRedirect("index.jsp");
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
//             List<Clientes> clientes = ClientesDAO.consultarClientes();
//            request.setAttribute("categorias", clientes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("pedidos/crear.jsp");
            dispatcher.forward(request, response);       
    
    }
       
       
}
