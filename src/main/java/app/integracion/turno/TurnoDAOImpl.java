package app.integracion.turno;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import app.negocio.turno.TTurno;

public class TurnoDAOImpl implements TurnoDAO{
	
	private String URL;
	private String USER;
	private String PASSWORD;
	
	public TurnoDAOImpl() {
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
        USER = prop.getProperty("db.user", "root");
        PASSWORD = prop.getProperty("db.pass", "");
	}
	
	@Override
	public void alta(TTurno turno) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement state = connection.prepareStatement("INSERT INTO turnos (nombre, hora_inicio, hora_fin, activo) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
		
		state.setString(1, turno.getNombre());
		state.setString(2, turno.getHoraInicio().toString());
		state.setString(3, turno.getHoraFin().toString());
		state.setBoolean(4, turno.isActivo());
		state.executeUpdate();
		
		ResultSet rs = state.getGeneratedKeys();

		if(rs.next()){
			turno.setID(rs.getInt(1));
		}
		
		connection.close();
	}

	@Override
	public void baja(int id) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		Statement state = connection.createStatement();
		state.executeLargeUpdate("UPDATE turnos SET activo = false WHERE id = " + id);
		
		connection.close();
	}

	@Override
	public void editar(TTurno turno) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		Statement state = connection.createStatement();
		state.executeLargeUpdate("UPDATE turnos SET"
				+ " nombre = '" + turno.getNombre() + "',"
				+ " hora_inicio = '" + turno.getHoraInicio().toString() + "',"
				+ " hora_fin = '" + turno.getHoraFin().toString() + "',"
				+ " activo = " + turno.isActivo()
				+ " WHERE id = " + turno.getID()
				);
		
		connection.close();
	}

	@Override
	public TTurno mostrar(int id) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		Statement state = connection.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM turnos WHERE id = " + id);
		
		if(rs.next()) {
			TTurno turno = new TTurno(rs.getInt("id"), rs.getString("nombre"), LocalTime.parse(rs.getString("hora_inicio")), LocalTime.parse(rs.getString("hora_fin")), rs.getBoolean("activo"));
			return turno;
		}
		
		connection.close();
		
		return null;
	}

	@Override
	public List<TTurno> listar() throws Exception {
		List<TTurno> lista = new ArrayList<TTurno>();
		
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		Statement state = connection.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM turnos WHERE activo = true");
		
		while(rs.next()) {
			TTurno turno = new TTurno(rs.getInt("id"), rs.getString("nombre"), LocalTime.parse(rs.getString("hora_inicio")), LocalTime.parse(rs.getString("hora_fin")), rs.getBoolean("activo"));
			lista.add(turno);
		}
		
		connection.close();
		
		return lista;
	}

	@Override
	public TTurno readByID(int id) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		Statement state = connection.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM turnos WHERE id = " + id);
		
		if(rs.next()) {
			TTurno turno = new TTurno(rs.getInt("id"), rs.getString("nombre"), LocalTime.parse(rs.getString("hora_inicio")), LocalTime.parse(rs.getString("hora_fin")), rs.getBoolean("activo"));
			return turno;
		}
		
		connection.close();
		
		return null;
	}

	@Override
	public TTurno readByNombre(String nombre) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		Statement state = connection.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM turnos WHERE nombre = '" + nombre + "'" );
		
		if(rs.next()) {
			TTurno turno = new TTurno(rs.getInt("id"), rs.getString("nombre"), LocalTime.parse(rs.getString("hora_inicio")), LocalTime.parse(rs.getString("hora_fin")), rs.getBoolean("activo"));
			return turno;
		}
		
		connection.close();
		
		return null;
	}
}