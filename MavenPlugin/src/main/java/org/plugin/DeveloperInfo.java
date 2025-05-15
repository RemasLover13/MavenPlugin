package org.plugin;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.model.Developer;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.List;


@Mojo(name = "developer-info")
public class DeveloperInfo extends AbstractMojo {
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    public void execute() throws MojoExecutionException {
        List<Developer> developers = project.getDevelopers();

        if (CollectionUtils.isEmpty(developers)) {
            getLog().error("No developers found in the project.");
            return;
        }

        getLog().info("Project Developers Information:");
        for (Developer developer : developers) {
            getLog().info("-----------------------------------");
            getLog().info("ID: " + StringUtils.defaultIfEmpty(developer.getId(), "-"));
            getLog().info("Name: " + StringUtils.defaultIfEmpty(developer.getName(), "-"));
            getLog().info("Email: " + StringUtils.defaultIfEmpty(developer.getEmail(), "-"));
            getLog().info("URL: " + StringUtils.defaultIfEmpty(developer.getUrl(), "-"));
            getLog().info("Organization: " + StringUtils.defaultIfEmpty(developer.getOrganization(), "-"));
            getLog().info("Organization URL: " + StringUtils.defaultIfEmpty(developer.getOrganizationUrl(), "-"));

            List<String> roles = developer.getRoles();
            String rolesInfo = (roles != null && !roles.isEmpty())
                    ? String.join(", ", roles)
                    : "-";
            getLog().info("Roles: " + rolesInfo);
            getLog().info("TimeZone: " + StringUtils.defaultIfEmpty(developer.getTimezone(), "-"));
            getLog().info("Properties: " + StringUtils.defaultIfEmpty(developer.getProperties().toString(), "-"));
        }
    }
}