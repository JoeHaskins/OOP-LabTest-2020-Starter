package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	
	ArrayList<Task> task = new ArrayList<Task>();
	float mouseXpress;
	float mouseYpress;
	boolean mousePosend = false;
	boolean mousePosstart = false;
	int countmouse = 0;

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
			float xstart = gradArray[t.getStart()-1];
			float xend = gradArray[(t.getEnd())-1];
			float xgap = (width -xborder) /31;

			if (countmouse == 1) {
				
				if (mousePosend != true) {
					if ( mouseXpress <= xend && mouseXpress >= xend -20 ) {
						mousePosend = true;
					} 
				}
				if (mousePosstart != true) {
					if (mouseXpress >= xstart && mouseXpress <= xstart +20  ) {
						mousePosstart = true;
					} 
				}
			}

			if (mousePosstart == true && mouseYpress > y && mouseYpress < y + gap ) {
				
				if (mouseX>= xborder && mouseX <= xstart - xgap) {
					t.setStart((t.getStart()-1));
				} else if (mouseX>= xborder && mouseX >= xstart + xgap && xstart + xgap != xend){
					t.setStart((t.getStart()+1));
				}

			} else if (mousePosend == true && mouseYpress > y && mouseYpress < y + gap ) {

				if (mouseX>= xborder && mouseX <= xend - xgap &&  xend -xgap != xstart) {
					t.setEnd((t.getEnd()-1));
				} else if (mouseX>= xborder && mouseX >= xend + xgap && mouseX < width-xgap){
					t.setEnd((t.getEnd()+1));
				}

			} 
				xstart = gradArray[t.getStart()-1];
				xend = gradArray[(t.getEnd())-1];
				rect(xstart, y, xend-xstart, gap, 5);
			

			fill(255);
			textAlign(LEFT, CENTER);
			text(t.getTask(), xborder/3, y + gap/2);
		}
		
	}
	
	public void mousePressed()
	{
		println("Mouse pressed");	
		mouseXpress = mouseX;
		mouseYpress = mouseY;
		mousePosend = false;
		mousePosstart = false;
		countmouse++;
		if (countmouse > 1) {
			countmouse = 0;
		}
		displayTasks();
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
