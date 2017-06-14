package ecommerce.modelo;

public class TecOrdenProducto {
    
    private Integer topCantidad;
    private Integer topSubtotal;
    public TecOrden ord;
    public TecProducto pro;

    public TecOrdenProducto() {
    }

    public Integer getTopCantidad() {
        return topCantidad;
    }

    public void setTopCantidad(Integer topCantidad) {
        this.topCantidad = topCantidad;
    }

    public Integer getTopSubtotal() {
        return topSubtotal;
    }

    public void setTopSubtotal(Integer topSubtotal) {
        this.topSubtotal = topSubtotal;
    }

    public TecOrden getOrd() {
        return ord;
    }

    public void setOrd(TecOrden ord) {
        this.ord = ord;
    }

    public TecProducto getPro() {
        return pro;
    }

    public void setPro(TecProducto pro) {
        this.pro = pro;
    }
    
}