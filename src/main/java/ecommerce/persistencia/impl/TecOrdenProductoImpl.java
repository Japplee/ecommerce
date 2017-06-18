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
    public TecOrdenProducto buscar(int idOrd, int idPro) {
        ResultSet rs;
        TecOrdenProducto ordPro = new TecOrdenProducto();
        String sql = "SELECT * FROM tec_orden_producto WHERE ord_id = ? AND pro_id = ?";
        
        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, idOrd);
            pstm.setInt(2, idPro);
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
                    
                    ordPro.setOrd(ord);
                    ordPro.setPro(pro);
                    ordPro.setTopCantidad(rs.getInt("top_cantidad"));
                    ordPro.setTopSubtotal(rs.getInt("top_subtotal"));

                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ordPro;
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
    public boolean guardar(TecOrdenProducto proOrd) {
        
        boolean resultado = false;
        String sql = "INSERT INTO tec_orden_producto(ord_id, pro_id, top_cantidad, top_subtotal) values(?, ?, ? ,?)";
        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, proOrd.ord.getOrdId());
            pstm.setInt(2, proOrd.pro.getProId());
            pstm.setInt(3, proOrd.getTopCantidad());
            pstm.setInt(4, proOrd.getTopSubtotal());
            pstm.executeUpdate();
            resultado = true;

        } catch (SQLException ex) {
            Logger.getLogger(TecClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    @Override
    public boolean editar(TecOrdenProducto proOrd) {
        
        boolean result = false;
        String sql = "UPDATE tec_orden_producto SET top_cantidad = ?, top_subtotal = ? WHERE ord_id = ? AND pro_id = ? ";
        Logger.getLogger(TecOrdenProductoImpl.class.getName()).log(Level.SEVERE, "Orden Producto editar {0}", proOrd);

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, proOrd.getTopCantidad());
            pstm.setInt(2, proOrd.getTopSubtotal());
            pstm.setInt(3, proOrd.ord.getOrdId());
            pstm.setInt(4, proOrd.pro.getProId());
     
            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecOrdenProductoImpl.class.getName()).log(Level.SEVERE, "Edita {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecOrdenProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public boolean borrar(int ordId, int proId) {
        
        boolean result = false;
        String sql = "DELETE FROM tec_orden_producto WHERE ord_id = ? AND pro_id = ?";

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, ordId);
            pstm.setInt(2, proId);
            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecClienteImpl.class.getName()).log(Level.SEVERE, "BORRA {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
}
