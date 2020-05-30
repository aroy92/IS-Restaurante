package app.negocio.producto;

import java.util.List;

public interface ProductoSA {
	public void alta(TProducto producto) throws Exception;
	public void baja(long id) throws Exception;
	public void editar(TProducto producto) throws Exception;
	public TProducto mostrar(long id) throws Exception;
	public List<TProducto> listar() throws Exception;
}
