package com.massivecraft.massivecore.entity;

import com.massivecraft.massivecore.SoundEffect;
import com.massivecraft.massivecore.collections.MassiveMap;
import com.massivecraft.massivecore.command.editor.annotation.EditorName;
import com.massivecraft.massivecore.command.editor.annotation.EditorType;
import com.massivecraft.massivecore.command.type.TypeMillisDiff;
import com.massivecraft.massivecore.command.type.primitive.TypeBooleanOn;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.PermissionUtil;
import com.massivecraft.massivecore.util.TimeUnit;
import com.mongodb.WriteConcern;
import org.bukkit.permissions.Permissible;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@EditorName("config")
public class MassiveCoreMConf extends Entity<MassiveCoreMConf>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	protected static transient MassiveCoreMConf i;
	public static MassiveCoreMConf get() { return i; }
	
	// -------------------------------------------- //
	// VERSION
	// -------------------------------------------- //
	
	public int version = 1;
	
	// -------------------------------------------- //
	// ALIASES
	// -------------------------------------------- //
	// Base command aliases.
	
	public List<String> aliasesMcore = MUtil.list("massivecore", "mcore");
	public List<String> aliasesUsys = MUtil.list("usys");
	public List<String> aliasesMstore = MUtil.list("massivestore", "mstore");
	public List<String> aliasesBuffer = MUtil.list("buffer");
	public List<String> aliasesCmdurl = MUtil.list("cmdurl");
	
	// -------------------------------------------- //
	// GENERAL
	// -------------------------------------------- //
	// General configuration options.
	
	public String taskServerId = null;
	public boolean versionSynchronizationEnabled = true;
	public int tabCompletionLimit = 100;
	public boolean recipientChatEventEnabled = true;
	public boolean consoleColorsEnabled = true;
	
	// -------------------------------------------- //
	// PERMISSIONS FORMATS
	// -------------------------------------------- //
	// Permission denied formatting.
	
	public Map<String, String> permissionDeniedFormats = MUtil.map(
		"some.awesome.permission.node", "<b>You must be awesome to %s<b>.",
		"some.derp.permission.node.1", "derp",
		"some.derp.permission.node.2", "derp",
		"some.derp.permission.node.3", "derp",
		"derp", "<b>Only derp people can %s<b>.\n<i>Ask a moderator to become derp."
	);
	
	public String getPermissionDeniedFormat(String permissionName)
	{
		Map<String, String> map = this.permissionDeniedFormats;
		String ret = map.get(permissionName);
		if (ret == null) return null;
		ret = MUtil.recurseResolveMap(ret, map);
		return ret;
	}
	
	// -------------------------------------------- //
	// TP DELAY
	// -------------------------------------------- //
	// Teleportation delay permissions.
	
	public Map<String, Integer> permissionToTpdelay = MUtil.map(
		"massivecore.notpdelay", 0,
		"default", 10
	);
	
	public int getTpdelay(Permissible permissible)
	{
		Integer ret = PermissionUtil.pickFirstVal(permissible, permissionToTpdelay);
		if (ret == null) ret = 0;
		return ret;
	}
	
	// -------------------------------------------- //
	// DELETE FILES
	// -------------------------------------------- //
	// Delete certain files for system cleanliness.
	
	public List<String> deleteFiles = new ArrayList<>();
	
	// -------------------------------------------- //
	// VARIABLES
	// -------------------------------------------- //
	// Chat and command variables.
	
	public String variableBookName = "***book***";
	public boolean variableBookEnabled = true;
	
	public String variableBufferName = "***buffer***";
	public boolean variableBufferEnabled = true;
	
	// -------------------------------------------- //
	// CLICK
	// -------------------------------------------- //
	// Button click sound configuration.
	
	public SoundEffect clickSound = SoundEffect.valueOf("UI_BUTTON_CLICK", 0.75f, 1.0f);
	
	// -------------------------------------------- //
	// MSTORE
	// -------------------------------------------- //
	// The database system.
	
	@EditorType(TypeMillisDiff.class)
	public volatile long millisBetweenLocalPoll = TimeUnit.MILLIS_PER_MINUTE * 5;
	@EditorType(TypeMillisDiff.class)
	public volatile long millisBetweenRemotePollWithoutPusher = TimeUnit.MILLIS_PER_SECOND * 10;
	@EditorType(TypeMillisDiff.class)
	public volatile long millisBetweenRemotePollWithPusher = TimeUnit.MILLIS_PER_MINUTE * 1;
	
	@EditorType(TypeBooleanOn.class)
	public boolean warnOnLocalAlter = false;

	@EditorType(TypeBooleanOn.class)
	public boolean advancedLocalPollingDebug = false;
	
	// -------------------------------------------- //
	// CLEAN
	// -------------------------------------------- //
	
	// How often should the task run?
	// When set to 0 this feature is disabled. Meaning no cleaning will be done.
	// Default: 1 day (Per default once a day.)
	public long cleanTaskPeriodMillis = TimeUnit.MILLIS_PER_DAY;
	
	// This is used to decide at what time of the day the task will run.
	// For Example: If the taskPeriodMillis is 24 hours:
	// Set it to 0 for UTC midnight.
	// Set it to 3600000 for UTC midnight + 1 hour.
	public long cleanTaskOffsetMillis = 0;
	
	// When did the task last run?
	// This need not be modified by the server owner.
	// It will be set for you automatically.
	// 0 means it never ran before.
	public long cleanTaskLastMillis = 0;
	
	// -------------------------------------------- //
	// MONGODB
	// -------------------------------------------- //
	// The database system MongoDB driver.
	
	public boolean catchingMongoDbErrorsOnSave = true;
	public boolean catchingMongoDbErrorsOnDelete = true;
	
	public static WriteConcern getMongoDbWriteConcern(boolean catchingErrors) { return catchingErrors ? WriteConcern.ACKNOWLEDGED : WriteConcern.UNACKNOWLEDGED; }
	public WriteConcern getMongoDbWriteConcernSave() { return getMongoDbWriteConcern(this.catchingMongoDbErrorsOnSave); }
	public WriteConcern getMongoDbWriteConcernDelete() { return getMongoDbWriteConcern(this.catchingMongoDbErrorsOnDelete); }
	
	// -------------------------------------------- //
	// DEVELOPER
	// -------------------------------------------- //
	
	public boolean debugEnabled = false;
	
	// -------------------------------------------- //
	// METRICS
	// -------------------------------------------- //
	// URL connections to https://bstats.org
	
	public boolean metricsEnabled = true;

	// -------------------------------------------- //
	// LORE SORTING
	// -------------------------------------------- //

	public boolean loreSortOnInventoryClick = false;
	public boolean loreSortOnInventoryOpen = false;

	public Map<String, Integer> lorePrioritiesPrefix = new MassiveMap<>();
	public Map<String, Integer> lorePrioritiesRegex = new MassiveMap<>();

}
