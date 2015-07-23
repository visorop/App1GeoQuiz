package com.visorop.app1geoquiz.helper;

import java.util.ArrayList;
import java.util.List;

public class Logger<T> {
    private List<T> logger;
    private int currentIndex;

    public Logger(){
        logger = new ArrayList<>();
        currentIndex = -1;
    }

    public int goBack(){
        if(currentIndex - 1 < 0){
            return -1;
        }else{
            return --currentIndex;
        }
    }

    public int goForward(){
        if(currentIndex + 1 >= logger.size()){
            return -1;
        }else{
            return ++currentIndex;
        }
    }

    public int getCurrentIndex(){
        return currentIndex;
    }

    public T getCurrent(){
        return logger.get(currentIndex);
    }

    public void add(T newValue){
        logger.add(newValue);
        if(currentIndex < 0){
            currentIndex++;
        }
    }

    public int goForwardAndAddIfNoMore(T value){
        if(goForward() < 0) {
            add(value);
            currentIndex++;
        }
        return currentIndex;
    }

    public void reset(){
        logger.clear();
        currentIndex = -1;
    }
}
