package app.integracion.factura;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import app.negocio.factura.TFactura;
import app.negocio.factura.TFacturaProducto;

public class FacturaDAOImpl implements FacturaDAO {
	
	private String URL;
	private String USER;
	private String PASSWORD;
	
	public FacturaDAOImpl() {
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
        URL = "jdbc:mysql://" + prop.getProperty("db.url", "127.0.0.1") + ":" + prop.getProperty("db.port", "3306") + "/" + prop.getProperty("db.database", "restaurantedb") + "?useUnicode=true&serverTimezone=UTC";;
        USER = "root";
        PASSWORD = "";
	}

	@Override
	public void abrir(TFactura factura) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		
		PreparedStatement state = connection.prepareStatement("INSERT INTO facturas (id_cliente, id_empleado) values (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
		
		state.setInt(1, factura.getIdCliente());
		state.setInt(2, factura.getIdEmpleado());
		state.executeUpdate();
		ResultSet rs = state.getGeneratedKeys();
		
		if (rs.next()) {
			factura.setId(rs.getLong(1));
		}
		
		rs.close();
		state.close();
		
		state = connection.prepareStatement("SELECT fecha_emision FROM facturas WHERE id = ?");
		state.setLong(1, factura.getId());
		
		rs = state.executeQuery();
		
		if (rs.next()) {
			factura.setFechaEmision(LocalDateTime.parse(rs.getString("fecha_emision").replace(' ', 'T')));
		}
		
		rs.close();
		state.close();
		
		connection.close();
	}

	@Override
	public void cerrar(long id) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement state = connection.prepareStatement("UPDATE facturas SET cerrada = true WHERE id = ?");
		state.setLong(1, id);
		state.executeUpdate();
		state.close();
		connection.close();
	}

	@Override
	public TFactura mostrar(long id) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement state = connection.prepareStatement("SELECT facturas.*, fp.id_producto FROM facturas LEFT JOIN facturas_productos fp ON fp.id_factura = facturas.id WHERE facturas.id = ?");
		state.setLong(1, id);
		ResultSet rs = state.executeQuery();
		TFactura factura = null;
		List<Long> productos = new LinkedList<>();
		long idProducto;
		if (rs.next()) {
			factura = new TFactura(rs.getLong("id"), rs.getFloat("total"), LocalDateTime.parse(rs.getString("fecha_emision").replace(' ', 'T')), rs.getInt("id_cliente"), rs.getInt("id_empleado"), rs.getBoolean("cerrada"), productos);
			idProducto = rs.getLong("id_producto");
			if (idProducto > 0) {
				productos.add(idProducto);
			}
		}
		while (rs.next()) {
			productos.add(rs.getLong("id_producto"));
		}
		rs.close();
		state.close();
		connection.close();
		
		return factura;
	}

	@Override
	public void addProducto(TFacturaProducto facturaProducto) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		// Buscamos si ya existe el producto asignado a la factura
		PreparedStatement state = connection.prepareStatement("SELECT * FROM facturas_productos WHERE id_factura = ? AND id_producto = ?");
		
		state.setLong(1, facturaProducto.getIdFactura());
		state.setLong(2, facturaProducto.getIdProducto());
		
		ResultSet rs = state.executeQuery();
		
		// Si existe el producto asociado a la factura
		if (rs.next()) {
			double nuevoPrecio = facturaProducto.getPrecio() + rs.getDouble("precio");
			int nuevaCantidad = facturaProducto.getCantidad() + rs.getInt("cantidad");
			PreparedStatement state2 = connection.prepareStatement("UPDATE facturas_productos SET precio = ?, cantidad = ? WHERE id_factura = ? AND id_producto = ?");
			state2.setDouble(1, nuevoPrecio);
			state2.setInt(2, nuevaCantidad);
			state2.setLong(3, facturaProducto.getIdFactura());
			state2.setLong(4, facturaProducto.getIdProducto());
			state2.executeUpdate();
			state2.close();
		}
		// Si no existe
		else {
			PreparedStatement state2 = connection.prepareStatement("INSERT INTO facturas_productos (id_factura, id_producto, cantidad, precio) VALUES (?,?,?,?)");
			
			state2.setLong(1, facturaProducto.getIdFactura());
			state2.setLong(2, facturaProducto.getIdProducto());
			state2.setInt(3, facturaProducto.getCantidad());
			state2.setDouble(4, facturaProducto.getPrecio());
			
			state2.executeUpdate();
			state2.close();
		}
		
		rs.close();
		state.close();
		
		state = connection.prepareStatement("SELECT total FROM facturas WHERE id = ?");
		
		state.setLong(1, facturaProducto.getIdFactura());
		
		rs = state.executeQuery();
		rs.next();
		double total = rs.getDouble("total");
		rs.close();
		state.close();

		total += facturaProducto.getPrecio();
		state = connection.prepareStatement("UPDATE facturas SET total = ? WHERE id = ?");
		
		state.setDouble(1, total);
		state.setLong(2, facturaProducto.getIdFactura());
		
		state.executeUpdate();
			
		state.close();
		connection.close();
	}

	@Override
	public void removeProducto(long idFactura, long idProducto) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		
		PreparedStatement state = connection.prepareStatement("SELECT precio FROM facturas_productos WHERE id_factura = ? AND id_producto = ?");
		state.setLong(1, idFactura);
		state.setLong(2, idProducto);
		ResultSet rs = state.executeQuery();
		
		rs.next();
		double precio = rs.getDouble("precio");
		rs.close();
		state.close();
		
		state = connection.prepareStatement("DELETE FROM facturas_productos WHERE id_factura = ? AND id_producto = ?");
		state.setLong(1, idFactura);
		state.setLong(2, idProducto);
		state.executeUpdate();
		state.close();
		
		state = connection.prepareStatement("SELECT total FROM facturas WHERE id = ?");
		
		state.setLong(1, idFactura);
		
		rs = state.executeQuery();
		rs.next();
		double total = rs.getDouble("total");
		rs.close();
		state.close();

		total -= precio;
		state = connection.prepareStatement("UPDATE facturas SET total = ? WHERE id = ?");
		
		state.setDouble(1, total);
		state.setLong(2, idFactura);
		
		state.executeUpdate();
		
		state.close();
		
		connection.close();
	}
	
	@Override
	public List<TFacturaProducto> mostrarProductos(long idFactura) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement state = connection.prepareStatement("SELECT * FROM facturas_productos WHERE id_factura = ?");
		state.setLong(1, idFactura);
		ResultSet rs = state.executeQuery();
		List<TFacturaProducto> list = new LinkedList<>();
		TFacturaProducto p;
		while (rs.next()) {
			p = new TFacturaProducto(rs.getLong("id_factura"), rs.getLong("id_producto"), rs.getInt("cantidad"), rs.getDouble("precio"));
			list.add(p);
		}
		rs.close();
		state.close();
		connection.close();
		return list;
	}

}
