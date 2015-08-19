## GridWorld

Codes for GridWorld train program.

### Ant

In every folder which contains `build.xml`, run:

```bash
ant         # compile and run
ant junit   # run unit test in test/
ant report  # generate junit report in build/junitreport/
```

### Shell

In every folder which contains `short.sh`, run:

```bash
sh short.sh
```

### Sonar-cube (Linux)

Before:

```bash
cd $SONAR_HOME
./sonar.sh start
```

In every folder which contains `sonar-runner.properties`, run:

```bash
# In the project's root directory
sonar-runner
```

After:

```bash
./sonar.sh stop
```

### Author

* Shawn Dai
