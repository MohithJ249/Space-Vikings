import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.*; // imports for story mode
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Date;
public class storyMode extends JPanel 
{
    JFrame game;
    Image expertBackground;
    expertMode hitter;
    private int ballX, ballY,pX,pY;
    private boolean ballMoveIt;        // starts and stops asteroids movement
    Timer balltimer;  //Timer for projectile and asteroid
    Timer bulletTimer;
    int randomGenerator;
    GridLayout expertGrid;
    Image background;
    Image galaxy;
    Image asteroid; // images to paint galaxy ,asteroid,tanks, and projectiles
    Image tank;
    Image projectile; 
    int[] exitArr1 = {80,80,30};
    int[] exitArr2 = {20,70,45};
    Rectangle exitRect;
    boolean exit;
    int count; // count used to make levels
    int lvl = 1;// level variable
    File inFile = new File("words2.txt"); // word file for asteroids
    String inFileName = "words2.txt";
    String value= "";
    String level = "Level: "+lvl; // displays level on screen
    String[] word;
    Scanner input = null;// scanner for reading the text file
    int x= 0;
    int time= 10; // time variable for timer for asteroid
    int getspawn, tankx1, tankx2, tankx3, tankx4, asteroidPlacerX;
    boolean asteroidHelper, toRemoveAsteroid;
    int[] spawn;
    int paintGalaxy;
    Rectangle detect, asteroidImage, bulletImage;
    Image bullet;
    int userInputX, userInputY;
    String user;
     JTextField userinput;
 //   Date gameStarted;
 //   long speedHelper;
    int asteroidPlacer;
	boolean finalBackground;
	Image finalDestination;
	int lives;
	String life;
	boolean died, congrats;
	Font gameOver;
	Image gameOverBoss;
    public storyMode()
    {
	died = congrats = false;
	lives = 3;
	life = "Lives: "+lives;
	finalBackground = false;
	asteroidHelper = toRemoveAsteroid = false;
	
	tankx1 = (this.getWidth()/20);
    	tankx2 = (int)((this.getWidth()/3.6));
    	tankx3 = (this.getWidth()/2);
    	tankx4 = (int)((this.getWidth()/1.35));
        spawn = new int[4];
	spawn[0] = tankx1;
	spawn[1] = tankx2;
	spawn[2] = tankx3;
	spawn[3] = tankx4;
	
        
//	gameStarted = new Date();
        ballX = 237;
        ballY = 10;     // coordinates for asteroid
        ballMoveIt = false;
        
	//speedHelper = gameStarted.getTime();

	//System.out.println(speedHelper);
        setLayout(null); // null layout for text field
        
        BallMover ballmover = new BallMover();
        balltimer = new Timer(time, ballmover); // timer for asteroid
        balltimer.start();

        expertGrid = new GridLayout(4,5);
        run();
        KeyText k= new KeyText();
        addKeyListener(k);
        getWords();
        repaint();
    
        background = Toolkit.getDefaultToolkit().getImage("space2.jpg");
        galaxy = Toolkit.getDefaultToolkit().getImage("space.jpg");
        finalDestination = Toolkit.getDefaultToolkit().getImage("space4.jpg");
        tank = Toolkit.getDefaultToolkit().getImage("tank1.png");
	gameOverBoss = Toolkit.getDefaultToolkit().getImage("boss1.GIF");
                //     projectile =Toolkit.getDefaultToolkit().getImage("projectile.png");
                     
       userinput = new JTextField(10);
       userinput.addKeyListener(k);
       add(userinput);
     
      
      detect = new Rectangle(ballX,ballY,170,168);



    }
   
