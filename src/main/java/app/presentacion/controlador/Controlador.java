package app.presentacion.controlador;

import app.presentacion.contexto.Contexto;

public abstract class Controlador {
	private static Controlador instance;

	public static Controlador getInstance() {
		if (instance == null) {	// Aplicando patrón Singletón
			instance = new ControladorImpl();
		}

		return instance;
	}

	public abstract void accion(Contexto contexto);
}