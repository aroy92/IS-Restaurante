package app.presentacion.factoriaCommand;

import app.presentacion.comandos.Command;

public abstract class FactoriaCommand {
	
	private static FactoriaCommand factoriaComando;
	
	public static FactoriaCommand getInstance() {
		if(factoriaComando == null) {
			factoriaComando = new FactoriaCommandImpl();
		}
		return factoriaComando;
	}
	
	public abstract Command generarComando(int evento);
}