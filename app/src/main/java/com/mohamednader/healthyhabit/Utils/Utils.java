package com.mohamednader.healthyhabit.Utils;

import android.app.AlertDialog;
import android.content.Context;

public class Utils {

    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }

    public static String generateFlagUrl(String fullCountryName) {
        String alpha2Code;

        switch (fullCountryName.toLowerCase()) {
            case "american":
                alpha2Code = "US";
                break;
            case "british":
                alpha2Code = "GB";
                break;
            case "canadian":
                alpha2Code = "CA";
                break;
            case "chinese":
                alpha2Code = "CN";
                break;
            case "croatian":
                alpha2Code = "HR";
                break;
            case "dutch":
                alpha2Code = "NL";
                break;
            case "egyptian":
                alpha2Code = "EG";
                break;
            case "filipino":
                alpha2Code = "PH";
                break;
            case "french":
                alpha2Code = "FR";
                break;
            case "greek":
                alpha2Code = "GR";
                break;
            case "indian":
                alpha2Code = "IN";
                break;
            case "irish":
                alpha2Code = "IE";
                break;
            case "italian":
                alpha2Code = "IT";
                break;
            case "jamaican":
                alpha2Code = "JM";
                break;
            case "japanese":
                alpha2Code = "JP";
                break;
            case "kenyan":
                alpha2Code = "KE";
                break;
            case "malaysian":
                alpha2Code = "MY";
                break;
            case "mexican":
                alpha2Code = "MX";
                break;
            case "moroccan":
                alpha2Code = "MA";
                break;
            case "polish":
                alpha2Code = "PL";
                break;
            case "portuguese":
                alpha2Code = "PT";
                break;
            case "russian":
                alpha2Code = "RU";
                break;
            case "spanish":
                alpha2Code = "ES";
                break;
            case "thai":
                alpha2Code = "TH";
                break;
            case "tunisian":
                alpha2Code = "TN";
                break;
            case "turkish":
                alpha2Code = "TR";
                break;
            case "unknown":
                alpha2Code = "UN";
                break;
            case "vietnamese":
                alpha2Code = "VN";
                break;
            default:
                // If the full country name is not found, you can handle the error here
                alpha2Code = "";
                break;
        }

        String flagUrl = "https://flagsapi.com/{nameHolder}/flat/64.png";
        return flagUrl.replace("{nameHolder}", alpha2Code);
    }


}
