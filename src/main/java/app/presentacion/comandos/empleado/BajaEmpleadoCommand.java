package app.presentacion.comandos.empleado;

import app.negocio.empleado.EmpleadoSA;
import app.negocio.factoriaSA.FactoriaSA;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosEmpleado;

public class BajaEmpleadoCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		int id = (int) objeto;
		EmpleadoSA sa = FactoriaSA.getInstance().generarEmpleadoSA();
		String mensaje;
		Contexto contexto;
		
		try {
			sa.baja(id);
			mensaje = " Se ha dado de baja el empleado correctamente. ";
			contexto = new Contexto(EventosEmpleado.BAJA_EMPLEADO_OK, mensaje);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosEmpleado.BAJA_EMPLEADO_KO, mensaje);
		}
		
		return contexto;
	}
}