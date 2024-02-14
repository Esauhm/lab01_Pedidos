<%@page import="modelos.Clientes"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Laboratorio #1</title>
        <!-- Incluir Bootstrap CSS y JavaScript -->
        <!--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">-->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <!-- Incluir FontAwesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

        <link rel="icon" href="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' height='1em' viewBox='0 0 640 512'%3E%3Cpath d='M36.8 192H603.2c20.3 0 36.8-16.5 36.8-36.8c0-7.3-2.2-14.4-6.2-20.4L558.2 21.4C549.3 8 534.4 0 518.3 0H121.7c-16 0-31 8-39.9 21.4L6.2 134.7c-4 6.1-6.2 13.2-6.2 20.4C0 175.5 16.5 192 36.8 192zM64 224V384v80c0 26.5 21.5 48 48 48H336c26.5 0 48-21.5 48-48V384 224H320V384H128V224H64zm448 0V480c0 17.7 14.3 32 32 32s32-14.3 32-32V224H512z'/%3E%3C/svg%3E" type="image/svg+xml">
        <!-- SweetAlert2 CDN -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.js"></script>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha384-oCE1HRaxdUz+DhqFRRgtcsls4U6TG3MWzUzpgAEaY8eUn57fj5cIK83AgiNajz6b" crossorigin="anonymous">



        <!-- Agrega las bibliotecas de DataTables -->
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.11.3/datatables.min.css" />
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.11.3/datatables.min.js"></script>
        <!-- Bloque de estilo personalizado -->
        <style>
            .body{
                background-color: #f5f7f8; /* Color de fondo suave */

            }
            /* Estilo del Navbar */
            .navbar {
                background-color: #ebf2f5; /* Color de fondo suave */
            }

            /* Estilo del logotipo */
            .navbar-brand {
                font-size: 24px;
                color: #333; /* Color de texto más oscuro */
            }

            /* Estilo del botón de hamburguesa */
            .custom-toggler .navbar-toggler-icon {
                background: none; /* Fondo transparente */
                border: none; /* Sin borde */
                outline: none; /* Sin contorno */
                padding: 0; /* Sin relleno */
                color: #333; /* Color de texto más oscuro */
                font-size: 24px; /* Tamaño de fuente */
            }

            /* Estilo de los enlaces del menú */
            .navbar-nav .nav-item {
                margin: 0 10px;
            }

            .navbar-nav .nav-link {
                font-size: 16px;
                color: #333; /* Color de texto más oscuro */
                transition: color 0.3s; /* Transición de color suave */
            }

            .navbar-nav .nav-link:hover {
                color: #555; /* Cambio de color al pasar el mouse */
            }

            /* Estilo del botón de cerrar sesión */
            .logout-button {
                background-color: #dc3545; /* Color de fondo rojo */
                color: #fff; /* Color de texto blanco */
                border: none;
                border-radius: 5px;
                padding: 5px 10px;
                transition: background-color 0.3s; /* Transición de color de fondo suave */
            }

            .logout-button:hover {
                background-color: #c82333; /* Cambio de color de fondo al pasar el mouse */
            }

            /* Estilo del contenedor del contenido */
            .container {
                background-color: #fff; /* Fondo del contenido más claro */
                padding: 20px;
                margin-bottom: 20px;
                margin-top: 25px;
                border-radius: 5px;
                box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1); /* Sombra suave */
            }

            .lfooter2 {
                background-image: linear-gradient(to right, #1E3A56, #101D27); /* Cambiar los colores del gradiente */
                -webkit-background-clip: text;
                -webkit-text-fill-color: transparent;
                font-weight: bold;
                font-family: 'Cursive', cursive; /* Fuente estilo vintage */
                font-size: 24px; /* Tamaño de fuente */
                color: #1E3A56; /* Color de fuente azul oscuro */
                text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5); /* Sombra de texto */
            }
            /* Estilo para el logo */
            .logo {
                width: 56px; /* Tamaño del logo */
                height: 56px; /* Tamaño del logo */
                border-radius: 50%; /* Forma circular */
                border: 2px solid #007BFF; /* Borde azul */
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); /* Sombra suave */
                transition: transform 0.2s, box-shadow 0.2s; /* Transiciones suaves */
            }

            /* Efecto al pasar el mouse */
            .logo:hover {
                transform: scale(1.1); /* Aumenta el tamaño al pasar el mouse */
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.3); /* Sombra más pronunciada */
            }

            /* Estilo de los enlaces del menú */
            .navbar-nav .nav-item {
                margin: 0 10px;
            }

            .navbar-nav .nav-link {
                font-size: 20px;
                color: #333; /* Color de texto oscuro */
                transition: color 0.3s, text-shadow 0.3s; /* Transiciones suaves de color y sombreado de texto */
            }

            .navbar-nav .nav-link:hover {
                color: #007BFF; /* Cambio de color de texto al pasar el mouse */
                text-shadow: 2px 2px 4px rgba(0, 123, 255, 0.3); /* Sombreado de texto al pasar el mouse */
            }
        </style>





    </head>
    <body class="body">
        <!-- Navbar con paleta de colores suaves -->
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <a class="navbar-brand" href="/Laboratorio1_DH_JG" style="margin-left: 10px;">
                    <img src="https://previews.123rf.com/images/lineartestpilot/lineartestpilot1802/lineartestpilot180278994/95592089-gato-de-dibujos-animados-aburrido-tomando-encuesta-ilustraci%C3%B3n-vectorial.jpg" alt="Logo de la Tienda" class="logo">
                </a>
                <a class="lfooter2">Laboratorio #1</a>
                <button class="navbar-toggler custom-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fas fa-bars"></i> <!-- Icono de hamburguesa personalizado de FontAwesome -->
                </button>
                <div class="collapse navbar-collapse" id="navbarNav" style="padding: 0px;">
                    <ul class="navbar-nav">
                        <li class="nav-item active">    
                            <a class="nav-link" href="/Laboratorio1_DH_JG">Inicio</a>
                        </li>
                        <li class="nav-item active">    
                            <a class="nav-link" href="CargarClientes">Clientes</a>
                        </li>
                        <li class="nav-item active">    
                            <a class="nav-link" href="pedido">Pedidos</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Graficos
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="graficoPedido">Meses con más pedidos</a></li>
                                <li><a class="dropdown-item" href="Grafico2">Clientes con más pedidos</a></li>
                            </ul>
                        </li>
                    </ul>    
                 </div>    
            </div>

        </nav>








