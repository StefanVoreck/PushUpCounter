import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

// Log in GUI object.
public class LoginFrame extends JFrame implements ActionListener{
	// Login frame fields
	private JLabel memberLabel;
	private JTextField memberField;
	private JButton loginButton;
	private JButton newMember;
	
	// New member fields
	private JFrame newMemberFrame;
	private JLabel newMemberLabel;
	private JTextField newMemberField;
	private JButton newMemberSubmitButton;

	public LoginFrame(){
		// frame
		setTitle("Log in");
		setSize(300, 100);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((dim.width - getWidth())/2, (dim.height - getHeight())/2);
		setLayout(new GridBagLayout());
		setVisible(true);

		// member label
		memberLabel = new JLabel("User:");

		// member text field
		memberField = new JTextField();
		memberField.addActionListener(this);
		memberField.setActionCommand("login");
		memberField.setToolTipText("Type your username here. Press \"Create new member\" to become a member.");

		// log in button
		loginButton = new JButton("Log in");
		loginButton.addActionListener(this);
		loginButton.setActionCommand("login");
		loginButton.setToolTipText("Click here to log in.");
		loginButton.setContentAreaFilled(false);

		// new member
		newMember = new JButton("Create new member");
		newMember.addActionListener(this);
		newMember.setActionCommand("newmember");
		newMember.setToolTipText("Click here to become a new member.");
		newMember.setContentAreaFilled(false);

		// layout
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.gridy = 0;
		add(memberLabel, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		add(memberField, gc);

		gc.gridx = 0;
		gc.gridy = 1;
		add(newMember, gc);	

		gc.gridx = 1;
		gc.gridy = 1;
		add(loginButton, gc);
	}
	
	public void newMember(){
		// frame
		newMemberFrame = new JFrame("New member");
		newMemberFrame.setSize(300, 100);
		newMemberFrame.setResizable(false);
		newMemberFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		newMemberFrame.setLocation((dim.width - newMemberFrame.getWidth())/2,(dim.height - newMemberFrame.getHeight())/2);
		newMemberFrame.setLayout(new GridBagLayout());
		newMemberFrame.setVisible(true);
		
		// label
		newMemberLabel = new JLabel("New member username:");
		
		// field
		newMemberField = new JTextField();
		newMemberField.addActionListener(this);
		newMemberField.setActionCommand("submit new member");
		newMemberField.setToolTipText("Write your wanted username here.");
		
		// button
		newMemberSubmitButton = new JButton("Submit");
		newMemberSubmitButton.addActionListener(this);
		newMemberSubmitButton.setActionCommand("submit new member");
		newMemberSubmitButton.setContentAreaFilled(false);
		
		// layout
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		newMemberFrame.add(newMemberLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		newMemberFrame.add(newMemberField, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		newMemberFrame.add(newMemberSubmitButton, gc);
		
	}

	public void actionPerformed(ActionEvent event){
		switch(event.getActionCommand()){
			case "login":{
				String temp = memberField.getText();
				try{
					Member member = IOStream.importMember(temp);
					dispose();
					new MainFrame(member);
				}catch(Exception e){
					if(temp.equals("")){
						JOptionPane.showMessageDialog(null, "Please type your username in the text field.");
					}else{
						JOptionPane.showMessageDialog(null, temp + " is not a member.");
					}
				}
				break;
			}
			
			case "newmember":{
				newMember();
				break;
			}
			
			case "submit new member":{
				String temp = newMemberField.getText();
				if(!temp.equals("")){
					try{
						Member newMember = new Member(temp, 30000);
						IOStream.exportMember(newMember);
						memberField.setText(temp);
						newMemberFrame.dispose();
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Error 0x03. Please contact admin.");;
					}
				}else{
					JOptionPane.showMessageDialog(null, "Please type your wanted username in the text field.");
				}
				break;
			}
			
			default:{
				System.out.printf("Error 0x01: Unhandled case. Please contact admin.\n");
			}
		}
	}
}

