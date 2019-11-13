package catserver.server;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CatServerConfig {
    private final File configFile;
    private YamlConfiguration config;

    public boolean hopperAsync = false;
    public boolean entityMoveAsync = true;
    public boolean chunkGenAsync = false;
    public boolean keepSpawnInMemory = true;
    public boolean enableSkipTick = true;
    public boolean disableUpdateGameProfile = true;
    public boolean modMob = false;
    public boolean entityAI = true;
    public long worldGenMaxTickTime = 15000000L;
    public int entityPoolNum = 3;
    public List<String> disableForgeGenWorld = new ArrayList<>();
    public static boolean fakePlayerEventPass = false;

    public List<String> fakePlayerPermissions = new ArrayList<>();

    public CatServerConfig(String file) {
        this.configFile = new File(file);
    }

    public void loadConfig() {
        if (configFile.exists()) {
            config = YamlConfiguration.loadConfiguration(configFile);
        } else {
            config = YamlConfiguration.loadConfiguration(new InputStreamReader(CatServer.class.getClassLoader().getResourceAsStream("configurations/catserver.yml")));
            try {
                configFile.createNewFile();
                config.save(configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        hopperAsync = getOrWriteBooleanConfig("async.hopper", hopperAsync);
        entityMoveAsync = getOrWriteBooleanConfig("async.entityMove", entityMoveAsync);
        chunkGenAsync = getOrWriteBooleanConfig("async.chunkGen", chunkGenAsync);
        keepSpawnInMemory = getOrWriteBooleanConfig("world.keepSpawnInMemory", keepSpawnInMemory);
        enableSkipTick = getOrWriteBooleanConfig("world.enableSkipTick", enableSkipTick);
        disableForgeGenWorld = getOrWriteStringListConfig("world.worldGen.disableForgeGenWorld", disableForgeGenWorld);
        disableUpdateGameProfile = getOrWriteBooleanConfig("disableUpdateGameProfile", disableUpdateGameProfile);
        worldGenMaxTickTime = getOrWriteStringLongConfig("maxTickTime.worldGen", 15) * 1000000;
        modMob = getOrWriteBooleanConfig("async.modMob", modMob);
        entityAI = getOrWriteBooleanConfig("async.entityAI", entityAI);
        entityPoolNum = getOrWriteIntConfig("async.asyncPoolNum", entityPoolNum);
        fakePlayerEventPass = getOrWriteBooleanConfig("fakePlayer.eventPass", fakePlayerEventPass);
        try {
            reloadFakePlayerPermissions();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean getOrWriteBooleanConfig(String path, boolean def) {
        if (config.contains(path)) {
            return config.getBoolean(path);
        }
        config.set(path, def);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return def;
    }

    private int getOrWriteIntConfig(String path, int def) {
        if (config.contains(path)) {
            return config.getInt(path);
        }
        config.set(path, def);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return def;
    }

    private List<String> getOrWriteStringListConfig(String path, List<String> def) {
        if (config.contains(path)) {
            return config.getStringList(path);
        }
        config.set(path, def);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return def;
    }

    private long getOrWriteStringLongConfig(String path, long def) {
        if (config.contains(path)) {
            return config.getLong(path);
        }
        config.set(path, def);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return def;
    }

    public void reloadFakePlayerPermissions() throws IOException {
        File permissFile = new File("fakePlayerPermission.txt");
        if (! permissFile.exists()) {
            permissFile.createNewFile();
            InputStreamReader inputStreamReader = new InputStreamReader(CatServer.class.getClassLoader().getResourceAsStream("configurations/fakePlayerPermission.txt"));
            List<String> lines = IOUtils.readLines(inputStreamReader);
            FileUtils.writeLines(permissFile, lines);
        }
        fakePlayerPermissions = FileUtils.readLines(permissFile, Charsets.UTF_8);
        System.out.println("FakePlayer Permissions:");
        fakePlayerPermissions.forEach(System.out::println);
    }
}
