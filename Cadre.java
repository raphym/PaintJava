import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;


public class Cadre extends JPanel
{
	
	//Couleur Fond
	private Color couleur=Color.WHITE ;

	//Objets
	private Vector<Points> vect = new Vector<Points>();


	public Cadre()
	{
		this.setBackground(couleur);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		draw(g);
		repaint();

	}

	public void draw(Graphics g)
	{

		for(int i=0; i<vect.size(); i++)
		{
			if(vect.get(i).getForme().equals("Rond"))
			{
				g.setColor(vect.get(i).getColor());
				g.fillOval(vect.get(i).getPosX(), vect.get(i).getPosY(), vect.get(i).getSize(), vect.get(i).getSize());
			}
			if(vect.get(i).getForme().equals("Carre"))
			{
				g.setColor(vect.get(i).getColor());
				g.fillRect(vect.get(i).getPosX(), vect.get(i).getPosY(), vect.get(i).getSize(), vect.get(i).getSize());
			}
		}

	}

	public void setColorFond(Color coli)
	{
		this.couleur=coli;
		setVisible(false);
		setVisible(true);
	}

	public void AddToVect(Points point)
	{
		vect.addElement(point);
	}
	public void removeAll()
	{
		vect.removeAllElements();
	}
}
