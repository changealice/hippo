package com.change.hippo;

import com.change.hippo.dto.UserDto;
import com.change.hippo.model.User;

import java.util.ArrayList;
import java.util.List;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * User: change.long Date: 16/6/14 Time: 上午9:47
 */
public class BeanMapper {

    private final static MapperFacade mapper;

    static {
        DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapper = mapperFactory.getMapperFacade();
    }

    private BeanMapper() {
    }

    /**
     * 基于Dozer转换对象的类型.
     */
    public static <S, D> D map(S source, Class<D> destinationClass) {
        return mapper.map(source, destinationClass);
    }

    /**
     * 基于Dozer转换Collection中对象的类型.
     */
    public static <S, D> List<D> mapList(Iterable<S> sourceList,
                                         Class<D> destinationClass) {
        return mapper.mapAsList(sourceList, destinationClass);
    }

    public static void main(String[] args) {

        User user = new User();
        user.setUsername("123123");
        user.setAddress("上海");
        long start = System.currentTimeMillis();
        for (int i = 0, len = 100000; i < len; i++) {
            UserDto userDto = BeanMapper.map(user, UserDto.class);
//			System.out.println(userDto);
        }
        System.out.println("copy bean ms:" + (System.currentTimeMillis() - start));

        final List<User> userList = new ArrayList<User>();
        for (int i = 0, len = 100000; i < len; i++) {
            User userTemp = new User();
            userTemp.setUsername("123123");
            userTemp.setAddress("上海");
            userList.add(userTemp);
        }
        start = System.currentTimeMillis();
        List<UserDto> userCopyList = BeanMapper.mapList(userList, UserDto.class);
        System.out.println("copy list cost ms:" + (System.currentTimeMillis() - start));
//		System.out.println(userCopyList);
    }

}
