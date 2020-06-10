#!/usr/bin/env bash

#!/usr/bin/env bash

source logger.sh

REPO_REMOTE_URL=$(git config --get remote.origin.url)
POM="pom.xml"
POM_DOC="pom-studio-documentation.xml"
DOC_TEMPLATE=${TOOLS}/template/documentation
SOURCE_DIRECTORY="src/main/asciidoc"
DOCUMENTATION_DIRECTORY=""

echo ""
echo "============ Studio Publish Documentation ============"

lines=$(find src/main/asciidoc  2> /dev/null | wc -l)
if [ "$lines" -eq 0 ]; then
    lines=$(find docs/asciidoc  2> /dev/null | wc -l)
    if [ "$lines" -eq 0 ]; then
        echo "========= ✅ No Documentation found: Exit =============="
        exit 0
    fi
    SOURCE_DIRECTORY="docs/asciidoc"
fi

echo "============ ✅ Documentation found in ${SOURCE_DIRECTORY}"

configure_plugin() {
    echo "========= Start Maven plugin configuration ==========="

    cp "${POM}" "${POM_DOC}"
    POM_PROPERTIES=pom-properties.xml

    # Extract properties from POM
    xmlstarlet sel -t -c '//_:properties/*' -v "name()" -n "${POM_DOC}" >${POM_PROPERTIES}
    # Clean data
    sed -i 's/\"//g' ${POM_PROPERTIES}
    sed -i 's/ xmlns:xsi=http:\/\/www.w3.org\/2001\/XMLSchema-instance//g' ${POM_PROPERTIES}
    sed -i 's/ xmlns=http:\/\/maven.apache.org\/POM\/4.0.0//g' ${POM_PROPERTIES}

    # Extract properties names
    PROPERTIES=properties.txt
    xmlstarlet sel -t -m '//_:properties/*' -v "name()" -n "${POM_DOC}" >${PROPERTIES}
    properties=()
    while IFS= read -r entry; do
        properties+=("$entry")
    done <${PROPERTIES}

    # Sanitize properties names (maven asciidoctor plugin don't support attributes with a dot)
    for property in "${properties[@]}"; do
        sed -i "s/${property}/${property/\./-}/g" ${POM_PROPERTIES}
    done

    # Sanitize properties names again Regex Processing
    sed -i 's/\//|/g' ${POM_PROPERTIES}

    CONTENT=$(cat ${POM_PROPERTIES})
    PLUGIN_NAME=asciidoctor-maven-plugin
    sed -i "s/<PROJECT_PROPERTIES\/>/${CONTENT}/g" "${TOOLS}/template/${PLUGIN_NAME}.xml"

    # Un Sanitize properties names again Regex Processing
    sed -i 's/|/\//g' "${TOOLS}/template/${PLUGIN_NAME}.xml"

    # Set doc directory
    sed -i "s:@SOURCE_DIRECTORY:${SOURCE_DIRECTORY}:g" "${TOOLS}/template/${PLUGIN_NAME}.xml"

    add-plugin.sh "${POM_DOC}" "${PLUGIN_NAME}"

    echo "============ ✅ Plugin configured ====================="
}

configure_documentation() {
    # Copy header and footer
    cp "${DOC_TEMPLATE}/docinfo.html" "${SOURCE_DIRECTORY}"
    cp "${DOC_TEMPLATE}/docinfo-footer.html" "${SOURCE_DIRECTORY}"

    if [[ ! -d "src/test/resources" ]]; then
        mkdir -p "src/test/resources"
        info "Test resources directory created"
    fi

    cp -r "${DOC_TEMPLATE}/resources/org" "src/test/resources"
    echo "============ ✅ Documentation configured =============="
}

generate_documentation() {
    ./mvnw -f ${POM_DOC} --batch-mode clean package
    EXIT_CODE=$?
    if [[ ${EXIT_CODE} -gt 0 ]]; then
        echo "============ 🔴 Documentation generation has failled =="
        exit "${EXIT_CODE}"
    fi
    echo "============ ✅ Documentation generated ==============="
}

