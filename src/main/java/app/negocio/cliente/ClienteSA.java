package app.negocio.cliente;

import java.util.List;

import app.negocio.factura.TFactura;

public interface ClienteSA {
	public void alta(TCliente cliente) throws Exception;
	public List<TCliente> listar() throws Exception;
	public List<TFactura> listarFacturas(int id) throws Exception;
}