package ecommerce.modelo;

import java.util.ArrayList;

public class TecCliente {
	private int cliId;
	private String cliNombres;
	private String cliApellidos;
	private String cliTelefono;
	private String cliDireccion;
	private String cliComuna;
	public ArrayList<TecOrden> tecOrden = new ArrayList<TecOrden>();

	public TecCliente() {
	}

	public int getCliId() {
		return this.cliId;
	}

	public void setCliId(int cliId) {
		this.cliId = cliId;
	}

	public String getCliNombres() {
		return this.cliNombres;
	}

	public void setCliNombres(String cliNombres) {
		this.cliNombres = cliNombres;
	}

	public String getCliApellidos() {
		return this.cliApellidos;
	}

	public void setCliApellidos(String cliApellidos) {
		this.cliApellidos = cliApellidos;
	}

	public String getCliTelefono() {
		return this.cliTelefono;
	}

	public void setCliTelefono(String cliTelefono) {
		this.cliTelefono = cliTelefono;
	}

	public String getCliDireccion() {
		return this.cliDireccion;
	}

	public void setCliDireccion(String cliDireccion) {
		this.cliDireccion = cliDireccion;
	}

	public String getCliComuna() {
		return this.cliComuna;
	}

	public void setCliComuna(String cliComuna) {
		this.cliComuna = cliComuna;
	}
}