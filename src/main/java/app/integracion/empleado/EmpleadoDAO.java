package app.integracion.empleado;

import java.util.List;

import app.negocio.empleado.TEmpleado;
import app.negocio.turno.TTurno;

public interface EmpleadoDAO {
	public void alta(TEmpleado empleado) throws Exception;
	public void baja(int id) throws Exception;
	public void editar(TEmpleado empleado) throws Exception;
	public TEmpleado mostrar(int id) throws Exception;
	public List<TEmpleado> listar() throws Exception;
	public List<TEmpleado> listarPorTurno(int id_turno) throws Exception;
	public TTurno readByIDTurno(int id_turno) throws Exception;
	public TEmpleado readByID(int id) throws Exception;
	public TEmpleado readByDNI(String dni) throws Exception;
}