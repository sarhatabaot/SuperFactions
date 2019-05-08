package com.massivecraft.massivecore.command.type.primitive;

import com.massivecraft.massivecore.command.type.TypeAbstract;
import org.bukkit.command.CommandSender;

import java.util.Collection;
import java.util.Collections;

public class TypeString extends TypeAbstract<String>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static TypeString i = new TypeString();
	public static TypeString get() { return i; }
	public TypeString() { super(String.class); }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public String getName()
	{
		return "text";
	}
	
	@Override
	public String read(String arg, CommandSender sender)
	{
		return arg;
	}
	
	@Override
	public Collection<String> getTabList(CommandSender sender, String arg)
	{
		return Collections.emptySet();
	}

}
