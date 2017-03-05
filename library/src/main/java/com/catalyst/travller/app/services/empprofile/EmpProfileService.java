package com.catalyst.travller.app.services.empprofile;

import com.catalyst.travller.app.services.empprofile.model.EmployeeProfile;

import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by vhebbar on 12/5/16.
 */

public interface EmpProfileService {

    @GET
    Observable<EmployeeProfile> getEmployeeProfile();
}
