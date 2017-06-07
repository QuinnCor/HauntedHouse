import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import javax.imageio.*;
import java.util.Random;

public class RogueEnemy extends RogueCharacter
{
	int Health;
	Random r;
	public RogueEnemy(BufferedImage image, int x, int y, int speed, int attackSpeed, int maxHealth, int range)
	{
		super(image, x, y, speed, attackSpeed, maxHealth, range);
		this.Health = Health;
		r = new Random();
	}
	public void setHealth(int newHealth)
	{
		Health = newHealth;
	}
	public int getHealth()
	{
		return Health;
	}
	public void randomMove()
	{
		int facing = r.nextInt(5);
		if(facing == 0)
		{
			setPosition(x + 20, y);
		}
		if(facing == 1)
		{
			setPosition(x + 20, y);
		}
		if(facing == 2)
		{
			setPosition(x + 20, y);
		}
		if(facing == 3)
		{
			setPosition(x + 20, y);
		}
	}
	public void getHit()
	{
		setHealth(Health-1);
	}
	private void isDead()
	{
		if(Health <= 0)
		{
			draw = false;
		}
	}
}