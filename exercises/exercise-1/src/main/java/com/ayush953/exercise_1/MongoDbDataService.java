package com.ayush953.exercise_1;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MongoDbDataService implements DataService{
    public int[] retrieveData() {
        return new int[]{11,22,55,33,19};
    }
}
