import fr.simonre.controller.ChartController;
import fr.simonre.controller.ParametersController;
import fr.simonre.controller.SimulationController;
import fr.simonre.model.Configuration;
import fr.simonre.model.Human;

public class Main 
{	
	static Configuration config;
	
	static ParametersController pc;
	static SimulationController sc;
	static ChartController cc;
	
	public static void main(String[] args) throws InterruptedException
	{	
		config = Configuration.getInstance();
		pc = ParametersController.getInstance();
		sc = SimulationController.getInstance();
		cc = ChartController.getInstance();
		
		config.generateFromConfigFile();
		
		pc.showParametersView(); //on affiche la première vue, celle des paramètres
	}
}