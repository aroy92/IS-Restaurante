package app.presentacion.controlador;

import app.presentacion.contexto.Contexto;

public abstract class Controlador {
	private static Controlador instance;

	public static Controlador getInstance() {
		if (instance == null) {	// Aplicando patr�n Singlet�n
			instance = new ControladorImpl();
		}

		return instance;
	}

	public abstract void accion(Contexto contexto);
}