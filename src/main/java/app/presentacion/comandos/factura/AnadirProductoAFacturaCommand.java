package app.presentacion.comandos.factura;

import app.negocio.factoriaSA.FactoriaSA;
import app.negocio.factura.FacturaSA;
import app.negocio.factura.TFacturaProducto;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosFactura;

public class AnadirProductoAFacturaCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		TFacturaProducto facturaProducto = (TFacturaProducto) objeto;
		FacturaSA sa = FactoriaSA.getInstance().generarFacturaSA();
		String mensaje;
		Contexto contexto;
		
		try {
			sa.addProducto(facturaProducto);
			mensaje = " Se ha añadido el producto a la factura correctamente. ";
			contexto = new Contexto(EventosFactura.ANADIR_PRODUCTO_A_FACTURA_OK, mensaje);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosFactura.ANADIR_PRODUCTO_A_FACTURA_KO, mensaje);
		}
		
		return contexto;
	}	
}