package com.tribal.application;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * A 'simulated' bug database that returns a JSON object with example bug data.
 *
 * This is used for the test case decorator that skips a test case if the bug is still open.
 *
 *
 */
@RestController
@RequestMapping(value="/bug")
public class BugController {
    private static final Logger logger = LogManager.getLogger(BugController.class);

    private Map<Integer, Bug> bugMap;

    public BugController() {
        bugMap = new HashMap<>();

        // Initialize the bug 'database' with some sample bugs
        bugMap.put(245, new Bug(245, "OPEN", "A sample description"));
        bugMap.put(364, new Bug(364, "OPEN", "An open bug"));
        bugMap.put(450, new Bug(450, "FIXED", "A fixed bug"));
        bugMap.put(774, new Bug(774, "CLOSED", "A closed bug"));


    }

    @RequestMapping(method = RequestMethod.GET)
    public Bug get(@RequestParam(value="id") String id) {
        logger.trace("GET");

        Integer intID = Integer.parseInt(id);

        if (bugMap.containsKey(intID)) {
            return bugMap.get(intID);
        }

        // If bug not found ...
        return new Bug(1, "UNDEFINED", "BUG NOT FOUND");
    }

    public static class Bug {
        private Integer id;
        private String status;
        private String description;

        public Bug() {
        }

        public Bug(Integer id, String status, String description) {
            this.id = id;
            this.status = status;
            this.description = description;
        }

        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }

        public String getDescription() {
            return description;
        }
        public void setDescription(String status) {
            this.description = description;
        }

    }
}