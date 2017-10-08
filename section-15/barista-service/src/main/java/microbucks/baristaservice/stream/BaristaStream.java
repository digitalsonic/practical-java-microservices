package microbucks.baristaservice.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface BaristaStream {
    @Input
    SubscribableChannel barista();

    @Output
    MessageChannel order();
}
