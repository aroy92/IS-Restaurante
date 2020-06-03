package app.presentacion.factoriaCommand;

import app.presentacion.comandos.Command;
import app.presentacion.comandos.cliente.AltaClienteCommand;
import app.presentacion.comandos.cliente.ListarClientesCommand;
import app.presentacion.comandos.cliente.ListarFacturasClienteCommand;
import app.presentacion.comandos.empleado.AltaEmpleadoCommand;
import app.presentacion.comandos.empleado.BajaEmpleadoCommand;
import app.presentacion.comandos.empleado.BuscarEmpleadoCommand;
import app.presentacion.comandos.empleado.EditarEmpleadoCommand;
import app.presentacion.comandos.empleado.ListarEmpleadosCommand;
import app.presentacion.comandos.empleado.ListarEmpleadosTurnoCommand;
import app.presentacion.comandos.empleado.MostrarEmpleadoCommand;
import app.presentacion.comandos.factura.AbrirFacturaCommand;
import app.presentacion.comandos.factura.AnadirProductoAFacturaCommand;
import app.presentacion.comandos.factura.CerrarFacturaCommand;
import app.presentacion.comandos.factura.EliminarProductoDeFacturaCommand;
import app.presentacion.comandos.factura.MostrarFacturaCommand;
import app.presentacion.comandos.factura.MostrarFacturaProductosCommand;
import app.presentacion.comandos.menu.MostrarClienteGUICommand;
import app.presentacion.comandos.menu.MostrarEmpleadoGUICommand;
import app.presentacion.comandos.menu.MostrarFacturaGUICommand;
import app.presentacion.comandos.menu.MostrarMenuGUICommand;
import app.presentacion.comandos.menu.MostrarProductoGUICommand;
import app.presentacion.comandos.menu.MostrarTurnoGUICommand;
import app.presentacion.comandos.producto.AltaProductoCommand;
import app.presentacion.comandos.producto.BajaProductoCommand;
import app.presentacion.comandos.producto.BuscarProductoCommand;
import app.presentacion.comandos.producto.EditarProductoCommand;
import app.presentacion.comandos.producto.ListarProductoCommand;
import app.presentacion.comandos.producto.MostrarProductoCommand;
import app.presentacion.comandos.turno.AltaTurnoCommand;
import app.presentacion.comandos.turno.BajaTurnoCommand;
import app.presentacion.comandos.turno.BuscarTurnoCommand;
import app.presentacion.comandos.turno.EditarTurnoCommand;
import app.presentacion.comandos.turno.ListarTurnoCommand;
import app.presentacion.comandos.turno.MostrarTurnoCommand;
import app.presentacion.eventos.EventosCliente;
import app.presentacion.eventos.EventosEmpleado;
import app.presentacion.eventos.EventosFactura;
import app.presentacion.eventos.EventosMenu;
import app.presentacion.eventos.EventosProducto;
import app.presentacion.eventos.EventosTurno;

public class FactoriaCommandImpl extends FactoriaCommand{

	@Override
	public Command generarComando(int evento) {
		Command comando = null;
		
		switch(evento) {
		
		// Comandos de MEN�
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
		
		// Comandos de EMPLEADO
		case EventosEmpleado.ALTA_EMPLEADO: comando = new AltaEmpleadoCommand(); break;
		case EventosEmpleado.BAJA_EMPLEADO: comando = new BajaEmpleadoCommand(); break;
		case EventosEmpleado.EDITAR_EMPLEADO: comando = new EditarEmpleadoCommand(); break;
		case EventosEmpleado.MOSTRAR_EMPLEADO: comando = new MostrarEmpleadoCommand(); break;
		case EventosEmpleado.LISTAR_EMPLEADOS: comando = new ListarEmpleadosCommand(); break;
		case EventosEmpleado.LISTAR_EMPLEADOS_TURNO: comando = new ListarEmpleadosTurnoCommand(); break;
		case EventosEmpleado.BUSCAR_EMPLEADO: comando = new BuscarEmpleadoCommand(); break;
		
		// Comandos de CLIENTE
		case EventosCliente.ALTA_CLIENTE: comando = new AltaClienteCommand(); break;
		case EventosCliente.LISTAR_CLIENTES: comando = new ListarClientesCommand(); break;
		case EventosCliente.LISTAR_FACTURAS_CLIENTE: comando = new ListarFacturasClienteCommand(); break;
		
		// Comandos de PRODUCTO
		case EventosProducto.ALTA_PRODUCTO: comando = new AltaProductoCommand(); break;
		case EventosProducto.BAJA_PRODUCTO: comando = new BajaProductoCommand(); break;
		case EventosProducto.EDITAR_PRODUCTO: comando = new EditarProductoCommand(); break;
		case EventosProducto.MOSTRAR_PRODUCTO: comando = new MostrarProductoCommand(); break;
		case EventosProducto.LISTAR_PRODUCTOS: comando = new ListarProductoCommand(); break;
		case EventosProducto.BUSCAR_PRODUCTO: comando = new BuscarProductoCommand(); break;
		
		// Comandos de PRODUCTO
		case EventosFactura.ABRIR_FACTURA: comando = new AbrirFacturaCommand(); break;
		case EventosFactura.CERRAR_FACTURA: comando = new CerrarFacturaCommand(); break;
		case EventosFactura.ANADIR_PRODUCTO_A_FACTURA: comando = new AnadirProductoAFacturaCommand(); break;
		case EventosFactura.ELIMINAR_PRODUCTO_DE_FACTURA: comando = new EliminarProductoDeFacturaCommand(); break;
		case EventosFactura.MOSTRAR_FACTURA: comando = new MostrarFacturaCommand(); break;
		case EventosFactura.MOSTRAR_FACTURA_PRODUCTOS: comando = new MostrarFacturaProductosCommand(); break;
		}
		return comando;
	}
}