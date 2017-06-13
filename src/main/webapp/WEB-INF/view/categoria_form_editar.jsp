<form action="categoriaActualizar" method="post">

    <fieldset><legend>Nuevo Categoria</legend>
        Nombre:
        <input type="text" name="nombre_categoria" value="${categoria.catNombre}"><br>
        <input type="hidden" name="id_categoria" value="${categoria.catId}"><br>
        <input type="reset" value="Cancelar">
        <input type="submit" value="Crear">
    </fieldset>

</form>