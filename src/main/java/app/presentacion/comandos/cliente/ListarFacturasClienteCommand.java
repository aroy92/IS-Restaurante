package app.presentacion.comandos.cliente;

import java.util.List;

import app.negocio.cliente.ClienteSA;
import app.negocio.factoriaSA.FactoriaSA;
import app.negocio.factura.TFactura;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosCliente;

public class ListarFacturasClienteCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		int id = (int) objeto;
		ClienteSA sa = FactoriaSA.getInstance().generarClienteSA();
		String mensaje;
		Contexto contexto;
		
		try {
			List<TFactura> lista = sa.listarFacturas(id);
			contexto = new Contexto(EventosCliente.LISTAR_FACTURAS_CLIENTE_OK, lista);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosCliente.LISTAR_FACTURAS_CLIENTE_KO, mensaje);
		}
		return contexto;
	}
}