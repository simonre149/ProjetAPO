package fr.simonre.view;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ParametersView extends JFrame 
{
	public JPanel panel;
	public JComboBox cb_Model;
	public JTextField input_N, input_I, input_T, input_J, input_V, input_PI, input_PD;
	public JCheckBox chb_confinement, chb_masques, chb_quarantaine, chb_vaccination;
	public JButton btn_validate;
	
	public ParametersView()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0, 1));
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lbl_Model = new JLabel("Modèle épidémique :");
		panel.add(lbl_Model);
		
		String[] model_options = {"SIR", "SEIR"};
		cb_Model = new JComboBox(model_options);
		panel.add(cb_Model);
		
		JLabel lbl_N = new JLabel("Nb de personnes :");
		panel.add(lbl_N);
		
		input_N = new JTextField();
		panel.add(input_N);
		input_N.setColumns(10);
		
		JLabel lbl_I = new JLabel("Nb de personnes infectées :");
		panel.add(lbl_I);
		
		input_I = new JTextField();
		input_I.setColumns(10);
		panel.add(input_I);
		
		JLabel lbl_T = new JLabel("Taille de la zone de simulation :");
		panel.add(lbl_T);
		
		input_T = new JTextField();
		input_T.setColumns(10);
		panel.add(input_T);
		
		JLabel lbl_J = new JLabel("Nombre de jours :");
		panel.add(lbl_J);
		
		input_J = new JTextField();
		input_J.setColumns(10);
		panel.add(input_J);
		
		JLabel lbl_V = new JLabel("Vitesse des personnes :");
		panel.add(lbl_V);
		
		input_V = new JTextField();
		input_V.setColumns(10);
		panel.add(input_V);
		
		JLabel lbl_PI = new JLabel("Probabilité de contamination :");
		panel.add(lbl_PI);
		
		input_PI = new JTextField();
		input_PI.setColumns(10);
		panel.add(input_PI);
		
		JLabel lbl_PD = new JLabel("Probabilité de décontamination :");
		panel.add(lbl_PD);
		
		input_PD = new JTextField();
		input_PD.setColumns(10);
		panel.add(input_PD);
		
		JLabel lbl_confinement = new JLabel("Confinement :");
		panel.add(lbl_confinement);
		chb_confinement = new JCheckBox();
		panel.add(chb_confinement);
		
		JLabel lbl_masques = new JLabel("Masques :");
		panel.add(lbl_masques);
		chb_masques = new JCheckBox();
		panel.add(chb_masques);
		
		JLabel lbl_quarantaine = new JLabel("Quarantaine :");
		panel.add(lbl_quarantaine);
		chb_quarantaine = new JCheckBox();
		panel.add(chb_quarantaine);
		
		JLabel lbl_vaccination = new JLabel("Vaccination :");
		panel.add(lbl_vaccination);
		chb_vaccination = new JCheckBox();
		panel.add(chb_vaccination);
		
		btn_validate = new JButton("Valider les paramètres");
		panel.add(btn_validate);
		
		setTitle("Paramètres");
	}
}
