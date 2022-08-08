package com.discordBot;
import io.github.cdimascio.dotenv.Dotenv;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class BookTrivia {
    private final Dotenv config;
    private static String token = null;

    public BookTrivia() {
        config = Dotenv.configure().ignoreIfMissing().load();
        token = config.get("TOKEN");
    }

    public static void main(String[] args) {
        Trivia trivia = new Trivia();
        BookTrivia create = new BookTrivia();
        //create a new instance of your discordbot's api
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

        //message listener for create channel request
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("bookworm")) {
                trivia.makeReq();

                EmbedHelper embed = new EmbedHelper(trivia.getQuestion(), trivia.getCategory(), trivia.getType());
                event.getChannel().sendMessage(embed.createEmbed());
                event.getMessage().addReaction("\uD83D\uDC4D");
            }
        });

        //check if the answer if correct and send a reply
        api.addMessageCreateListener(replyEvent -> {
            if (replyEvent.getMessageContent().equalsIgnoreCase("Trivia answer: " + trivia.getCorrectAnswer())) {
                replyEvent.getChannel().sendMessage("You are correct!");
            }
            else if (trivia.getType().equals("boolean") && replyEvent.getMessageContent().equalsIgnoreCase("Trivia answer: "+ trivia.getIncorrectAnswer())) {
                replyEvent.getChannel().sendMessage("Incorrect! The answer is " + trivia.getCorrectAnswer());
            }
            else if(replyEvent.getMessageContent().substring(0, 14).equalsIgnoreCase("Trivia answer:")){

                replyEvent.getChannel().sendMessage("Incorrect! The answer is " + trivia.getCorrectAnswer());
            }
        });

        System.out.println("Invite discordbot:" + api.createBotInvite());
    }

}
