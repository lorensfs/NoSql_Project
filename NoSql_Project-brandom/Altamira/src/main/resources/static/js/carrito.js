function addCart(formulario) {
    console.log("addCart ejecutado"); // Verifica que la función se ejecuta
    var idProducto = formulario.elements[0].value;
    var url = "/carrito/agregar/" + idProducto;
    console.log("URL generada:", url); // Verifica que la URL sea la correcta
    $("#resultsBlock").load(url);
}
