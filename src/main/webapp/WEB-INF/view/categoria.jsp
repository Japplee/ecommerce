<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>NOMBRE</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="i" items="${listadoCategorias}">
                <tr>
                    <td>${i.catId}</td>
                    <td>${i.catNombre}</td>
                    <td><a href="categoriaFormEditar?catId=${i.catId}">Editar</a></td>
                    <td><a href="categoriaBorrar?catId=${i.catId}">Borrar</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
