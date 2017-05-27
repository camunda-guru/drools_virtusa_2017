package com.nokia.utilities;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.io.ResourceType;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import com.nokia.entities.Fire;
import com.nokia.entities.Room;
import com.nokia.entities.Sprinkler;

public class TestStateful {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		kbuilder.add( ResourceFactory.newClassPathResource( "FireAlarm.drl", Fire.class),

		              ResourceType.DRL );

		if ( kbuilder.hasErrors() ) {

		    System.err.println( kbuilder.getErrors().toString() );

		}

		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

		kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		String[] RoomNames={"Kitchen","Living Room","Bed Room","AV Room"};
		Map<String,Room> map = new HashMap<String,Room>();
		Room room=null;
		Sprinkler sprinkler=null;
		for(String name : RoomNames)
		{
		   room = new Room();
		   room.setName(name);
		   map.put(name, room);
		   ksession.insert(room);
		   sprinkler=new Sprinkler();
		   sprinkler.setRoom(room);
		   ksession.insert(sprinkler);
		   
		}
		
		Fire kitchenFire=new Fire();
		kitchenFire.setRoom(map.get("Kitchen"));
		
		Fire livingFire=new Fire();
		livingFire.setRoom(map.get("Living Room"));
		
		FactHandle khandle= ksession.insert(kitchenFire);
		FactHandle lhandle=ksession.insert(livingFire);
		ksession.fireAllRules();
		ksession.retract( khandle);

		ksession.retract( lhandle );
		ksession.fireAllRules();
	}

}
