#!/usr/bin/env bash

. ${PWD}/.github/actions/logger.sh

GITHUB_TOKEN=$1
REPO_REMOTE_URL=$(git config --get remote.origin.url)
POM="documentation/pom.xml"
PROJECT_DIR=${PWD}
DOC_TEMPLATE="${PROJECT_DIR}/.github/actions/template"
DOC_DIRECTORY="documentation/src/main/asciidoc"
GENERATED_DOC_DIRECTORY=""
VERSION=$(./mvnw -q -Dexec.executable=echo -Dexec.args='${project.version}' --non-recursive exec:exec)

echo "============ Publish Documentation ============"

git config --global user.name "altus34"
git config --global user.email "d.avenante@gmail.com"

configure_documentation() {
    # Copy header and footer
    cp "${DOC_TEMPLATE}/docinfos/docinfo.html" "${DOC_DIRECTORY}"
    cp "${DOC_TEMPLATE}/docinfos/docinfo-footer.html" "${DOC_DIRECTORY}"
    cp -r "${DOC_TEMPLATE}/docinfos/js" "${DOC_DIRECTORY}/js"
    echo "============ ✅ Documentation configured =============="
}

generate_documentation() {
    echo "============ ✅ Generate Documentation ================"
    ./mvnw -f ${POM} --batch-mode clean package
    EXIT_CODE=$?
    if [[ ${EXIT_CODE} -gt 0 ]]; then
        echo "============ 🔴 Documentation generation has failed =="
        exit "${EXIT_CODE}"
    fi
    echo "============ ✅ Documentation generated ==============="
}

checkout_documentation_branch() {
    echo "============ ✅ Documentation Branch Initialization ==="
    # Checkout the gh-pages branch of this repository in a new folder
    git clone "${REPO_REMOTE_URL}" ../documentation
    cd ../documentation || exit

    DOC_BRANCH_EXIST=$(git ls-remote --heads "${REPO_REMOTE_URL}" gh-pages | wc -l)

    if [[ ${DOC_BRANCH_EXIST} -eq 0 ]]; then
        warn "Documentation" "Branch gh-pages not available"
        info "Documentation" "Create gh-pages branch"
        git checkout -b gh-pages
        EXIT_CODE=$?
        if [[ ${EXIT_CODE} -gt 0 ]]; then
            echo "============ 🔴 Unable to create documentation branch ="
            exit "${EXIT_CODE}"
        fi
        echo "Clean visible content"
        for file in *; do
            rm -rf "$file"
        done

        info "Documentation" "Clean hidden content"
        for file in .*; do
            if [[ "$file" != ".git" ]]; then
                rm -rf "$file"
            fi
        done
        git commit -a -m "Clean documentation branch"
        git push "https://${GITHUB_ACTOR}:${GITHUB_TOKEN}@github.com/${GITHUB_REPOSITORY}.git" gh-pages
    fi

    git checkout gh-pages
}

init_documentation_folder() {
    # Copy expected bases files
    cp "${DOC_TEMPLATE}/.gitignore" .
    cp "${DOC_TEMPLATE}/.nojekyll" .
    cp "${DOC_TEMPLATE}/favicon.png" .
    cp "${DOC_TEMPLATE}/index.html" .

    GENERATED_DOC_DIRECTORY=${VERSION}

    # Clean delete all SNAPSHOT directories
    for file in *; do
        if [[ -d "$file" && ! -L "$file" && "$file" == *"SNAPSHOT"* ]]; then
            echo "Delete directory $file"
            rm -rf "$file"
        fi
    done

    # Clean Current documentation
    if [[ -d "${GENERATED_DOC_DIRECTORY}" ]]; then
        info "Documentation" "Directory (${GENERATED_DOC_DIRECTORY}) already exist -> delete ${GENERATED_DOC_DIRECTORY} directory"
        rm -rf "${GENERATED_DOC_DIRECTORY}"
    fi
    rm versions.json
}

copy_documentation() {
    info "Documentation" "Create documentation directory (${GENERATED_DOC_DIRECTORY})"
    mkdir "${GENERATED_DOC_DIRECTORY}"
    cp "${DOC_TEMPLATE}/favicon.png" "${GENERATED_DOC_DIRECTORY}"
    cp -r "${PROJECT_DIR}/documentation/target/generated-docs/." "./${GENERATED_DOC_DIRECTORY}"
}

generate_current_documentation_link() {
    CURRENT_LINK="current"
    if [[ -L "${CURRENT_LINK}" ]]; then
        info "Documentation" "Current link exist -> delete link"
        rm "${CURRENT_LINK}"
    fi

    # Link to the more recent doc version
    LINK_PATH_TARGET=$(find . -maxdepth 1 \( ! -name '.*' ! -name '*SNAPSHOT*' \) -type d | sort -r | head -n 1)
    if [[ ${LINK_PATH_TARGET} == "" ]]; then
        LINK_PATH_TARGET=${GENERATED_DOC_DIRECTORY}
    fi
    info "Documentation" "Create symlink to directory ${LINK_PATH_TARGET}"
    ln -s "${LINK_PATH_TARGET}" "${CURRENT_LINK}"
}

generate_versions_file() {
    echo "[" >versions.json
    for file in *; do
        if [[ -d "$file" && ! -L "$file" ]]; then
            echo "  { \"version\": \"${file}\" }," >>versions.json
        fi
    done
    sed -i '$ s/.$//' versions.json
    echo "]" >>versions.json
}

push_documentation() {
    # Push the gh-pages changes
    git add .
    git commit -a -m "Update Documentation [skip ci]"
    git push "https://${GITHUB_ACTOR}:${GITHUB_TOKEN}@github.com/${GITHUB_REPOSITORY}.git" gh-pages
}

configure_documentation
generate_documentation

checkout_documentation_branch
init_documentation_folder
copy_documentation

generate_current_documentation_link
generate_versions_file
#push_documentation