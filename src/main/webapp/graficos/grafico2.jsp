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
        <div class="card-header">
            <h2 class="mb-4 mt-5">Clientes con mas pedidos</h2>
        </div>
        <div class="card-body">
            <div class="row">
                <!-- Gráfico de barras -->
                <div class="col-4">
                    <div class="text-center" style="width: 100%; height: 350px; text-align: center;">
                        <h3>Clientes con mas pedidos</h3>
                        <canvas id="graficoClientes"></canvas>
                    </div>
                </div>
                <!-- Gráfico de pastel -->
                <div class="col-4 mx-auto">
                    <div class="text-center" style="width: 70%; height: 350px; text-align: center; margin-left: 100px;">
                        <h3>Clientes con mas pedidos</h3>
                        <canvas id="graficoPastel"></canvas>
                    </div>
                </div>
                <!-- Gráfico de línea -->
                <div class="col-4">
                    <div class="text-center" style="width: 100%; height: 350px; text-align: center;">
                        <h3>Clientes con mas pedidos</h3>
                        <canvas id="graficoLinea"></canvas>
                    </div>
                </div>
            </div>
        </div>
    </div>


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
        
        
        // Crear un gráfico de pastel para la distribución de sexos
        var ctxPastel = document.getElementById('graficoPastel').getContext('2d');
        var graficoPastel = new Chart(ctxPastel, {
        type: 'pie',
                data: {
                labels: ['Hombres', 'Mujeres'],
                        datasets: [{
                        data: [hombres, mujeres],
                                backgroundColor: [
                                        'rgba(255, 99, 132, 0.2)',
                                        'rgba(54, 162, 235, 0.2)',
                                        'rgba(255, 206, 86, 0.2)',
                                        'rgba(75, 192, 192, 0.2)'
                                ],
                                borderColor: [
                                        'rgba(255, 99, 132, 1)',
                                        'rgba(54, 162, 235, 1)',
                                        'rgba(255, 206, 86, 1)',
                                        'rgba(75, 192, 192, 1)'
                                ],
                                borderWidth: 1
                        }]
                }
        });
        // Crear un gráfico de líneas para la distribución de sexos
        var ctxLinea = document.getElementById('graficoLinea').getContext('2d');
        var graficoLinea = new Chart(ctxLinea, {
        type: 'line',
                data: {
                labels: ['Hombres', 'Mujeres'],
                        datasets: [{
                        label: 'Distribución de Sexo (Línea)',
                                data: [hombres, mujeres],
                                fill: false, // No rellenes la línea
                                borderColor: 'rgba(75, 192, 192, 1)', // Color de la línea
                                borderWidth: 2
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