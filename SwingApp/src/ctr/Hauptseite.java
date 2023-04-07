package ctr;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;






public class Hauptseite extends JFrame {

	private JPanel contentPane;
	private static NotenBerechnenK controll = new NotenBerechnenK();
	private static TextArea textArea = new TextArea();
	private static JButton buttonNotenBerechnen = new JButton("Berechnen");
	private static JButton buttonAbbrechen = new JButton("Abbrechen");
	private BerechnenButton berechnenButton = new BerechnenButton();
	private static JPanel panel = new JPanel();
	private AbbrechenButton abbrechenButton = new AbbrechenButton();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hauptseite frame = new Hauptseite();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

	public Hauptseite() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel.setBackground(Color.WHITE);
		panel.setBounds(5, 5, 424, 256);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		textArea.setBounds(22, 82, 210, 119);
		panel.add(textArea);
		
		buttonAbbrechen.setBounds(240, 224, 100, 22);
		buttonAbbrechen.addActionListener(abbrechenButton);
		
		buttonNotenBerechnen.setBounds(22, 224, 100, 22);
		buttonNotenBerechnen.addActionListener(berechnenButton);
		panel.add(buttonNotenBerechnen);
		panel.add(buttonAbbrechen);
		
		JLabel lblNewLabel = new JLabel("Noten Berechnen");
		lblNewLabel.setBounds(22, 41, 210, 22);
		panel.add(lblNewLabel);
	}
	
	 class BerechnenButton implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				double[] erg = controll.notenBerechnen(textArea.getText());
				if((erg[0] > 0 && erg[0] <=6) && erg[1] > 0) {
					JOptionPane.showMessageDialog(null,
                            "Die Durchschnittsnote ist: " + String.format("%1.1f", erg[0]) + "\r\n" + "Die Anzahl der Noten betr�gt: " + String.format("%1.0f", erg[1]),
                            "Durchschnittsnote",					      
                            JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null,
                            "String ist Fehlerhaft!!!!",
                            "Warnung",					      
                            JOptionPane.WARNING_MESSAGE);
				}
			}
	}
	 
	 class AbbrechenButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			panel.setVisible(false);
			System.exit(0);
		}
		 
		 
	 }
	 



}
