package com.massivecraft.factions.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.massivecraft.factions.TerritoryAccess;
import com.massivecraft.factions.entity.Board;
import com.massivecraft.massivecore.ps.PS;

import java.lang.reflect.Type;
import java.util.Map;

public class BoardAdapter implements JsonDeserializer<Board>, JsonSerializer<Board>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static BoardAdapter i = new BoardAdapter();
	public static BoardAdapter get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@SuppressWarnings("unchecked")
	@Override
	public Board deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{
		return new Board((Map<PS, TerritoryAccess>) context.deserialize(json, Board.MAP_TYPE));
	}

	@Override
	public JsonElement serialize(Board src, Type typeOfSrc, JsonSerializationContext context)
	{
		return context.serialize(src.getMap(), Board.MAP_TYPE);
	}
	
}
