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
public class SmsRestauOrg {
        public static final String ACCOUNT_SID = "ACad6dda39a3bbf0d88c3c65e3ce2f500d";
    public static final String AUTH_TOKEN = "e80c5cbc3f67a62e0356f67de8d5f4ce";

    public void sendSms(String body,String sendTo) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message.creator(new PhoneNumber(sendTo),new PhoneNumber("+17608405089"),body).create();
        
    }
}
