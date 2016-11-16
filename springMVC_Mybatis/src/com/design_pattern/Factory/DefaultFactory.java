package com.design_pattern.Factory;

public class DefaultFactory extends AbstractFactory{

	public static AbstractFactory createFactory() {
		return new DefaultFactory();
	}

	@Override
	Weapon createWeapon() {
		return new Gun();
	}
	
	@Override
	VehiCle createVehicle(){
		return new Car();
	}


	

}
