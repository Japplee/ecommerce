/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.persistencia.impl;

import com.mysql.jdbc.MySQLConnection;
import ecommerce.modelo.TecCategoria;
import ecommerce.modelo.TecOrden;
import ecommerce.persistencia.dao.TecOrdenDao;
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
public class TecOrdenImpl implements TecOrdenDao{
    
    private final Connection conn;
    
    public TecOrdenImpl() {
        this.conn = MysqlDaoFactory.createConnection();
    }

    @Override
    public TecOrden buscar(int numConfirm, int idOrd) {
        
        TecOrden ord = null;
        ResultSet rs;
        String sql = "";
        int valor = 0;
        
        if (numConfirm != 0) {
            sql = "SELECT * FROM tec_orden WHERE ord_num_confirmacion = ?";
            valor = numConfirm;
            
        }else if (idOrd != 0) {
            sql = "SELECT * FROM tec_orden WHERE ord_id = ?";
            valor = idOrd;
        }

        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, valor);
            rs = pstm.executeQuery();

            if (!rs.next()) {
                Logger.getLogger(TecOrdenImpl.class.getName()).log(Level.SEVERE, "NO HAY DATOS");

            } else {
                do {
                    ord = new TecOrden();
                    ord.setOrdId(rs.getInt("ord_id"));
                    //ord.setCli(rs.getInt("cli_id"));
                    ord.setOrdCreacion(rs.getString("ord_fcreacion"));
                    ord.setOrdNumConfirmacion(rs.getInt("ord_num_confirmacion"));
                    ord.setOrdPrecioTotal(rs.getInt("ord_precio_total"));

                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecOrdenImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ord;
    }
    
    @Override
    public ArrayList<TecOrden> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean guardar(TecOrden ord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editar(TecOrden ord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean borrar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
