package ecommerce.persistencia.factory;

import ecommerce.persistencia.dao.TecCategoriaDao;
import ecommerce.persistencia.impl.TecCategoriaImpl;
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
//
//    public ProductoDao getProductoDao() { return new ProductoImpl(); }
}
