package app.negocio.empleado;

import java.util.List;

public interface EmpleadoSA {
	public void alta(TEmpleado empleado) throws Exception;
	public void baja(int id) throws Exception;
	public void editar(TEmpleado empleado) throws Exception;
	public TEmpleado mostrar(int id) throws Exception;
	public List<TEmpleado> listar() throws Exception;
	public List<TEmpleado> listarPorTurno(int id_turno) throws Exception;
}