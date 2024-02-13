<%-- 
    Document   : editar
    Created on : 02-12-2024, 11:17:47 PM
    Author     : Esau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Cliente</title>
    <%@ include file="/Layout/header.jsp" %>
    
    <style>
        /* Estilo para el formulario de edición de clientes */
        .registration-form {
            width: 100%;
            max-width: 550px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s; 
        }
        
        .registration-form:hover {
            transform: scale(1.02); /* Aumenta el tamaño de la imagen al 110% cuando se hace hover */
        }
        
        .registration-form h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        
        .form-group {
            margin-bottom: 15px;
        }
        
        .form-group label {
            font-weight: bold;
        }
        
        .form-control {
            width: 100%;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        
        .btn-register {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #007bff; /* Color de fondo azul */
            color: #fff; /* Color de texto blanco */
            border: none;
            border-radius: 5px;
            text-align: center;
            text-decoration: none;
            font-weight: bold;
        }
        
        .btn-register:hover {
            background-color: #0056b3; /* Cambio de color al pasar el mouse */
        }
    </style>
</head>
<body>
    <div class="registration-form mt-5">
        <h2 class="text-center">Editar Cliente</h2>
        <form action="clienteEditado" method="post" onsubmit="return validarFormulario()">
            <!-- Campos del formulario -->
            <input type="hidden" name="id" value="${cliente.getID()}">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" class="form-control" id="nombre" name="nombre" required value="${cliente.getNombre()}">
            </div>
            <div class="form-group">
                <label for="direccion">Dirección:</label>
                <input type="text" class="form-control" id="direccion" name="direccion" value="${cliente.getDirección()}">
            </div>
            <div class="form-group">
                <label for="telefono">Teléfono:</label>
                <input type="text" class="form-control" id="telefono" name="telefono" value="${cliente.getTeléfono()}" oninput="this.value = this.value.replace(/[^0-9]/g, '');">
            </div>
            <div class="form-group">
                <label for="email">Correo Electrónico:</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="correo@ejemplo.com" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" required value="${cliente.getEmail()}">
            </div>
            <div class="centered-buttons text-center">
                <button type="submit" class="btn btn-primary btn-register mb-2">Guardar</button>
            </div>
        </form>
    </div>
    <%@ include file="/Layout/footer.jsp" %>
</body>
</html>

