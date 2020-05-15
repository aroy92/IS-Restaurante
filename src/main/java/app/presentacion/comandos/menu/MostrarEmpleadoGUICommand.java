package app.presentacion.comandos.menu;

import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosMenu;

public class MostrarEmpleadoGUICommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		return new Contexto( EventosMenu.MOSTRAR_EMPLEADO_GUI, null);
	}
}