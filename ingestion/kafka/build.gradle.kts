/*
 * Copyright (c) 2020 41North.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
  `java-library`
  kotlin("jvm")
  `maven-publish`
}

dependencies {

  implementation(project(":ingestion:base"))

  implementation("io.kcache:kcache")
  implementation("org.apache.kafka:kafka-clients")

  runtimeOnly("org.apache.logging.log4j:log4j-core")

  testImplementation(project(":testutil"))
  testImplementation("org.springframework.kafka:spring-kafka-test")
}

tasks {
  register<JavaExec>("runKafka") {
    group = "run"
    description = "Execute Exflo's Kafka plugin from Gradle"
    classpath = sourceSets.main.get().runtimeClasspath
    main = "org.hyperledger.besu.Besu"
    // Customize args as required to test and execute Exflo from Gradle
    // See https://github.com/41north/exflo/blob/develop/readme/.github/USAGE.md to customize params
    // Otherwise it will take defined defaults
    args = listOf("--plugin-exflo-kafka-enabled=true")
  }

  withType<Test> {
    useJUnitPlatform()
  }
}
