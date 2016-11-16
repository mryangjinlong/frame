package com.design_pattern.Factory;

public class Main {

	public static void main(String[] args) {
		AbstractFactory factory = MagicFactory.createFactory();
		Weapon weapon = factory.createWeapon();
		VehiCle vehicle = factory.createVehicle();
		
		weapon.shoot();
		vehicle.run();
	}

}
