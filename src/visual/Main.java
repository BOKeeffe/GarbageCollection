package visual;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import allFish.BlueFish;
import allFish.Fish;
import allFish.RedFish;
import allFish.YellowFish;



/**
 * @author williamokeeffe
 * 20050506
 * *********************************************************
 * Main is the driver class in the program.
 * The program consists of four stages
 * 1). Allocate Fish---When the allocate Fish buttons are press, red/blue or yellow fish. the sub classes of fish are added to temporary Heap(ArrayList)
 * 
 * 2). Add root----The garbage collector will remove all references from the temporary Heap(Arraylist) that are 
 * not a root or linked to a root. A root can be added to a fish using the add root button
 * 
 * 3). Reference--- If a fish is referenced by another fish that is either a root or linked to a root it is marked not for marked true ie not for collection.
 * A referenced can be added to  a fish in the temporary heap by pressing the reference button and choosing the index of referencer fish and the referenced fish
 *
 * 4). Once the collector button is pressed all the fish in the temporary Heap(ArrayList) is add to the Permanent Heap(HashMap) and the ArrayList is cleared
 * for more fish. then the fish marked true or false depending on conditions. 
 * The marked booleans are stored as the HashMap Values and fish as the Keys. 
 * If a Hashmap boolean (value) is == to false then the matching Keys are removed from the Permanent Heap. all boolean (Values) marked true are safed from 
 * collection in HashMap
 * 
 *
 */
public class Main extends JFrame implements ActionListener{
	
	

	private JFrame frame;
	RedFish redFish;
	BlueFish blueFish;
	YellowFish yellowFish;
	Fish fish;

	
	

	public ArrayList<Fish> list;
	public HashMap<Fish, Boolean> map;
	public int index  = 0;
	public boolean marked;
	
	
	
	
	
	//Jframe Buttons
	
