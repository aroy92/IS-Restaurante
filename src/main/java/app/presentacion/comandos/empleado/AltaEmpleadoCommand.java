package app.presentacion.comandos.empleado;

import app.negocio.empleado.EmpleadoSA;
import app.negocio.empleado.TEmpleado;
import app.negocio.factoriaSA.FactoriaSA;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosEmpleado;

public class AltaEmpleadoCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		TEmpleado empleado = (TEmpleado) objeto;
		EmpleadoSA sa = FactoriaSA.getInstance().generarEmpleadoSA();
		String mensaje;
		Contexto contexto;
		
		try {
			sa.alta(empleado);
			mensaje = " Empleado dado de alta corretamente. Su ID es: " + empleado.getId() + ". ";
			contexto = new Contexto(EventosEmpleado.ALTA_EMPLEADO_OK, mensaje);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosEmpleado.ALTA_EMPLEADO_KO, mensaje);
		}
		
		return contexto;
	}
}