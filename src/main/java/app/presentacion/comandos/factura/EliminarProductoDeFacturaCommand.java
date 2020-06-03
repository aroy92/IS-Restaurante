package app.presentacion.comandos.factura;

import app.negocio.factoriaSA.FactoriaSA;
import app.negocio.factura.FacturaSA;
import app.negocio.factura.TFacturaProducto;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosFactura;

public class EliminarProductoDeFacturaCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		TFacturaProducto facturaProducto = (TFacturaProducto) objeto;
		FacturaSA sa = FactoriaSA.getInstance().generarFacturaSA();
		String mensaje;
		Contexto contexto;
		
		try {
			sa.removeProducto(facturaProducto.getIdFactura(), facturaProducto.getIdProducto());
			mensaje = " Se ha eliminado el producto de la factura correctamente. ";
			contexto = new Contexto(EventosFactura.ELIMINAR_PRODUCTO_DE_FACTURA_OK, mensaje);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosFactura.ELIMINAR_PRODUCTO_DE_FACTURA_KO, mensaje);
		}
		
		return contexto;
	}
}