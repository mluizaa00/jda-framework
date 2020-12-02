package com.celeste.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class EmbedUtils {

    public static final Color BLUE = new Color(59, 165, 248);
    public static final Color RED = new Color(230, 35, 45);
    public static final Color YELLOW = new Color(252, 217, 45);
    public static final Color GREEN = new Color(91, 217, 12);

    public static MessageEmbed createEmbed(String title, String description, Color color, String thumbnail, String footer) {
        return new EmbedBuilder()
          .setTitle(title)
          .setDescription(description)
          .setColor(color)
          .setFooter(footer)
          .setThumbnail(thumbnail)
          .build();
    }

}
