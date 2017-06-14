package ecommerce.persistencia.factory;

import ecommerce.persistencia.dao.TecCategoriaDao;
import ecommerce.persistencia.dao.TecClienteDao;
import ecommerce.persistencia.dao.TecOrdenDao;
import ecommerce.persistencia.dao.TecOrdenProductoDao;
import ecommerce.persistencia.dao.TecProductoDao;
import ecommerce.persistencia.impl.TecCategoriaImpl;
import ecommerce.persistencia.impl.TecClienteImpl;
import ecommerce.persistencia.impl.TecOrdenImpl;
import ecommerce.persistencia.impl.TecOrdenProductoImpl;
import ecommerce.persistencia.impl.TecProductoImpl;
import javax.servlet.ServletContext;

public abstract class DAOFactory {

    public static DAOFactory getFactory(TipoBD bd, ServletContext context) {
        
        switch (bd) {

            case DERBY:
                return new DerbyDaoFactory();
            case MYSQL:
                return new MysqlDaoFactory(context);
            case ORACLE:
                throw new IllegalArgumentException();
            default:
                throw new IllegalArgumentException();
        }

    }

    public TecCategoriaDao getTecCategoriaDao() { return new TecCategoriaImpl(); }
    
    public TecOrdenDao getTecOrdenDao() { return new TecOrdenImpl(); }
    
    public TecOrdenProductoDao getTecOrdenProductoDao() { return new TecOrdenProductoImpl(); }

    public TecClienteDao getTecClienteDao() { return new TecClienteImpl(); }
    
    public TecProductoDao getTecProductoDao() { return new TecProductoImpl(); }
}
