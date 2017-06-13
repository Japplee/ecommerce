package ecommerce.modelo;

import java.util.ArrayList;

public class TecOrden {
	private int ordId;
	private String ordCreacion;
	private Integer ordNumConfirmacion;
	private Integer ordPrecioTotal;
	public TecCliente cli;
	public ArrayList<TecOrdenProducto> tecOrdenProducto = new ArrayList<TecOrdenProducto>();

    public TecOrden() {
    }

    public int getOrdId() {
        return ordId;
    }

    public void setOrdId(int ordId) {
        this.ordId = ordId;
    }

    public String getOrdCreacion() {
        return ordCreacion;
    }

    public void setOrdCreacion(String ordCreacion) {
        this.ordCreacion = ordCreacion;
    }

    public Integer getOrdNumConfirmacion() {
        return ordNumConfirmacion;
    }

    public void setOrdNumConfirmacion(Integer ordNumConfirmacion) {
        this.ordNumConfirmacion = ordNumConfirmacion;
    }

    public Integer getOrdPrecioTotal() {
        return ordPrecioTotal;
    }

    public void setOrdPrecioTotal(Integer ordPrecioTotal) {
        this.ordPrecioTotal = ordPrecioTotal;
    }

    public TecCliente getCli() {
        return cli;
    }

    public void setCli(TecCliente cli) {
        this.cli = cli;
    }

    public ArrayList<TecOrdenProducto> getTecOrdenProducto() {
        return tecOrdenProducto;
    }

    public void setTecOrdenProducto(ArrayList<TecOrdenProducto> tecOrdenProducto) {
        this.tecOrdenProducto = tecOrdenProducto;
    }
        
        
}