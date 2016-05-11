
# Welcome to Team 9's awesome project


Setup
----
1. Install git (https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
2. In a terminal, run `git clone https://github.com/dandoman/team9sofine.git`
3. Then `$cd team9sofine`

#### Back End
What you need:

* Java 8 - http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
* Maven - https://maven.apache.org/download.cgi
* Postgres - http://www.postgresql.org/download/

Getting set up:

* Start your postgres database 
* In src/main/resources, use the two scripts to create the database, user and tables. Must be run manually at the moment.

Running during normal development: 

*All of the following are to be run from the project root directory*

* 'mvn spring-boot:run' - will run the back end on port 8080
* 'mvn package' - will build the fat jar with the embedded tomcat server
* Once the fat jar is built, can run using 'java -jar ${jarName}'

While the service is running, you can see some basic back end swagger API documentation at 
* http://localhost:8080/v2/api-docs

#### Front End

What you need:

* Nodejs (only if you don't use Vagrant) - https://nodejs.org/en/download/
* (Optional) Vagrant - https://www.vagrantup.com/downloads.html
* (Optional) VirtualBox - https://www.virtualbox.org/wiki/Downloads

######  If you have chosen to install both Vagrant and VirtualBox
Run the following commands in a terminal to get started:
```sh

$ cd <code dir>/team9sofine
$ npm install
$ gulp build
```

Now, in your browser, go to `http://192.168.33.110` and you should see the frontend loaded. Within the terminal, you can also run `$lynx myapp.dev` to see a text-only version. Ctrl+C to quit out.

###### If you have chosen NOT to install Vagrant
1. Install Nodejs, along with NPM https://nodejs.org/en/download/
2. Install any http webserver, such as apache or nginx (Tomcat will probably work as well, as these are all essentially static files)
3. Run the following:
```sh
# Run these in a terminal
$ sudo apt install --global gulp # Or whatever your package manager is
$ cd <code dir>/team9sofine
$ npm install
$ gulp build
```
4. Symbolic link `/team9sofine/static` to the default Apache or Nginx web directory (usually `/var/www` on Debian-based distros)
5. Go to `http://localhost`
