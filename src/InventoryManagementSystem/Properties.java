package InventoryManagementSystem;

public class Properties {
    // Esses valores são usados em todo o aplicativo e são consolidados aqui para
    // fácil modificação global e consistência.

    // O título do aplicativo.
    private static final String APPLICATION_NAME = "Inventory Management System";
    // A versão atual do aplicativo.
    private static final String APPLICATION_VERSION = "v1.0";
    // O nome do aplicativo e o número da versão listados juntos
    private static final String APPLICATION_NAME_AND_VERSION = APPLICATION_NAME
            .concat(" (".concat(APPLICATION_VERSION).concat(")"));
    // O texto do cabeçalho das caixas de diálogo de confirmação.
    private static final String DIALOG_HEADER = "Are you sure?";
    // A caixa de diálogo de confirmação do cancelamento.
    private static final String CANCEL_CONFIRMATION_PROMPT = "By clicking the OK button, you will return to the previous page and lose any unsaved changes.";
    // A caixa de diálogo de confirmação de exclusão.
    private static final String DELETE_CONFIRMATION_PROMPT = "By clicking the OK button, you will confirm the deletion. This action is permanent.";
    // A mensagem apresentada quando o usuário tenta enviar dados inválidos.
    private static final String INVALID_DATA_MESSAGE = "ERROR: Please correct your values, they contain invalid characters.";
    // A mensagem do rótulo do espaço reservado da tabela vazia
    private static final String TABLE_PLACEHOLDER_MESSAGE = "Use the Add button to populate this table";
    // A mensagem que diz ao usuário para selecionar um elemento.
    private static final String TELL_USER_TO_SELECT_ELEMENT_MESSAGE = "ERROR: Please select an element from the table to perform that action.";

    /**
     * @return o nome do aplicativo
     */
    public static String getAPPLICATION_NAME() {
        return APPLICATION_NAME;
    }

    /**
     * @return a versão do aplicativo
     */
    public static String getAPPLICATION_VERSION() {
        return APPLICATION_VERSION;
    }

    /**
     * @return o nome do aplicativo e a versão
     */
    public static String getAPPLICATION_NAME_AND_VERSION() {
        return APPLICATION_NAME_AND_VERSION;
    }

    /**
     * @return a confirmação de cancelamento
     */
    public static String getCANCEL_CONFIRMATION_PROMPT() {
        return CANCEL_CONFIRMATION_PROMPT;
    }

    /**
     * @return a confirmação de exclusão
     */
    public static String getDELETE_CONFIRMATION_PROMPT() {
        return DELETE_CONFIRMATION_PROMPT;
    }

    /**
     * @return o cabeçalho da caixa de diálogo
     */
    public static String getDIALOG_HEADER() {
        return DIALOG_HEADER;
    }

    /**
     * @return a mensagem de dados inválidos
     */
    public static String getINVALID_DATA_MESSAGE() {
        return INVALID_DATA_MESSAGE;
    }

    /**
     * @return a mensagem do rótulo do espaço reservado da tabela vazia
     */
    public static String getTABLE_PLACEHOLDER_MESSAGE() {
        return TABLE_PLACEHOLDER_MESSAGE;
    }

    /**
     * @return a mensagem que diz ao usuário para selecionar um elemento
     */
    public static String getTELL_USER_TO_SELECT_ELEMENT_MESSAGE() {
        return TELL_USER_TO_SELECT_ELEMENT_MESSAGE;
    }
}
