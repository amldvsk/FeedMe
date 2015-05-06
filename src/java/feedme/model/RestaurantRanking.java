/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author NadavBismuth
 */
public class RestaurantRanking {
    
    private int rankId;
    private int restId;
    private Double rankValue ; 
    private String comment;
    protected JSONObject rankObject;
    
    
    
    
     public RestaurantRanking( int restId, Double rankValue, String comment) {
        
        this.restId = restId;
        this.rankValue = rankValue;
        this.comment = comment;
    }
     
     
    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    public int getRestId() {
        return restId;
    }

    public void setRestId(int restId) {
        this.restId = restId;
    }

    public Double getRankValue() {
        return rankValue;
    }

    public void setRankValue(Double rankValue) {
        this.rankValue = rankValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    public JSONObject toJson() throws JSONException {
        rankObject = new JSONObject();

        rankObject.put("rankId", getRankId());
        rankObject.put("restId", getRestId());
        rankObject.put("rankValue", getRankValue());
        rankObject.put("comment", getComment());
        return rankObject;
    }

   
    
    
}
