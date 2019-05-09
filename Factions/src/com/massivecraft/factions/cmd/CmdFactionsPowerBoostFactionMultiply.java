package com.massivecraft.factions.cmd;

public class CmdFactionsPowerBoostFactionMultiply extends CmdFactionsPowerBoostFactionAbstract
{
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public double calcNewPowerboost(double current, double d)
	{
		return current * d;
	}

}
