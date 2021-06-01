package com.lm.pub4.Util;

import com.lm.pub4.Entities.Topic;
import com.lm.pub4.Entities.User;
import com.lm.pub4.Exceptions.InvalidArgumentsException;

import java.util.List;

public class GetEntitiesByName {

    public static User getUserByName(List<User> userList, String userName) throws InvalidArgumentsException {
        for (User user: userList) {
            if (user.getName().equals(userName))
                return user;
        }
        throw new InvalidArgumentsException();
    }

    public static Topic getTopicByName(List<Topic> topics, String topicName) throws InvalidArgumentsException {
        for (Topic topic: topics) {
            if (topic.getName().equals(topicName))
                return topic;
        }
        throw new InvalidArgumentsException();
    }
}
