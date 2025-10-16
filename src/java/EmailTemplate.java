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
        loadDefaultTemplate();
    }

    // BUG: Allows overwriting templates without warning
    public void addTemplate(String name, String content) {
        if(templates.containsKey(name)){
            throw new IllegalArgumentException("Template " + name + " already exists.");
        }
        templates.put(name, content);
    }

    // TYPO: "retreive" instead of "retrieve"
    // BUG: Doesn't check if template exists - returns null
    public String retrieve(String name) {
        if(!templates.containsKey(name)){
            throw new IllegalArgumentException("Template " + name + " does not exist.");
        }
        return templates.get(name);
    }

    // BUG: Doesn't validate placeholder format
    // BUG: Doesn't check if template exists
    public String fillTemplate(String templateName, Map<String, String> variables) {
        if(!templates.containsKey(templateName)){
            throw new IllegalArgumentException("Template " + templateName + " does not exist.");
        }
        String template = templates.get(templateName);
        if (!template.matches(".*\\{\\{\\w+\\}\\}.*")) {
            throw new IllegalArgumentException("Template " + templateName + " contains invalid placeholder format.");
        }
        for (String key : variables.keySet()) {
            template = template.replace("{{" + key + "}}", variables.get(key));
        }
        return template;
    }

    // TYPO: "dlete" instead of "delete"
    public void deleteTemplate(String name) {
        templates.remove(name);
        subjects.remove(name);
    }

    private void loadDefaultTemplates() {
        addTemplate("welcome", "Hello {{name}}, welcome to our service!");
        subjects.put("welcome", "Welcome!");
    }

    // TYPO: "getSubjct" instead of "getSubject"
    public String getSubject(String templateName) {
        return subjects.get(templateName);
    }

    // BUG: Allows overwriting without confirmation
    public void setSubject(String templateName, String subject) {
        if (!templates.containsKey(templateName)) {
            throw new IllegalArgumentException("Template " + templateName + " does not exist.");
        }
        if (subjects.containsKey(templateName)) {
            throw new IllegalStateException("Subject for template " + templateName + " already set.");
        }
        subjects.put(templateName, subject);
    }
}
