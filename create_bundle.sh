#!/bin/bash
mvn package gpg:sign -Dgpg.passphrase="$GPGPWD" -Dmaven.test.skip=true $@ || exit 1
echo "creating bundle"
version=$(grep '<version>' pom.xml | head -n1 | tr -d ' a-z<>/')
echo "Version $version"
cd target
jar -cvf bundle_${version}.jar rsa-${version}.pom rsa-${version}.pom.asc rsa-${version}.jar.asc rsa-${version}.jar rsa-${version}-javadoc.jar rsa-${version}-javadoc.jar.asc rsa-${version}-sources.jar.asc rsa-${version}-sources.jar

