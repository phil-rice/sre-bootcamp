package dev.srebootcamp.errorQueue.controllers;

import dev.srebootcamp.errorQueue.service.ErrorService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MockErrorService extends ErrorService {
    private List<String> msgs = new ArrayList<>();

    @Override
    public void error(String msg) {
        msgs.add(msg);
    }

    public void clear() {
        msgs.clear();
    }

    public List<String> getMsgs() {
        return Collections.unmodifiableList(msgs);
    }
}
