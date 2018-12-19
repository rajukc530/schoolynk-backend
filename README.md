schoolynk-backend
---

schoolynk-console component is backend Rest API server application.
SchooLynk is a web-based support tool to go on to university for students.

## Getting Started

Setup following prerequisite software.
- Java 1.8 (not 1.9 or upper version)
- git
- IntelliJ IDEA
- Google Cloud SDK, and gcloud app Java Extensions

clone repository.

```
```

import to IntelliJ IDEA.

checkout codeStyle directory.
(you can use project standard code style in IntelliJ IDEA.)

```
git checkout .idea/codeStyles
```

install cloud datastore emulator

```
gcloud components install cloud-datastore-emulator
```


## Starting this program

run datastore emulator on your local pc.

```
gcloud beta emulators datastore start
```

set environment variables(run this command at same terminal as following command)

```
$(gcloud beta emulators datastore env-unset)
```

execute schoolynk-backend on your local pc.

```
gradle appengineRun
```

## Contributing

### How to report issues.

visit github issue page.


check exisiting issues.

if you find same or similar issue, comment additional information.
if you don't find, create new issue.


### How to contribute schoolynk-cosnole code.

write your code and check, with github flow.

