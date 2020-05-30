package app.negocio.factura;

public interface FacturaSA {
	public void alta(TFactura factura) throws Exception;
	public void baja(long id) throws Exception;
	public TFactura mostrar(long id) throws Exception;
	public void addProducto(TFacturaProducto facturaProducto) throws Exception;
	public void removeProducto(long idFactura, long idProducto) throws Exception;
}
