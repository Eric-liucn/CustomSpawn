package com.devcooker.customspawn.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.ArrayList;
import java.util.List;

@ConfigSerializable
public class Config {

    @Setting(value = "规则")
    public List<Rule> rules = new ArrayList<Rule>(){{
        add(new Rule("cnm", "123", "禁止使用词语&4cnm"));
    }};
}
