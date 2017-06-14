/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.persistencia.dao;

import ecommerce.modelo.TecProducto;
import java.util.ArrayList;

/**
 *
 * @author Jorge
 */
public interface TecProductoDao {
    
    public TecProducto buscar(int idPro);

    public ArrayList<TecProducto> listar();

    public boolean guardar(TecProductoDao pro);

    public boolean editar(TecProductoDao pro);

    public boolean borrar(int id);
    
}
