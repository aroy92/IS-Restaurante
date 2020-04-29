package app.view.gui.test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import app.controller.Controller;
import app.data.test.TestTransfer;
import app.observer.Observer;
import app.sa.ApplicationServiceFactory;
import app.sa.test.TestASImpl;

public class GUITestMain extends JPanel implements Observer {

  private static final long serialVersionUID = 1L;

  private Controller controller;
  private TestASImpl testAS;

  // Center
  private JScrollPane center;
  private JTable jTable;

  // Bottom
  private JPanel bottom;
  private JButton deleteBtn;
  private JButton newBtn;

  private List<TestTransfer> testsList;
  private int selected;

  public GUITestMain(Controller c) {
    controller = c;
    testAS = ApplicationServiceFactory.getTestInstance();
    init();
    testAS.addObserver(this);
  }

  private void init() {
    setLayout(new BorderLayout());

    // Buttons
    Font font = new Font("Dialog", Font.BOLD, 20);
    newBtn = new JButton("Nuevo");
    newBtn.setFont(font);
    deleteBtn = new JButton("Eliminar");
    deleteBtn.setFont(font);
    deleteBtn.setEnabled(false);

    // Buttons panel
    bottom = new JPanel();
    bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
    bottom.add(newBtn);
    bottom.add(deleteBtn);
    add(bottom, BorderLayout.PAGE_END);

    // Table
    selected = -1;
    center = new JScrollPane();
    jTable = new JTable();
    jTable.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 17));
    jTable.setFont(new Font("Dialog", Font.PLAIN, 17));
    jTable.setRowHeight(30);
    jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    setTableModel();

    center.setViewportView(jTable);
    add(center, BorderLayout.CENTER);

    // Listeners
    setButtonsListeners();
    setTableListeners();

    update();
  }

  private void setTableModel() {
    jTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Fecha" }) {
      private static final long serialVersionUID = 1L;

      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    });
  }

  private void setTableListeners() {
    jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
          return;
        selected = jTable.getSelectedRow();
        if (selected == -1) {
          disableButtons();
        } else {
          enableButtons();
        }
      }
    });
  }

  private void setButtonsListeners() {
    newBtn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    deleteBtn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if (selected != -1) {
          controller.deleteTest(testsList.get(selected).getId());
        }
      }
    });
  }

  private void enableButtons() {
    deleteBtn.setEnabled(true);
  }

  private void disableButtons() {
    deleteBtn.setEnabled(false);
  }

  @Override
  public void update() {
    // Deseleccionamos el elemento de la tabla (dispara la acción valueChanged)
    jTable.clearSelection();
    DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
    // Al poner el contador de filas a cero se eliminan las filas de la tabla
    tableModel.setRowCount(0);
    // Si testsList está instanciado liberamos los elementos y asignamos a null para
    // asegurar que el GC lo elimina
    if (testsList != null) {
      testsList.clear();
      testsList = null;
    }
    testsList = testAS.getTests();
    if (testsList == null) {
      controller.showError("Error al recibir los tests");
    } else {
      String[] data;
      for (Iterator<TestTransfer> it = testsList.iterator(); it.hasNext();) {
        TestTransfer testTransfer = it.next();
        data = new String[2];
        data[0] = testTransfer.getName();
        data[1] = DateFormat.getInstance().format(testTransfer.getDate());
        tableModel.addRow(data);
      }
    }
    tableModel.fireTableDataChanged();
  }
}
