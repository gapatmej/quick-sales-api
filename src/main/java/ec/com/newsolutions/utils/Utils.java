package ec.com.newsolutions.utils;

import ec.com.newsolutions.config.Constants;
import ec.com.newsolutions.domain.ElectronicDocument;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static void generateAccessKey(ElectronicDocument electronicDocument){
        /*StringBuilder accessKey = new StringBuilder();
        accessKey.append(Constants.accessKeyFormatDate.format(electronicDocument.getDateIssue()));
        accessKey.append(electronicDocument.getReceiptType());
        accessKey.append(electronicDocument.getOrganization().getIdentification());
        accessKey.append(electronicDocument.getOrganization().getSriEnvironment());
        accessKey.append(electronicDocument.getEstablishmentCode());
        accessKey.append(electronicDocument.getEmissionPointCode());
        accessKey.append(electronicDocument.getSequence());
        accessKey.append(Constants.numericCode);
        accessKey.append(electronicDocument.getElectronicDocumentInfo().getEmissionType());
        accessKey.append(generarDigitoVerificador(accessKey.toString()));


        electronicDocument.getElectronicDocumentInfo().setAccessKey(accessKey.toString());*/

    }

    private static int generarDigitoVerificador(String accessKey) {

        int baseMultiplier = 7;
        int[] aux = new int[accessKey.length()];
        int multiplier = 2;
        int total = 0;
        int checker = 0;
        for (int i = aux.length - 1; i >= 0; --i) {
            aux[i] = Integer.parseInt("" + accessKey.charAt(i));
            aux[i] *= multiplier;
            ++multiplier;
            if (multiplier > baseMultiplier) {
                multiplier = 2;
            }
            total += aux[i];
        }

        if ((total == 0) || (total == 1))
            checker = 0;
        else {
            checker = (11 - (total % 11) == 11) ? 0 : 11 - (total % 11);
        }

        if (checker == 10) {
            checker = 1;
        }

        return checker;
    }

    public static String escapeCharacters(String string, char... chars){
        for (char c: chars) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\\");
            stringBuilder.append(c);
            string = string.replaceAll(stringBuilder.toString(),"\\\\"+c);
        }
        return string;
    }

    public static List stringToList(String string, String separator){
        String [] stringValues = string.split(separator);
        List<Object> values = new ArrayList<>();
        for (String value: stringValues) {
            values.add(value);
        }
        return values;
    }


}
