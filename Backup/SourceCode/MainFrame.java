import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

// Main GUI.
public class MainFrame extends JFrame implements ActionListener, FocusListener{
	// MainFrame fields
	private JButton addOneButton;
	private JButton addTenButton;
	private JButton addFifteenButton;
	private JButton submitButton;
	private JButton summaryButton;
	private JButton newGoalButton;
	private JLabel recommendedAverageLabel;
	private JLabel totalCountLabel;
	private JLabel dayLabel;
	private JLabel dailyCountLabel;
	private JLabel missedDaysLabel;
	private JLabel quotaLabel;
	private JLabel yearQuotaLabel;
	private JLabel highScoreLabel;
	private JTextField manualEntryField;
	private Member member;
	
	// New goal frame fields
	private JFrame newGoalFrame;
	private JLabel newGoalLabel;
	private JTextField newGoalField;
	private JButton newGoalSubmitButton;
	
	// constructor
	public MainFrame(Member member){
		// import member
		this.member = member;
		
		// frame
		setSize(700, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((dim.width - getWidth())/2, (dim.height - getHeight())/2);
		setLayout(new GridBagLayout());
		setVisible(true);
		
		// add one to count
		addOneButton = new JButton("+1");
		addOneButton.setToolTipText("Click here to increase your counter by one.");
		addOneButton.addActionListener(this);
		addOneButton.setActionCommand("addone");
		addOneButton.setContentAreaFilled(false);
		
		// add ten to count 
		addTenButton = new JButton("+10");
		addTenButton.setToolTipText("Click here to increase your counter by ten.");
		addTenButton.addActionListener(this);
		addTenButton.setActionCommand("addten");
		addTenButton.setContentAreaFilled(false);
		
		// add fifteen to count
		addFifteenButton = new JButton("+15");
		addFifteenButton.setToolTipText("Click here to increase your counter by fifteen.");
		addFifteenButton.addActionListener(this);
		addFifteenButton.setActionCommand("addfifteen");
		addFifteenButton.setContentAreaFilled(false);
		
		// submit button
		submitButton = new JButton("Submit");
		submitButton.setToolTipText("Click here to submit the amount that you typed.");
		submitButton.addActionListener(this);
		submitButton.setActionCommand("submit");
		submitButton.setContentAreaFilled(false);
		
		// new goal button
		newGoalButton = new JButton("New goal");
		newGoalButton.setToolTipText("Click here to set a new goal.");
		newGoalButton.addActionListener(this);
		newGoalButton.setActionCommand("new goal");
		newGoalButton.setContentAreaFilled(false);
		
		// summary
		summaryButton = new JButton("Summary");
		summaryButton.setToolTipText("Shows a summary of the year.");
		summaryButton.addActionListener(this);
		summaryButton.setActionCommand("summary");
		summaryButton.setContentAreaFilled(false);
		
		// recommended average label
		recommendedAverageLabel = new JLabel();
		recommendedAverageLabel.setToolTipText("Shows the recommended daily amount of push ups to reach goal.");
		
		// total count label
		totalCountLabel = new JLabel();
		totalCountLabel.setToolTipText("Shows the total amount of push ups.");
		
		// day label
		dayLabel = new JLabel();
		dayLabel.setToolTipText("Shows how many days since the beginning of the year.");
		
		// daily count label
		dailyCountLabel = new JLabel();
		dailyCountLabel.setToolTipText("Shows the daily amount of push ups.");
		
		// missed days label
		missedDaysLabel = new JLabel();
		missedDaysLabel.setToolTipText("Shows number of missed days.");
		
		// quota label
		quotaLabel = new JLabel();
		quotaLabel.setToolTipText("Shows the quota of total count until goal.");
		
		// high score label
		highScoreLabel = new JLabel();
		highScoreLabel.setToolTipText("Shows your daily high score.");
		
		// year label
		yearQuotaLabel = new JLabel();
		yearQuotaLabel.setToolTipText("Year quota.");
		
		
		// manual entry field
		manualEntryField = new JTextField();
		manualEntryField.setToolTipText("Update your daily counter by the amount that you typed here.");
		manualEntryField.addActionListener(this);
		manualEntryField.setActionCommand("submit");
		manualEntryField.addFocusListener(this);
		
		// Layout
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		add(dayLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		add(dailyCountLabel, gc);
		
		gc.gridx = 2;
		gc.gridy = 0;
		add(highScoreLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		add(missedDaysLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		add(totalCountLabel, gc);
		
		gc.gridx = 2;
		gc.gridy = 1;
		add(quotaLabel, gc);
		
		gc.gridx = 3;
		gc.gridy = 1;
		add(yearQuotaLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		add(recommendedAverageLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		add(addOneButton, gc);
		
		gc.gridx = 2;
		gc.gridy = 2;
		add(addTenButton, gc);
		addTenButton.requestFocus();
		
		gc.gridx = 3;
		gc.gridy = 2;
		add(addFifteenButton, gc);
		
		gc.gridx = 0;
		gc.gridy = 3;
		add(manualEntryField, gc);
		
		gc.gridx = 1;
		gc.gridy = 3;
		add(submitButton, gc);
		
		gc.gridx = 2;
		gc.gridy = 3;
		add(summaryButton, gc);
		
		gc.gridx = 3;
		gc.gridy = 3;
		add(newGoalButton, gc);
		
		
		updateGUI();
	}
	
	public void updateGUI(){
		setTitle("Member: " + member + ". Goal: " + member.getGoal() + " ");
		totalCountLabel.setText("Total count: " + member.getTotalCount());
		dailyCountLabel.setText("Daily count: " + member.getDailyCount());
		recommendedAverageLabel.setText("Recommended daily average: " + member.getRecommendedAverage());
		dayLabel.setText("Day: " + (member.getToday() + 1));
		missedDaysLabel.setText("Missed days: " + member.getMissedDays());
		manualEntryField.setText("Enter daily count:");
		quotaLabel.setText("Quota: " + member.getQuota() + "%");
		yearQuotaLabel.setText("Year quota: " + member.getYearQuota() + "%");
		highScoreLabel.setText("Daily high score: " + member.getHighScore());
	}
	
	public void newGoal(){
		// frame
		newGoalFrame = new JFrame("New goal");
		newGoalFrame.setSize(300, 100);
		newGoalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		newGoalFrame.setLocation((dim.width - newGoalFrame.getWidth())/2, (dim.height - newGoalFrame.getHeight())/2);
		newGoalFrame.setLayout(new GridBagLayout());
		newGoalFrame.setVisible(true);
		
		// label
		newGoalLabel = new JLabel("Type your new goal:");
		
		// text field
		newGoalField = new JTextField();
		newGoalField.addActionListener(this);
		newGoalField.setActionCommand("new goal submit");
		
		// submit button
		newGoalSubmitButton = new JButton("Submit");
		newGoalSubmitButton.addActionListener(this);
		newGoalSubmitButton.setActionCommand("new goal submit");
		newGoalSubmitButton.setContentAreaFilled(false);
		
		// layout
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		newGoalFrame.add(newGoalLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		newGoalFrame.add(newGoalField, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		newGoalFrame.add(newGoalSubmitButton, gc);
	}
	
	public void actionPerformed(ActionEvent event){
		switch(event.getActionCommand()){
			case "addone":{
				member.increaseCount(1);
				break;
			}
			
			case "addten":{
				member.increaseCount(10);
				break;
			}
			
			case "addfifteen":{
				member.increaseCount(15);
				break;
			}
			
			case "submit":{
				try{
					member.modifyCount(Integer.parseInt(manualEntryField.getText()));
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Use numbers only.");
				}
				manualEntryField.setText("");
				break;
			}
			
			case "summary":{
				new SummaryFrame(member);
				break;
			}
			
			case "new goal":{
				newGoal();
				break;
			}
			
			case "new goal submit":{
				try{
					member.setGoal(Integer.parseInt(newGoalField.getText()));
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Use numbers only.");
				}
				newGoalFrame.dispose();
				break;
			}
			
			
			default:{
				JOptionPane.showMessageDialog(null, "error 0x04: Unhandled case. Please contact admin.");
			}
		}
		updateGUI();
		
		try{
			IOStream.exportMember(member);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "error 0x05: Unknown error while exporting. Please contact admin.");
		}
	}
	
	public void focusGained(FocusEvent event){
		manualEntryField.setText("");
	}
	
	public void focusLost(FocusEvent event){}
}

