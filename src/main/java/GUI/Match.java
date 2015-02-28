package GUI;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import teamdin.*;
import Tiles.*;

//tiles by http://www.martinpersson.org.

public class Match extends BasicGameState {
	Image p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14;
	Image of1, of2, of3, of4, of5, of6, of7, of8, of9, of11, of12, of13, of10; //opposite oponent
	Image or1,or2, or3, or4, or5, or6, or7, or8, or9, or11, or12, or13, or10; //right oponent
	Image ol1, ol2, ol3, ol4, ol5, ol6, ol7, ol8, ol9, ol11, ol12, ol13, ol10; //left oponent
	int[] positions;
	Color visible;
	Color invisible;
	Color vp1, vp2, vp3,vp4,vp5,vp6,vp7,vp8,vp9,vp10,vp11,vp12,vp13,vp14;
	
	Image[] oponentLeftTiles, oponentRightTiles, oponentFrontTiles;
	float mouseX, mouseY;
	int approx;
	boolean tileClicked;
	int tileSeleced;
	teamdin.Game game;
	teamdin.Player player;
	int counter;
	boolean swapped;
	
	ArrayList<Tile> hand;
	
	public Match(int state){
		
	}

	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		game = new teamdin.Game();
		player = game.getPlayer();
		
		hand = player.getHand();
		counter = 0;
		
		
		
		
		
		
		p1 = new Image(hand.get(0).getImage());
		p2 = new Image(hand.get(1).getImage());
		p3 = new Image(hand.get(2).getImage());
		p4 = new Image(hand.get(3).getImage());
		p5 = new Image(hand.get(4).getImage());
		p6 = new Image(hand.get(5).getImage());
		p7 = new Image(hand.get(6).getImage());
		p8 = new Image(hand.get(7).getImage());
		p9 = new Image(hand.get(8).getImage());
		p10 = new Image(hand.get(9).getImage());
		p11 = new Image(hand.get(10).getImage());
		p12 = new Image(hand.get(11).getImage());
		p13 = new Image(hand.get(12).getImage());
		p14 = new Image(hand.get(0).getImage());
		
		
		or1=or2= or3= or4= or5= or6= or7= or8= or9= or11= or12= or13= or10 = new Image("res/tiles/facedownright.png");
		of1= of2= of3= of4= of5= of6= of7= of8= of9= of11= of12= of13=  of10 = new Image("res/tiles/facedown.png");
		ol1= ol2= ol3= ol4= ol5= ol6= ol7= ol8= ol9= ol11= ol12= ol13=  ol10 = new Image("res/tiles/facedownleft.png");
		
		oponentLeftTiles = new Image[]{ol1, ol2, ol3, ol4, ol5, ol6, ol7, ol8, ol9, ol11, ol12, ol13, ol10};
		oponentRightTiles = new Image[] {or1,or2, or3, or4, or5, or6, or7, or8, or9, or11, or12, or13, or10};
		oponentFrontTiles = new Image[] {of1, of2, of3, of4, of5, of6, of7, of8, of9, of11, of12, of13, of10};
		
		visible = new Color(1,1,1,1.0f);
		invisible = new Color(1,1,1,0.0f);
		
	
		vp1 = vp2 = vp3 = vp4 = vp5 = vp6 = vp7 = vp8 = vp9 = vp10 = vp11 = vp12 = vp13 = vp14 = visible;
		
