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
    
    public TecOrdenProducto buscar(int idOrd, int idPro);

    public ArrayList<TecOrdenProducto> listar();

    public boolean guardar(TecOrdenProducto ordPro);

    public boolean editar(TecOrdenProducto ordPro);

    public boolean borrar(int ordId, int proId);
}
