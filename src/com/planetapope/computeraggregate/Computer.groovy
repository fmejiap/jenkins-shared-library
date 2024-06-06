package com.planetapope.computeraggregate

public class Computer {

	String model
	
	//Builder Class
	public static class ComputerBuilder{
		String model
		public ComputerBuilder setModel(model) {
			this.model = model;
			return this;
		}
		
		public Computer build(){
			return new Computer(this);
		}

	}

}
