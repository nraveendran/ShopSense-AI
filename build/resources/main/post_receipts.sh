#!/bin/bash

INPUT_FILE="$1"

if [[ -z "$INPUT_FILE" ]]; then
  echo "Usage: $0 <json_file>"
  exit 1
fi

while IFS= read -r json_line
do
  if [[ -n "$json_line" ]]; then
    curl -s -X POST http://localhost:8080/api/receipts \
      -H "Content-Type: application/json" \
      -d "$json_line"
    echo # newline for readability between requests
  fi
done < "$INPUT_FILE"
