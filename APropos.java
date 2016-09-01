import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class APropos extends JFrame

{
	protected PanelAPropos pan = new PanelAPropos();

	public APropos()
	{
		//Parametre de la fenetre
		this.setSize(525, 322);
		this.setTitle("A Propos");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setContentPane(pan);

		pan.b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				exitp();
			}
		});

		this.setVisible(true);
	}


	public void exitp()
	{
		this.dispose();
	}
}