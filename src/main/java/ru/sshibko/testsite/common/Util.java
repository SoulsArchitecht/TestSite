package ru.sshibko.testsite.common;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.time.LocalDate;

public class Util {

/*    public static LocalDate parseDate(String val) throws ParseException {
        LocalDate res = null;
        if (!StringUtils.isEmpty(val)) {
            String country = LocaleContextHolder.getLocale().getLanguage();
            if (Const.LocaleNames.RU.equals(country))
                res = Const.DATE_FORMAT_RU.parse(val);
            else
                res = Const.DATE_FORMAT.parse(val);
        }
        return res;
    }*/

    public static String onlyDigits(String text) {
        String digits = text == null || text.isEmpty() || text.isBlank() ? null : text.replaceAll("\\D+", "");
        return digits == null || digits.isEmpty() || digits.isBlank() ? null : digits;
    }

    public static String normalizePhone(String phone) {
        return normalizePhone(phone, null);
    }

    public static String normalizePhone(String phone, String country) {
        String normalizedPhone = onlyDigits(phone);
        if (normalizedPhone != null) {
            if ("Белоруссия".equals(country)) {
                if (normalizedPhone.length() > 12)
                    normalizedPhone = normalizedPhone.substring(0, 13);
                if (normalizedPhone.length() == 9)
                    normalizedPhone = "375" + normalizedPhone;
                if (normalizedPhone.length() == 11 && normalizedPhone.startsWith("80"))
                    normalizedPhone = "375" + normalizedPhone.substring(2);
            } else {
                if (normalizedPhone.length() > 10)
                    normalizedPhone = normalizedPhone.substring(1, 11);
                if (normalizedPhone.length() == 10)
                    normalizedPhone = "7" + normalizedPhone;
                if (normalizedPhone.startsWith("8"))
                    normalizedPhone = "7" + normalizedPhone.substring(1);
            }
        }
        return normalizedPhone;
    }

/*    public static String formatPhone(String phone, String country) {
        return formatPhone(Const.PHONE_FORMAT_DASHED, phone, country);
    }*/

    public static String formatPhone(String format, String phone, String country) {
        String formatedPhone = normalizePhone(phone, country);
        if (formatedPhone != null) {
            if ("Белоруссия".equals(country) && formatedPhone.length() > 11) {
                formatedPhone = String.format(format,
                        formatedPhone.substring(0, 3).equals("375") ? "+" : "",
                        formatedPhone.substring(0, 3),
                        formatedPhone.substring(3, 5),
                        formatedPhone.substring(5, 8),
                        formatedPhone.substring(8, 10),
                        formatedPhone.substring(10)
                );
            } else if (formatedPhone.length() > 10) {
                formatedPhone = String.format(format,
                        formatedPhone.substring(0, 1).equals("7") ? "+" : "",
                        formatedPhone.substring(0, 1),
                        formatedPhone.substring(1, 4),
                        formatedPhone.substring(4, 7),
                        formatedPhone.substring(7, 9),
                        formatedPhone.substring(9)
                );
            }
            return formatedPhone;
        }
        return phone;
    }
}
