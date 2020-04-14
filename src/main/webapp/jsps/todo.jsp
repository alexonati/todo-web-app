<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title> &nbsp Todos</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
	<nav class="navbar navbar-light" style="background-color: #726E97;">

		<a class="btn btn-info btn-lg">
                  <span class="glyphicon glyphicon-home" style="color:white"> TODO List Web App</span>
                </a>

		<ul class="nav nav-pills nav-fill">
			<li class="nav-item">
             <a class= "btn btn-info btn-lg" href="listtodo.do">Todo's</a></li>
             </li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a class="btn btn-info btn-lg" href="login.do">Logout</a></li>
		</ul>
	</nav>
	</br>
	<div class="container">
		<H2>Welcome ${Email}</H2>
		</br>
		<p> <strong>Your todos are:</strong></p>
		</br>
	<table class="table table-hover table-active table-borderless">
      <thead>
        <tr>
          <th scope="col">Description</th>
          <th scope="col">Category</th>
          <th scope="col">Actions</th>
        </tr>
      </thead>
      <tbody>
       <c:forEach items="${todos}" var="todo">
        <tr>
          <td>${todo.name}</td>
          <td>${todo.category}</td>
          <td><a class="btn btn-danger" href="delete.do?todo=${todo.name}&category=${todo.category}">Delete</a> </td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
    </br>
        <a class="btn btn-success" href="addtodo.do">Add New Todo</a>
	</div>
	<nav class="navbar fixed-bottom navbar-light" style="background-color: #726E97;">
      <span class="navbar-text">
      <a class="navbar-brand" href="https://github.com/ioanalexandruonati?tab=repositories" target="_blank">My github repos!</a></p>
      </span>
    </nav>
	</footer>
</body>

</html>