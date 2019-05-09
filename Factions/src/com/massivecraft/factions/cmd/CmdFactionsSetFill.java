package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Perm;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MConf;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.predicate.Predicate;
import com.massivecraft.massivecore.ps.PS;
import com.massivecraft.massivecore.util.ChunkUtil;

import java.util.Set;


public class CmdFactionsSetFill extends CmdFactionsSetXSimple
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdFactionsSetFill(boolean claim)
	{
		// Super
		super(claim);
		
		// Aliases
		this.addAliases("fill");

		// Format
		this.setFormatOne("<h>%s<i> %s <h>%d <i>chunk %s<i> using fill.");
		this.setFormatMany("<h>%s<i> %s <h>%d <i>chunks near %s<i> using fill.");
		
		// Requirements
		this.addRequirements(RequirementIsPlayer.get());
		Perm perm = claim ? Perm.CLAIM_FILL : Perm.UNCLAIM_FILL;
		this.addRequirements(RequirementHasPerm.get(perm));
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Set<PS> getChunks() throws MassiveException
	{
		// Common Startup
		final PS chunk = PS.valueOf(me.getLocation()).getChunk(true);
		
		// What faction (aka color) resides there?
		// NOTE: Wilderness/None is valid. 
		final Faction color = BoardColl.get().getFactionAt(chunk);
		
		// Calculate
		int max = MConf.get().setFillMax;
		Predicate<PS> matcher = ps -> BoardColl.get().getFactionAt(ps) == color;
		return ChunkUtil.getChunkArea(chunk, matcher, max);
	}

}
