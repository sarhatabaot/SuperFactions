package com.massivecraft.massivecore.item;

import org.bukkit.Material;

public class ConverterToMaterial extends Converter<String, Material>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static final ConverterToMaterial i = new ConverterToMaterial();
	public static ConverterToMaterial get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Material convert(String x)
	{
		if (x == null) return null;
		Material ret = Material.getMaterial(x);
		if (ret == null) throw new NullPointerException(x);
		return ret;
	}

}
