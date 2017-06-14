/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.persistencia.impl;

import ecommerce.modelo.TecOrden;
import ecommerce.modelo.TecOrdenProducto;
import ecommerce.modelo.TecProducto;
import ecommerce.persistencia.dao.TecOrdenProductoDao;
import ecommerce.persistencia.factory.MysqlDaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class TecOrdenProductoImpl implements TecOrdenProductoDao {
    
    private final Connection conn;
    
    public TecOrdenProductoImpl() {
        this.conn = MysqlDaoFactory.createConnection();
    }

    @Override
    public ArrayList<TecOrdenProducto> buscar(int idOrd) {
        ArrayList<TecOrdenProducto> ordenProductos = new ArrayList<>();
        ResultSet rs;
        String sql = "SELECT * FROM tec_orden_producto WHERE ord_id = ?";
        
        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, idOrd);
            rs = pstm.executeQuery();

            if (!rs.next()) {
                System.out.println("NO HAY DATOS");

            } else {
                do {
                    //Orden
                    TecOrden ord = new TecOrden();
                    TecOrdenImpl ordImpl = new TecOrdenImpl();
                    ord = ordImpl.buscar(0, rs.getInt("ord_id"));
                    
                    //Producto
                    TecProducto pro = new TecProducto();
                    TecProductoImpl proImpl = new TecProductoImpl();
                    pro = proImpl.buscar(rs.getInt("pro_id"));
                    
                    TecOrdenProducto ordPro = new TecOrdenProducto();
                    ordPro.setOrd(ord);
                    ordPro.setPro(pro);
                    ordPro.setTopCantidad(rs.getInt("top_cantidad"));
                    ordPro.setTopSubtotal(rs.getInt("top_subtotal"));
                    ordenProductos.add(ordPro);

                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ordenProductos;
    }

    @Override
    public ArrayList<TecOrdenProducto> listar() {
        
        ArrayList<TecOrdenProducto> ordenProductos = new ArrayList<>();
        
        ResultSet rs;
        String sql = "SELECT * FROM tec_orden_producto";
        
        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (!rs.next()) {
                System.out.println("NO HAY DATOS");

            } else {
                do {
                    
                    //Orden
                    TecOrden ord;
                    TecOrdenImpl ordImpl = new TecOrdenImpl();
                    ord = ordImpl.buscar(0, rs.getInt("ord_id"));
                    
                    //Producto
                    TecProducto pro;
                    TecProductoImpl proImpl = new TecProductoImpl();
                    pro = proImpl.buscar(rs.getInt("pro_id"));
                    
                    TecOrdenProducto ordPro = new TecOrdenProducto();
                    ordPro.setOrd(ord);
                    ordPro.setPro(pro);
                    ordPro.setTopCantidad(rs.getInt("top_cantidad"));
                    ordPro.setTopSubtotal(rs.getInt("top_subtotal"));
                    ordenProductos.add(ordPro);

                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ordenProductos;
        
    }

    @Override
    public boolean guardar(TecOrdenProducto cat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editar(TecOrdenProducto cat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean borrar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
