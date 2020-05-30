package app.presentacion.comandos.producto;

import java.util.List;

import app.negocio.factoriaSA.FactoriaSA;
import app.negocio.producto.ProductoSA;
import app.negocio.producto.TProducto;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosProducto;

public class ListarProductoCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		ProductoSA sa = FactoriaSA.getInstance().generarProductoSA();
		String mensaje;
		Contexto contexto;
		
		try {
			List<TProducto> lista = sa.listar();
			contexto = new Contexto(EventosProducto.LISTAR_PRODUCTOS_OK, lista);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosProducto.LISTAR_PRODUCTOS_KO, mensaje);
		}
		
		return contexto;
	}
}