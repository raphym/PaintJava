import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

public class PanelAPropos extends JPanel 
{
	Image image;

	JButton b = new JButton("close the window");

	public PanelAPropos()
	{
		image=(new javax.swing.ImageIcon(getClass().getResource("fond.jpg"))).getImage();
		this.setLayout(new BorderLayout());
		add(b, BorderLayout.SOUTH);

	}
	public void paintComponent(Graphics g)
	{
		this.setOpaque(false);
		g.drawImage (image, 0, 0, null);
		super.paintComponent(g);
	}

}