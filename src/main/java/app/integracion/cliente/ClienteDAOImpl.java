package app.integracion.cliente;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import app.negocio.cliente.TCliente;
import app.negocio.factura.TFactura;

public class ClienteDAOImpl implements ClienteDAO{
	
	private String URL;
	private String USER;
	private String PASSWORD;
	
	public ClienteDAOImpl() {
        Properties prop = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties");
        if (is == null) {
          System.out.println("config.properties file not found");
        } else {
        	try {
        		prop.load(is);
        	} catch (IOException ex) {
    			System.out.println("Cannot open config.properties file: " + ex.getMessage());
    	    }
        }
        URL = "jdbc:mysql://" + prop.getProperty("db.url", "127.0.0.1") + ":" + prop.getProperty("db.port", "3306") + "/" + prop.getProperty("db.database", "restaurantedb");
        USER = "root";
        PASSWORD = "";
	}
	
	@Override
	public void alta(TCliente cliente) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement state = connection.prepareStatement("INSERT INTO clientes (nombre) values (?)", PreparedStatement.RETURN_GENERATED_KEYS);
		
		state.setString(1, cliente.getNombre());
		state.executeUpdate();
		
		ResultSet rs = state.getGeneratedKeys();

		if (rs.next()) {
			cliente.setID(rs.getInt(1));
		}
		
		rs.close();
		state.close();
		
		connection.close();
	}

	@Override
	public List<TCliente> listar() throws Exception {
		List<TCliente> clientes = new LinkedList<>();
		
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		Statement state = connection.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM clientes");
		
		while (rs.next()) {
			clientes.add(new TCliente(rs.getInt("id"), rs.getString("nombre")));
		}
		
		rs.close();
		state.close();
		connection.close();
		
		return clientes;
	}

	@Override
	public List<TFactura> listarFacturas(int id) throws Exception {
		List<TFactura> facturas = new LinkedList<>();
		
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement state = connection.prepareStatement("SELECT * FROM facturas WHERE id_cliente = ?");
		state.setInt(1, id);
		ResultSet rs = state.executeQuery();
		
		while(rs.next()) {
			TFactura factura = new TFactura();
			factura.setId(rs.getLong("id"));
			factura.setIdCliente(rs.getInt("id_cliente"));
			factura.setIdEmpleado(rs.getInt("id_empleado"));
			factura.setTotal(rs.getFloat("total"));
			factura.setFechaEmision(LocalDateTime.parse(rs.getString("fecha_emision").replace(' ', 'T')));
			factura.setCerrada(rs.getBoolean("cerrada"));
			
			facturas.add(factura);
		}
		
		rs.close();
		state.close();
		connection.close();
		
		return facturas;
	}

	@Override
	public TCliente readByNombre(String nombre) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement state = connection.prepareStatement("SELECT * FROM clientes WHERE nombre = ?");
		state.setString(1, nombre);
		ResultSet rs = state.executeQuery();
		TCliente cliente = null;
		if(rs.next()) {
			cliente = new TCliente(rs.getInt("id"), rs.getString("nombre"));
		}
		rs.close();
		state.close();
		connection.close();
		
		return cliente;
	}

	@Override
	public TCliente readById(int id) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement state = connection.prepareStatement("SELECT * FROM clientes WHERE id = ?");
		state.setInt(1, id);
		ResultSet rs = state.executeQuery();
		TCliente cliente = null;
		if (rs.next()) {
			cliente = new TCliente(rs.getInt("id"), rs.getString("nombre"));
		}
		rs.close();
		state.close();
		connection.close();
		
		return cliente;
	}
}