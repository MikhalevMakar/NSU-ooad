package ru.nsu.ooad.communicationboost.abilitycore.util;

public final class UtilConsts {
    public static final class CommandConsts { // TODO: move to application.properties
        public static final String GET_COMMAND_INFO = "/get_command_info";
        public static final String SEND_MESSAGE_BY_EMAIL_URL = "/send_message_by_email";
        public static final String SEND_MESSAGE_BY_ROLE_URL = "/send_message_by_role";
        public static final String ACCEPT_PRODUCT_REQUEST_URL = "/accept_product_request";
        public static final String CANCEL_PRODUCT_REQUEST_URL = "/cancel_product_request";
        public static final String GET_PRODUCT_INFO_URL = "/get_product_info";
        public static final String SEND_PRODUCT_REQUEST_URL = "/send_product_request";
        public static final String VIEW_PRODUCT_REQUESTS_URL = "/view_product_requests";
        public static final String BLOCK_USER_URL = "/block_user";
        public static final String UNBLOCK_USER_URL = "/unblock_user";
        public static final String CHANGE_USER_ROLE_URL = "/change_role";
        public static final String START_URL = "/start";

        private CommandConsts() {
            throw new IllegalStateException(StringConsts.INSTANTIATION_MESSAGE);
        }
    }

    public static final class StringConsts {
        public static final String INSTANTIATION_MESSAGE = "Instantiation of utility class";

        private StringConsts() {
            throw new IllegalStateException(INSTANTIATION_MESSAGE);
        }
    }

    private UtilConsts() {
        throw new IllegalStateException(StringConsts.INSTANTIATION_MESSAGE);
    }
}
