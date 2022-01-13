[![](https://jitpack.io/v/mluizaa00/jda-framework.svg)](https://jitpack.io/#mluizaa00/jda-framework)
# jda-framework

Hello! Welcome to the jda-framework repository.

## Creating your first command

This framework uses the annotation **@Command** to register a command.
It can register the name, aliases, permissions and role.

Example: 
````java
    @Command(
      name = "ping",
      aliases = "pong",
      permissions = Permission.ADMINISTRATOR,
      role = 773622683450736672L
    )
    public void handlePingCommand(final Message context, final String[] args) {
        final TextChannel channel = context.getTextChannel();
        channel.sendMessage("Pong!").queue();
    }
````


***

## Registring the CommandFrame:

After creating all your commands, the only thing you need to do is create a **CommandFrame** and set your prefix! 

The CommandFrame simplifies your life! It registers all commands and catch the event when a command is used.

Example:

```java
   final CommandFrame frame = AbstractCommandFrame.builder()
     .addPrefixes("!", "-")
     .build();

   frame.loadCommands(new PingCommand());
   frame.build(jda);
```


***

## MessageHolder

The MessageHolder holds the messages for errors when using a **Command** such as:
* No permission
* No role 

To use it, you just need to use the method below **before** the frame.build method.

Example:

```java
frame.getMessageHolder().setMessage(MessageType.LACK_PERM_MESSAGE, "Custom message!");
```
