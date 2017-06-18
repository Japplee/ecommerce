<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="nuevosProductos">Nuevo producto</a><br>
<br>
<div>
    <table>
        <thead>
            <tr>
                <th>ID PRODUCTO</th>
                <th>ID CATEGORIA</th>
                <th>NOMBRE</th>
                <th>DESCRIPCION</th>
                <th>PRECIO</th>
                <th>ULTIMA ACTUALIZACION</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="i" items="${listadoProductos}">
                <tr>
                    <td>${i.getProId()}</td>
                    <td>${i.cat.getCatId()}</td>
                    <td>${i.getProNombre()}</td>
                    <td>${i.getProDescripcion()}</td>
                    <td>${i.getProPrecio()}</td>
                    <td>${i.getProUltimaActualizacion()}</td>
                    <td><a href="editarProductoForm?id=${i.getProId()}">Editar</a></td>
                    <td><a href="eliminarProducto?id=${i.getProId()}">Borrar</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>