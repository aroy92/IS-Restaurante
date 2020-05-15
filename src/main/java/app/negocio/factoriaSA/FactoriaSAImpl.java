package app.negocio.factoriaSA;

import app.negocio.turno.TurnoSA;
import app.negocio.turno.TurnoSAImpl;

public class FactoriaSAImpl extends FactoriaSA{

	@Override
	public TurnoSA generarTurnoSA() {
		return new TurnoSAImpl();
	}
	
	//---------
}