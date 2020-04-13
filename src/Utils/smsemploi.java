/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author heshem
 */
public class smsemploi {


    public static final String ACCOUNT_SID = "ACe4aecdef23b28a594496c48af62bf462";
    public static final String AUTH_TOKEN = "79481f5b60ccdf7004237b6a9de6dc36";

    public void sendSms(String body) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message.creator(new PhoneNumber("+21658651997"),
        new PhoneNumber("+13307543589"), 
        body).create();
    }
}   

