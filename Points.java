import java.awt.Color;


public class Points
{

	private int posX;
	private int posY;
	private int theSize;
	private Color pColor;
	private String forme;


	public Points (int posX,int posY, Color col,String forme,int size)
	{
		this.posX=posX;
		this.posY=posY;
		this.pColor=col;
		this.forme=forme;
		this.theSize=size;

	}

	public void setForme(String forme)
	{
		this.forme=forme;
	}

	public String getForme()
	{
		return forme;
	}

	public void setPosXY(int posX,int posY)
	{
		this.posX=posX;
		this.posY=posY;
	}
	public int getPosX()
	{
		return posX;
	}
	public int getPosY()
	{
		return posY;
	}
	public void setSize(int size)
	{
		this.theSize=size;
	}
	public int getSize()
	{
		return theSize;
	}
	public void setColor(Color col)
	{
		this.pColor=col;
	}
	public Color getColor()
	{
		return this.pColor;
	}

}
