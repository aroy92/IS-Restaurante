package app.negocio.factura;

import java.util.Iterator;
import java.util.List;

import app.integracion.cliente.ClienteDAO;
import app.integracion.empleado.EmpleadoDAO;
import app.integracion.factoriaDAO.FactoriaDAO;
import app.integracion.factura.FacturaDAO;
import app.integracion.producto.ProductoDAO;
import app.negocio.cliente.TCliente;
import app.negocio.empleado.TEmpleado;
import app.negocio.producto.TProducto;

public class FacturaSAImpl implements FacturaSA {

	@Override
	public void abrir(TFactura factura) throws Exception {
		
		if (factura.getTotal() < 0)
			throw new Exception(" El total no puede ser negativo. ");
		
		ClienteDAO clienteDao = FactoriaDAO.getInstance().generarClienteDAO();
		TCliente cliente = clienteDao.readById(factura.getIdCliente());
		if (cliente == null)
			throw new Exception(" El cliente indicado no existe. ");
		
		EmpleadoDAO empleadoDao = FactoriaDAO.getInstance().generarEmpleadoDAO();
		TEmpleado empleado = empleadoDao.mostrar(factura.getIdEmpleado());
		if (empleado == null)
			throw new Exception(" El empleado indicado no existe. ");
		
		FacturaDAO facturaDao = FactoriaDAO.getInstance().generarFacturaDAO();
		facturaDao.abrir(factura);
		
	}

	@Override
	public void cerrar(long id) throws Exception {
		FacturaDAO facturaDao = FactoriaDAO.getInstance().generarFacturaDAO();
		TFactura factura = facturaDao.mostrar(id);
		if (factura == null)
			throw new Exception(" La factura indicada no existe. ");
		if (factura.isCerrada())
			throw new Exception(" La factura ya está cerrada. ");
		facturaDao.cerrar(id);
	}

	@Override
	public TFactura mostrar(long id) throws Exception {
		FacturaDAO facturaDao = FactoriaDAO.getInstance().generarFacturaDAO();
		TFactura factura = facturaDao.mostrar(id);
		if (factura == null)
			throw new Exception(" La factura indicada no existe. ");
		return factura;
	}

	@Override
	public void addProducto(TFacturaProducto facturaProducto) throws Exception {
		FacturaDAO facturaDao = FactoriaDAO.getInstance().generarFacturaDAO();
		TFactura factura = facturaDao.mostrar(facturaProducto.getIdFactura());
		if (factura == null)
			throw new Exception(" La factura indicada no existe. ");
		if (factura.isCerrada())
			throw new Exception(" La factura está cerrada y no puede modificarse. ");
		ProductoDAO productoDao = FactoriaDAO.getInstance().generarProductoDAO();
		TProducto producto = productoDao.mostrar(facturaProducto.getIdProducto());
		if (producto == null)
			throw new Exception(" El producto indicado no existe. ");
		if (facturaProducto.getCantidad() <= 0)
			throw new Exception(" La cantidad debe ser un entero positivo. ");
		double precio = producto.getPrecio() * facturaProducto.getCantidad();
		facturaProducto.setPrecio(precio);
		facturaDao.addProducto(facturaProducto);
	}

	@Override
	public void removeProducto(long idFactura, long idProducto) throws Exception {
		FacturaDAO facturaDao = FactoriaDAO.getInstance().generarFacturaDAO();
		TFactura factura = facturaDao.mostrar(idFactura);
		if (factura == null)
			throw new Exception(" La factura indicada no existe. ");
		if (factura.isCerrada())
			throw new Exception(" La factura está cerrada y no puede modificarse. ");
		ProductoDAO productoDao = FactoriaDAO.getInstance().generarProductoDAO();
		TProducto producto = productoDao.mostrar(idProducto);
		if (producto == null)
			throw new Exception(" El producto indicado no existe. ");
		List<Long> idProductos = factura.getProductos();
		boolean found = false;
		for (Iterator<Long> iterator = idProductos.iterator(); iterator.hasNext() && !found;) {
			Long id = iterator.next();
			if (id == idProducto) {
				found = true;
			}
		}
		if (found) {
			facturaDao.removeProducto(idFactura, idProducto);
		}
	}
	
	@Override
	public List<TFacturaProducto> mostrarProductos(long idFactura) throws Exception {
		FacturaDAO facturaDao = FactoriaDAO.getInstance().generarFacturaDAO();
		TFactura factura = facturaDao.mostrar(idFactura);
		if (factura == null)
			throw new Exception(" La factura indicada no existe. ");
		return facturaDao.mostrarProductos(idFactura);
	}

}
