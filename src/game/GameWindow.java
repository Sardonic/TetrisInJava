package game;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;


@SuppressWarnings("serial")
public class GameWindow extends JFrame
{	
	public static final int MAX_WIDTH = 640;
	public static final int MAX_HEIGHT = 480;
	public static final double FPS = 30;
	public static final long MS_FRAME_DELAY = (long)(1000 / FPS);
	
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
		addKeyListener(manager);
		startGameLoop();
	}

	private KeyListener makeKeyListener()
	{
		KeyListener kbListener = new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					shouldRun = false;
				}
			}
		};
		
		return kbListener;
	}
	
	private WindowListener makeWindowListener()
	{
		WindowListener winListener = new WindowAdapter()
		{
			public void windowClosing(WindowEvent arg0)
			{
				shouldRun = false;
			}
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
			long currentTime = System.currentTimeMillis();
			long elapsedTime = (long) (currentTime - lastUpdateTime);
			long endTime;
			manager.update(elapsedTime);
			
			repaint();
			
			endTime = System.currentTimeMillis();
			long timeDiff = endTime - currentTime;
			
			if (timeDiff < MS_FRAME_DELAY) {
                try {
                    Thread.sleep((long)MS_FRAME_DELAY - timeDiff);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
			}
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
