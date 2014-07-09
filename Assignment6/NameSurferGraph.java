/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);
		// You fill in the rest //
	}
	
	
	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		// empty hashmap
		entries.clear();
		update();
	}
	
	
	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		entries.put(entry.getName(), entry);
		if (entries.get(entry.getName()).getColor() == null) {
			Color setNewColor = lineColors[(colorIter % 4)];
			entries.get(entry.getName()).setColor(setNewColor);
		} 
		colorIter++;
		update();
	}
	
	public void removeEntry(NameSurferEntry entry) {
		entries.remove(entry.getName());
		update();
	}
	
	/**
	 * Updates the display image by deleting all the graphical objects
	 * from the canvas and then reassembling the display according to
	 * the list of entries. Your application must call update after
	 * calling either clear or addEntry; update is also called whenever
	 * the size of the canvas changes.
	 */
	public void update() {
		removeAll();
		drawGraph();
		drawEntries();
	}
	
	private void drawEntries() {
		for (String key: entries.keySet()) {
			int lastRankY = -1;
			for (int i = START_DECADE; i < (START_DECADE + NDECADES*10); i+=10) {
				String labelName = entries.get(key).getName();

				int iter = ((i-START_DECADE)/10)+1;
				double spacing = (getWidth() / NDECADES);
				double rankSpacing = (getHeight() - 2*GRAPH_MARGIN_SIZE)/MAX_RANK;

				int nextRank = (entries.get(key).getRank(i+10));
				if (nextRank == 0) {
					nextRank = MAX_RANK;
				}
//				if (nextRank == null) break;
				int rank = (entries.get(key).getRank(i));
				if (rank == 0) {
					rank = MAX_RANK;
					labelName += " *";
				} else {
					labelName += " " + rank;
				}
				int rankY = rank*(getHeight() - 2*GRAPH_MARGIN_SIZE)/MAX_RANK;
				if (lastRankY > -1) {
					GLine ranking = new GLine((iter-2)*spacing, lastRankY+GRAPH_MARGIN_SIZE, (iter-1)*spacing, rankY+GRAPH_MARGIN_SIZE);
					ranking.setColor(entries.get(key).getColor());
					add(ranking);
				}
//				GLine ranking = new GLine((iter-1)*spacing, rank+GRAPH_MARGIN_SIZE, iter*spacing, nextRank+GRAPH_MARGIN_SIZE);
				GLabel nameRank = new GLabel(labelName, (iter-1)*spacing+3, rankY+GRAPH_MARGIN_SIZE-3);
				nameRank.setColor(entries.get(key).getColor());
				add(nameRank);
//				GLabel test = new GLabel(rankSpacing+"",50,50);
//				add(test);
				lastRankY = rankY;
			}
		}
	}
	
	private void drawGraph() {
		GLine topbar = new GLine(0,GRAPH_MARGIN_SIZE,getWidth(),GRAPH_MARGIN_SIZE);
		GLine bottombar = new GLine(0,getHeight() - GRAPH_MARGIN_SIZE,getWidth(),getHeight() - GRAPH_MARGIN_SIZE);
		for (int i = START_DECADE; i < (START_DECADE + NDECADES*10); i+=10) {
			double iter = ((i-START_DECADE)/10)+1;
			double spacing = (getWidth() / NDECADES);
			GLine crossbar = new GLine(iter*spacing, 0, iter*spacing, getHeight());
			add(crossbar);
			GLabel date = new GLabel((" " + i), (iter-1)*spacing, getHeight() - 4);
			add(date);
		}
		add(topbar);
		add(bottombar);
	}
	
	private int colorIter = 0;

	private Map<String, NameSurferEntry> entries = new HashMap<String, NameSurferEntry>();
	private Color[] lineColors = {Color.BLACK, Color.RED, Color.BLUE, Color.MAGENTA};
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
