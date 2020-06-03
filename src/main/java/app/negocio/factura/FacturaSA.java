package app.negocio.factura;

import java.util.List;

public interface FacturaSA {
	public void abrir(TFactura factura) throws Exception;
	public void cerrar(long id) throws Exception;
	public TFactura mostrar(long id) throws Exception;
	public void addProducto(TFacturaProducto facturaProducto) throws Exception;
	public void removeProducto(long idFactura, long idProducto) throws Exception;
	public List<TFacturaProducto> mostrarProductos(long idFactura) throws Exception;
}
