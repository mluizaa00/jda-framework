package com.luizaprestes.framework.model.command;

import com.luizaprestes.framework.CommandFrame;
import com.luizaprestes.framework.exception.CommandException;
import com.luizaprestes.framework.model.CommandModel;
import com.luizaprestes.framework.model.command.impl.CommandFrameImpl;
import com.luizaprestes.framework.model.message.MessageHolder;
import com.luizaprestes.framework.views.listener.CommandListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractCommandFrame implements CommandFrame {

  @Getter
  private final String[] prefixes;
  private final Set<CommandModel> commandSet;

  private final CommandLoader loader;

  @Getter
  private final MessageHolder messageHolder;

  public AbstractCommandFrame(final String[] prefixes) {
    this.prefixes = prefixes;
    this.commandSet = new HashSet<>();

    this.loader = new CommandLoader();
    this.messageHolder = new MessageHolder();
  }

  @Override
  public CommandModel getCommand(final String name) {
    for (CommandModel model : commandSet) {
      if (model.getName().equalsIgnoreCase(name)) {
        return model;
      }

      for (String alias : model.getAliases()) {
        if (alias.equalsIgnoreCase(name)) {
          return model;
        }
      }
    }

    return null;
  }

  @Override
  public void loadCommands(final Object... holders) {
    for (final Object holder : holders) {
      loader.load(this, holder);
    }
  }

  @Override
  public void registerCommands(final CommandModel... models) {
    commandSet.addAll(Arrays.asList(models));
  }

  @Override
  public void build(final JDA jda) {
    jda.addEventListener(new CommandListener(this));
  }

  @NotNull
  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private List<String> prefixes;

    public Builder addPrefixes(final String... prefixes) {
      this.prefixes.addAll(Arrays.asList(prefixes));
      return this;
    }

    @NotNull
    public CommandFrame build() {
      if (prefixes.size() == 0) {
        throw new CommandException("No prefixes were added to the CommandFrame.");
      }

      return new CommandFrameImpl(prefixes.toArray(String[]::new));
    }

  }

}
