package org.cloudutils.queues.publisher;

import com.microsoft.azure.servicebus.Message;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class MessageBuilderTest {

    @Before
    public void setup() {
    }

    @Test
    public void toMessage_runsSuccessfully() {
        MessageBuilder messageBuilder = MessageBuilder.testBuilder().build();
        Message message = messageBuilder.toMessage();

        MatcherAssert.assertThat(messageBuilder.messageId(), is(message.getMessageId()));
        MatcherAssert.assertThat(messageBuilder.contentType(), is(message.getContentType()));
        MatcherAssert.assertThat(messageBuilder.label().get(), is(message.getLabel()));
        MatcherAssert.assertThat(messageBuilder.timeToLive(), is(message.getTimeToLive()));
    }
}
