package app.integracion.factoriaDAO;

import app.integracion.turno.TurnoDAO;
import app.integracion.turno.TurnoDAOImpl;

public class FactoriaDAOImpl extends FactoriaDAO{

	@Override
	public TurnoDAO generarTurnoDAO() {
		return new TurnoDAOImpl();
	}
}