  //Akash Basu
      //Mohith Jalagam
      //per.5
      //Kim
      //DontDie.java game Starter: calls both story and expert classes
      //5-19-2017

  import java.awt.*;
	import java.awt.event.*;
	import java.io.File;
	import java.util.Scanner; // imports for game
	import javax.swing.*;
	import javax.swing.event.*;
	
	public class DontDie extends JFrame {
	      JFrame game; // main game frame
	      start callstart;
	      public static void main(String[]args) {
	        DontDie d = new DontDie();
	        d.run();   
	      }
	      public void run() {  // method that creates the Jframe and incorporates all the panels
		   game = new JFrame();
		   callstart = new start();
	       game.setTitle("Don't Die...");
	       game.setSize(744,696);
	       game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       game.setVisible(true);
	       callstart.addMouseListener(callstart);
	       game.setContentPane(callstart);
	         
	      }
	
	
		class start extends JPanel implements MouseListener {
	    int frameX, frameY;
	    Rectangle startRect, instructionsRect, instructionsExitRect, storyRect, expertRect; // Rectangles declared for painted buttons
	    boolean startWindow, instructionsWindow, instructionsExit, storyWindow, expertWindow; // booleans that check for whether the buttons have been clicked
		Polygon triangle;
		Image background;
		int[] exitArr1;
		int[] exitArr2;
		storyMode mode1;
		expertMode mode2;
		Font allWordFont;
	    public start() //initializes global variables
	    {
			frameX = getWidth();
			frameY = getHeight();
			exitArr1 = new int[3];
			exitArr2 = new int[3];
			
			exitArr1[0] = 80;
			exitArr1[1] = 80;       // configures JFrame so that it can be resized along with buttons 
			exitArr1[2] = 30;
			 
		    exitArr2[0] = 20;
		    exitArr2[1] = 20+(frameY/22);
			exitArr2[2] = 20+(20+(frameY/22))/3;
			
			 startRect = new Rectangle((frameX/2)-100,(frameY/2)-50,200,100);//start rectangle
			instructionsRect = new Rectangle((frameX/2)-100,(frameY/2)+100,200,100);
			//Line Above: instructions rectangle
			
			storyRect = new Rectangle(frameX/2-50,frameY/2-90,200,100);//story rectangle
			expertRect = new Rectangle(frameX/2-260,frameY/2-90,200,100);//expert rectangle
			
			instructionsExitRect = new Rectangle(80,20,(frameX/8),(frameY/23));


			triangle = new Polygon(exitArr1, exitArr2, 3);
			startWindow = instructionsWindow = instructionsExit = false; // initializes all booleans for buttons since they haven’t been clicked yet
			storyWindow = expertWindow = false;
			background = Toolkit.getDefaultToolkit().getImage("space.jpg"); //gets space background
			allWordFont = new Font("Arial", Font.PLAIN, 25);
			repaint();
	    }
	    
	     public void paintComponent(Graphics g) //paints the frame
	     {
			super.paintComponent(g);
	        setBackground(Color.BLACK);
			allWordFont = new Font("Arial", Font.PLAIN, getHeight()/50);
			
	        g.setFont(allWordFont);
			frameX = getWidth();
			frameY = getHeight();
	     if(startWindow)  //actual game code
	     {
			 exitArr1[0] = 80;
			 exitArr1[1] = 80;
			 exitArr1[2] = 30;
			 
			 exitArr2[0] = 20;
			 exitArr2[1] = 20+(frameY/22);  // triangle part of back button 
			 exitArr2[2] = 20+(20+(frameY/22))/3;
			triangle = new Polygon(exitArr1,exitArr2,3);
			 g.setColor(Color.WHITE);
			
			g.drawImage(background,0,0,this);
			 g.fillRect(frameX/2-50,frameY/2-90,200,100);//story
			 storyRect = new Rectangle(frameX/2-50,frameY/2-90,200,100);
			 //Line Above: story rectangle
			 g.fillRect(frameX/2-260,frameY/2-90,200,100);//expert
			 expertRect = new Rectangle(frameX/2-260,frameY/2-90,200,100);
			 //Line Above: expert rectangle
			 g.setColor(Color.BLACK);
			 g.drawString("Expert",((frameX/2-50)-130),(50+(frameY/2-90))); // buttons painted: Expert mode
			 g.drawString("Story",((frameX/2-260)+280),(50+(frameY/2-90))); // buttons painted: Story Mode
			 g.setColor(Color.WHITE);
			instructionsExitRect = new Rectangle(80,20,(frameX/8),(frameY/23)); //rectangle variable for backButton
			g.fillRect(80,20,(frameX/8),(frameY/23));//back button
			
			g.fillPolygon(exitArr1,exitArr2,3);
			g.setColor(Color.BLACK);
			g.drawString("Back",(int)((80+(frameX/8))/1.8), (int)((20+(frameY/22))/1.5));
			
			 if(storyWindow) //checks of story mode is selected
			 {
				mode1 = new storyMode();
				game.setContentPane(mode1);
				game.setVisible(true);
			 }
			 if(expertWindow) // checks if expertmode is selected
			 {
				 mode2 = new expertMode();
				 game.setContentPane(mode2);
				 game.setVisible(true);
			 }
	     }
	     else if(instructionsWindow) //Instructions code
	     {
			  exitArr1[0] = 80;
			 exitArr1[1] = 80;
			 exitArr1[2] = 30;
			 
			 exitArr2[0] = 20;
			 exitArr2[1] = 20+(frameY/22);  // frame configurations 
			 exitArr2[2] = 20+(20+(frameY/22))/3;
			 g.drawImage(background,0,0,this); // draws space background
			 g.setColor(Color.WHITE);
			 g.fillRect(80,20,(frameX/8),(frameY/23));//back
			 instructionsExitRect = new Rectangle(80,20,(frameX/8),(frameY/23)); 
			 //Line Above: back rectangle
			 g.fillPolygon(exitArr1,exitArr2,3);
			 g.setColor(Color.BLACK);
			 g.drawString("Back",(int)((80+(frameX/8))/1.8), (int)((20+(frameY/22))/1.5));
			 g.setColor(Color.WHITE);
			 g.drawString("Instructions",(frameX/2)+22,(frameY/8));
          		   g.drawString("Try to shoot down as many asteroids as you possibly can.", (frameX/8),(frameY/8+50));
          		   g.drawString("The tank will only shoot the asteroid if you type the word correctly", (frameX/8),(frameY/8+75));
             		g.drawString("Make sure the asteroids don't reach your galaxy.",(frameX/8),(frameY/8+100));
             
             		g.drawString("Story: The ancient rogue galaxy of Nahnwin has invaded" ,(frameX/8),(frameY/8+125));
             		g.drawString("the milky way.",(frameX/8),(frameY/8+150));
             		g.drawString("They have already captured earth.",(frameX/8),(frameY/8+175));
             		g.drawString("You are a lost rougue who managed to escape the invasion " ,(frameX/8),(frameY/8+200));
             		g.drawString("along with three others.",(frameX/8),(frameY/8+225));
             		g.drawString("Together with the others, you managed to steal four abandoned tanks.",(frameX/8),(frameY/8+250));              
             		g.drawString("FREE THE MILKY WAY OF THESE HORRIBLE FIENDS!",(frameX/8),(frameY/8+275));                           
             		g.drawString("And whatever you do... DON'T DIE!!!",(frameX/8),(frameY/8+300));
			 
			 
		 }
	     else if(!(startWindow) && !(instructionsWindow)) 
	     {    //What the user sees when he/she starts the game
			g.drawImage(background,0,0,this);
	        g.setColor(Color.WHITE);
	        g.fillRect((frameX/2)-100,(frameY/2)-50,200,100);//start
	        g.fillRect((frameX/2)-100,(frameY/2)+100,200,100);//instructions
	        startRect = new Rectangle((frameX/2)-100,(frameY/2)-50,200,100);//start rectangle
			instructionsRect = new Rectangle((frameX/2)-100,(frameY/2)+100,200,100);
			//Line above: instructions rectangle
	        g.setColor(Color.BLACK);
	        Font startFont = new Font("Arial",Font.PLAIN,36);  // paints default home page with frame configurations
	        g.setFont(startFont);
	        g.drawString("Start",(frameX/2)-50,(frameY/2)+10);
	        g.drawString("Instructions",(frameX/2)-100,(frameY/2)+160);
	        g.setColor(Color.WHITE);
	        Font startFont2 = new Font("Arial",Font.PLAIN,106);
	         g.setFont(startFont2);
	
	           g.drawString("DON'T DIE",(frameX/2)-250,(frameY/8));
	    }
	
	     }
	   public void mousePressed(MouseEvent e ) 
	   {   //Checks which rectangle('Start','Instructions') was clicked
	   }
	   public void mouseClicked(MouseEvent e ) {
	    int x = e.getX();
	     int y = e.getY();
	     
	     if(startRect.contains(x,y)) // checks if start was clicked
	     {
	         startWindow = true;
	         repaint();
	
	     }
	     if(instructionsRect.contains(x,y)) // checks if instructions was clicked
	     {
			 instructionsWindow = true;
			 repaint();
		 }
		 
		 if(instructionsExitRect.contains(x,y))//checks if back button for instructions was clicked
		 {
				instructionsWindow = false;
				startWindow = false;
				repaint();
		 }
		 if(triangle.contains(x,y))
		 {
			    instructionsWindow = false;
				startWindow = false;
				repaint();
		 }
		 if(storyRect.contains(x,y)) // checks if story mode was clicked
		 {
			 storyWindow = true;
			 repaint();
		 }
		 if(expertRect.contains(x,y))// checks if expertmode was clicked
		 {
			 expertWindow = true;
			 repaint();
		 }           
	   }
	   public void mouseEntered(MouseEvent e ) {
		   
	   }
	   public void mouseExited(MouseEvent e) {
	   }       
	   public void mouseReleased(MouseEvent e) {
		   
	   }
	 }
	
	}

