package app.integracion.turno;

import java.util.List;

import app.negocio.turno.TTurno;

public interface TurnoDAO {
	public void alta(TTurno turno) throws Exception;
	public void baja(int id) throws Exception;
	public void editar(TTurno turno) throws Exception;
	public TTurno mostrar(int id) throws Exception;
	public List<TTurno> listar() throws Exception;
	public TTurno readByID(int id) throws Exception;
	public TTurno readByNombre(String nombre) throws Exception;
}