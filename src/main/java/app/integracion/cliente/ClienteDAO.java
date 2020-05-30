package app.integracion.cliente;

import java.util.List;

import app.negocio.cliente.TCliente;
import app.negocio.factura.TFactura;

public interface ClienteDAO {
	public void alta(TCliente cliente) throws Exception;
	public List<TCliente> listar() throws Exception;
	public List<TFactura> listarFacturas(int id) throws Exception;
	public TCliente readByNombre(String nombre) throws Exception;
	public TCliente readById(int id) throws Exception;
}