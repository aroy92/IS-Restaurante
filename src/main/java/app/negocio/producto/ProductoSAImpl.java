package app.negocio.producto;

import java.util.List;

import app.integracion.factoriaDAO.FactoriaDAO;
import app.integracion.producto.ProductoDAO;

public class ProductoSAImpl implements ProductoSA {

	@Override
	public void alta(TProducto producto) throws Exception {
		ProductoDAO dao = FactoriaDAO.getInstance().generarProductoDAO();
		
		if (producto.getPrecio() < 0)
			throw new Exception(" El precio no puede ser negativo. ");
		producto.setNombre(producto.getNombre().trim());
		if (producto.getNombre().length() == 0)
			throw new Exception(" El producto debe tener nombre. ");
		
		producto.setActivo(true);
		dao.alta(producto);
	}

	@Override
	public void baja(long id) throws Exception {
		ProductoDAO dao = FactoriaDAO.getInstance().generarProductoDAO();
		TProducto producto = dao.mostrar(id);
		
		if (producto == null)
			throw new Exception(" No existe un producto con el ID introducido. ");
		if (!producto.isActivo())
			throw new Exception(" El producto ya está eliminado. ");
		dao.baja(id);
	}

	@Override
	public void editar(TProducto producto) throws Exception {
		ProductoDAO dao = FactoriaDAO.getInstance().generarProductoDAO();
		if (dao.mostrar(producto.getId()) == null)
			throw new Exception(" No existe un producto con el ID introducido. ");
		if (producto.getPrecio() < 0)
			throw new Exception(" El precio no puede ser negativo. ");
		producto.setNombre(producto.getNombre().trim());
		if (producto.getNombre().length() == 0)
			throw new Exception(" El producto debe tener nombre. ");
		dao.editar(producto);
	}

	@Override
	public TProducto mostrar(long id) throws Exception {
		ProductoDAO dao = FactoriaDAO.getInstance().generarProductoDAO();
		TProducto producto = dao.mostrar(id);
		if (producto == null || !producto.isActivo())
			throw new Exception(" No existe un producto con el ID introducido. ");
		return producto;
	}

	@Override
	public List<TProducto> listar() throws Exception {
		ProductoDAO dao = FactoriaDAO.getInstance().generarProductoDAO();
		return dao.listar();
	}

}
