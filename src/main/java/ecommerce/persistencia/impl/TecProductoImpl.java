/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.persistencia.impl;

import ecommerce.modelo.TecCategoria;
import ecommerce.modelo.TecProducto;
import ecommerce.persistencia.dao.TecProductoDao;
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
public class TecProductoImpl implements TecProductoDao{
    
    private final Connection conn;

    public TecProductoImpl() {
        this.conn = MysqlDaoFactory.createConnection();
    }

    @Override
    public TecProducto buscar(int idPro) {
        
        TecProducto pro = null;
        ResultSet rs;
        String sql = "SELECT * FROM tec_producto WHERE pro_id = ?";
        
        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, idPro);
            rs = pstm.executeQuery();

            if (!rs.next()) {
                Logger.getLogger(TecOrdenImpl.class.getName()).log(Level.SEVERE, "NO HAY DATOS");

            } else {
                do {
                    
                    TecCategoria cat = new TecCategoria();
                    TecCategoriaImpl catImpl = new TecCategoriaImpl();
                    cat = catImpl.buscar(rs.getInt("cat_id"));
                    
                    pro = new TecProducto();
                    pro.setProId(rs.getInt("pro_id"));
                    pro.setCat(cat);
                    pro.setProNombre(rs.getString("pro_nombre"));
                    pro.setProDescripcion(rs.getString("pro_descripcion"));
                    pro.setProPrecio(rs.getInt("pro_precio"));
                    pro.setProUltimaActualizacion(rs.getString("pro_ultima_actualizacion"));

                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecOrdenImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pro; 
    }

    @Override
    public ArrayList<TecProducto> listar() {
        
        ArrayList<TecProducto> productos = new ArrayList<>();
        TecProducto pro;
        
        ResultSet rs;
        String sql = "SELECT * FROM tec_producto";
        
        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (!rs.next()) {
                System.out.println("NO HAY DATOS");

            } else {
                do {
                    
                    TecCategoria cat;
                    TecCategoriaImpl catImpl = new TecCategoriaImpl();
                    cat = catImpl.buscar(rs.getInt("cat_id"));
                    
                    pro = new TecProducto();
                    pro.setProId(rs.getInt("pro_id"));
                    pro.setCat(cat);
                    pro.setProNombre(rs.getString("pro_nombre"));
                    pro.setProDescripcion(rs.getString("pro_descripcion"));
                    pro.setProPrecio(rs.getInt("pro_precio"));
                    pro.setProUltimaActualizacion(rs.getString("pro_ultima_actualizacion"));
                    productos.add(pro);
                    
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }

    @Override
    public boolean guardar(TecProductoDao pro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editar(TecProductoDao pro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean borrar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
