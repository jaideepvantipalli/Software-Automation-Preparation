# Maven Quick Reference

## 1. Create Your First Maven Project

``` bash
mvn archetype:generate -DgroupId=com.jaideep -DartifactId=StudentManagement -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

### Command Breakdown

  Option                       Meaning
  ---------------------------- -------------------------------------------
  mvn                          Executes Maven
  archetype:generate           Generates a Maven project from a template
  groupId                      Organization/package name
  artifactId                   Project name
  maven-archetype-quickstart   Basic Java project template
  interactiveMode=false        Skips prompts

## Maven Terminologies

-   **Maven** -- Build automation and dependency management tool.
-   **POM** -- Project Object Model (`pom.xml`).
-   **groupId** -- Organization/package identifier.
-   **artifactId** -- Project name.
-   **version** -- Project version.
-   **Dependency** -- External library.
-   **Plugin** -- Performs build tasks.
-   **Archetype** -- Project template.
-   **Local Repository** -- `.m2/repository`.
-   **Central Repository** -- Public Maven repository.
-   **Remote Repository** -- Company/private repository.
-   **Coordinates** -- `groupId + artifactId + version`.

## Maven Build Lifecycle

validate → compile → test → package → verify → install → deploy

### Common Commands

``` bash
mvn -version
mvn clean
mvn compile
mvn test
mvn package
mvn install
mvn deploy
mvn dependency:tree
mvn help:effective-pom
```

### Phase Summary

-   **validate** -- Validate project.
-   **compile** -- Compile Java source.
-   **test** -- Run unit tests.
-   **package** -- Create JAR/WAR.
-   **verify** -- Additional checks.
-   **install** -- Copy artifact to `.m2`.
-   **deploy** -- Publish artifact to remote repository.

### Key Points

-   `pom.xml` is the heart of Maven.
-   `target/` contains generated files.
-   `.m2` stores downloaded dependencies.
-   Maven downloads dependencies automatically.
-   Every Maven project follows a standard directory layout.
