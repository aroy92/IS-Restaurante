package app.integracion.factoriaDAO;

import app.integracion.turno.TurnoDAO;

public abstract class FactoriaDAO {
	private static FactoriaDAO dao;
	
	public static FactoriaDAO getInstance() {
		if(dao == null) {
			dao = new FactoriaDAOImpl();
		}
		return dao;
	}
	
	public abstract TurnoDAO generarTurnoDAO();
}