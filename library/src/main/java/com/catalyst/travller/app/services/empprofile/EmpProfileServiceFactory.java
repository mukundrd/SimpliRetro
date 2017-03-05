package com.catalyst.travller.app.services.empprofile;

import android.content.Context;

import com.catalyst.travller.app.services.BaseFactory;
import com.catalyst.travller.app.utils.AppConstants;

import retrofit2.Retrofit;

/**
 * Created by vhebbar on 12/5/16.
 */

public class EmpProfileServiceFactory extends BaseFactory {

    private static EmpProfileService service;

    public static void init(Context context){
        Retrofit retrofit=getRetrofit(context, AppConstants.BASE_URL);
        service=retrofit.create(EmpProfileService.class);
    }

    public static EmpProfileService getService(){
        return service;
    }
}
