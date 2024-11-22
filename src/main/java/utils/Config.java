package utils;

import enums.SystemProperties;

import java.util.Objects;
import java.util.Properties;

import static enums.SystemProperties.*;

public class Config {

    private static final String props = "config.properties";

    private static String env;
    private static String site;
    private static String lang;
    private static String country;
    private static String countryCode;
    private static String adminUrl;
    private static Users users;
    private static boolean agent = false;

    public static boolean isUstabor() {
        return getEnv().equals("ustabor");
    }

    public static boolean isFixListKg() {
        return getEnv().equals("fixinglist_kg");
    }

    public static boolean isFixinglist() {
        return getEnv().equals("fixinglist");
    }

    public static boolean isBildrlist() {
        return getEnv().equals("test");
    }

    public static boolean isNewTest() {
        return getEnv().equals("new_test");
    }

    public static String getEnv() {
        if (env == null) {
            env = Objects.requireNonNullElse(
                    getEnvironmentVariableValue(SITE),
                    "test"
            );
        }
        return env;
    }

    public static String getLang() {
        if (lang == null) {
            lang = Objects.requireNonNullElse(
                    getEnvironmentVariableValue(LANG),
                    "ru"
            );
        }
        return lang;
    }

    private static String getBaseUrl() {
        if (site == null) {
            site = Objects.requireNonNullElse(
                    getConfigPropertyFromEnvVariable(SITE),
                    "https://www.bildrlist.com"
            );
        }

        return site + "/";
    }

    public static String getFullUrl() {
        if (isBildrlist()) {
            return getBaseUrl() + getLang() + "/";
        }

        if (isUstabor()) {
            return getBaseUrl() + getLang() + "/";
        }

        return getBaseUrl() + getLang() + "-" + getCountryCode() + "/";
    }

    public static String getAdminUrl() {
        if (adminUrl == null) {
            adminUrl = getBaseUrl().replace("www", "ka8rms");
        }

        return adminUrl;
    }

    public static String getCountryCode() {
        if (countryCode == null) {
            if (isUstabor()) {
                countryCode = "uz";
            } else if (isBildrlist()) {
                countryCode = "uz";
            } else if (isFixListKg()) {
                countryCode = "kg";
            } else {
                countryCode = Objects.requireNonNullElse(
                        getEnvironmentVariableValue(COUNTRY),
                        "ru"
                );
            }
        }
        return countryCode;
    }

    public static String getCountry() {
        if (country == null) {
            country = XmlParser.getTextByKey(getCountryCode());
        }
        return country;
    }

    public static Users getUsers() {
        if (users == null) {
            users = new Users();
        }

        return users;
    }

    private static String getConfigPropertyFromEnvVariable(SystemProperties variable) {
        var varValue = getEnvironmentVariableValue(variable);
        return PropertyReader.getInstance().getProperty(variable + "." + varValue, props);
    }

    private static String getEnvironmentVariableValue(SystemProperties variable) {
        return System.getProperty(variable.toString(), null);
    }

    public static boolean isMobileTag() {
        Properties properties = System.getProperties();
        return properties.getProperty("tags") != null && properties.getProperty("tags").contains("mobile");
    }

    public static void setAgentNeeded(boolean value) {
        agent = value;
    }

    public static boolean getAgentNeeded() {
        return agent;
    }
}
