package app.presentacion.comandos.producto;

import app.negocio.factoriaSA.FactoriaSA;
import app.negocio.producto.ProductoSA;
import app.negocio.producto.TProducto;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosProducto;

public class AltaProductoCommand implements Command {

	@Override
	public Contexto ejecutar(Object objeto) {
		TProducto producto = (TProducto) objeto;
		ProductoSA sa = FactoriaSA.getInstance().generarProductoSA();
		String mensaje;
		Contexto contexto;
		
		try {
			sa.alta(producto);
			mensaje = " Producto dado de alta corretamente. Su ID es: " + producto.getId() + ". ";
			contexto = new Contexto(EventosProducto.ALTA_PRODUCTO_OK, mensaje);
		} catch (Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosProducto.ALTA_PRODUCTO_KO, mensaje);
		}
		
		return contexto;
	}
}