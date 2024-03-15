<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <title>Document</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/verTransferenciaContacto/verTransferenciaContacto_main.css" />
  </head>
  <body>
    <div id="maindiv">
      <div id="AfiliacionBanner">
        <h1 style="font-family: Roboto;">Afilia un celular para recibir dinero.</h1>
        <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Celular.png" alt="" />
        <button id="AfiliarBtn">Hazlo aqui</button>
      </div>
      <div id="TransferenciaBanner">
        <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/1.png" alt="" />
        <h1>Transfiere de celular a celular.</h1>
        <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/2.png" alt="" />
        <button id="TransferenciaBtn">Hazlo aqui</button>
      </div>
      <button id="DatosAfiliadosBtn">Datos afiliados</button>
    </div>
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/verTransferenciaContacto/verTransferenciaContacto_main.js"></script>
  </body>
</html>
