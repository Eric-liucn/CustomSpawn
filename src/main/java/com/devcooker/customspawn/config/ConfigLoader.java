package com.devcooker.customspawn.config;

import io.leangen.geantyref.TypeToken;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurationOptions;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.loader.ConfigurationLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigLoader {

    public static ConfigLoader instance;
    private final ConfigurationLoader<CommentedConfigurationNode> loader;
    private CommentedConfigurationNode node;
    private Config config;

    public ConfigLoader(final Path configDir) throws IOException{
        instance = this;
        if (!Files.exists(configDir)){
            Files.createDirectories(configDir);
        }
        Path configFile = configDir.resolve("setting.conf");
        loader = HoconConfigurationLoader.builder()
                .path(configFile)
                .defaultOptions(
                        ConfigurationOptions.defaults()
                                .serializers(collection -> {})
                                .shouldCopyDefaults(true)
                )
                .build();
        load();
        save();
    }

    public void load() throws IOException{
        node = loader.load();
        config = node.get(TypeToken.get(Config.class), new Config());
    }

    public void save() throws IOException {
        node.set(TypeToken.get(Config.class), this.config);
        loader.save(node);
    }

    public Config getConfig() {
        return config;
    }
}
