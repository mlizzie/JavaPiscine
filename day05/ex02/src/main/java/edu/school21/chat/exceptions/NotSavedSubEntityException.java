package edu.school21.chat.exceptions;

public class NotSavedSubEntityException extends RuntimeException {

    @Override
    public void printStackTrace() {
        System.err.print("Cannot save message");
    }
}
