<form action="guardarProducto" method="POST">
    <fieldset>
        <legend>Nuevo Producto</legend>
        Id categoria:
        <input type="text" name="id_categoria" value="${producto.cat.getCatId()}"><br>
        Nombre:
        <input type="text" name="nombre_producto" value="${producto.getProNombre()}"><br>
        Descripcion:
        <input type="text" name="descripcion_producto" value="${producto.getProDescripcion()}"><br>
        Precio:
        <input type="text" name="precio_producto" value="${producto.getProPrecio()}"><br>
        Ultima actualizacion:
        <input type="text" name="ultima_producto" value="${producto.getProUltimaActualizacion()}"><br>
        <input type="submit" value="editar">
    </fieldset>
</form>