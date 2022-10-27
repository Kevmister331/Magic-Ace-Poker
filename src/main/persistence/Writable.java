package persistence;

import org.json.JSONObject;

// Some of this code in this class is sourced from the provided CPSC 210 JSON program
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}