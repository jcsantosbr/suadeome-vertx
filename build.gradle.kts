@file:Suppress("PropertyName")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.js.translate.context.Namer.kotlin
import java.net.URI

val VERTX_VER = "3.5.1"

group = "com.jcs.suadeome1"
version = "1.0-SNAPSHOT"

buildscript {
    var kotlinVersion: String by extra
    kotlinVersion = "1.2.30"

    extra["shadow-ver"] = "2.0.1"

    repositories {
        jcenter()
        mavenCentral()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", kotlinVersion))
        classpath("com.github.jengelman.gradle.plugins:shadow:${extra["shadow-ver"]}")
    }
}

plugins {
    application
    kotlin("jvm") version "1.2.30"
}

application {
    mainClassName = "com.jcs.suadeome.MainKt"
}

val kotlinVersion: String by extra

repositories {
    jcenter()
    mavenCentral()
    maven {
        url = URI.create("https://oss.sonatype.org/content/repositories/iovertx-3717/")
    }
}

dependencies {
    compile(kotlinModule("stdlib-jdk8", kotlinVersion))
    compile("io.vertx:vertx-web-client:$VERTX_VER")
    testCompile("io.vertx:vertx-core:$VERTX_VER")
    testCompile("io.vertx:vertx-lang-kotlin:$VERTX_VER")
    testCompile("io.vertx:vertx-web:$VERTX_VER")
}

tasks.withType<ShadowJar> {
    baseName = "suadeome-vertx"
    version = "0.1-SNAPSHOT"
    classifier = ""
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
