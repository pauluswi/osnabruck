package com.pswied.osnabruck.middleware.generated;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.10.0.
 */
@SuppressWarnings("rawtypes")
public class AuditLedger extends Contract {
    public static final String BINARY = "6080604052348015600e575f5ffd5b50610b668061001c5f395ff3fe608060405234801561000f575f5ffd5b5060043610610060575f3560e01c80630a5d033114610064578063293a7a311461007957806334461067146100a5578063ac5527ab146100b8578063bc9bf79b146100f6578063ca267f281461012f575b5f5ffd5b610077610072366004610818565b610136565b005b61008c6100873660046108a6565b61031b565b60405161009c949392919061090e565b60405180910390f35b61008c6100b3366004610957565b6105ad565b6100e66100c63660046108a6565b805160208183018101805160028252928201919093012091525460ff1681565b604051901515815260200161009c565b6101216101043660046108a6565b805160208183018101805160018252928201919093012091525481565b60405190815260200161009c565b5f54610121565b600283604051610146919061096e565b9081526040519081900360200190205460ff16156101c15760405162461bcd60e51b815260206004820152602d60248201527f4175646974207265636f726420666f722074686973205472616365204944206160448201526c6c72656164792065786973747360981b60648201526084015b60405180910390fd5b60408051608081018252848152602081018490524291810191909152606081018290525f8054600181018255908052815182916004027f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e563019081906102269082610a08565b506020820151600182019061023b9082610a08565b50604082015160028201556060820151600382019061025a9082610a08565b50505f805490915061026e90600190610ac3565b905080600186604051610281919061096e565b90815260200160405180910390208190555060016002866040516102a5919061096e565b908152604051908190036020018120805492151560ff19909316929092179091556102d190869061096e565b60405180910390207fa6c3d5ae6eba74324c9a0a6ed9c1e4d09d1aaf393c221d663d9f7dceb411700885428660405161030c93929190610ae8565b60405180910390a25050505050565b6060805f6060600285604051610331919061096e565b9081526040519081900360200190205460ff166103835760405162461bcd60e51b815260206004820152601060248201526f149958dbdc99081b9bdd08199bdd5b9960821b60448201526064016101b8565b5f600186604051610394919061096e565b90815260200160405180910390205490505f5f82815481106103b8576103b8610b1c565b905f5260205f2090600402016040518060800160405290815f820180546103de90610984565b80601f016020809104026020016040519081016040528092919081815260200182805461040a90610984565b80156104555780601f1061042c57610100808354040283529160200191610455565b820191905f5260205f20905b81548152906001019060200180831161043857829003601f168201915b5050505050815260200160018201805461046e90610984565b80601f016020809104026020016040519081016040528092919081815260200182805461049a90610984565b80156104e55780601f106104bc576101008083540402835291602001916104e5565b820191905f5260205f20905b8154815290600101906020018083116104c857829003601f168201915b505050505081526020016002820154815260200160038201805461050890610984565b80601f016020809104026020016040519081016040528092919081815260200182805461053490610984565b801561057f5780601f106105565761010080835404028352916020019161057f565b820191905f5260205f20905b81548152906001019060200180831161056257829003601f168201915b505050919092525050815160208301516040840151606090940151919b909a50929850965090945050505050565b5f81815481106105bb575f80fd5b905f5260205f2090600402015f91509050805f0180546105da90610984565b80601f016020809104026020016040519081016040528092919081815260200182805461060690610984565b80156106515780601f1061062857610100808354040283529160200191610651565b820191905f5260205f20905b81548152906001019060200180831161063457829003601f168201915b50505050509080600101805461066690610984565b80601f016020809104026020016040519081016040528092919081815260200182805461069290610984565b80156106dd5780601f106106b4576101008083540402835291602001916106dd565b820191905f5260205f20905b8154815290600101906020018083116106c057829003601f168201915b5050505050908060020154908060030180546106f890610984565b80601f016020809104026020016040519081016040528092919081815260200182805461072490610984565b801561076f5780601f106107465761010080835404028352916020019161076f565b820191905f5260205f20905b81548152906001019060200180831161075257829003601f168201915b5050505050905084565b634e487b7160e01b5f52604160045260245ffd5b5f82601f83011261079c575f5ffd5b813567ffffffffffffffff8111156107b6576107b6610779565b604051601f8201601f19908116603f0116810167ffffffffffffffff811182821017156107e5576107e5610779565b6040528181528382016020018510156107fc575f5ffd5b816020850160208301375f918101602001919091529392505050565b5f5f5f6060848603121561082a575f5ffd5b833567ffffffffffffffff811115610840575f5ffd5b61084c8682870161078d565b935050602084013567ffffffffffffffff811115610868575f5ffd5b6108748682870161078d565b925050604084013567ffffffffffffffff811115610890575f5ffd5b61089c8682870161078d565b9150509250925092565b5f602082840312156108b6575f5ffd5b813567ffffffffffffffff8111156108cc575f5ffd5b6108d88482850161078d565b949350505050565b5f81518084528060208401602086015e5f602082860101526020601f19601f83011685010191505092915050565b608081525f61092060808301876108e0565b828103602084015261093281876108e0565b9050846040840152828103606084015261094c81856108e0565b979650505050505050565b5f60208284031215610967575f5ffd5b5035919050565b5f82518060208501845e5f920191825250919050565b600181811c9082168061099857607f821691505b6020821081036109b657634e487b7160e01b5f52602260045260245ffd5b50919050565b601f821115610a0357805f5260205f20601f840160051c810160208510156109e15750805b601f840160051c820191505b81811015610a00575f81556001016109ed565b50505b505050565b815167ffffffffffffffff811115610a2257610a22610779565b610a3681610a308454610984565b846109bc565b6020601f821160018114610a68575f8315610a515750848201515b5f19600385901b1c1916600184901b178455610a00565b5f84815260208120601f198516915b82811015610a975787850151825560209485019460019092019101610a77565b5084821015610ab457868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b81810381811115610ae257634e487b7160e01b5f52601160045260245ffd5b92915050565b606081525f610afa60608301866108e0565b8460208401528281036040840152610b1281856108e0565b9695505050505050565b634e487b7160e01b5f52603260045260245ffdfea26469706673582212207dfc1e646abf1d3aeabeaacde994f61fb246d10271d8c0799c56a6177ddd2f6d64736f6c634300081e0033";

