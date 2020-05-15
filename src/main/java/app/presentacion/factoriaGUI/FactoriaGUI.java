package app.presentacion.factoriaGUI;

public abstract class FactoriaGUI {
	
	private static FactoriaGUI presentacion;
	
	public static FactoriaGUI getInstance() {
		if(presentacion == null) {
			presentacion = new FactoriaGUIImpl();
		}
		return presentacion;
	}
	
	public abstract GUI generarGUI(int evento);
}