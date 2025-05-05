package org.plugin;

import org.apache.maven.model.Developer;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.project.MavenProject;

import java.util.List;



@Mojo(name = "developer-info")
public class DeveloperInfo extends AbstractMojo {
    @Component
    private MavenProject project;

    public void execute() throws MojoExecutionException {
        List<Developer> developers = project.getDevelopers();

        if (developers.isEmpty()) {
            getLog().error("No developers found in the project.");
            return;
        }

        getLog().info("Project Developers Information:");
        for (Developer developer : developers) {
            getLog().info("-----------------------------------");
            getLog().info("ID: " + developer.getId());
            getLog().info("Name: " + developer.getName());
            getLog().info("Email: " + developer.getEmail());
            getLog().info("URL: " + developer.getUrl());
            getLog().info("Organization: " + developer.getOrganization());
            getLog().info("Organization URL: " + developer.getOrganizationUrl());
            getLog().info("Roles: " + String.join(", ", developer.getRoles()));
            getLog().info("Timezone: " + developer.getTimezone());
            getLog().info("Properties: " + developer.getProperties());
        }
    }
}