package app.presentacion.comandos.turno;

import app.negocio.factoriaSA.FactoriaSA;
import app.negocio.turno.TurnoSA;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosTurno;

public class BajaTurnoCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		int id = (int) objeto;
		TurnoSA sa = FactoriaSA.getInstance().generarTurnoSA();
		String mensaje;
		Contexto contexto;
		
		try {
			sa.baja(id);
			mensaje = " Se ha dado de baja el turno correctamente. ";
			contexto = new Contexto(EventosTurno.BAJA_TURNO_OK, mensaje);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosTurno.BAJA_TURNO_KO, mensaje);
		}
		
		return contexto;
	}
}