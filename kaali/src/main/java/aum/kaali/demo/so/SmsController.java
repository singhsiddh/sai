package aum.kaali.demo.so;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
public class SmsController {
	static String TWILIO_ACCOUNT_SID_actual="AC574cf87a7ba7e8059aba3d50969eb628";
	
	static String TWILIO_AUTH_TOKEN_Actual="0c6fb694615337f2b5ad680e7431d42e";
	
//static String TWILIO_ACCOUNT_SID="AC48fb2e0cb8f4674eec80923e6acbdebe";
	
	//static String TWILIO_AUTH_TOKEN="0c923ca1265dd27c4f77bdffd97d8495";
	
	/*
	 * TEST ACCOUNT SID
AC48fb2e0cb8f4674eec80923e6acbdebe
Used to exercise the REST API
TEST AUTHTOKEN
 
0c923ca1265dd27c4f77bdffd97d8495

+15672922603

	 */
        @GetMapping(value = "/sendSMS")
        public ResponseEntity<String> sendSMS(String message) {

                //Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));
        	 Twilio.init(TWILIO_ACCOUNT_SID_actual, TWILIO_AUTH_TOKEN_Actual);

                Message.creator(new PhoneNumber("+353894496030"),//"<TO number - ie your cellphone>"),
                                new PhoneNumber("+15672922603")//"<FROM number - ie your Twilio number")
                                , "Hello from Twilio sai baba 📞"+message).create();

                return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
        }
}