schoolynk-api
---

[![CircleCI](https://circleci.com/gh/SchooLynk/schoolynk-api.svg?style=shield&circle-token=303fcbb33ba5b57a9ed88b5caf36774efe22d396)](https://circleci.com/gh/SchooLynk/schoolynk-api)
[![develop](https://img.shields.io/badge/host-dev-yellowgreen.svg)](https://api-dot-schoolynk-dev.appspot.com/swagger-ui.html)

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
git clone git@github.com:SchooLynk/schoolynk-api.git
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

execute schoolynk-api on your local pc.

```
gradle appengineRun
```

more information
- https://github.com/SchooLynk/schoolynk-specifications/blob/master/6.howtojoin/localenv/idea.md
- https://github.com/SchooLynk/schoolynk-specifications/blob/master/6.howtojoin/localenv/gcloudsdk.md
- https://cloud.google.com/datastore/docs/tools/datastore-emulator

## Contributing

### How to report issues.

visit github issue page.

https://github.com/SchooLynk/schoolynk-api/issues

check exisiting issues.

if you find same or similar issue, comment additional information.
if you don't find, create new issue.


### How to contribute schoolynk-cosnole code.

write your code and check, with github flow.

https://github.com/SchooLynk/schoolynk-specifications/blob/master/6.howtojoin/process/ghflow.md
