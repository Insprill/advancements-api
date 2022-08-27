[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![GNU License][license-shield]][license-url]




<!-- PROJECT LOGO -->
<br />
<p style="text-align: center;">

  <h3 align="center">Advancements API</h3>

  <p align="center">
    A Spigot library for creating custom advancements
    <br />
    <b>WARNING: This is a work-in-progress and breaking changes may occur at any time!</b>
    <br />
    <a href="https://javadoc.io/doc/net.insprill/advancements-api"><strong>View Javadocs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/Insprill/advancements-api/issues">Report Bug</a>
    ·
    <a href="https://github.com/Insprill/advancements-api/issues">Request Feature</a>
  </p>




<!-- TABLE OF CONTENTS -->

## Table of Contents

* [About The Project](#about-the-project)
* [Usage](#usage)
* [Roadmap](#roadmap)
* [Contributing](#contributing)
* [Building](#building)
* [License](#license)




<!-- ABOUT THE PROJECT -->

## About The Project

Advancements API is a project that allows custom advancements to be created and registered in code.  

While it's written in Kotlin, the API has been designed to be used in Java projects with minimal overhead.




<!-- USAGE EXAMPLES -->

## Usage

### Implementing in your project

[![Maven Central][maven-central-shield]][maven-central-url]
#### Maven

```xml
<dependency>
    <groupId>net.insprill</groupId>
    <artifactId>advancements-api</artifactId>
    <version>version</version>
</dependency>
```

#### Gradle (Groovy or Kotlin)

```groovy
dependencies {
    implementation("net.insprill:advancements-api:version")
}
```

### Shading

When shading Advancements API, if you minimize the final output, make sure to exclude advancements-api from this process.
The NMS implementations are access via reflection at runtime, and they will get removed from the output if it's not excluded.




<!-- ROADMAP -->

## Roadmap

See the [open issues](https://github.com/Insprill/advancements-api/issues) for a list of proposed features (and known issues).




<!-- CONTRIBUTING -->

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any
contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AwesomeFeature`)
3. Commit your Changes (`git commit -m 'Add some AwesomeFeature'`)
4. Push to the Branch (`git push origin feature/AwesomeFeature`)
5. Open a Pull Request




<!-- BUILDING -->

## Building

To compile Advancements API, you will need JDK 8 or higher and an internet connection.  
Clone this repo, then run `./gradlew build` from your terminal.  
You can find the compiled jar in the `build/libs` directory.  
If you'd like to publish it to your local Maven repository, you can run `./gradlew publishToMavenLocal`.




<!-- LICENSE -->

## License

Distributed under the Apache 2.0 License. See [LICENSE][license-url] for more information.




<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[contributors-shield]: https://img.shields.io/github/contributors/Insprill/advancements-api.svg?style=for-the-badge
[contributors-url]: https://github.com/Insprill/advancements-api/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/Insprill/advancements-api.svg?style=for-the-badge
[forks-url]: https://github.com/Insprill/advancements-api/network/members
[stars-shield]: https://img.shields.io/github/stars/Insprill/advancements-api.svg?style=for-the-badge
[stars-url]: https://github.com/Insprill/advancements-api/stargazers
[issues-shield]: https://img.shields.io/github/issues/Insprill/advancements-api.svg?style=for-the-badge
[issues-url]: https://github.com/Insprill/advancements-api/issues
[license-shield]: https://img.shields.io/github/license/Insprill/advancements-api.svg?style=for-the-badge
[license-url]: https://github.com/Insprill/advancements-api/blob/master/LICENSE
[maven-central-shield]: https://img.shields.io/maven-central/v/net.insprill/advancements-api
[maven-central-url]: https://mvnrepository.com/artifact/net.insprill/advancements-api
