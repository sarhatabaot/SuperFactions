package com.massivecraft.massivecore.item;

import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class ConverterFromInventoryContents extends Converter<ItemStack[], Map<Integer, DataItemStack>>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static final ConverterFromInventoryContents i = new ConverterFromInventoryContents();
	public static ConverterFromInventoryContents get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Map<Integer, DataItemStack> convert(ItemStack[] x)
	{
		if (x == null) return null;
		return DataItemStack.fromBukkitContents(x);
	}

}
