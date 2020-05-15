package app.presentacion.comandos.menu;

import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosMenu;

public class MostrarTurnoGUICommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		return new Contexto( EventosMenu.MOSTRAR_TURNO_GUI, null);
	}
}