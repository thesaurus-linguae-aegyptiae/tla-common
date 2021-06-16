[![](https://jitpack.io/v/thesaurus-linguae-aegyptiae/tla-common.svg)](https://jitpack.io/#thesaurus-linguae-aegyptiae/tla-common)
![Build](https://github.com/thesaurus-linguae-aegyptiae/tla-common/workflows/build/badge.svg)
![LINE](https://img.shields.io/badge/line--coverage-90.07%25-brightgreen.svg)
![METHOD](https://img.shields.io/badge/method--coverage-83.90%25-brightgreen.svg)

Copyright (c) 2020-2021 Berlin-Brandenburgische Akademie der Wissenschaften

# TLA data transfer model library

Use this to speak to speak to TLA backend endpoints.

## Installation

Add this to your gradle file:

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.thesaurus-linguae-aegyptiae:tla-common:master-SNAPSHOT'
}
```

## Misc

You can check for the newest version of package dependencies by running:

    ./gradlew dependencyUpdates


publish to local maven repository:

    ./gradlew publishToMavenLocal
