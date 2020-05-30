package app.presentacion.comandos.cliente;

import java.util.List;

import app.negocio.cliente.ClienteSA;
import app.negocio.cliente.TCliente;
import app.negocio.factoriaSA.FactoriaSA;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosCliente;

public class ListarClientesCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		ClienteSA sa = FactoriaSA.getInstance().generarClienteSA();
		String mensaje;
		Contexto contexto;
		
		try {
			List<TCliente> lista = sa.listar();
			contexto = new Contexto(EventosCliente.LISTAR_CLIENTES_OK, lista);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosCliente.LISTAR_CLIENTES_KO, mensaje);
		}
		
		return contexto;
	}
}