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
                case "/ordenes":
                    this.listarOrdenes(request, response);
                    break;
                case "/ordenProductos":
                    this.listarOrdenProductos(request, response);
                    break;
                case "/productos":
                    this.listarProductos(request, response);
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
        ArrayList<TecOrdenProducto> listadoProductos = ordenProductoDao.buscar(tecOrden.getOrdId());
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
}
