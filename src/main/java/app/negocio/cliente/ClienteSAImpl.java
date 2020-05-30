package app.negocio.cliente;

import java.util.List;

import app.integracion.cliente.ClienteDAO;
import app.integracion.factoriaDAO.FactoriaDAO;
import app.negocio.factura.TFactura;

public class ClienteSAImpl implements ClienteSA{

	@Override
	public void alta(TCliente cliente) throws Exception {
		ClienteDAO dao = FactoriaDAO.getInstance().generarClienteDAO();
		TCliente aux = null;
		
		aux = dao.readByNombre(cliente.getNombre());
		if(aux == null) {
			dao.alta(cliente);
		}
		else {
			throw new Exception(" No pueden existir dos clientes con el mismo nombre. ");
		}
	}

	@Override
	public List<TCliente> listar() throws Exception {
		ClienteDAO dao = FactoriaDAO.getInstance().generarClienteDAO();
		List<TCliente> lista = null;
		
		lista = dao.listar();
		
		return lista;
	}

	@Override
	public List<TFactura> listarFacturas(int id) throws Exception {
		ClienteDAO dao = FactoriaDAO.getInstance().generarClienteDAO();
		List<TFactura> lista = null;
		
		lista = dao.listarFacturas(id);
		
		return lista;
	}
}