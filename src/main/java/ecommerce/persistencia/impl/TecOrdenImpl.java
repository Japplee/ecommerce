/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.persistencia.impl;

import com.mysql.jdbc.MySQLConnection;
import ecommerce.modelo.TecCategoria;
import ecommerce.modelo.TecCliente;
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
                    
                    TecCliente cli = new TecCliente();
                    TecClienteImpl impCli = new TecClienteImpl();
                    cli = impCli.buscar(rs.getInt("cli_id"));
                    ord = new TecOrden();
                    ord.setOrdId(rs.getInt("ord_id"));
                    ord.setCli(cli);
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
        
        ArrayList<TecOrden> ordenes = new ArrayList<>();
        
        ResultSet rs;
        String sql = "SELECT * FROM tec_orden";
        
        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (!rs.next()) {
                System.out.println("NO HAY DATOS");

            } else {
                do {
                    
                    TecOrden ord;
                    TecCliente cli;
                    TecClienteImpl impCli = new TecClienteImpl();
                    cli = impCli.buscar(rs.getInt("cli_id"));
                    ord = new TecOrden();
                    ord.setOrdId(rs.getInt("ord_id"));
                    ord.setCli(cli);
                    ord.setOrdCreacion(rs.getString("ord_fcreacion"));
                    ord.setOrdNumConfirmacion(rs.getInt("ord_num_confirmacion"));
                    ord.setOrdPrecioTotal(rs.getInt("ord_precio_total"));
                    ordenes.add(ord);

                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ordenes;
    }

    @Override
    public boolean guardar(TecOrden ord) {
        boolean resultado = false;
        String sql = "INSERT INTO tec_orden(cli_id, ord_fcreacion, ord_num_confirmacion, ord_precio_total) values(?, ?, ? ,?)";
        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, ord.getCli().getCliId());
            pstm.setString(2, ord.getOrdCreacion());
            pstm.setInt(3, ord.getOrdNumConfirmacion());
            pstm.setInt(4, ord.getOrdPrecioTotal());
            pstm.executeUpdate();
            resultado = true;

        } catch (SQLException ex) {
            Logger.getLogger(TecClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    @Override
    public boolean editar(TecOrden ord) {
        
        boolean result = false;
        String sql = "UPDATE tec_orden SET ord_fcreacion = ?, ord_num_confirmacion = ?,"
                + "ord_precio_total = ? WHERE ord_id = ?";
        Logger.getLogger(TecOrdenImpl.class.getName()).log(Level.SEVERE, "Hospital editar {0}", ord);

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, ord.getOrdCreacion());
            pstm.setInt(2, ord.getOrdNumConfirmacion());
            pstm.setInt(3, ord.getOrdPrecioTotal());
            pstm.setInt(4, ord.getOrdId());
            
            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecOrdenImpl.class.getName()).log(Level.SEVERE, "Edita {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecOrdenImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
        
    }

    @Override
    public boolean borrar(int id) {

        boolean result = false;
        String sql = "DELETE FROM tec_orden WHERE ord_id = ?";

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, id);
            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecClienteImpl.class.getName()).log(Level.SEVERE, "BORRA {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
}
