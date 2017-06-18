<form action="editarCliente" method="POST">
    <fieldset>
        <legend>Nuevo Cliente</legend>
        <input type="hidden" name="id_cliente" value="${cliente.getCliId()}">
        Nombres:
        <input type="text" name="nombres_cliente" value="${cliente.getCliNombres()}"><br>
        Apellidos:
        <input type="text" name="apellidos_cliente" value="${cliente.getCliApellidos()}"><br>
        Telefono:
        <input type="text" name="numero_cliente" value="${cliente.getCliTelefono()}"><br>
        Direccion:
        <input type="text" name="direccion_cliente" value="${cliente.getCliDireccion()}"><br>
        Comuna:
        <input type="text" name="comuna_cliente" value="${cliente.getCliComuna()}"><br>
        <input type="submit" value="editar">
    </fieldset>
</form>