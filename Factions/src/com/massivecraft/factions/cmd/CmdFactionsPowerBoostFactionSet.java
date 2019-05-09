package com.massivecraft.factions.cmd;

public class CmdFactionsPowerBoostFactionSet extends CmdFactionsPowerBoostFactionAbstract
{
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public double calcNewPowerboost(double current, double d)
	{
		return d;
	}

}
