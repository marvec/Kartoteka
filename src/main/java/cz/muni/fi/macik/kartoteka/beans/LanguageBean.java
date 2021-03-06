package cz.muni.fi.macik.kartoteka.beans;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

/**
 * Bean class which controls localization and internationalization settings
 *
 * @author Marián Macik
 */
@Named
@SessionScoped
public class LanguageBean implements Serializable {

    private String localeCode = "en";

    private static Map<String, Object> countries;

    static {
        countries = new LinkedHashMap<>();
        countries.put("English", new Locale("en")); //label, value
        countries.put("Slovenčina", new Locale("sk"));
    }

    public Map<String, Object> getCountriesInMap() {
        return countries;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    //value change event listener
    public void countryLocaleCodeChanged(ValueChangeEvent e) {
        String newLocaleValue = e.getNewValue().toString();

        //loop country map to compare the locale code
        for (Map.Entry<String, Object> entry : countries.entrySet()) {
            if (entry.getValue().toString().equals(newLocaleValue)) {
                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
            }
        }
    }

}
