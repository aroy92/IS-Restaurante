package app.presentacion.productoGUI;

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

import app.negocio.producto.TProducto;
import app.presentacion.contexto.Contexto;
import app.presentacion.controlador.Controlador;
import app.presentacion.eventos.EventosMenu;
import app.presentacion.eventos.EventosProducto;
import app.presentacion.factoriaGUI.GUI;

public class ProductoGUI extends JFrame implements GUI{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel mensaje;
	private DefaultTableModel dtm;
	private JTable tabla;
	
	private TProducto productoBuscar;

	public ProductoGUI() {
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
		JLabel modulo = new JLabel(" > PRODUCTO");
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
		String[] nombreColummnas = { "ID", "Nombre", "Precio" };
		dtm = new DefaultTableModel(null, nombreColummnas);
		tabla = new JTable(dtm);
		tabla.setPreferredSize(new Dimension(800, 400));
		JScrollPane scroll = new JScrollPane(tabla);
		panelTabla.add(scroll);
		// -----------------------
		
		panelMensaje.add(mensaje);
		
		fondo.add(panelTabla, BorderLayout.CENTER);
		fondo.add(panelMensaje, BorderLayout.SOUTH);
		
		// Panel de botones
		JButton altaBoton = crearBoton(getClass().getClassLoader().getResource("iconos/turno/alta.png").getPath(), new Color(243, 89, 63));
		altaBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JTextField nombreField = new JTextField();
				JTextField precioField = new JTextField();
				Object[] mensaje = {
						"Nombre:", nombreField,
						"Precio:", precioField
				};
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "ALTA PRODUCTO", JOptionPane.OK_CANCEL_OPTION);
				if(opcion == JOptionPane.OK_OPTION) {
					try {
						String nombre = nombreField.getText().trim();
						String precio = precioField.getText().trim();
						boolean valid = true;
						String validationMessage = "";
						if (nombre.length() == 0) {
							validationMessage += "El campo \"Nombre\" no puede estar vacío.";
							valid = false;
						}
						if (precio.length() == 0) {
							if (validationMessage.length() != 0)
								validationMessage += "\n";
							validationMessage += "El campo \"Precio\" no puede estar vacío.";
							valid = false;
						} 
						if (!valid) {
							JOptionPane.showMessageDialog(null, validationMessage, "Mensaje de error", JOptionPane.WARNING_MESSAGE);
						} else {
							float precioFloat = Float.parseFloat(precio.replace(',', '.'));
							TProducto producto = new TProducto();
							producto.setNombre(nombre);
							producto.setPrecio(precioFloat);
							Contexto contexto = new Contexto(EventosProducto.ALTA_PRODUCTO, producto);
							Controlador.getInstance().accion(contexto);
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "El campo \"Precio\" debe ser un número válido.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		panelBotones.add(altaBoton);
		
		JButton bajasBoton = crearBoton(getClass().getClassLoader().getResource("iconos/turno/baja.png").getPath(), new Color(0, 112, 192));
		bajasBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "BAJA PRODUCTO", JOptionPane.QUESTION_MESSAGE);
					if(idString != null) {
						idString = idString.trim();
						if (idString.length() == 0) {
							JOptionPane.showMessageDialog(null, "El campo \"ID\" no debe estar vacío", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
						} else {
							long id = Long.parseLong(idString);
							Contexto contexto = new Contexto(EventosProducto.BAJA_PRODUCTO, id);
							Controlador.getInstance().accion(contexto);
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "El campo \"ID\" debe contener un número entero positivo", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelBotones.add(bajasBoton);
		
		JButton editarBoton = crearBoton(getClass().getClassLoader().getResource("iconos/turno/editar.png").getPath(), new Color(91, 155, 213));
		editarBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// La operacion de EDITAR se divide en dos partes:
				// 1. A partir del ID introducido, buscamos si existe o no el producto. (Sigue la misma lógica que MOSTRAR)
				try {
					String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "EDITAR PRODUCTO", JOptionPane.QUESTION_MESSAGE);
					if(idString != null) {
						idString = idString.trim();
						if (idString.length() == 0) {
							JOptionPane.showMessageDialog(null, "El campo \"ID\" no debe estar vacío", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
						} else {
							long id = Long.parseLong(idString);
							Contexto contexto = new Contexto(EventosProducto.BUSCAR_PRODUCTO, id);
							Controlador.getInstance().accion(contexto);
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "El campo \"ID\" debe contener un número entero positivo", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				}
				
				// 2. Ya con la información cargada de Turno (en caso de que exista), permitimos al usuario modificar sus campos.
				if (productoBuscar != null) {
					JTextField nombreField = new JTextField(productoBuscar.getNombre());
					JTextField precioField = new JTextField(productoBuscar.getPrecio() + "");
					Object[] mensaje = {
							"Nombre:", nombreField,
							"Precio:", precioField
					};
					int opcion = JOptionPane.showConfirmDialog(null, mensaje, "EDITAR PRODUCTO", JOptionPane.OK_CANCEL_OPTION);
					if (opcion == JOptionPane.OK_OPTION) {
						try {
							String nombre = nombreField.getText().trim();
							String precio = precioField.getText().trim();
							boolean valid = true;
							String validationMessage = "";
							if (nombre.length() == 0) {
								validationMessage += "El campo \"Nombre\" no puede estar vacío.";
								valid = false;
							}
							if (precio.length() == 0) {
								if (validationMessage.length() != 0)
									validationMessage += "\n";
								validationMessage += "El campo \"Precio\" no puede estar vacío.";
								valid = false;
							} 
							if (!valid) {
								JOptionPane.showMessageDialog(null, validationMessage, "Mensaje de error", JOptionPane.WARNING_MESSAGE);
							} else {
								float precioFloat = Float.parseFloat(precio.replace(',', '.'));
								TProducto producto = new TProducto(productoBuscar.getId(), nombre, precioFloat, productoBuscar.isActivo());
								Contexto contexto = new Contexto(EventosProducto.EDITAR_PRODUCTO, producto);
								Controlador.getInstance().accion(contexto);
							}
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "El campo \"Precio\" debe ser un número válido.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
						}
					}
					productoBuscar = null;
				}
			}
		});
		panelBotones.add(editarBoton);
		
		JButton mostrarBoton = crearBoton(getClass().getClassLoader().getResource("iconos/turno/mostrar.png").getPath(), new Color(112, 173, 71));
		mostrarBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "EDITAR PRODUCTO", JOptionPane.QUESTION_MESSAGE);
					if(idString != null) {
						idString = idString.trim();
						if (idString.length() == 0) {
							JOptionPane.showMessageDialog(null, "El campo \"ID\" no debe estar vacío", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
						} else {
							long id = Long.parseLong(idString);
							Contexto contexto = new Contexto(EventosProducto.MOSTRAR_PRODUCTO, id);
							Controlador.getInstance().accion(contexto);
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "El campo \"ID\" debe contener un número entero positivo", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelBotones.add(mostrarBoton);
		
		JButton listarBoton = crearBoton(getClass().getClassLoader().getResource("iconos/turno/listar.png").getPath(), new Color(255, 192, 0));
		listarBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Contexto contexto = new Contexto(EventosProducto.LISTAR_PRODUCTOS, null);
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
		case EventosProducto.ALTA_PRODUCTO_OK: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(91, 186, 86));
		}; break;
		case EventosProducto.ALTA_PRODUCTO_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosProducto.BAJA_PRODUCTO_OK: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(91, 186, 86));
		}; break;
		case EventosProducto.BAJA_PRODUCTO_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosProducto.EDITAR_PRODUCTO_OK: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(91, 186, 86));
		}; break;
		case EventosProducto.EDITAR_PRODUCTO_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosProducto.MOSTRAR_PRODUCTO_OK: {
			TProducto producto = (TProducto) contexto.getObjeto();
			dtm.addRow(new Object[] {producto.getId(), producto.getNombre(), producto.getPrecio()});
		}; break;
		case EventosProducto.MOSTRAR_PRODUCTO_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosProducto.LISTAR_PRODUCTOS_OK: {
			@SuppressWarnings("unchecked") 	List<TProducto> lista = (List<TProducto>) contexto.getObjeto();
			for(TProducto producto : lista) {
				dtm.addRow(new Object[] {producto.getId(), producto.getNombre(), producto.getPrecio()});
			}
		}; break;
		case EventosProducto.LISTAR_PRODUCTOS_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosProducto.BUSCAR_PRODUCTO_OK: {
			productoBuscar = (TProducto) contexto.getObjeto();
		}; break;
		case EventosProducto.BUSCAR_PRODUCTO_KO: {
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		}
	}
}