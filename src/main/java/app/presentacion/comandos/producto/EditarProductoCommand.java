package app.presentacion.comandos.producto;

import app.negocio.factoriaSA.FactoriaSA;
import app.negocio.producto.ProductoSA;
import app.negocio.producto.TProducto;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosProducto;

public class EditarProductoCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		TProducto turno = (TProducto) objeto;
		ProductoSA sa = FactoriaSA.getInstance().generarProductoSA();
		String mensaje;
		Contexto contexto;
		
		try {
			sa.editar(turno);
			mensaje = " Se ha editado el producto correctamente. ";
			contexto = new Contexto(EventosProducto.EDITAR_PRODUCTO_OK, mensaje);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosProducto.EDITAR_PRODUCTO_KO, mensaje);
		}
		
		return contexto;
	}	
}