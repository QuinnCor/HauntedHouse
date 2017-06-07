import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.KeyStroke;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

public class RogueDriver extends JApplet implements KeyListener
{
	//IMPORTANT- Make sure Quinn uses the current Sprite class- it will be in the main branch of the Github repository
	Sprite sprite; //16 Sprites- Name them similar to the file names I made to make it easier to keep track
	BufferedImage brickImage;
	BufferedImage upAttack;//16 BufferedImages
	BufferedImage leftAttack;
	BufferedImage rightAttack;
	BufferedImage downAttack;
	//MapGenerator gen;
	ArrayList<BufferedImage> animations; //This array list will
	ArrayList<Character> sprites;
	ArrayList<Integer> keys;
	Sprite testBrick;
	RoguePickup pickup;
	//RogueEnemy enemy;
	Sprite[][] map;
	int[][] numberMap;
	int xPos, yPos;
	int characterImageDisplayed;
	int facing = 0;
	int speed = 5;
	long timeheld = 0;
	final int XSIZE = 150;
	final int YSIZE = 150;
	final int XINIT = 50;
	final int YINIT = 50;
	final int XROWS = 10;
	final int YROWS = 6;
	final int W = 87;
	final int A = 65;
	final int S = 83;
	final int D = 68;
	final int UP = 38;
	final int DOWN = 40;
	final int LEFT = 37;
	final int RIGHT = 39;
	final int SPACE = 32;
	final int FACE_RIGHT = 0;
	final int FACE_DOWN = 1;
	final int FACE_LEFT = 2;
	final int FACE_UP = 3;
	Runner thread;
	public void init()
	{
		animations  = new ArrayList<BufferedImage>();
		sprites = new ArrayList<Character>();
		keys = new ArrayList<Integer>();
		xPos = 50;
		yPos = 50;
		thread = new Runner();
		setLayout(null);
		setFocusable(true);
		setSize(2000, 1200);
		addKeyListener(this);
		setContentPane(new DrawingPanel());
		try
		{
			brickImage = ImageIO.read(new File("brick.png"));

			animations.add(ImageIO.read(new File("Player1_Down.png")));
			animations.add(ImageIO.read(new File("Player1_DownRun.png")));
			animations.add(ImageIO.read(new File("Player1_Down2.png")));
			animations.add(ImageIO.read(new File("Player1_DownRun2.png")));
			animations.add(ImageIO.read(new File("Player1_Up.png")));
			animations.add(ImageIO.read(new File("Player1_UpRun.png")));
			animations.add(ImageIO.read(new File("Player1_Up2.png")));
			animations.add(ImageIO.read(new File("Player1_UpRun2.png")));
			animations.add(ImageIO.read(new File("Player1_Left.png")));
			animations.add(ImageIO.read(new File("Player1_LeftRun.png")));
			animations.add(ImageIO.read(new File("Player1_Left2.png")));
			animations.add(ImageIO.read(new File("Player1_LeftRun2.png")));
			animations.add(ImageIO.read(new File("Player1_Right.png")));
			animations.add(ImageIO.read(new File("Player1_RightRun.png")));
			animations.add(ImageIO.read(new File("Player1_Right2.png")));
			animations.add(ImageIO.read(new File("P1right_01_new.png")));
			animations.add(ImageIO.read(new File("P1down_01_new.png")));
			animations.add(ImageIO.read(new File("P1left_01_new.png")));
			animations.add(ImageIO.read(new File("P1up_01_new.png")));
			pickup = new RoguePickup(brickImage,900,900);
			//enemy = new RogueEnemy(brickImage,500,500,500,500,5,500);
			//Do this once for each character model - There should be 16 total
		}
		catch(Exception e)
		{
			System.out.println("File Not Found");
		}
		for(int index = 0; index < animations.size(); index++)
		{
			sprites.add(new Character(animations.get(index), index, index, 50, 50, 50, 50));
		}
		testBrick = new Sprite(brickImage, 300, 300);
		thread.start();
	}
	public void keyPressed(KeyEvent e) //Multi-Key Listener
	{
		boolean isRegistered = false;
		int keyPressedValue = e.getKeyCode();
		timeheld++;
		for(int keyIndex = 0; keyIndex < keys.size(); keyIndex++)
		{
			if(keys.get(keyIndex).intValue() == keyPressedValue)
			{
				isRegistered = true;
			}
		}
		if(!isRegistered)
		{
			keys.add((Integer)e.getKeyCode());
		}
	}
	public void keyReleased(KeyEvent e)
	{
		int keyReleasedValue = e.getKeyCode();
		for(int keyIndex = 0; keyIndex < keys.size(); keyIndex++)
		{
			if(keys.get(keyIndex).intValue() == keyReleasedValue)
			{
				keys.remove(keyIndex);
				if(keyReleasedValue == KeyEvent.VK_SPACE)
				{
					if(facing == 0)
					{
						characterImageDisplayed = 12;
					}
					if(facing == 1)
					{
						characterImageDisplayed = 0;
					}
					if(facing == 2)
					{
						characterImageDisplayed = 8;
					}
					if(facing == 3)
					{
						characterImageDisplayed = 4;
					}
				}
			}

		}
		timeheld = 0;
	}
	public void keyClicked(KeyEvent e)
	{

	}
	public void keyTyped(KeyEvent e)
	{

	}
	public class DrawingPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			sprites.get(characterImageDisplayed).draw(g);
			testBrick.draw(g);
			//enemy.draw(g);
				if(pickup.collidesGeneral(sprites.get(1)) == false)
				{
					pickup.draw(g);
				}
			//sprites.get(5).draw(g); //Use a timer to change the image ever few milliseconds for now. Once the image index reaches 16, it should go back to index = 0
		}
	}
	public class Runner implements Runnable
	{
		Thread t;
		public Runner()
		{
			characterImageDisplayed = 0;
		}
		public void run()
		{
			try
			{
				while(true)
				{
					if(keys.contains(RIGHT) && !sprites.get(1).collidesRight(testBrick))
					{
						xPos += speed;
						facing = FACE_RIGHT;
						if(timeheld < 1)
						{
							characterImageDisplayed = 12;
						}
						else
						{
							if(characterImageDisplayed < 12 || characterImageDisplayed > 14)
							{
								characterImageDisplayed = 12;
							}
							characterImageDisplayed++;
							if(characterImageDisplayed == 14)
							{
								characterImageDisplayed = 12;
							}
						}
					}
					if(keys.contains(LEFT) && !sprites.get(1).collidesLeft(testBrick))
					{
						xPos -= speed;
						facing = FACE_LEFT;
						if(timeheld < 1)
						{
							characterImageDisplayed = 8;
						}
						else
						{
							if(characterImageDisplayed < 8 || characterImageDisplayed > 12)
							{
								characterImageDisplayed = 8;
							}
							characterImageDisplayed++;
							if(characterImageDisplayed >= 12)
							{
								characterImageDisplayed = 8;
							}
						}
					}
					if(keys.contains(UP) && !sprites.get(1).collidesUp(testBrick))
					{
						yPos -= speed;
						facing = FACE_UP;
						if(timeheld < 1)
						{
							characterImageDisplayed = 4;
						}
						else
						{
							if(characterImageDisplayed < 4 || characterImageDisplayed > 8)
							{
								characterImageDisplayed = 4;
							}
							characterImageDisplayed++;
							if(characterImageDisplayed >= 8)
							{
								characterImageDisplayed = 4;
							}
						}
					}
					if(keys.contains(DOWN) && !sprites.get(1).collidesDown(testBrick))
					{
						yPos += speed;
						facing = FACE_DOWN;
						if(timeheld < 1)
						{
							characterImageDisplayed = 0;
						}
						else
						{
							if(characterImageDisplayed < 0 || characterImageDisplayed > 4)
							{
								characterImageDisplayed = 0;
							}
							characterImageDisplayed++;
							if(characterImageDisplayed >= 4)
							{
								characterImageDisplayed = 0;
							}
						}
					}
					if(keys.contains(SPACE))
					{
								if(facing == 0)
								{
									characterImageDisplayed = 15;
								}
								if(facing == 1)
								{
									characterImageDisplayed = 16;
								}
								if(facing == 2)
								{
									characterImageDisplayed = 17;
								}
								if(facing == 3)
								{
									characterImageDisplayed = 18;
								}
					}
					for(int spriteIndex = 0; spriteIndex < sprites.size(); spriteIndex++)
					{
						sprites.get(spriteIndex).setPosition(xPos, yPos);
					}
					//if(enemy.attackCollision(sprites.get(1), facing) == true)
					//{
						//enemy.getHit();
						//System.out.println("hit");
					//}
					if(sprites.get(1).collidesDown(testBrick))
					{
						System.out.println("Collision");
					}
					//enemy.randomMove();
					repaint();
					t.sleep(40);
				}
			}
			catch(Exception e)
			{
				System.out.println("Game paused");
			}
		}
		public void start()
		{
			if(t == null)
			{
				t = new Thread(this, "GameLoop");
				t.start();
			}
		}
	}
}