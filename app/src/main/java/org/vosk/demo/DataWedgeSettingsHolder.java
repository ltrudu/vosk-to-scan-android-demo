package org.vosk.demo;

import android.app.Activity;
import android.content.Context;

import com.zebra.datawedgeprofileenums.INT_E_DELIVERY;
import com.zebra.datawedgeprofileenums.MB_E_CONFIG_MODE;
import com.zebra.datawedgeprofileenums.SC_E_AIM_TYPE;
import com.zebra.datawedgeprofileenums.SC_E_SCANNER_IDENTIFIER;
import com.zebra.datawedgeprofileintents.DWProfileSetConfigSettings;
import com.zebra.datawedgeprofileintents.DWProfileSwitchBarcodeParamsSettings;

import java.util.HashMap;

public class DataWedgeSettingsHolder {

    protected static String mDemoProfileName = "org.vosk.demo";
    protected static String mDemoIntentAction = "org.vosk.demo.RECVR";
    protected static String mDemoIntentCategory = "android.intent.category.DEFAULT";
    protected static long mDemoTimeOutMS = 5000; //5s timeout...

    /**
     * This member will hold the profile settings that will be used to setup
     * the DataWedge configuration profile when doing a SetProfileConfig
     */
    protected static DWProfileSetConfigSettings mSetConfigSettings;

    public static void initSettings(final Context context)
    {
        mSetConfigSettings = new DWProfileSetConfigSettings()
        {{
            mProfileName = mDemoProfileName;
            mTimeOutMS = mDemoTimeOutMS;
            MainBundle.APP_LIST = new HashMap<>();
            MainBundle.APP_LIST.put(context.getPackageName(), null);
            MainBundle.CONFIG_MODE = MB_E_CONFIG_MODE.CREATE_IF_NOT_EXIST;
            IntentPlugin.intent_action = mDemoIntentAction;
            IntentPlugin.intent_category = mDemoIntentCategory;
            IntentPlugin.intent_output_enabled = true;
            IntentPlugin.intent_delivery = INT_E_DELIVERY.BROADCAST;
            KeystrokePlugin.keystroke_output_enabled = false;
            ScannerPlugin.scanner_selection_by_identifier = SC_E_SCANNER_IDENTIFIER.AUTO;
            ScannerPlugin.scanner_input_enabled = true;
            //ScannerPlugin.ScanParams.decode_audio_feedback_uri = "";
            //ScannerPlugin.Decoders.decoder_aztec = true;
            //ScannerPlugin.Decoders.decoder_ean8 = false;
            //ScannerPlugin.Decoders.decoder_ean13 = false;
            //ScannerPlugin.Decoders.decoder_code128 = true;
            //ScannerPlugin.Decoders.decoder_i2of5 = true;
            //ScannerPlugin.Decoders.decoder_datamatrix = true;
            //ScannerPlugin.Decoders.decoder_japanese_postal = true;
            //ScannerPlugin.DecodersParams.decoder_i2of5_check_digit = SC_E_I2OF5_CHECK_DIGIT.USS_CHECK_DIGIT;
            //ScannerPlugin.DecodersParams.decoder_i2of5_redundancy = false;
            //ScannerPlugin.UpcEan.upcean_supplemental_mode = SC_E_UPCEAN_SUPPLEMENTAL_MODE.SUPPLEMENTAL_378_379;
        }};
    }
}
