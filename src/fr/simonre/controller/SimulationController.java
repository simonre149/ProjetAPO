package fr.simonre.controller;

import fr.simonre.model.Configuration;
import fr.simonre.model.EpidemicModel;
import fr.simonre.model.Human;
import fr.simonre.model.SEIRModel;
import fr.simonre.model.SIRModel;

public class SimulationController 
{
	private static volatile SimulationController instance = null;
	
	Configuration config = Configuration.getInstance();
	
	EpidemicModel model;
	Human[] humans;
	
	//SINGLETON
	public final static SimulationController getInstance() 
	{
        if (SimulationController.instance == null) 
        {
           synchronized(ParametersController.class)
           {
             if (SimulationController.instance == null)
            	 SimulationController.instance = new SimulationController();
           }
        }
        return SimulationController.instance;
    }
	
	public void calculateModel()
	{
		String model_name = config.getParameter("modele_epidemique");
		int population_size = Integer.parseInt(config.getParameter("population_totale"));
		int nb_steps = Integer.parseInt(config.getParameter("nb_jours"));
		
		switch(model_name)
		{
		case "SIR":
			model = new SIRModel(population_size, nb_steps);
		case "SEIR":
			model = new SEIRModel(population_size, nb_steps);
		}
	}
}
