package com.design_pattern.Factory;

public class MagicFactory extends AbstractFactory {

	public static AbstractFactory createFactory() {
		return new MagicFactory();
	}

	@Override
	Weapon createWeapon() {
		return new MagicStick();
	}

	@Override
	VehiCle createVehicle() {
		return new Broom();
	}

}
