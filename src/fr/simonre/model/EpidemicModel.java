package fr.simonre.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import fr.simonre.controller.ChartController;

public abstract class EpidemicModel 
{
	double alpha, beta, gamma; //temps d'incubation, pourcentage de transmission, temps de guérison
	int[] S, I, R; //nb de personnes dans chaque état à chaque temps
	
	float i_probability, r_probability; //en pourcentage
	
	int nb_steps, current_step_index = 1;
	
	Human[] population;
	
	HashMap<String, int[]> results;
	
	Configuration config = Configuration.getInstance();
	ChartController cc = ChartController.getInstance();
	
	
	EpidemicModel(int population_size, int nb_steps)
	{
		S = new int[nb_steps];
		I = new int[nb_steps];
		R = new int[nb_steps];
		
		population = new Human[population_size];
		
		I[0] = Integer.parseInt(config.getParameter("population_infectee"));
		S[0] = population_size - I[0];
		R[0] = 0;
		
		i_probability = Float.parseFloat(config.getParameter("probabilite_de_contamination")) * 100;
		r_probability = Float.parseFloat(config.getParameter("probabilite_de_decontamination")) * 100;
		
		if(Integer.parseInt(config.getParameter("masques")) == 1)
			i_probability /= 2;
		
		for (int i = 0; i < population.length; i++)
		{
			if (i < I[0])
				population[i] = new Human("I");
			else
				population[i] = new Human("S");
		}
		
		results = new HashMap<String, int[]>();
	}
	
	public void potentiallyUninfect()
	{
		for (Human h : population)
		{
			if (h.state == "I")
			{
				int random = ThreadLocalRandom.current().nextInt(0, 101);
				if (random <= r_probability)
					h.state = "R";
			}
		}
	}
	
	public abstract void checkInfectionOrExposition(); //pour le premier paramètre
	
	public abstract void potentiallyInfectOrExpose(Human h);
	
	public abstract void updateStatesCounters();
}
