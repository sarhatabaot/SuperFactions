package com.massivecraft.factions;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.factions.entity.MPerm.MPermable;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.util.RelationUtil;
import com.massivecraft.massivecore.collections.MassiveSet;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class TerritoryAccess
{
	// -------------------------------------------- //
	// FIELDS: RAW
	// -------------------------------------------- //
	
	// no default value, can't be null
	private final String hostFactionId;
	public String getHostFactionId() { return this.hostFactionId; }
	
	// default is true
	private final boolean hostFactionAllowed;
	public boolean isHostFactionAllowed() { return this.hostFactionAllowed; }

	// default is empty
	private final Set<String> grantedIds;
	public Set<String> getGrantedIds() { return this.grantedIds; }

	// -------------------------------------------- //
	// FIELDS: VERSION
	// -------------------------------------------- //

	public int version = 1;

	// -------------------------------------------- //
	// FIELDS: DELTA
	// -------------------------------------------- //
	
	// The simple ones
	public TerritoryAccess withHostFactionId(String hostFactionId) { return valueOf(hostFactionId, hostFactionAllowed, grantedIds); }
	public TerritoryAccess withHostFactionAllowed(Boolean hostFactionAllowed) { return valueOf(hostFactionId, hostFactionAllowed, grantedIds); }
	public TerritoryAccess withGrantedIds(Collection<String> factionIds) { return valueOf(hostFactionId, hostFactionAllowed, grantedIds); }
	
	// The intermediate ones
	public TerritoryAccess withGranted(MPermable mpermable, boolean with)
	{
		return withGrantedId(mpermable.getId(), with);
	}

	public TerritoryAccess withGrantedId(String grantedId, boolean with)
	{
		if (this.getHostFactionId().equals(grantedId))
		{
			return valueOf(hostFactionId, with, grantedIds);
		}
		
		Set<String> grantedIds = new MassiveSet<>(this.getGrantedIds());
		if (with)
		{
			grantedIds.add(grantedId);
		}
		else
		{
			grantedIds.remove(grantedId);
		}
		return valueOf(hostFactionId, hostFactionAllowed, grantedIds);
	}

	// -------------------------------------------- //
	// FIELDS: DIRECT
	// -------------------------------------------- //
	
	// This method intentionally returns null if the Faction no longer exists.
	// In Board we don't even return this TerritoryAccess if that is the case.
	public Faction getHostFaction()
	{
		return Faction.get(this.getHostFactionId());
	}
	
	public Set<MPermable> getGranteds()
	{
		return MPerm.idsToMPermables(this.getGrantedIds());
	}

	// -------------------------------------------- //
	// PRIVATE CONSTRUCTOR
	// -------------------------------------------- //

	// Strictly for GSON only
	private TerritoryAccess()
	{
		this.hostFactionId = null;
		this.hostFactionAllowed = true;
		this.grantedIds = null;
	}
	
	private TerritoryAccess(String hostFactionId, Boolean hostFactionAllowed, Collection<String> grantedIds)
	{
		if (hostFactionId == null) throw new NullPointerException("hostFactionId");
		if (grantedIds == null) throw new NullPointerException("grantedIds");
		this.hostFactionId = hostFactionId;
		
		Set<String> grantedIdsInner = new MassiveSet<>();
		grantedIdsInner.addAll(grantedIds);
		if (grantedIdsInner.remove(hostFactionId))
		{
			hostFactionAllowed = true;
		}
		this.grantedIds = Collections.unmodifiableSet(grantedIdsInner);
		
		this.hostFactionAllowed = (hostFactionAllowed == null || hostFactionAllowed);
	}
	
	// -------------------------------------------- //
	// FACTORY: VALUE OF
	// -------------------------------------------- //
	
	public static TerritoryAccess valueOf(String hostFactionId, Boolean hostFactionAllowed, Collection<String> grantedIds)
	{
		return new TerritoryAccess(hostFactionId, hostFactionAllowed, grantedIds);
	}
	
	public static TerritoryAccess valueOf(String hostFactionId)
	{
		return valueOf(hostFactionId, null, Collections.emptySet());
	}
	
	// -------------------------------------------- //
	// INSTANCE METHODS
	// -------------------------------------------- //

	public boolean isGranted(MPermable permable)
	{
		return isGranted(permable.getId());
	}

	public boolean isGranted(String permableId)
	{
		return this.getGrantedIds().contains(permableId);
	}
	
	// A "default" TerritoryAccess could be serialized as a simple string only.
	// The host faction is still allowed (default) and no faction or player has been granted explicit access (default).
	public boolean isDefault()
	{
		return this.isHostFactionAllowed() && this.getGrantedIds().isEmpty();
	}

	// -------------------------------------------- //
	// ACCESS STATUS
	// -------------------------------------------- //
	
	public AccessStatus getTerritoryAccess(MPlayer mplayer)
	{
		if (isGranted(mplayer.getId())) return AccessStatus.ELEVATED;
		if (isGranted(mplayer.getFaction().getId())) return AccessStatus.ELEVATED;
		if (isGranted(mplayer.getRank().getId())) return AccessStatus.ELEVATED;
		if (isGranted(RelationUtil.getRelationOfThatToMe(mplayer, this.getHostFaction()).toString())) return AccessStatus.ELEVATED;
		
		if (this.getHostFactionId().equals(mplayer.getFaction().getId()) && !this.isHostFactionAllowed()) return AccessStatus.DECREASED;
		
		return AccessStatus.STANDARD;
	}

}
