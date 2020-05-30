package app.integracion.factoriaDAO;

import app.integracion.cliente.ClienteDAO;
import app.integracion.empleado.EmpleadoDAO;
import app.integracion.factura.FacturaDAO;
import app.integracion.producto.ProductoDAO;
import app.integracion.turno.TurnoDAO;

public abstract class FactoriaDAO {
	private static FactoriaDAO dao;
	
	public static FactoriaDAO getInstance() {
		if(dao == null) {
			dao = new FactoriaDAOImpl();
		}
		return dao;
	}
	
	public abstract TurnoDAO generarTurnoDAO();
	public abstract EmpleadoDAO generarEmpleadoDAO();
	public abstract ClienteDAO generarClienteDAO();
	public abstract ProductoDAO generarProductoDAO();
	public abstract FacturaDAO generarFacturaDAO();
}