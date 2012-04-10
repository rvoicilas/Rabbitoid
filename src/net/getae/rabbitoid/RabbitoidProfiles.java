package net.getae.rabbitoid;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class RabbitoidProfiles {
    private static final String PROFILE_NAME = "profile";
    private XmlPullParserFactory factory;
    private XmlPullParser parser;

    public RabbitoidProfiles(StringReader stringReader) throws XmlPullParserException {
	factory = XmlPullParserFactory.newInstance();
	factory.setNamespaceAware(true);

	parser = factory.newPullParser();
	parser.setInput(stringReader);
    }

    public ArrayList<String> getProfiles() throws IOException, XmlPullParserException {
	ArrayList<String> profiles = new ArrayList<String>();

	parser.nextTag();
	parser.require(XmlPullParser.START_TAG, null, "profiles");
	while (parser.nextTag() == XmlPullParser.START_TAG) {
	    parser.require(XmlPullParser.START_TAG, null, "profile");
	    profiles.add(parser.getAttributeValue(null, "name"));
	    parser.nextTag();
	}

	profiles = new ArrayList(new HashSet(profiles));
	return profiles;
    }
}
