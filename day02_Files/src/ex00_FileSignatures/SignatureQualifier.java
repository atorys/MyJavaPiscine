package ex00_FileSignatures;

import java.util.HashMap;
import java.util.Set;

public class SignatureQualifier {

    private final HashMap<String, String>   BaseSignatureTypes;
    private final Set<String>               Keys;

    public SignatureQualifier(char[] reference) {
        BaseSignatureTypes = new HashMap<>();
        get(reference);
        Keys = BaseSignatureTypes.keySet();
    }

    public String verify(char[] type) {
        StringBuilder key = new StringBuilder();
        String hex_ch;
        String hex_key;
        for (char ch : type) {
                hex_ch = Integer.toHexString(ch);
                if (hex_ch.length() == 1)
                    key.append("0");
                key.append(hex_ch);
        }
        hex_key = key.toString().toUpperCase();
        for (String s: Keys) {
            if (hex_key.contains(s))
                return BaseSignatureTypes.get(s);
        }
        return null;
    }

    public void get(char[] reference) {
        if (reference == null)
            return ;
        String      line = new String(reference);
        String[]    lines = line.split("\n");
        String      key;
        String      value;

        for (int i = 0; i < lines.length; ++i) {
            value = lines[i].substring(0, lines[i].indexOf(","));
            key = lines[i].substring(lines[i].indexOf(",") + 1).replaceAll(" ", "");
            BaseSignatureTypes.put(key, value);
        }
    }

    public void display() {
        System.out.println(BaseSignatureTypes);
    }
}
