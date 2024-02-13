<%-- 
    Document   : index
    Created on : 10-26-2023, 05:40:51 PM
    Author     : Esau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/Layout/header.jsp" %>

<div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
        <img src="https://www.marketeroslatam.com/wp-content/uploads/2021/08/encuestas-online.jpg" class="d-block w-100" style="width: 400px; height: 600px;" alt="...">
       
      </div>
      <div class="carousel-item">
         <img src="https://wpmanageninja.com/wp-content/uploads/2019/01/21-FT.png" class="d-block w-100" style="width: 500px; height: 600px;" alt="...">
      </div>
      <div class="carousel-item">
        <img src="https://www.surveylegend.com/wordpress/wp-content/uploads/2020/03/Surveys-Advantages-vs-Disadvantages.png" class="d-block w-100" style="width: 500px; height: 600px;" alt="...">
      </div>

  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>

 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<%@ include file="/Layout/footer.jsp" %>

