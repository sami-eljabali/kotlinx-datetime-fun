plugins {
    kotlin("multiplatform") version "2.3.10"
    id("org.jetbrains.dokka") version "1.9.20"
    `maven-publish`
    signing
}

kotlin {
    jvm {
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    sourceSets {
        commonMain.dependencies {
            api("org.jetbrains.kotlinx:kotlinx-datetime:0.6.2")
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
        jvmTest.dependencies {
            implementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
            runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
        }
    }
}

tasks {
    register<Jar>("dokkaJar") {
        from(dokkaHtml)
        dependsOn(dokkaHtml)
        archiveClassifier.set("javadoc")
    }
    withType<Jar> {
        metaInf.with(
            copySpec {
                from("${rootProject.rootDir}/LICENSE")
            }
        )
    }
}

publishing {
    publications.withType<MavenPublication>().configureEach {
        artifactId = rootProject.name
        artifact(tasks["dokkaJar"])
        pom {
            name.set(rootProject.name)
            description.set("kotlinx.datetime Kotlin extension functions library.")
            url.set("https://github.com/sami-eljabali/kotlinx-datetime-fun")
            licenses {
                license {
                    name.set("MIT License")
                    url.set("https://github.com/sami-eljabali/kotlinx-datetime-fun/blob/main/LICENSE")
                }
            }
            developers {
                developer {
                    id.set("seljabali")
                    name.set("Sami Eljabali")
                    email.set("sami@eljabali.org")
                    url.set("sami.eljabali.org")
                }
            }
            scm {
                connection.set("scm:git:git://github.com/seljabali/kotlinx-datetime-fun.git")
                developerConnection.set("scm:git:ssh://github.com/seljabali/kotlinx-datetime-fun.git")
                url.set("https://github.com/sami-eljabali/kotlinx-datetime-fun/tree/main")
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(
        System.getProperty("GPG_PRIVATE_KEY"),
        System.getProperty("GPG_PRIVATE_PASSWORD")
    )
    sign(publishing.publications)
}
