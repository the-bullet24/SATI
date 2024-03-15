/*
 * Función     : fnReloadCaptcha
 * Descripción : Invoca al action CaptchaAction
 *               para actualizar el valor del captcha
 */
 function fnReloadCaptcha(){
 	alert('fnReloadCaptcha');
    var now  = new Date();
    var imageObject = document.getElementById('captcha');
    imageObject.src='./captcha.do?reload=' + now.getTime();
}

