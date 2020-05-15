package app.presentacion.despachador;

import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosMenu;
import app.presentacion.factoriaGUI.FactoriaGUI;
import app.presentacion.factoriaGUI.GUI;

public class DespachadorVistaImpl extends DespachadorVista{

	private GUI vista;
	
	@Override
	public void generarVista(Contexto contexto) {
		switch(contexto.getEvento()) {
		case EventosMenu.MOSTRAR_MENU_GUI: vista = FactoriaGUI.getInstance().generarGUI(contexto.getEvento()); break;
		case EventosMenu.MOSTRAR_TURNO_GUI: vista = FactoriaGUI.getInstance().generarGUI(contexto.getEvento()); break;
		case EventosMenu.MOSTRAR_EMPLEADO_GUI: vista = FactoriaGUI.getInstance().generarGUI(contexto.getEvento()) ; break;
		case EventosMenu.MOSTRAR_CLIENTE_GUI: vista = FactoriaGUI.getInstance().generarGUI(contexto.getEvento()); break;
		case EventosMenu.MOSTRAR_PRODUCTO_GUI: vista = FactoriaGUI.getInstance().generarGUI(contexto.getEvento()); break;
		case EventosMenu.MOSTRAR_FACTURA_GUI: vista = FactoriaGUI.getInstance().generarGUI(contexto.getEvento()); break;
		default: vista.actualizar(contexto);
		}
	}
}