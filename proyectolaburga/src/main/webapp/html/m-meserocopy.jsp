
<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <title>Labur-Ga | Mesero</title>
  <link rel="stylesheet" href="../css/style.css">
  <link rel="stylesheet" href="../css/mesero copy.css">
  <link rel="stylesheet" href="../css/variables.css">
</head>

<body class="mesero">

  <!-- Encabezado -->
  <header class="encabezado">
    <!-- <h1>Panel del Mesero</h1> -->
    <p>Gestión de mesas y pedidos</p>
  </header>

  <div class="layout">
    <!-- Sidebar -->
    <aside class="sidebar">
      <h2>Panel Menú Mesero</h2>
      <nav>
        <img src="../recurso/logo-burguer.png" alt="imagen-logo-lagurga">
        <a href="m-mesas.jsp" class="btn">Mesas</a>
        <a href="unir-mesas.jsp" class="btn">Unir Mesas</a>
        <a href="separar-mesas.jsp" class="btn">Separar Mesas</a>
        <a href="separar-mesas.jsp" class="btn">Enviar cuenta</a>
        <a href="verificarcuenta.jsp" class="btn">Verificar cuenta</a>
        <!-- <a href="separar-mesas.html" class="btn">Cerrar cuenta</a> -->
        <a href="m-formulario-reserva.jsp" class="btn">Realizar reserva</a>
        <a href="m-formulario-clientenuevo-mesero.jsp" class="btn">Nuevo cliente</a>
      </nav>
    </aside>

    <!-- Contenido principal -->
    <main class="contenido">
      <!-- Contenido principal -->

      <!-- Cuadrícula de mesas -->
      <section class="mesas">
        <div class="mesa disponible">
          <h3>Mesa 1</h3>
          <p class="estado--mesa">Estado: Disponible</p>
          <a href="m-registrar-pedido.jsp?idMesa=1" class="btn btn--registrar-pedido btn-naranja">Registrar pedido</a>
          <button class="btn btn--agregar-producto btn-verde" onclick="cerrarCuenta('mesa2')">Agregar producto</button>
          <!-- <button class="btn btn-naranja">Registrar pedido</button> -->
        </div>
        <div class="mesa ocupada">
          <h3>Mesa 2</h3>
          <p class="estado--mesa">Estado: Ocupada</p>
          <a href="m-registrar-pedido.jsp?idMesa=2" class="btn btn--registrar-pedido btn-naranja">Registrar pedido</a>
          <button class="btn btn--agregar-producto btn-verde" onclick="cerrarCuenta('mesa2')">Agregar producto</button>
        </div>
        <div class="mesa ocupada">
          <h3>Mesa 3</h3>
          <p class="estado--mesa">Estado: Ocupada</p>
          <a href="m-registrar-pedido.jsp?idMesa=3" class="btn btn--registrar-pedido btn-naranja">Registrar pedido</a>
          <button class="btn btn--agregar-producto btn-verde" onclick="cerrarCuenta('mesa2')">Agregar producto</button>
        </div>
        <div class="mesa reservada">
          <h3>Mesa 4</h3>
          <p class="estado--mesa">Estado: Reservada</p>
          <a href="m-registrar-pedido.jsp?idMesa=4" class="btn btn--registrar-pedido btn-naranja">Registrar pedido</a>
          <button class="btn btn--agregar-producto btn-verde" onclick="cerrarCuenta('mesa2')">Agregar producto</button>
        </div>
        <div class="mesa disponible">
          <h3>Mesa 5</h3>
          <p class="estado--mesa">Estado: Disponible</p>
          <a href="m-registrar-pedido.jsp?idMesa=5" class="btn btn--registrar-pedido btn-naranja">Registrar pedido</a>
          <button class="btn btn--agregar-producto btn-verde" onclick="cerrarCuenta('mesa2')">Agregar producto</button>
        </div>
        <div class="mesa ocupada">
          <h3>Mesa 6</h3>
          <p class="estado--mesa">Estado: Ocupada</p>
          <a href="m-registrar-pedido.jsp?idMesa=6" class="btn btn--registrar-pedido btn-naranja">Registrar pedido</a>
          <button class="btn btn--agregar-producto btn-verde" onclick="cerrarCuenta('mesa2')">Agregar producto</button>
        </div>
        <div class="mesa reservada">
          <h3>Mesa 7</h3>
          <p class="estado--mesa">Estado: Reservada</p>
          <a href="m-registrar-pedido.jsp?idMesa=7" class="btn btn--registrar-pedido  btn-naranja">Registrar pedido</a>
          <button class="btn btn--agregar-producto btn-verde" onclick="cerrarCuenta('mesa2')">Agregar producto</button>
        </div>
        <div class="mesa disponible">
          <h3>Mesa 8</h3>
          <p class="estado--mesa">Estado: Disponible</p>
          <a href="m-registrar-pedido.jsp?idMesa=8" class="btn--registrar-pedido btn-naranja">Registrar pedido</a>
          <button class="btn btn--agregar-producto btn-verde" onclick="cerrarCuenta('mesa2')">Agregar producto</button>
        </div>
      </section>
      </form>
    </main>
  </div>
  <!-- Pie de página -->
  <footer class="footer">
    <p>&copy; 2025 Labur-Ga. Todos los derechos reservados.</p>
  </footer>

</body>

</html>