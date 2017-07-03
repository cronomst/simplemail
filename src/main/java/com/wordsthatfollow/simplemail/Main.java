package com.wordsthatfollow.simplemail;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author kshook
 */
public class Main
{
    public static void main(String[] args)
    {
        if (args.length < 3) {
            System.out.println("Usage: <from address> <to address> <subject> <body>");
            System.exit(0);
        }
        String fromAddress = args[0];
        String toAddress = args[1];
        String subject = args[2];
        String body = args[3];
        
        Config config = Main.readConfig("simplemail.ini");
        SimpleMailSender sender = new SimpleMailSender(config);
        
        try {
            sender.send(fromAddress, toAddress, subject, body);
        } catch (EmailException e) {
            System.err.println(e);
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public static Config readConfig(String file)
    {
        Config config = new Config();
        HashMap<String, String> configData = new HashMap<>();
        
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split("=");
                String key = parts[0].trim();
                String value = parts[1].trim();
                configData.put(key, value);
                line = reader.readLine();
            }
            
        } catch (IOException e) {
            System.err.println(e);
        }
        
        config.hostName = configData.get("hostName");
        config.port = Integer.parseInt(configData.get("port"));
        config.username = configData.get("username");
        config.password = configData.get("password");
        
        return config;
    }
    
}
