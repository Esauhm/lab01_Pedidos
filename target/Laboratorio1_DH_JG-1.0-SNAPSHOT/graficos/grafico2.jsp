<%-- 
    Document   : grafico2
    Created on : 02-13-2024, 04:38:29 PM
    Author     : Esau
--%>

<%@page import="modelos.Clientes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<title>Lista de Encuestas</title>
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

<div class="container card">
    <div class="card mb-4">
        <div class="card-header text-center">
            <h2 class="mb-4 mt-5 display-4">CLIENTES CON MÁS PEDIDOS</h2>
        </div>
        <div class="card-body">
            <div class="row">
                <!-- Gráfico de barras -->
                <div class="col-4">
                    <div class="text-center" style="width: 100%; height: 350px; text-align: center;">
                        <h3>Clientes con más pedidos</h3>
                        <canvas id="graficoClientes"></canvas>
                    </div>
                </div>
                <!-- Gráfico de pastel -->
                <div class="col-4 mx-auto">
                    <div class="text-center" style="width: 70%; height: 350px; text-align: center; margin-left: 100px;">
                        <h3>Clientes</h3>
                        <canvas id="graficoPastel"></canvas>
                    </div>
                </div>
                <!-- Gráfico de línea -->
                <div class="col-4">
                    <div class="text-center" style="width: 100%; height: 350px; text-align: center;">
                        <h3>Clientes con más pedidos</h3>
                        <canvas id="graficoLineas"></canvas>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <table id="miTabla" class="table">
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Total de Pedidos</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${datos}" var="cliente">
                <tr>
                    <td>${cliente.getNombre()}</td>
                    <td>${cliente.getTotalPedidos()}</td>

                </tr>
            </c:forEach>
        </tbody>
    </table>


    <%@ include file="/Layout/footer.jsp" %>

    <script src="
            https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js
    "></script>

    <script>

                // Obtener los datos de los clientes con más pedidos desde el servlet o clase DAO
                var datosClientes = ${datosJSON}; // Asegúrate de pasar correctamente los datos desde el servlet o clase DAO

        // Verificar si se están recibiendo los datos correctamente
        console.log(datosClientes);
        // Obtener los nombres de los clientes y el total de pedidos
        var nombresClientes = datosClientes.map(function(cliente) {
        return cliente.Nombre;
        });
        var totalPedidos = datosClientes.map(function(cliente) {
        return cliente.totalPedidos;
        });
        // Crear el gráfico utilizando Chart.js
        var ctx = document.getElementById('graficoClientes').getContext('2d');
        var graficoClientes = new Chart(ctx, {
        type: 'bar',
                data: {
                labels: nombresClientes,
                        datasets: [{
                        label: 'Total de Pedidos',
                                data: totalPedidos,
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
        // Crear el gráfico de pastel utilizando Chart.js
        var ctxPastel = document.getElementById('graficoPastel').getContext('2d');
        var graficoPastel = new Chart(ctxPastel, {
        type: 'pie',
                data: {
                labels: nombresClientes,
                        datasets: [{
                        label: 'Total de Pedidos',
                                data: totalPedidos,
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
                responsive: true
                }
        });
        // Crear el gráfico de líneas utilizando Chart.js
        var ctxLineas = document.getElementById('graficoLineas').getContext('2d');
        var graficoLineas = new Chart(ctxLineas, {
        type: 'line',
                data: {
                labels: nombresClientes,
                        datasets: [{
                        label: 'Total de Pedidos',
                                data: totalPedidos,
                                fill: false,
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
                                tension: 0.1
                        }]
                },
                options: {
                responsive: true
                }
        });


    </script>