checkout_documentation_branch() {
    echo "============ ✅ Documentation Branch Initialized========"

    # Checkout the gh-pages branch of this repository in a new folder
    git clone "${REPO_REMOTE_URL}" ../documentation
    cd ../documentation || exit

    DOC_BRANCH_EXIST=$(git ls-remote --heads "${REPO_REMOTE_URL}" gh-pages | wc -l)

    if [[ ${DOC_BRANCH_EXIST} -eq 0 ]]; then
        warn "Branch gh-pages not available"
        info "Create gh-pages branch"
        git checkout -b gh-pages
        EXIT_CODE=$?
        if [[ ${EXIT_CODE} -gt 0 ]]; then
            echo "============ 🔴 Unable to create documentation branch ="
            exit "${EXIT_CODE}"
        fi
        info "Clean visible content"
        for file in *; do
            rm -rf "$file"
        done

        info "Clean hidden content"
        for file in .*; do
            if [[ "$file" != ".git" ]]; then
                rm -rf "$file"
            fi
        done
        git commit -a -m "Clean documentation branch"
        git push --set-upstream origin gh-pages
    fi

    git checkout gh-pages
}

init_documentation_folder() {
    # Copy expected bases files
    cp "${DOC_TEMPLATE}/.gitignore" .
    cp "${DOC_TEMPLATE}/.nojekyll" .
    cp "${DOC_TEMPLATE}/favicon.png" .
    cp "${DOC_TEMPLATE}/index.html" .

    DOCUMENTATION_DIRECTORY=${VERSION}

    # Clean delete all SNAPSHOT directories
    for file in *; do
        if [[ -d "$file" && ! -L "$file" && "$file" == *"SNAPSHOT"* ]]; then
            echo "Delete directory $file"
            rm -rf "$file"
        fi
    done

    # Clean Current documentation
    if [[ -d "${DOCUMENTATION_DIRECTORY}" ]]; then
        info "Directory (${DOCUMENTATION_DIRECTORY}) already exist -> delete ${DOCUMENTATION_DIRECTORY} directory"
        rm -rf "${DOCUMENTATION_DIRECTORY}"
    fi
    rm versions.json
}

copy_documentation() {
    info "Create documentation directory (${DOCUMENTATION_DIRECTORY})"
    mkdir "${DOCUMENTATION_DIRECTORY}"
    cp "${DOC_TEMPLATE}/favicon.png" "${DOCUMENTATION_DIRECTORY}"
    rsync -az "${CIRCLE_WORKING_DIRECTORY/\~/$HOME}/target/generated-docs/." "./${DOCUMENTATION_DIRECTORY}"
}

generate_current_documentation_link() {
    CURRENT_LINK="current"
    if [[ -L "${CURRENT_LINK}" ]]; then
        info "Current link exist -> delete link"
        rm "${CURRENT_LINK}"
    fi

    # Link to the more recent doc version
    LINK_PATH_TARGET=$(find . -maxdepth 1 \( ! -name '.*' ! -name '*SNAPSHOT*' \) -type d | sort -r | head -n 1)
    if [[ ${LINK_PATH_TARGET} == "" ]]; then
        LINK_PATH_TARGET=${DOCUMENTATION_DIRECTORY}
    fi
    info "Create symlink to directory ${LINK_PATH_TARGET}"
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
    git config --global user.name "${MACHINE_USER}"
    git config --global user.email "${MACHINE_USER_EMAIL}"

    # Push the gh-pages changes
    git add .
    git commit -a -m "Update Documentation [skip ci]"
    git pull origin gh-pages
    git push --force origin gh-pages
}

configure_plugin
configure_documentation
generate_documentation

checkout_documentation_branch
init_documentation_folder
copy_documentation

generate_current_documentation_link
generate_versions_file
push_documentation
