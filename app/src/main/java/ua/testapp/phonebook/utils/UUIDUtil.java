package ua.testapp.phonebook.utils;

import java.util.UUID;

import javax.inject.Inject;

public class UUIDUtil {
    public static UUID getRandomUUID () {
        return UUID.randomUUID();
    }
}
