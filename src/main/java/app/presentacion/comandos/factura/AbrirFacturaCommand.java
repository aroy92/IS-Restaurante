package app.presentacion.comandos.factura;

import app.negocio.factoriaSA.FactoriaSA;
import app.negocio.factura.FacturaSA;
import app.negocio.factura.TFactura;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosFactura;

public class AbrirFacturaCommand implements Command {

	@Override
	public Contexto ejecutar(Object objeto) {
		TFactura factura = (TFactura) objeto;
		FacturaSA sa = FactoriaSA.getInstance().generarFacturaSA();
		String mensaje;
		Contexto contexto;
		
		try {
			sa.abrir(factura);
			mensaje = " Factura abierta corretamente. Su ID es: " + factura.getId() + ". ";
			contexto = new Contexto(EventosFactura.ABRIR_FACTURA_OK, mensaje);
		} catch (Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosFactura.ABRIR_FACTURA_KO, mensaje);
		}
		
		return contexto;
	}
}