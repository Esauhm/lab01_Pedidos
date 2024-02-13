<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Gráfico de Pedidos</title>
    <!-- Incluir Chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    
<div class="container card">
    <div class="card mb-4">
    <div class="card-header">
        <h2 class="mb-4 mt-5">Meses con mayor pedidos</h2>
    </div>
    <div class="card-body">
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
    
    
    
    <script>
        
       
        
        // Obtener los datos JSON del atributo de solicitud
        var encuestasJSON = ${requestScope.encuestasJSON};

        // Parsear los datos JSON
        var data = JSON.parse(encuestasJSON);

        // Preparar datos para el gráfico
        var labels = data.map(function(pedido) {
            return pedido.cantidad;
        });
        var valores = data.map(function(pedido) {
            return pedido.mes;
        });
        

        // Crear gráfico
        var ctxBarras = document.getElementById('graficoBarras').getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Cantidad de Pedidos',
                    data: valores,
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            }
        });
    </script>
</body>
</html>
