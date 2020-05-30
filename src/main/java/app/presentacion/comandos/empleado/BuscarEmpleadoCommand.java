package app.presentacion.comandos.empleado;

import app.negocio.empleado.EmpleadoSA;
import app.negocio.empleado.TEmpleado;
import app.negocio.factoriaSA.FactoriaSA;
import app.presentacion.comandos.Command;
import app.presentacion.contexto.Contexto;
import app.presentacion.eventos.EventosEmpleado;

public class BuscarEmpleadoCommand implements Command{

	@Override
	public Contexto ejecutar(Object objeto) {
		int id = (int) objeto;
		TEmpleado empleado;
		EmpleadoSA sa = FactoriaSA.getInstance().generarEmpleadoSA();
		String mensaje;
		Contexto contexto;
		
		try {
			empleado = sa.mostrar(id);	// Reutilizo la accion de mostrar pues siguen la misma logica.
			contexto = new Contexto(EventosEmpleado.BUSCAR_EMPLEADO_OK, empleado);
		} catch(Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosEmpleado.BUSCAR_EMPLEADO_KO, mensaje);
		}
		
		return contexto;
	}
}