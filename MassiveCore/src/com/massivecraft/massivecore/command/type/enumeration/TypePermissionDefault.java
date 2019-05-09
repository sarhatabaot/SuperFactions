package com.massivecraft.massivecore.command.type.enumeration;

import org.bukkit.permissions.PermissionDefault;

public class TypePermissionDefault extends TypeEnum<PermissionDefault>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static TypePermissionDefault i = new TypePermissionDefault();
	public static TypePermissionDefault get() { return i; }
	public TypePermissionDefault()
	{
		super(PermissionDefault.class);
	}
	
}
