package com.sud.life.estatement.utils;

public enum BposConfigConstants {

    USER_SESSION_BY_JWT("sud.eStatement.bposApi.UserSessionInfoByJWT"),
    MESSAGE_CODES("sud.eStatement.bposApi.MessageCode"),

    CONFIG_VALUE_BY_KEY("sud.eStatement.bposApi.ConfigValueByKey"),
    PROPOSAL_DEPOSITE_RECEIPT("sud.eStatement.proposal_deposite_recipt"),

    JBOSS_HOME_DEFAULT_LOCATION("F:/JBOSS_SERVER/jboss-eap-8.0");

    final String configKey;

    BposConfigConstants(String configKey) {
        this.configKey = configKey;
    }
    public String getValue() {
        return configKey;
    }
}
