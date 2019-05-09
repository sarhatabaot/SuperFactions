package com.massivecraft.factions.cmd;

public class CmdFactionsPowerBoostFactionAdd extends CmdFactionsPowerBoostFactionAbstract
{
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public double calcNewPowerboost(double current, double d)
	{
		return current + d;
	}

}
