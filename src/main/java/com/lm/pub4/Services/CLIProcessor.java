package com.lm.pub4.Services;

import com.lm.pub4.Entities.MessageBody;
import com.lm.pub4.Entities.Topic;
import com.lm.pub4.Entities.User;
import com.lm.pub4.Exceptions.InvalidArgumentsException;
import com.sun.jmx.remote.internal.ArrayQueue;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.json.GsonJsonParser;

import java.util.*;

import static com.lm.pub4.Util.GetEntitiesByName.getTopicByName;
import static com.lm.pub4.Util.GetEntitiesByName.getUserByName;

public class CLIProcessor {

    private List<User> userList = new ArrayList<>();
    private List<Topic> topics = new ArrayList<>();

    private Queue<MessageBody> messages = new ArrayDeque<>();

    public CLIProcessor() {
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        List<String> commands;

        while (true) {
            System.out.println("Please enter the command: ");
            String command = sc.nextLine();
            commands = Arrays.asList(command.split(" "));

            String method = commands.get(0);
            try {
                if ("addUser".equals(method)) {
                    this.addUser(commands);
                    System.out.println("User added successfully");
                } else if ("addTopic".equals(method)) {
                    this.addTopic(commands);
                } else if ("subscribeTopic".equals(method)) {
                    this.subscribeTopic(commands);
                } else if ("publishMessage".equals(method)) {
                    this.publishMessage(commands);
                } else if ("processMessages".equals(method)) {
                    this.processMessages();
                } else if ("viewSubscribedTopics".equals(method)) {
                    this.viewSubscribedTopics(commands);
                } else if ("removeUser".equals(method)) {
                    this.removeUser(commands);
                } else if ("removeTopic".equals(method)) {
                    this.removeTopic(commands);
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("Insufficient Arguments");
                continue;
            } catch (InvalidArgumentsException ex) {
                System.out.println("Invalid Arguments");
                continue;
            }
        }
    }

    private void addUser(List<String> commands) throws InvalidArgumentsException, ArrayIndexOutOfBoundsException {
        String name = commands.get(1);
        String role = commands.get(2);

        if (Strings.isNotBlank(name) && Strings.isNotBlank(role)) {
            User user = new User(name, role);
            this.userList.add(user);
            return;
        }
        throw new InvalidArgumentsException();
    }

    private void addTopic(List<String> commands) throws InvalidArgumentsException, ArrayIndexOutOfBoundsException {
        String topicName = commands.get(1);
        String userName = commands.get(2);

        if (Strings.isNotBlank(userName) && getUserByName(userList, userName).getUserRole().equals(User.UserRole.ADMIN)) {
            this.topics.add(new Topic(topicName));
            return;
        }
        throw new InvalidArgumentsException();
    }

    private void removeUser(List<String> commands) throws InvalidArgumentsException, ArrayIndexOutOfBoundsException {
        String userName1 = commands.get(1);
        String userName2 = commands.get(2);

        if (Strings.isNotBlank(userName2) && getUserByName(userList, userName2).getUserRole().equals(User.UserRole.ADMIN)) {
            this.userList.remove(getUserByName(userList, userName1));
            return;
        }
        throw new InvalidArgumentsException();
    }

    private void removeTopic(List<String> commands) throws InvalidArgumentsException, ArrayIndexOutOfBoundsException {
        String topicName = commands.get(1);
        String userName = commands.get(2);

        if (Strings.isNotBlank(userName) && getUserByName(userList, userName).getUserRole().equals(User.UserRole.ADMIN)) {
            this.topics.remove(getTopicByName(topics, topicName));
            return;
        }
        throw new InvalidArgumentsException();
    }

    private void subscribeTopic(List<String> commands) throws InvalidArgumentsException, ArrayIndexOutOfBoundsException {
        String topicName = commands.get(1);
        String userName = commands.get(2);

        Topic topic = new Topic(topicName);
        topic.addUser(getUserByName(userList, userName));
    }

    private void publishMessage(List<String> commands) throws InvalidArgumentsException, ArrayIndexOutOfBoundsException {

        GsonJsonParser jsonParser = new GsonJsonParser();
        Map<String, Object> messageMap = jsonParser.parseMap(commands.get(1));

        MessageBody newMessage = new MessageBody(
                (String) messageMap.get("id"),
                (String) messageMap.get("topicName"),
                (String) messageMap.get("text"));

        messages.add(newMessage);
    }

    private void processMessages() throws InvalidArgumentsException, ArrayIndexOutOfBoundsException {

        for (MessageBody message : messages) {
            Topic topic = getTopicByName(topics, message.getTopicName());
            for (User user : topic.getUsers()) {
                System.out.println("topic: " + topic.getName() + ", message: " + message.getText() + ", sentTo: " + user.getName());
            }
        }
    }

    private void viewSubscribedTopics(List<String> commands) throws InvalidArgumentsException, ArrayIndexOutOfBoundsException {

        User user = getUserByName(userList, commands.get(1));
        List<Topic> subscribedTopics = new ArrayList<>();
        for (Topic topic : topics) {
            if (topic.getUsers().contains(user)) {
                subscribedTopics.add(topic);
            }
        }

        System.out.println("Subscribed topics are: " + subscribedTopics);
    }
}