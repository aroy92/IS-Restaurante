package app.presentacion.empleadoGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import app.negocio.empleado.TEmpleado;
import app.presentacion.contexto.Contexto;
import app.presentacion.controlador.Controlador;
import app.presentacion.eventos.EventosEmpleado;
import app.presentacion.eventos.EventosMenu;
import app.presentacion.factoriaGUI.GUI;

public class EmpleadoGUI extends JFrame implements GUI{


	private static final long serialVersionUID = 1L;
	
	private JLabel mensaje;
	private DefaultTableModel dtm;
	private JTable tabla;
	
	private TEmpleado empleadoBuscar;

	public EmpleadoGUI() {
		vista();
	}

	private void vista() {
		setSize(1080, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel fondo = new JPanel();
		JPanel barra = new JPanel();		
		JPanel panelMenuBoton = new JPanel();
		JPanel panelSalir = new JPanel();
		JPanel panelBotones = new JPanel();
		JPanel panelTabla = new JPanel();
		JPanel panelMensaje = new JPanel();
		
		mensaje = new JLabel(" ");
		mensaje.setFont(new Font("Arial", Font.PLAIN, 18));
		mensaje.setForeground(new Color(255, 255, 255));
		JLabel modulo = new JLabel(" > EMPLEADO");
		modulo.setFont(new Font("Arial", Font.PLAIN, 18));
		modulo.setForeground(new Color(255, 255, 255));
		
		barra.setBackground(new Color(66, 86, 98));
		panelMenuBoton.setBackground(new Color(66, 86, 98));
		panelSalir.setBackground(new Color(66, 86, 98));
		fondo.setBackground(new Color(225, 225, 225));
		panelBotones.setBackground(new Color(125, 125, 125));
		
		barra.setLayout(new BorderLayout());
		fondo.setLayout(new BorderLayout());
		panelTabla.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
		panelMensaje.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 20));
		
