package app.presentacion.clienteGUI;

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

import app.negocio.cliente.TCliente;
import app.negocio.factura.TFactura;
import app.presentacion.contexto.Contexto;
import app.presentacion.controlador.Controlador;
import app.presentacion.eventos.EventosCliente;
import app.presentacion.eventos.EventosMenu;
import app.presentacion.factoriaGUI.GUI;

public class ClienteGUI extends JFrame implements GUI{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel mensaje;
	private DefaultTableModel dtm;
	private JTable tabla;

	public ClienteGUI() {
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
		JLabel modulo = new JLabel(" > CLIENTE");
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
		
		// Creaci�n del bot�n de men�
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
		
		// Creaci�n de la tabla.
		String[] nombreColummnas = { "ID", "Nombre"};
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
		JButton altaBoton = crearBoton(getClass().getClassLoader().getResource("iconos/cliente/alta.png").getPath(), new Color(243, 89, 63));
		altaBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JTextField nombreField = new JTextField();
				Object[] mensaje = {
						"Nombre:", nombreField,
				};
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "ALTA CLIENTE", JOptionPane.OK_CANCEL_OPTION);
				if(opcion == JOptionPane.OK_OPTION) {
					if(nombreField.getText() != null && !nombreField.getText().equalsIgnoreCase("")) {
						TCliente cliente = new TCliente();
						cliente.setNombre(nombreField.getText());
						Contexto contexto = new Contexto(EventosCliente.ALTA_CLIENTE, cliente);
						Controlador.getInstance().accion(contexto);
					}
					else {
						JOptionPane.showMessageDialog(null, "Datos introducidos incorrectos.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		panelBotones.add(altaBoton);
		
		JButton listarBoton = crearBoton(getClass().getClassLoader().getResource("iconos/cliente/listar.png").getPath(), new Color(112, 173, 71));
		listarBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Contexto contexto = new Contexto(EventosCliente.LISTAR_CLIENTES, null);
				Controlador.getInstance().accion(contexto);
			}
		});
		panelBotones.add(listarBoton);
		
		JButton listarFacturasBoton =  crearBoton(getClass().getClassLoader().getResource("iconos/cliente/listarFacturas.png").getPath(), new Color(255, 192, 0));
		listarFacturasBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String idString = JOptionPane.showInputDialog(null, "Introduce ID de cliente:", "LISTAR FACTURAS DE CLIENTE", JOptionPane.QUESTION_MESSAGE);
				try {
					if(idString != null) {
						int id = Integer.parseInt(idString);
						Contexto contexto = new Contexto(EventosCliente.LISTAR_FACTURAS_CLIENTE, id);
						Controlador.getInstance().accion(contexto);
					}
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelBotones.add(listarFacturasBoton);
		//-----------------------------------------
		
		// A�ado todos los paneles al JFrame.
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
		
		String[] nombreColummnas = { "ID", "Nombre"};
		dtm.setColumnCount(2);
		dtm.setColumnIdentifiers(nombreColummnas);
	}

	@Override
	public void actualizar(Contexto contexto) {	
		// Antes de actualizar, limpiamos el contenido de mensaje (JLabel) y de dtm (DefaultTableModel) para que los resultados de operaciones anteriores
		// no se mezclen con el resultado de la operaci�n actual.
		limpiarJLabel();
		limpiarTabla();
		
		switch(contexto.getEvento()) {
		case EventosCliente.ALTA_CLIENTE_OK: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(91, 186, 86));
		}; break;
		case EventosCliente.ALTA_CLIENTE_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosCliente.LISTAR_CLIENTES_OK: {
			@SuppressWarnings("unchecked") 	List<TCliente> lista = (List<TCliente>) contexto.getObjeto();
			for(TCliente c: lista) {
				dtm.addRow(new Object[] {c.getID(), c.getNombre()});
			}
		}; break;
		case EventosCliente.LISTAR_CLIENTES_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosCliente.LISTAR_FACTURAS_CLIENTE_OK: {
			@SuppressWarnings("unchecked") 	List<TFactura> lista = (List<TFactura>) contexto.getObjeto();
			String[] nombreColumnas = { "ID Factura", "Fecha emisión", "Precio total"};
			dtm.setColumnCount(3);
			dtm.setColumnIdentifiers(nombreColumnas);
			
			for(TFactura f: lista) {
				dtm.addRow(new Object[] {f.getId(), f.getFechaEmision(), f.getTotal()});
			}
		}; break;
		case EventosCliente.LISTAR_FACTURAS_CLIENTE_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		}
	}
}