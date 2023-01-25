package org.file.system.event;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.time.LocalDateTime;

public record Event(WatchEvent<?> watchEvent, Path file, LocalDateTime time) {
}
