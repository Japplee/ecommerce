<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <table>
        <thead>
            <tr>
                <th>ID ORDEN</th>
                <th>ID PRODUCTO</th>
                <th>CANTIDAD</th>
                <th>SUBTOTAL</th>             
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="i" items="${listadoOrdenProductos}">
                <tr>
                    <td>${i.ord.getOrdId()}</td>
                    <td>${i.pro.getProId()}</td>
                    <td>${i.getTopCantidad()}</td>
                    <td>${i.getTopSubtotal()}</td>
                    <td><a href="#">Editar</a></td>
                    <td><a href="#">Borrar</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
