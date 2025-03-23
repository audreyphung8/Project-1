package labs.lab9;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class VotingSystem 
{
	
    public static void main(String[] args)
    {	
    	final int FIELD_WIDTH = 20;
        JPanel panel = new JPanel(new GridLayout(4, 1)); 

        JPanel electionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        JLabel electionLabel = new JLabel("Election name: ");
        JTextField electionName = new JTextField(FIELD_WIDTH);
        electionPanel.add(electionLabel);
        electionPanel.add(electionName);
        panel.add(electionPanel);

        JPanel aPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel aName = new JLabel("Candidate A Name: ");
        JTextField candidateA = new JTextField(FIELD_WIDTH);
        aPanel.add(aName);
        aPanel.add(candidateA);
        panel.add(aPanel);

        JPanel bPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel bName = new JLabel("Candidate B Name: ");
        JTextField candidateB = new JTextField(FIELD_WIDTH);
        bPanel.add(bName);
        bPanel.add(candidateB);
        panel.add(bPanel);

        JPanel propositionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel propositionName = new JLabel("Num propositions: ");
        JComboBox propositions = new JComboBox();
        for (int i = 1; i <= 15; i++) 
        {
            propositions.addItem(i);
        }
        propositionsPanel.add(propositionName);
        propositionsPanel.add(propositions);
        panel.add(propositionsPanel);

        Object[] message = {panel};
        boolean temp = true;
        
        String telection = "";
    	String tAName = "";
    	String tBName = "";
    	int tpropositions = 0; 
        
        while (temp)
        {
        	int option = JOptionPane.showConfirmDialog(null, message, "Election Info", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        	if (option == JOptionPane.OK_OPTION)
            {
        		telection = electionName.getText();
            	tAName = candidateA.getText();
            	tBName = candidateB.getText();
        		
            	tpropositions = (Integer) propositions.getSelectedItem();
            	
            	if (telection.isEmpty() || tAName.isEmpty() || tBName.isEmpty())
            	{
            		continue;
            	}
            	else
            	{
            		temp = false;
            	}
            }
        	
        	if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) 
        	{
                System.exit(0); 
            }
        }
        
        votingScreen(telection, tAName, tBName, tpropositions);
    }
    
    public static void votingScreen(String telection, String tAName, String tBName, int tpropositions)
    {
    	//Second frame
    	JFrame frame = new JFrame("Voting System - Audrey Phung - 34447680");
    	frame.setSize(600,700);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	//Exit file
    	JMenuBar menuBar = new JMenuBar();
    	JMenu menu = new JMenu("File");
    	JMenuItem exitItem = new JMenuItem("Exit");
    	exitItem.addActionListener(e -> System.exit(0));
    	menu.add(exitItem);
    	menuBar.add(menu);
    	frame.setJMenuBar(menuBar);
    	frame.setVisible(true);
    	
    	JPanel overallPanel = new JPanel(new BorderLayout());
    	overallPanel.setLayout(new BoxLayout(overallPanel, BoxLayout.Y_AXIS));
    	
    	JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	JLabel title = new JLabel(telection);
    	titlePanel.add(title);
    	titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, titlePanel.getPreferredSize().height));
    	overallPanel.add(titlePanel);
    	
    	int votes[] = {0,0};
    	JPanel candidatesPanel = new JPanel();
    	candidatesPanel.setLayout(new GridLayout(2,1));
    	candidatesPanel.setBorder(BorderFactory.createTitledBorder("Candidates:"));
    	JLabel candidateA = new JLabel(tAName + ": " + votes[0]);
    	JLabel candidateB = new JLabel(tBName + ": " + votes[1]);
    	boldLargerVotes(votes[0], votes[1], candidateA, candidateB);
    
    	candidatesPanel.add(candidateA);
    	candidatesPanel.add(candidateB);
    	overallPanel.add(candidatesPanel);
    	
    	JPanel propositionsPanel = new JPanel();
    	propositionsPanel.setLayout(new GridLayout(tpropositions, 1));
    	propositionsPanel.setBorder(BorderFactory.createTitledBorder("Propositions:"));
    	
    	JLabel[] proposition = new JLabel[tpropositions];
    	JLabel[] yes = new JLabel[tpropositions];
    	JLabel[] no = new JLabel[tpropositions];
    	int[] yesVotes = new int[tpropositions]; 
    	int[] noVotes = new int[tpropositions];
    	
    	for (int i = 0; i < tpropositions; i++)
    	{ 
    		JPanel temp = new JPanel(new FlowLayout(FlowLayout.LEFT));
    		proposition[i] = new JLabel(i+1 + ": ");
    		yes[i] = new JLabel("YES: " + yesVotes[i] + " votes,");
    		no[i] = new JLabel("NO: " + noVotes[i] + " votes");
    		boldLargerVotes(yesVotes[i], noVotes[i], yes[i], no[i]);

    		temp.add(proposition[i]);
    		temp.add(yes[i]);
    		temp.add(no[i]);
    		
    		propositionsPanel.add(temp);
    	}
    	overallPanel.add(propositionsPanel);
    	
    	double[] donation = {0};
    	JPanel donationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    	JLabel donationLabel = new JLabel(String.format("Donation total: $%.2f", donation[0]));
    	donationPanel.add(donationLabel);
    	donationPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, donationPanel.getPreferredSize().height));
    	overallPanel.add(donationPanel);
    	
    	JPanel notesPanel = new JPanel();
    	JLabel noteText = new JLabel("Notes: ");
    	notesPanel.add(noteText);
    	JTextArea notesBox = new JTextArea(5, 30); 
    	notesBox.setLineWrap(true); 
    	JScrollPane scrollPane = new JScrollPane(notesBox);
    	notesPanel.add(scrollPane);
    	notesPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, notesPanel.getPreferredSize().height));
    	overallPanel.add(notesPanel);

    	JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    	JButton voteButton = new JButton("Cast Vote");
    	voteButton.addActionListener(e -> castingVoteScreen(votes, tAName, tBName, tpropositions, candidateA, candidateB, yes, no, yesVotes, noVotes, donation, donationLabel));
    	buttonPanel.add(voteButton);
    	overallPanel.add(buttonPanel);
    	
    	
    	frame.add(overallPanel);
    	frame.setVisible(true);
    }
    
    public static void boldLargerVotes(int vote1, int vote2, JLabel yesVote, JLabel noVote)
    {
    	if (vote1 > vote2)
    	{
    		yesVote.setFont(new Font("Arial", Font.BOLD, 12));
    		noVote.setFont(new Font("Arial", Font.PLAIN, 12));
    	}
    	else if (vote1 == vote2) 
    	{
    		yesVote.setFont(new Font("Arial", Font.BOLD, 12));
			noVote.setFont(new Font("Arial", Font.BOLD, 12));
    	}
    	else
    	{
    		noVote.setFont(new Font("Arial", Font.BOLD, 12));
    		yesVote.setFont(new Font("Arial", Font.PLAIN, 12));
    	}
    }
    
    public static void castingVoteScreen(int votes[], String tAName, String tBName, int tpropositions, JLabel tcandidateA, JLabel tcandidateB, JLabel yes[], JLabel no[], int yesVotes[], int noVotes[], double donation[], JLabel donationLabel)
    {
    	JPanel overallPanel = new JPanel(new BorderLayout());
    	overallPanel.setLayout(new BoxLayout(overallPanel, BoxLayout.Y_AXIS));
    	
    	//Candidates
    	JPanel candidatesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    	candidatesPanel.setBorder(BorderFactory.createTitledBorder("Candidates:"));
    	JLabel candidateTitle = new JLabel("Candidate:");
    	candidatesPanel.add(candidateTitle);
    	
    	JRadioButton candidateA = new JRadioButton(tAName);
    	JRadioButton candidateB = new JRadioButton(tBName);
    	ButtonGroup g = new ButtonGroup();
    	g.add(candidateA);
    	g.add(candidateB);
    	
    	candidatesPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, candidatesPanel.getPreferredSize().height));
    	candidatesPanel.add(candidateA);
    	candidatesPanel.add(candidateB);
    	overallPanel.add(candidatesPanel);
    	
    	//Propositions
    	JPanel propositionsPanel = new JPanel();
    	propositionsPanel.setLayout(new GridLayout(tpropositions, 1));
    	propositionsPanel.setBorder(BorderFactory.createTitledBorder("Propositions:"));
    	JRadioButton[] yesButton = new JRadioButton[tpropositions];
    	JRadioButton[] noButton = new JRadioButton[tpropositions];
    	for (int i = 0; i < tpropositions; i++)
    	{
    		yesButton[i] = new JRadioButton("YES");
    		noButton[i] = new JRadioButton("NO");
    		ButtonGroup g2 = new ButtonGroup();
    		g2.add(yesButton[i]);    		
    		g2.add(noButton[i]);
    		
    		JLabel propLabel = new JLabel("Prop " + (i+1) + ": ");
    		JPanel temp = new JPanel();
    		temp.add(propLabel);
    		temp.add(yesButton[i]);
    		temp.add(noButton[i]);
    		
    		propositionsPanel.add(temp);
    	}
    	overallPanel.add(propositionsPanel);
    	
    	//Donation
    	JPanel donationPanel =  new JPanel(new FlowLayout(FlowLayout.LEFT));
    	donationPanel.setBorder(BorderFactory.createTitledBorder("Donation:"));
    	JCheckBox makeDonation = new JCheckBox("I would like to make a donation");
    	JTextField donationAmount = new JTextField("0.00", 10);
    	donationAmount.setEnabled(false);
    	
    	makeDonation.addActionListener(e -> {
    		if (makeDonation.isSelected())
    		{
    			donationAmount.setEnabled(true);
    			donationAmount.setText("0.00");
    		}
    		else
    		{
    			donationAmount.setEnabled(false);
    			donationAmount.setText("0.00");
    		}
    	});
    	
    	donationPanel.add(makeDonation);
    	donationPanel.add(donationAmount);
    	overallPanel.add(donationPanel);
    	
    	//Button
    	Object message[] = {overallPanel};
    	int option = JOptionPane.showConfirmDialog(null, message, "Cast Vote", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    	boolean temp = true;
    	
    	if (option == JOptionPane.OK_OPTION)
    	{
    		temp = true;
    		if (makeDonation.isSelected())
    		{
    			try
    			{
    				 double tdonation = Double.parseDouble(donationAmount.getText());
    				 
    				 if (tdonation >= 0)
    				 {
    					 donation[0] += tdonation;
    					 donationLabel.setText(String.format("Donation total: $%.2f", donation[0]));
    				 }
    				 else
    				 {
    					 JOptionPane.showMessageDialog(null, "Please enter a valid donation amount.", "Error", JOptionPane.ERROR_MESSAGE);
    					 temp = false;
    				 }
    			}
    			catch (NumberFormatException e)
    			{
    				JOptionPane.showMessageDialog(null, "Please enter a valid donation amount.", "Error", JOptionPane.ERROR_MESSAGE);
    				temp = false;
    			}
    		}
    		
    		if (temp)
    		{
    			if (candidateA.isSelected())
        		{
        			votes[0]++;
        			tcandidateA.setText(tAName + ": " + votes[0]);
        		}
        		else if (candidateB.isSelected())
        		{
        			votes[1]++;
        			tcandidateB.setText(tBName + ": " + votes[1]);
        		}
        		boldLargerVotes(votes[0], votes[1], tcandidateA, tcandidateB);
        		
        		for (int i = 0; i < tpropositions; i++)
        		{
        			if (yesButton[i].isSelected())
        			{
        				yesVotes[i]++;
        				yes[i].setText("YES: " + yesVotes[i] + " votes,");
        			}
        			else if (noButton[i].isSelected())
        			{
        				noVotes[i]++;
        				no[i].setText("NO: " + noVotes[i] + " votes");
        			}
        			boldLargerVotes(yesVotes[i], noVotes[i], yes[i], no[i]);
        		}
    		}
    		
    	}
    }
    
    
}
