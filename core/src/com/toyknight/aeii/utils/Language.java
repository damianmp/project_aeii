package com.toyknight.aeii.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.PropertiesUtils;
import com.toyknight.aeii.AEIIException;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Majirefy 5/22/15.
 */
public class Language {

    private static ObjectMap<String, String> languageMap = new ObjectMap<String, String>();

    public static void init() throws AEIIException {
        try {
            FileHandle languageFile = FileProvider.getLanguageFile();
            InputStreamReader reader = new InputStreamReader(languageFile.read(), "UTF8");
            PropertiesUtils.load(languageMap, reader);
        } catch (IOException ex) {
            throw new AEIIException(ex.getClass() + ": " + ex.getMessage());
        }
    }

    public static String getFontFilename() {
        return languageMap.get("FONT", "en_US.ttf");
    }

    public static String getUnitName(int index) {
        return languageMap.get("UNIT_NAME_" + index);
    }

    public static String getText(String key) {
        return languageMap.get(key, "");
    }

    public static String createCharset() {
        ObjectMap.Values<String> values = languageMap.values();
        String charset = "";
        for (String text : values) {
            charset += text;
        }
        //String additional_charset = FileProvider.getAssetsFile("lang/charset.dat").readString("UTF8");
        return charset;
    }

    public static String removeDuplicate(String str) {
        char[] temp = str.toCharArray();
        int length = temp.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (temp[i] == temp[j]) {
                    int test = j;
                    for (int k = j + 1; k < length; k++) {
                        temp[test] = temp[k];
                        test++;
                    }
                    length--;
                    j--;
                }
            }
        }
        return String.copyValueOf(temp).substring(0, length);
    }

}
