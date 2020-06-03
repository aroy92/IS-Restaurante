package app.presentacion.comandos.factura;

import app.negocio.factoriaSA.FactoriaSA;
import app.negocio.factura.FacturaSA;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosFactura;

public class CerrarFacturaCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		long id = (long) objeto;
		FacturaSA sa = FactoriaSA.getInstance().generarFacturaSA();
		String mensaje;
		Contexto contexto;
		
		try {
			sa.cerrar(id);
			mensaje = " Factura cerrada correctamente. ";
			contexto = new Contexto(EventosFactura.CERRAR_FACTURA_OK, mensaje);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosFactura.CERRAR_FACTURA_KO, mensaje);
		}
		
		return contexto;
	}
}