package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

/**
 * @author Zoujialiang
 * @date 2020/10/19 17:30
 */
@Data
@AllArgsConstructor
public class UserBuy {
    private String nickName;
    private String dyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return ((UserBuy) o).getNickName().substring(0, 1).equals(this.nickName.substring(0, 1)) && nickName.substring(nickName.length() - 1).equals(((UserBuy) o).getNickName().substring(((UserBuy) o).getNickName().length() - 1));
    }

    @Override
    public int hashCode() {
        String substring = nickName.substring(0, 1);
        char c = substring.charAt(0);
        return Objects.hash(c);
    }
}
