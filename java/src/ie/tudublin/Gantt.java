package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	
	ArrayList<Task> task = new ArrayList<Task>();

	public void settings()
	{
		size(800, 600);
	}

	public void loadTasks()
	{
		Table table = loadTable("tasks.csv", "header");
		for(TableRow row:table.rows()) {
			Task t = new Task(row);
			task.add(t);
		}
	}

	public void printTasks()
	{
		for(Task t:task) {
			System.out.println(t);
		}
	}

	public void displayTasks() {
		//float y = map(i, 0, task.size(), border, height-border)
	}
	
	public void mousePressed()
	{
		println("Mouse pressed");	
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
	}

	
	
	public void setup() 
	{
		loadTasks();
		printTasks();
	}
	
	public void draw()
	{			
		background(0);
		int gradiant = 30;
		float xborder = width * 0.20f;
		float yborder = height * 0.05f;
		int counter = 1;

		stroke(255);
		for (int i = 0; i < gradiant; i++) {
			float x = map(i, 0, gradiant+1, xborder, width);
			line(x, yborder, x, height-yborder);
			textAlign(LEFT, CENTER);
			text(counter, x-4, yborder/2);
			counter++;
		}
	}
}
