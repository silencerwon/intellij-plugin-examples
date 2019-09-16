import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.intellij") version "0.4.10"
    java
    kotlin("jvm") version "1.3.50"
}

group = "com.silencer.examples"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.github.rybalkinsd", "kohttp", "0.10.0")
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin", "2.9.7")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.2")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version = "2019.2.1"
}
configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    changeNotes("""
      Add change notes here.<br>
      <em>most HTML tags may be used</em>""")
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}