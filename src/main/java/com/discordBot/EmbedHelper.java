package com.discordBot;

import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;

public class EmbedHelper {
    private String question;
    private final String category;
    private final String type;
    private final String [] choices;

    public EmbedHelper(String question, String category, String type, String [] choices) {
        this.question = question;
        this.category = category;
        this.type = type;
        this.choices = choices;
    }

    public EmbedBuilder createEmbed() {
        //Type answer
        String ansType = "";
        if(type.equals("boolean")){
            ansType = "true or false";
        }
        else{
            ansType = "the answer";
            question = question + "\n";
            for(int i = 0; i < choices.length; i++){
                question += choices[i] + " \n";
            }
        }

        //create an embed
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Question")
                .setDescription(question)
                .addField("How to reply", "Type \"Trivia Answer: \" followed by " + ansType)
                .addField("Category", category)
                .setColor(Color.BLUE);
        return embed;
    }
}