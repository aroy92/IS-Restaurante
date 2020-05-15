package app.negocio.turno;

import java.util.List;

public interface TurnoSA {
	public void alta(TTurno turno) throws Exception;
	public void baja(int id) throws Exception;
	public void editar(TTurno turno) throws Exception;
	public TTurno mostrar(int id) throws Exception;
	public List<TTurno> listar() throws Exception;
}