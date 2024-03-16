
val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {

    id("java.application.java-common-conventions") apply true
    alias(libs.plugins.quarkus)
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    versionCatalog.findVersion("quarkusPluginVersion").ifPresent {
        implementation(enforcedPlatform("io.quarkus:quarkus-bom:${it}"))}
        implementation("org.apache.commons:commons-text")
        implementation("io.quarkus:quarkus-resteasy-reactive")
        implementation("io.quarkus:quarkus-arc")
        testImplementation("io.quarkus:quarkus-junit5-mockito")
        testImplementation("io.rest-assured:rest-assured")
        implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
        implementation("io.quarkus:quarkus-resteasy-reactive-jsonb")
        annotationProcessor("io.quarkus:quarkus-panache-common")
        implementation("io.quarkus:quarkus-jdbc-postgresql")
        implementation("io.quarkus:quarkus-hibernate-validator")
        implementation("io.quarkus:quarkus-hibernate-orm-panache")
        testImplementation("io.quarkus:quarkus-jdbc-h2")
        implementation("io.quarkus:quarkus-security")
        implementation("io.quarkus:quarkus-smallrye-openapi")
        implementation("io.quarkus:quarkus-flyway")
        implementation(project(":client"))
    }

group = "com.mhp"
version = "1.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

