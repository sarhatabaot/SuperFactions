package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.comparator.ComparatorFactionList;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.Parameter;
import com.massivecraft.massivecore.pager.Pager;
import com.massivecraft.massivecore.pager.Stringifier;
import com.massivecraft.massivecore.predicate.PredicateAnd;
import com.massivecraft.massivecore.predicate.PredicateVisibleTo;
import com.massivecraft.massivecore.store.SenderColl;
import com.massivecraft.massivecore.util.Txt;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.function.Predicate;

public class CmdFactionsList extends FactionsCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdFactionsList()
	{
		// Parameters
		this.addParameter(Parameter.getPage());
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		int page = this.readArg();
		final CommandSender sender = this.sender;
		final MPlayer msender = this.msender;
		
		// NOTE: The faction list is quite slow and mostly thread safe.
		// We run it asynchronously to spare the primary server thread.

		Predicate<MPlayer> predicateOnline = PredicateAnd.get(mp -> mp.getId() != null, SenderColl.PREDICATE_ONLINE, PredicateVisibleTo.get(sender));

		// Pager Create
		final Pager<Faction> pager = new Pager<>(this, "Faction List", page, (Stringifier<Faction>) (faction, index) -> {
			if (faction.isNone())
			{
				return Txt.parse("<i>Factionless<i> %d online", FactionColl.get().getNone().getMPlayersWhereOnlineTo(sender).size());
			}
			else
			{
				return Txt.parse("%s<i> %d/%d online, %d/%d/%d",
					faction.getName(msender),
					faction.getMPlayersWhere(predicateOnline).size(),
					faction.getMPlayers().size(),
					faction.getLandCount(),
					faction.getPowerRounded(),
					faction.getPowerMaxRounded()
				);
			}
		});
		
		Bukkit.getScheduler().runTaskAsynchronously(Factions.get(), () -> {
			// Pager Items
			final List<Faction> factions = FactionColl.get().getAll(ComparatorFactionList.get(sender));
			pager.setItems(factions);

			// Pager Message
			pager.message();
		});
	}
	
}
