package fr.simonre.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JOptionPane;

public final class Configuration 
{
	private static volatile Configuration instance = null;
	
	Properties prop = new Properties();
	static String config_filename = "config.txt";
	InputStream file_input;
	OutputStream file_output;
	
	List<String> config_keys = new ArrayList<String>();
	public Map<String, String> config_dic = new HashMap<String, String>();
	
	
	private Configuration() 
	{
		config_keys = Arrays.asList
		(
			"modele_epidemique",
			"population_totale", 
			"population_infectee",
			"taille_de_la_zone",
			"nb_jours",
			"vitesse_des_personnes",
			"probabilite_de_contamination",
			"probabilite_de_decontamination",
			"probabilite_d_exposition",
			"probabilite_de_contamination_apres_exposition",
			"confinement",
			"masques",
			"quarantaine",
			"vaccination"
		);
	}
	
	public boolean generateFromConfigFile()
	{
		try
		{
			file_input = new FileInputStream(config_filename);
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Fichier '" + config_filename + "' introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		try
		{
			prop.load(file_input);
			
			for(String key : config_keys)
			{
				if (prop.getProperty(key) != null)
				{
					config_dic.put(key, prop.getProperty(key));
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Paramètre de configuration '" + key + "' non valide dans le fichier de configuration.", "Erreur", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			return true;
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "Fichier '" + config_filename + "' introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public String getParameter(String parameter_name)
	{
		return config_dic.get(parameter_name);
	}
	
	public void setParameter(String parameter_name, String parameter_value)
	{
		try
		{
			file_output = new FileOutputStream(config_filename);
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Fichier '" + config_filename + "' introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		
		try
		{
			prop.setProperty(parameter_name, parameter_value);
			prop.store(file_output, null);
			generateFromConfigFile();
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "Fichier '" + config_filename + "' introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	//SINGLETON
	public final static Configuration getInstance() 
	{
        if (Configuration.instance == null) 
        {
           synchronized(Configuration.class) 
           {
             if (Configuration.instance == null)
            	 Configuration.instance = new Configuration();
           }
        }
        return Configuration.instance;
    }
}
