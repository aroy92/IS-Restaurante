package app.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import app.controller.Controller;
import app.view.gui.GUIMain;

public class View extends JFrame {

  private static final long serialVersionUID = 1L;

  private JPanel panel;

  public View(String title, Controller c) {
    super(title);
    init(c);
  }

  private void init(Controller c) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(500, 400));
    setSize(new Dimension(500, 400));

    panel = new GUIMain(c);
    getContentPane().add(new JPanel(), BorderLayout.PAGE_START);
    getContentPane().add(new JPanel(), BorderLayout.LINE_START);
    getContentPane().add(panel, BorderLayout.CENTER);
    getContentPane().add(new JPanel(), BorderLayout.LINE_END);
    getContentPane().add(new JPanel(), BorderLayout.PAGE_END);

    pack();

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        c.closeApp();
        super.windowClosing(e);
      }
    });
  }
}
