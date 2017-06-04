﻿package it.polimi.ingsw.GC_24.view;


import java.util.HashMap;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;


public abstract class ViewPlayer extends MyObservable implements MyObserver,Runnable {


		protected String name;
		protected String colour;
		protected HashMap<String, Object> hm;
		
		public String getName() {
			return name;
		}
		
		public String getColour() {
			return colour;
		}

		@Override
		public abstract void run();

		
		public abstract String setName();
		
		public abstract String setColour();
		
		public abstract String createMessage(String name, String Colour);


}
