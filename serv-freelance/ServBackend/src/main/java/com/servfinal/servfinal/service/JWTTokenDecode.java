package com.servfinal.servfinal.service;

import com.google.gson.Gson;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.nimbusds.jose.shaded.json.parser.ParseException;

import java.util.Base64;

public class JWTTokenDecode {

    public static String getEmailFromToken(String token){
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        JSONParser parser = new JSONParser();
        JSONObject jsonPayload;
        try{
            jsonPayload = (JSONObject) parser.parse(payload);

        }catch(ParseException e){
            throw new RuntimeException("Unable to parse TOKEN to json");
        }

        return jsonPayload.getAsString("sub");
    }
}
