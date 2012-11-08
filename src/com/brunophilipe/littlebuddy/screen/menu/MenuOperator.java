package com.brunophilipe.littlebuddy.screen.menu;

public class MenuOperator {
	private static MenuOperator instance;
	private int state;
	
	private Menu menu_main;
	private Menu menu_exit;
	
	private static final int STATE_MAIN = 0;
	private static final int STATE_EXIT = 1;
	
	private MenuOperator() {
		String menuItems[] = {"feed","play","read","sleep","clean","medicine","exit"};
		menu_main = new Menu(menuItems);
	}
}
