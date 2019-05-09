package com.massivecraft.factions.cmd;

public class CmdFactionsPowerBoostPlayerTake extends CmdFactionsPowerBoostPlayerAbstract
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
