package java8;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Builder
@Getter
@Setter
public class A {

    private int age;

    private String name;

}
