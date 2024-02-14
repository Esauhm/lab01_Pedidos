<%-- Document : crear Created on : 02-12-2024, 10:53:14 PM Author : Juanjo --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>

    <%@ include file="/Layout/header.jsp" %>
        <!doctype html>
        <html lang="en">

        <head>
            <title>Crear pedido</title>
            <!-- Required meta tags -->
            <meta charset="utf-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

    
             

        </head>
        <style>
            /* Agrega estilos CSS aquí */
            form {
                max-width: 400px;
                margin: 0 auto;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }

            label {
                font-weight: bold;
            }

            input[type="text"],
            input[type="number"],input[type="date"],select
             {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            input[type="checkbox"] {
                margin-top: 5px;
            }

            input[type="submit"] {
                display: block;
                width: 100%;
                padding: 10px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                text-align: center;
                text-decoration: none;
                font-weight: bold;
            }
        </style>

        <body>

            <main>

                <form action="editarPedido" class="mt-5" method="post">
                    <h1>Crear Pedido</h1>
                <input type="hidden" name="id" value="${pedidos.getID()}">
                    <label for="cliente">Cliente:</label>
                    
                    <select id="cliente" name="cliente">
                        
                        <c:forEach var="cliente" items="${clientes}">
                            <option value="${cliente.getID()}" ${pedidos.getID_Cliente() == cliente.getID() ? 'selected' : ''}>${cliente.getNombre()}</option>
                        </c:forEach>
                    </select>

                    <label for="fechaPedido">Fecha del pedido:</label>
                    <input type="date" id="fechaPedido" name="fechaPedido" value="${pedidos.getFecha()}" required><br><br>

                    <label for="totalPedido">Total:</label>
                    <input type="number" id="totalPedido" name="totalPedido"  required value="${pedidos.getTotal()}" ><br><br>
                  
                  
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="estadoPedido">Estados</label>
                        <select class="form-select" id="estadoPedido" name="estadoPedido">
                             <option value="1" ${pedidos.getEstado() == '1' ? 'selected' : ''}>Pendiente</option>
                            <option value="2" ${pedidos.getEstado() == '2' ? 'selected' : ''}>En camino</option>
                            <option value="3" ${pedidos.getEstado() == '3' ? 'selected' : ''}>Entregado</option>
                        </select>
                      </div>
                    
                    <input type="submit" value="Guardar Pedido">
                </form>

            </main>
            <footer>
                <!-- place footer here -->
            </footer>
            <!-- Bootstrap JavaScript Libraries -->
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
                crossorigin="anonymous"></script>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
                crossorigin="anonymous"></script>
        </body>
         <%@ include file="/Layout/footer.jsp" %>

        </html>
        
       