	private JButton redButton;
	private JButton blueButton;
	private JButton yellowButton;
	private JButton mainMenu;
	private JButton addVar;
	private JButton showFish;
	private JButton ShowHeap;
	private JButton reference;
	private JButton garbageC;
	private JButton permHeap;
	
	
	public Main() {
		list = new ArrayList<Fish>();
		map = new HashMap<>();
		initialize();
		;
		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Frame
		frame = new JFrame();
		frame.setBounds(100, 100, 563, 358);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		allocateFishButtons(); // allocate fish JButtons
		assignRefButtons();     // assign references JButtons
		garbageCollectorButtons(); // Garbage collector JButtons
		
	}
	
	
	public void sweep(){
		 list.clear();
	     if (map.get(redFish).booleanValue() == false){
	    	 String input = JOptionPane.showInputDialog("Current heap state before collection" + "\n"+  map + "\n\n" + "RedFish is redundant and is available for collection" + "\n" + "Type option to run Garbage Colection (y/n)");
	    	 if(input.equals("y")){
	    		 map.remove(redFish);
	    	 }	 
	    	 else{
	    		 //do nothing
	    	 }
	     }
	     else if (map.get(blueFish).booleanValue() == false){
	    	 String input = JOptionPane.showInputDialog("Current heap state before collection" + "\n" + map + "\n\n" + "BlueFish is redundant and is available for collection" + "\n" + "Type option to run Garbage Colection (y/n)");
	    	 if(input.equals("y")){
	    		 map.remove(blueFish);
	    		 JOptionPane.showMessageDialog(null, "BlueFish removed from heap memory freed");
	    	 }	 
	    	 else{
	    		 //do nothing
	    	 }
	     }
	     else if (map.get(yellowFish).booleanValue() == false){ 
	    	 String input = JOptionPane.showInputDialog("Current heap state before collection" + "\n" + map + "\n\n" + "YellowFish is redundant and is available for collection" + "\n" + "Type option to run Garbage Colection (y/n)");
	    	 if(input.equals("y")){
	    		 map.remove(yellowFish);
	    		 JOptionPane.showMessageDialog(null, "YellowFish removed from heap memory freed");
	    	 }	 
	    	 else{
	    		 //do nothing
	    	 }
	     }
	     
		JOptionPane.showMessageDialog(null, "The Heap state after garbage Collection" +  "\n" + map);   
	}
	
	
	/**
	 * redMarkValue()
	 * 				
	 * If the booleans root & linkedToRoot are true then marked value of redFish is true
	 */
	public void redMarkValue(){
		if(redFish.root == true || redFish.linkedToRoot == true){
			redFish.marked = true;
		}else{
			redFish.marked = false;	
		}
	}
	
	
	/**
	 * BlueMarkValue()
	 * 				
	 * If the booleans root & linkedToRoot are true then marked value of redFish is true
	 */
	public void blueMarkValue(){
		if(blueFish.root == true || blueFish.linkedToRoot == true){
			blueFish.marked = true;
		}else{
			blueFish.marked = false;
		}
	}
	
	
	/**
	 * yellowMarkValue()
	 * 				
	 * If the booleans root & linkedToRoot are true then marked value of redFish is true
	 */
	public void yellowMarkValue(){
		if(yellowFish.root == true || yellowFish.linkedToRoot == true){
			yellowFish.marked = true;
		}else{
			yellowFish.marked = false;
		}
	}
	
	
	/**
	 * A method that adds all the fish from the Fish AttayList to the Fish  Heap  (HashMap)
	 */
	public void addToHashMap(){
		for (int i = 0; i < list.size(); i++) {
			if(i == list.indexOf(redFish)){
				redMarkValue();
				map.put(redFish, redFish.marked);
				JOptionPane.showMessageDialog(null, map.keySet() + "value " + map.values() );
				
			}
			else if(i == list.indexOf(blueFish)){
				blueMarkValue();
				map.put(blueFish, blueFish.marked);
				JOptionPane.showMessageDialog(null, map.keySet() + "value " + map.values());
				
			}
			else if (i == list.indexOf(yellowFish)) {
				yellowMarkValue();
				//marked = yellowFish.isReferenced();
				map.put(yellowFish, yellowFish.marked);
				JOptionPane.showMessageDialog(null, map.keySet() + "value " + map.values());
			}
			
		}
		
	}	
	
	
	/**
	 * AllocateFish
	 * 
	 * A method that creates the buttons used to allocate fish to the fish array
	 */
	public void allocateFishButtons(){
		JLabel allocateFish = new JLabel("Allocate Fish");
		allocateFish.setBounds(22, 179, 92, 16);
		getFrame().getContentPane().add(allocateFish);
		
		redButton = new JButton("Red Fish");
		redButton.setBounds(202, 174, 117, 29);
		getFrame().getContentPane().add(redButton);
		redButton.addActionListener(this);
		
		blueButton = new JButton("Blue Fish");
		blueButton.setBounds(305, 174, 117, 29);
		getFrame().getContentPane().add(blueButton);
		blueButton.addActionListener(this);
		
		yellowButton = new JButton("Yellow Fish");
		yellowButton.setBounds(411, 174, 117, 29);
		getFrame().getContentPane().add(yellowButton);
		yellowButton.addActionListener(this);
		
		addVar = new JButton("add Root");
		addVar.setBounds(202, 215, 117, 29);
		getFrame().getContentPane().add(addVar);
		addVar.addActionListener(this);
		
		JLabel lblMakeRoot = new JLabel("Add Root");
		lblMakeRoot.setBounds(22, 214, 117, 29);
		getFrame().getContentPane().add(lblMakeRoot);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(42, 19, 1, 16);
		getFrame().getContentPane().add(textPane);
		
		JTextPane txtpnToAllocateA = new JTextPane();
		txtpnToAllocateA.setBounds(16, 6, 541, 156);
		txtpnToAllocateA.setEditable(false);
		txtpnToAllocateA.setBackground(new Color(173, 216, 230));
		txtpnToAllocateA.setText("1). To allocate a fish please press the (Red Fish, Blue Fish, Yellow Fish) buttons.\n\n" +
								 "2). To make an allocated fish a root press the (ADD ROOT) button\n\n" +
				                 "3). To Assign a Reference to a Fish press the (REFERENCE) button\n\n" +
				                 "4). To run the Garbage Collector press the (COLLECTOR) button.\n\n5). To view currently stored fish press the  (SHOW FISH) button.\n\n\n");
		getFrame().getContentPane().add(txtpnToAllocateA);
		
        showFish = new JButton("Show Temp Heap");
        showFish.setBounds(305, 297, 138, 29);
		frame.getContentPane().add(showFish);
		showFish.addActionListener(this);
	}
	
	
	public void assignRefButtons(){
		reference = new JButton("Reference");
		reference.setBounds(202, 256, 117, 29);
		getFrame().getContentPane().add(reference);
		reference.addActionListener(this);
		
		JLabel lblAssignAReference = new JLabel("Assign a Reference");
		lblAssignAReference.setBounds(22, 261, 132, 16);
		getFrame().getContentPane().add(lblAssignAReference);
	}
	
