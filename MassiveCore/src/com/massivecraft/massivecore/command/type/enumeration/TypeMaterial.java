package com.massivecraft.massivecore.command.type.enumeration;

import com.massivecraft.massivecore.util.Txt;
import org.bukkit.Material;

public class TypeMaterial extends TypeEnum<Material>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static TypeMaterial i = new TypeMaterial();
	public static TypeMaterial get() { return i; }
	public TypeMaterial()
	{
		super(Material.class);
		this.setHelp(
			Txt.parse("<aqua>https://hub.spigotmc.org/stash/projects/SPIGOT/repos/bukkit/browse/src/main/java/org/bukkit/Material.java")
		);
	}

}
