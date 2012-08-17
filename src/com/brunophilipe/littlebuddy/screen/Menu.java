package com.brunophilipe.littlebuddy.screen;

import java.util.ArrayList;

public class Menu {
	private ArrayList<MenuItem> items;
	private int selectedItem = 0, skip=0;
	
	public Menu(String inputs[]) {
		items = new ArrayList<MenuItem>();
		MenuItem auxItem;
		
		for (String input : inputs) {
			auxItem = new MenuItem(input);
			items.add(auxItem);
		}
	}
	
	public void setSelectedMenuItem(int id) {
		selectedItem = id;
	}
	
	public void increaseSelectedMenuItem() {
		if (selectedItem<items.size()-1) selectedItem++;
		else selectedItem = 0;
	}
	
	public void decreaseSelectedMenuItem() {
		if (selectedItem>0)	{
			if (skip>0) skip--;
			else selectedItem--;
		}
		else selectedItem = items.size()-1;
	}
	
	public boolean[][] getMenuScreen() {
		int i,x,y,m,n,selectedItem=this.selectedItem,skip=this.skip;
		i = x = y = m = n = 0;
		boolean screen[][] = new boolean[58][43],aux;
		
		while (selectedItem >= 4) {
			selectedItem--;
			skip++;
		}
		
		for (i=0;i<4;i++) {
			for (y=0;y<10;y++) {
				for (x=0;x<56;x++) {
					aux = items.get(i+skip).getMap()[m][n];
					if (i == selectedItem) aux = !aux;
					screen[x+1][y+(i*11)] = aux;
					m++;
				}
				n++;
				m=0;
			}
			y++;
			n=0;
		}
		
		return screen;
	}
	
}
