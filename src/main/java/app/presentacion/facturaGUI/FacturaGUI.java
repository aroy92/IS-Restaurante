package app.presentacion.facturaGUI;

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

import app.negocio.factura.TFactura;
import app.negocio.factura.TFacturaProducto;
import app.presentacion.contexto.Contexto;
import app.presentacion.controlador.Controlador;
import app.presentacion.eventos.EventosFactura;
import app.presentacion.eventos.EventosMenu;
import app.presentacion.factoriaGUI.GUI;

public class FacturaGUI extends JFrame implements GUI{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel mensaje;
	private DefaultTableModel dtmFactura;
	private DefaultTableModel dtmProductos;
	private JTable tablaFactura;
	private JTable tablaProductos;
	
	private TFactura facturaBuscar;

	public FacturaGUI() {
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
		JPanel panelTablaFactura = new JPanel();
		JPanel panelTablaProductos = new JPanel();
		JPanel panelMensaje = new JPanel();
		
		mensaje = new JLabel(" ");
		mensaje.setFont(new Font("Arial", Font.PLAIN, 18));
		mensaje.setForeground(new Color(255, 255, 255));
		JLabel modulo = new JLabel(" > FACTURA");
		modulo.setFont(new Font("Arial", Font.PLAIN, 18));
		modulo.setForeground(new Color(255, 255, 255));
		
		barra.setBackground(new Color(66, 86, 98));
		panelMenuBoton.setBackground(new Color(66, 86, 98));
		panelSalir.setBackground(new Color(66, 86, 98));
		fondo.setBackground(new Color(225, 225, 225));
		panelBotones.setBackground(new Color(125, 125, 125));
		
		barra.setLayout(new BorderLayout());
		fondo.setLayout(new BorderLayout());
		panelTablaFactura.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
		panelTablaProductos.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
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
		
		// Creación de la tabla factura.
		String[] nombreColummnasFactura = { "ID", "ID Empleado", "ID Cliente", "Fecha de emisión", "Total", "Cerrada" };
		dtmFactura = new DefaultTableModel(null, nombreColummnasFactura);
		tablaFactura = new JTable(dtmFactura);
		tablaFactura.setPreferredSize(new Dimension(1000, 47));
		JScrollPane scrollFactura = new JScrollPane(tablaFactura);
		scrollFactura.setPreferredSize(new Dimension(1000, 70));
		panelTablaFactura.add(scrollFactura);
		// -----------------------
		
		// Creación de la tabla producto.
		String[] nombreColummnasProductos = { "ID Producto", "Cantidad", "Precio" };
		dtmProductos = new DefaultTableModel(null, nombreColummnasProductos);
		tablaProductos = new JTable(dtmProductos);
		tablaProductos.setPreferredSize(new Dimension(1000, 277));
		JScrollPane scrollProductos = new JScrollPane(tablaProductos);
		scrollProductos.setPreferredSize(new Dimension(1000, 300));
		panelTablaProductos.add(scrollProductos);
		// -----------------------
		
		panelMensaje.add(mensaje);
		
		fondo.add(panelTablaFactura, BorderLayout.NORTH);
		fondo.add(panelTablaProductos, BorderLayout.CENTER);
		fondo.add(panelMensaje, BorderLayout.SOUTH);
		
		// Panel de botones
		JButton abrirBoton = crearBoton(getClass().getClassLoader().getResource("iconos/factura/abrir.jpg").getPath(), new Color(243, 89, 63));
		abrirBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JTextField idEmpleadoField = new JTextField();
				JTextField idClienteField = new JTextField();
				Object[] mensaje = {
						"ID Empleado:", idEmpleadoField,
						"ID Cliente:", idClienteField
				};
				int opcion = JOptionPane.showConfirmDialog(null, mensaje, "ABRIR FACTURA", JOptionPane.OK_CANCEL_OPTION);
				if(opcion == JOptionPane.OK_OPTION) {
					try {
						String idEmpleado = idEmpleadoField.getText().trim();
						String idCliente = idClienteField.getText().trim();
						boolean valid = true;
						String validationMessage = "";
						if (idEmpleado.length() == 0) {
							validationMessage += "El campo \"ID Empleado\" no puede estar vacío.";
							valid = false;
						}
						if (idCliente.length() == 0) {
							if (validationMessage.length() != 0)
								validationMessage += "\n";
							validationMessage += "El campo \"ID Cliente\" no puede estar vacío.";
							valid = false;
						} 
						if (!valid) {
							JOptionPane.showMessageDialog(null, validationMessage, "Mensaje de error", JOptionPane.WARNING_MESSAGE);
						} else {
							int idEmpleadoInt = Integer.parseInt(idEmpleado);
							int idClienteInt = Integer.parseInt(idCliente);
							TFactura factura = new TFactura();
							factura.setIdEmpleado(idEmpleadoInt);
							factura.setIdCliente(idClienteInt);
							Contexto contexto = new Contexto(EventosFactura.ABRIR_FACTURA, factura);
							Controlador.getInstance().accion(contexto);
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Ambos campos deben contener un número entero positivo.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		panelBotones.add(abrirBoton);
		
		JButton cerrarBoton = crearBoton(getClass().getClassLoader().getResource("iconos/factura/cerrar.jpg").getPath(), new Color(0, 112, 192));
		cerrarBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "CERRAR FACTURA", JOptionPane.QUESTION_MESSAGE);
					if(idString != null) {
						idString = idString.trim();
						if (idString.length() == 0) {
							JOptionPane.showMessageDialog(null, "El campo \"ID\" no debe estar vacío", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
						} else {
							long id = Long.parseLong(idString);
							Contexto contexto = new Contexto(EventosFactura.CERRAR_FACTURA, id);
							Controlador.getInstance().accion(contexto);
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "El campo \"ID\" debe contener un número entero positivo.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelBotones.add(cerrarBoton);
		
		JButton anadirProductoBoton = crearBoton(getClass().getClassLoader().getResource("iconos/factura/anadir.jpg").getPath(), new Color(91, 155, 213));
		anadirProductoBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					JTextField idFacturaField = new JTextField();
					JTextField idProductoField = new JTextField();
					JTextField cantidadField = new JTextField();
					Object[] mensaje = {
							"ID Factura:", idFacturaField,
							"ID Producto:", idProductoField,
							"Cantidad:", cantidadField
					};
					int opcion = JOptionPane.showConfirmDialog(null, mensaje, "AÑADIR PRODUCTO A FACTURA", JOptionPane.OK_CANCEL_OPTION);
					if(opcion == JOptionPane.OK_OPTION) {
						String idFacturaString = idFacturaField.getText().trim();
						String idProductoString = idProductoField.getText().trim();
						String cantidadString = cantidadField.getText().trim();
						boolean valid = true;
						String validationMessage = "";
						if (idFacturaString.length() == 0) {
							validationMessage += "El campo \"ID Factura\" no puede estar vacío.";
							valid = false;
						}
						if (idProductoString.length() == 0) {
							if (validationMessage.length() != 0)
								validationMessage += "\n";
							validationMessage += "El campo \"ID Producto\" no puede estar vacío.";
							valid = false;
						}
						if (cantidadString.length() == 0) {
							if (validationMessage.length() != 0)
								validationMessage += "\n";
							validationMessage += "El campo \"Cantidad\" no puede estar vacío.";
							valid = false;
						}
						if (!valid) {
							JOptionPane.showMessageDialog(null, validationMessage, "Mensaje de error", JOptionPane.WARNING_MESSAGE);
						} else {
							long idFactura = Long.parseLong(idFacturaString);
							long idProducto = Long.parseLong(idProductoString);
							int cantidad = Integer.parseInt(cantidadString);
							TFacturaProducto facturaProducto = new TFacturaProducto();
							facturaProducto.setIdFactura(idFactura);
							facturaProducto.setIdProducto(idProducto);
							facturaProducto.setCantidad(cantidad);
							Contexto contexto = new Contexto(EventosFactura.ANADIR_PRODUCTO_A_FACTURA, facturaProducto);
							Controlador.getInstance().accion(contexto);
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Los campos deben contener un número entero positivo", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelBotones.add(anadirProductoBoton);
		
		JButton eliminarProductoBoton = crearBoton(getClass().getClassLoader().getResource("iconos/factura/eliminar.jpg").getPath(), new Color(112, 173, 71));
		eliminarProductoBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					JTextField idFacturaField = new JTextField();
					JTextField idProductoField = new JTextField();
					Object[] mensaje = {
							"ID Factura:", idFacturaField,
							"ID Producto:", idProductoField
					};
					int opcion = JOptionPane.showConfirmDialog(null, mensaje, "ELIMINAR PRODUCTO DE FACTURA", JOptionPane.OK_CANCEL_OPTION);
					if(opcion == JOptionPane.OK_OPTION) {
						String idFacturaString = idFacturaField.getText().trim();
						String idProductoString = idProductoField.getText().trim();
						boolean valid = true;
						String validationMessage = "";
						if (idFacturaString.length() == 0) {
							validationMessage += "El campo \"ID Factura\" no puede estar vacío.";
							valid = false;
						}
						if (idProductoString.length() == 0) {
							if (validationMessage.length() != 0)
								validationMessage += "\n";
							validationMessage += "El campo \"ID Producto\" no puede estar vacío.";
							valid = false;
						} 
						if (!valid) {
							JOptionPane.showMessageDialog(null, validationMessage, "Mensaje de error", JOptionPane.WARNING_MESSAGE);
						} else {
							long idFactura = Long.parseLong(idFacturaString);
							long idProducto = Long.parseLong(idProductoString);
							TFacturaProducto facturaProducto = new TFacturaProducto();
							facturaProducto.setIdFactura(idFactura);
							facturaProducto.setIdProducto(idProducto);
							Contexto contexto = new Contexto(EventosFactura.ELIMINAR_PRODUCTO_DE_FACTURA, facturaProducto);
							Controlador.getInstance().accion(contexto);
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Los campos deben contener un número entero positivo", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelBotones.add(eliminarProductoBoton);
		
		JButton mostrarBoton = crearBoton(getClass().getClassLoader().getResource("iconos/factura/mostrar.jpg").getPath(), new Color(255, 192, 0));
		mostrarBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					String idString = JOptionPane.showInputDialog(null, "Introduce ID de factura:", "MOSTRAR FACTURA", JOptionPane.QUESTION_MESSAGE);
					if(idString != null) {
						idString = idString.trim();
						if (idString.length() == 0) {
							JOptionPane.showMessageDialog(null, "El campo \"ID\" no debe estar vacío", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
						} else {
							long id = Long.parseLong(idString);
							Contexto contexto = new Contexto(EventosFactura.MOSTRAR_FACTURA, id);
							Controlador.getInstance().accion(contexto);
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "El campo \"ID\" debe contener un número entero positivo", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				}
				if (facturaBuscar != null) {
					if (!facturaBuscar.getProductos().isEmpty()) {
						Contexto contexto = new Contexto(EventosFactura.MOSTRAR_FACTURA_PRODUCTOS, facturaBuscar.getId());
						Controlador.getInstance().accion(contexto);
					}
				}
				facturaBuscar = null;
			}
		});
		panelBotones.add(mostrarBoton);
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
	
	public void limpiarTablaFacturas() {
		for(int i = dtmFactura.getRowCount() - 1; i >= 0; i--) {
			dtmFactura.removeRow(i);
		}
	}
	
	public void limpiarTablaProductos() {
		for(int i = dtmProductos.getRowCount() - 1; i >= 0; i--) {
			dtmProductos.removeRow(i);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actualizar(Contexto contexto) {	
		limpiarJLabel();
		
		switch(contexto.getEvento()) {
		case EventosFactura.ABRIR_FACTURA_OK: {
			limpiarTablaFacturas();
			limpiarTablaProductos();
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(91, 186, 86));
		}; break;
		case EventosFactura.ABRIR_FACTURA_KO: {
			limpiarTablaFacturas();
			limpiarTablaProductos();
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosFactura.CERRAR_FACTURA_OK: {
			limpiarTablaFacturas();
			limpiarTablaProductos();
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(91, 186, 86));
		}; break;
		case EventosFactura.CERRAR_FACTURA_KO: {
			limpiarTablaFacturas();
			limpiarTablaProductos();
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosFactura.ANADIR_PRODUCTO_A_FACTURA_OK: {
			limpiarTablaFacturas();
			limpiarTablaProductos();
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(91, 186, 86));
		}; break;
		case EventosFactura.ANADIR_PRODUCTO_A_FACTURA_KO: {
			limpiarTablaFacturas();
			limpiarTablaProductos();
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosFactura.ELIMINAR_PRODUCTO_DE_FACTURA_OK: {
			limpiarTablaFacturas();
			limpiarTablaProductos();
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(91, 186, 86));
		}; break;
		case EventosFactura.ELIMINAR_PRODUCTO_DE_FACTURA_KO: {
			limpiarTablaFacturas();
			limpiarTablaProductos();
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosFactura.MOSTRAR_FACTURA_OK: {
			limpiarTablaFacturas();
			limpiarTablaProductos();
			TFactura factura = (TFactura) contexto.getObjeto();
			dtmFactura.addRow(new Object[] {factura.getId(), factura.getIdEmpleado(), factura.getIdCliente(), factura.getFechaEmision().toString().replace('T', ' '), factura.getTotal(), factura.isCerrada() ? "SI" : "NO"});
			facturaBuscar = factura;
		}; break;
		case EventosFactura.MOSTRAR_FACTURA_KO: {
			limpiarTablaFacturas();
			limpiarTablaProductos();
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		case EventosFactura.MOSTRAR_FACTURA_PRODUCTOS_OK: {
			limpiarTablaProductos();
			List<TFacturaProducto> productos = (List<TFacturaProducto>) contexto.getObjeto();
			for (TFacturaProducto producto : productos) {
				dtmProductos.addRow(new Object[] {producto.getIdProducto(), producto.getCantidad(), producto.getPrecio()});
			}
		}; break;
		case EventosFactura.MOSTRAR_FACTURA_PRODUCTOS_KO: {
			limpiarTablaFacturas();
			limpiarTablaProductos();
			String texto = (String) contexto.getObjeto();
			mensaje.setText(texto);
			mensaje.setOpaque(true);
			mensaje.setBackground(new Color(218, 63, 54));
		}; break;
		}
	}
}