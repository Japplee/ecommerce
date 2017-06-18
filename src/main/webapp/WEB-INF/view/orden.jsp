<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="nuevasOrdenes">Crear Orden</a><br>
<br>
<div>
    <table>
        <thead>
            <tr>
                <th>ID ORDEN</th>
                <th>ID CLIENTE</th>
                <th>FECHA CREACION</th>
                <th>NUM CONFIRMACION</th>             
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="i" items="${listadoOrdenes}">
                <tr>
                    <td>${i.getOrdId()}</td>
                    <td>${i.cli.getCliId()}</td>
                    <td>${i.getOrdCreacion()}</td>
                    <td>${i.getOrdPrecioTotal()}</td>
                    <td><a href="editarOrdenForm?id=${i.getOrdId()}">Editar</a></td>
                    <td><a href="eliminarOrden?id=${i.getOrdId()}">Borrar</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
