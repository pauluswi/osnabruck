// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

/**
 * @title AuditLedger
 * @dev Immutable registry for transaction audit hashes.
 */
contract AuditLedger {

    struct AuditRecord {
        string traceId;
        string auditHash;
        uint256 timestamp;
        string sourceSystem;
    }

    // Array to store all audit records
    AuditRecord[] public records;

    // Mapping for quick lookup by Trace ID
    mapping(string => uint256) public traceIdToIndex;
    mapping(string => bool) public traceIdExists;

    // Event to enable off-chain indexing and verification
    event AuditRecordCreated(
        string indexed traceId,
        string auditHash,
        uint256 timestamp,
        string sourceSystem
    );

    /**
     * @dev Records a new audit hash on the blockchain.
     * @param _traceId The unique trace ID of the transaction.
     * @param _auditHash The SHA-256 hash of the transaction payload.
     * @param _sourceSystem Identifier of the system originating the request.
     */
    function recordAudit(string memory _traceId, string memory _auditHash, string memory _sourceSystem) public {
        require(!traceIdExists[_traceId], "Audit record for this Trace ID already exists");

        AuditRecord memory newRecord = AuditRecord({
            traceId: _traceId,
            auditHash: _auditHash,
            timestamp: block.timestamp,
            sourceSystem: _sourceSystem
        });

        records.push(newRecord);
        uint256 index = records.length - 1;

        traceIdToIndex[_traceId] = index;
        traceIdExists[_traceId] = true;

        emit AuditRecordCreated(_traceId, _auditHash, block.timestamp, _sourceSystem);
    }

    /**
     * @dev Retrieves an audit record by Trace ID.
     */
    function getAuditRecord(string memory _traceId) public view returns (string memory, string memory, uint256, string memory) {
        require(traceIdExists[_traceId], "Record not found");

        uint256 index = traceIdToIndex[_traceId];
        AuditRecord memory record = records[index];

        return (record.traceId, record.auditHash, record.timestamp, record.sourceSystem);
    }

    /**
     * @dev Returns the total number of audit records.
     */
    function getRecordCount() public view returns (uint256) {
        return records.length;
    }
}
