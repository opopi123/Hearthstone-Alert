package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window {
	
	public void init(){
		
		final JFrame frame = new JFrame("Hearthstone Secret Tracker");
		//Panels add ability for organization.
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		frame.add(panel1);
		frame.add(panel2);
		frame.add(panel3);
		//App will exit on closing the window.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(200, 400);
		
		
		/* moved all lines where the Secrets are instantiated to SecretArray.java
		 * Instead the actionlisteners just create new SecretArrays
		 * 
		 */
	
		JButton hunterButton = new JButton("Hunter");
		hunterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	SecretArray myHunterSecret = new SecretArray(1);
            	rework(frame, myHunterSecret, 1);     	
            }
        });
		JButton mageButton = new JButton("Mage");
		mageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	SecretArray myMageSecret = new SecretArray(2);
            	rework(frame, myMageSecret, 2);     	
            }
        });	
		JButton pallyButton = new JButton("Paladin");
		pallyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	SecretArray myPallySecret = new SecretArray(3);
            	rework(frame, myPallySecret, 3);     	
            }
        });
		
		panel1.add(hunterButton);
		panel2.add(mageButton);
		panel3.add(pallyButton);
		//Adds the buttons to the JFrame, with the panels they're each in.
		frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
		frame.getContentPane().add(panel1, BorderLayout.CENTER);
		frame.getContentPane().add(panel2, BorderLayout.LINE_START);
		frame.getContentPane().add(panel3, BorderLayout.LINE_END);	
		
		
	}
	
	//Should remove elements of SecretArray every time a button is pressed
	//Define buttons' ActionListeners in here, or through another method
	public void rework(final JFrame frame, final SecretArray secrets, final int secretType){
		
	 	frame.setSize(600, 500);
    	frame.getContentPane().removeAll();
    	JPanel[] panelArray = new JPanel[20]; 
    	JButton[] buttonArray = new JButton[20];
    	for(int i=0; i<11; i++){  //Creating panels/buttons for triggers, 11 overall
    		panelArray[i] = new JPanel();
    		buttonArray[i] = new JButton();
    		panelArray[i].add(buttonArray[i]);
    	}
		
		panelArray[11] = new JPanel();
		final JLabel secretText = new JLabel(secrets.returnArray(secrets,secretType));
		panelArray[11].add(secretText);
		
		panelArray[12] = new JPanel();
		buttonArray[12] = new JButton("Reset");
		panelArray[12].add(buttonArray[12]);
    	
    	
    	//ButtonArray populated with Action Listeners.
   		buttonArray[0].setText("Minion Attacks Enemy Minion");
		buttonArray[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				eliminateSecrets(0, frame, secrets, secretType, secretText);
			}
		});
		buttonArray[1].setText("Minion Attacks Enemy Hero");
		buttonArray[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				eliminateSecrets(1, frame, secrets, secretType, secretText);
			}
		});
		buttonArray[2].setText("Hero Attacks Enemy Minion");
		buttonArray[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				eliminateSecrets(2, frame, secrets, secretType, secretText);
			}
		});
		buttonArray[3].setText("Hero Attacks Enemy Hero");
		buttonArray[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				eliminateSecrets(3, frame, secrets, secretType, secretText);
			}
		});
		buttonArray[4].setText("Minion is Summoned");
		buttonArray[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				eliminateSecrets(4, frame, secrets, secretType, secretText);
			}
		});
		buttonArray[5].setText("Enemy Minion Dies");
		buttonArray[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				eliminateSecrets(5, frame, secrets, secretType, secretText);
			}
		});
		buttonArray[6].setText("Enemy Hero Dies");
		buttonArray[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				eliminateSecrets(6, frame, secrets, secretType, secretText);
			}
		});
		buttonArray[7].setText("Enemy Minion Dies with Enemy Minion Left on the Board");
		buttonArray[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				eliminateSecrets(7, frame, secrets, secretType, secretText);
			}
		});
		buttonArray[8].setText("Spell Cast");
		buttonArray[8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				eliminateSecrets(8, frame, secrets, secretType, secretText);
			}
		});
		buttonArray[9].setText("Spell Cast on Enemy Minion");
		buttonArray[9].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				eliminateSecrets(9, frame, secrets, secretType, secretText);
			}
		});
		buttonArray[10].setText("Spell Cast on Enemy Hero");
		buttonArray[10].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				eliminateSecrets(10, frame, secrets, secretType, secretText);
			}
		});
		
		//Reset Button
		buttonArray[12].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				SecretArray newSecretArray = new SecretArray(secretType);
				rework(frame, newSecretArray, secretType);
			}
		});
		
		
		frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
		for(int i=0; i<13; i++){
			panelArray[i].setBounds(50,50,100, i*70);
			frame.add(panelArray[i]);
		}       
		frame.pack();
		
	}
	
	
	//Got rid of TriggerCheck, placed it in here and SecretArray is printed out in JLabel
	public void eliminateSecrets(int trigger, JFrame frame, SecretArray secrets, int secretType, 
			JLabel secretText){
		
		String text;  //Text for secrets
		String finale = "The secret has been activated.";
		
		switch(secretType){
		//hunter
		case 1:
			for(int i = 0; i < 5; i++){
				if(secrets.hunterarray[i].TriggerArray[trigger]){
					secrets.remove(secrets, i, 1);
				}
			}
			text = secrets.returnArray(secrets, secretType);
			if(text.isEmpty()){
				secretText.setText(finale);
			}
			else{
				secretText.setText(secrets.returnArray(secrets, secretType));
			}
			break;
			
		//mage
		case 2:
			for(int i = 0; i < 7; i++){
				if(secrets.magearray[i].TriggerArray[trigger]){
					secrets.remove(secrets, i, 2);
				}
			}
			text = secrets.returnArray(secrets, secretType);
			if(text.isEmpty()){
				secretText.setText(finale);
			}
			else{
				secretText.setText(secrets.returnArray(secrets, secretType));
			}
			break;
			
		//paladin
		case 3:
			for(int i = 0; i < 5; i++){
				if(secrets.pallyarray[i].TriggerArray[trigger]){
					secrets.remove(secrets, i, 3);
				}	
			}
			text = secrets.returnArray(secrets, secretType);
			if(text.isEmpty()){
				secretText.setText(finale);
			}
			else{
				secretText.setText(secrets.returnArray(secrets, secretType));
			}
			break;
			

		}
	}
		
}
	

