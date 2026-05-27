<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>registrar cliente</title>
  <link rel="stylesheet" href="../css/formulario-clientenuevo-mesero.css">
  <link rel="stylesheet" href="../css/variables.css">
</head>
<body>
  <header>
    <h1>Panel Mesero - Nuevo Cliente</h1>
  </header>
  
  <main>
    <form action="/registrarCliente" method="post">
      <!-- Nombre del cliente -->
      <label for="nombre">Nombre:</label>
      <input type="text" id="nombre" name="nombre" required>
  
      <!-- Teléfono -->
      <label for="telefono">Teléfono:</label>
      <input type="text" id="telefono" name="telefono" required>
  
      <!-- Correo -->
      <label for="correo">Correo:</label>
      <input type="email" id="correo" name="correo">
  
      <!-- Botón de enviar y cancelar -->
      <button type="submit" class="btn--envio">Registrar Cliente</button>
      <button type="submit" class="btn--cancelar">Cancelar Registro</button>
    </form>
  </main>
  
  <footer class="footer"> 
    <p>&copy; 2025 Labur-Ga. Todos los derechos reservados.</p>
  </footer>
  
</body>
</html>