#!/bin/sh

PROJECT_NAME="ULessonSharedLibrary"
LIB_NAME="ulessonshared"

VERSION=$(cat VERSION) || exit
ORIGINAL_BRANCH=$(git rev-parse --abbrev-ref HEAD)

# Stash changes (just in case)
git stash

# Delete version branch if it exists
# shellcheck disable=SC2039
git branch -D "$VERSION" &>/dev/null

# Checkout version branch
git checkout --orphan "$VERSION" || exit

# Build project
./gradlew assembleXCFramework || exit

# Copy generated artifact to the root
cp -r ulessonshared/build/XCFrameworks/release/ulessonshared.xcframework .

PACKAGE_MANIFEST="
// swift-tools-version:5.3
import PackageDescription

let package = Package(
    name: \"$PROJECT_NAME\",
    platforms: [
        .iOS(.v13)
    ],
    products: [
        // Products define the executables and libraries a package produces, and make them visible to other packages.
        .library(
            name: \"$LIB_NAME\",
            targets: [\"${LIB_NAME}\"])
    ],
    dependencies: [
        // Dependencies declare other packages that this package depends on.
    ],
    targets: [
        // Targets are the basic building blocks of a package. A target can define a module or a test suite.
        // Targets can depend on other targets in this package, and on products in packages this package depends on.
        .binaryTarget(
            name: \"$LIB_NAME\",
            path: \"$LIB_NAME.xcframework\"
        ),
    ]
)"

# Write manifest
echo "$PACKAGE_MANIFEST" > Package.swift

# Add the manifest AND the XCFramework file
git add Package.swift "$LIB_NAME.xcframework" -f || exit

# Commit them
git commit -m "Added Package.swift and $LIB_NAME.xcframework for Version $VERSION" || exit

# (Force-)Push the code
git push --set-upstream origin "$VERSION" -f || exit

# Checkout the original branch
git checkout "$ORIGINAL_BRANCH" -f

# Pop the stash (if any)
git stash pop
