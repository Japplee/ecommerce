/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.controlador;

import ecommerce.persistencia.dao.TecCategoriaDao;
import ecommerce.persistencia.factory.DAOFactory;
import ecommerce.persistencia.factory.TipoBD;
import ecommerce.modelo.TecCategoria;
import ecommerce.modelo.TecCliente;
import ecommerce.modelo.TecOrden;
import ecommerce.modelo.TecOrdenProducto;
import ecommerce.modelo.TecProducto;
import ecommerce.persistencia.dao.TecClienteDao;
import ecommerce.persistencia.dao.TecOrdenDao;
import ecommerce.persistencia.dao.TecOrdenProductoDao;
import ecommerce.persistencia.dao.TecProductoDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dci
 */
public class ControladorEcommerce extends HttpServlet {

    public static DAOFactory fabrica;

    @Override
    public void init() throws ServletException {

        ControladorEcommerce.fabrica = DAOFactory.getFactory(TipoBD.MYSQL, this.getServletContext());
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();
        try {
            switch (userPath) {
                case "/categorias":
                    this.listarCategorias(request, response);
                    break;
                case "/nuevaCategoria":
                    this.mostrarFormNuevaCategoria(request, response);
                    break;
                case "/categoriaFormEditar":
                    this.mostrarFormEditar(request, response);
                    break;
                case "/verCarro":
                    this.mostrarCarro(request, response);
                    break;
                case "/clientes":
                    this.listarClientes(request, response);
                    break;
                case "/nuevoCliente":
                    this.mostrarFormCliente(request, response);
                    break;
                case "/borrarCliente":
                    this.borrarCliente(request, response);
                    break;
                case "/editarClienteForm":
                    this.mostrarFormEditarCliente(request, response);
                    break;    
                case "/ordenes":
                    this.listarOrdenes(request, response);
                    break;
                case "/nuevasOrdenes":
                    this.mostrarFormOrdenes(request, response);
                    break;
                case "/eliminarOrden":
                    this.borrarOrden(request, response);
                    break;
                case "/editarOrdenForm":
                    this.mostrarFormEditarOrden(request, response);
                    break;
                case "/ordenProductos":
                    this.listarOrdenProductos(request, response);
                    break;
                case "/nuevaOrdenProducto":
                    this.nuevaOrdenProducto(request, response);
                    break;
                case "/eliminarOrdenProducto":
                    this.eliminarOrdenProducto(request, response);
                    break;
                case "/editarOrdenProductoForm":
                    this.editarOrdenProductoForm(request, response);
                    break;
                case "/productos":
                    this.listarProductos(request, response);
                    break;
                case "/nuevosProductos":
                    this.mostrarFormProductos(request, response);
                    break;
                case "/eliminarProducto":
                    this.eliminarProducto(request, response);
                    break;
                case "/editarProductoForm":
                    this.mostrarFormEditarProducto(request, response);
                    break;
            }

        } catch (SQLException e) {
            throw new ServletException(e);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();
        try {
            switch (userPath) {
                case "/guardarCategoria":
                    this.guardarCategoria(request, response);
                    break;
                case "/categoriaActualizar":
                    this.actualizarCategoria(request, response);
                    break;
                case "/guardarCliente":
                    this.guardarCliente(request, response);
                    break;
                case "/editarCliente":
                    this.editarCliente(request, response);
                    break;  
                case "/guardarOrden":
                    this.guardarOrden(request, response);
                    break;
                case "/editarOrden":
                    this.editarOrden(request, response);
                    break;
                case "/guardarProducto":
                    this.guardarProducto(request, response);
                    break;
                case "/editarProducto":
                    this.editarProducto(request, response);
                    break;
                case "/guardarOrdenProducto":
                    this.guardarOrdenProducto(request, response);
                    break;
                case "/editarOrdenProducto":
                    this.editarOrdenProducto(request, response);
                    break;
            }

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarCategorias(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        TecCategoriaDao categoriaDao = ControladorEcommerce.fabrica.getTecCategoriaDao();
        ArrayList<TecCategoria> listadoCategoria = categoriaDao.listar();

        request.setAttribute("listadoCategorias", listadoCategoria);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/categoria.jsp");
        dispatcher.forward(request, response);

    }

    private void mostrarFormNuevaCategoria(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/categoria_form.jsp");
        dispatcher.forward(request, response);
    }

    private void guardarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        TecCategoria cat = new TecCategoria();
        cat.setCatNombre(request.getParameter("nombre_categoria"));

        TecCategoriaDao categoriaDao = ControladorEcommerce.fabrica.getTecCategoriaDao();
        categoriaDao.guardar(cat);
        response.sendRedirect("index.jsp");
    }

    private void mostrarFormEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        TecCategoriaDao categoriaDao = ControladorEcommerce.fabrica.getTecCategoriaDao();
        int id = Integer.parseInt(request.getParameter("catId"));
        TecCategoria cat = categoriaDao.buscar(id);

        if (cat == null) {
            Logger.getLogger(ControladorEcommerce.class.getName()).log(Level.INFO, "categoria:{0}",cat);
        }

        request.setAttribute("categoria", cat);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/categoria_form_editar.jsp");

        dispatcher.forward(request, response);

    }

    private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        
        TecCategoria cat = new TecCategoria();
        cat.setCatNombre(request.getParameter("nombre_categoria"));
        cat.setCatId(Integer.parseInt(request.getParameter("id_categoria")));
        
        TecCategoriaDao categoriaDao = ControladorEcommerce.fabrica.getTecCategoriaDao();
        categoriaDao.editar(cat);
        response.sendRedirect("index.jsp");
    }

