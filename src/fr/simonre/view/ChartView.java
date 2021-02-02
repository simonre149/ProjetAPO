package fr.simonre.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

public class ChartView extends JFrame
{
	public ChartView(XYChart chart)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0, 1));
		getContentPane().add(panel, BorderLayout.CENTER);

	    // Add content to the window.
	    JPanel chartPanel = new XChartPanel(chart);
	    add(chartPanel);
	    
	    setTitle("Simulation");

	    pack();
	    setVisible(true);
	}
}