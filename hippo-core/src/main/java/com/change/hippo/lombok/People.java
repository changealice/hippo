package com.change.hippo.lombok;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * User: change.long
 * Date: 16/6/29
 * Time: 下午7:04
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class People implements Serializable {

    //    @Getter @Setter
    private String id;
    //    @Getter @Setter
    private String name;

}
