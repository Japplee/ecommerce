<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>NOMBRES</th>
                <th>APELLIDOS</th>
                <th>TELEFONO</th>
                <th>DIRECCION</th>
                <th>COMUNA</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="i" items="${listadoClientes}">
                <tr>
                    <td>${i.getCliId()}</td>
                    <td>${i.getCliNombres()}</td>
                    <td>${i.getCliApellidos()}</td>
                    <td>${i.getCliTelefono()}</td>
                    <td>${i.getCliDireccion()}</td>
                    <td>${i.getCliComuna()}</td>
                    <td><a href="#">Editar</a></td>
                    <td><a href="#">Borrar</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
