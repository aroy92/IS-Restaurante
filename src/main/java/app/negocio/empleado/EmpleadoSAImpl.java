package app.negocio.empleado;

import java.util.List;

import app.integracion.empleado.EmpleadoDAO;
import app.integracion.factoriaDAO.FactoriaDAO;
import app.negocio.turno.TTurno;

public class EmpleadoSAImpl implements EmpleadoSA{

	@Override
	public void alta(TEmpleado empleado) throws Exception {
		EmpleadoDAO dao = FactoriaDAO.getInstance().generarEmpleadoDAO();
		TEmpleado aux = null;
		TTurno turno = null;
		
		if (empleado.getSalario() < 0) {
			throw new Exception(" El salario no puede ser negativo. ");
		}
		
		turno = dao.readByIDTurno(empleado.getIdTurno());
		if (turno == null) {
			throw new Exception(" No se puede añadir un empleado en un turno inexistente. ");
		}

		aux = dao.readByDNI(empleado.getDNI());
		if (aux == null) {
			empleado.setActivo(true);
			dao.alta(empleado);
		} else if (aux.isActivo()) {
			throw new Exception(" No pueden existir dos empleados con el mismo DNI. ");
		} else {
			empleado.setId(aux.getId());	// Activamos de nuevo el empleado.
			empleado.setActivo(true);
			dao.editar(empleado);
		}
	}

	@Override
	public void baja(int id) throws Exception {
		EmpleadoDAO dao = FactoriaDAO.getInstance().generarEmpleadoDAO();
		TEmpleado empleado = null;
		
		empleado = dao.readByID(id);
		if (empleado != null && empleado.isActivo()) {
			dao.baja(id);
		} else if (empleado == null){
			throw new Exception(" No existe un empleado con el ID introducido. ");
		} else if (empleado != null && !empleado.isActivo()){
			throw new Exception(" El empleado seleccionado no esta activo. ");
		}
	}

	@Override
	public void editar(TEmpleado empleado) throws Exception {
		EmpleadoDAO dao = FactoriaDAO.getInstance().generarEmpleadoDAO();
		TEmpleado aux = null;
		TTurno turno = null;
		
		if(empleado.getSalario() < 0) {
			throw new Exception(" El salario no puede ser negativo. ");
		}
		
		turno = dao.readByIDTurno(empleado.getIdTurno());
		if(turno == null) {
			throw new Exception(" No se puede añadir un empleado en un turno inexistente. ");
		}
		
		aux = dao.readByDNI(empleado.getDNI());
		if(aux == null) {
			dao.editar(empleado);
		}
		else {
			if(empleado.getDNI().equalsIgnoreCase(aux.getDNI()) && empleado.getId() != aux.getId()) {
				throw new Exception(" No pueden existir dos empleados con el mismo DNI. ");
			}
			else {
				dao.editar(empleado);
			}
		}
	}

	@Override
	public TEmpleado mostrar(int id) throws Exception {
		EmpleadoDAO dao = FactoriaDAO.getInstance().generarEmpleadoDAO();
		TEmpleado empleado = null;
		
		empleado = dao.mostrar(id);
		if(empleado != null && empleado.isActivo()) {
			return empleado;
		}
		else {
			throw new Exception(" No existe un empleado con el ID introducido. ");
		}
	}

	@Override
	public List<TEmpleado> listar() throws Exception {
		EmpleadoDAO dao = FactoriaDAO.getInstance().generarEmpleadoDAO();
		List<TEmpleado> lista = null;
		
		lista = dao.listar();
		
		return lista;
	}

	@Override
	public List<TEmpleado> listarPorTurno(int id_turno) throws Exception {
		EmpleadoDAO dao = FactoriaDAO.getInstance().generarEmpleadoDAO();
		List<TEmpleado> lista = null;
		TTurno turno = null;
		
		turno = dao.readByIDTurno(id_turno);
		if(turno != null) {
			lista = dao.listarPorTurno(id_turno);
		}
		else {
			throw new Exception(" No existe un turno con el ID introducido. ");
		}
		
		return lista;
	}
}