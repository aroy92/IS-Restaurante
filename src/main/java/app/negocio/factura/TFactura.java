package app.negocio.factura;

import java.time.LocalDateTime;
import java.util.List;

public class TFactura {
	private long id;
	private double total;
	private LocalDateTime fecha_emision;
	private int idEmpleado;
	private int idCliente;
	private boolean cerrada;
	private List<Long> productos;
	
	public TFactura() {}
	
	public TFactura(long id, double total, LocalDateTime fecha_emision, int idEmpleado, int idCliente, boolean cerrada, List<Long> productos) {
		this.setId(id);
		this.setTotal(total);
		this.setFechaEmision(fecha_emision);
		this.setIdEmpleado(idEmpleado);
		this.setIdCliente(idCliente);
		this.setCerrada(cerrada);
		this.setProductos(productos);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public LocalDateTime getFechaEmision() {
		return fecha_emision;
	}

	public void setFechaEmision(LocalDateTime fecha_emision) {
		this.fecha_emision = fecha_emision;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public List<Long> getProductos() {
		return productos;
	}

	public void setProductos(List<Long> productos) {
		this.productos = productos;
	}

	public boolean isCerrada() {
		return cerrada;
	}

	public void setCerrada(boolean cerrada) {
		this.cerrada = cerrada;
	}
}