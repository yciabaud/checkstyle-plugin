package hudson.plugins.checkstyle;

import hudson.Plugin;
import hudson.model.Descriptor.FormException;
import hudson.plugins.checkstyle.rules.CheckStyleRules;
import java.io.IOException;
import javax.servlet.ServletException;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.StaplerRequest;

/**
 * Initializes the Checkstyle messages and descriptions.
 *
 * @author Ulli Hafner
 */
public class CheckStylePlugin extends Plugin {
    
    /** Additional Checkstyle rules definition. */
    private String rulesPath;
    
    /** {@inheritDoc} */
    @Override
    public void start() throws IOException {
        load();
        CheckStyleRules.getInstance().initialize(rulesPath);
    }

    /**
     * Returns the additional Checkstyle rules definition location.
     * 
     * @return a path to a folder containing additional Checkstyle rules definition
     */
    public String getRulesPath() {
        return rulesPath;
    }

    /**
     * Sets the additional Checkstyle rules definition location.
     * 
     * @param rulesPath path to set
     */
    public void setRulesPath(String rulesPath) {
        this.rulesPath = rulesPath;
    }

    /** {@inheritDoc} */
    @Override
    public void configure(StaplerRequest req, JSONObject formData) throws IOException, ServletException, FormException {
        super.configure(req, formData);

        if(formData.containsKey("rulesPath") && "".equals(formData.getString("rulesPath"))){
            rulesPath = formData.getString("rulesPath");
            save();
        }
    }

}
