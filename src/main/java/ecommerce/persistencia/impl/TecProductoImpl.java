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
    public boolean guardar(TecProducto pro) {
        boolean resultado = false;
        String sql = "INSERT INTO tec_producto(cat_id, pro_nombre, pro_descripcion, pro_precio, pro_ultima_actualizacion) values(?, ?, ? ,?, ?)";
        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, pro.cat.getCatId());
            pstm.setString(2, pro.getProNombre());
            pstm.setString(3, pro.getProDescripcion());
            pstm.setInt(4, pro.getProPrecio());
            pstm.setString(5, pro.getProUltimaActualizacion());
            pstm.executeUpdate();
            resultado = true;

        } catch (SQLException ex) {
            Logger.getLogger(TecClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    @Override
    public boolean editar(TecProducto pro) {
        boolean result = false;
        String sql = "UPDATE tec_producto SET pro_nombre = ?, pro_descripcion = ?,"
                + "pro_precio = ?, pro_ultima_actualizacion = ?, cat_id = ? WHERE ord_id = ?";
        Logger.getLogger(TecProductoImpl.class.getName()).log(Level.SEVERE, "Producto editar {0}", pro);

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, pro.getProNombre());
            pstm.setString(2, pro.getProDescripcion());
            pstm.setInt(3, pro.getProPrecio());
            pstm.setString(4, pro.getProUltimaActualizacion());
            pstm.setInt(5, pro.cat.getCatId());
            pstm.setInt(6, pro.getProId());
            
            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecProductoImpl.class.getName()).log(Level.SEVERE, "Edita {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public boolean borrar(int id) {
        boolean result = false;
        String sql = "DELETE FROM tec_producto WHERE pro_id = ?";

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, id);
            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecProductoImpl.class.getName()).log(Level.SEVERE, "BORRA {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
}
