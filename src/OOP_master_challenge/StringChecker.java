package OOP_master_challenge;

/**
 * @author Marika Grzebieniowska on 09.07.2018
 * @project internship
 */

/*Turned out to be unnecessary, but I leave it for future use.
 Maybe will move it to a util class. */

class StringChecker {

    public static boolean isEmptyOrWhiteSpace(String s) {
        if (s.isEmpty()) {
            return true;
        }

        for (int i = 0; i < s.length(); i++) {
            if (Character.isWhitespace(s.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    // code copied from apache commons lang3 isValidEnum()
    public static <E extends Enum<E>> boolean isValidEnum(Class<E> enumClass, String enumName) {
        if (enumName == null) {
            return false;
        } else {
            try {
                Enum.valueOf(enumClass, enumName);
                return true;
            } catch (IllegalArgumentException var3) {
                return false;
            }
        }
    }

}
