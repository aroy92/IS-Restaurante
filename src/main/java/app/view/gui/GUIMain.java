package app.view.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import app.controller.Controller;
import app.view.gui.test.GUITestMain;

public class GUIMain extends JPanel {

  private static final long serialVersionUID = 1L;

  private JPanel topPanel;
  private JTabbedPane tabbedPanel;

  public GUIMain(Controller c) {
    init(c);
  }

  private void init(Controller c) {
    setLayout(new BorderLayout(0, 5));

    topPanel = new GUIMainTop();
    add(topPanel, BorderLayout.PAGE_START);

    tabbedPanel = new javax.swing.JTabbedPane();
    tabbedPanel.setFont(new Font("Dialog", Font.BOLD, 24));

    // Tabs
    tabbedPanel.add("Test", new GUITestMain(c));

    add(tabbedPanel, BorderLayout.CENTER);
  }
}