    public static final String FUNC_GETAUDITRECORD = "getAuditRecord";

    public static final String FUNC_GETRECORDCOUNT = "getRecordCount";

    public static final String FUNC_RECORDAUDIT = "recordAudit";

    public static final String FUNC_RECORDS = "records";

    public static final String FUNC_TRACEIDEXISTS = "traceIdExists";

    public static final String FUNC_TRACEIDTOINDEX = "traceIdToIndex";

    public static final Event AUDITRECORDCREATED_EVENT = new Event("AuditRecordCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected AuditLedger(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AuditLedger(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected AuditLedger(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected AuditLedger(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<AuditRecordCreatedEventResponse> getAuditRecordCreatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(AUDITRECORDCREATED_EVENT, transactionReceipt);
        ArrayList<AuditRecordCreatedEventResponse> responses = new ArrayList<AuditRecordCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AuditRecordCreatedEventResponse typedResponse = new AuditRecordCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.traceId = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.auditHash = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.sourceSystem = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static AuditRecordCreatedEventResponse getAuditRecordCreatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(AUDITRECORDCREATED_EVENT, log);
        AuditRecordCreatedEventResponse typedResponse = new AuditRecordCreatedEventResponse();
        typedResponse.log = log;
        typedResponse.traceId = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.auditHash = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.sourceSystem = (String) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<AuditRecordCreatedEventResponse> auditRecordCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getAuditRecordCreatedEventFromLog(log));
    }

    public Flowable<AuditRecordCreatedEventResponse> auditRecordCreatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(AUDITRECORDCREATED_EVENT));
        return auditRecordCreatedEventFlowable(filter);
    }

    public RemoteFunctionCall<Tuple4<String, String, BigInteger, String>> getAuditRecord(String _traceId) {
        final Function function = new Function(FUNC_GETAUDITRECORD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_traceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple4<String, String, BigInteger, String>>(function,
                new Callable<Tuple4<String, String, BigInteger, String>>() {
                    @Override
                    public Tuple4<String, String, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, String, BigInteger, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (String) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getRecordCount() {
        final Function function = new Function(FUNC_GETRECORDCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> recordAudit(String _traceId, String _auditHash, String _sourceSystem) {
        final Function function = new Function(
                FUNC_RECORDAUDIT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_traceId), 
                new org.web3j.abi.datatypes.Utf8String(_auditHash), 
                new org.web3j.abi.datatypes.Utf8String(_sourceSystem)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple4<String, String, BigInteger, String>> records(BigInteger param0) {
        final Function function = new Function(FUNC_RECORDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple4<String, String, BigInteger, String>>(function,
                new Callable<Tuple4<String, String, BigInteger, String>>() {
                    @Override
                    public Tuple4<String, String, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, String, BigInteger, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (String) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Boolean> traceIdExists(String param0) {
        final Function function = new Function(FUNC_TRACEIDEXISTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> traceIdToIndex(String param0) {
        final Function function = new Function(FUNC_TRACEIDTOINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static AuditLedger load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AuditLedger(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static AuditLedger load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AuditLedger(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static AuditLedger load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new AuditLedger(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static AuditLedger load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new AuditLedger(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<AuditLedger> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(AuditLedger.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<AuditLedger> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AuditLedger.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<AuditLedger> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(AuditLedger.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<AuditLedger> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AuditLedger.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class AuditRecordCreatedEventResponse extends BaseEventResponse {
        public byte[] traceId;

        public String auditHash;

        public BigInteger timestamp;

        public String sourceSystem;
    }
}
