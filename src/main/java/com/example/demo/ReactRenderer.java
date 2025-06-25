package com.example.demo;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;


@Service
public class ReactRenderer {
    private static final String REACT_COMPONENT_PATH = "frontend/build/ssr-bundle.js";


    public String renderComponent(String componentName, String propsJson) throws Exception {
        String jsCode = new String(Files.readAllBytes(Paths.get(REACT_COMPONENT_PATH)));

        try (Context context = Context.create("js")) {
            String polyfill = """
                            globalThis.TextEncoder = class {
                            encode(str) {
                                const buf = [];
                                for (let i = 0; i < str.length; i++) {
                                buf.push(str.charCodeAt(i));
                                }
                                return new Uint8Array(buf);
                            }
                            };
                        """;

            context.eval("js", polyfill);
            context.eval("js", jsCode);
            Value renderFunction = context.getBindings("js").getMember("renderComponent");
            if (renderFunction == null || !renderFunction.canExecute()) {
                throw new IllegalStateException("Render function not found or not executable");
            }

            Value result = renderFunction.execute(componentName, propsJson);
            return result.asString();
        }
    }
}
