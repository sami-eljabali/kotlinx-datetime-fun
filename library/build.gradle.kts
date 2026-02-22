plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.dokka)
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
            api(libs.kotlinx.datetime)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmTest.dependencies {
            implementation(libs.junit.jupiter.api)
            runtimeOnly(libs.junit.jupiter.engine)
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
