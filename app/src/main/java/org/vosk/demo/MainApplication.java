package org.vosk.demo;

import android.app.Application;
import android.util.Log;

import com.zebra.datawedgeprofileintents.DWProfileCommandBase;
import com.zebra.datawedgeprofileintents.DWProfileCreate;
import com.zebra.datawedgeprofileintents.DWProfileCreateSettings;
import com.zebra.datawedgeprofileintents.DWProfileDelete;
import com.zebra.datawedgeprofileintents.DWProfileDeleteSettings;
import com.zebra.datawedgeprofileintents.DWProfileSetConfig;
import com.zebra.datawedgeprofileintents.DataWedgeConstants;

public class MainApplication extends Application {

    private static String TAG = "vosk";
    @Override
    public void onCreate() {
        super.onCreate();
        DataWedgeSettingsHolder.initSettings(this);
        createDWProfile();
    }


    private void createDWProfile()
    {
        DWProfileDelete dwProfileDelete = new DWProfileDelete(this);
        DWProfileDeleteSettings dwProfileDeleteSettings = new DWProfileDeleteSettings()
        {{
            mProfileName = DataWedgeSettingsHolder.mDemoProfileName;
            mTimeOutMS = DataWedgeSettingsHolder.mDemoTimeOutMS;
        }};
        dwProfileDelete.execute(dwProfileDeleteSettings, new DWProfileCommandBase.onProfileCommandResult() {
            @Override
            public void result(String profileName, String action, String command, String result, String resultInfo, String commandidentifier) {
                DWProfileCreate profileCreate = new DWProfileCreate(MainApplication.this);

                DWProfileCreateSettings profileCreateSettings = new DWProfileCreateSettings()
                {{
                    mProfileName = DataWedgeSettingsHolder.mDemoProfileName;
                    mTimeOutMS = DataWedgeSettingsHolder.mDemoTimeOutMS;

                }};

                profileCreate.execute(profileCreateSettings, new DWProfileCommandBase.onProfileCommandResult() {
                    @Override
                    public void result(String profileName, String action, String command, String result, String resultInfo, String commandidentifier) {
                        if(result.equalsIgnoreCase(DataWedgeConstants.COMMAND_RESULT_SUCCESS))
                        {
                            Log.d(TAG,"Profile: " + profileName + " created with success.\nSetting config now.");
                            DWProfileSetConfig profileSetConfig = new DWProfileSetConfig(MainApplication.this);

                            profileSetConfig.execute(DataWedgeSettingsHolder.mSetConfigSettings, new DWProfileCommandBase.onProfileCommandResult() {
                                @Override
                                public void result(String profileName, String action, String command, String result, String resultInfo, String commandidentifier) {
                                    if(result.equalsIgnoreCase(DataWedgeConstants.COMMAND_RESULT_SUCCESS))
                                    {
                                        Log.d(TAG,"Set config on profile: " + profileName + " succeeded.");

                                    }
                                    else
                                    {
                                        Log.d(TAG,"Error setting params on profile: " + profileName + "\n" + resultInfo);
                                    }
                                }

                                @Override
                                public void timeout(String profileName) {
                                    Log.d(TAG,"Timeout while trying to set params on profile: " + profileName);
                                }
                            });
                        }
                        else
                        {
                            Log.d(TAG,"Error creating profile: " + profileName + "\n" + resultInfo);
                        }
                    }

                    @Override
                    public void timeout(String profileName) {
                        Log.d(TAG,"Timeout while trying to create profile: " + profileName);
                    }
                });
            }

            @Override
            public void timeout(String profileName) {
                Log.d(TAG,"Timeout while trying to delete profile: " + profileName);
            }
        });
    }

}
