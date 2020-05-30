package app.presentacion.comandos.empleado;

import app.negocio.empleado.EmpleadoSA;
import app.negocio.empleado.TEmpleado;
import app.negocio.factoriaSA.FactoriaSA;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosEmpleado;

public class EditarEmpleadoCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		TEmpleado empleado = (TEmpleado) objeto;
		EmpleadoSA sa = FactoriaSA.getInstance().generarEmpleadoSA();
		String mensaje;
		Contexto contexto;
		
		try {
			sa.editar(empleado);
			mensaje = " Se ha editado el empleado correctamente. ";
			contexto = new Contexto(EventosEmpleado.EDITAR_EMPLEADO_OK, mensaje);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosEmpleado.EDITAR_EMPLEADO_KO, mensaje);
		}
		
		return contexto;
	}
}