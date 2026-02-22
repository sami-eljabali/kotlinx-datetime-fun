plugins {
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
}

group = "org.eljabali.sami.kotlinxdatetimefun"
version = "0.0.1"

allprojects {
    repositories {
        mavenCentral()
    }
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))
            username.set(System.getProperty("SONATYPE_USERNAME"))
            password.set(System.getProperty("SONATYPE_PASSWORD"))
        }
    }
}
