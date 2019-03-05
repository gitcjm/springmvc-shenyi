package com.test;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.ConfigurationNode;

import java.util.List;

public class MyReadConfig {
    public MyReadConfig(String fileName) {
        XMLConfiguration xmlConfiguration = new XMLConfiguration();
        try {
            xmlConfiguration.load(fileName);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public String getProp(XMLConfiguration xmlConfiguration) {
        String result = "";

        ConfigurationNode root = xmlConfiguration.getRootNode();
        List<ConfigurationNode> list = root.getChildren();
        for (ConfigurationNode b : list) {
            List<ConfigurationNode> listP = b.getChildren();
            for (ConfigurationNode p : listP) {
                String pName = p.getAttribute(1).getName();
                String pValue = p.getAttribute(1).getValue().toString();
                result = pName + "=" + pValue + ";" + result;
            }
        }

        return result;
    }
}
