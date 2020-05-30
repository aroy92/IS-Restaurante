package app.presentacion.comandos.producto;

import app.negocio.factoriaSA.FactoriaSA;
import app.negocio.producto.ProductoSA;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosProducto;

public class BajaProductoCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		long id = (long) objeto;
		ProductoSA sa = FactoriaSA.getInstance().generarProductoSA();
		String mensaje;
		Contexto contexto;
		
		try {
			sa.baja(id);
			mensaje = " Producto dado de baja correctamente. ";
			contexto = new Contexto(EventosProducto.BAJA_PRODUCTO_OK, mensaje);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosProducto.BAJA_PRODUCTO_KO, mensaje);
		}
		
		return contexto;
	}
}