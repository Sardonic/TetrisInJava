package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;

import javax.swing.*;


@SuppressWarnings("serial")
public class GameWindow extends JFrame
{	
	public static final int MAX_WIDTH = 640;
	public static final int MAX_HEIGHT = 480;
	
	private final int width;
	private final int height;
	
	private double lastUpdateTime = System.currentTimeMillis();
	private boolean shouldRun = true;
	
	private GameManager manager;
	
	private GameWindow(int width, int height)
	{
		this.width = width; 
		this.height = height;	
	}
	
	private void init()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("TETRIS!!!!");
		manager = new GameManager(width, height);
		add(manager, BorderLayout.CENTER);

		setResizable(false);
		setVisible(true);
		this.pack();
		
		addKeyListener(makeKeyListener());
		addWindowListener(makeWindowListener());
		startGameLoop();
	}

	private KeyListener makeKeyListener()
	{
		KeyListener kbListener = new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					shouldRun = false;
				}
			}

			public void keyReleased(KeyEvent e) {}

			public void keyTyped(KeyEvent e) {}
		};
		
		return kbListener;
	}
	
	private WindowListener makeWindowListener()
	{
		WindowListener winListener = new WindowListener()
		{
			public void windowClosing(WindowEvent arg0)
			{
				shouldRun = false;
			}
			
			public void windowActivated(WindowEvent arg0) {}

			public void windowClosed(WindowEvent arg0) {}

			public void windowDeactivated(WindowEvent arg0) {}

			public void windowDeiconified(WindowEvent arg0) {}

			public void windowIconified(WindowEvent arg0) {}

			public void windowOpened(WindowEvent arg0) {}
		};
		
		return winListener;
	}
	
	private void startGameLoop()
	{
		System.out.println(this.getWidth() + ":" + this.getHeight());
		Thread gameLoop = new Thread()
		{
			public void run()
			{
				mainLoop();
			}
		};
		
		gameLoop.start();
	}

	//run in different thread
	private void mainLoop()
	{		 		
		while(shouldRun)
		{
			double currentTime = System.currentTimeMillis();
			double elapsedTime = currentTime - lastUpdateTime;
			manager.update(elapsedTime);
			lastUpdateTime = currentTime;
		}
		
		exit();
	}

	private void exit()
	{
		System.out.println("We're done here!");
		dispose();
		System.exit(0);
	}
	
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GameWindow(MAX_WIDTH, MAX_HEIGHT).init();
			}
		});
	}
}
