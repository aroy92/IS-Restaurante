package app.negocio.empleado;

public class TEmpleado {
	private int id;
	private String nombre;
	private String dni;
	private float salario;
	private Boolean activo;
	private int id_turno;
	
	public TEmpleado() {}
	
	public TEmpleado(int id, String nombre, String dni, float salario, Boolean activo, int id_turno) {
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.salario = salario;
		this.activo = activo;
		this.id_turno = id_turno;
	}
	
	// GETTERS
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDNI() {
		return dni;
	}
	
	public float getSalario() {
		return salario;
	}
	
	public Boolean isActivo() {
		return activo;
	}
	
	public int getIdTurno() {
		return id_turno;
	}
	
	// SETTERS
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setDNI(String dni) {
		this.dni = dni;
	}
	
	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	public void setIdTurno(int id_turno) {
		this.id_turno = id_turno;
	}
}