import java.awt.*;
import java.awt.image.BufferedImage;
public class RogueCharacter extends RogueSprite
{
	int health;
	int attackSpeed;
	int range;
	public RogueCharacter(BufferedImage image, int x, int y, int attackSpeed, int maxHealth, int range)
	{
		super(image, x, y);
		this.attackSpeed = attackSpeed;
		this.range = range;
		health = maxHealth;
	}
	public boolean attackCollision(RogueSprite sprite, int direction)
	{
		//0 = Right, 1 = Left, 2 = Up, 3 = Down
		boolean collides = false;
		int x2 = (int)sprite.getCollider().getX();
		int y2 = (int)sprite.getCollider().getY();
		int width2 = (int)sprite.getCollider().getWidth();
		int height2 = (int)sprite.getCollider().getHeight();
		if(direction == 0)
		{
			if(y + (height / 2) > y2 && y + (height / 2) < (y2 + height2))
			{
				if(x2 - (x + width) < range)
				{
					collides = true;
				}
			}
		}
		if(direction == 1)
		{
			if(y + (height / 2) > y2 && y + (height / 2) < (y2 + height2))
			{
				if(x - (x2 + width2) < range)
				{
					collides = true;
				}
			}
		}
		if(direction == 2)
		{
			if(x + (width / 2) > x2 && x + (width / 2) < (x2 + width2))
			{
				if(y - (y2 + height2) < range)
				{
					collides = true;
				}
			}
		}
		if(direction == 3)
		{
			if(x + (width / 2) > x2 && x + (width / 2) < (x2 + width2))
			{
				if(y2 - (y + height) < range)
				{
					collides = true;
				}
			}
		}
		return collides;
	}
	public void setHealth(int health)
	{
		this.health = health;
	}
	public void setAttackSpeed(int attackSpeed)
	{
		this.attackSpeed = attackSpeed;
	}
	public int getHealth()
	{
		return health;
	}
	public int getASpeed()
	{
		return attackSpeed;
	}
	public boolean isAlive()
	{
		boolean isAlive = true;
		if(health <= 0)
		{
			isAlive = false;
		}
		return isAlive;
	}
}