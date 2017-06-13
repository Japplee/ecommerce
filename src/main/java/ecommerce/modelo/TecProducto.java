package ecommerce.modelo;

import java.util.ArrayList;

public class TecProducto {
	private int proId;
	private String proNombre;
	private String proDescripcion;
	private Integer proPrecio;
	private String proUltimaActualizacion;
	public TecCategoria cat;
	public ArrayList<TecOrdenProducto> tecOrdenProducto = new ArrayList<TecOrdenProducto>();
}