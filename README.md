# TicketMonster - a JBoss example

TicketMonster is an online ticketing demo application that gets you started with JBoss technologies, and helps you learn and evaluate them.

Here are a few instructions for building and running it. You can learn more about the example from the [tutorial](http://www.jboss.org/ticket-monster).

## Ticket Monster with Docker

To run with docker:

```
mvn -Pf8-build
docker run -it --rm -p 8080:8080 fabric8/ticket-monster-monolith-backend:1.0-SNAPSHOT
```

Can port forward to your local machine from vagrant like this:

> vagrant ssh -- -vnNTL *:8080:$DOCKER_HOST_IP:8080

## Updating the Performance dates

_NOTE: This step is optional. It is necessary only if you want to update the dates of the Performances in the `import.sql` script in an automated manner. Updating the performance dates ensure that they are always set to some timestamp in the future, and ensures that all performances are visible in the Monitor section of the TicketMonster application._

1. Run the `update_import_sql` Perl script. You'll need the `DateTime`, `DateTime::Format::Strptime` and `Tie::File` Perl modules. These are usually available by default in your Perl installation.

        $ perl update_import_sql.pl src/main/resources/import.sql

## Generating the administration site

_NOTE: This step is optional. The administration site is already present in the source code. If you want to regenerate it from Forge, and apply the changes outlined in the tutorial, you may continue to follow the steps outlined here. Otherwise, you can skip this step and proceed to build TicketMonster._

Before building and running TicketMonster, you must generate the administration site with Forge.

1. Ensure that you have [JBoss Forge](http://jboss.org/forge) installed. The current version of TicketMonster supports version 2.6.0.Final or higher of JBoss Forge. JBoss Developer Studio 8 is recommended, since it contains JBoss Forge 2 with all the necessary plugins for the TicketMonster app.

2. Start the JBoss Forge console in JBoss Developer Studio. This can be done from the Forge Console view. If the view is not already visible, it can be opened through the 'Window' menu: _Window_ -> _Show View_ -> _Other..._. Select the 'Forge Console' item in the dialog to open the Forge Console. Click the _Start_ button in the Forge Console tab, to start Forge.

3. From the JBoss Forge prompt, browse to the 'demo' directory of the TicketMonster sources and execute the script for generating the administration site

	    $ cd ticket-monster/demo
	    $ run admin_layer.fsh

    The git patches need to be applied manually. Both the patches are located in the patches sub-directory. To apply the manual changes, first apply the patch located in file _admin_layer_functional.patch_. Then perform the same for the file _admin_layer_graphics.patch_ if you want to apply the style changes for the generated administration site. You can do so in JBoss Developer Studio, by opening the context-menu on the project (Right-click on the project) and then apply a git patch via _Team_ -> _Apply Patch..._. Locate the patch file in the Workspace, select it and click the 'Next' button. In the next dialog, select to apply the patch on the 'ticket-monster' project in the workspace. Click Finish in the final page of the wizard after satisfying that the patch applies cleanly.

4. Deployment to JBoss EAP 6.3 is optional. The project can be built and deployed to a running instance of JBoss EAP through the following command in JBoss Forge:

	    $ build clean package wildfly:deploy

## Building TicketMonster

TicketMonster can be built from Maven, by runnning the following Maven command:

    mvn clean package

### Building TicketMonster with tests

If you want to run the Arquillian tests as part of the build, you can enable one of the two available Arquillian profiles.

For running the tests in an _already running_ application server instance, use the `arq-wildfly-remote` profile.

    mvn clean package -Parq-wildfly-remote

If you want the test runner to _start_ an application server instance, use the `arq-wildfly-managed` profile. You must set up the `JBOSS_HOME` property to point to the server location, or update the `src/main/test/resources/arquillian.xml` file.

    mvn clean package -Parq-wildfly-managed

### Building TicketMonster with Postgresql (for OpenShift)

If you intend to deploy into [OpenShift](http://openshift.com), you can use the `postgresql-openshift` profile

    mvn clean package -Ppostgresql-openshift

### Building TicketMonster with MySQL (for OpenShift)

If you intend to deploy into [OpenShift](http://openshift.com), you can use the `mysql-openshift` profile

    mvn clean package -Pmysql-openshift

## Running TicketMonster

You can run TicketMonster into a local JBoss EAP 6.3 instance or on OpenShift.

### Running TicketMonster locally

#### Start JBoss Enterprise Application Platform 6.3

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat

#### Deploy TicketMonster

1. Make sure you have started the JBoss Server as described above.
2. Type this command to build and deploy the archive into a running server instance.

        mvn clean package wildfly:deploy

	(You can use the `arq-wildfly-remote` profile for running tests as well)

3. This will deploy `target/ticket-monster.war` to the running instance of the server.
4. Now you can see the application running at `http://localhost:8080/ticket-monster`

