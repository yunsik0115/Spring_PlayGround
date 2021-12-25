package Yunsik.Core.web;

import Yunsik.Core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    private final MyLogger myLogger;


    public void logic(String testId) {
        myLogger.log("servicd id = " + testId);
    }

}
