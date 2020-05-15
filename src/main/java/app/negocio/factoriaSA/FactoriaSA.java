package app.negocio.factoriaSA;

import app.negocio.turno.TurnoSA;

public abstract class FactoriaSA {
	private static FactoriaSA sa;
	
	public static FactoriaSA getInstance() {
		if(sa == null) {
			sa = new FactoriaSAImpl();
		}
		return sa;
	}
	
	public abstract TurnoSA generarTurnoSA();
}