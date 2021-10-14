package by.gsu.epamlab.model.enums;

import by.gsu.epamlab.exceptions.ValidateRunTimeException;


public enum Filter {
    TODAY,
    TOMORROW,
    SOMEDAY,
    FIXED,
    BIN;

    public String getParamName() {
        return name().toLowerCase();
    }

    public static Filter getByStr(String strFilter) {
        try {
            return valueOf(strFilter.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ValidateRunTimeException("no valid filter " + strFilter);
        }
    }
}
