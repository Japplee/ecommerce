/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.persistencia.dao;

import ecommerce.modelo.TecOrdenProducto;
import java.util.ArrayList;

/**
 *
 * @author Jorge
 */
public interface TecOrdenProductoDao {
    
    public ArrayList<TecOrdenProducto> buscar(int idOrd);

    public ArrayList<TecOrdenProducto> listar();

    public boolean guardar(TecOrdenProducto cat);

    public boolean editar(TecOrdenProducto cat);

    public boolean borrar(int id);
}
