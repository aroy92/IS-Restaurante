package app.presentacion.controlador;

import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.despachador.DespachadorVista;
import app.presentacion.factoriaCommand.FactoriaCommand;

public class ControladorImpl extends Controlador{

	@Override
	public void accion(Contexto contexto) {
		Command comando = FactoriaCommand.getInstance().generarComando(contexto.getEvento());
		contexto = comando.ejecutar(contexto.getObjeto());
		DespachadorVista.getInstance().generarVista(contexto);
	}
}