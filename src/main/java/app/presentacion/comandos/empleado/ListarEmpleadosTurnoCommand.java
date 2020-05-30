package app.presentacion.comandos.empleado;

import java.util.List;

import app.negocio.empleado.EmpleadoSA;
import app.negocio.empleado.TEmpleado;
import app.negocio.factoriaSA.FactoriaSA;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosEmpleado;

public class ListarEmpleadosTurnoCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		int id_turno = (int) objeto;
		EmpleadoSA sa = FactoriaSA.getInstance().generarEmpleadoSA();
		String mensaje;
		Contexto contexto;
		
		try {
			List<TEmpleado> lista = sa.listarPorTurno(id_turno);
			contexto = new Contexto(EventosEmpleado.LISTAR_EMPLEADOS_TURNO_OK, lista);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosEmpleado.LISTAR_EMPLEADOS_TURNO_KO, mensaje);
		}
		
		return contexto;
	}
}