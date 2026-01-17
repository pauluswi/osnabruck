#!/bin/bash

echo "=================================================="
echo "1. Submitting Transaction..."
echo "=================================================="

# Send request and capture response
RESPONSE=$(curl -s -X POST http://localhost:8080/api/transactions \
-H "Content-Type: application/json" \
-d '{
  "transactionId": "TX-1001",
  "senderAccount": "DE123456789",
  "receiverAccount": "DE987654321",
  "amount": 500.00,
  "currency": "EUR",
  "timestamp": "2023-10-27T10:00:00Z"
}')

echo "Response: $RESPONSE"

# Extract Trace ID using sed (works on Mac/Linux without jq)
TRACE_ID=$(echo $RESPONSE | sed -n 's/.*"traceId":"\([^"]*\)".*/\1/p')

if [ -z "$TRACE_ID" ]; then
  echo "Error: Could not extract Trace ID. Is the app running?"
  exit 1
fi

echo ""
echo "Captured Trace ID: $TRACE_ID"
echo "Waiting 1 second for mock blockchain..."
sleep 1

echo ""
echo "=================================================="
echo "2. Verifying Transaction (Integrity Check)"
echo "   Expected: VERIFIED"
echo "=================================================="

curl -s -X POST http://localhost:8080/api/verify \
-H "Content-Type: application/json" \
-d "{
  \"traceId\": \"$TRACE_ID\",
  \"transactionData\": {
    \"transactionId\": \"TX-1001\",
    \"senderAccount\": \"DE123456789\",
    \"receiverAccount\": \"DE987654321\",
    \"amount\": 500.00,
    \"currency\": \"EUR\",
    \"timestamp\": \"2023-10-27T10:00:00Z\"
  }
}"

echo ""
echo ""
echo "=================================================="
echo "3. Verifying Tampered Transaction"
echo "   Expected: TAMPERED (Amount changed to 500.01)"
echo "=================================================="

curl -s -X POST http://localhost:8080/api/verify \
-H "Content-Type: application/json" \
-d "{
  \"traceId\": \"$TRACE_ID\",
  \"transactionData\": {
    \"transactionId\": \"TX-1001\",
    \"senderAccount\": \"DE123456789\",
    \"receiverAccount\": \"DE987654321\",
    \"amount\": 500.01,
    \"currency\": \"EUR\",
    \"timestamp\": \"2023-10-27T10:00:00Z\"
  }
}"

echo ""
