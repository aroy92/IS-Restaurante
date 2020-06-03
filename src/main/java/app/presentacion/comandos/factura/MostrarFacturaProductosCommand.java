package app.presentacion.comandos.factura;

import java.util.List;

import app.negocio.factoriaSA.FactoriaSA;
import app.negocio.factura.FacturaSA;
import app.negocio.factura.TFacturaProducto;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosFactura;

public class MostrarFacturaProductosCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		long idFactura = (long) objeto;
		FacturaSA sa = FactoriaSA.getInstance().generarFacturaSA();
		List<TFacturaProducto> productos;
		String mensaje;
		Contexto contexto;
		
		try {
			productos = sa.mostrarProductos(idFactura);
			contexto = new Contexto(EventosFactura.MOSTRAR_FACTURA_PRODUCTOS_OK, productos);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosFactura.MOSTRAR_FACTURA_PRODUCTOS_KO, mensaje);
		}
		
		return contexto;
	}
}