<%-- 
    Document   : footer
    Created on : 26 sep. 2023, 08:41:05
    Author     : Esau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    </div>
<style>
 @import url('https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;700&display=swap');
*{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Open Sans', sans-serif;
}
/*:::::Pie de Pagina*/
.pie-pagina{
    width: 100%;
    margin-top: 10px;
    background-color:#000000;
}
.pie-pagina .grupo-1{
    width: 100%;
    max-width: 1200px;
    margin: auto;
    display:grid;
    grid-template-columns: repeat(3, 1fr);
    grid-gap:50px;
    padding: 45px 0px;
}
.pie-pagina .grupo-1 .box figure{
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}
.pie-pagina .grupo-1 .box figure img{
    width: 250px;
}
.pie-pagina .grupo-1 .box h2{
    color: #fff;
    margin-bottom: 25px;
    font-size: 20px;
}
.pie-pagina .grupo-1 .box p{
    color: #efefef;
    margin-bottom: 10px;
}
.pie-pagina .grupo-1 .red-social a{
    display: inline-block;
    text-decoration: none;
    width: 45px;
    height: 45px;
    line-height: 45px;
    color: #fff;
    margin-right: 10px;
    background-color: #7e694e;
    text-align: center;
    transition: all 300ms ease;
}
.pie-pagina .grupo-1 .red-social a:hover{
    color: aqua;
}
.pie-pagina .grupo-2{
    background-color: #ffd39b;
    padding: 12px 10px;
    text-align: center;
    color: #333333;
}
.pie-pagina .grupo-2 small{
    font-size: 15px;
}
@media screen and (max-width:800px){
    .pie-pagina .grupo-1{
        width: 90%;
        grid-template-columns: repeat(1, 1fr);
        grid-gap:30px;
        padding: 35px 0px;
    }
}
.lfooter{
   background-image: linear-gradient(to right, #1E3A56, #101D27); /* Cambiar los colores del gradiente */
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    font-weight: bold;
    font-family: 'Cursive', cursive; /* Fuente estilo vintage */
    font-size: 74px; /* Tamaño de fuente */
    color: #1E3A56; /* Color de fuente azul oscuro */
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5); /* Sombra de texto */
}

</style>
    
      <footer class="pie-pagina">
        <div class="grupo-1">
            <div class="box">
                <a class="lfooter" >Encuestify</a>
            </div>
            <div class="box">
                <h2>SOBRE NOSOTROS</h2>
                <p>¿Quieres conocer más sobre Encuestify?</p>
                <a class="nav-about" style="color: white" >Nosotros</a>

            </div>
            <div class="box">
                <h2>SIGUENOS</h2>
                <div class="red-social">
                    <a href="" class="fa-brands fa-facebook"></a>
                    <a href="" class="fa-brands fa-instagram"></a>
                    <a href="" class="fab fa-youtube"></a>
                    <a href="" class="fab fa-twitter"></a>


                </div>
            </div>
        </div>
        <div class="grupo-2">
            <small>&copy; 2023 <b>Encuestify</b> - Todos los Derechos Reservados: DiegoHernandez_JuanGomez.</small>
        </div>
    </footer>

</body>
</html>
    <%-- Mostrar mensaje de éxito si está presente en la sesión --%>
    <% String successMessage = (String) session.getAttribute("successMessage"); %>
    <% if (successMessage != null) { %>
        <script>
            Swal.fire({
                title: 'Éxito',
                text: '<%= successMessage %>',
                icon: 'success',
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 3000
            });
        </script>
        <% session.removeAttribute("successMessage"); %>
    <% } %>

    <%-- Mostrar mensaje de error si está presente en la sesión --%>
    <% String errorMessage = (String) session.getAttribute("errorMessage"); %>
    <% if (errorMessage != null) { %>
        <script>
            Swal.fire({
                title: 'Error',
                text: '<%= errorMessage %>',
                icon: 'error',
                toast: true,
                position: 'center',
                showConfirmButton: false,
                timer: 3000
            });
        </script>
        <% session.removeAttribute("errorMessage"); %>
    <% } %>

<script>
    
    function eliminarElemento(idElemento, elemento) {
        console.log("ID del " + elemento + " a eliminar:", idElemento);

        Swal.fire({
            title: '¿Estás seguro?',
            text: `Esta acción eliminará el ${elemento}. ¿Deseas continuar?`,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Sí, eliminar',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                // Si el usuario confirmó, redirige a la URL de eliminación
                window.location.href = elemento+`eliminar?id=` + idElemento;
            }
        });
    }
     function comprar() {
    // Verificar si la sesión contiene un cliente (puedes ajustar esta lógica según tu aplicación)
    var clienteEnSesion = <%= session.getAttribute("cliente") != null %> ;

    if (clienteEnSesion) {
        // Si la sesión contiene un cliente, muestra una confirmación adicional antes de comprar
        Swal.fire({
            title: 'Confirmar compra',
            text: '¿Estás seguro de realizar la compra?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: 'Sí, comprar',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                // El usuario confirmó la compra, aquí puedes redirigir a la página de compra
                alert("vamos a la ruta comprar")
                window.location.href = "comprar";
                
            }
        });
    } else {
        // Si la sesión no contiene un cliente, muestra el mensaje de inicio de sesión como antes
        Swal.fire({
            title: 'Iniciar sesión',
            text: 'Para realizar la compra, debes iniciar sesión y tener un cliente válido.',
            icon: 'info',
            showCancelButton: true,
            confirmButtonText: 'Iniciar sesión',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                // Redirige a la página de inicio de sesión
                window.location.href = "login";
            }
        });
    }
}

$(document).ready(function() {
    $('#miTabla').DataTable({
        "language": {
            "sProcessing": "Procesando...",
            "sLengthMenu": "Mostrar _MENU_ registros por página",
            "sZeroRecords": "No se encontraron resultados",
            "sEmptyTable": "Ningún dato disponible en esta tabla",
            "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix": "",
            "sSearch": "Buscar:",
            "sUrl": "",
            "sInfoThousands": ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast": "Último",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            }
        },
        "lengthMenu": [5, 10, 25], // Mostrar opciones de 5, 10 y 25 registros por página
        "pageLength": 5 // Establecer 5 como valor por defecto
    });
});


</script>