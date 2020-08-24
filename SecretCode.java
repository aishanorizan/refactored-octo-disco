import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class SecretCode {

	private String inputString;
	private int StringLength;
	private String outputString;
	
	private JFrame frmSecretCode;
	private JTextField inputtextField;
	private JTextField outputtextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SecretCode window = new SecretCode();
					window.frmSecretCode.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SecretCode() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSecretCode = new JFrame();
		frmSecretCode.setFont(new Font("Hopeless Heart DEMO", Font.PLAIN, 12));
		frmSecretCode.setResizable(false);
		frmSecretCode.setTitle("Secret Code");
		frmSecretCode.setBounds(100, 100, 450, 300);
		frmSecretCode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSecretCode.getContentPane().setLayout(null);
		
		JLabel lblEnterText = new JLabel("Enter Text:");
		lblEnterText.setBounds(10, 11, 95, 14);
		frmSecretCode.getContentPane().add(lblEnterText);
		
		inputtextField = new JTextField();
		inputtextField.setBounds(10, 27, 414, 20);
		frmSecretCode.getContentPane().add(inputtextField);
		inputtextField.setColumns(10);
		
		JButton btnEncode = new JButton("Encode");
		btnEncode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					String input = inputtextField.getText();
					boolean valid = checkStringValidity(input);
					
					if(valid == true)
					{
						shiftchar(input, input.length());
						String output = shiftchar(input, input.length());
						outputtextField.setText(output);
					}
					else if(valid == false)
					{
						JOptionPane.showMessageDialog(null, "Please Reentered!!!\n There is error in your sentences" );
					}
				}
		});
		btnEncode.setBounds(173, 58, 89, 23);
		frmSecretCode.getContentPane().add(btnEncode);
		
		JLabel lblOutput = new JLabel("Output:");
		lblOutput.setBounds(10, 90, 46, 14);
		frmSecretCode.getContentPane().add(lblOutput);
		
		outputtextField = new JTextField();
		outputtextField.setBounds(10, 115, 414, 20);
		frmSecretCode.getContentPane().add(outputtextField);
		outputtextField.setColumns(10);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				inputtextField.setText("");
				outputtextField.setText("" );
			
			}
		});
		btnClear.setBounds(173, 227, 89, 23);
		frmSecretCode.getContentPane().add(btnClear);
	}
	
	public SecretCode(String inputString) {		
	}
	
	public int getStringLength(String inputString) {
		StringLength = inputString.length();
		return StringLength;
	}
	
	public boolean checkStringValidity(String inputString) {
		
		boolean valid;
		Pattern s = Pattern.compile("^[A-Za-z\t\n\r\f]*$*");
		Matcher m = s.matcher(inputString);
		
		if(m.matches())
		{
			valid = true;
		}
		else
		{
			valid = false;
		}
		
		return valid;
	}
	
	public String shiftchar(String inputString, int StringLength) {
		
		char word;
		char shiftWord;
		int n = 0;
		
		for(int i = 0; i < StringLength; i++)
		{
			if(inputString.charAt(i) != ' ')
			{
				n++;
			}
		}
		
		for(int j = 0; j < StringLength; j++)
		{
			word = inputString.charAt(j);
			
			if(word == 'z')
			{
				word = (char)(word - 'z' + 'a' - 2);
			}
			else if(word == ' ')
			{
				word = (char)(word - ' ' + 'a' - 1);
			}
			
			shiftWord = (char)((word + n) % Integer.MAX_VALUE);
			outputString += shiftWord;
		}
		return outputString;
	}
}
