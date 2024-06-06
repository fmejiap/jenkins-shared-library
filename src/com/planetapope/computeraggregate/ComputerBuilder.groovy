package com.planetapope.computeraggregate
public class ComputerBuilder{
		private String model
		public ComputerBuilder setModel(model) {
			this.model = model;
			return this;
		}
		
		public Computer build(){
			return new Computer(this.model);
		}

	}
