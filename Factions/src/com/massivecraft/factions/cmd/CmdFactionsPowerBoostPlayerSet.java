package com.massivecraft.factions.cmd;

public class CmdFactionsPowerBoostPlayerSet extends CmdFactionsPowerBoostPlayerAbstract
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
