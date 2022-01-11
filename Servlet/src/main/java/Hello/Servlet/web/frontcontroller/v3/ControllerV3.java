package Hello.Servlet.web.frontcontroller.v3;

import Hello.Servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
