#!/bin/bash
if [[ -z ${VERSION} ]]; then
  VERSION="latest"
fi
docker build . -f docker/Dockerfile -t "${REGISTRY}"github-tech-test:${VERSION} --no-cache