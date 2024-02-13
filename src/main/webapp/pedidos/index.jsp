<%-- 
    Document   : index
    Created on : 02-12-2024, 10:08:09 PM
    Author     : Esau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<title>Lista de Clientes</title>
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
</style>
<div class="container">

    <h1 class="mb-3">Lista de Pedidos</h1>

    <!-- Botón para agregar un nuevo cliente -->
    <a href="crearPedidos" class="btn btn-primary mb-3">
        <i class="fas fa-plus"></i> Agregar pedido
    </a>

    <!-- Tabla para mostrar la lista de clientes -->
    <table id="miTabla" class="table">
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Fecha</th>
                <th>Total</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${lstPedidos}" var="pedido">
                <tr>
                    <td>${pedido.getNombre_cliente(pedido.getID_Cliente())}</td>
                    <td>${pedido.getFecha()}</td>
                    <td>${pedido.getTotal()}</td>
                    
                    <c:choose>
                        <c:when test="${pedido.getEstado() == 1}">
                            <td>Pendiente</td>
                        </c:when>
                         <c:when test="${pedido.getEstado() == 2}">
                           <td>En camino</td>
                        </c:when>
                            <c:when test="${pedido.getEstado() == 3}">
                           <td>Entregado</td>
                        </c:when>
                        <c:otherwise>
                             <td>N/A</td>
                        </c:otherwise>
                    </c:choose>
                 
                    <td>
                        <a href="editarPedido?id=${pedido.getID()}" class="btn btn-primary">
                            <i class="fas fa-pencil-alt"></i> Editar
                        </a>
                        <a href="eliminarPedido?id=${pedido.getID()}" class="btn btn-danger" >
                            <i class="fas fa-trash"></i> Eliminar
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="/Layout/footer.jsp" %>
