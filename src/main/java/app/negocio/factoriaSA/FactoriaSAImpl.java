package app.negocio.factoriaSA;

import app.negocio.cliente.ClienteSA;
import app.negocio.cliente.ClienteSAImpl;
import app.negocio.empleado.EmpleadoSA;
import app.negocio.empleado.EmpleadoSAImpl;
import app.negocio.producto.ProductoSA;
import app.negocio.producto.ProductoSAImpl;
import app.negocio.turno.TurnoSA;
import app.negocio.turno.TurnoSAImpl;

public class FactoriaSAImpl extends FactoriaSA {

	@Override
	public TurnoSA generarTurnoSA() {
		return new TurnoSAImpl();
	}
	
	@Override
	public EmpleadoSA generarEmpleadoSA() {
		return new EmpleadoSAImpl();
	}

	@Override
	public ClienteSA generarClienteSA() {
		return new ClienteSAImpl();
	}

	@Override
	public ProductoSA generarProductoSA() {
		return new ProductoSAImpl();
	}
	
	

	//---------
}