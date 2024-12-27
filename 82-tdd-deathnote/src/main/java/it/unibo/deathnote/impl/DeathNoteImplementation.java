package it.unibo.deathnote.impl ;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote{

    private final Map<String, String> names = new HashMap<>();
    private final Map<String, String> details = new HashMap<>();
    private long timeOfLastWriting;

    public Map<String, String> getNames() {
        return this.names;
    }

    public Map<String, String> getDetails() {
        return this.details;
    }

    @Override
    public String getRule(int ruleNumber) {
        if(ruleNumber < 1 || ruleNumber > RULES.size()) {
            throw new IllegalArgumentException("Such rule does not exist");
        }
        return RULES.get(ruleNumber);
    }

    @Override
    public void writeName(String name) {
        if(name.equals(null) || name.equals("")) {
            throw new NullPointerException("No name has been written");
        }
        if(!names.containsKey(name)) {
            this.names.put(name, "heart attack");
            this.details.put(name, null);
            timeOfLastWriting = System.currentTimeMillis();
        }
    }

    @Override
    public boolean writeDeathCause(String name, String cause) {
        if(!names.containsKey(name)) {
            throw new IllegalStateException("The name has not been written in the death note");
        }
        if(cause.equals(null)) {
            throw new IllegalStateException("You did not wrote a cause");
        }
        if(System.currentTimeMillis() - timeOfLastWriting < 40) {
            names.replace(name, cause);
            timeOfLastWriting = System.currentTimeMillis();
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean writeDetails(String name, String details) {
        if(!this.names.containsKey(name)) {
            throw new IllegalStateException("The name has not been written in the death note");
        }
        if(details == null) {
            throw new IllegalStateException("You need to write details of the death");
        }
        if(System.currentTimeMillis() - timeOfLastWriting < 6040) {
            this.details.replace(name, details);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getDeathCause(String name) {
        return this.names.get(name);
    }

    @Override
    public String getDeathDetails(String name) {
        return this.details.get(name);
    }

    @Override
    public boolean isNameWritten(String name) {
        return this.names.containsKey(name);
    }

    
}