package com.luizaprestes.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.Color;


public class EmbedCreator {

    private final EmbedBuilder builder = new EmbedBuilder();

    public EmbedCreator(String title) {
        builder.setTitle(title);
    }

    public EmbedCreator color(Color color) {
        builder.setColor(color);
        return this;
    }

    public EmbedCreator description(String description) {
        builder.setDescription(description);
        return this;
    }

    public EmbedCreator footer(String footer) {
        builder.setFooter(footer);
        return this;
    }

    public EmbedCreator thumbnail(String thumbnail) {
        builder.setThumbnail(thumbnail);
        return this;
    }

    public EmbedCreator author(String author) {
        builder.setAuthor(author);
        return this;
    }

    public EmbedCreator field(String name, String value, boolean inline) {
        builder.addField(name, value, inline);
        return this;
    }

    public MessageEmbed build() {
        return builder.build();
    }

}
