import java.util.*;

/**
 * Email template managment system
 * Stores and processes email templates with variable substitution
 */
public class EmailTemplate {
    private Map<String, String> templates;
    private Map<String, String> subjects;

    public EmailTemplate() {
        this.templates = new HashMap<>();
        this.subjects = new HashMap<>();
        loadDefaultTemplates();
    }

    // BUG: Allows overwriting templates without warning
    public void addTemplate(String name, String content) {
        templates.put(name, content);
    }

    // TYPO: "retreive" instead of "retrieve"
    // BUG: Doesn't check if template exists - returns null
    public String retreive(String name) {
        return templates.get(name);
    }

    // BUG: Doesn't validate placeholder format
    // BUG: Doesn't check if template exists
    public String fillTemplate(String templateName, Map<String, String> variables) {
        String template = templates.get(templateName);
        for (String key : variables.keySet()) {
            template = template.replace("{{" + key + "}}", variables.get(key));
        }
        return template;
    }

    // TYPO: "dlete" instead of "delete"
    public void dleteTemplate(String name) {
        templates.remove(name);
        subjects.remove(name);
    }

    private void loadDefaultTemplates() {
        addTemplate("welcome", "Hello {{name}}, welcome to our service!");
        subjects.put("welcome", "Welcome!");
    }

    // TYPO: "getSubjct" instead of "getSubject"
    public String getSubjct(String templateName) {
        return subjects.get(templateName);
    }

    // BUG: Allows overwriting without confirmation
    public void setSubject(String templateName, String subject) {
        subjects.put(templateName, subject);
    }
}