		approx = 0;
		tileClicked = false;
		swapped = false;
		
		
		
		
	}

	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(Color.darkGray);
		g.setColor(Color.lightGray);
		g.fillRoundRect(850, 20, 100, 30, 5);
		g.fillRoundRect(50, 20, 100, 30, 5);
		g.fillRoundRect(870, 620, 80, 30, 5);
		g.setColor(Color.black);
		g.drawString("Options", 865, 24);
		g.drawString("Scores", 65, 24);
		g.setColor(Color.red);
		g.drawString("QUIT",890, 626);
		g.drawString("" + counter, 10, 10);
		
		
		
		
		
		
		
		
		//dumb way of doing it, but oh well, don't mind typing. Player tiles
		p1.draw(140,600,70,70, vp1);
		p2.draw(190,600,70,70, vp2);
		p3.draw(240,600,70,70,vp3);
		p4.draw(290,600,70,70,vp4);
		p5.draw(340,600,70,70,vp5);
		p6.draw(390,600,70,70,vp6);
		p7.draw(440,600,70,70,vp7);
		p8.draw(490,600,70,70,vp8);
		p9.draw(540,600,70,70,vp9);
		p10.draw(590,600,70,70,vp10);
		p11.draw(640,600,70,70,vp11);
		p12.draw(690,600,70,70,vp12);
		p13.draw(740,600,70,70,vp13);
		p14.draw(790,600,70,70,vp14);
		
		
		//computer tiles
		int pieceWidth = 40;
		for(Image i: oponentFrontTiles){
			i.draw(200+pieceWidth,25,50,50);
			pieceWidth += 37;
		}
		pieceWidth = 40;
		for(Image i: oponentLeftTiles){
			i.draw(25,50+ pieceWidth,40,40);
			pieceWidth += 33;
		}
		pieceWidth = 40;
		for(Image i: oponentRightTiles){
			i.draw(935,50 +pieceWidth,40,40);
			pieceWidth += 33;
		}
		
		int xpos = 150;
		g.drawString("" + hand.size(), 50,50);
		for(Tile t: hand){
			int id = t.getId();
			g.setColor(Color.red);
			g.drawString(""+id, xpos, 500);
			xpos +=50;
			
		}
		
		
		
		
		
	//	positions = []
		
		
		
		
		
	}
	
	private void refreshHand(){
		try {
			hand = player.getHand();
			p1 = new Image(hand.get(0).getImage());
			p2 = new Image(hand.get(1).getImage());
			p3 = new Image(hand.get(2).getImage());
			p4 = new Image(hand.get(3).getImage());
			p5 = new Image(hand.get(4).getImage());
			p6 = new Image(hand.get(5).getImage());
			p7 = new Image(hand.get(6).getImage());
			p8 = new Image(hand.get(7).getImage());
			p9 = new Image(hand.get(8).getImage());
			p10 = new Image(hand.get(9).getImage());
			p11 = new Image(hand.get(10).getImage());
			p12 = new Image(hand.get(11).getImage());
			p13 = new Image(hand.get(12).getImage());
			p14 = new Image(hand.get(0).getImage());
			
			
			p1.draw(140,600,70,70, vp1);
			p2.draw(190,600,70,70, vp2);
			p3.draw(240,600,70,70,vp3);
			p4.draw(290,600,70,70,vp4);
			p5.draw(340,600,70,70,vp5);
			p6.draw(390,600,70,70,vp6);
			p7.draw(440,600,70,70,vp7);
			p8.draw(490,600,70,70,vp8);
			p9.draw(540,600,70,70,vp9);
			p10.draw(590,600,70,70,vp10);
			p11.draw(640,600,70,70,vp11);
			p12.draw(690,600,70,70,vp12);
			p13.draw(740,600,70,70,vp13);
			p14.draw(790,600,70,70,vp14);
			
			
			
		} catch (SlickException e) {
			
			e.printStackTrace();
		}
		
		
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
		
		
		approx = ((int)mouseX-100)/50;
		
		
		
		
		if(Mouse.isButtonDown(0)==false){ //to avoid being able to click more than one simultaneously
			tileClicked = false;
			tileSeleced = 0;
			
		}
		
		
		
		if(mouseY > 30 && mouseY < 100 && Mouse.isButtonDown(0) && !tileClicked){
			
			switch(approx){
			case 1: 
					//vp1 = invisible; 
					tileSeleced = 1;
					tileClicked=true; 
					break; //Couldve used p1.setAlpha(0.0), but I intend of using the visibility to check. Actual words may be more intuitive.
			case 2: 
					//vp2 = invisible; 
					tileSeleced = 2;
					tileClicked=true; 
					break;
			case 3: 
					//vp3 = invisible; 
					tileSeleced = 3;
					tileClicked=true; 
					break;
			case 4: 
					//vp4 = invisible; 
					tileSeleced = 4;
					tileClicked=true; 
					break;
			case 5: 
					//vp5 = invisible; 
					tileSeleced = 5;
					tileClicked=true; 
					break;
			case 6: 
					//vp6 = invisible; 
					tileSeleced = 6;
					tileClicked=true; 
					break;
			case 7: 
					//vp7 = invisible; 
					tileSeleced = 7;
					tileClicked=true; 
					break;
			case 8: 
					//vp8 = invisible; 
					tileSeleced = 8;
					tileClicked=true; 
					break;
			case 9: 
					//vp9 = invisible; 
					tileSeleced = 9;
					tileClicked=true; 
					break;
			case 10: 
					//vp10 = invisible; 
					tileSeleced = 10;
					tileClicked=true; 
					break;
			case 11: 
					//vp11 = invisible; 
					tileSeleced = 11;
					tileClicked=true; 
					break;
			case 12: 
					//vp12 = invisible; 
					tileSeleced = 12;
					tileClicked=true; 
					break;
			case 13: 
					//vp13 = invisible; 
					tileSeleced = 13;
					tileClicked=true; 
					break;
			case 14: 
					//vp14 = invisible; 
					tileSeleced = 14;
					tileClicked=true; 
					break;
			}
			
		}
		if(Mouse.isButtonDown(0)==false){ //IMPLEMENTING DRAG AND DROP
			tileClicked = false;
			tileSeleced = 0;
		}
		
		if(mouseY > 30 && mouseY < 100 && tileClicked){
			int changeThis;
			if(MouseEvent.MOUSE_DRAGGED ==0){
				
				System.out.println(""+MouseEvent.MOUSE_DRAGGED);
				
					
				
				changeThis = approx-1;
			
				

			}
		//	player.swapTiles(tileSeleced-1, changeThis);
		//	refreshHand();
			
		} 
		
		

		if((mouseX > 850 && mouseX < 950)&& (mouseY > 650 && mouseY < 680)){
			//options screen
			if(Mouse.isButtonDown(0)){
				
				sbg.enterState(1);
			}
		}
		if((mouseX > 50 && mouseX < 150)&& (mouseY > 650 && mouseY < 680)){
			//scores screen
			if(Mouse.isButtonDown(0)){
				sbg.enterState(1);
				
			}
		}
		if((mouseX > 870 && mouseX < 950)&& (mouseY > 50 && mouseY < 80)){
			//start screen
			if(Mouse.isButtonDown(0)){
				sbg.enterState(0);
				
			}
		}
		
		

		
	}
	
	

	@Override
	public int getID() {
		
		return 2;
	}

}
