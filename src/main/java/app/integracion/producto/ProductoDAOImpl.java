package app.integracion.producto;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import app.negocio.producto.TProducto;

public class ProductoDAOImpl implements ProductoDAO {
	
	private String URL;
	private String USER;
	private String PASSWORD;
	
	public ProductoDAOImpl() {
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
	public void alta(TProducto producto) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement state = connection.prepareStatement("INSERT INTO productos (nombre, precio) values (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
		
		state.setString(1, producto.getNombre());
		state.setFloat(2, producto.getPrecio());
		state.executeUpdate();
		
		ResultSet rs = state.getGeneratedKeys();

		if(rs.next()){
			producto.setId(rs.getLong(1));
		}
		
		rs.close();
		state.close();
		connection.close();
	}

	@Override
	public void baja(long id) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement state = connection.prepareStatement("UPDATE productos SET activo = FALSE WHERE id = ?");
		state.setLong(1, id);
		state.executeUpdate();
		
		state.close();
		connection.close();
	}

	@Override
	public void editar(TProducto producto) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement state = connection.prepareStatement("UPDATE productos SET nombre = ?, precio = ? WHERE id = ?");
		state.setString(1, producto.getNombre());
		state.setFloat(2, producto.getPrecio());
		state.setLong(3, producto.getId());
		state.executeUpdate();
		
		state.close();
		connection.close();
	}

	@Override
	public TProducto mostrar(long id) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		
		TProducto producto = null;
		
		PreparedStatement state = connection.prepareStatement("SELECT * FROM productos where id = ?");
		state.setLong(1, id);
		ResultSet rs = state.executeQuery();
		
		if (rs.next()) {
			producto = new TProducto(rs.getInt("id"), rs.getString("nombre"), rs.getFloat("precio"), rs.getBoolean("activo"));
		}
		
		rs.close();
		state.close();
		connection.close();
		return producto;
	}

	@Override
	public List<TProducto> listar() throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		
		List<TProducto> productos = new LinkedList<>();
		
		Statement state = connection.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM productos WHERE activo = true");
		
		while (rs.next()) {
			productos.add(new TProducto(rs.getInt("id"), rs.getString("nombre"), rs.getFloat("precio"), rs.getBoolean("activo")));
		}
		
		rs.close();
		state.close();
		connection.close();
		return productos;
	}

}
