package app.presentacion.despachador;

import app.presentacion.contexto.Contexto;

public abstract class DespachadorVista {
	private static DespachadorVista despachadorVista;
	
	public static DespachadorVista getInstance() {
		if(despachadorVista == null) {
			despachadorVista = new DespachadorVistaImpl();
		}
		return despachadorVista;
	}
	
	public abstract void generarVista(Contexto contexto);
}