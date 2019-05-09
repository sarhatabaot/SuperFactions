package com.massivecraft.factions.cmd;

public class CmdFactionsPowerBoostFactionTake extends CmdFactionsPowerBoostFactionAbstract
{
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public double calcNewPowerboost(double current, double d)
	{
		return current - d;
	}

}
