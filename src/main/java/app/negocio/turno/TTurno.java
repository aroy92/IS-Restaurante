package app.negocio.turno;

import java.time.LocalTime;

public class TTurno {
	
	private int id;
	private String nombre;
	private LocalTime hora_inicio;
	private LocalTime hora_fin;
	private Boolean activo;
	
	// Sobrecarga de constructores.
	public TTurno() {}

	public TTurno(int id, String nombre, LocalTime hora_inicio, LocalTime hora_fin, Boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.hora_inicio = hora_inicio;
		this.hora_fin = hora_fin;
		this.activo = activo;
	}
	
	// GETTERS
	
	public int getID() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public LocalTime getHoraInicio() {
		return hora_inicio;
	}
	
	public LocalTime getHoraFin() {
		return hora_fin;
	}
	
	public Boolean getActivo() {
		return activo;
	}
	
	// SETTERS
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setHoraInicio(LocalTime hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	
	public void setHoraFin(LocalTime hora_fin) {
		this.hora_fin = hora_fin;
	}
	
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}