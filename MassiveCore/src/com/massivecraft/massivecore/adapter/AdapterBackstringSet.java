package com.massivecraft.massivecore.adapter;

import com.massivecraft.massivecore.collections.BackstringSet;
import com.massivecraft.massivecore.command.type.RegistryType;
import com.massivecraft.massivecore.xlib.gson.JsonDeserializationContext;
import com.massivecraft.massivecore.xlib.gson.JsonDeserializer;
import com.massivecraft.massivecore.xlib.gson.JsonElement;
import com.massivecraft.massivecore.xlib.gson.JsonParseException;
import com.massivecraft.massivecore.xlib.gson.JsonSerializationContext;
import com.massivecraft.massivecore.xlib.gson.JsonSerializer;
import com.massivecraft.massivecore.xlib.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

public class AdapterBackstringSet implements JsonDeserializer<BackstringSet<?>>, JsonSerializer<BackstringSet<?>>
{
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	public final static Type stringSetType = new TypeToken<Set<String>>(){}.getType(); 
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static final AdapterBackstringSet i = new AdapterBackstringSet();
	public static AdapterBackstringSet get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public JsonElement serialize(BackstringSet<?> src, Type type, JsonSerializationContext context)
	{
		return context.serialize(src.getStringSet(), stringSetType);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public BackstringSet<?> deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException
	{
		Set<String> stringSet = context.deserialize(json, stringSetType);
		ParameterizedType ptype = (ParameterizedType) type;
		Type[] args = ptype.getActualTypeArguments();
		Class<?> clazz = (Class<?>) args[0];
		return new BackstringSet(RegistryType.getType(clazz), stringSet);
	}

}
