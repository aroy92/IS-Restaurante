package app.presentacion.comandos.turno;

import java.util.List;

import app.negocio.factoriaSA.FactoriaSA;
import app.negocio.turno.TTurno;
import app.negocio.turno.TurnoSA;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosTurno;

public class ListarTurnoCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		TurnoSA sa = FactoriaSA.getInstance().generarTurnoSA();
		String mensaje;
		Contexto contexto;
		
		try {
			List<TTurno> lista = sa.listar();
			contexto = new Contexto(EventosTurno.LISTAR_TURNOS_OK, lista);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosTurno.LISTAR_TURNOS_KO, mensaje);
		}
		
		return contexto;
	}
}