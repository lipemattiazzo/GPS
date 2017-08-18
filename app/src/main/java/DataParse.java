/**
 * Created by Administrador on 17/08/2017.
 */

package locationproviders.atcandroid.com.locationproviders;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.google.android.gms.location.places.ui.zza.getPlace;

public class DataParse {
    public List<HashMap<String, String>> parse(String jsonData) {
        JSONArray jsonArray = null;
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject((String) jsonData);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getPlaces(jsonArray);
    }



    private List<HashMap<String, String>> getPlaces(JSONArray jsonArray) {
        int placesCount = jsonArray.length();
        List<HashMap<String,String>>placesList=new ArrayList<>();
        HashMap<String,String>placeMap=null;
        for (int i = 0, i<placesCount; i++){
        try{
            placeMap=getPlace((JSONObject)jsonArray.get(i));
            placesList.add(placeMap);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
    return placesList;
}
private HashMap<String,String>googlePlaceMap= new HashMap<String, String>();
String placeName= "-NA-";
String vicinity =