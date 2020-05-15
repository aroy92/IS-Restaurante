package app.presentacion;

import javax.swing.SwingUtilities;

import app.presentacion.contexto.Contexto;
import app.presentacion.controlador.Controlador;
import app.presentacion.eventos.EventosMenu;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Contexto contexto = new Contexto(EventosMenu.MOSTRAR_MENU_GUI, null);
					Controlador.getInstance().accion(contexto);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}