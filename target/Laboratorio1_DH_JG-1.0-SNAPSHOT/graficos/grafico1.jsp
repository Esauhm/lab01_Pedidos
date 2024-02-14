
<%@page import="modelos.Pedidos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<title>Meses con mayor pedidos</title>
<%@ include file="/Layout/header.jsp" %>


<style>
    /* Estilo para la tabla de productos */
    .table {
        width: 100%;
        border-collapse: collapse;
    }

    .container{
        max-width: 85%;
    }
    .table th, .table td {
        border: 1px solid #ccc;
        padding: 8px;
        text-align: left;
    }

    .table th {
        background-color: #f2f2f2;
    }

    .btn {
        padding: 5px 10px;
        text-decoration: none;
        border: none;
        cursor: pointer;
    }

    .btn-primary {
        background-color: #007bff;
        color: #fff;
    }

    .btn-primary:hover {
        background-color: #0056b3;
    }

    .btn-danger {
        background-color: #dc3545;
        color: #fff;
    }

    .btn-danger:hover {
        background-color: #c82333;
    }

    /* Agregar sombreado al hover de los cards */
    .card:hover {
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.2); /* Cambia los valores según tus preferencias */
        transition: box-shadow 0.3s ease-in-out;
    }

    /* Cambiar el color de fondo del card al hacer hover */
    .card:hover {
        background-color: #f8f9fa; /* Cambia el color de fondo según tus preferencias */
        transition: background-color 0.3s ease-in-out;
    }

    .card-header {
        background: url("https://img.freepik.com/vector-premium/diseno-fondo-holograma-color-pastel-abstracto-horizontal_29865-3302.jpg") center/cover no-repeat;
        /*    color: white;
            text-align: center;*/
        padding: 22px;
    }
</style>

     <div class="container card mb-4">
        <div class="card-header justify-content-center d-flex">
            <h2 class="mb-4 mt-5">Meses con mayor pedidos</h2>
        </div>
        <div class="card-body">
            <!-- Añadimos el filtro para seleccionar años -->
            <form action="filtrar" class="row" method="post">
                <div class="form-group col">
                    <label for="anio">Seleccionar año:</label>
                    <select class="form-control" id="anio" name="anio">
                        <c:forEach var="year" begin="2019" end="2024">
                            <option value="${year}" ${anioSelect == year ? 'selected' : ''}>${year}</option>
                        </c:forEach>
                    </select>              
                </div>

                <div class="col">
                    <input type="submit" class="btn-primary btn mt-3" value="Filtrar">
                </div>
            </form>
           
            <div class="row">
                <!-- Gráfico de barras -->
                <div class="col-4">
                    <div class="text-center" style="width: 100%; height: 350px; text-align: center;">
                        <h3>Distribución de Sexo</h3>
                        <canvas id="graficoBarras"></canvas>
                    </div>
                </div>
                <!-- Gráfico de pastel -->
                <div class="col-4 mx-auto">
                    <div class="text-center" style="width: 70%; height: 350px; text-align: center; margin-left: 100px;">
                        <h3>Distribución de Sexo</h3>
                        <canvas id="graficoPastel"></canvas>
                    </div>
                </div>
                <!-- Gráfico de línea -->
                <div class="col-4">
                    <div class="text-center" style="width: 100%; height: 350px; text-align: center;">
                        <h3>Distribución de Sexo</h3>
                        <canvas id="graficoLinea"></canvas>
                    </div>
                </div>
            </div>
        </div>
       
    </div>
 <div class="container">
    <table id="miTabla" class="table">
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Total de Pedidos</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${datos}" var="pedido">
                <tr>
                    <td>${pedido.getMes()}</td>
                    <td>${pedido.getCantidad()}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
 </div>
    
    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js"></script>
    
    <script>
        
       
        
        // Obtener los datos JSON del atributo de solicitud
        var jsonPedidos = ${encuestasJSON};
        
        // Parsear los datos JSON
       // Preparar datos para el gráfico
        var labels = jsonPedidos.map(function(pedido) {
            return pedido.mes;
        });
        var valores = jsonPedidos.map(function(pedido) {
            return pedido.cantidad;
        });
        

        // Crear gráfico
        var ctx = document.getElementById('graficoBarras').getContext('2d');
      var graficoClientes = new Chart(ctx, {
        type: 'bar',
                data: {
                labels: labels,
                        datasets: [{
                        label: 'Total de Pedidos',
                                data: valores,
                                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                                borderColor: 'rgba(75, 192, 192, 1)',
                                borderWidth: 1
                        }]
                },
                options: {
                scales: {
                y: {
                beginAtZero: true
                }
                }
                }
        });
        
        
         var ctxPastel = document.getElementById('graficoPastel').getContext('2d');
        var graficoPastel = new Chart(ctxPastel, {
           type: 'pie',
                data: {
                labels: labels,
                        datasets: [{
                        label: 'Total de Pedidos',
                                data: valores,
                                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                                borderColor: 'rgba(75, 192, 192, 1)',
                                borderWidth: 1
                        }]
                },
                options: {
                scales: {
                y: {
                beginAtZero: true
                }
                }
                }
        });
        // Crear un gráfico de líneas para la distribución de sexos
        var ctxLinea = document.getElementById('graficoLinea').getContext('2d');
        var graficoLinea = new Chart(ctxLinea, {
            type: 'line',
                data: {
                labels: labels,
                        datasets: [{
                        label: 'Total de Pedidos',
                                data: valores,
                                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                                borderColor: 'rgba(75, 192, 192, 1)',
                                borderWidth: 1
                        }]
                },
                options: {
                scales: {
                y: {
                beginAtZero: true
                }
                }
                }
        });
    </script>
</body>
  <%@ include file="/Layout/footer.jsp" %>
</html>
