/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.persistencia.impl;

import ecommerce.modelo.TecCategoria;
import ecommerce.modelo.TecCliente;
import ecommerce.persistencia.dao.TecClienteDao;
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
public class TecClienteImpl implements TecClienteDao {

    private final Connection conn;

    public TecClienteImpl() {
        this.conn = MysqlDaoFactory.createConnection();
    }
    
    @Override
    public TecCliente buscar(int idCli) {
        TecCliente cli = null;
        ResultSet rs;
        String sql = "SELECT * FROM tec_cliente WHERE cli_id = ?";

        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, idCli);
            rs = pstm.executeQuery();

            if (!rs.next()) {
                Logger.getLogger(TecOrdenImpl.class.getName()).log(Level.SEVERE, "NO HAY DATOS");

            } else {
                do {
                    
                    cli = new TecCliente();
                    cli.setCliId(rs.getInt("cli_id"));
                    cli.setCliNombres(rs.getString("cli_nombres"));
                    cli.setCliApellidos(rs.getString("cli_apellidos"));
                    cli.setCliTelefono(rs.getString("cli_telefono"));
                    cli.setCliDireccion(rs.getString("cli_direccion"));
                    cli.setCliComuna(rs.getString("cli_comuna"));

                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecOrdenImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cli; 
    }

    @Override
    public ArrayList<TecCliente> listar() {
        
        ArrayList<TecCliente> clientes = new ArrayList<>();
        
        ResultSet rs;
        String sql = "SELECT * FROM tec_cliente";
        
        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (!rs.next()) {
                System.out.println("NO HAY DATOS");

            } else {
                do {
                    
                    TecCliente cli = new TecCliente();
                    cli.setCliId(rs.getInt("cli_id"));
                    cli.setCliNombres(rs.getString("cli_nombres"));
                    cli.setCliApellidos(rs.getString("cli_apellidos"));
                    cli.setCliTelefono(rs.getString("cli_telefono"));
                    cli.setCliDireccion(rs.getString("cli_direccion"));
                    cli.setCliComuna(rs.getString("cli_comuna"));
                    clientes.add(cli);

                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
        
    }

    @Override
    public boolean guardar(TecCliente cat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editar(TecCliente cat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean borrar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