    public void run()
    {
        for(int i = 5; i < 21; i++)
        {
            add(new JLabel());
            if(i >= 17)
            {
                //g.drawImage(tank,0,0,this); // This is not necessary right now but will be changed 
            }
        }
    }
    public void getWords() { // reads 1000 words and takes 200 words and puts them into an array

	int numOfWords = 0;                 
        String all = "";
         try
         {
            input = new Scanner(inFile);
         }
         catch(FileNotFoundException e )
         {
            System.out.println("Cannot find "+inFileName+" file.");
            System.exit(1);    
         }
         while(input.hasNext())
         {
             numOfWords++;
		
			String word = input.next();
			all += word + '$';
		
         }
       	word = new String[numOfWords];
	for (int i = 0; i < numOfWords; i++) {
		word[i] = all.substring(0, all.indexOf('$'));
		all = all.substring(all.indexOf('$') + 1);
	}
		
    
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
	gameOver = new Font("Arial", Font.BOLD, 36);
	if(congrats)
	{
		remove(userinput);
		g.drawString("Congratulations! You survived!", 300,500);
	}
	else if(!(died))
	{
		tankx1 = (this.getWidth()/20);
    		tankx2 = (int)((this.getWidth()/3.6));
    		tankx3 = (this.getWidth()/2);
    		tankx4 = (int)((this.getWidth()/1.35));
 
		spawn[0] = tankx1;       //spawning locations
		spawn[1] = tankx2;
		spawn[2] = tankx3;
		spawn[3] = tankx4;

		paintGalaxy = (this.getHeight()/2)+200;
		if(!(finalBackground))
        		g.drawImage(background,0,0,this);
		else 
			g.drawImage(finalDestination,0,0,this);     
       		 //g.drawImage(expertBackground,0,0,this);
       		 g.setColor(Color.white);
	
		if(getspawn == 0)
		{
			
			asteroidPlacer = tankx1;
      		 	g.drawImage(asteroid,asteroidPlacer, ballY,this.getWidth()/6, this.getHeight()/5,null);
        		Font ballFont = new Font("Arial",Font.PLAIN,26); //paints asteroids and words        g.setFont(ballFont);
       		 	g.drawString(word[x],(asteroidPlacer+40),(ballY+85));
		}
		else if(getspawn == 1)
		{
			asteroidPlacer = tankx2;
        		g.drawImage(asteroid,asteroidPlacer, ballY,this.getWidth()/6, this.getHeight()/5,null);
        		Font ballFont = new Font("Arial",Font.PLAIN,26); //paints asteroids and words        g.setFont(ballFont);
        		g.drawString(word[x],(asteroidPlacer+40),(ballY+85));
		}
		else if(getspawn == 2)
		{
			asteroidPlacer = tankx3;
        		g.drawImage(asteroid,asteroidPlacer, ballY,this.getWidth()/6, this.getHeight()/5,null);
        		Font ballFont = new Font("Arial",Font.PLAIN,26); //paints asteroids and words        g.setFont(ballFont);
        		g.drawString(word[x],(asteroidPlacer+40),(ballY+85));
		}	
		else 
		{
			asteroidPlacer = tankx4;
        		g.drawImage(asteroid,asteroidPlacer, ballY,this.getWidth()/6, this.getHeight()/5,null);
        		Font ballFont = new Font("Arial",Font.PLAIN,26); //paints asteroids and words        g.setFont(ballFont);
        		g.drawString(word[x],(asteroidPlacer+40),(ballY+85));
		}
		if(asteroidHelper)
		{
		
	
			g.drawImage(bullet, asteroidPlacer, pY, this.getWidth()/10, this.getHeight()/5, this);
			bulletImage = new Rectangle(asteroidPlacer, pY, this.getWidth()/10, this.getHeight()/5);
		
			if(bulletImage.contains(asteroidPlacer, ballY))
			{
				count++;
				balltimer.stop();
				bulletTimer.stop();
				pY = paintGalaxy;
				ballY = 10;
				randomizingWords();
			
			}
		}	
	
       		 g.drawString(level,(this.getWidth()/20),50);
      		  g.drawString(life, (int)((this.getWidth()/1.35)), 50);
       
      		  g.setColor(Color.white);
        
     		   g.drawImage(galaxy,0,paintGalaxy, this.getWidth(), this.getHeight(), null);
      		  g.drawImage(tank, (this.getWidth()/20), (this.getHeight()/2)+200, this.getWidth()/10, this.getHeight()/5, null);  //tank1 From left to right
      		  g.drawImage(tank,(int)((this.getWidth()/3.6)),(this.getHeight()/2)+200, this.getWidth()/10, this.getHeight()/5, null); //tank2
       		 g.drawImage(tank,(this.getWidth()/2),(this.getHeight()/2)+200, this.getWidth()/10, this.getHeight()/5, null); //tank3
       		 g.drawImage(tank,(int)((this.getWidth()/1.35)),(this.getHeight()/2)+200, this.getWidth()/10, this.getHeight()/5, null);  //tank4

			//this.getWidth and this.getHeight help adjust images according
				//to the frame size

		userInputX = (int)(((int)((this.getWidth()/3.6)) + (this.getWidth()/2))/2);
 		userInputY = this.getHeight()/2+300;
      		 userinput.setBounds(userInputX,userInputY, this.getWidth()/10, 20);
		//helps the JTextField adjust according to the frame size
	}
	
	else if(died)
	{
		remove(userinput);
		setBackground(Color.WHITE);
		g.setColor(Color.RED);
		g.setFont(gameOver);
		g.drawImage(gameOverBoss, 0,0,this);
		g.drawString("Game Over", (this.getWidth()/6),(this.getHeight()/2)-100);
		g.drawString("I thought I told you not to die!", (this.getWidth()/20),(this.getHeight()/2-50));
	}
	
	
    }
    public void randomizingWords()
    {
	getspawn = (int)(Math.random()*4)+0;    //helps randomizing location of asteroid
        ballX=spawn[getspawn];      	//location randomizer
	x= (int)(Math.random()*99)+0;
	balltimer.start();
    }
    class BallMover implements ActionListener 
    {
		
