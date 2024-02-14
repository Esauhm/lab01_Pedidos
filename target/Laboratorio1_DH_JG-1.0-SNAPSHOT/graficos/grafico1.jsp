
<%@page import="modelos.Pedidos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<title>Meses con mayor pedidos</title>
<%@ include file="/Layout/header.jsp" %>

<style>
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
                    <h3>Meses con más pedidos</h3>
                    <canvas id="graficoBarras"></canvas>
                </div>
            </div>
            <!-- Gráfico de pastel -->
            <div class="col-4 mx-auto">
                <div class="text-center" style="width: 70%; height: 350px; text-align: center; margin-left: 100px;">
                    <h3>Meses con más pedidos</h3>
                    <canvas id="graficoPastel"></canvas>
                </div>
            </div>
            <!-- Gráfico de línea -->
            <div class="col-4">
                <div class="text-center" style="width: 100%; height: 350px; text-align: center;">
                    <h3>Meses con más pedidos</h3>
                    <canvas id="graficoLinea"></canvas>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js"></script>

<script>



    // Obtener los datos JSON del atributo de solicitud
    var jsonPedidos = ${encuestasJSON};

    // Parsear los datos JSON
    // Preparar datos para el gráfico
    var labels = jsonPedidos.map(function (pedido) {
        return pedido.mes;
    });
    var valores = jsonPedidos.map(function (pedido) {
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
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.5)',
                        'rgba(54, 162, 235, 0.5)',
                        'rgba(255, 206, 86, 0.5)',
                        'rgba(75, 192, 192, 0.5)',
                        'rgba(153, 102, 255, 0.5)',
                        'rgba(255, 159, 64, 0.5)',
                        'rgba(255, 0, 0, 0.5)', // Rojo
                        'rgba(0, 255, 0, 0.5)', // Verde
                        'rgba(0, 0, 255, 0.5)', // Azul
                        'rgba(128, 128, 128, 0.5)'    // Gris
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)',
                        'rgba(255, 0, 0, 1)', // Rojo
                        'rgba(0, 255, 0, 1)', // Verde
                        'rgba(0, 0, 255, 1)', // Azul
                        'rgba(128, 128, 128, 1)'    // Gris
                    ],
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
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.5)',
                        'rgba(54, 162, 235, 0.5)',
                        'rgba(255, 206, 86, 0.5)',
                        'rgba(75, 192, 192, 0.5)',
                        'rgba(153, 102, 255, 0.5)',
                        'rgba(255, 159, 64, 0.5)',
                        'rgba(255, 0, 0, 0.5)', // Rojo
                        'rgba(0, 255, 0, 0.5)', // Verde
                        'rgba(0, 0, 255, 0.5)', // Azul
                        'rgba(128, 128, 128, 0.5)'    // Gris
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)',
                        'rgba(255, 0, 0, 1)', // Rojo
                        'rgba(0, 255, 0, 1)', // Verde
                        'rgba(0, 0, 255, 1)', // Azul
                        'rgba(128, 128, 128, 1)'    // Gris
                    ],
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
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)',
                        'rgba(255, 0, 0, 1)', // Rojo
                        'rgba(0, 255, 0, 1)', // Verde
                        'rgba(0, 0, 255, 1)', // Azul
                        'rgba(128, 128, 128, 1)'    // Gris
                    ],
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
