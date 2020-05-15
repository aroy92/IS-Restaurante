package app.presentacion.factoriaGUI;

import app.presentacion.eventos.EventosMenu;
import app.presentacion.menuGUI.MenuGUI;
import app.presentacion.turnoGUI.TurnoGUI;

public class FactoriaGUIImpl extends FactoriaGUI{
	
	public GUI generarGUI(int evento){
		GUI gui = null;
		switch(evento){
		case EventosMenu.MOSTRAR_MENU_GUI: gui = new MenuGUI(); break;
		case EventosMenu.MOSTRAR_TURNO_GUI: gui = new TurnoGUI(); break;
		// resto...
		//
		}
		return gui;
	}
}