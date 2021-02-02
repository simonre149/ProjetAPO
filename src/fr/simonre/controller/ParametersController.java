package fr.simonre.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fr.simonre.model.Configuration;
import fr.simonre.view.ParametersView;

public final class ParametersController implements ActionListener
{
	private static volatile ParametersController instance = null;
	
	Configuration config = Configuration.getInstance();
	SimulationController sc = SimulationController.getInstance();
	
	ParametersView pv;
	
	JLabel lbl_PE, lbl_PCE;
	JTextField input_PE, input_PCE;
	
	public void showParametersView()
	{
		pv = new ParametersView();
		
		pv.cb_Model.addActionListener(this);
		pv.btn_validate.addActionListener(this);
		
		//input fields
		pv.input_N.setText(config.getParameter("population_totale"));
		pv.input_I.setText(config.getParameter("population_infectee"));
		pv.input_T.setText(config.getParameter("taille_de_la_zone"));
		pv.input_J.setText(config.getParameter("nb_jours"));
		pv.input_V.setText(config.getParameter("vitesse_des_personnes"));
		pv.input_PI.setText(config.getParameter("probabilite_de_contamination"));
		pv.input_PD.setText(config.getParameter("probabilite_de_decontamination"));
		
		pv.pack();
		pv.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == pv.cb_Model)
		{
			if (pv.cb_Model.getSelectedItem() == "SEIR")
			{
				pv.remove(pv.btn_validate);
				lbl_PE = new JLabel("Probabilité d'exposition :");
				pv.panel.add(lbl_PE);
				
				input_PE = new JTextField();
				input_PE.setColumns(10);
				pv.panel.add(input_PE);
				input_PE.setText(config.getParameter("probabilite_d_exposition"));
				
				lbl_PCE = new JLabel("Probabilité de contamination après exposition :");
				pv.panel.add(lbl_PCE);
				
				input_PCE = new JTextField();
				input_PCE.setColumns(10);
				pv.panel.add(input_PCE);
				input_PCE.setText(config.getParameter("probabilite_de_contamination_apres_exposition"));
				
				pv.panel.revalidate();
				pv.panel.repaint();
				pv.panel.updateUI();
			}
			else if (pv.cb_Model.getSelectedItem() == "SIR")
			{
				lbl_PE.setEnabled(false);
				input_PE.setEnabled(false);
				lbl_PCE.setEnabled(false);
				input_PE.setEnabled(false);
				
				pv.panel.revalidate();
				pv.panel.repaint();
				pv.panel.updateUI();
			}
		}
		
		if (e.getSource() == pv.btn_validate)
		{
			if (pv.cb_Model.getSelectedItem().toString() != config.getParameter("modele_epidemique"))
			{
				config.setParameter("modele_epidemique", pv.cb_Model.getSelectedItem().toString());
			}
			if (pv.input_N.getText() != config.getParameter("population_saine"))
			{
				config.setParameter("population_saine", pv.input_N.getText());
			}
			if (pv.input_I.getText() != config.getParameter("population_infectee"))
			{
				config.setParameter("population_infectee", pv.input_I.getText());
			}
			if (pv.input_T.getText() != config.getParameter("taille_de_la_zone"))
			{
				config.setParameter("taille_de_la_zone", pv.input_T.getText());
			}
			if (pv.input_J.getText() != config.getParameter("nb_jours"))
			{
				config.setParameter("nb_jours", pv.input_N.getText());
			}
			if (pv.input_V.getText() != config.getParameter("vitesse_des_personnes"))
			{
				config.setParameter("vitesse_des_personnes", pv.input_V.getText());
			}
			
			sc.calculateModel();
			pv.setVisible(false); //on cache la vue des paramètres
		}
	}
	
	//SINGLETON
	public final static ParametersController getInstance() 
	{
        if (ParametersController.instance == null) 
        {
           synchronized(ParametersController.class) 
           {
             if (ParametersController.instance == null)
            	 ParametersController.instance = new ParametersController();
           }
        }
        return ParametersController.instance;
    }
}
