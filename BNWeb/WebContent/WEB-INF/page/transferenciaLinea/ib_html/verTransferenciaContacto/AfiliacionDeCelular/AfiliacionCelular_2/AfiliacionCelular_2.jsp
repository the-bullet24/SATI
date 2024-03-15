<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <title>Afiliación Celular</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/verTransferenciaContacto/AfiliacionDeCelular/AfiliacionCelular_2/AfiliacionCelular_2.css">
</head>
<body>
    <div id="maindiv">
    <h1 style="font-family: 'daxcompact-mediumregular'; font-size: 20px;margin-bottom: 19px;">Afilia tu Celular</h1>
        
        <div class="container">
            <p>Para continuar con el proceso de afiliaci&oacute;n
                 te hemos enviado un c&oacute;digo de verificaci&oacute;n
                  SMS a tu n&uacute;mero celular.</p>

            <div class="CelularContainer">
                 <h2>947 058 652</h2>
            </div>
        </div>

    <form id="CodeValidation" name="CodeValidation">
        <label for="code">C&oacute;digo de verificaci&oacute;n</label>
        <div class="codeContainer">
            <input type="password" name="code" id="code" required maxlength="6">
            <a href="">Reenviar c&oacute;digo</a>
        </div>
    </form>
    </div>


<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>   
<script type="text/javascript" src="<%=request.getContextPath()%>/js/verTransferenciaContacto/AfiliacionDeCelular/AfiliacionCelular_2/AfiliacionCelular_2.js"></script>   
</body>
</html>