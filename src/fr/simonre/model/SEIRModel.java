package fr.simonre.model;

import java.util.concurrent.ThreadLocalRandom;

public class SEIRModel extends EpidemicModel
{
	int[] E;
	float ec_probability = Float.parseFloat(config.getParameter("probabilite_de_contamination_apres_exposition"));
	
	public SEIRModel(int population_size, int nb_steps) 
	{
		super(population_size, nb_steps);
		
		E = new int[nb_steps];
		
		for (int i = 1; i < nb_steps; i++)
		{
			for (Human h : population)
				h.move();
			
			
			potentiallyUninfect();
			checkInfectionOrExposition();
			checkInfectionAfterExposition();
			updateStatesCounters();
			current_step_index++;
		}
		
		results.put("S", S);
		results.put("E", E);
		results.put("I", I);
		results.put("R", R);
		
		cc.showChartView("Résultats", results);
	}
	
	@Override
	public void checkInfectionOrExposition()
	{
		for (Human h1 : population)
		{
			for (Human h2 : population)
			{
				//si h1 n'est pas h2 et que h1 est contaminé
				if (h1 != h2 && h1.state == "I")
				{
					//si la différence de leur position <= 1
					if (Math.abs(h2.position.x - h1.position.x) <= 1 || Math.abs(h2.position.y - h1.position.y) <= 1)
						//et que h2 n'est ni I ni R
						if (h2.state != "I" && h2.state != "R" && h2.state != "E")
						{
							//alors on infecte potentiellement h2
							potentiallyInfectOrExpose(h2);
						}
				}
			}
		}
	}
	
	@Override
	public void potentiallyInfectOrExpose(Human h)
	{
		int random = ThreadLocalRandom.current().nextInt(0, 101);
		if (random <= i_probability)
				h.state = "E";
	}
	
	public void checkInfectionAfterExposition()
	{
		for (Human h : population)
		{
			if (h.state == "E")
			{
				int random = ThreadLocalRandom.current().nextInt(0, 101);
				if (random <= ec_probability)
					h.state = "I";
			}
		}
	}

	@Override
	public void updateStatesCounters() 
	{
		for (Human h : population)
		{
			if(h.state == "S") S[current_step_index]++;
			if(h.state == "E") E[current_step_index]++;
			if(h.state == "I") I[current_step_index]++;
			if(h.state == "R") R[current_step_index]++;
		}
	}
}
