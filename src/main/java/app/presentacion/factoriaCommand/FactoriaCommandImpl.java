package app.presentacion.factoriaCommand;

import app.presentacion.comandos.Command;
import app.presentacion.comandos.menu.MostrarClienteGUICommand;
import app.presentacion.comandos.menu.MostrarEmpleadoGUICommand;
import app.presentacion.comandos.menu.MostrarFacturaGUICommand;
import app.presentacion.comandos.menu.MostrarMenuGUICommand;
import app.presentacion.comandos.menu.MostrarProductoGUICommand;
import app.presentacion.comandos.menu.MostrarTurnoGUICommand;
import app.presentacion.comandos.turno.AltaTurnoCommand;
import app.presentacion.comandos.turno.BajaTurnoCommand;
import app.presentacion.comandos.turno.BuscarTurnoCommand;
import app.presentacion.comandos.turno.EditarTurnoCommand;
import app.presentacion.comandos.turno.ListarTurnoCommand;
import app.presentacion.comandos.turno.MostrarTurnoCommand;
import app.presentacion.eventos.EventosMenu;
import app.presentacion.eventos.EventosTurno;

public class FactoriaCommandImpl extends FactoriaCommand{

	@Override
	public Command generarComando(int evento) {
		Command comando = null;
		
		switch(evento) {
		
		// Comandos de MENÚ
		case EventosMenu.MOSTRAR_MENU_GUI: comando = new MostrarMenuGUICommand(); break;
		case EventosMenu.MOSTRAR_TURNO_GUI: comando = new MostrarTurnoGUICommand(); break;
		case EventosMenu.MOSTRAR_EMPLEADO_GUI: comando = new MostrarEmpleadoGUICommand(); break;
		case EventosMenu.MOSTRAR_CLIENTE_GUI: comando = new MostrarClienteGUICommand(); break;
		case EventosMenu.MOSTRAR_PRODUCTO_GUI: comando = new MostrarProductoGUICommand(); break;
		case EventosMenu.MOSTRAR_FACTURA_GUI: comando = new MostrarFacturaGUICommand(); break;
		
		// Comandos de TURNO
		case EventosTurno.ALTA_TURNO: comando = new AltaTurnoCommand(); break;
		case EventosTurno.BAJA_TURNO: comando = new BajaTurnoCommand(); break;
		case EventosTurno.EDITAR_TURNO: comando = new EditarTurnoCommand(); break;
		case EventosTurno.MOSTRAR_TURNO: comando = new MostrarTurnoCommand(); break;
		case EventosTurno.LISTAR_TURNOS: comando = new ListarTurnoCommand(); break;
		case EventosTurno.BUSCAR_TURNO: comando = new BuscarTurnoCommand(); break;
		// Resto...
		}
		return comando;
	}
}