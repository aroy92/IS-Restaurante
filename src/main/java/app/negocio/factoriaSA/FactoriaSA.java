package app.negocio.factoriaSA;

import app.negocio.cliente.ClienteSA;
import app.negocio.empleado.EmpleadoSA;
import app.negocio.factura.FacturaSA;
import app.negocio.producto.ProductoSA;
import app.negocio.turno.TurnoSA;

public abstract class FactoriaSA {
	private static FactoriaSA sa;
	
	public static FactoriaSA getInstance() {
		if(sa == null) {
			sa = new FactoriaSAImpl();
		}
		return sa;
	}
	
	public abstract TurnoSA generarTurnoSA();
	public abstract EmpleadoSA generarEmpleadoSA();
	public abstract ClienteSA generarClienteSA();
	public abstract ProductoSA generarProductoSA();
	public abstract FacturaSA generarFacturaSA();
}