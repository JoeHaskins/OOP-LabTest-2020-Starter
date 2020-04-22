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
		int gradiant = 30;
		float xborder = width * 0.20f;
		float yborder = height * 0.05f;
		int counter = 1;
		float gradArray[] = new float[31];

		stroke(255);
		fill(255);
		for (int i = 0; i < gradiant; i++) {
			float x = map(i, 0, gradiant+1, xborder, width);
			line(x, yborder, x, height-yborder);
			textAlign(LEFT, CENTER);
			text(counter, x-4, yborder/2);
			counter++;
		}

		for (int i = 0; i < gradiant; i++) {
			float x = map(i, 0, gradiant+1, xborder, width);
			
			gradArray[i] = x;
		}

		colorMode(HSB);
		for (int j = 0; j < task.size(); j++) {
			float h = map(j, 0, task.size(), 0, 255);
			stroke(0);
			fill(h,255,255);
			Task t = task.get(j);
			float gap = ((height - yborder*2) / task.size()) * 0.85f;
			float y = map(j, 0, task.size(), yborder*2, height-yborder);
			rect(gradArray[t.getStart()-1], y, gradArray[(t.getEnd())-1]-gradArray[t.getStart()-1], gap, 5);

			fill(255);
			textAlign(LEFT, CENTER);
			text(t.getTask(), xborder/3, y + gap/2);
		}
		
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
		displayTasks();
	}
}
