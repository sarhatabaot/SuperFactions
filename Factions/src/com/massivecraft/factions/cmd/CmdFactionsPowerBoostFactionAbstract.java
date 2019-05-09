package com.massivecraft.factions.cmd;

import com.massivecraft.factions.cmd.type.TypeFaction;

public abstract class CmdFactionsPowerBoostFactionAbstract extends CmdFactionsPowerBoostAbstract
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //

	public CmdFactionsPowerBoostFactionAbstract()
	{
		super(TypeFaction.get(), "faction");
	}

}
