package app.presentacion.turnoGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
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

import app.negocio.turno.TTurno;
import app.presentacion.contexto.Contexto;
import app.presentacion.controlador.Controlador;
import app.presentacion.eventos.EventosMenu;
import app.presentacion.eventos.EventosTurno;
import app.presentacion.factoriaGUI.GUI;

public class TurnoGUI extends JFrame implements GUI{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel mensaje;
	private DefaultTableModel dtm;
	private JTable tabla;
	
	private TTurno turnoBuscar;

	public TurnoGUI() {
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
		JLabel modulo = new JLabel(" > TURNO");
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
		String[] nombreColummnas = { "ID", "Nombre", "Hora Inicio", "Hora Fin"};
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
		JButton altaBoton = crearBoton(getClass().getClassLoader().getResource("iconos/turno/alta.png").getPath(), new Color(243, 89, 63));
		altaBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JTextField nombreField = new JTextField();
				JTextField horaInicioField = new JTextField();
				JTextField horaFinField = new JTextField();
				Object[] mensaje = {
						"Nombre:", nombreField,
						"Hora inicio (HH:MM):", horaInicioField,
						"Hora fin (HH:MM):", horaFinField,
				};
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "ALTA TURNO", JOptionPane.OK_CANCEL_OPTION);
				if(opcion == JOptionPane.OK_OPTION) {
					try {
						if(nombreField.getText() != null && horaInicioField.getText() != null && horaFinField.getText() != null && !nombreField.getText().equalsIgnoreCase("") && !horaInicioField.getText().equalsIgnoreCase("") && !horaFinField.getText().equalsIgnoreCase("")) {
							TTurno turno = new TTurno();
							turno.setNombre(nombreField.getText());
							turno.setHoraInicio(LocalTime.parse(horaInicioField.getText()));
							turno.setHoraFin(LocalTime.parse(horaFinField.getText()));
							Contexto contexto = new Contexto(EventosTurno.ALTA_TURNO, turno);
							Controlador.getInstance().accion(contexto);
						}
						else {
							JOptionPane.showMessageDialog(null, "Nombre incorrecto.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
						}
					} catch(DateTimeParseException ex) {
						JOptionPane.showMessageDialog(null, "Datos introducidos incorrectos.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		panelBotones.add(altaBoton);
		
		JButton bajasBoton = crearBoton(getClass().getClassLoader().getResource("iconos/turno/baja.png").getPath(), new Color(0, 112, 192));
		bajasBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "BAJA TURNO", JOptionPane.QUESTION_MESSAGE);
				try {
					if(idString != null) {
						int id = Integer.parseInt(idString);
						Contexto contexto = new Contexto(EventosTurno.BAJA_TURNO, id);
						Controlador.getInstance().accion(contexto);
					}
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelBotones.add(bajasBoton);
		
		JButton editarBoton = crearBoton(getClass().getClassLoader().getResource("iconos/turno/editar.png").getPath(), new Color(91, 155, 213));
		editarBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// La operacion de EDITAR se divide en dos partes:
				// 1. A partir del ID introducido, buscamos si existe o no el Turno. (Sigue la misma lógica que MOSTRAR)
				String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "BUSCAR TURNO", JOptionPane.QUESTION_MESSAGE);
				try {
					if(idString != null) {
						int id = Integer.parseInt(idString);
						Contexto contexto = new Contexto(EventosTurno.BUSCAR_TURNO, id);
						Controlador.getInstance().accion(contexto);
					}
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				}
				
				// 2. Ya con la información cargada de Turno (en caso de que exista), permitimos al usuario modificar sus campos.
				if (turnoBuscar != null) {
					JTextField nombreField = new JTextField(turnoBuscar.getNombre());
					JTextField horaInicioField = new JTextField(turnoBuscar.getHoraInicio().toString());
					JTextField horaFinField = new JTextField(turnoBuscar.getHoraFin().toString());
					Object[] mensaje = {
							"Nombre:", nombreField,
							"Hora inicio (HH:MM):", horaInicioField,
							"Hora fin (HH:MM):", horaFinField,
					};
					int opcion = JOptionPane.showConfirmDialog(null, mensaje, "EDITAR TURNO", JOptionPane.OK_CANCEL_OPTION);
					if(opcion == JOptionPane.OK_OPTION) {
						try {
							if(nombreField.getText() != null && horaInicioField.getText() != null && horaFinField.getText() != null && nombreField.getText().equalsIgnoreCase("") && horaInicioField.getText().equalsIgnoreCase("") && horaFinField.getText().equalsIgnoreCase("")) {
								TTurno turno = new TTurno();
								turno.setID(turnoBuscar.getID());
								turno.setNombre(nombreField.getText());
								turno.setHoraInicio(LocalTime.parse(horaInicioField.getText()));
								turno.setHoraFin(LocalTime.parse(horaFinField.getText()));
								turno.setActivo(turnoBuscar.isActivo());
								Contexto contexto = new Contexto(EventosTurno.EDITAR_TURNO, turno);
								Controlador.getInstance().accion(contexto);
							}
						} catch(DateTimeParseException ex) {
							JOptionPane.showMessageDialog(null, "Datos introducidos incorrectos.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
						}
					}
					turnoBuscar = null;
				}
			}
		});
		panelBotones.add(editarBoton);
		
		JButton mostrarBoton = crearBoton(getClass().getClassLoader().getResource("iconos/turno/mostrar.png").getPath(), new Color(112, 173, 71));
		mostrarBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "MOSTRAR TURNO", JOptionPane.QUESTION_MESSAGE);
				try {
					if(idString != null) {
						int id = Integer.parseInt(idString);
						Contexto contexto = new Contexto(EventosTurno.MOSTRAR_TURNO, id);
						Controlador.getInstance().accion(contexto);
					}
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelBotones.add(mostrarBoton);
		
		JButton listarBoton = crearBoton(getClass().getClassLoader().getResource("iconos/turno/listar.png").getPath(), new Color(255, 192, 0));
		listarBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Contexto contexto = new Contexto(EventosTurno.LISTAR_TURNOS, null);
				Controlador.getInstance().accion(contexto);
			}
		});
		panelBotones.add(listarBoton);
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
		case EventosTurno.ALTA_TURNO_OK: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(91, 186, 86));
		}; break;
		case EventosTurno.ALTA_TURNO_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosTurno.BAJA_TURNO_OK: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(91, 186, 86));
		}; break;
		case EventosTurno.BAJA_TURNO_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosTurno.EDITAR_TURNO_OK: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(91, 186, 86));
		}; break;
		case EventosTurno.EDITAR_TURNO_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosTurno.MOSTRAR_TURNO_OK: {
			TTurno turno = (TTurno) contexto.getObjeto();
			dtm.addRow(new Object[] {turno.getID(), turno.getNombre(), turno.getHoraInicio(), turno.getHoraFin()});
		}; break;
		case EventosTurno.MOSTRAR_TURNO_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosTurno.LISTAR_TURNOS_OK: {
			@SuppressWarnings("unchecked") 	List<TTurno> lista = (List<TTurno>) contexto.getObjeto();
			for(TTurno t: lista) {
				dtm.addRow(new Object[] {t.getID(), t.getNombre(), t.getHoraInicio(), t.getHoraFin()});
			}
		}; break;
		case EventosTurno.LISTAR_TURNOS_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosTurno.BUSCAR_TURNO_OK: {
			turnoBuscar = (TTurno) contexto.getObjeto();
		}; break;
		case EventosTurno.BUSCAR_TURNO_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		}
	}
}