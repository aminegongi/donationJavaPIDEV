/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Hedi
 */

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
public class Sms {
        //public static final String ACCOUNT_SID = "ACe0f48e1b3019191da56c262dcc80355b";
    //public static final String AUTH_TOKEN = "b79583229b3dccd174ac3d42bfa7a10d";
    
    public static final String ACCOUNT_SID = "ACc4790f20cfd67d46fc568c862c915019";
    public static final String AUTH_TOKEN = "78f846512817db91ac56fe9e919ec283";

    public void sendSms(String body) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

       /* Message.creator(new PhoneNumber("+21695891297"),
        new PhoneNumber("+12036337665"), 
        body).create();*/
       
       Message.creator(new PhoneNumber("+21695891297"),
        new PhoneNumber("+15629122149"), 
        body).create();
    }
}


