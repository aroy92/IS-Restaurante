package app.negocio.cliente;

public class TCliente {
	private int id;
	private String nombre;
	
	// Sobrecarga de constructores
	public TCliente() {}
	
	public TCliente(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	// GETTERS
	public int getID() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	// SETTERS
	public void setID(int id) {
		this.id = id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}