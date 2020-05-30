package app.negocio.factura;

public class TFacturaProducto {

	private long idFactura;
	private long idProducto;
	private int cantidad;
	private float precio;
	
	public TFacturaProducto() {}
	
	public TFacturaProducto(long idFactura, long idProducto, int cantidad, float precio) {
		this.setIdFactura(idFactura);
		this.setIdProducto(idProducto);
		this.setCantidad(cantidad);
		this.setPrecio(precio);
	}

	public long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
}
