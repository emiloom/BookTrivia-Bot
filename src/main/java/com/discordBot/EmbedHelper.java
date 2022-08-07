package com.discordBot;

import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;

public class EmbedHelper {
    private final String question;
    private final String category;

    public EmbedHelper(String question, String category) {
        this.question = question;
        this.category = category;
    }

    public EmbedBuilder createEmbed() {
        //create an embed
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Question")
                .setDescription(question)
                .addField("How to reply", "Type \"Trivia Answer: \" followed by true or false")
                .addField("Category", category)
                .setColor(Color.BLUE);
        return embed;
    }
}