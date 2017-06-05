package org.jboss.as.quickstarts.jms;

import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;

public class MentionParser {
  

  public static void main(String[] args) {
  
    String mentions = "mentions.txt";
    
    readMentions(mentions);
  } 

  public static void readMentions(String mentionsFile) {

    Stream<String> mentionsStream = null;
    try {
      // Read all lines froma  file as a Stream. Bytes decoded into UTF-8 char
      mentionsStream = Files.lines(Paths.get(mentionsFile));
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("=============== Result from lines() and Stream ================");
    mentionsStream.forEach(System.out::println);

  }
}
