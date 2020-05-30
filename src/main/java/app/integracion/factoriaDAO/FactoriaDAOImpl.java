package app.integracion.factoriaDAO;

import app.integracion.cliente.ClienteDAO;
import app.integracion.cliente.ClienteDAOImpl;
import app.integracion.empleado.EmpleadoDAO;
import app.integracion.empleado.EmpleadoDAOImpl;
import app.integracion.factura.FacturaDAO;
import app.integracion.factura.FacturaDAOImpl;
import app.integracion.producto.ProductoDAO;
import app.integracion.producto.ProductoDAOImpl;
import app.integracion.turno.TurnoDAO;
import app.integracion.turno.TurnoDAOImpl;

public class FactoriaDAOImpl extends FactoriaDAO{

	@Override
	public TurnoDAO generarTurnoDAO() {
		return new TurnoDAOImpl();
	}

	@Override
	public EmpleadoDAO generarEmpleadoDAO() {
		return new EmpleadoDAOImpl();
	}
	
	@Override
	public ClienteDAO generarClienteDAO() {
		return new ClienteDAOImpl();
	}

	@Override
	public ProductoDAO generarProductoDAO() {
		return new ProductoDAOImpl();
	}

	@Override
	public FacturaDAO generarFacturaDAO() {
		return new FacturaDAOImpl();
	}
}