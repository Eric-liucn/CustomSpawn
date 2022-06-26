package com.devcooker.customspawn.config;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigLoader {

    public static ConfigLoader instance;
    private final ConfigurationLoader<CommentedConfigurationNode> loader;
    private CommentedConfigurationNode node;
    private Config config;

    public ConfigLoader(final Path configDir) throws IOException, ObjectMappingException {
        instance = this;
        if (!Files.exists(configDir)){
            Files.createDirectories(configDir);
        }
        Path configFile = configDir.resolve("setting.conf");
        loader = HoconConfigurationLoader.builder()
                .setPath(configFile)
                .setDefaultOptions(
                        ConfigurationOptions.defaults()
                                .withSerializers(collection -> collection.register(TypeToken.of(Rule.class), new Rule.RuleSerializer()))
                                .withShouldCopyDefaults(true)
                )
                .build();
        load();
        save();
    }

    public void load() throws IOException, ObjectMappingException {
        node = loader.load();
        config = node.getValue(TypeToken.of(Config.class), new Config());
    }

    public void save() throws ObjectMappingException, IOException {
        node.setValue(TypeToken.of(Config.class), this.config);
        loader.save(node);
    }

    public Config getConfig() {
        return config;
    }
}
