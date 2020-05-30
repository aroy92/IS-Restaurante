package app.negocio.producto;

public class TProducto {
	private long id;
	private String nombre;
	private float precio;
	private boolean activo;
	
	public TProducto() {}
	
	public TProducto(long id, String nombre, float precio, boolean activo) {
		this.setId(id);
		this.setNombre(nombre);
		this.setPrecio(precio);
		this.setActivo(activo);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
