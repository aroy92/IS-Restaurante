package app.presentacion.comandos.factura;

import app.negocio.factoriaSA.FactoriaSA;
import app.negocio.factura.FacturaSA;
import app.negocio.factura.TFactura;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosFactura;

public class MostrarFacturaCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		long id = (long) objeto;
		TFactura factura;
		FacturaSA sa = FactoriaSA.getInstance().generarFacturaSA();
		String mensaje;
		Contexto contexto;
		
		try {
			factura = sa.mostrar(id);
			contexto = new Contexto(EventosFactura.MOSTRAR_FACTURA_OK, factura);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosFactura.MOSTRAR_FACTURA_KO, mensaje);
		}
		
		return contexto;
	}
}