package app.presentacion.comandos.menu;

import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosMenu;

public class MostrarProductoGUICommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		return new Contexto( EventosMenu.MOSTRAR_PRODUCTO_GUI, null);
	}
}