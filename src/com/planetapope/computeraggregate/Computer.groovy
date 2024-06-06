package com.planetapope.computeraggregate

public class Computer {

	String model
	def Computer(model){
		this.model=model
	}
	//Builder Class
	public static class ComputerBuilder{
		private String model
		public ComputerBuilder setModel(model) {
			this.model = model;
			return this;
		}
		
		public Computer build(){
			return new Computer(this.model);
		}

	}

}
