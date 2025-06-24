<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
  <style>
    /* MODAL CSS */
    .modal {
      display: none; /* nascosto all'inizio */
      position: fixed;
      z-index: 1000;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0,0,0,0.5);
    }

    .modal-content {
      background-color: white;
      margin: 15% auto;
      padding: 20px;
      border-radius: 10px;
      width: 300px;
      position: relative;
    }

    .close {
      position: absolute;
      top: 10px;
      right: 15px;
      font-size: 20px;
      cursor: pointer;
    }
  </style>
</head>

<body>
<h1 align="center">Login</h1>


<form action="servletregistrazioneutenti" method="post">

<p align="center">
	<label for="name"></label><input required type="text" placeholder="Nome" name="nome" id="name"><br>
	<label for="mail"></label><input required type="email" placeholder="Email" name="email" id="mail"><br>
	<label for="passw"></label><input required type="password" placeholder="Password" name="passw" id="passw"><br>
</p>


<p align="center">
	<input type="submit" value="Accedi"> 
	<input type="button" value="Registrati" OnClick="openModal()">
</p>

</form>



<!-- MODAL REGISTRAZIONE -->
<div id="registerModal" class="modal">
  <div class="modal-content">
    <span class="close" onclick="closeModal()">&times;</span>
    <form action="servletregistrazioneutenti" method="post">
      <h2>Registrati</h2>
      <input type="text" name="nome" placeholder="Nome" required><br>
      <input type="text" name="cognome" placeholder="Cognome" required><br>
      <input type="email" name="email" placeholder="Email" required><br>
      <input type="password" name="passw" placeholder="Password" required><br>
      <input type="date" name="eta" placeholder="Data di nascita" required><br>
      <button type="submit">Invia</button>
    </form>
  </div>
</div>

<script>
  function openModal() {
    document.getElementById("registerModal").style.display = "block";
  }

  function closeModal() {
    document.getElementById("registerModal").style.display = "none";
  }

  // chiudi cliccando fuori dal popup
  window.onclick = function(event) {
    const modal = document.getElementById("registerModal");
    if (event.target === modal) {
      modal.style.display = "none";
    }
  }
</script>

</body>
</html>