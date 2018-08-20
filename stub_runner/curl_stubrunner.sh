#!/usr/bin/env bash
STUBRUNNER_PORT="${STUBRUNNER_PORT:-8765}"

curl "localhost:${STUBRUNNER_PORT}/v1/charges?limit=25" | jq