/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.persistencia.dao;

import ecommerce.modelo.TecCliente;
import java.util.ArrayList;

/**
 *
 * @author Jorge
 */
public interface TecClienteDao {
    
    public TecCliente buscar(int idCli);

    public ArrayList<TecCliente> listar();

    public boolean guardar(TecCliente cat);

    public boolean editar(TecCliente cat);

    public boolean borrar(int id);
    
}
