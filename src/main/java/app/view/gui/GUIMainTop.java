package app.view.gui;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIMainTop extends JPanel {

  private static final long serialVersionUID = 1L;

  private JPanel logoPanel;

  public GUIMainTop() {
    init();
  }

  private void init() {
    setLayout(new BorderLayout());

    logoPanel = new JPanel();

    try {
      BufferedImage logo = ImageIO.read(new File("images/logo.jpg"));
      JLabel label = new JLabel(new ImageIcon(logo));
      logoPanel.add(label);
    } catch (IOException ex) {
    }

    add(logoPanel, BorderLayout.LINE_START);
  }
}
