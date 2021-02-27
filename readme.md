<h1 align="center">jignore: CLI for generating .gitignore files</h1>
<p>
  <a href="https://twitter.com/hjnowakowski">
    <img alt="Twitter: hjnowakowski" src="https://img.shields.io/twitter/follow/hjnowakowski.svg?style=social" target="_blank" />
  </a>
</p>

## Disclamer 
> This project is not ready yet (as a CLI gitignore tool). The initial goal is to provide a POC of a CLI application written in Java but leveraging GraalVM to increase it's performance. If I have time, I'll finish this app such that is a usable CLI utility for generating `.gitignore` files.

## Prerequisites

* GraalVM Component Updater v2.0.0 (used to install `native-image`)
* GraalVM native-image building tool

## Installing GraalVM tools

I've used brew to download the GraalVM

```sh
brew install --cask graalvm/tap/graalvm-ce-lts-java11

gu install native-image # Installs the native-image tool that is used to generate an executable
```

## Build and run as native image

```sh
./gradlew jar #Creates a jar with the app in the build/libs directory

cd build/libs

native-image -jar jg-1.0-SNAPSHOT.jar \
 -H:IncludeResources='gitignore.io.json' \ #graalvm needs to have resources explicitly referenced
 -H:ReflectionConfigurationFiles="<DIR-TO-THE-PROJECT>/src/main/resources/reflection-config.json" \ #since we are deserializing the json file, we need to provide proper reflection configuration
 --no-fallback

```

## Author

👤 **Henryk Nowakowski**