		// Creación del botón de menú
		JButton menuBoton = crearBotonMenu("MENÚ");
		menuBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				Contexto contexto = new Contexto(EventosMenu.MOSTRAR_MENU_GUI, null);
				Controlador.getInstance().accion(contexto);
			}
		});
		
		JButton salir = crearBotonSalir();
		//--------------------------------
		
		panelMenuBoton.add(menuBoton);
		panelMenuBoton.add(modulo);
		panelSalir.add(salir);
		barra.add(panelMenuBoton, BorderLayout.WEST);
		barra.add(panelSalir, BorderLayout.EAST);
		
		// Creación de la tabla.
		String[] nombreColummnas = { "ID", "Nombre", "DNI", "Salario", "Activo", "ID Turno"};
		dtm = new DefaultTableModel(null, nombreColummnas);
		tabla = new JTable(dtm);
		tabla.setPreferredSize(new Dimension(800, 400));
		JScrollPane scroll = new JScrollPane(tabla);
		panelTabla.add(scroll);
		// -----------------------
		
		panelMensaje.add(mensaje);
		
		fondo.add(panelTabla, BorderLayout.CENTER);
		fondo.add(panelMensaje, BorderLayout.SOUTH);
		
		// Panel de botones.		
		JButton altaBoton = crearBoton(getClass().getClassLoader().getResource("iconos/empleado/alta.png").getPath(), new Color(243, 89, 63));
		altaBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JTextField nombreField = new JTextField();
				JTextField dniField = new JTextField();
				JTextField salarioField = new JTextField();
				JTextField turnoField = new JTextField();
				Object[] mensaje = {
						"Nombre:", nombreField,
						"DNI:", dniField,
						"Salario:", salarioField,
						"ID Turno:", turnoField,
				};
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "ALTA EMPLEADO", JOptionPane.OK_CANCEL_OPTION);
				if(opcion == JOptionPane.OK_OPTION) {
					try {
						if(nombreField.getText() != null && dniField.getText() != null && salarioField.getText() != null && turnoField.getText() != null && !nombreField.getText().equalsIgnoreCase("") && !dniField.getText().equalsIgnoreCase("") && !salarioField.getText().equalsIgnoreCase("") && !turnoField.getText().equalsIgnoreCase("")) {
							TEmpleado empleado = new TEmpleado();
							empleado.setNombre(nombreField.getText());
							empleado.setDNI(dniField.getText());
							empleado.setSalario(Float.parseFloat(salarioField.getText()));
							empleado.setIdTurno(Integer.parseInt(turnoField.getText()));
							Contexto contexto = new Contexto(EventosEmpleado.ALTA_EMPLEADO, empleado);
							Controlador.getInstance().accion(contexto);
						}
						else {
							JOptionPane.showMessageDialog(null, "Datos introducidos incorrectos.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
						}
					} catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Datos introducidos incorrectos.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		panelBotones.add(altaBoton);
		
		JButton bajasBoton = crearBoton(getClass().getClassLoader().getResource("iconos/empleado/baja.png").getPath(), new Color(0, 112, 192));
		bajasBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "BAJA EMPLEADO", JOptionPane.QUESTION_MESSAGE);
				try {
					if(idString != null) {
						int id = Integer.parseInt(idString);
						Contexto contexto = new Contexto(EventosEmpleado.BAJA_EMPLEADO, id);
						Controlador.getInstance().accion(contexto);
					}
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelBotones.add(bajasBoton);
		
		JButton editarBoton = crearBoton(getClass().getClassLoader().getResource("iconos/empleado/editar.png").getPath(), new Color(91, 155, 213));
		editarBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// La operacion de EDITAR se divide en dos partes:
				// 1. A partir del ID introducido, buscamos si existe o no el Empleado. (Sigue la misma lógica que MOSTRAR)
				String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "BUSCAR EMPLEADO", JOptionPane.QUESTION_MESSAGE);
				try {
					if(idString != null) {
						int id = Integer.parseInt(idString);
						Contexto contexto = new Contexto(EventosEmpleado.BUSCAR_EMPLEADO, id);
						Controlador.getInstance().accion(contexto);
					}
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				}
				
				// 2. Ya con la información cargada de Empleado (en caso de que exista), permitimos al usuario modificar sus campos.
				if (empleadoBuscar != null) {
					JTextField nombreField = new JTextField(empleadoBuscar.getNombre());
					JTextField dniField = new JTextField(empleadoBuscar.getDNI());
					JTextField salarioField = new JTextField(String.valueOf(empleadoBuscar.getSalario()));
					JTextField turnoField = new JTextField(String.valueOf(empleadoBuscar.getIdTurno()));
					Object[] mensaje = {
							"Nombre:", nombreField,
							"DNI:", dniField,
							"Salario:", salarioField,
							"ID Turno:", turnoField,
					};
					int opcion = JOptionPane.showConfirmDialog(null, mensaje, "EDITAR EMPLEADO", JOptionPane.OK_CANCEL_OPTION);
					if(opcion == JOptionPane.OK_OPTION) {
						try {
							if(nombreField.getText() != null && dniField.getText() != null && salarioField.getText() != null && turnoField.getText() != null && !nombreField.getText().equalsIgnoreCase("") && !dniField.getText().equalsIgnoreCase("") && !salarioField.getText().equalsIgnoreCase("") && !turnoField.getText().equalsIgnoreCase("")) {
								TEmpleado empleado = new TEmpleado();
								empleado.setId(empleadoBuscar.getId());
								empleado.setNombre(nombreField.getText());
								empleado.setDNI(dniField.getText());
								empleado.setSalario(Float.parseFloat(salarioField.getText()));
								empleado.setIdTurno(Integer.parseInt(turnoField.getText()));
								empleado.setActivo(empleadoBuscar.isActivo());
								Contexto contexto = new Contexto(EventosEmpleado.EDITAR_EMPLEADO, empleado);
								Controlador.getInstance().accion(contexto);
							}
						} catch(NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Datos introducidos incorrectos.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
						}
					}
					empleadoBuscar = null;
				}
			}
		});
		panelBotones.add(editarBoton);
		
		JButton mostrarBoton = crearBoton(getClass().getClassLoader().getResource("iconos/empleado/mostrar.png").getPath(), new Color(112, 173, 71));
		mostrarBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "MOSTRAR EMPLEADO", JOptionPane.QUESTION_MESSAGE);
				try {
					if(idString != null) {
						int id = Integer.parseInt(idString);
						Contexto contexto = new Contexto(EventosEmpleado.MOSTRAR_EMPLEADO, id);
						Controlador.getInstance().accion(contexto);
					}
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelBotones.add(mostrarBoton);
		
		JButton listarBoton = crearBoton(getClass().getClassLoader().getResource("iconos/empleado/listar.png").getPath(), new Color(255, 192, 0));
		listarBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Contexto contexto = new Contexto(EventosEmpleado.LISTAR_EMPLEADOS, null);
				Controlador.getInstance().accion(contexto);
			}
		});
		panelBotones.add(listarBoton);
		
		JButton listarPorTurnoBoton = crearBoton(getClass().getClassLoader().getResource("iconos/empleado/listarPorTurno.png").getPath(), new Color(112, 48, 160));
		listarPorTurnoBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String idString = JOptionPane.showInputDialog(null, "Introduce ID de turno:", "LISTAR EMPLEADOS POR TURNO", JOptionPane.QUESTION_MESSAGE);
				try {
					if(idString != null) {
						int id = Integer.parseInt(idString);
						Contexto contexto = new Contexto(EventosEmpleado.LISTAR_EMPLEADOS_TURNO, id);
						Controlador.getInstance().accion(contexto);
					}
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelBotones.add(listarPorTurnoBoton);
		//-----------------------------------------
		
		// Añado todos los paneles al JFrame.
		add(barra, BorderLayout.NORTH);
		add(fondo, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	public JButton crearBoton(String Path, Color color) {
		JButton boton = new JButton();
		boton.setPreferredSize(new Dimension(135, 60));
		boton.setBackground(color);
		boton.setBorder(null);
		boton.setFocusPainted(false);
		boton.setIcon(new ImageIcon(Path));

		return boton;
	}
	
	public JButton crearBotonMenu(String nombre) {
		JButton boton = new JButton(nombre);
		boton.setFocusPainted(false);
		boton.setBorderPainted(false);
		boton.setFont(new Font("Arial", Font.PLAIN, 18));
		boton.setBackground(new Color(255, 255, 255));
		boton.setMaximumSize(new Dimension(170, 50));
		return boton;
	}
	
	public JButton crearBotonSalir() {
		JButton boton = new JButton("X");
		boton.setFocusPainted(false);
		boton.setBorder(null);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
		boton.setPreferredSize(new Dimension(30, 30));
		boton.setFont(new Font("Corbel", Font.BOLD, 20));
		boton.setForeground(new Color(110, 120, 140));
		boton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea cerrar el programa?", "Salir", JOptionPane.YES_NO_OPTION);

				if(confirmacion == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		return boton;
	}
	
	public void limpiarJLabel() {
		mensaje.setText(" ");
		mensaje.setOpaque(false);
	}
	
	public void limpiarTabla() {
		for(int i = dtm.getRowCount() - 1; i >= 0; i--) {
			dtm.removeRow(i);
		}
	}

	@Override
	public void actualizar(Contexto contexto) {	
		// Antes de actualizar, limpiamos el contenido de mensaje (JLabel) y de dtm (DefaultTableModel) para que los resultados de operaciones anteriores
		// no se mezclen con el resultado de la operación actual.
		limpiarJLabel();
		limpiarTabla();
		
		switch(contexto.getEvento()) {
		case EventosEmpleado.ALTA_EMPLEADO_OK:
			mensaje.setText((String) contexto.getObjeto());
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(91, 186, 86));
			break;
		case EventosEmpleado.ALTA_EMPLEADO_KO:
			mensaje.setText((String) contexto.getObjeto());
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
			break;
		case EventosEmpleado.BAJA_EMPLEADO_OK:
			mensaje.setText((String) contexto.getObjeto());
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(91, 186, 86));
			break;
		case EventosEmpleado.BAJA_EMPLEADO_KO:
			mensaje.setText((String) contexto.getObjeto());
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
			break;
		case EventosEmpleado.EDITAR_EMPLEADO_OK: {
			mensaje.setText((String) contexto.getObjeto());
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(91, 186, 86));
		}; break;
		case EventosEmpleado.EDITAR_EMPLEADO_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosEmpleado.MOSTRAR_EMPLEADO_OK: {
			TEmpleado empleado = (TEmpleado) contexto.getObjeto();
			dtm.addRow(new Object[] {empleado.getId(), empleado.getNombre(), empleado.getDNI(), empleado.getSalario(), empleado.isActivo(), empleado.getIdTurno()});
		}; break;
		case EventosEmpleado.MOSTRAR_EMPLEADO_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosEmpleado.LISTAR_EMPLEADOS_OK: {
			@SuppressWarnings("unchecked") 	List<TEmpleado> lista = (List<TEmpleado>) contexto.getObjeto();
			for(TEmpleado e: lista) {
				dtm.addRow(new Object[] {e.getId(), e.getNombre(), e.getDNI(), e.getSalario(), e.isActivo(), e.getIdTurno()});
			}
		}; break;
		case EventosEmpleado.LISTAR_EMPLEADOS_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosEmpleado.LISTAR_EMPLEADOS_TURNO_OK: {
			@SuppressWarnings("unchecked") 	List<TEmpleado> lista = (List<TEmpleado>) contexto.getObjeto();
			for(TEmpleado e: lista) {
				dtm.addRow(new Object[] {e.getId(), e.getNombre(), e.getDNI(), e.getSalario(), e.isActivo(), e.getIdTurno()});
			}
		}; break;
		case EventosEmpleado.LISTAR_EMPLEADOS_TURNO_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosEmpleado.BUSCAR_EMPLEADO_OK: {
			empleadoBuscar = (TEmpleado) contexto.getObjeto();
		}; break;
		case EventosEmpleado.BUSCAR_EMPLEADO_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		}
	}
}