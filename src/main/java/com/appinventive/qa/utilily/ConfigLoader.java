package com.appinventive.qa.utilily;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConfigLoader {
    private JsonNode node;
    private static ConfigLoader _instance = null;

    public ConfigLoader() {
    }

    public static synchronized ConfigLoader getInstance() {
        if (_instance == null) {
            _instance = new ConfigLoader();
        }

        return _instance;
    }

//    public Map<String, Object> loadConfigFileYml(String config) throws FileNotFoundException {
//        String configFilePath = "src/main/resources/config/" + config + ".yml";
//        InputStream inputStream = new FileInputStream(configFilePath);
//        Yaml yaml = new Yaml();
//        Map<String, Object> data = (Map)yaml.load(inputStream);
//        System.out.println().debug(data);
//        return data;
//    }

    public String loadEnvFromJson() throws IOException {
        String jsonEnvFilePath = "src/main/resources/config/env.json";
        InputStream getLocalJsonFile = new FileInputStream(jsonEnvFilePath);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(getLocalJsonFile);
        return node.get("FactBaseUrl").toString().replaceAll("\"", "");
    }

    public String loadTeamFromJson() throws IOException {
        String jsonEnvFilePath = "src/main/resources/config/env.json";
        InputStream getLocalJsonFile = new FileInputStream(jsonEnvFilePath);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(getLocalJsonFile);
        return node.get("FactExecutionTeam").toString().replaceAll("\"", "");
    }

    public JsonNode loadConfigJsonFile(String jsonConfigFilePath) throws IOException {
        InputStream getLocalJsonFile = new FileInputStream(jsonConfigFilePath);
        ObjectMapper mapper = new ObjectMapper();
        this.node = mapper.readTree(getLocalJsonFile);
        return this.node;
    }

    public String getConfigValue(String key) {
        return this.node.at("/" + key.replace(".", "/")).asText();
    }

    public JsonNode getConfigValueNode(String key) {
        return this.node.at("/" + key.replace(".", "/"));
    }
}

