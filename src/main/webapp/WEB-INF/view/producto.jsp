<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <td><a href="#">Editar</a></td>
                    <td><a href="#">Borrar</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>