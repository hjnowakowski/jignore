<h1 align="center">jignore: CLI for generating .gitignore files</h1>
<p>
  <a href="https://twitter.com/hjnowakowski">
    <img alt="Twitter: hjnowakowski" src="https://img.shields.io/twitter/follow/hjnowakowski.svg?style=social" target="_blank" />
  </a>
</p>

## Disclaimer

> The main purpose of this project is to test the GraalVM's native-image tool with a sample CLI Java program. The CLI program (gitignore generator) can be also used on it's own. Some functionality might be missing, but it's planned to be added in some future (see the [Issues section](https://github.com/hjnowakowski/jignore/issues) for missing functions).

## Description

`jignore` is a CLI app that can generate .gitignore contents.
`jignore` is written in Java and can be compiled to the native image (thanks to GraalVM). 

**Example usage**
```sh
jg java kotlin intellij
```
The above snipped will create a `.gitignore` (or append to existing) with the ignore contents related to `java`, `kotlin` and `intellij` keywords.
Note that firstly you need to compile the app to the native image (see below sections on how to do that) and later add it to the `PATH`.

## Shout-out to [gitignore.io](https://docs.gitignore.io/use/api)

The app uses the .gitignore collection from [gitignore.io](https://docs.gitignore.io/use/api)

## Performance comparison

The left Terminal shows the execution of the **native image**. The right one uses **jar** program.
The difference is not huge, however still clearly visible. Note that native image does not require JVM to run.

<div align="center"> 
<img src="readme-assets/jar-vs-native.gif">
</div>

## Prerequisites

* GraalVM Component Updater v2.0.0 (used to install `native-image`)
* GraalVM native-image building tool

## Install GraalVM tools

I've used brew to download the GraalVM

```sh
brew install --cask graalvm/tap/graalvm-ce-lts-java11

gu install native-image # Installs the native-image tool that is used to generate an executable
```

## Build and run the app as a native image

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
