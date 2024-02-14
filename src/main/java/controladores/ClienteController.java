/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import modelosDAO.ClienteDAO;

/**
 *
 * @author Esau
 */
@WebServlet(name = "ClienteController", urlPatterns = {"/ClienteController", "/Grafico2", "/clientesEliminar","/GuardarCliente", "/CargarClientes", "/clientesEditar", "/AgregarClientes", "/clienteEditado"})
public class ClienteController extends HttpServlet {

    private ClienteDAO clienteDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            clienteDAO = new ClienteDAO();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ClienteController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClienteController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession();
        switch (action) {
            case "/CargarClientes":
                //System.out.println("si llegua aqui veijoonnnnnn");
                mostrarDatosClientes(request, response);
                break;
            case "/AgregarClientes":
                //System.out.println("si llegua aqui veijoonnnnnn si");
                mostrarFormAgregar(request, response);
                break;
            case "/clientesEditar":
                //System.out.println("si llegua aqui veijoonnnnnn si");
                mostrarFormEditar(request, response);
                break;
            case "/clientesEliminar":
                //System.out.println("si llegua aqui veijoonnnnnn si");
                clientesEliminar(request, response);
                break;   
            case "/Grafico2":
                //System.out.println("si llegua aqui veijoonnnnnn si");
                Grafico2(request, response);
                break;    
                
                
                

            default:
                // Lógica para otras rutas si es necesario
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        HttpSession session = request.getSession();
        switch (action) {
            case "/GuardarCliente":
                //System.out.println("si llegua aqui veijoonnnnnn");
                GuardarCliente(request, response);
                break;
                
             case "/clienteEditado":
                //System.out.println("si llegua aqui veijoonnnnnn");
                clienteEditado(request, response);
                break;
   

            default:
                // Lógica para otras rutas si es necesario
                break;
        }
    }
    
    private void Grafico2(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
         HttpSession session = request.getSession();

        try {
            // Lógica para obtener los datos del producto desde la base de datos
            List<Clientes> listaEncuestas = clienteDAO.consultagraficos();

            if (listaEncuestas != null) {
                System.out.println("LLegua hasta antes de enviar los datos a la vista ");
                // Crear una instancia de Gson
                Gson gson = new Gson();

                // Convertir la lista de encuestas a JSON
                String datosJSON = gson.toJson(listaEncuestas);

                // Pasar los datos JSON a la vista
                request.setAttribute("datosJSON", datosJSON);
                
                

                RequestDispatcher dispatcher = request.getRequestDispatcher("graficos/grafico2.jsp");
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

    private void mostrarDatosClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<Clientes> listaClientes = clienteDAO.consultaGeneral();
        session.setAttribute("clientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("clientes/index.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormAgregar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("clientes/crear.jsp");
        dispatcher.forward(request, response);

    }

    private void GuardarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");

        Clientes nuevoCliente = new Clientes(nombre, direccion, telefono, email);
        boolean exito = clienteDAO.agregarCliente(nuevoCliente);

        if (exito) {
            List<Clientes> listaClientes = clienteDAO.consultaGeneral();
            session.setAttribute("clientes", listaClientes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("clientes/index.jsp");
            dispatcher.forward(request, response);
        } else {
            session.setAttribute("errorMessage", "Error al registrar al usuario");

            // Manejo de errores (puedes personalizar esto según tus necesidades)
            response.sendRedirect("registro?error=true");
        }
    }

    private void mostrarFormEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String idCliente = request.getParameter("id");

        // Verificar si el ID es válido (puedes agregar validaciones adicionales)
        if (idCliente != null && !idCliente.isEmpty()) {
            try {
                int idclient = Integer.parseInt(idCliente);

                // Lógica para obtener los datos del producto desde la base de datos
                Clientes clientes = clienteDAO.obtenerClienteByID(idclient);

                if (clientes != null) {
                    // Pasar los datos del producto a la vista de edición
                    request.setAttribute("cliente", clientes);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("clientes/editar.jsp");
                    dispatcher.forward(request, response);
                } else {
                    // Manejo de error si el producto no existe
                    response.sendRedirect("clientes?error=true");
                }
            } catch (NumberFormatException e) {
                // Manejo de error si el ID no es un número válido
                session.setAttribute("errorMessage", "Error al cargar editar producto");
                response.sendRedirect("clientes?error=true");
            }
        } else {
            session.setAttribute("errorMessage", "Error al cargar editar producto");
            // Manejo de error si no se proporciona un ID válido en la ruta
            response.sendRedirect("clientes?error=true");
        }
    }
    
    private void clienteEditado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
     
       
            
            String nombre = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String email = request.getParameter("email");
            
            String idCliente = request.getParameter("id"); 

            Clientes clienteEdit = new Clientes(nombre, direccion, telefono, email);
            clienteEdit.setID(Integer.parseInt(idCliente));

            // Lógica para guardar los cambios en la base de datos
            boolean exito = clienteDAO.actualizarCliente(clienteEdit);

            
            if (exito) {
                // Redirigir a la página de productos después de la edición exitosa con un mensaje de éxito
                 List<Clientes> listaClientes = clienteDAO.consultaGeneral();
                 session.setAttribute("clientes", listaClientes);
                 session.setAttribute("successMessage", "Cliente editado con éxito");
                RequestDispatcher dispatcher = request.getRequestDispatcher("clientes/index.jsp");
                dispatcher.forward(request, response);
            } else {
                
                List<Clientes> listaClientes = clienteDAO.consultaGeneral();
                session.setAttribute("clientes", listaClientes);
                session.setAttribute("errorMessage", "Error al editar el Cliente");
                RequestDispatcher dispatcher = request.getRequestDispatcher("clientes/index.jsp");
                dispatcher.forward(request, response);
              
            }
    }
    
    
     private void clientesEliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
    
            // Obtener el ID del producto a eliminar desde la solicitud
            String idcliente = request.getParameter("id");

            if (idcliente != null && !idcliente.isEmpty()) {
                try {
                    int idCliente = Integer.parseInt(idcliente);

                    // Llamar al método en el DAO para eliminar el producto
                    boolean exito = clienteDAO.eliminarClientes(idCliente);

                    if (exito) {
                        // Redirigir a la página de productos después de la edición exitosa con un mensaje de éxito
                         List<Clientes> listaClientes = clienteDAO.consultaGeneral();
                         session.setAttribute("clientes", listaClientes);
                         session.setAttribute("successMessage", "Cliente Eliminado con éxito");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("clientes/index.jsp");
                        dispatcher.forward(request, response);
                    } else {

                        List<Clientes> listaClientes = clienteDAO.consultaGeneral();
                        session.setAttribute("clientes", listaClientes);
                        session.setAttribute("errorMessage", "Error al Eliminar el Cliente");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("clientes/index.jsp");
                        dispatcher.forward(request, response);

                    }
                } catch (NumberFormatException e) {
                    // Manejar el caso en el que el parámetro de ID no es un número válido con un mensaje de error
                    session.setAttribute("errorMessage", "Error al eliminar el cliente");
                    response.sendRedirect("clientes");
                }
            } else {
                // Manejar el caso en el que no se proporcionó un ID válido con un mensaje de error
                session.setAttribute("errorMessage", "Error al eliminar el cliente");
                response.sendRedirect("clientes");
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

}
