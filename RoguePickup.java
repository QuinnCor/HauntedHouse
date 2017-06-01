import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.util.Random;

public class RoguePickup extends RogueSprite
{
	Random r = new Random();
	int posX;
	int	posY;
	BufferedImage image;
	public RoguePickup(BufferedImage image, int posX, int posY)
	{
		super(image, posX, posY);
		this.posX = posX;
		this.posY = posY;
	}
	public void setRandomPosition()
	{
		posX = r.nextInt(1000);
		posY = r.nextInt(1000);
		setPosition(posX,posY);
	}

	public int getPosX()
	{
		return posX;
	}
	public int getPosY()
	{
		return posY;
	}


}