package com.discordBot;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.Boolean.parseBoolean;


public class Trivia {
    private String category;
    private String type;
    private String question;
    private String correctAns;

    public void makeReq(){
        try{
            URL url = new URL("https://opentdb.com/api.php?amount=1&category=10");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer bufferContent = new StringBuffer();

            while((inputLine = br.readLine()) != null){
                bufferContent.append(inputLine);
            }
            br.close();

            Gson gson = new Gson();
            JsonObject returnObj = gson.fromJson(String.valueOf(bufferContent), JsonObject.class);
            JsonArray nestedArr = returnObj.get("results").getAsJsonArray();

            for(Object triviaObj : nestedArr){
                JsonObject current = (JsonObject) triviaObj;
                this.category = current.get("category").getAsString();
                this.type = current.get("type").getAsString();
                this.question = current.get("question").getAsString().replaceAll("&#039;", "'");
               this.question = this.question.replaceAll("&quot;", "\"");
                this.correctAns = current.get("correct_answer").getAsString();
            }
            connection.disconnect();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public String getCategory() {
        return this.category;
    }

    public String getType(){return this.type;}

    public String getQuestion() {
        return this.question;
    }

    public String getCorrectAnswer() {
        return this.correctAns;
    }

    public String getIncorrectAnswer() {
        String lowerCase = this.correctAns.toLowerCase();
        boolean value = parseBoolean(lowerCase);
        return Boolean.toString(!value);
    }
}
