package com.brunophilipe.littlebuddy.screen.menu;

import com.brunophilipe.littlebuddy.utility.MenuEntries;

public class MenuItem {
	private Boolean composition[][];
	
	public MenuItem(String entry) {
		String values[] = MenuEntries.getMenuEntry(entry);
		int x,y;
		char curLine[];
		composition = new Boolean[97][10];
		
		for (y=0;y<10;y++) {
			curLine = values[y].toCharArray();
			for (x=0;x<95;x++) {
				composition[x][y] = (curLine[x] == '8');
			}
		}
	}
	
	public Boolean[][] getMap() {
		return composition;
	}
}
