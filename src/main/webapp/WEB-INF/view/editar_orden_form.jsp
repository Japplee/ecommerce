<form action="editarOrden" method="POST">
    <fieldset>
        <legend>Nueva Orden</legend>
        <input type="hidden" value="${orden.getOrdId()}" name="id_orden">
        <input type="hidden" value="${orden.cli.getCliId()}" name="id_cliente">
        Fecha:
        <input type="text" name="fecha_orden" value="${orden.getOrdCreacion()}"><br>
        Num Confirmacion:
        <input type="text" name="numero_orden" value="${orden.getOrdNumConfirmacion()}"><br>
        Precio total:
        <input type="text" name="precio_orden" value="${orden.getOrdPrecioTotal()}"><br>
        <input type="submit" value="editar">
    </fieldset>
</form>