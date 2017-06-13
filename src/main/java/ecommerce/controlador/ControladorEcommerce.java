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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/carro_mostrar.jsp");
        dispatcher.forward(request, response);
    }
}
