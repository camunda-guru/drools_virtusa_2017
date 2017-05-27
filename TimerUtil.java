package com.nokia.utility;

import java.util.Calendar;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.api.runtime.conf.TimedRuleExectionOption;
import org.kie.api.runtime.help.QuartzHelper;

import com.nokia.entities.Alarm;
import com.nokia.entities.Message;

public class TimerUtil {

	public static void main(String[] args) {
		KieSessionConfiguration ksconf = KieServices.Factory.get().newKieSessionConfiguration();
		ksconf.setOption( TimedRuleExectionOption.YES );
		//ksconf.setOption(ClockTypeOption.get("realtime"));
		
		
		KieServices ks = KieServices.Factory.get();
	        KieContainer kcont =  ks.getKieClasspathContainer();
	        KieBase kbase = kcont.getKieBase("timer-rules");
		   KieSession ksession = kbase.newKieSession(ksconf, null);
		   Alarm alarm=new Alarm();
		   alarm.setOn(true);
		   Message message = new Message();
		   message.setMobileNumber(9952032862L);
		   alarm.setMessage(message);
		   ksession.insert(alarm);
		   ksession.fireAllRules();
		   
		  
	}

}
