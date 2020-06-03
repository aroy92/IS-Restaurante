package app.presentacion.factoriaGUI;

import app.presentacion.clienteGUI.ClienteGUI;
import app.presentacion.empleadoGUI.EmpleadoGUI;
import app.presentacion.eventos.EventosMenu;
import app.presentacion.facturaGUI.FacturaGUI;
import app.presentacion.menuGUI.MenuGUI;
import app.presentacion.productoGUI.ProductoGUI;
import app.presentacion.turnoGUI.TurnoGUI;

public class FactoriaGUIImpl extends FactoriaGUI{
	
	public GUI generarGUI(int evento){
		GUI gui = null;
		switch(evento){
		case EventosMenu.MOSTRAR_MENU_GUI: gui = new MenuGUI(); break;
		case EventosMenu.MOSTRAR_TURNO_GUI: gui = new TurnoGUI(); break;
		case EventosMenu.MOSTRAR_EMPLEADO_GUI: gui = new EmpleadoGUI(); break;
		case EventosMenu.MOSTRAR_CLIENTE_GUI: gui = new ClienteGUI(); break;
		case EventosMenu.MOSTRAR_PRODUCTO_GUI: gui = new ProductoGUI(); break;
		case EventosMenu.MOSTRAR_FACTURA_GUI: gui = new FacturaGUI(); break;
		}
		return gui;
	}
}