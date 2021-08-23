<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Accident</title>
</head>
<body>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
  <a href="<c:url value='/create'/>">Add new accident</a>
  <div class="d-flex justify-content-center p-5">
    <table class="table table-bordered border-primary">
      <thead>
      <tr>
        <th scope="col">Id</th>
        <th scope="col">Name</th>
        <th scope="col">Text</th>
        <th scope="col">Address</th>
        <th scope="col">Accident type</th>
        <th scope="col">Rules</th>
        <th scope="col">Edit</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${accidents}" var="accident" >
        <tr>
          <th scope="row">${accident.id}</th>
          <td>${accident.name}</td>
          <td>${accident.text}</td>
          <td>${accident.address}</td>
          <td>${accident.type.name}</td>
          <td>${accident.rules}</td>
          <td><a href="<c:url value='/edit?id=${accident.id}'/>">Edit</a></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>