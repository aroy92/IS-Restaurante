package app.negocio.turno;

import java.util.List;

import app.integracion.empleado.EmpleadoDAO;
import app.integracion.factoriaDAO.FactoriaDAO;
import app.integracion.turno.TurnoDAO;
import app.negocio.empleado.TEmpleado;

public class TurnoSAImpl implements TurnoSA{

	@Override
	public void alta(TTurno turno) throws Exception {
		TurnoDAO dao = FactoriaDAO.getInstance().generarTurnoDAO();
		TTurno aux = null;
		
		if(turno.getHoraFin().isBefore(turno.getHoraInicio()) || turno.getHoraInicio().equals(turno.getHoraFin())) {
			throw new Exception(" La hora de inicio no puede ser posterior o igual a la hora de fin. ");
		}
		
		aux = dao.readByNombre(turno.getNombre());
		if(aux == null) {
			turno.setActivo(true);
			dao.alta(turno);
		}
		else {
			if(aux.isActivo()) {
				throw new Exception(" No pueden existir dos turnos con el mismo nombre. ");
			}
			else {
				turno.setID(aux.getID());	// Activamos de nuevo el turno.
				turno.setActivo(true);
				dao.editar(turno);
			}
		}
	}

	@Override
	public void baja(int id) throws Exception {
		TurnoDAO dao = FactoriaDAO.getInstance().generarTurnoDAO();
		EmpleadoDAO daoEmpleado = FactoriaDAO.getInstance().generarEmpleadoDAO();
		TTurno turno = null;
		List<TEmpleado> lista = null;
		
		turno = dao.readByID(id);
		
		if(turno != null && turno.isActivo()) {
			lista = daoEmpleado.listarPorTurno(id);
			if(lista.size() == 0) {
				dao.baja(id);
			}
			else {
				throw new Exception(" No puedes dar de baja un turno con empleados asignados. ");
			}
		}
		else if(turno == null){
			throw new Exception(" No existe un turno con el ID introducido. ");
		}
		else if (turno != null && !turno.isActivo()){
			throw new Exception(" El turno seleccionado no esta activo. ");
		}
	}

	@Override
	public void editar(TTurno turno) throws Exception {
		TurnoDAO dao = FactoriaDAO.getInstance().generarTurnoDAO();
		TTurno aux = null;
		
		if(turno.getHoraFin().isBefore(turno.getHoraInicio()) || turno.getHoraInicio().equals(turno.getHoraFin())) {
			throw new Exception(" La hora de inicio no puede ser posterior o igual a la hora de fin. ");
		}
		
		aux = dao.readByNombre(turno.getNombre());
		if(aux == null) {
			dao.editar(turno);
		}
		else {
			if(turno.getNombre().equalsIgnoreCase(aux.getNombre()) && turno.getID() != aux.getID()) {
				throw new Exception(" No pueden existir dos turnos con el mismo nombre. ");
			}
			else {
				dao.editar(turno);
			}
		}
	}

	@Override
	public TTurno mostrar(int id) throws Exception {
		TurnoDAO dao = FactoriaDAO.getInstance().generarTurnoDAO();
		TTurno turno = null;
		
		turno = dao.mostrar(id);
		if(turno != null && turno.isActivo()) {
			return turno;
		}
		else {
			throw new Exception(" No existe un turno con el ID introducido. ");
		}
	}

	@Override
	public List<TTurno> listar() throws Exception {
		TurnoDAO dao = FactoriaDAO.getInstance().generarTurnoDAO();
		List<TTurno> lista = null;
		
		lista = dao.listar();
		
		return lista;
	}
}