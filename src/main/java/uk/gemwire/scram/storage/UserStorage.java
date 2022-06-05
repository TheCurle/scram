package uk.gemwire.scram.storage;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Handles reading and writing user info files on disk.
 * User info contains username, password hashes, and permissions.
 *
 * Information such as accessible servers, pending messages, etc, are stored in database.
 *
 * @author Curle
 */

@Deprecated
public class UserStorage {

    /**
     * The storage file has the format:
     *  USERNAME|HASHEDPASSWORD|ROLE1, ROLE2
     * It is important that these are not spaced out.
     */
    private static File storageFile = Path.of("./storage/users.db").toFile();

    public static List<UserDetails> readUsers() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(storageFile);
        List<String> lines = new ArrayList<>();

        // Read file
        while (fileScanner.hasNextLine())
            lines.add(fileScanner.nextLine());

        // Parse users
        List<UserDetails> details = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\\|");

            details.add(User.builder().passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                    .username(parts[0])
                    .password(parts[1])
                    .roles(parts[2])
                    .build());
        }

        return details;

    }

    public static void writeUsers(InMemoryUserDetailsManager service) {
        try (FileWriter out = new FileWriter(storageFile)) {
            Field users = InMemoryUserDetailsManager.class.getDeclaredField("users");
            users.setAccessible(true);
            Map<String, UserDetails> map = (Map<String, UserDetails>) users.get(service);

            for (UserDetails user : map.values()) {
                // USER|PASSWORD|ROLE
                out.write(user.getUsername() + "|" + user.getPassword() + "|" + ((String[]) user.getAuthorities().toArray())[0].substring(5));
            }
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
