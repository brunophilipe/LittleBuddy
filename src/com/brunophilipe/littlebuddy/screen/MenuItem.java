package com.brunophilipe.littlebuddy.screen;

import com.brunophilipe.littlebuddy.utility.MenuEntries;

public class MenuItem {
	private Boolean composition[][];
	
	public MenuItem(String entry) {
		String values[] = MenuEntries.getMenuEntry(entry);
		int x,y;
		char curLine[];
		composition = new Boolean[56][10];
		
		for (y=0;y<10;y++) {
			curLine = values[y].toCharArray();
			for (x=0;x<56;x++) {
				composition[x][y] = (curLine[x] == '8');
			}
			values[y] = new String(curLine);
		}
	}
	
	public Boolean[][] getMap() {
		return composition;
	}
}
