package com.massivecraft.massivecore.store;

import com.massivecraft.massivecore.SenderPresence;
import com.massivecraft.massivecore.SenderType;
import com.massivecraft.massivecore.util.IdUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SenderIdSourceMixinAllSenderIds implements SenderIdSource
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static SenderIdSourceMixinAllSenderIds i = new SenderIdSourceMixinAllSenderIds();
	public static SenderIdSourceMixinAllSenderIds get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Collection<Collection<String>> getSenderIdCollections()
	{
		List<Collection<String>> ret = new ArrayList<>();
		ret.add(IdUtil.getIds(SenderPresence.ANY, SenderType.ANY));
		return ret;
	}
	
}
