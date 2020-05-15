package app.presentacion.comandos.turno;

import app.negocio.factoriaSA.FactoriaSA;
import app.negocio.turno.TTurno;
import app.negocio.turno.TurnoSA;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosTurno;

public class MostrarTurnoCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		int id = (int) objeto;
		TTurno turno;
		TurnoSA sa = FactoriaSA.getInstance().generarTurnoSA();
		String mensaje;
		Contexto contexto;
		
		try {
			turno = sa.mostrar(id);
			contexto = new Contexto(EventosTurno.MOSTRAR_TURNO_OK, turno);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosTurno.MOSTRAR_TURNO_KO, mensaje);
		}
		
		return contexto;
	}
}