    private void mostrarCarro(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        
        TecOrdenDao ordenDao = ControladorEcommerce.fabrica.getTecOrdenDao();
        TecOrden tecOrden = ordenDao.buscar(0, 1);
        
        TecOrdenProductoDao ordenProductoDao = ControladorEcommerce.fabrica.getTecOrdenProductoDao();
        ArrayList<TecOrdenProducto> listadoProductos = ordenProductoDao.listar();
        //TecCategoriaDao categoriaDao = ControladorEcommerce.fabrica.getTecCategoriaDao();
        //ArrayList<TecCategoria> listadoCategoria = categoriaDao.listar();

        request.setAttribute("listadoProductos", listadoProductos);
        request.setAttribute("orden", tecOrden);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/carro_mostrar.jsp");
        dispatcher.forward(request, response);
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        TecClienteDao clienteDao = ControladorEcommerce.fabrica.getTecClienteDao();
        ArrayList<TecCliente> listadoClientes = clienteDao.listar();
   
        request.setAttribute("listadoClientes", listadoClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/cliente.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listarOrdenes(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        TecOrdenDao ordenDao = ControladorEcommerce.fabrica.getTecOrdenDao();
        ArrayList<TecOrden> listadoOrdenes = ordenDao.listar();
           
        request.setAttribute("listadoOrdenes", listadoOrdenes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/orden.jsp");
        dispatcher.forward(request, response);
    }

    private void listarOrdenProductos(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        TecOrdenProductoDao ordenProductoDao = ControladorEcommerce.fabrica.getTecOrdenProductoDao();
        ArrayList<TecOrdenProducto> listadoOrdenProductos = ordenProductoDao.listar();
           
        request.setAttribute("listadoOrdenProductos", listadoOrdenProductos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/ordenProducto.jsp");
        dispatcher.forward(request, response);
    }

    private void listarProductos(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        
        TecProductoDao productoDao = ControladorEcommerce.fabrica.getTecProductoDao();
        ArrayList<TecProducto> listadoProductos = productoDao.listar();
        
        request.setAttribute("listadoProductos", listadoProductos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/producto.jsp");
        dispatcher.forward(request, response);
        
    }

    private void guardarCliente(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        TecCliente tecCliente = new TecCliente();
        
        tecCliente.setCliNombres(request.getParameter("nombres_cliente"));
        tecCliente.setCliApellidos(request.getParameter("apellidos_cliente"));
        tecCliente.setCliTelefono(request.getParameter("numero_cliente"));
        tecCliente.setCliDireccion(request.getParameter("direccion_cliente"));
        tecCliente.setCliComuna(request.getParameter("comuna_cliente"));
        TecClienteDao clienteDao = ControladorEcommerce.fabrica.getTecClienteDao();
        clienteDao.guardar(tecCliente);
        response.sendRedirect("clientes");
        
    }

    private void mostrarFormCliente(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/cliente_form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void mostrarFormEditarCliente(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        TecCliente cliente;
        TecClienteDao clienteDao = ControladorEcommerce.fabrica.getTecClienteDao();
        cliente = clienteDao.buscar(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("cliente", cliente);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/editar_cliente_form.jsp");
        dispatcher.forward(request, response);
    }

    private void borrarCliente(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        TecClienteDao clienteDao = ControladorEcommerce.fabrica.getTecClienteDao();
        clienteDao.borrar(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("clientes");
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        TecCliente tecCliente = new TecCliente();
        
        tecCliente.setCliId(Integer.parseInt(request.getParameter("id_cliente")));
        tecCliente.setCliNombres(request.getParameter("nombres_cliente"));
        tecCliente.setCliApellidos(request.getParameter("apellidos_cliente"));
        tecCliente.setCliTelefono(request.getParameter("numero_cliente"));
        tecCliente.setCliDireccion(request.getParameter("direccion_cliente"));
        tecCliente.setCliComuna(request.getParameter("comuna_cliente"));
        TecClienteDao clienteDao = ControladorEcommerce.fabrica.getTecClienteDao();
        clienteDao.editar(tecCliente);
        response.sendRedirect("clientes");
        
    }

    private void mostrarFormOrdenes(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
       RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/orden_form.jsp");
        dispatcher.forward(request, response); 
    }

    private void guardarOrden(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        TecCliente cliente;
        TecClienteDao clienteDao = ControladorEcommerce.fabrica.getTecClienteDao();
        
        cliente = clienteDao.buscar(Integer.parseInt(request.getParameter("id_cliente")));
        
        TecOrdenDao ordenDao = ControladorEcommerce.fabrica.getTecOrdenDao();
        TecOrden orden = new TecOrden();
        
        orden.setCli(cliente);
        orden.setOrdCreacion(request.getParameter("fecha_orden"));
        orden.setOrdNumConfirmacion(Integer.parseInt(request.getParameter("numero_orden")));
        orden.setOrdPrecioTotal(Integer.parseInt(request.getParameter("precio_orden")));
        
        ordenDao.guardar(orden);
        response.sendRedirect("ordenes");
    }

    private void editarOrden(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        TecCliente cliente;
        TecClienteDao clienteDao = ControladorEcommerce.fabrica.getTecClienteDao();
        TecOrdenDao ordenDao = ControladorEcommerce.fabrica.getTecOrdenDao();
        
        cliente = clienteDao.buscar(Integer.parseInt(request.getParameter("id_cliente")));
        
        TecOrden orden = new TecOrden();
        orden.setOrdId(Integer.parseInt(request.getParameter("id_orden")));
        orden.setCli(cliente);
        orden.setOrdCreacion(request.getParameter("fecha_orden"));
        orden.setOrdNumConfirmacion(Integer.parseInt(request.getParameter("numero_orden")));
        orden.setOrdPrecioTotal(Integer.parseInt(request.getParameter("precio_orden")));
        
        ordenDao.editar(orden);
        response.sendRedirect("ordenes");
    }

    private void borrarOrden(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        TecOrdenDao ordenDao = ControladorEcommerce.fabrica.getTecOrdenDao();
        ordenDao.borrar(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("ordenes");
    }

    private void mostrarFormEditarOrden(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        TecOrden orden;
        TecOrdenDao ordenDao = ControladorEcommerce.fabrica.getTecOrdenDao();
        orden = ordenDao.buscar(0, Integer.parseInt(request.getParameter("id")));
        request.setAttribute("orden", orden);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/editar_orden_form.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormProductos(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/producto_form.jsp");
        dispatcher.forward(request, response); 
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        TecProductoDao productoDao = ControladorEcommerce.fabrica.getTecProductoDao();
        productoDao.borrar(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("productos");
    }

    private void mostrarFormEditarProducto(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        TecProducto producto;
        TecProductoDao productoDao = ControladorEcommerce.fabrica.getTecProductoDao();
        producto = productoDao.buscar(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("producto", producto);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/editar_producto_form.jsp");
        dispatcher.forward(request, response);
    }

    private void guardarProducto(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
                
        TecCategoria categoria;
        TecCategoriaDao categoriaDao = ControladorEcommerce.fabrica.getTecCategoriaDao();
        
        categoria = categoriaDao.buscar(Integer.parseInt(request.getParameter("id_categoria")));
        
        TecProductoDao productoDao = ControladorEcommerce.fabrica.getTecProductoDao();
        TecProducto producto = new TecProducto();
        
        producto.setCat(categoria);
        producto.setProNombre(request.getParameter("nombre_producto"));
        producto.setProDescripcion(request.getParameter("descripcion_producto"));
        producto.setProPrecio(Integer.parseInt(request.getParameter("precio_producto")));
        producto.setProUltimaActualizacion(request.getParameter("ultima_producto"));
        
        productoDao.guardar(producto);
        response.sendRedirect("productos");
    }

    private void editarProducto(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        
        TecCategoria categoria;
        TecCategoriaDao categoriaDao = ControladorEcommerce.fabrica.getTecCategoriaDao();
        TecProductoDao productoDao = ControladorEcommerce.fabrica.getTecProductoDao();
        
        categoria = categoriaDao.buscar(Integer.parseInt(request.getParameter("id_categoria")));
        
        TecProducto producto = new TecProducto();
        producto.setCat(categoria);
        producto.setProNombre(request.getParameter("nombre_producto"));
        producto.setProDescripcion(request.getParameter("descripcion_producto"));
        producto.setProPrecio(Integer.parseInt(request.getParameter("precio_producto")));
        producto.setProUltimaActualizacion(request.getParameter("ultima_producto"));
        
        productoDao.editar(producto);
        response.sendRedirect("productos");
    }

    private void nuevaOrdenProducto(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/ordenProducto_form.jsp");
        dispatcher.forward(request, response); 
    }

    private void eliminarOrdenProducto(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        TecOrdenProductoDao ordenProductoDao = ControladorEcommerce.fabrica.getTecOrdenProductoDao();
        ordenProductoDao.borrar(Integer.parseInt(request.getParameter("idOrd")),
                Integer.parseInt(request.getParameter("idPro")));
        response.sendRedirect("ordenProductos");
    }

    private void editarOrdenProductoForm(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        TecOrdenProducto ordenProducto;
        TecOrdenProductoDao ordenProductoDao = ControladorEcommerce.fabrica.getTecOrdenProductoDao();
        ordenProducto = ordenProductoDao.buscar(Integer.parseInt(request.getParameter("idOrd")), Integer.parseInt(request.getParameter("idPro")));
        request.setAttribute("ordenProducto", ordenProducto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/editar_ordenProducto_form.jsp");
        dispatcher.forward(request, response);
            
    }

    private void guardarOrdenProducto(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        //Orden
        TecOrden orden;
        TecOrdenDao ordenDao = ControladorEcommerce.fabrica.getTecOrdenDao();
        orden = ordenDao.buscar(0, Integer.parseInt(request.getParameter("id_orden")));
        
        //Producto
        TecProducto producto;
        TecProductoDao productoDao = ControladorEcommerce.fabrica.getTecProductoDao();
        producto = productoDao.buscar(Integer.parseInt(request.getParameter("id_producto")));
        
        TecOrdenProductoDao ordenProductoDao = ControladorEcommerce.fabrica.getTecOrdenProductoDao();
        TecOrdenProducto ordenProducto = new TecOrdenProducto();
        
        ordenProducto.setOrd(orden);
        ordenProducto.setPro(producto);
        ordenProducto.setTopCantidad(Integer.parseInt(request.getParameter("top_cantidad")));
        ordenProducto.setTopSubtotal(Integer.parseInt(request.getParameter("top_subtotal")));
        
        ordenProductoDao.guardar(ordenProducto);
        response.sendRedirect("ordenProductos");
    }

    private void editarOrdenProducto(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
        
        //Orden
        TecOrden orden;
        TecOrdenDao ordenDao = ControladorEcommerce.fabrica.getTecOrdenDao();
        orden = ordenDao.buscar(0, Integer.parseInt(request.getParameter("id_orden")));
        
        //Producto
        TecProducto producto;
        TecProductoDao productoDao = ControladorEcommerce.fabrica.getTecProductoDao();
        producto = productoDao.buscar(Integer.parseInt(request.getParameter("id_producto")));
        
        TecOrdenProductoDao ordenProductoDao = ControladorEcommerce.fabrica.getTecOrdenProductoDao();
        TecOrdenProducto ordenProducto = new TecOrdenProducto();
        
        ordenProducto.setOrd(orden);
        ordenProducto.setPro(producto);
        ordenProducto.setTopCantidad(Integer.parseInt(request.getParameter("top_cantidad")));
        ordenProducto.setTopSubtotal(Integer.parseInt(request.getParameter("top_subtotal")));
        System.out.println("AAAAA" + ordenProducto.ord.getOrdId());
        System.out.println("BBBBB" + ordenProducto.pro.getProId());
        ordenProductoDao.editar(ordenProducto);
        response.sendRedirect("ordenProductos");
    }
}
