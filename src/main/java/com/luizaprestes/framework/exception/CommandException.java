package com.luizaprestes.framework.exception;

public final class CommandException extends RuntimeException {

  public CommandException() {
  }

  public CommandException(final String message) {
    super(message);
  }

  public CommandException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
