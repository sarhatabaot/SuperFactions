package com.massivecraft.factions.cmd;

import com.massivecraft.factions.cmd.type.TypeMPlayer;

public abstract class CmdFactionsPowerBoostPlayerAbstract extends CmdFactionsPowerBoostAbstract
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //

	public CmdFactionsPowerBoostPlayerAbstract()
	{
		super(TypeMPlayer.get(), "player");
	}

}
