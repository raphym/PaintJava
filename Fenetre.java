import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Fenetre extends JFrame implements MouseListener,MouseMotionListener
{
	protected JFileChooser jF = new JFileChooser();

	protected int size;
	protected Color coloriageCurseur;
	protected Color color2;
	protected String forme;
	protected boolean utiliseGomme;




	//Objet
	private Points point;

	//Les Panels
	private Panel container = new Panel();
	private Cadre c=new Cadre();
	private Panel bas = new Panel();

	//Le menu bar
	private JMenuBar menuBar = new JMenuBar();

	//Les differents onglets
	private JMenu ongletFichier = new JMenu("File"); //Fichier

	private JMenu ongletEdition = new JMenu("Edit"); //Edition

	//private JMenu ongletAide = new JMenu("Help"); //Edition

	//Les differents sous-onglets de edition
	private JMenu ongletFormePointer = new JMenu("Shape of the pen"); //Forme du pointer
	private JMenu ongletCouleurPointer = new JMenu("Color of the pen"); //Couleur du pointer
	private JMenu ongletCouleurDeFond = new JMenu("Backgroud Color"); //Couleur de fond

	//Les differents items
	private JMenuItem itemSauvegarder = new JMenuItem("Save");
	private JMenuItem itemEffacer = new JMenuItem("Erase all");
	private JMenuItem itemQuitter = new JMenuItem("Exit");
	private JMenuItem itemRond = new JMenuItem("Circle");
	private JMenuItem itemCarre = new JMenuItem("Square");
	private JMenuItem itemRouge = new JMenuItem("Red");
	private JMenuItem itemVert = new JMenuItem("Green");
	private JMenuItem itemBleu = new JMenuItem("Blue");
	private JMenuItem itemFondBlanc = new JMenuItem("White");
	private JMenuItem itemFondNoir = new JMenuItem("Black");
	private JMenuItem itemAPropos = new JMenuItem("About");
	private JMenuItem itemGomme = new JMenuItem("Erase");
	private JMenuItem itemDeGomme = new JMenuItem("Remove the eraser");



	//Les boutons du bas de la frame
	private JButton plus = new JButton("Bigger pen  : " + size);
	private JButton moins = new JButton("Smaller pen : " + size);


	public Fenetre()
	{

		//Variables
		coloriageCurseur=Color.red;
		size =6;
		forme="Rond";
		utiliseGomme=false;

		plus.setText("Bigger pen : " + size);
		moins.setText("Smaller pen : " + size);

		//Parametre de la fenetre
		this.setSize(600,660);
		this.setTitle("Paint");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		//Boutons du bas de la frame
		bas.setLayout(new FlowLayout());
		bas.add(plus);
		bas.add(moins);


		//Agencement du container
		container.setLayout(new BorderLayout());
		container.add(c, BorderLayout.CENTER);
		container.add(bas, BorderLayout.SOUTH);


		//Ajout des onglets au menu edition

		itemDeGomme.setEnabled(false);
		this.ongletEdition.add(itemGomme);
		this.ongletEdition.add(itemDeGomme);
		this.ongletEdition.addSeparator();
		this.ongletEdition.add(ongletFormePointer);
		this.ongletEdition.addSeparator();
		this.ongletEdition.add(ongletCouleurPointer);
		this.ongletEdition.addSeparator();
		this.ongletEdition.add(ongletCouleurDeFond);


		//Ajout des onglets principales au menuBar
		this.menuBar.add(ongletFichier);
		this.menuBar.add(ongletEdition);
		//this.menuBar.add(ongletAide);

		//Ajout des differents items
		this.ongletFichier.add(itemSauvegarder);
		this.ongletFichier.add(itemEffacer);
		this.ongletFichier.addSeparator();
		this.ongletFichier.add(itemQuitter);

		this.ongletFormePointer.add(itemRond);
		this.ongletFormePointer.add(itemCarre);

		this.ongletCouleurPointer.add(itemRouge);
		this.ongletCouleurPointer.add(itemVert);
		this.ongletCouleurPointer.add(itemBleu);

		this.ongletCouleurDeFond.add(itemFondBlanc);
		this.ongletCouleurDeFond.add(itemFondNoir);

		//this.ongletAide.add(itemAPropos);
		//Activer le menu bar
		this.setJMenuBar(menuBar);

		//Activation des boutons items
		
		//Sauvegarder
		this.itemSauvegarder.addActionListener(new myActionListener());

		//QUITTER
		this.itemQuitter.addActionListener(new myActionListener());

		//Effacer
		this.itemEffacer.addActionListener(new myActionListener());

		//Rond
		this.itemRond.addActionListener(new myActionListener());

		//carre
		this.itemCarre.addActionListener(new myActionListener());

		//Rouge
		this.itemRouge.addActionListener(new myActionListener());

		//Vert
		this.itemVert.addActionListener(new myActionListener());

		//Bleu
		this.itemBleu.addActionListener(new myActionListener());

		//Fond blanc
		this.itemFondBlanc.addActionListener(new myActionListener());

		//Fond Noir
		this.itemFondNoir.addActionListener(new myActionListener());

		//APropos
		this.itemAPropos.addActionListener(new myActionListener());

		//Gomme
		this.itemGomme.addActionListener(new myActionListener());

		//Gomme
		this.itemDeGomme.addActionListener(new myActionListener());

		//Activer la souris
		c.addMouseListener(this);
		c.addMouseMotionListener(this);

		//Activer les boutons d'Agrandissements
		plus.addActionListener(new myActionListener());
		moins.addActionListener(new myActionListener());

		//container sera le Content Pane
		this.setContentPane(container);
		this.setVisible(true);
	}

	//Les evenements de la souris
	public void mouseClicked(MouseEvent e) 
	{

		point = new Points(e.getX(),e.getY(),coloriageCurseur,forme,size);
		c.AddToVect(point);
	}
	public void mousePressed(MouseEvent e) 
	{
		point = new Points(e.getX(),e.getY(),coloriageCurseur,forme,size);
		c.AddToVect(point);
	}
	public void mouseDragged(MouseEvent e)
	{
		if(e.getX()>0 && e.getY()>0)
		{
			point = new Points(e.getX(),e.getY(),coloriageCurseur,forme,size);
			c.AddToVect(point);
		}
	}
	public void mouseEntered(MouseEvent e) 
	{		
	}
	public void mouseExited(MouseEvent e)
	{
	}
	public void mouseReleased(MouseEvent e) 
	{
	}
	public void mouseMoved(MouseEvent e) 
	{		
	}	


	//Class interne permettant de gerer les boutons
	class myActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource().equals(itemSauvegarder))//button agrandir
			{
				save();
			}
			
			if(e.getSource().equals(plus))//button agrandir
			{
				size++;
				moins.setEnabled(true);
				plus.setText("Bigger pen : " + size);
				moins.setText("Smaller pen : " + size);	
				bas.setVisible(false);
				bas.setVisible(true);
			}
			if(e.getSource().equals(moins))//button diminuer
			{

				if(size>2)
				{
					size--;
					plus.setText("Bigger pen : " + size);
					moins.setText("Smaller pen : " + size);
					bas.setVisible(false);
					bas.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "minimum size", "Taille", size);
					moins.setEnabled(false);
				}
			}

			if(e.getSource().equals(itemQuitter))//button Quitter
			{
				System.exit(0);
			}

			if(e.getSource().equals(itemEffacer))//button Effacer
			{
				c.removeAll();
				if(coloriageCurseur==c.getBackground())
				{
					coloriageCurseur=color2;
					gommage();
				}		
			}

			if(e.getSource().equals(itemRond))//button Rond
			{
				forme="Rond";
			}

			if(e.getSource().equals(itemCarre))//button Carre
			{
				forme="Carre";
			}

			if(e.getSource().equals(itemRouge))//button Rouge
			{
				coloriageCurseur=Color.red;
				gommage();
			}

			if(e.getSource().equals(itemVert))//button Vert
			{
				coloriageCurseur=Color.green;
				gommage();
			}

			if(e.getSource().equals(itemBleu))//button Bleu
			{
				coloriageCurseur=Color.blue;
				gommage();
			}

			if(e.getSource().equals(itemGomme))// Gomme
			{
				utiliseGomme=true;
				color2=coloriageCurseur;
				coloriageCurseur=c.getBackground();
				gommage();
			}

			if(e.getSource().equals(itemDeGomme))// Degomme
			{
				coloriageCurseur=color2;
				gommage();
			}

			if(e.getSource().equals(itemFondBlanc))//button fond blanc
			{
				if(utiliseGomme==true)
					c.removeAll();
				c.setBackground(Color.white);
			}
			if(e.getSource().equals(itemFondNoir))//button fond noir
			{
				if(utiliseGomme==true)
					c.removeAll();
				c.setBackground(Color.black);
			}
			if(e.getSource().equals(itemAPropos))//button A Propos
			{
				APropos a = new APropos();	 
			}
		}

		private void save() 
		{	
			String nom ="";//nom de l'image
			String path="";//address de l'enrgistrement
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showSaveDialog(new JPanel());
			File file = fileChooser.getSelectedFile();

			path=file.getAbsolutePath();//obtenir l'adress
			path=path+".jpeg";

			// Create a new image with the JComponent size
			BufferedImage bufferImage = new BufferedImage(c.getSize().width, c.getSize().height,BufferedImage.TYPE_INT_ARGB);
			// The panel draw itself into the graphics of the image
			c.paint(bufferImage.createGraphics());
			// Instantiate a file for saving the screenshot
			File imageFile = new File(path);
			try
			{
				imageFile.createNewFile();
				ImageIO.write(bufferImage, "png", imageFile);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	public void gommage()
	{
		if(c.getBackground()==coloriageCurseur)
		{
			itemGomme.setText("erasing");
			itemGomme.setEnabled(false);
			itemFondBlanc.setEnabled(false);
			itemFondNoir.setEnabled(false);
			itemRouge.setEnabled(false);
			itemVert.setEnabled(false);
			itemBleu.setEnabled(false);
			itemDeGomme.setEnabled(true);
		}
		else
		{
			itemGomme.setText("Gomme");
			itemGomme.setEnabled(true);
			itemFondBlanc.setEnabled(true);
			itemFondNoir.setEnabled(true);
			itemRouge.setEnabled(true);
			itemVert.setEnabled(true);
			itemBleu.setEnabled(true);			
			itemDeGomme.setEnabled(false);
		}
	}
}
