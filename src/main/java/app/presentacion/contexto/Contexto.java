package app.presentacion.contexto;

public class Contexto {
	
	private int evento;
	private Object objeto;

	public Contexto(int evento, Object objeto) {
		this.evento = evento;
		this.objeto = objeto;
	}
	
	public int getEvento() {
		return evento;
	}
		
	public Object getObjeto() {
		return objeto;
	}
	
	public void setEvento(int evento) {
		this.evento = evento;
	}
	
	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
}