	public void garbageCollectorButtons(){

		garbageC = new JButton("Collector");
		garbageC.setBounds(202, 297, 117, 29);
		getFrame().getContentPane().add(garbageC);
		garbageC.addActionListener(this);
		
		JLabel lblRunTheGarbge = new JLabel("Run the Garbge Collector");
		lblRunTheGarbge.setBounds(22, 302, 172, 16);
		frame.getContentPane().add(lblRunTheGarbge);
		
		permHeap = new JButton("Show Heap");
		permHeap.setBounds(429, 297, 117, 29);
		frame.getContentPane().add(permHeap);
		permHeap.addActionListener(this);
		
	}
	

	
public void actionPerformed(ActionEvent e){
		/*****************************************************************
		 *  Show all fish in the temporary Heap(ArrayList)
		 */
		if (e.getSource() == showFish) {
			if(list.isEmpty()){
				JOptionPane.showMessageDialog(null, "The temperory heap is currently empty");
			}
			else{
				JOptionPane.showMessageDialog(null, "\n" + list);
			}
			 
		}
		
		/**
		 * Show all fish in permanent Heap(HshMap)
		 */
		else if(e.getSource() == permHeap){
			if(map.isEmpty()){
				JOptionPane.showMessageDialog(null, "The Heap is currently empty");
			}
			else{
				JOptionPane.showMessageDialog(null, "\n" + map);
				
			}
		}
		
		
		/**
		 * runs the garbage collector. which adds the arrayList to the hashMap data collection and removes redundant objects
		 */
		else if (e.getSource() == garbageC){
			if(list.isEmpty()){
				JOptionPane.showMessageDialog(null, "Temporary Heap is currently empty");
			}else{
				JOptionPane.showMessageDialog(null, "To run the collector the contents arte being moved from the temoray Heap(ArrayList)" + "\n\n" + "To the permenant Heap(Hashmap) for collection sweep");
				addToHashMap();
				sweep();
				 JOptionPane.showMessageDialog(null, "\n" + map);
			}
		}
		
		/*******************************************************************************
		 * allocates fish to the temporary heap(ArrayList)
		 */

		//Check for red button action
		else if(e.getSource() == redButton){
		 	//JOptionPane.showMessageDialog(null," Add Red Fish");
			String newId = JOptionPane.showInputDialog("Enter id of Red Fish");
			redFish = new RedFish();
			list.add(this.redFish);
			this.redFish.setId(newId);
			JOptionPane.showMessageDialog(null,  list) ;
			
		}
		
		 //Check for blue button action
		else if(e.getSource() == blueButton){
			//JOptionPane.showMessageDialog(null," Add Blue Fish");
			String newId = JOptionPane.showInputDialog("\n" + list + "\n" +"Enter id of Blue Fish");
			blueFish = new BlueFish();
			list.add(this.blueFish);
			this.blueFish.setId(newId);
			JOptionPane.showMessageDialog(null,  list) ;
			
		}
		
		//check for yellow button action
		else if (e.getSource() == yellowButton) {
			//JOptionPane.showMessageDialog(null," Add Yellow Fish");
			String newId = JOptionPane.showInputDialog("Enter id of Yellow Fish" + "\n"+ list);
			yellowFish = new YellowFish();
			list.add(this.yellowFish);
			this.yellowFish.setId(newId);
			JOptionPane.showMessageDialog(null,  list) ;
		}
		
		//check for addVar action
		else if (e.getSource() == addVar) {
			String strInput = JOptionPane.showInputDialog("\n"+"Enter  index of fish make root" + "\n"+ list + " \n");
			index = Integer.parseInt(strInput) ;
			if(index == list.indexOf(redFish)){
				redFish.setRoot(true);
				JOptionPane.showMessageDialog(null,index  + ": " + list + "\n\n" + "RedFish is now a root = true ");
				
				
			}
			else if(index == list.indexOf(blueFish)){
				//System.out.println("hello blue" );
				redFish.setRoot(true);
				JOptionPane.showMessageDialog(null,index  + ": " + list + "\n\n" + "BlueFish is now a root = true ");
				
			}
			else if(index == list.indexOf(yellowFish)){
				//System.out.println("hello yellow");
				yellowFish.setRoot(true);
				JOptionPane.showMessageDialog(null,index  + ": " + list + "\n\n" + "YellowFish is now a root = true ");
				
			}
			
		}
		
		/***********************************************************************
		 * Add references to fish that have been allocated to the arrayList.
		 * A reference can only be added to fish smaller than or equal this fish
		 */
		else if(e.getSource() == reference){
			
			String ref1 = JOptionPane.showInputDialog(null,  " " + getList() + "\n\n" + "Please chose index of first a fish ");
			index = Integer.parseInt(ref1) ;
			
			//RedFish references to red, blue or yellow fish,
			//Red fish can reference any fish as its the largest fish.
			if(index == list.indexOf(redFish)){
				
				String ref2 = JOptionPane.showInputDialog(null, " " + getList() +  "\n\n" + "Please chose index of second fish " );
				index = Integer.parseInt(ref2);
				
				// redFish referenced another RedFish
				if(index == list.indexOf(redFish)){
					
					//check to see if fish 1 (ref1) is a root or linked to a root. if so fish 2 (ref2) is linked to a root
					if(list.get(Integer.parseInt(ref1)).root == true   ||    list.get(Integer.parseInt(ref1)).linkedToRoot == true ){
						list.get(Integer.parseInt(ref2)).setLinkedToRoot(true);
					}
					redFish.setReferenced(true); 
					JOptionPane.showMessageDialog(null, "Element:" + ref2 + ": " + list.get(Integer.parseInt(ref2))  + "\n" + " is now referenced by " + "\n" + "Element" + ref1 + ": " + list.get(Integer.parseInt(ref1)));
				//redFish :  RedFish	
					ref1  =   ref2;
				}
				
				else if(index == list.indexOf(blueFish)){
					
					//check to see if fish 1 (ref1) is a root or linked to a root. if so fish 2 (ref2) is linked to a root
					if(list.get(Integer.parseInt(ref1)).root == true   ||    list.get(Integer.parseInt(ref1)).linkedToRoot == true ){
						list.get(Integer.parseInt(ref2)).setLinkedToRoot(true);
					}
					blueFish.setReferenced(true);
					JOptionPane.showMessageDialog(null, "Element:" + ref2 + ": " + list.get(Integer.parseInt(ref2))  + "\n" + " is now referenced by " + "\n" + "Element" + ref1 + ": " + list.get(Integer.parseInt(ref1)));
				//RedFish :   blueFish	
					ref1  =   ref2;
				}
				
				else if(index == list.indexOf(yellowFish)){
					
					//check to see if fish 1 (ref1) is a root or linked to a root. if so fish 2 (ref2) is linked to a root
					if(list.get(Integer.parseInt(ref1)).root == true   ||    list.get(Integer.parseInt(ref1)).linkedToRoot == true ){
						list.get(Integer.parseInt(ref2)).setLinkedToRoot(true);
					}
					yellowFish.setReferenced(true);
					JOptionPane.showMessageDialog(null, "Element:" + ref2 + ": " + list.get(Integer.parseInt(ref2))  + "\n" + " is now referenced by " + "\n" + "Element" + ref1 + ": " + list.get(Integer.parseInt(ref1)));
				//RedFish  =   yellowFish
					ref1   =   ref2;	
					}
			}
			
			// blueFish References to blueFish or yellow Fish
			//BlueFish can reference blue or yellow fish but not red as red is bigger.
			else if(index == list.indexOf(blueFish)){
				
				String ref2 = JOptionPane.showInputDialog(null, getList() +  "\n\n" + "Please chose index of second fish " );
				index = Integer.parseInt(ref2);
				
				if(index == list.indexOf(redFish)){
					JOptionPane.showMessageDialog(null, "Red Fish is bigger:   Blue Fish cannot reference bigger Fish,  (Please reference only blue/yellow)");	
				}
				else if(index == list.indexOf(blueFish)){
					
					//check to see if fish 1 (ref1) is a root or linked to a root. if so fish 2 (ref2) is linked to a root
					if(list.get(Integer.parseInt(ref1)).root == true   ||    list.get(Integer.parseInt(ref1)).linkedToRoot == true ){
						list.get(Integer.parseInt(ref2)).setLinkedToRoot(true);
					}
					blueFish.setReferenced(true);
					JOptionPane.showMessageDialog(null, "Element:" + ref2 + ":" + list.get(Integer.parseInt(ref2))  + "\n" + " is now referenced by " + "\n" + "Element" + ref1 + ": " + list.get(Integer.parseInt(ref1)));
				 //blueFish :  blueFish	
					ref1   =   ref2;
					JOptionPane.showMessageDialog(null,  list) ;
					
				}
				else if(index == list.indexOf(yellowFish)){
					
					//check to see if fish 1 (ref1) is a root or linked to a root. if so fish 2 (ref2) is linked to a root
					if(list.get(Integer.parseInt(ref1)).root == true   ||    list.get(Integer.parseInt(ref1)).linkedToRoot == true ){
						list.get(Integer.parseInt(ref2)).setLinkedToRoot(true);
					}
					yellowFish.setReferenced(true);
					JOptionPane.showMessageDialog(null, "Element:" + ref2 + ":" + list.get(Integer.parseInt(ref2))  + "\n" + " is now referenced by " + "\n" + "Element" + ref1 + ": " + list.get(Integer.parseInt(ref1)));
					 //blueFish :  blueFish	
						ref1   =   ref2;
						JOptionPane.showMessageDialog(null,  list) ;
					}
				
				
			}	
			
			//yellowFish References to yellow Fish
			//yellow fish can only reference yellow; As red and blue fish are bigger;
			else if(index == list.indexOf(yellowFish)){
				
				String ref2 = JOptionPane.showInputDialog(null, getList() +  "\n\n" + "Please chose index of second fish "  );
				index = Integer.parseInt(ref2);
				
				if(index == list.indexOf(redFish)){
					JOptionPane.showMessageDialog(null, "Red Fish is bigger:   Yellow Fish cannot reference bigger Fish,  (Please reference only Yellow)");
				
				}
				else if(index == list.indexOf(blueFish)){
					JOptionPane.showMessageDialog(null, "Blue Fish is bigger:   Yellow Fish cannot reference bigger Fish,  (Please reference only Yellow)");
				}
				else if(index == list.indexOf(yellowFish)){
					
					//check to see if fish 1 (ref1) is a root or linked to a root. if so fish 2 (ref2) is linked to a root
					if(list.get(Integer.parseInt(ref1)).root == true   ||    list.get(Integer.parseInt(ref1)).linkedToRoot == true ){
						list.get(Integer.parseInt(ref2)).setLinkedToRoot(true);
					}
					yellowFish.setReferenced(true);
					JOptionPane.showMessageDialog(null, "Element:" + ref2 + ":" + list.get(Integer.parseInt(ref2))  + "\n" + " is now referenced by " + "\n" + "Element" + ref1 + ": " + list.get(Integer.parseInt(ref1)));
					 //blueFish :  blueFish	
						ref1   =   ref2;
						JOptionPane.showMessageDialog(null,  list) ;	
				}
				
			}
		}
		
}

	
	
public RedFish getRedFish() {
	return redFish;
}	

public void setRedFish(RedFish redFish) {
	this.redFish = redFish;
}

public BlueFish getBlueFish() {
	return blueFish;
}

	public void setBlueFish(BlueFish blueFish) {
		this.blueFish = blueFish;
	}

	public YellowFish getYellowFish() {
		return yellowFish;
	}

	public void setYellowFish(YellowFish yellowFish) {
		this.yellowFish = yellowFish;
	}

	public Fish getFish() {
		return fish;
	}

	public void setFish(Fish fish) {
		this.fish = fish;
	}

	public ArrayList<Fish> getList() {
		return list;
	}

	public void setList(ArrayList<Fish> list) {
		this.list = list;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
