/*
 *
 *  * Copyright 2024 New Relic Corporation. All rights reserved.
 *  * SPDX-License-Identifier: Apache-2.0
 *
 */

package software.amazon.awssdk.services.bedrockruntime;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import software.amazon.awssdk.core.client.config.SdkClientConfiguration;
import software.amazon.awssdk.core.client.handler.AsyncClientHandler;
import software.amazon.awssdk.protocols.json.AwsJsonProtocolFactory;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static com.newrelic.utils.BedrockRuntimeUtil.incrementBedrockInstrumentedMetric;

/**
 * Service client for accessing Amazon Bedrock Runtime asynchronously.
 */
@Weave(type = MatchType.ExactClass, originalName = "software.amazon.awssdk.services.bedrockruntime.DefaultBedrockRuntimeAsyncClient")
final class DefaultBedrockRuntimeAsyncClient_Instrumentation {
//    private static final Logger log = LoggerFactory.getLogger(DefaultBedrockRuntimeAsyncClient.class);
//
//    private static final AwsProtocolMetadata protocolMetadata = AwsProtocolMetadata.builder()
//            .serviceProtocol(AwsServiceProtocol.REST_JSON).build();

    private final AsyncClientHandler clientHandler;

    private final AwsJsonProtocolFactory protocolFactory;

    private final SdkClientConfiguration clientConfiguration;

    private final BedrockRuntimeServiceClientConfiguration serviceClientConfiguration;

    private final Executor executor;

    protected DefaultBedrockRuntimeAsyncClient_Instrumentation(BedrockRuntimeServiceClientConfiguration serviceClientConfiguration,
            SdkClientConfiguration clientConfiguration) {
        this.clientHandler = Weaver.callOriginal();
        this.clientConfiguration = Weaver.callOriginal();
        this.serviceClientConfiguration = Weaver.callOriginal();
        this.protocolFactory = Weaver.callOriginal();
        this.executor = Weaver.callOriginal();
    }

    @Trace
    public CompletableFuture<InvokeModelResponse> invokeModel(InvokeModelRequest invokeModelRequest) {
        CompletableFuture<InvokeModelResponse> invokeModelResponseFuture = Weaver.callOriginal();

        // FIXME needs to be incremented constantly for UI
        incrementBedrockInstrumentedMetric();

        System.out.println("Request: " + invokeModelRequest);
        System.out.println("Request Body: " + invokeModelRequest.body());

        return invokeModelResponseFuture;
    }

}
