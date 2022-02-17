function goQR() {
    var str = document.getElementById("suffix").value;
    str.startsWith('/') ? str = str.slice(1) : str;
    window.location=location.href+str
   }
