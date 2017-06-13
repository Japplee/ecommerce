/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.persistencia.dao;

import ecommerce.modelo.TecOrden;
import java.util.ArrayList;

/**
 *
 * @author Jorge
 */
public interface TecOrdenDao {
    
    public TecOrden buscar(int numConfirm, int idOrd);

    public ArrayList<TecOrden> listar();

    public boolean guardar(TecOrden ord);

    public boolean editar(TecOrden ord);

    public boolean borrar(int id);
    
}
