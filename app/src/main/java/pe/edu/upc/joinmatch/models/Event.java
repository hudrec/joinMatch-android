package pe.edu.upc.joinmatch.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 7/7/17.
 */

public class Event {
    private Integer id;
    private String name;
    private String sport;
    private Integer players;
    private String longitude;
    private String latitude;

    public Event(){

    }
    
    

    public String getName() {
        return name;
    }

    public Event setName(String name) {

        this.name = name;
        return this;
    }

    public Integer getPlayers() {
        return players;
    }

    public Event setPlayers(Integer players) {
        this.players = players;
        return this;
    }

    public String getSport() {
        return sport;
    }

    public Event setSport(String sport) {
        this.sport = sport;
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public Event setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public static Event build(JSONObject jsonEvent) {
        Event event = new Event();
        try {
            /*List<String> sortBysAvailable = new ArrayList<>();
            for(int i = 0; i < jsonEvent.length(); i++ ) {
                sortBysAvailable.add(jsonEvent.getJSONArray("sortBysAvailable").getString(i));
            }*/
            //String url = jsonEvent.getString("url");
            //Map<String, String> urlsToLogos = ClearbitLogoApi.getUrlsToLogosFor(url);
            event.setId(jsonEvent.getInt("id"))
                    .setName(jsonEvent.getString("name"))
                    .setPlayers(jsonEvent.getInt("players"))
                    .setSport(jsonEvent.getString("sport"))
                    .setLongitude(jsonEvent.getString("longitude"))
                    /*.setLanguage(jsonEvent.getString("language"))
                    .setCountry(jsonEvent.getString("country"))
                    .setSortBysAvailable(sortBysAvailable)
                    .setUrlsToLogos(urlsToLogos)*/;
            return event;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static List<Event> build(JSONArray jsonEvents) {
        List<Event> events = new ArrayList<>();
        for(int i = 0; i < jsonEvents.length(); i++)
            try {
                events.add(Event.build(jsonEvents.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return events;
    }

    public Integer getId() {
        return id;
    }

    public Event setId(Integer id) {
        this.id = id;
        return this;
    }
}
