package com.brunophilipe.littlebuddy.screen;

import java.util.ArrayList;

public class Menu {
	private ArrayList<MenuItem> items;
	private int selectedItem = 0, scroll=0;
	private static Menu instance = null;
	
	public static Menu getInstance() {
		return instance;
	}
	
	public static Menu buildAndGetInstance() {
		String menuItems[] = {"feed","play","read","sleep","clean","medicine","exit"};
		return instance = new Menu(menuItems);
	}
	
	private Menu(String inputs[]) {
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
		if (selectedItem>0) selectedItem--;
		else selectedItem = items.size()-1;
	}
	
	public boolean[][] getMenuScreen() {
		int i,x,y,m,n;
		i = x = y = m = n = 0;
		boolean screen[][] = new boolean[58][43],aux;
		
		while (selectedItem - scroll > 3) {
			scroll++;
		}
		
		while (selectedItem < scroll) scroll--;
		
		for (i=0+scroll;i<4+scroll;i++) {
			for (y=0;y<10;y++) {
				for (x=0;x<56;x++) {
					aux = items.get(i).getMap()[m][n];
					if (i == selectedItem) aux = !aux;
					screen[x+1][y+((i-scroll)*11)] = aux;
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
