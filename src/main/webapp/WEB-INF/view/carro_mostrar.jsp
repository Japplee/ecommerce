<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <table>
        <thead>
            <tr>
                <th>Imagen</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Subtotal</th>
                <th>Opciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="i" items="${listadoProductos}">
                <tr>
                    <td><img src="http://www.100pies.net/Gifs/Alimentos/Manzanas/manzana02.gif"></td>
                    <td>${i.pro.getProNombre()}</td>
                    <td>$${i.pro.getProPrecio()}</td>
                    <td>x${i.getTopCantidad()}</td>
                    <td>$${i.getTopSubtotal()}</td>
                    <td><a href="#">Borrar</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p>Precio Total: $${orden.getOrdPrecioTotal()}</p>
</div>