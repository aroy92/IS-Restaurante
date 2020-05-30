package app.presentacion.comandos.cliente;

import app.negocio.cliente.ClienteSA;
import app.negocio.cliente.TCliente;
import app.negocio.factoriaSA.FactoriaSA;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosCliente;

public class AltaClienteCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		TCliente cliente = (TCliente) objeto;
		ClienteSA sa = FactoriaSA.getInstance().generarClienteSA();
		String mensaje;
		Contexto contexto;
		
		try {
			sa.alta(cliente);
			mensaje = " Cliente dado de alta corretamente. Su ID es: " + cliente.getID() + ". ";
			contexto = new Contexto(EventosCliente.ALTA_CLIENTE_OK, mensaje);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosCliente.ALTA_CLIENTE_KO, mensaje);
		}
		
		return contexto;
	}
}