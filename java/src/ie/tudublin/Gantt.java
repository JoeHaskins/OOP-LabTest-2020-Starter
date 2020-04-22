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
		int x = width/40;
		int y = height/25;
		int counter = 1;

		stroke(255);
		for (int i = 9; i < 39; i++) {
			line(i*x, y, i*x, height-y);
			textAlign(LEFT, CENTER);
			text(counter, i*x-(width/150), y/2);
			counter++;
		}
	}
}
