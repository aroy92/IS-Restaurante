package app.integracion.empleado;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import app.negocio.empleado.TEmpleado;
import app.negocio.turno.TTurno;

public class EmpleadoDAOImpl implements EmpleadoDAO{
	
	private String URL;
	private String USER;
	private String PASSWORD;
	
	public EmpleadoDAOImpl() {
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
	public void alta(TEmpleado empleado) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement state = connection.prepareStatement("INSERT INTO empleados (nombre, dni, salario, activo, id_turno) values (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
		
		state.setString(1, empleado.getNombre());
		state.setString(2, empleado.getDNI());
		state.setDouble(3, empleado.getSalario());
		state.setBoolean(4, empleado.isActivo());
		state.setInt(5, empleado.getIdTurno());
		state.executeUpdate();
		
		ResultSet rs = state.getGeneratedKeys();

		if(rs.next()){
			empleado.setId(rs.getInt(1));
		}
		
		rs.close();
		state.close();
		connection.close();
	}

	@Override
	public void baja(int id) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement state = connection.prepareStatement("UPDATE empleados SET activo = false WHERE id = ?");
		
		state.setInt(1, id);
		state.executeUpdate();
		
		state.close();
		connection.close();
	}

	@Override
	public void editar(TEmpleado empleado) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		String sql = "UPDATE empleados SET nombre = ?, dni = ?, salario = ?, id_turno = ?, activo = ? WHERE id = ?";
		PreparedStatement state = connection.prepareStatement(sql);
		
		state.setString(1, empleado.getNombre());
		state.setString(2, empleado.getDNI());
		state.setFloat(3, empleado.getSalario());
		state.setInt(4, empleado.getIdTurno());
		state.setBoolean(5, empleado.isActivo());
		state.setInt(6, empleado.getId());
		
		state.executeUpdate();
		
		state.close();
		connection.close();
	}

	@Override
	public TEmpleado mostrar(int id) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement state = connection.prepareStatement("SELECT * FROM empleados WHERE id = ?");
		state.setInt(1, id);
		ResultSet rs = state.executeQuery();
		
		TEmpleado empleado = null;
		if(rs.next()) {
			empleado = new TEmpleado(rs.getInt("id"), rs.getString("nombre"), rs.getString("dni"), rs.getFloat("salario"), rs.getBoolean("activo"), rs.getInt("id_turno"));
		}
		
		rs.close();
		state.close();
		connection.close();
		
		return empleado;
	}

	@Override
	public List<TEmpleado> listar() throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		Statement state = connection.createStatement();
		ResultSet rs = state.executeQuery("SELECT * FROM empleados WHERE activo = true");
		
		List<TEmpleado> empleados = new LinkedList<>();
		
		while(rs.next()) {
			TEmpleado empleado = new TEmpleado(rs.getInt("id"), rs.getString("nombre"), rs.getString("dni"), rs.getFloat("salario"), rs.getBoolean("activo"), rs.getInt("id_turno"));
			empleados.add(empleado);
		}
		
		rs.close();
		state.close();
		connection.close();
		
		return empleados;
	}

	@Override
	public List<TEmpleado> listarPorTurno(int idTurno) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement state = connection.prepareStatement("SELECT * FROM empleados WHERE id_turno = ? AND activo = true");
		
		state.setInt(1, idTurno);
		
		ResultSet rs = state.executeQuery();
		
		List<TEmpleado> empleados = new LinkedList<>();
		
		while(rs.next()) {
			TEmpleado empleado = new TEmpleado(rs.getInt("id"), rs.getString("nombre"), rs.getString("dni"), rs.getFloat("salario"), rs.getBoolean("activo"), rs.getInt("id_turno"));
			empleados.add(empleado);
		}
		
		rs.close();
		state.close();
		connection.close();
		
		return empleados;
	}

	@Override
	public TTurno readByIDTurno(int idTurno) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement state = connection.prepareStatement("SELECT * FROM turnos WHERE id = ?");
		
		state.setInt(1, idTurno);
		
		ResultSet rs = state.executeQuery();
		
		TTurno turno = null;
		if (rs.next()) {
			turno = new TTurno(rs.getInt("id"), rs.getString("nombre"), LocalTime.parse(rs.getString("hora_inicio")), LocalTime.parse(rs.getString("hora_fin")), rs.getBoolean("activo"));
		}
		
		rs.close();
		state.close();
		connection.close();
		
		return turno;
	}

	@Override
	public TEmpleado readByID(int id) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement state = connection.prepareStatement("SELECT * FROM empleados WHERE id = ?");
		
		state.setInt(1, id);
		
		ResultSet rs = state.executeQuery();
		
		TEmpleado empleado = null;
		if (rs.next()) {
			empleado = new TEmpleado(rs.getInt("id"), rs.getString("nombre"), rs.getString("dni"), rs.getFloat("salario"), rs.getBoolean("activo"), rs.getInt("id_turno"));
		}
		
		rs.close();
		state.close();
		connection.close();
		
		return empleado;
	}

	@Override
	public TEmpleado readByDNI(String dni) throws Exception {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement state = connection.prepareStatement("SELECT * FROM empleados WHERE dni = ?");
		
		state.setString(1, dni);
		
		ResultSet rs = state.executeQuery();
		
		TEmpleado empleado = null;
		if (rs.next()) {
			empleado = new TEmpleado(rs.getInt("id"), rs.getString("nombre"), rs.getString("dni"), rs.getFloat("salario"), rs.getBoolean("activo"), rs.getInt("id_turno"));
		}
		
		rs.close();
		state.close();
		connection.close();
		
		return empleado;
	}
}