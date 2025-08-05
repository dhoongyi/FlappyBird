package FlappyBird;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class GameControl extends JPanel implements ActionListener,KeyListener  {

	private static final long serialVersionUID = 1L;
	
	JFrame parentJFrame;

	int boardWidth = 350;
	int boardHeight = 600;

	// load Image
	Image backgroundImg, birdImg, topPipeImg, bottomPipeImg;

//    Bird
	int birdX = boardWidth / 7;
	int birdY = boardHeight / 2;
	int birdWidth = 34;
	int birdHeight = 24;

	// pipe
	int pipeX = boardWidth;
	int pipeY = 0;
	int pipeWidth = 62;
	int pipeHeight = 512;

	Bird bird;
	int velocityY = 0;
	int gravity = 1;
	int pipeSpeed;
	int pipeSpacing;
	int pipeInterval;

	ArrayList<Pipe> pipes;
	Random random = new Random();

	Timer gameLoop;
	Timer placePipeTimer;
	boolean gameOver = false;
	double score = 0;
	String mode;

	/**
	 * Create the panel.
	 */
	public GameControl(String mode,JFrame parentJFrame) {

		this.mode = mode;
		this.parentJFrame = parentJFrame;
		setPreferredSize(new Dimension(boardWidth, boardHeight));
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(this);

		backgroundImg = new ImageIcon(getClass().getResource("/assets/background-menu.png")).getImage();
		birdImg = new ImageIcon(getClass().getResource("/assets/flappybird.png")).getImage();
		topPipeImg = new ImageIcon(getClass().getResource("/assets/toppipe.png")).getImage();
		bottomPipeImg = new ImageIcon(getClass().getResource("/assets/bottompipe.png")).getImage();

		switch (mode.toLowerCase()) {
			case "easy":
				pipeSpacing = 200;
				pipeSpeed = 4;
				 pipeInterval = 3000;
				break;
			case "normal":
				pipeSpacing = 150;
				pipeSpeed = 6;
				 pipeInterval = 2000;
				break;
			case "hard":
				pipeSpacing = 100;
				pipeSpeed = 8;
				 pipeInterval = 1300;
				break;
			default:
				pipeSpacing = 150;
				pipeSpeed = 6;
				pipeInterval = 2000;
		}
		
		bird = new Bird(birdImg);
		pipes = new ArrayList<>();

		placePipeTimer = new Timer(pipeInterval,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				placePipes();
			}
		});		
		placePipeTimer.start();

		gameLoop = new Timer(40,this);
		gameLoop.start();

	}
	
	void placePipes() {
        int pipeY = 0;
        int randomY = pipeY - 128 - random.nextInt(256);
        int openingSpace = pipeSpacing;

        Pipe topPipe = new Pipe(topPipeImg, boardWidth, randomY);
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImg, boardWidth, randomY + pipeHeight + openingSpace);
        pipes.add(bottomPipe);
		
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        for (Pipe pipe : pipes) {
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }

        // normal font
//        g.setColor(Color.white);
//        g.setFont(new Font("Micro5", Font.BOLD, 50));
//        if (!gameOver) {
//            g.drawString(String.valueOf((int) score), 170, 70);
//        }
        
        // This is score styling font with stroke
        Graphics2D g2 = (Graphics2D) g;

        Font font = new Font("Tiny5", Font.BOLD, 50);
        g2.setFont(font);

        // for smoother text
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        String text = String.valueOf((int) score);
        FontRenderContext frc = g2.getFontRenderContext();
        TextLayout textLayout = new TextLayout(text, font, frc);

        Shape shape = textLayout.getOutline(AffineTransform.getTranslateInstance(155, 70));

        g2.translate(0, 0);

        if (!gameOver) {
            // Draw outline
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(8));
            g2.draw(shape);

            // Fill text
            g2.setColor(Color.WHITE);
            g2.fill(shape);
        }
    }

    public void move() {
        velocityY += gravity;
        bird.y += velocityY;

        for (Pipe pipe : pipes) {
            pipe.x -= pipeSpeed;

            if (!pipe.passed && bird.x > pipe.x + pipe.width) {
                score += 0.5;
                pipe.passed = true;
            }

            if (collision(bird, pipe)) {
                gameOver = true;
                GameOver gameover = new GameOver((int)score);
                gameover.setVisible(true);
				setVisible(false);
				parentJFrame.dispose();
            }
        }

        if (bird.y > boardHeight - bird.height || bird.y < 0) {
            gameOver = true;
            GameOver gameover = new GameOver((int)score);
            gameover.setVisible(true);
			setVisible(false);
			parentJFrame.dispose();
        }
    }

    boolean collision(Bird b, Pipe p) {
        return b.x < p.x + p.width && b.x + b.width > p.x &&
               b.y < p.y + p.height && b.y + b.height > p.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
            repaint();
        } else {
            placePipeTimer.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // 
    }
    
    
    
    
    
    
    
    
  // bird class
    class Bird{
    	int x = birdX;
    	int y = birdY;
    	int width = birdWidth;
    	int height = birdHeight;
    	Image img;

    	Bird(Image img){
    	this.img = img;
    	}
    }
    
    
    
 // pipe class
    class Pipe {
    	int x;
    	int y;
    	int width = 64;
    	int height = 512;
    	Image img;
    	boolean passed = false;

    	Pipe(Image img, int x, int y) {
    	this.img = img;
    	this.x = x;
    	this.y = y;
    	}
    }
    
}



