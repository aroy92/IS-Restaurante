package app.presentacion.menuGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.presentacion.contexto.Contexto;
import app.presentacion.controlador.Controlador;
import app.presentacion.eventos.EventosMenu;
import app.presentacion.factoriaGUI.GUI;

public class MenuGUI extends JFrame implements GUI{
	
	private static final long serialVersionUID = 1L;

	public MenuGUI() {
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
		
		barra.setBackground(new Color(66, 86, 98));
		panelMenuBoton.setBackground(new Color(66, 86, 98));
		panelSalir.setBackground(new Color(66, 86, 98));
		fondo.setBackground(new Color(225, 225, 225));
		
		barra.setLayout(new BorderLayout());
		fondo.setLayout(new FlowLayout(FlowLayout.CENTER, 20,250));
		
		JButton menuBoton = crearBotonMenu("MENÚ");
		menuBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				Contexto contexto = new Contexto(EventosMenu.MOSTRAR_MENU_GUI, null);
				Controlador.getInstance().accion(contexto);
			}
		});
		
		JButton salir = crearBotonSalir();
		
		panelMenuBoton.add(menuBoton);
		panelSalir.add(salir);
		barra.add(panelMenuBoton, BorderLayout.WEST);
		barra.add(panelSalir, BorderLayout.EAST);
		
		JButton turnosBoton = crearBoton(getClass().getClassLoader().getResource("iconos/menu/turnos.png").getPath(), new Color(243, 89, 63));
		turnosBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				Contexto contexto = new Contexto(EventosMenu.MOSTRAR_TURNO_GUI, null);
				Controlador.getInstance().accion(contexto);
			}
		});
		fondo.add(turnosBoton);
		
		JButton empleadosBoton = crearBoton(getClass().getClassLoader().getResource("iconos/menu/empleados.png").getPath(), new Color(0, 112, 192));
		empleadosBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				Contexto contexto = new Contexto(EventosMenu.MOSTRAR_EMPLEADO_GUI, null);
				Controlador.getInstance().accion(contexto);
			}
		});
		fondo.add(empleadosBoton);
		
		JButton clientesBoton = crearBoton(getClass().getClassLoader().getResource("iconos/menu/clientes.png").getPath(), new Color(91, 155, 213));
		clientesBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				Contexto contexto = new Contexto(EventosMenu.MOSTRAR_CLIENTE_GUI, null);
				Controlador.getInstance().accion(contexto);
			}
		});
		fondo.add(clientesBoton);
		
		JButton productosBoton = crearBoton(getClass().getClassLoader().getResource("iconos/menu/productos.png").getPath(), new Color(112, 173, 71));
		productosBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				Contexto contexto = new Contexto(EventosMenu.MOSTRAR_PRODUCTO_GUI, null);
				Controlador.getInstance().accion(contexto);
			}
		});
		fondo.add(productosBoton);
		
		JButton facturasBoton = crearBoton(getClass().getClassLoader().getResource("iconos/menu/facturas.png").getPath(), new Color(255, 192, 0));
		facturasBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				Contexto contexto = new Contexto(EventosMenu.MOSTRAR_FACTURA_GUI, null);
				Controlador.getInstance().accion(contexto);
			}
		});
		fondo.add(facturasBoton);
		
		add(barra, BorderLayout.NORTH);
		add(fondo, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public JButton crearBoton(String Path, Color color) {
		JButton boton = new JButton();
		boton.setPreferredSize(new Dimension(175, 100)); // 200, 125
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

	@Override
	public void actualizar(Contexto contexto) {
		//...
	}
}