        public void actionPerformed(ActionEvent e) //actionListener for the asteroids
        { // Controls the asteroid and randomizes words using math.random 
            ballY++;
	 asteroid = Toolkit.getDefaultToolkit().getImage("asteroid 2.png"); //getsimages for story mode
             
		       
            if (ballY > paintGalaxy) 
	    {
                ballY = 10;
              //  int y = x+=2;      //increases the speed
		getspawn = (int)(Math.random()*4)+0;    //helps randomizing location of asteroid
               	ballX=spawn[getspawn];      	//location randomizer
		x= (int)(Math.random()*99)+0;
		
              
            }
		
	    if(ballY==paintGalaxy)
	    {
			  life  = "Lives :"+(lives-1);
		lives--;
                if(lives == 0)
		{
			died = true;
		}
	    }
	    else
	    {
		if(count==18)
		{
			lvl++;
			time = 7;
		}
                if(count==30)
		{
			time = 4;
			finalBackground = true;
                     lvl++;		
		}
		if(count==33)
		{
			congrats = true;
		}
		
		 level = "Level: "+((count/3)+1);

	    }
           repaint();

        }
    }    
     class bulletMover implements ActionListener {
        public void actionPerformed(ActionEvent e) //actionListener for the asteroids
        { // Controls the asteroid and randomizes words using math.random 
		bullet = Toolkit.getDefaultToolkit().getImage("projectile.png"); 
            	pY--;
	  	
            

        }
    }    
    class KeyText implements KeyListener
    {
	String user2;
	 public void keyPressed(KeyEvent e) {
	  if(e.getKeyCode() == KeyEvent.VK_ENTER) {
	     	user = userinput.getText();
			userinput.setText("");
			
			if(user.equals(word[x])){

				bulletMover bMover = new bulletMover();
				bulletTimer = new Timer(5, bMover);	//timer for projectile
				bulletTimer.start();
        			pY = paintGalaxy;
				asteroidHelper = true;
				repaint();
	
					
			}
			
	}
	 }
	 public void keyTyped(KeyEvent e) {
	 }
	 public void keyReleased(KeyEvent e) {
	 }
	
	}
   
}



