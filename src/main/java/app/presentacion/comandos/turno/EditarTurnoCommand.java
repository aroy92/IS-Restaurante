package app.presentacion.comandos.turno;

import app.negocio.factoriaSA.FactoriaSA;
import app.negocio.turno.TTurno;
import app.negocio.turno.TurnoSA;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosTurno;

public class EditarTurnoCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		TTurno turno = (TTurno) objeto;
		TurnoSA sa = FactoriaSA.getInstance().generarTurnoSA();
		String mensaje;
		Contexto contexto;
		
		try {
			sa.editar(turno);
			mensaje = " Se ha editado el turno correctamente. ";
			contexto = new Contexto(EventosTurno.EDITAR_TURNO_OK, mensaje);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosTurno.EDITAR_TURNO_KO, mensaje);
		}
		
		return contexto;
	}	
}