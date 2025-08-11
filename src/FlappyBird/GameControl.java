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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameControl extends JPanel implements ActionListener,KeyListener  {

	private static final long serialVersionUID = 1L;
	
	JFrame parentJFrame;

	int boardWidth = 350;
	int boardHeight = 600;

	// load Image
	Image backgroundImg, birdImg, topPipeImg, bottomPipeImg, readyImg;

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
	int pipecount = 0;

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
	String startMode;
	int currentPlayerId = LoggedUserInfo.getInstance().getUserId();
	
	String modeChangeMessage = "";
	long modeChangeMessageTime;
	
	boolean started = false;

	/**
	 * Create the panel.
	 */
	public GameControl(String mode, String selectedBird, String selectedTheme,JFrame parentJFrame) {

		startMode = mode;
		this.mode = mode;
		this.parentJFrame = parentJFrame;
		setPreferredSize(new Dimension(boardWidth, boardHeight));
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(this);

		backgroundImg = new ImageIcon(getClass().getResource("/assets/"+selectedTheme+".png")).getImage();
		birdImg = new ImageIcon(getClass().getResource("/assets/"+selectedBird+".png")).getImage();
		topPipeImg = new ImageIcon(getClass().getResource("/assets/toppipe.png")).getImage();
		bottomPipeImg = new ImageIcon(getClass().getResource("/assets/bottompipe.png")).getImage();
		readyImg = new ImageIcon(getClass().getResource("/assets/getready.png")).getImage();

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
//		placePipeTimer.start();

		gameLoop = new Timer(40,this);
//		gameLoop.start();

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
            
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(8));
            g2.draw(shape);

            // Fill text
            g2.setColor(Color.WHITE);
            g2.fill(shape);
        }
        
        if(!started && !gameOver) {
        	g.drawImage(readyImg, 80, 110, 200, 300, null);
        	
        	g2.setFont(new Font("Tiny5", Font.BOLD, 18));
            g2.setColor(Color.WHITE);
            g2.drawString("Press Spacebar to Start", 80, 500);
		}
        
        // Mode Change Message
        if(!modeChangeMessage.isEmpty()) {
        	long currentTime = System.currentTimeMillis();
        	if(currentTime - modeChangeMessageTime < 2000) {
                g2.setFont(new Font("Tiny5", Font.BOLD, 20));
                g2.setColor(Color.YELLOW);
                g2.drawString(modeChangeMessage, 60, 100);
        	}
        }else {
        	modeChangeMessage = "";
        }
        
    }

    public void move() {
        velocityY += gravity;
        bird.y += velocityY;

        for (Pipe pipe : pipes) {
            pipe.x -= pipeSpeed;

            if (!pipe.passed && bird.x > pipe.x + pipe.width) {
            	
                pipe.passed = true;
                playSound("point.wav");
                
                if(pipes.indexOf(pipe) % 2 == 0) {
                	
                	switch(mode.toLowerCase()) {
	                	case "easy":
	                		pipecount+=1;
	                		score+=1;
	                		break;
	                	case "normal":
	                		pipecount+=2;
	                		score+=2;
	                		break;
	                	case "hard":
	                		pipecount+=3;
	                		score+=3;
	                		break;
                	}
                	
                	if(pipecount == 10 && startMode.equalsIgnoreCase("easy") && mode.equalsIgnoreCase("easy")) {
                		changeMode("normal");
                	}else if(pipecount == 20 && startMode.equalsIgnoreCase("easy") && mode.equalsIgnoreCase("normal")) {
                		changeMode("hard");
                	}else if(pipecount == 10 && startMode.equalsIgnoreCase("normal") && mode.equalsIgnoreCase("normal")) {
                		changeMode("hard");
                	}
                	
                }
                
                
            }
            
            

            if (collision(bird, pipe)) {
                gameOver = true;
                playSound("hit.wav");
                saveScore((int)score);
                GameOver gameover = new GameOver((int)score);
                gameover.setVisible(true);
				setVisible(false);
				parentJFrame.dispose();
            }
        }

        if (bird.y > boardHeight - bird.height || bird.y < 0) {
            gameOver = true;
            playSound("die.wav");
            saveScore((int)score);
            GameOver gameover = new GameOver((int)score);
            gameover.setVisible(true);
			setVisible(false);
			parentJFrame.dispose();
        }
    }
    
    public void changeMode(String mode) {
    	this.mode = mode;
    	
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
    	
    	placePipeTimer.setDelay(pipeInterval);
    	
    	modeChangeMessage = "Mode Change to " + mode.toUpperCase();
    	modeChangeMessageTime = System.currentTimeMillis();
    	
    }
    
    
    
    public void saveScore(int score) {
        String selectQuery = "SELECT * FROM scores WHERE user_id = ?";
        String insertQuery = "INSERT INTO scores (user_id, score) VALUES (?, ?)";
        String updateQuery = "UPDATE scores SET score = ? WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {

            selectStmt.setInt(1, currentPlayerId);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                // Score already exists, so update it
                int existingScore = rs.getInt("score");
                if (score > existingScore) {
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setInt(1, score);
                        updateStmt.setInt(2, currentPlayerId);
                        updateStmt.executeUpdate();
                    }
                }else{
                    System.out.println("New score is not higher. No update performed.");
                }
            } else {
                // Score doesn't exist, so insert a new record
                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                    insertStmt.setInt(1, currentPlayerId);
                    insertStmt.setInt(2, score);
                    insertStmt.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
        	if (!started) {
                started = true;
                placePipeTimer.start();
                gameLoop.start();
            }
            velocityY = -9;
            
            playSound("wing.wav");
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // 
    }
    
    
    
    private void playSound(String soundFileName) {
        try {
            
            java.net.URL soundURL = getClass().getResource("/audio/" + soundFileName);
            if (soundURL == null) {
                System.out.println("Sound file not found: " + soundFileName);
                return;
            }

            javax.sound.sampled.AudioInputStream audioIn = javax.sound.sampled.AudioSystem.getAudioInputStream(soundURL);
            javax.sound.sampled.Clip clip = javax.sound.sampled.AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            
            clip.addLineListener(event -> {
                if (event.getType() == javax.sound.sampled.LineEvent.Type.STOP) {
                    clip.close();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
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


