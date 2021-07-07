package com.tyzj.mapper;

import com.tyzj.pojo.Pojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PhoneMapper {

    void InsertPhoneList(@Param("phonelist") List<Pojo> phonelist);

}
