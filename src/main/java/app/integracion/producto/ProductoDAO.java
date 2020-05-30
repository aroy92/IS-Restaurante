package app.integracion.producto;

import java.util.List;

import app.negocio.producto.TProducto;

public interface ProductoDAO {
	public void alta(TProducto producto) throws Exception;
	public void baja(long id) throws Exception;
	public void editar(TProducto producto) throws Exception;
	public TProducto mostrar(long id) throws Exception;
	public List<TProducto> listar() throws Exception;
}
