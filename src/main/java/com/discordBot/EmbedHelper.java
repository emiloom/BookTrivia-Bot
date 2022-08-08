package com.discordBot;

import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;

public class EmbedHelper {
    private final String question;
    private final String category;
    private final String type;

    public EmbedHelper(String question, String category, String type) {
        this.question = question;
        this.category = category;
        this.type = type;
    }

    public EmbedBuilder createEmbed() {
        //Type answer
        String ansType = "";
        if(type.equals("boolean")){
            ansType = "true or false";
        }
        else{
            ansType = "the answer";
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