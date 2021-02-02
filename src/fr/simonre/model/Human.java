package fr.simonre.model;

import java.util.concurrent.ThreadLocalRandom;

import fr.simonre.model.Vector2D;

public class Human 
{
	public String state; //état de la personne
	float size; //rayon de la personne
	int speed; //nb de déplacements dans un seul step
	int simulation_size;
	
	public Vector2D position; //position 2D
	
	Configuration config = Configuration.getInstance();
	
	public Human(String state)
	{
		this.simulation_size = Integer.parseInt(config.getParameter("taille_de_la_zone"));
		this.speed = Integer.parseInt(config.getParameter("vitesse_des_personnes"));
		this.size = 10 / simulation_size;
		this.state = state;
		//position initiale aléatoire
		Vector2D position = new Vector2D(ThreadLocalRandom.current().nextInt(0, simulation_size), ThreadLocalRandom.current().nextInt(0, simulation_size));
		this.position = position;
	}
	
	public void move()
	{
		int random_x = ThreadLocalRandom.current().nextInt(0, 2); //direction horizontale
		int random_y = ThreadLocalRandom.current().nextInt(0, 2); //direction verticale
		
		//horizontal
		if (random_x == 0) //gauche
		{
			position.x -= speed;
			if (position.x < 0) position.x = simulation_size;
		}
		else if (random_x == 1) //droite
		{
			position.x += speed;
			if (position.x > simulation_size) position.x = 0;
		}
		
		//vertical
		if (random_y == 0) //bas
		{
			position.y -= speed;
			if (position.y < 0) position.y = simulation_size;
		}
		else if (random_y == 1) //haut
		{
			position.y += speed;
			if (position.y > simulation_size) position.y = 0;
		}
	}
}