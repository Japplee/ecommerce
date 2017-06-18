<form action="editarOrdenProducto" method="POST">
    <fieldset>
        <legend>Nueva Orden Producto</legend>
        <input type="hidden" value="${ordenProducto.ord.getOrdId()}" name="id_orden">
        <input type="hidden" value="${ordenProducto.pro.getProId()}" name="id_producto">
        Top cantidad:
        <input type="text" name="top_cantidad" value="${ordenProducto.getTopCantidad()}"><br>
        Top subtotal:
        <input type="text" name="top_subtotal" value="${ordenProducto.getTopSubtotal()}"><br>
        <input type="submit" value="editar">
    </fieldset>
</form>