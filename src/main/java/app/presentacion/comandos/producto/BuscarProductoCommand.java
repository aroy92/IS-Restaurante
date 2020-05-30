package app.presentacion.comandos.producto;

import app.negocio.factoriaSA.FactoriaSA;
import app.negocio.producto.ProductoSA;
import app.negocio.producto.TProducto;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosProducto;

public class BuscarProductoCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		long id = (long) objeto;
		TProducto producto;
		ProductoSA sa = FactoriaSA.getInstance().generarProductoSA();
		String mensaje;
		Contexto contexto;
		
		try {
			producto = sa.mostrar(id); // Reutilizo la acción de mostrar pues siguen la misma lógica.
			contexto = new Contexto(EventosProducto.BUSCAR_PRODUCTO_OK, producto);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosProducto.BUSCAR_PRODUCTO_KO, mensaje);
		}
		
		return contexto;
	}
}