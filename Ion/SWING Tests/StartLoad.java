import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import java.awt.Font;

public class StartLoad extends JPanel implements MouseMotionListener, MouseListener{

	private static JFrame StartLoad_frame = new JFrame("StartLoad");
	private BlankArea blankArea = new BlankArea(new Color(20,11,231,188));
    private static StartLoad newContentPane;
    private int sel=0;

	Rectangle StartLoadButton;
	int StartLoadButton_x1,StartLoadButton_x2,StartLoadButton_y1,StartLoadButton_y2;

	Rectangle StartLoadButtonMulti;
	int StartLoadButton_x1m,StartLoadButton_x2m,StartLoadButton_y1m,StartLoadButton_y2m;

	StartLoad(){
		super(new GridBagLayout());
        GridBagLayout gridbag = (GridBagLayout)getLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setBackground(new Color(0,0,123,255));

        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridheight = GridBagConstraints.REMAINDER;

        c.insets = new Insets(1, 1, 1, 1);
        gridbag.setConstraints(blankArea, c);
        blankArea.setBackground(new Color(20,11,231,188));
        add(blankArea);
      
        c.insets = new Insets(0, 0, 0, 0);
        c.gridheight = 200;
        
        blankArea.addMouseMotionListener(this);
        blankArea.addMouseListener(this);
 
        setPreferredSize(new Dimension(1920, 1000));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        StartLoad_frame.pack();

        StartLoadButton = new Rectangle((int)(StartLoad_frame.getSize().getWidth()/2),(int)(StartLoad_frame.getSize().getHeight()/3),(int)(StartLoad_frame.getSize().getWidth()/5),(int)(StartLoad_frame.getSize().getHeight()/8) );
        StartLoadButton.overridecolor = 1;
        StartLoadButton.overridecolorwith = new Color(33,200,200,243);

		StartLoadButtonMulti = new Rectangle((int)(StartLoad_frame.getSize().getWidth()/2),(int)(2*StartLoad_frame.getSize().getHeight()/3),(int)(StartLoad_frame.getSize().getWidth()/5),(int)(StartLoad_frame.getSize().getHeight()/8) );
        StartLoadButtonMulti.overridecolor = 1;
        StartLoadButtonMulti.overridecolorwith = new Color(33,200,200,243);
		blankArea.newRect(StartLoadButton,StartLoadButtonMulti,null,null,null);

	}

	public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    	switch(sel){
    		case 0: break;
    		case 1: break;
    		case 2: break;
    		default: break;
    	}
    }

    public void mouseMoved(MouseEvent e) {
    	if(e.getX()>StartLoadButton_x1 && e.getX()<StartLoadButton_x2 && e.getY()>StartLoadButton_y1 && e.getY()<StartLoadButton_y2)
    	{
    		StartLoadButton.overridecolorwith = new Color(33,23,200,243);
        	blankArea.newRect(StartLoadButton,StartLoadButtonMulti,null,null,null);
    		sel = 1;
    	}
    	else if (e.getX()>StartLoadButton_x1m && e.getX()<StartLoadButton_x2m && e.getY()>StartLoadButton_y1m && e.getY()<StartLoadButton_y2m)
    	{
    		StartLoadButtonMulti.overridecolorwith = new Color(33,23,200,243);
        	blankArea.newRect(StartLoadButton,StartLoadButtonMulti,null,null,null);
    		sel = 2;	
    	}
    	else
    	{
    		StartLoadButton.overridecolorwith = new Color(33,200,200,243);
    		StartLoadButtonMulti.overridecolorwith = new Color(33,200,200,243);
        	blankArea.newRect(StartLoadButton,StartLoadButtonMulti,null,null,null);
    		sel = 0;
    	}
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void reInitBoard() {

        StartLoad_frame.pack();

        StartLoadButton = new Rectangle((int)(StartLoad_frame.getSize().getWidth()/2),(int)(StartLoad_frame.getSize().getHeight()/3),(int)(StartLoad_frame.getSize().getWidth()/5),(int)(StartLoad_frame.getSize().getHeight()/8) );
        StartLoadButton.overridecolor = 1;
        StartLoadButton.overridecolorwith = new Color(33,200,200,243);

        StartLoadButtonMulti = new Rectangle((int)(StartLoad_frame.getSize().getWidth()/2),(int)(2*StartLoad_frame.getSize().getHeight()/3),(int)(StartLoad_frame.getSize().getWidth()/5),(int)(StartLoad_frame.getSize().getHeight()/8) );
        StartLoadButtonMulti.overridecolor = 1;
        StartLoadButtonMulti.overridecolorwith = new Color(33,200,200,243);

        blankArea.newRect(StartLoadButton,StartLoadButtonMulti,null,null,null);
   		blankArea.addString(new ShowString("WAITING FOR USERS:List",(int)(StartLoad_frame.getSize().getWidth()/2),(int)(StartLoad_frame.getSize().getHeight()/3),new Color(0,255,0,255),new Font("Serif", Font.PLAIN, 20)));
   		blankArea.addString(new ShowString("START GAME",(int)(StartLoad_frame.getSize().getWidth()/2),(int)(2*StartLoad_frame.getSize().getHeight()/3),new Color(0,255,0,255),new Font("Serif", Font.PLAIN, 20)));

        StartLoadButton_x1 = (int)(StartLoad_frame.getSize().getWidth()/2) - (int)(StartLoad_frame.getSize().getWidth()/5)/2;
        StartLoadButton_x2 = (int)(StartLoad_frame.getSize().getWidth()/2) + (int)(StartLoad_frame.getSize().getWidth()/5)/2; 
        StartLoadButton_y1 = (int)(StartLoad_frame.getSize().getHeight()/3)-(int)(StartLoad_frame.getSize().getHeight()/8)/2;
        StartLoadButton_y2 = (int)(StartLoad_frame.getSize().getHeight()/3)+(int)(StartLoad_frame.getSize().getHeight()/8)/2;

        StartLoadButton_x1m = (int)(StartLoad_frame.getSize().getWidth()/2) - (int)(StartLoad_frame.getSize().getWidth()/5)/2;
        StartLoadButton_x2m = (int)(StartLoad_frame.getSize().getWidth()/2) + (int)(StartLoad_frame.getSize().getWidth()/5)/2; 
        StartLoadButton_y1m = (int)(2*StartLoad_frame.getSize().getHeight()/3)-(int)(StartLoad_frame.getSize().getHeight()/8)/2;
        StartLoadButton_y2m = (int)(2*StartLoad_frame.getSize().getHeight()/3)+(int)(StartLoad_frame.getSize().getHeight()/8)/2;

    }

    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        StartLoad_frame.setResizable(false);

        //Create and set up the content pane.
        newContentPane = new StartLoad();
        newContentPane.setOpaque(true); //content panes must be opaque


        //Create and set up the window.
        StartLoad_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StartLoad_frame.addComponentListener(new ComponentListener() {
            public void componentResized(ComponentEvent e) {
                newContentPane.reInitBoard();    
            }
            public void componentHidden(ComponentEvent e) {
            }

            public void componentMoved(ComponentEvent e) {
            }

            public void componentShown(ComponentEvent e) {
            }
        });

        StartLoad_frame.add(newContentPane);

        //Display the window.
        StartLoad_frame.pack();
        StartLoad_frame.setVisible(true);
    }

    public static void launch(){
        createAndShowGUI();
    }
}